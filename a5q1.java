class LibraryMember {

    private String membershipId;
    private String branchCode;
    private double finesOwed;
    private String displayName;
  
    public LibraryMember(String membershipId,
                         String branchCode,
                         double finesOwed,
                         String displayName) {

        if (membershipId == null) {
            throw new IllegalArgumentException("Invalid membership ID");
        }

        String id = membershipId.trim();

        if (id.isEmpty() || id.length() < 4) {
            throw new IllegalArgumentException("Invalid membership ID");
        }

        this.membershipId = id;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }

    // Check access according to Java visibility rules
    public static String classifyAccess(String fieldModifier,
                                        String accessorContext) {

        if (fieldModifier.equals("private")) {

            if (accessorContext.equals("SAME_CLASS")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("default")) {

            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("protected")) {

            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    // Count allowed/denied for each modifier
    public static String summarizeByModifier(String[][] attempts) {

        String[] modifiers = {
            "private",
            "default",
            "protected",
            "public"
        };

        int[] allowed = new int[4];
        int[] denied = new int[4];

        for (String[] attempt : attempts) {

            String modifier = attempt[0];
            String context = attempt[1];

            String result =
                classifyAccess(modifier, context);

            int index = -1;

            for (int i = 0; i < modifiers.length; i++) {

                if (modifiers[i].equals(modifier)) {
                    index = i;
                    break;
                }
            }

            if (result.equals("ALLOWED")) {
                allowed[index]++;
            } else {
                denied[index]++;
            }
        }

        return "private: " + allowed[0] + " allowed / " +
               denied[0] + " denied | " +

               "default: " + allowed[1] + " allowed / " +
               denied[1] + " denied | " +

               "protected: " + allowed[2] + " allowed / " +
               denied[2] + " denied | " +

               "public: " + allowed[3] + " allowed / " +
               denied[3] + " denied";
    }
}


public class a5q1 {

    public static void main(String[] args) {

        // Test classifyAccess()
        System.out.println(
            LibraryMember.classifyAccess(
                "private",
                "SAME_CLASS"
            )
        );

        System.out.println(
            LibraryMember.classifyAccess(
                "protected",
                "DIFFERENT_PACKAGE"
            )
        );

        // Test summarizeByModifier()
        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
            LibraryMember.summarizeByModifier(attempts)
        );

        // Test constructor validation
        try {
            LibraryMember member =
                new LibraryMember(
                    "LB9",
                    "BR1",
                    0,
                    "Priya Nair"
                );

            System.out.println("Construction successful");

        } catch (IllegalArgumentException e) {
            System.out.println("Construction rejected");
        }

        // Valid ID
        try {
            LibraryMember member =
                new LibraryMember(
                    "LB94",
                    "BR1",
                    0,
                    "Priya Nair"
                );

            System.out.println("Valid construction successful");

        } catch (IllegalArgumentException e) {
            System.out.println("Construction rejected");
        }
    }
}
