class Employee
{
    int employeeId;
    String name;
    String department;
    String designation;
    double salary;
    boolean permanent;
    e1()
    {
        int employeeId = 0;
        String name = "Not Assigned";
        String department = "Not Assigned";
        String designation = "Trainee";
        float salary = 0.0;
        boolean permanent = false;
    }
    e2()
    {
        
    }
    void displayEmployee() {
        System.out.println(employeeId);
        System.out.println(name);
        System.out.println(department);
    }
}

public class test
{
    public static void main(String[] args)
    {
        Employee e1 = new Employee();
        Employee e2 = new Employee(101, "Rohan");
        Employee e1 = new Employee();
        
        e1.displayEmployee();
    }
}