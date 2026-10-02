package oop.assignment_problems;

import java.util.Scanner;

abstract class Employee {
    protected String name;
    protected double monthlySalary;

    public Employee(String name, double monthlySalary) {
        this.name = name;
        this.monthlySalary = monthlySalary;
    }

    public String getName() { return name; }
    public abstract double calculateBonus();
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name, double salary) { super(name, salary); }
    @Override public double calculateBonus() {
        return monthlySalary * 0.10;
    }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name, double salary) { super(name, salary); }
    @Override public double calculateBonus() {
        return monthlySalary * 0.05;
    }
}

class InternEmployee extends Employee {
    public InternEmployee(String name, double salary) { super(name, salary); }
    @Override public double calculateBonus() {
        return 2000.0;
    }
}

public class FestivalBonus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();
        double grandTotal = 0.0;

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String name = scanner.next();
            double salary = scanner.nextDouble();

            Employee employee;
            switch (type) {
                case "FULLTIME": employee = new FullTimeEmployee(name, salary); break;
                case "PARTTIME": employee = new PartTimeEmployee(name, salary); break;
                case "INTERN": employee = new InternEmployee(name, salary); break;
                default: continue;
            }

            double bonus = employee.calculateBonus();
            grandTotal += bonus;
            System.out.printf("%s: %.2f%n", employee.getName(), bonus);
        }
        System.out.printf("Total Bonus: %.2f%n", grandTotal);
        scanner.close();
    }
}
