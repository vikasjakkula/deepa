// Employee Onboarding System - constructor overloading + chaining + encapsulation

class Employee {

    // 1. Encapsulation: fields are private
    private int employeeId;
    private String name;
    private String department;
    private String designation;
    private double salary;
    private boolean permanent;

    // instance initializer block: runs for every object, before the constructor body
    { totalEmployees++; }

    private static int totalEmployees = 0;   // one copy for the whole class

    // Case A : no information available
    Employee() {
        this(0, "Not Assigned", "Not Assigned", "Trainee", 0.0, false);
    }

    // Case B : id + name
    Employee(int employeeId, String name) {
        this(employeeId, name, "Not Assigned", "Trainee", 0.0, false);
    }

    // Case C : id + name + department
    Employee(int employeeId, String name, String department) {
        this(employeeId, name, department, "Trainee", 0.0, false);
    }

    // Case F : contract employee -> id + name + salary   (int,String,double) is a
    // different signature from Case C's (int,String,String), so it is allowed
    Employee(int employeeId, String name, double salary) {
        this(employeeId, name, "Contract", "Consultant", salary, false);
    }

    // Case D : experienced employee, still on probation
    Employee(int employeeId, String name, String department,
             String designation, double salary) {
        this(employeeId, name, department, designation, salary, false);
    }

    // Case E : MASTER constructor - the only one that really assigns the fields
    Employee(int employeeId, String name, String department,
             String designation, double salary, boolean permanent) {
        this.employeeId  = employeeId;      // this.field = parameter  -> beats shadowing
        this.name        = name;
        this.department  = department;
        this.designation = designation;
        this.salary      = salary;
        this.permanent   = permanent;
    }

    // copy constructor
    Employee(Employee other) {
        this(other.employeeId, other.name, other.department,
             other.designation, other.salary, other.permanent);
    }

    // ---- The design problem ----
    // We also want (employeeId, department) but that is again (int, String),
    // the same signature as Case B, so a second constructor is impossible.
    // Solution: static factory methods, because METHODS can have different names.
    static Employee withName(int employeeId, String name) {
        return new Employee(employeeId, name);
    }

    static Employee withDepartment(int employeeId, String department) {
        return new Employee(employeeId, "Not Assigned", department);
    }

    // ---- getters / setters (encapsulation) ----
    int getEmployeeId()      { return employeeId; }
    String getName()         { return name; }
    double getSalary()       { return salary; }
    boolean isPermanent()    { return permanent; }

    void setSalary(double salary) {
        if (salary < 0) {
            System.out.println("Salary cannot be negative");
            return;
        }
        this.salary = salary;
    }

    void setPermanent(boolean permanent) { this.permanent = permanent; }

    static int getTotalEmployees() { return totalEmployees; }

    void displayEmployee() {
        System.out.println("Employee ID : " + employeeId);
        System.out.println("Name        : " + name);
        System.out.println("Department  : " + department);
        System.out.println("Designation : " + designation);
        System.out.println("Salary      : " + salary);
        System.out.println("Permanent   : " + permanent);
        System.out.println("--------------------------------");
    }
}

public class test {
    public static void main(String[] args) {

        Employee e1 = new Employee();                                  // Case A
        Employee e2 = new Employee(101, "Rohan");                      // Case B
        Employee e3 = new Employee(102, "Priya", "Development");       // Case C
        Employee e4 = new Employee(103, "Arjun", "Testing",
                                   "Senior Tester", 65000);            // Case D
        Employee e5 = new Employee(104, "Sneha", "Development",
                                   "Team Lead", 95000, true);          // Case E
        Employee e6 = new Employee(105, "Kiran", 40000.0);             // contract

        Employee e7 = Employee.withDepartment(106, "Research");        // factory method

        e1.displayEmployee();
        e2.displayEmployee();
        e3.displayEmployee();
        e4.displayEmployee();
        e5.displayEmployee();
        e6.displayEmployee();
        e7.displayEmployee();

        System.out.println("Total employees created : " + Employee.getTotalEmployees());
    }
}
