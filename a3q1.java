public class a3q1 {

    String title;
    String borrowerName;
    int daysOverdue;

    BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    double fineAmount() {
        if (daysOverdue > 0) {
            return daysOverdue * 5;
        } else {
            return 0;
        }
    }

    boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    static double totalFineCollected(BookIssue[] issues) {

        double total = 0;

        for (int i = 0; i < issues.length; i++) {
            total += issues[i].fineAmount();
        }

        return total;
    }

    public static void main(String[] args) {

        a3q1[] issues = {
            new a3q1("Clean Code", "Arun", 18),
            new a3q1("Effective Java", "Bala", 5),
            new a3q1("Refactoring", "Charan", 0),
            new a3q1("DSA Handbook", "David", 21),
            new a3q1("Design Patterns", "Ezhil", 9)
        };

        for (int i = 0; i < issues.length; i++) {

            if (issues[i].isSeverelyOverdue()) {
                System.out.println(
                    issues[i].title + " - " +
                    issues[i].daysOverdue +
                    " days - Severely overdue"
                );
            } else {
                System.out.println(
                    issues[i].title + " - " +
                    issues[i].daysOverdue +
                    " days - OK"
                );
            }
        }
        double total = BookIssue.totalFineCollected(issues);

        System.out.println("Total fine collected: Rs " + total);
    }
}
