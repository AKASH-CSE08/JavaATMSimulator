import java.util.ArrayList;

class Account {

    private String accountHolder;
    private int pin;
    private double balance;

    private ArrayList<Transaction> transactionHistory;

    public Account(String accountHolder, int pin, double balance) {

        this.accountHolder = accountHolder;
        this.pin = pin;
        this.balance = balance;

        transactionHistory = new ArrayList<>();
    }

    public boolean validatePin(int enteredPin) {
        return this.pin == enteredPin;
    }

    public void deposit(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Deposit amount must be greater than 0."
            );
        }

        balance += amount;

        transactionHistory.add(
                new Transaction("Deposit", amount)
        );

        System.out.println("₹" + amount + " deposited successfully.");
    }

    public void withdraw(double amount)
            throws InsufficientBalanceException {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Withdrawal amount must be greater than 0."
            );
        }

        if (amount > balance) {
            throw new InsufficientBalanceException(
                    "Insufficient balance!"
            );
        }

        balance -= amount;

        transactionHistory.add(
                new Transaction("Withdraw", amount)
        );

        System.out.println("Please collect your cash: ₹" + amount);
    }

    public void checkBalance() {
        System.out.println("Current Balance: ₹" + balance);
    }

    public void showTransactionHistory() {

        System.out.println("\n===== TRANSACTION HISTORY =====");

        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {

            for (Transaction transaction : transactionHistory) {
                transaction.displayTransaction();
            }
        }
    }

    public String getAccountHolder() {
        return accountHolder;
    }
}