package oop.assignment_problems;

import java.util.Scanner;

abstract class Customer {
    protected double amount;

    public Customer(double amount) {
        this.amount = amount;
    }

    public abstract String getCustomerType();
    public abstract double calculateFinalAmount();
}

class StudentCustomer extends Customer {
    public StudentCustomer(double amount) { super(amount); }
    @Override public String getCustomerType() { return "STUDENT"; }
    @Override public double calculateFinalAmount() {
        return amount * 0.90; // 10% discount
    }
}

class StaffCustomer extends Customer {
    public StaffCustomer(double amount) { super(amount); }
    @Override public String getCustomerType() { return "STAFF"; }
    @Override public double calculateFinalAmount() {
        return amount * 0.95; // 5% discount
    }
}

class GuestCustomer extends Customer {
    public GuestCustomer(double amount) { super(amount); }
    @Override public String getCustomerType() { return "GUEST"; }
    @Override public double calculateFinalAmount() {
        return amount + 10.0; // full amount plus service charge
    }
}

public class CanteenBilling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();
        double grandTotal = 0.0;

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double amount = scanner.nextDouble();

            Customer customer;
            switch (type) {
                case "STUDENT": customer = new StudentCustomer(amount); break;
                case "STAFF": customer = new StaffCustomer(amount); break;
                case "GUEST": customer = new GuestCustomer(amount); break;
                default: continue;
            }

            double finalAmount = customer.calculateFinalAmount();
            grandTotal += finalAmount;
            System.out.printf("%s: %.2f%n", customer.getCustomerType(), finalAmount);
        }
        System.out.printf("Total: %.2f%n", grandTotal);
        scanner.close();
    }
}
