package string.assigment_problems;

import java.util.*;

public class WordFrequencyReport {

    public static void printFilteredWordFrequency(String feedback) {
        Set<String> stopWords = new HashSet<>(Arrays.asList("the", "was", "and", "a", "is", "of", "in"));

        // Normalize text: lowercase and strip punctuation
        String cleaned = feedback.toLowerCase().replace(".", "").replace(",", "");

        String[] words = cleaned.split("\\s+");
        Map<String, Integer> frequencyMap = new HashMap<>();

        for (String word : words) {
            if (!word.isEmpty() && !stopWords.contains(word)) {
                frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
            }
        }

        // Sort by frequency descending
        List<Map.Entry<String, Integer>> list = new ArrayList<>(frequencyMap.entrySet());
        list.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        String sample = "The mentor was great, the session was great and clear.";
        printFilteredWordFrequency(sample);
    }
}
