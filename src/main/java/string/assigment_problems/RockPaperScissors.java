package string.assigment_problems;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    // Helper method to determine the winner of a single round
    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }
        
        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
            (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock")) ||
            (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"))) {
            return "Player Wins";
        }
        
        return "Computer Wins";
    }

    public static void main(String[] args) {
        String[] moves = {"Rock", "Paper", "Scissors"};
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int wins = 0;
        int losses = 0;
        int draws = 0;
        int totalRounds = 5;

        System.out.println("=== The College Coding Arcade: Rock-Paper-Scissors ===");

        for (int i = 1; i <= totalRounds; i++) {
            System.out.print("\nRound " + i + " - Enter your move (Rock, Paper, Scissors): ");
            String playerMove = scanner.nextLine().trim();

            // Generate computer move randomly
            String computerMove = moves[random.nextInt(3)];

            // Determine round result
            String result = playRound(playerMove, computerMove);

            System.out.println("Player: " + playerMove + " | Computer: " + computerMove + " -> " + result);

            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }
        }

        // Calculate win percentage
        double winPercentage = ((double) wins / totalRounds) * 100;

        // Print final summary stats
        System.out.println("\n-------------------------------------------------------");
        System.out.printf("Final Summary (after %d rounds) | Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%\n",
                totalRounds, wins, losses, draws, winPercentage);
        System.out.println("-------------------------------------------------------");

        scanner.close();
    }
}
