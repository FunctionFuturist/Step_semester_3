package oop.assignment_problems;

public class CompanyEmployee {
    private String empName;
    private double salary;

    // Static fields shared across all employee instances
    private static String companyName = "Bright Horizon Technologies";
    private static int employeeCount = 0;

    public CompanyEmployee(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++; // Increment count on every constructor call
    }

    public static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }

    public static void main(String[] args) {
        CompanyEmployee e1 = new CompanyEmployee("Rahul", 50000);
        CompanyEmployee e2 = new CompanyEmployee("Sita", 62000);
        CompanyEmployee e3 = new CompanyEmployee("Amit", 55000);

        // Access static method via Class name without using object instances
        CompanyEmployee.printCompanyInfo();
    }
}
