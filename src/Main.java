public class Main {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("     WELCOME TO JAVA ATM");
        System.out.println("================================");

        Account account = new Account(
                "Akash",
                1234,
                5000.0
        );

        ATM atm = new ATM(account);

        atm.start();
    }
}