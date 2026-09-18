class AccessChecker {
    public static String classifyAccess(
            String fieldModifier,
            String accessorContext) {

        // private
        if (fieldModifier.equals("private")) {

            if (accessorContext.equals("SAME_CLASS")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        // default
        if (fieldModifier.equals("default")) {

            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        // protected
        if (fieldModifier.equals("protected")) {

            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE") ||
                accessorContext.equals(
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {

                return "ALLOWED";
            }

            return "DENIED";
        }

        // public
        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    // Convert underscore-separated text
    // into title case
    public static String describeContext(
            String accessorContext) {

        String[] words =
            accessorContext.split("_");

        String result = "";

        for (String word : words) {

            String formatted =
                word.substring(0, 1).toUpperCase()
                + word.substring(1).toLowerCase();

            result += formatted + " ";
        }

        return result.trim();
    }
}


public class a5q2 {

    public static void main(String[] args) {

        System.out.println(
            AccessChecker.classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
            )
        );

        System.out.println(
            AccessChecker.classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
            )
        );

        System.out.println(
            AccessChecker.describeContext(
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
            )
        );

        // More tests
        System.out.println(
            AccessChecker.classifyAccess(
                "private",
                "SAME_CLASS"
            )
        );

        System.out.println(
            AccessChecker.classifyAccess(
                "default",
                "DIFFERENT_PACKAGE"
            )
        );

        System.out.println(
            AccessChecker.classifyAccess(
                "public",
                "DIFFERENT_PACKAGE"
            )
        );
    }
}
