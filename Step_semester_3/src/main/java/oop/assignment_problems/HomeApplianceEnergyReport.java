package oop.assignment_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface SaverModeCapable {}

abstract class Appliance {
    protected String name;
    protected double hours;
    protected boolean isSaver;

    public Appliance(String name, double hours, boolean isSaver) {
        this.name = name;
        this.hours = hours;
        this.isSaver = isSaver;
    }

    public abstract double getPowerRatingWatts();

    public boolean isSupported() {
        if (isSaver && !(this instanceof SaverModeCapable)) {
            return false;
        }
        return true;
    }

    public double calculateUnits() {
        double units = (getPowerRatingWatts() * hours) / 1000.0;
        if (isSaver) {
            units *= 0.75;
        }
        return units;
    }

    public double calculateCost() {
        return calculateUnits() * 8.0;
    }

    public void printReport() {
        if (!isSupported()) {
            System.out.printf("%s: saver mode not supported%n", name);
        } else {
            System.out.printf("%s: Units=%.2f Cost=%.2f%n", name, calculateUnits(), calculateCost());
        }
    }
}

class Fridge extends Appliance {
    public Fridge(double hours, boolean isSaver) {
        super("FRIDGE", hours, isSaver);
    }

    @Override
    public double getPowerRatingWatts() {
        return 150.0;
    }
}

class AirConditioner extends Appliance implements SaverModeCapable {
    public AirConditioner(double hours, boolean isSaver) {
        super("AC", hours, isSaver);
    }

    @Override
    public double getPowerRatingWatts() {
        return 1500.0;
    }
}

class TV extends Appliance {
    public TV(double hours, boolean isSaver) {
        super("TV", hours, isSaver);
    }

    @Override
    public double getPowerRatingWatts() {
        return 100.0;
    }
}

class WashingMachine extends Appliance implements SaverModeCapable {
    public WashingMachine(double hours, boolean isSaver) {
        super("WASHER", hours, isSaver);
    }

    @Override
    public double getPowerRatingWatts() {
        return 500.0;
    }
}

public class HomeApplianceEnergyReport {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = Integer.parseInt(scanner.next());

        List<Appliance> appliances = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            double hours = Double.parseDouble(scanner.next());
            boolean isSaver = false;

            if (scanner.hasNext("SAVER")) {
                scanner.next();
                isSaver = true;
            }

            if (name.equals("FRIDGE")) {
                appliances.add(new Fridge(hours, isSaver));
            } else if (name.equals("AC")) {
                appliances.add(new AirConditioner(hours, isSaver));
            } else if (name.equals("TV")) {
                appliances.add(new TV(hours, isSaver));
            } else if (name.equals("WASHER")) {
                appliances.add(new WashingMachine(hours, isSaver));
            }
        }

        double totalCost = 0.0;
        for (Appliance app : appliances) {
            app.printReport();
            if (app.isSupported()) {
                totalCost += app.calculateCost();
            }
        }

        System.out.printf("Total Cost: %.2f%n", totalCost);
        scanner.close();
    }
}
