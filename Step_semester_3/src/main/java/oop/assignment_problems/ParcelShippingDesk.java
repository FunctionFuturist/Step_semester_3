package oop.assignment_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface Insurable {
    double calculateInsurance();
}

abstract class Parcel {
    protected String type;
    protected double weightKg;
    protected double declaredValue;

    public Parcel(String type, double weightKg, double declaredValue) {
        this.type = type;
        this.weightKg = weightKg;
        this.declaredValue = declaredValue;
    }

    public abstract double calculateShippingCharge();

    public double getInsuranceCost() {
        if (this instanceof Insurable) {
            return ((Insurable) this).calculateInsurance();
        }
        return 0.0;
    }

    public double calculateTotal() {
        return calculateShippingCharge() + getInsuranceCost();
    }

    public void printDetails() {
        System.out.printf("%s: Charge=%.2f Insurance=%.2f Total=%.2f%n",
                type, calculateShippingCharge(), getInsuranceCost(), calculateTotal());
    }
}

class StandardParcel extends Parcel {
    public StandardParcel(double weightKg, double declaredValue) {
        super("STANDARD", weightKg, declaredValue);
    }

    @Override
    public double calculateShippingCharge() {
        return 40.0 + (10.0 * weightKg);
    }
}

class ExpressParcel extends Parcel implements Insurable {
    public ExpressParcel(double weightKg, double declaredValue) {
        super("EXPRESS", weightKg, declaredValue);
    }

    @Override
    public double calculateShippingCharge() {
        return 80.0 + (15.0 * weightKg);
    }

    @Override
    public double calculateInsurance() {
        return declaredValue * 0.02;
    }
}

class FragileParcel extends Parcel implements Insurable {
    public FragileParcel(double weightKg, double declaredValue) {
        super("FRAGILE", weightKg, declaredValue);
    }

    @Override
    public double calculateShippingCharge() {
        return (40.0 + (10.0 * weightKg)) + 50.0;
    }

    @Override
    public double calculateInsurance() {
        return declaredValue * 0.02;
    }
}

public class ParcelShippingDesk {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();

        List<Parcel> parcels = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double weight = scanner.nextDouble();
            double value = scanner.nextDouble();

            if (type.equals("STANDARD")) {
                parcels.add(new StandardParcel(weight, value));
            } else if (type.equals("EXPRESS")) {
                parcels.add(new ExpressParcel(weight, value));
            } else if (type.equals("FRAGILE")) {
                parcels.add(new FragileParcel(weight, value));
            }
        }

        double grandTotal = 0.0;
        for (Parcel parcel : parcels) {
            parcel.printDetails();
            grandTotal += parcel.calculateTotal();
        }

        System.out.printf("Grand Total: %.2f%n", grandTotal);
        scanner.close();
    }
}
