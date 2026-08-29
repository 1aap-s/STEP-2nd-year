class LibraryMember {

    static String name;
    static String memberId;
    static int booksIssued;


    LibraryMember(String name, String memberId, int booksIssued) {
        LibraryMember.name = name;
        LibraryMember.memberId = memberId;
        LibraryMember.booksIssued = booksIssued;
    }

    void printBrokenCard() {
        System.out.println(name);
    }


    String fixedName;
    String fixedMemberId;
    int fixedBooksIssued;


    static String libraryName = "Central Library";
    static int memberCount = 1000;

    LibraryMember(String name, int booksIssued, boolean fixed) {

        this.fixedName = name;
        this.fixedBooksIssued = booksIssued;

        memberCount++;
        this.fixedMemberId = "LM-" + memberCount;
    }

    void printMemberCard() {
        System.out.println(
            fixedName + " | " + fixedMemberId
        );
    }

    static void printTotalMembers() {
        System.out.println("Total members: " + (memberCount - 1000));
    }
}


public class a3q4 {

    public static void main(String[] args) {


        System.out.println("Broken version:");

        LibraryMember member1 =
            new LibraryMember("Aditi", "LM-1001", 2);

        LibraryMember member2 =
            new LibraryMember("Rohan", "LM-1002", 3);

        member1.printBrokenCard();
        member2.printBrokenCard();


        System.out.println("\nFixed version:");

        LibraryMember fixed1 =
            new LibraryMember("Aditi", 2, true);

        LibraryMember fixed2 =
            new LibraryMember("Rohan", 3, true);

        fixed1.printMemberCard();
        fixed2.printMemberCard();

        LibraryMember.printTotalMembers();
    }
}
