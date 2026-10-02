package oop.practice_problems;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class LibraryItem {
    protected String title;

    public LibraryItem(String title) {
        this.title = title;
    }

    public String getTitle() { return title; }
    public abstract int getBorrowDays();

    public String calculateDueDate(LocalDate currentDate) {
        LocalDate dueDate = currentDate.plusDays(getBorrowDays());
        return dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}

class Book extends LibraryItem {
    public Book(String title) { super(title); }
    @Override public int getBorrowDays() { return 14; }
}

class DVD extends LibraryItem {
    public DVD(String title) { super(title); }
    @Override public int getBorrowDays() { return 7; }
}

class Magazine extends LibraryItem {
    public Magazine(String title) { super(title); }
    @Override public int getBorrowDays() { return 3; }
}

public class LibrarySystem {
    private static final LocalDate CURRENT_DATE = LocalDate.of(2023, 10, 26);

    public static LibraryItem createItem(String type, String title) {
        switch (type) {
            case "BOOK": return new Book(title);
            case "DVD": return new DVD(title);
            case "MAGAZINE": return new Magazine(title);
            default: throw new IllegalArgumentException("Unknown type: " + type);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Pattern pattern = Pattern.compile("^([A-Z]+)\\s+\"(.*?)\"$");

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String type = matcher.group(1);
                String title = matcher.group(2);
                LibraryItem item = createItem(type, title);
                System.out.println(item.getTitle() + ": " + item.calculateDueDate(CURRENT_DATE));
            }
        }
        scanner.close();
    }
}
