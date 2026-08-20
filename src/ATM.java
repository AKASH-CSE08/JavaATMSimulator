import java.util.Scanner;

class ATM {

    private Account account;
    private Scanner scanner;

    public ATM(Account account) {
        this.account = account;
        scanner = new Scanner(System.in);
    }

    public boolean authenticate() {

        int attempts = 3;

        while (attempts > 0) {

            System.out.print("Enter your 4-digit PIN: ");

            try {

                int enteredPin = scanner.nextInt();

                if (account.validatePin(enteredPin)) {
                    System.out.println("\nPIN Verified Successfully!");
                    return true;
                } else {

                    attempts--;

                    if (attempts > 0) {
                        System.out.println(
                                "Invalid PIN! Attempts remaining: "
                                        + attempts
                        );
                    }
                }

            } catch (Exception e) {

                System.out.println(
                        "Invalid input! Please enter numbers only."
                );

                scanner.nextLine();
            }
        }

        try {
            throw new InvalidPinException(
                    "Too many incorrect PIN attempts. Account locked!"
            );
        } catch (InvalidPinException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public void start() {

        if (!authenticate()) {
            return;
        }

        int choice=0;

        do {

            System.out.println("\n================================");
            System.out.println("          JAVA ATM MENU");
            System.out.println("================================");

            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");

            System.out.print("\nEnter your choice: ");

            try {

                choice = scanner.nextInt();

                switch (choice) {

                    case 1:
                        account.checkBalance();
                        break;

                    case 2:
                        System.out.print("Enter deposit amount: ₹");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                        break;

                    case 3:
                        System.out.print("Enter withdrawal amount: ₹");
                        double withdrawAmount = scanner.nextDouble();
                        account.withdraw(withdrawAmount);
                        break;

                    case 4:
                        account.showTransactionHistory();
                        break;

                    case 5:
                        System.out.println(
                                "\nThank you for using Java ATM Simulator!"
                        );
                        break;

                    default:
                        System.out.println(
                                "Invalid choice! Please try again."
                        );
                }

            } catch (InsufficientBalanceException e) {

                System.out.println(
                        "Transaction Failed: " + e.getMessage()
                );

            } catch (IllegalArgumentException e) {

                System.out.println(
                        "Invalid Amount: " + e.getMessage()
                );

            } catch (Exception e) {

                System.out.println(
                        "Invalid input! Please enter valid data."
                );

                scanner.nextLine();
                choice=0 ;
            }

        } while (choice != 5);
    }
}