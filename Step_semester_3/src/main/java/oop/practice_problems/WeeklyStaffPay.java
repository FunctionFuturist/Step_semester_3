package oop.practice_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class StaffMember {
    protected String name;

    public StaffMember(String name) {
        this.name = name;
    }

    public abstract double calculatePay();

    public void printPay() {
        System.out.printf("%s: %.2f%n", name, calculatePay());
    }
}

class FullTimeStaff extends StaffMember {
    private double weeklySalary;

    public FullTimeStaff(String name, double weeklySalary) {
        super(name);
        this.weeklySalary = weeklySalary;
    }

    @Override
    public double calculatePay() {
        return weeklySalary;
    }
}

class HourlyStaff extends StaffMember {
    private double hours;
    private double rate;

    public HourlyStaff(String name, double hours, double rate) {
        super(name);
        this.hours = hours;
        this.rate = rate;
    }

    @Override
    public double calculatePay() {
        if (hours <= 40) {
            return hours * rate;
        } else {
            return (40 * rate) + ((hours - 40) * 1.5 * rate);
        }
    }
}

class InternStaff extends StaffMember {
    private double stipend;

    public InternStaff(String name, double stipend) {
        super(name);
        this.stipend = stipend;
    }

    @Override
    public double calculatePay() {
        return stipend;
    }
}

public class WeeklyStaffPay {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();

        List<StaffMember> staffList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String name = scanner.next();
            if (type.equals("FULLTIME")) {
                double salary = scanner.nextDouble();
                staffList.add(new FullTimeStaff(name, salary));
            } else if (type.equals("HOURLY")) {
                double hours = scanner.nextDouble();
                double rate = scanner.nextDouble();
                staffList.add(new HourlyStaff(name, hours, rate));
            } else if (type.equals("INTERN")) {
                double stipend = scanner.nextDouble();
                staffList.add(new InternStaff(name, stipend));
            }
        }

        double totalPayroll = 0.0;
        for (StaffMember staff : staffList) {
            staff.printPay();
            totalPayroll += staff.calculatePay();
        }

        System.out.printf("Total Payroll: %.2f%n", totalPayroll);
        scanner.close();
    }
}
