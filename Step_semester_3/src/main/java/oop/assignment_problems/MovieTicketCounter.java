package oop.assignment_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Ticket {
    protected static final double CONVENIENCE_FEE = 20.0;
    protected String seatType;
    protected int count;

    public Ticket(String seatType, int count) {
        this.seatType = seatType;
        this.count = count;
    }

    public abstract double getBasePricePerTicket();

    public double calculateAmount() {
        return count * (getBasePricePerTicket() + CONVENIENCE_FEE);
    }

    public void printAmount() {
        System.out.printf("%s: %.2f%n", seatType, calculateAmount());
    }
}

class RegularTicket extends Ticket {
    public RegularTicket(int count) {
        super("REGULAR", count);
    }

    @Override
    public double getBasePricePerTicket() {
        return 150.0;
    }
}

class PremiumTicket extends Ticket {
    public PremiumTicket(int count) {
        super("PREMIUM", count);
    }

    @Override
    public double getBasePricePerTicket() {
        return 250.0;
    }
}

class ReclinerTicket extends Ticket {
    public ReclinerTicket(int count) {
        super("RECLINER", count);
    }

    @Override
    public double getBasePricePerTicket() {
        return 400.0;
    }
}

public class MovieTicketCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();

        List<Ticket> tickets = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String seat = scanner.next();
            int count = scanner.nextInt();

            if (seat.equals("REGULAR")) {
                tickets.add(new RegularTicket(count));
            } else if (seat.equals("PREMIUM")) {
                tickets.add(new PremiumTicket(count));
            } else if (seat.equals("RECLINER")) {
                tickets.add(new ReclinerTicket(count));
            }
        }

        double total = 0.0;
        for (Ticket ticket : tickets) {
            ticket.printAmount();
            total += ticket.calculateAmount();
        }

        System.out.printf("Total: %.2f%n", total);
        scanner.close();
    }
}
