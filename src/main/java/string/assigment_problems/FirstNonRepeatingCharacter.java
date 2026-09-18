package string.assigment_problems;

public class FirstNonRepeatingCharacter {

    public static char findFirstNonRepeatingChar(String text) {
        int[] frequency = new int[256];

        // Count frequency of each character
        for (int i = 0; i < text.length(); i++) {
            frequency[text.charAt(i)]++;
        }

        // Find first character with frequency 1
        for (int i = 0; i < text.length(); i++) {
            if (frequency[text.charAt(i)] == 1) {
                return text.charAt(i);
            }
        }

        return '\0'; // Return null character if no unique character exists
    }

    public static void main(String[] args) {
        String input1 = "swiss";
        char result1 = findFirstNonRepeatingChar(input1);
        if (result1 != '\0') {
            System.out.println("Input: \"" + input1 + "\" -> First Non-Repeating Character: '" + result1 + "'");
        } else {
            System.out.println("Input: \"" + input1 + "\" -> No Non-Repeating Character Found");
        }

        String input2 = "aabbcc";
        char result2 = findFirstNonRepeatingChar(input2);
        if (result2 != '\0') {
            System.out.println("Input: \"" + input2 + "\" -> First Non-Repeating Character: '" + result2 + "'");
        } else {
            System.out.println("Input: \"" + input2 + "\" -> No Non-Repeating Character Found");
        }
    }
}
