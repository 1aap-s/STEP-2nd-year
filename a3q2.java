class Employee {

    private int empId;
    private String empName;
    private double salary;

    Employee(int empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}


class ManagerEmployee extends Employee {

    private double teamBonus;

    ManagerEmployee(int empId, String empName, double salary, double teamBonus) {

        super(empId, empName, salary);

        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}


class InternEmployee extends Employee {

    private double stipendCap;

    InternEmployee(int empId, String empName, double salary, double stipendCap) {

        super(empId, empName, salary);

        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {

        if (getSalary() < stipendCap) {
            return getSalary();
        } else {
            return stipendCap;
        }
    }
}


public class a3q2 {

    public static void main(String[] args) {

        Employee plain = new Employee(101, "Arun", 40000);

        Employee manager =
            new ManagerEmployee(102, "Bala", 70000, 8000);

        Employee intern =
            new InternEmployee(103, "Charan", 12000, 10000);


        if (plain instanceof ManagerEmployee) {

            ManagerEmployee m = (ManagerEmployee) plain;
            System.out.println("Manager effective pay: Rs " +
                               m.effectiveSalary());

        } else if (plain instanceof InternEmployee) {

            InternEmployee i = (InternEmployee) plain;
            System.out.println("Intern effective pay: Rs " +
                               i.effectiveSalary());

        } else {

            System.out.println("Plain employee pay: Rs " +
                               plain.getSalary());
        }

        if (manager instanceof ManagerEmployee) {

            ManagerEmployee m = (ManagerEmployee) manager;

            System.out.println("Manager effective pay: Rs " +
                               m.effectiveSalary());

        } else if (manager instanceof InternEmployee) {

            InternEmployee i = (InternEmployee) manager;

            System.out.println("Intern effective pay: Rs " +
                               i.effectiveSalary());

        } else {

            System.out.println("Plain employee pay: Rs " +
                               manager.getSalary());
        }

        if (intern instanceof ManagerEmployee) {

            ManagerEmployee m = (ManagerEmployee) intern;

            System.out.println("Manager effective pay: Rs " +
                               m.effectiveSalary());

        } else if (intern instanceof InternEmployee) {

            InternEmployee i = (InternEmployee) intern;

            System.out.println("Intern effective pay: Rs " +
                               i.effectiveSalary());

        } else {

            System.out.println("Plain employee pay: Rs " +
                               intern.getSalary());
        }
    }
}
