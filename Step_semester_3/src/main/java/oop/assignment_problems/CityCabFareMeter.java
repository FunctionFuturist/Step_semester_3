package oop.assignment_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface NightServiceable {}

abstract class Cab {
    protected String type;
    protected double km;
    protected String time;

    public Cab(String type, double km, String time) {
        this.type = type;
        this.km = km;
        this.time = time;
    }

    public abstract double getRatePerKm();

    public boolean isTripValid() {
        if (time.equalsIgnoreCase("NIGHT") && !(this instanceof NightServiceable)) {
            return false;
        }
        return true;
    }

    public double calculateFare() {
        double baseFare = km * getRatePerKm();
        double fare = Math.max(baseFare, 100.0);

        if (time.equalsIgnoreCase("NIGHT")) {
            fare *= 1.20;
        }

        return fare;
    }

    public void printFare() {
        if (!isTripValid()) {
            System.out.printf("%s: night service not available%n", type);
        } else {
            System.out.printf("%s: %.2f%n", type, calculateFare());
        }
    }
}

class MiniCab extends Cab {
    public MiniCab(double km, String time) {
        super("MINI", km, time);
    }

    @Override
    public double getRatePerKm() {
        return 10.0;
    }
}

class SedanCab extends Cab implements NightServiceable {
    public SedanCab(double km, String time) {
        super("SEDAN", km, time);
    }

    @Override
    public double getRatePerKm() {
        return 14.0;
    }
}

class SUVCab extends Cab implements NightServiceable {
    public SUVCab(double km, String time) {
        super("SUV", km, time);
    }

    @Override
    public double getRatePerKm() {
        return 18.0;
    }
}

public class CityCabFareMeter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();

        List<Cab> trips = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String cabType = scanner.next();
            double km = scanner.nextDouble();
            String time = scanner.next();

            if (cabType.equals("MINI")) {
                trips.add(new MiniCab(km, time));
            } else if (cabType.equals("SEDAN")) {
                trips.add(new SedanCab(km, time));
            } else if (cabType.equals("SUV")) {
                trips.add(new SUVCab(km, time));
            }
        }

        double totalFare = 0.0;
        for (Cab cab : trips) {
            cab.printFare();
            if (cab.isTripValid()) {
                totalFare += cab.calculateFare();
            }
        }

        System.out.printf("Total: %.2f%n", totalFare);
        scanner.close();
    }
}
