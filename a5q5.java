final class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    private static String systemName;

    // Static block runs once
    static {
        systemName = "Nightly Circulation Ledger";
    }


    // Constructor
    public LoanReceipt(
            String memberId,
            String[] bookIds) {

        if (memberId == null || bookIds == null) {
            throw new IllegalArgumentException(
                "Invalid receipt data"
            );
        }

        if (bookIds.length > 20) {
            throw new IllegalArgumentException(
                "Maximum 20 books allowed"
            );
        }


        // Validate every book ID
        for (String id : bookIds) {

            if (id == null ||
                !id.matches("BK-\\d{3}")) {

                throw new IllegalArgumentException(
                    "Invalid book ID: " + id
                );
            }
        }


        this.memberId = memberId;

        // Defensive copy IN
        this.bookIds = bookIds.clone();
    }


    // Defensive copy OUT
    public String[] getBookIds() {
        return bookIds.clone();
    }


    // Wither method
    public LoanReceipt withCorrectedBookId(
            int index,
            String newId) {

        if (index < 0 ||
            index >= bookIds.length) {

            throw new IndexOutOfBoundsException(
                "Invalid index"
            );
        }


        if (newId == null ||
            !newId.matches("BK-\\d{3}")) {

            throw new IllegalArgumentException(
                "Invalid book ID"
            );
        }


        // Make a copy
        String[] corrected =
            bookIds.clone();

        // Modify the COPY
        corrected[index] = newId;


        // Return a completely new object
        return new LoanReceipt(
            memberId,
            corrected
        );
    }


    // Nightly processing
    public static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        if (receipts == null) {
            throw new IllegalArgumentException(
                "Receipts cannot be null"
            );
        }


        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;


        for (LoanReceipt receipt : receipts) {

            // Handle null safely
            if (receipt == null) {
                nullSkipped++;
                continue;
            }


            processed++;


            // Runtime type checking
            if (receipt instanceof ReferenceOnlyLoanReceipt) {

                referenceOnly++;

            } else {

                regular++;
            }
        }


        return processed + " processed | " +
               nullSkipped + " null skipped | " +
               referenceOnly + " reference-only | " +
               regular + " regular";
    }
}


// Child class
class ReferenceOnlyLoanReceipt
        extends LoanReceipt {

    private String roomNumber;


    public ReferenceOnlyLoanReceipt(
            String memberId,
            String[] bookIds,
            String roomNumber) {

        super(memberId, bookIds);

        this.roomNumber = roomNumber;
    }
}


public class a5q5 {

    public static void main(String[] args) {

        try {

            LoanReceipt invalid =
                new LoanReceipt(
                    "LIB-8841",
                    new String[]{
                        "BK-100",
                        "bad"
                    }
                );

            System.out.println(
                "Construction successful"
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                "Construction rejected"
            );
        }

        LoanReceipt r =
            new LoanReceipt(
                "LIB-8841",
                new String[]{
                    "BK-100",
                    "BK-101"
                }
            );


        String[] ids = r.getBookIds();

        // Modify returned array
        ids[0] = "HACKED";


        // Original receipt remains unchanged
        System.out.println(
            "Original ID: "
            + r.getBookIds()[0]
        );
        LoanReceipt corrected =
            r.withCorrectedBookId(
                0,
                "BK-999"
            );


        System.out.println(
            "Original receipt ID: "
            + r.getBookIds()[0]
        );

        System.out.println(
            "Corrected receipt ID: "
            + corrected.getBookIds()[0]
        );
        LoanReceipt[] receipts = {

            new ReferenceOnlyLoanReceipt(
                "LIB-001",
                new String[]{
                    "BK-200"
                },
                "Reading Room 3"
            ),

            null,

            new LoanReceipt(
                "LIB-002",
                new String[]{
                    "BK-201"
                }
            )
        };


        System.out.println(
            LoanReceipt.processNightlyCirculation(
                receipts
            )
        );
    }
}
