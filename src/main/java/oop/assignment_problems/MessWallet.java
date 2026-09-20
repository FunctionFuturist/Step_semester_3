package oop.assignment_problems;

public class MessWallet {
    private double balance;

    public MessWallet(double openingBalance) {
        if (openingBalance < 0) {
            System.out.println("Warning: Negative opening balance provided. Initializing balance to 0.0");
            this.balance = 0.0;
        } else {
            this.balance = openingBalance;
        }
    }

    public void topUp(double amount) {
        if (amount <= 0) {
            System.out.println("Top-up rejected: Amount must be greater than 0");
            return;
        }
        this.balance += amount;
        System.out.println("Balance after top-up: " + this.balance);
    }

    public void deduct(double amount) {
        if (amount > this.balance) {
            System.out.println("Deduct rejected: insufficient balance");
        } else {
            this.balance -= amount;
            System.out.println("Deduct successful. New balance: " + this.balance);
        }
    }

    public double getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        MessWallet wallet = new MessWallet(500.0);
        wallet.topUp(200.0);
        wallet.deduct(1000.0);
        System.out.println("Final balance: " + wallet.getBalance());
    }
}
