final class SurgeFeeCalculator {

    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {

        if (minimumSurgePercent < 0) {
            throw new IllegalArgumentException(
                "Minimum surge percent cannot be negative"
            );
        }

        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(
            double orderValue, int delayMinutes) {

        // Validation at calculation time
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

        // No delay = no surge fee
        if (delayMinutes == 0) {
            return 0.0;
        }

        double fee = 0.0;

        // First 5 minutes at 0.5%
        int firstTier = Math.min(delayMinutes, 5);
        fee += firstTier * orderValue * 0.005;

        // Minutes 6-15 at 1%
        if (delayMinutes > 5) {
            int secondTier = Math.min(delayMinutes - 5, 10);
            fee += secondTier * orderValue * 0.01;
        }

        // Minutes 16+ at 2%
        if (delayMinutes > 15) {
            int thirdTier = delayMinutes - 15;
            fee += thirdTier * orderValue * 0.02;
        }

        // Minimum floor applies only when delayed
        double minimumFee =
            orderValue * minimumSurgePercent / 100.0;

        return Math.max(fee, minimumFee);
    }
}

public class Main {
    public static void main(String[] args) {

        SurgeFeeCalculator calculator =
            new SurgeFeeCalculator(1.0);

        System.out.println(
            calculator.calculateSurgeFee(500, 0)
        );

        System.out.println(
            calculator.calculateSurgeFee(500, 1)
        );

        System.out.println(
            calculator.calculateSurgeFee(500, 16)
        );
    }
}
