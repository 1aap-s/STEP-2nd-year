class BookInventory {

    private int copiesTotal;
    private int copiesAvailable;

    public BookInventory(int copiesTotal) {

        if (copiesTotal <= 0) {
            throw new IllegalArgumentException(
                "copiesTotal must be positive"
            );
        }

        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    public void checkOut() {

        // Don't allow available copies below 0
        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }

    public void checkIn() {

        // Don't allow available copies above total
        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }
}


public class a5q3 {

    public static void main(String[] args) {

        // Test invalid construction
        try {

            BookInventory b1 =
                new BookInventory(0);

            System.out.println(
                "Construction successful"
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                "Construction rejected"
            );
        }


        // Test checkout
        BookInventory b =
            new BookInventory(3);

        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut();   // rejected

        System.out.println(
            "Available after checkout: "
            + b.getCopiesAvailable()
        );


        // Test check-in
        b.checkIn();
        b.checkIn();
        b.checkIn();
        b.checkIn();    // rejected

        System.out.println(
            "Available after check-in: "
            + b.getCopiesAvailable()
        );
    }
}
