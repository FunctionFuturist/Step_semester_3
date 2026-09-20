package array.assigment_problems;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class Candidate implements Comparable<Candidate> {
    private String name;
    private double cgpa;
    private int codingScore;

    public Candidate(String name, double cgpa, int codingScore) {
        this.name = name;
        this.cgpa = cgpa;
        this.codingScore = codingScore;
    }

    public String getName() {
        return name;
    }

    public double getCgpa() {
        return cgpa;
    }

    public int getCodingScore() {
        return codingScore;
    }

    public double getCompositeScore() {
        return (cgpa * 10.0) + (codingScore * 0.5);
    }

    @Override
    public int compareTo(Candidate other) {
        // Sort descending by composite score
        return Double.compare(other.getCompositeScore(), this.getCompositeScore());
    }
}

public class PlacementDriveEngine {

    // Thresholds: CGPA-only filter (>= 7.5)
    public static boolean isEligible(double cgpa) {
        return cgpa >= 7.5;
    }

    // Thresholds: Combined filter for borderline CGPA (CGPA >= 6.5 AND codingScore >= 60)
    public static boolean isEligible(double cgpa, int codingScore) {
        return cgpa >= 6.5 && codingScore >= 60;
    }

    public static String shortlistAndRank(Candidate[] candidates) {
        if (candidates == null || candidates.length == 0) {
            return "";
        }

        List<Candidate> shortlistedList = new ArrayList<>();
        for (Candidate c : candidates) {
            // Check direct eligibility or combined eligibility
            if (isEligible(c.getCgpa()) || isEligible(c.getCgpa(), c.getCodingScore())) {
                shortlistedList.add(c);
            }
        }

        Candidate[] shortlistedArray = shortlistedList.toArray(new Candidate[0]);
        Arrays.sort(shortlistedArray);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < shortlistedArray.length; i++) {
            Candidate c = shortlistedArray[i];
            result.append(i + 1)
                  .append(". ")
                  .append(c.getName())
                  .append(" (")
                  .append(c.getCompositeScore())
                  .append(")");
            
            if (i < shortlistedArray.length - 1) {
                result.append(" | ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Candidate[] candidates = {
            new Candidate("Aisha", 8.2, 40),
            new Candidate("Rohit", 6.8, 65),
            new Candidate("Meena", 6.0, 90),
            new Candidate("Karan", 7.5, 20)
        };

        System.out.println(shortlistAndRank(candidates));
        // Output: 1. Aisha (102.0) | 2. Rohit (100.5) | 3. Karan (85.0)
    }
}
