package oop.assignment_problems;

public class Student {
    private String name;
    private double attendance;

    // Static fields shared across all student instances
    private static String collegeName = "SRM Institute of Science and Technology";
    private static int studentCount = 0;

    public Student(String name, double attendance) {
        this.name = name;
        this.attendance = attendance;
        studentCount++; // Increment count on every instantiation
    }

    public static void printCollegeInfo() {
        System.out.println(collegeName);
        System.out.println("Students created: " + studentCount);
    }

    public static void main(String[] args) {
        Student s1 = new Student("Aarav", 85.5);
        Student s2 = new Student("Diya", 92.0);

        // Call static method directly via the Class name
        Student.printCollegeInfo();
    }
}
