package oop.practice_problems;

import java.util.Scanner;

abstract class Journey {
    protected double distance;

    public Journey(double distance) {
        this.distance = distance;
    }

    public abstract String getTransportType();
    public abstract double calculateFare();
}

class BusJourney extends Journey {
    public BusJourney(double distance) { super(distance); }
    @Override public String getTransportType() { return "BUS"; }
    @Override public double calculateFare() {
        return Math.min(2.0 + (0.10 * distance), 10.0);
    }
}

class TrainJourney extends Journey {
    public TrainJourney(double distance) { super(distance); }
    @Override public String getTransportType() { return "TRAIN"; }
    @Override public double calculateFare() {
        return 3.0 + (0.15 * distance);
    }
}

class MetroJourney extends Journey {
    private double peakHourFactor;

    public MetroJourney(double distance, double peakHourFactor) {
        super(distance);
        this.peakHourFactor = peakHourFactor;
    }

    @Override public String getTransportType() { return "METRO"; }
    @Override public double calculateFare() {
        return (1.50 + (0.20 * distance)) * peakHourFactor;
    }
}

public class TransportSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();
        double grandTotal = 0.0;

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double distance = scanner.nextDouble();

            Journey journey;
            if (type.equals("METRO")) {
                double peakFactor = scanner.nextDouble();
                journey = new MetroJourney(distance, peakFactor);
            } else if (type.equals("TRAIN")) {
                journey = new TrainJourney(distance);
            } else {
                journey = new BusJourney(distance);
            }

            double fare = journey.calculateFare();
            grandTotal += fare;
            System.out.printf("%s: %.2f%n", journey.getTransportType(), fare);
        }
        System.out.printf("Total: %.2f%n", grandTotal);
        scanner.close();
    }
}
