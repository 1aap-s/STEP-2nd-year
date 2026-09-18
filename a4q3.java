class Canteen {
    private String canteenCode;
    private String canteenName;
    private int trustScore;

    
    public Canteen(String canteenCode, String canteenName, int trustScore) {
        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    // Constructor chaining
    public Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, 3);
    }

    public int compareTo(Canteen other) {

        
        if (this.trustScore != other.trustScore) {
            return other.trustScore - this.trustScore;// 1. Higher trust score first
        }

        
        String thisCode = this.canteenCode
                .replaceAll("\\s+", "")
                .toLowerCase();// 2. Code as tie-breaker

        String otherCode = other.canteenCode
                .replaceAll("\\s+", "")
                .toLowerCase();

        int codeResult = thisCode.compareTo(otherCode);

        if (codeResult != 0) {
            return codeResult;
        }

        // 3. Name length as final tie-breaker
        return this.canteenName.length() - other.canteenName.length();
    }

    public static Canteen[] rankCanteens(Canteen[] canteens) {

        // Manual selection sort
        for (int i = 0; i < canteens.length - 1; i++) {

            int best = i;

            for (int j = i + 1; j < canteens.length; j++) {

                if (canteens[j].compareTo(canteens[best]) < 0) {
                    best = j;
                }
            }

            // Swap
            Canteen temp = canteens[i];
            canteens[i] = canteens[best];
            canteens[best] = temp;
        }

        return canteens;
    }

    public String getCode() {
        return canteenCode;
    }
}

public class a4q3 {
    public static void main(String[] args) {

        Canteen[] canteens = {
            new Canteen("HB3-C", "Spice Junction", 3),
            new Canteen("hb1- c", "Grand Mess", 5),
            new Canteen("HB2-C", "Southern Treats")
        };

        Canteen[] ranked = Canteen.rankCanteens(canteens);

        for (Canteen c : ranked) {
            System.out.println(c.getCode());
        }
    }
}
