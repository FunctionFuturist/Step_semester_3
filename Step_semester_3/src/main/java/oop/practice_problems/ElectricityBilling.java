package oop.practice_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class ElectricityConnection {
    protected String type;
    protected double units;

    public ElectricityConnection(String type, double units) {
        this.type = type;
        this.units = units;
    }

    public abstract double calculateBill();

    public void printBill() {
        System.out.printf("%s: %.2f%n", type, calculateBill());
    }
}

class HomeConnection extends ElectricityConnection {
    public HomeConnection(double units) {
        super("HOME", units);
    }

    @Override
    public double calculateBill() {
        if (units <= 100) {
            return units * 5.0;
        } else {
            return (100 * 5.0) + ((units - 100) * 7.0);
        }
    }
}

class ShopConnection extends ElectricityConnection {
    public ShopConnection(double units) {
        super("SHOP", units);
    }

    @Override
    public double calculateBill() {
        return (units * 8.0) + 100.0;
    }
}

class FactoryConnection extends ElectricityConnection {
    public FactoryConnection(double units) {
        super("FACTORY", units);
    }

    @Override
    public double calculateBill() {
        double calculated = units * 6.0;
        return Math.max(calculated, 1000.0);
    }
}

public class ElectricityBilling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();

        List<ElectricityConnection> connections = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double units = scanner.nextDouble();

            if (type.equals("HOME")) {
                connections.add(new HomeConnection(units));
            } else if (type.equals("SHOP")) {
                connections.add(new ShopConnection(units));
            } else if (type.equals("FACTORY")) {
                connections.add(new FactoryConnection(units));
            }
        }

        double totalBill = 0.0;
        for (ElectricityConnection conn : connections) {
            conn.printBill();
            totalBill += conn.calculateBill();
        }

        System.out.printf("Total: %.2f%n", totalBill);
        scanner.close();
    }
}
