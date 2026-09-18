class DeliveryAccount {

    private String studentId;
    private double orderValue;

    private static String systemName;

    // One-time class-level setup
    static {
        systemName = "Nightly Reconciliation Engine";
    }

    // Full constructor
    public DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    // Provisional constructor
    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    // Final surge calculation
    public final double calculateSurgeFee(int delayMinutes) {

        if (orderValue < 0) {
            throw new IllegalArgumentException(
                "Order value cannot be negative"
            );
        }

        if (delayMinutes < 0) {
            throw new IllegalArgumentException(
                "Delay cannot be negative"
            );
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double fee = 0.0;

        // 1-5 minutes → 0.5%
        int firstTier = Math.min(delayMinutes, 5);
        fee += firstTier * orderValue * 0.005;

        // 6-15 minutes → 1%
        if (delayMinutes > 5) {
            int secondTier = Math.min(delayMinutes - 5, 10);
            fee += secondTier * orderValue * 0.01;
        }

        // 16+ minutes → 2%
        if (delayMinutes > 15) {
            int thirdTier = delayMinutes - 15;
            fee += thirdTier * orderValue * 0.02;
        }

        // Minimum floor = 1%
        double minimumFee = orderValue * 0.01;

        return Math.max(fee, minimumFee);
    }

    public void processAccount(
            DeliveryAccount account,
            double amount,
            int delayMinutes) {

        if (account == null) {
            return;
        }

        double fee = account.calculateSurgeFee(delayMinutes);

        if (account instanceof PremiumAccount) {
            fee = fee * 0.5;
        }

        System.out.println(
            "Student: " + account.studentId +
            " | Surge Fee: Rs " + fee
        );
    }

    public static void processBatch(
            DeliveryAccount[] accounts,
            double[] amounts,
            int[] delayMinutesArray) {

        // Validate parallel arrays
        if (accounts == null ||
            amounts == null ||
            delayMinutesArray == null) {

            throw new IllegalArgumentException(
                "Input arrays cannot be null"
            );
        }

        if (accounts.length != amounts.length ||
            accounts.length != delayMinutesArray.length) {

            throw new IllegalArgumentException(
                "Parallel arrays must have equal lengths"
            );
        }

        int processed = 0;
        int nullSkipped = 0;
        int premium = 0;
        int regular = 0;

        double totalSurgeFees = 0.0;

        DeliveryAccount processor =
            new DeliveryAccount("PROCESSOR", 0);

        for (int i = 0; i < accounts.length; i++) {

            DeliveryAccount account = accounts[i];

            if (account == null) {
                nullSkipped++;
                continue;
            }

            try {
                processor.processAccount(
                    account,
                    amounts[i],
                    delayMinutesArray[i]
                );

                double fee =
                    account.calculateSurgeFee(
                        delayMinutesArray[i]
                    );

                if (account instanceof PremiumAccount) {
                    fee = fee * 0.5;
                    premium++;
                } else {
                    regular++;
                }

                totalSurgeFees += fee;
                processed++;

            } catch (IllegalArgumentException e) {
                System.out.println(
                    "Invalid account at index " + i
                );
            }
        }

        System.out.println(
            processed + " processed | " +
            nullSkipped + " null skipped | " +
            premium + " premium | " +
            regular + " regular | " +
            "grand total surge fees = Rs " +
            totalSurgeFees
        );
    }

    public String getStudentId() {
        return studentId;
    }
}


// Child class
class PremiumAccount extends DeliveryAccount {

    public PremiumAccount(
            String studentId,
            double orderValue) {

        super(studentId, orderValue);
    }

    public PremiumAccount(String studentId) {
        super(studentId);
    }
}


public class a4q5 {

    public static void main(String[] args) {

        DeliveryAccount[] accounts = {
            new PremiumAccount("STU001", 500),
            null,
            new DeliveryAccount("STU002", 300)
        };

        double[] amounts = {
            500,
            400,
            300
        };

        int[] delayMinutesArray = {
            10,
            5,
            0
        };

        DeliveryAccount.processBatch(
            accounts,
            amounts,
            delayMinutesArray
        );
    }
}
