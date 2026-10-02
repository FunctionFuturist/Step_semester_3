package oop.practice_problems;

import java.util.Scanner;

abstract class PaymentMethod {
    protected double amount;

    public PaymentMethod(double amount) {
        this.amount = amount;
    }

    public abstract String getPaymentType();
    public abstract double calculateFeeRate();

    public double calculateTotalAmount() {
        return amount * (1 + calculateFeeRate());
    }
}

class CardPayment extends PaymentMethod {
    public CardPayment(double amount) { super(amount); }
    @Override public String getPaymentType() { return "CARD"; }
    @Override public double calculateFeeRate() { return 0.02; }
}

class WalletPayment extends PaymentMethod {
    public WalletPayment(double amount) { super(amount); }
    @Override public String getPaymentType() { return "WALLET"; }
    @Override public double calculateFeeRate() { return 0.01; }
}

class BankTransferPayment extends PaymentMethod {
    public BankTransferPayment(double amount) { super(amount); }
    @Override public String getPaymentType() { return "BANKTRANSFER"; }
    @Override public double calculateFeeRate() { return 0.00; }
}

public class PaymentSystem {
    public static PaymentMethod createPayment(String type, double amount) {
        switch (type) {
            case "CARD": return new CardPayment(amount);
            case "WALLET": return new WalletPayment(amount);
            case "BANKTRANSFER": return new BankTransferPayment(amount);
            default: throw new IllegalArgumentException("Unknown type: " + type);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();
        double grandTotal = 0.0;

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double amount = scanner.nextDouble();
            PaymentMethod payment = createPayment(type, amount);
            double finalAmount = payment.calculateTotalAmount();
            grandTotal += finalAmount;
            System.out.printf("%s: %.2f%n", payment.getPaymentType(), finalAmount);
        }
        System.out.printf("Total: %.2f%n", grandTotal);
        scanner.close();
    }
}
