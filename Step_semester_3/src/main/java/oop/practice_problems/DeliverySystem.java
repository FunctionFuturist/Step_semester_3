package oop.practice_problems;

import java.util.Scanner;

abstract class DeliveryRequest {
    protected double weight;
    protected double distance;

    public DeliveryRequest(double weight, double distance) {
        this.weight = weight;
        this.distance = distance;
    }

    public abstract String getDeliveryType();
    public abstract double calculateFee();
}

class StandardDelivery extends DeliveryRequest {
    public StandardDelivery(double weight, double distance) { super(weight, distance); }
    @Override public String getDeliveryType() { return "STANDARD"; }
    @Override public double calculateFee() { return 5.0 + (0.50 * weight) + (0.10 * distance); }
}

class ExpressDelivery extends DeliveryRequest {
    public ExpressDelivery(double weight, double distance) { super(weight, distance); }
    @Override public String getDeliveryType() { return "EXPRESS"; }
    @Override public double calculateFee() { return 15.0 + (1.00 * weight) + (0.20 * distance); }
}

class InternationalDelivery extends DeliveryRequest {
    private double customsFee;

    public InternationalDelivery(double weight, double distance, double customsFee) {
        super(weight, distance);
        this.customsFee = customsFee;
    }

    @Override public String getDeliveryType() { return "INTERNATIONAL"; }
    @Override public double calculateFee() { return 25.0 + (2.00 * weight) + (0.50 * distance) + customsFee; }
}

public class DeliverySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();
        double grandTotal = 0.0;

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double weight = scanner.nextDouble();
            double distance = scanner.nextDouble();

            DeliveryRequest request;
            if (type.equals("INTERNATIONAL")) {
                double customsFee = scanner.nextDouble();
                request = new InternationalDelivery(weight, distance, customsFee);
            } else if (type.equals("EXPRESS")) {
                request = new ExpressDelivery(weight, distance);
            } else {
                request = new StandardDelivery(weight, distance);
            }

            double fee = request.calculateFee();
            grandTotal += fee;
            System.out.printf("%s: %.2f%n", request.getDeliveryType(), fee);
        }
        System.out.printf("Total: %.2f%n", grandTotal);
        scanner.close();
    }
}
