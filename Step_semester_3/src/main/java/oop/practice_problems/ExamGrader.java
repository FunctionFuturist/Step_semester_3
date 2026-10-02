package oop.practice_problems;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class Question {
    protected String questionText;
    protected String correctAnswer;
    protected String studentAnswer;
    protected double points;

    public Question(String questionText, String correctAnswer, String studentAnswer, double points) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.studentAnswer = studentAnswer;
        this.points = points;
    }

    public abstract String getTypeLabel();
    public abstract double evaluateScore();
}

class MCQQuestion extends Question {
    public MCQQuestion(String q, String c, String s, double p) { super(q, c, s, p); }
    @Override public String getTypeLabel() { return "MCQ"; }
    @Override public double evaluateScore() {
        return studentAnswer.trim().equalsIgnoreCase(correctAnswer.trim()) ? points : 0.0;
    }
}

class TFQuestion extends Question {
    public TFQuestion(String q, String c, String s, double p) { super(q, c, s, p); }
    @Override public String getTypeLabel() { return "TF"; }
    @Override public double evaluateScore() {
        return studentAnswer.trim().equalsIgnoreCase(correctAnswer.trim()) ? points : 0.0;
    }
}

class EssayQuestion extends Question {
    public EssayQuestion(String q, String c, String s, double p) { super(q, c, s, p); }
    @Override public String getTypeLabel() { return "ESSAY"; }
    @Override public double evaluateScore() {
        String[] keywords = correctAnswer.split(",");
        String studentLower = studentAnswer.toLowerCase();
        int matched = 0;
        for (String kw : keywords) {
            if (studentLower.contains(kw.trim().toLowerCase())) {
                matched++;
            }
        }
        if (matched >= 2) return 0.75 * points;
        if (matched == 1) return 0.50 * points;
        return 0.0;
    }
}

public class ExamGrader {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();
        scanner.nextLine();

        Pattern pattern = Pattern.compile("^([A-Z]+)\\s+\"(.*?)\"\\s+\"(.*?)\"\\s+\"(.*?)\"\\s+(\\d+)$");

        double totalScore = 0.0;
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String type = matcher.group(1);
                String qText = matcher.group(2);
                String correct = matcher.group(3);
                String student = matcher.group(4);
                double points = Double.parseDouble(matcher.group(5));

                Question q;
                switch (type) {
                    case "MCQ": q = new MCQQuestion(qText, correct, student, points); break;
                    case "TF": q = new TFQuestion(qText, correct, student, points); break;
                    case "ESSAY": q = new EssayQuestion(qText, correct, student, points); break;
                    default: continue;
                }

                double score = q.evaluateScore();
                totalScore += score;
                System.out.printf("%s: %.2f%n", q.getTypeLabel(), score);
            }
        }
        System.out.printf("Total Score: %.2f%n", totalScore);
        scanner.close();
    }
}
