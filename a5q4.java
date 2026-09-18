class LibraryMember {

    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswerHash;
    // 1. No-argument constructor
    public LibraryMember() {
        this(null, null);
    }


    // 2. Name-only constructor
    public LibraryMember(String name) {
        this(null, name);
    }


    // 3. Main constructor
    public LibraryMember(
            String membershipId,
            String name) {

        this.membershipId = membershipId;
        this.name = name;
        this.premiumMember = false;
    }


    // membershipId getter
    public String getMembershipId() {
        return membershipId;
    }


    // membershipId setter
    // Can work only once
    public void setMembershipId(String id) {

        if (this.membershipId == null) {
            this.membershipId = id;
        }
    }


    // name getter
    public String getName() {
        return name;
    }


    // name setter
    public void setName(String name) {
        this.name = name;
    }


    // boolean getter
    public boolean isPremiumMember() {
        return premiumMember;
    }


    // boolean setter
    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }


    // Write-only security answer
    public void setSecurityAnswer(String answer) {

        if (answer != null) {

            // Deterministic one-way transformation
            this.securityAnswerHash =
                Integer.toHexString(
                    answer.hashCode()
                );
        }
    }

    // IMPORTANT:
    // There is NO getter for securityAnswerHash.
}


public class a5q4 {

    public static void main(String[] args) {

        // Test name-only constructor
        LibraryMember m1 =
            new LibraryMember("Priya Nair");

        System.out.println(
            "Membership ID: "
            + m1.getMembershipId()
        );


        // Test id + name constructor
        LibraryMember m2 =
            new LibraryMember(
                "LIB-8841",
                "Priya Nair"
            );

        System.out.println(
            "Membership ID: "
            + m2.getMembershipId()
        );


        // Test write-once membership ID
        LibraryMember m3 =
            new LibraryMember();

        m3.setMembershipId("LIB-8841");
        m3.setMembershipId("FAKE-0000");

        System.out.println(
            "Final ID: "
            + m3.getMembershipId()
        );


        // Test premium member
        m3.setPremiumMember(true);

        System.out.println(
            "Premium: "
            + m3.isPremiumMember()
        );


        // Test security answer
        m3.setSecurityAnswer("Blue");

        System.out.println(
            "Security answer has been set."
        );

        // There is intentionally no way to retrieve it.
    }
}
