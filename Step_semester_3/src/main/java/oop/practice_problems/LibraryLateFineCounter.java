package oop.practice_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class LibraryItem {
    protected String title;
    protected int daysLate;

    public LibraryItem(String title, int daysLate) {
        this.title = title;
        this.daysLate = daysLate;
    }

    public abstract double calculateFine();

    public void printFine() {
        System.out.printf("%s: %.2f%n", title, calculateFine());
    }
}

class Book extends LibraryItem {
    public Book(String title, int daysLate) {
        super(title, daysLate);
    }

    @Override
    public double calculateFine() {
        return daysLate * 2.0;
    }
}

class DVD extends LibraryItem {
    public DVD(String title, int daysLate) {
        super(title, daysLate);
    }

    @Override
    public double calculateFine() {
        return Math.min(daysLate * 5.0, 50.0);
    }
}

class Magazine extends LibraryItem {
    public Magazine(String title, int daysLate) {
        super(title, daysLate);
    }

    @Override
    public double calculateFine() {
        return daysLate * 1.0;
    }
}

public class LibraryLateFineCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();

        List<LibraryItem> items = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String title = scanner.next();
            int daysLate = scanner.nextInt();

            if (type.equals("BOOK")) {
                items.add(new Book(title, daysLate));
            } else if (type.equals("DVD")) {
                items.add(new DVD(title, daysLate));
            } else if (type.equals("MAGAZINE")) {
                items.add(new Magazine(title, daysLate));
            }
        }

        double totalFines = 0.0;
        for (LibraryItem item : items) {
            item.printFine();
            totalFines += item.calculateFine();
        }

        System.out.printf("Total Fines: %.2f%n", totalFines);
        scanner.close();
    }
}
