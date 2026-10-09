package oop.practice_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class TravelBooking {
    protected static final double BOOKING_FEE = 50.0;
    protected String mode;
    protected double distanceKm;

    public TravelBooking(String mode, double distanceKm) {
        this.mode = mode;
        this.distanceKm = distanceKm;
    }

    public abstract double calculateBaseFare();

    public double calculateTotalFare() {
        return calculateBaseFare() + BOOKING_FEE;
    }

    public void printFare() {
        System.out.printf("%s: %.2f%n", mode, calculateTotalFare());
    }
}

class BusBooking extends TravelBooking {
    public BusBooking(double distanceKm) {
        super("BUS", distanceKm);
    }

    @Override
    public double calculateBaseFare() {
        return distanceKm * 2.0;
    }
}

class TrainBooking extends TravelBooking {
    public TrainBooking(double distanceKm) {
        super("TRAIN", distanceKm);
    }

    @Override
    public double calculateBaseFare() {
        return distanceKm * 1.5;
    }
}

class FlightBooking extends TravelBooking {
    public FlightBooking(double distanceKm) {
        super("FLIGHT", distanceKm);
    }

    @Override
    public double calculateBaseFare() {
        return 2500.0 + (distanceKm * 4.0);
    }
}

public class TravelBookingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();

        List<TravelBooking> bookings = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String mode = scanner.next();
            double distance = scanner.nextDouble();

            if (mode.equals("BUS")) {
                bookings.add(new BusBooking(distance));
            } else if (mode.equals("TRAIN")) {
                bookings.add(new TrainBooking(distance));
            } else if (mode.equals("FLIGHT")) {
                bookings.add(new FlightBooking(distance));
            }
        }

        for (TravelBooking booking : bookings) {
            booking.printFare();
        }

        scanner.close();
    }
}
