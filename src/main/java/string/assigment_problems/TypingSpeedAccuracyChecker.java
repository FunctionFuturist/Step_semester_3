package string.assigment_problems;

public class TypingSpeedAccuracyChecker {

    public static void checkTypingAccuracy(String original, String typed) {
        int total = original.length();
        int matched = 0;
        int firstMismatchPos = -1;
        char origChar = ' ', typedChar = ' ';

        for (int i = 0; i < total; i++) {
            if (i < typed.length() && original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatchPos == -1) {
                firstMismatchPos = i + 1;
                origChar = original.charAt(i);
                typedChar = (i < typed.length()) ? typed.charAt(i) : ' ';
            }
        }

        double accuracy = ((double) matched / total) * 100;

        System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | ", matched, total, accuracy);
        if (firstMismatchPos != -1) {
            System.out.printf("First Mismatch at position %d ('%c' vs '%c')\n", firstMismatchPos, origChar, typedChar);
        } else {
            System.out.println("No Mismatches\n");
        }
    }

    public static void main(String[] args) {
        checkTypingAccuracy("hello world", "hello worlt");
        checkTypingAccuracy("coding", "coding");
    }
}
