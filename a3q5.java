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

    String getName() {
        return empName;
    }

    int getEmpId() {
        return empId;
    }
}


class ManagerEmployee extends Employee {

    private double teamBonus;

    ManagerEmployee(
        int empId,
        String empName,
        double salary,
        double teamBonus
    ) {

        super(empId, empName, salary);

        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}


class InternEmployee extends Employee {

    private double stipendCap;

    InternEmployee(
        int empId,
        String empName,
        double salary,
        double stipendCap
    ) {

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


class ParkingSlot {

    String slotNo;
    int capacity;
    int occupiedCount;

    ParkingSlot(
        String slotNo,
        int capacity,
        int occupiedCount
    ) {

        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    void allot(String vehicleNo) {

        if (occupiedCount < capacity) {

            occupiedCount++;

            System.out.println(
                vehicleNo + " allotted to slot " + slotNo
            );
        }
    }
}


class CompanyEmployeeRecord {

    String name;
    String empId;

    Employee employee;

    ParkingSlot slot;

    static int totalRecords = 0;


    CompanyEmployeeRecord(
        String name,
        String empId,
        Employee employee,
        ParkingSlot slot
    ) {

        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;

        totalRecords++;
    }


    String fullProfile() {

        double pay;

        if (employee instanceof ManagerEmployee) {

            ManagerEmployee manager =
                (ManagerEmployee) employee;

            pay = manager.effectiveSalary();

        } else if (employee instanceof InternEmployee) {

            InternEmployee intern =
                (InternEmployee) employee;

            pay = intern.effectiveSalary();

        } else {

            pay = employee.getSalary();
        }


        String parking;

        if (slot != null) {
            parking = slot.slotNo;
        } else {
            parking = "no parking assigned";
        }


        return name +
               " | Pay: Rs " + pay +
               " | Slot: " + parking;
    }
}


public class a3q5 {

    public static void main(String[] args) {

        ParkingSlot slotA1 =
            new ParkingSlot("A1", 4, 3);

        ParkingSlot slotA2 =
            new ParkingSlot("A2", 5, 4);


        Employee manager =
            new ManagerEmployee(
                101,
                "Divya",
                70000,
                8000
            );

        Employee employee =
            new Employee(
                102,
                "Karan",
                40000
            );

        Employee intern =
            new InternEmployee(
                103,
                "Meera",
                12000,
                10000
            );

        slotA1.allot("TN01AA1111");
        slotA2.allot("TN01AA2222");


        CompanyEmployeeRecord record1 =
            new CompanyEmployeeRecord(
                "Divya",
                "E101",
                manager,
                slotA1
            );

        CompanyEmployeeRecord record2 =
            new CompanyEmployeeRecord(
                "Karan",
                "E102",
                employee,
                slotA2
            );

        CompanyEmployeeRecord record3 =
            new CompanyEmployeeRecord(
                "Meera",
                "E103",
                intern,
                null
            );


        System.out.println(record1.fullProfile());
        System.out.println(record2.fullProfile());
        System.out.println(record3.fullProfile());

        System.out.println(
            "Total records: " +
            CompanyEmployeeRecord.totalRecords
        );
    }
}
