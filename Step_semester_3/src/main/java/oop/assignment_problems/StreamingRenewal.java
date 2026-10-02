package oop.assignment_problems;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

abstract class SubscriptionPlan {
    protected String name;
    protected LocalDate startDate;

    public SubscriptionPlan(String name, LocalDate startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    public String getName() { return name; }
    public abstract int getValidityDays();

    public String calculateRenewalDate() {
        LocalDate renewalDate = startDate.plusDays(getValidityDays());
        return renewalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}

class BasicPlan extends SubscriptionPlan {
    public BasicPlan(String name, LocalDate startDate) { super(name, startDate); }
    @Override public int getValidityDays() { return 30; }
}

class StandardPlan extends SubscriptionPlan {
    public StandardPlan(String name, LocalDate startDate) { super(name, startDate); }
    @Override public int getValidityDays() { return 90; }
}

class PremiumPlan extends SubscriptionPlan {
    public PremiumPlan(String name, LocalDate startDate) { super(name, startDate); }
    @Override public int getValidityDays() { return 365; }
}

public class StreamingRenewal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String name = scanner.next();
            String dateStr = scanner.next();
            LocalDate startDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            SubscriptionPlan plan;
            switch (type) {
                case "BASIC": plan = new BasicPlan(name, startDate); break;
                case "STANDARD": plan = new StandardPlan(name, startDate); break;
                case "PREMIUM": plan = new PremiumPlan(name, startDate); break;
                default: continue;
            }

            System.out.println(plan.getName() + ": " + plan.calculateRenewalDate());
        }
        scanner.close();
    }
}
