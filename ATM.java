import java.util.ArrayList;
import java.util.Scanner;

class BankAccount {
    static void register() {
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------");
        System.out.println("Enter your name :");
        ATM.name = sc.nextLine();
        System.out.println("Enter username :");
        String user = sc.nextLine();
        System.out.println("Enter password :");
        String pass = sc.nextLine();
        System.out.println("Enter your Account number :");
        ATM.accNumber = sc.nextLine();
        System.out.println("REGISTRATION SUCCESSFULLY!");
        System.out.println("---------------------------");
        ATM.prompt();
        while (true) {
            display(ATM.name);
            int choice = sc.nextInt();
            if (choice == 1) {
                login(user, pass);
                break;
            } else {
                if (choice == 2) {
                    System.exit(0);
                } else {
                    System.out.println("Bad value! Enter again!");
                }
            }
        }
    }

    static void display(String name) {
        System.out.println("Welcome " + name);
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.print("Enter choice: ");
    }

    static void login(String user, String pass) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username: ");
        String enteredUser = sc.nextLine();
        System.out.print("Enter password: ");
        String enteredPass = sc.nextLine();

        if (user.equals(enteredUser) && pass.equals(enteredPass)) {
            System.out.println("Login successful!");
            ATM.prompt();
        } else {
            System.out.println("Incorrect username or password!");
            login(user, pass);
        }
    }
}

class Transaction {
    static void withdraw() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------");
        System.out.println("Enter amount to withdraw :");
        int wCash = sc.nextInt();
        if (wCash <= ATM.balance) {
            ATM.balance -= wCash;
            ATM.history.add("Withdraw: " + wCash);
            System.out.println("Amount Rs" + wCash + "/- withdraw successfully");
            System.out.println("---------------------------");
        } else {
            System.out.println("Insufficient balance to withdraw the cash");
            System.out.println("---------------------------");
        }
        ATM.prompt();
    }

    static void deposit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------");
        System.out.print("Enter amount to deposit :");
        int dCash = sc.nextInt();
        ATM.updateBalance(dCash);
        ATM.history.add("Deposit: " + dCash);
        System.out.println("Amount Rs." + dCash + "/- deposit successful!");
        System.out.println("---------------------------");
        ATM.prompt();
    }

    static void transfer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the receiving body:");
        String recipient = sc.nextLine();
        System.out.println("Enter the account number of the receiving body");
        int accNumber = sc.nextInt();
        System.out.println("Enter the amount to be transferred :");
        int tCash = sc.nextInt();
        if (tCash <= ATM.balance) {
            ATM.balance -= tCash;
            ATM.history.add("Transfer to " + recipient + ": " + tCash);
            System.out.println("Amount Rs." + tCash + "/- transferred successfully");
            System.out.println("---------------------------");
        } else {
            System.out.println("Insufficient balance to transfer the cash");
            System.out.println("---------------------------");
        }
        ATM.prompt();
    }
}

class Check {
    static void checkBalance() {
        System.out.println("------------------");
        System.out.println("The available balance in the bank account :");
        ATM.showBalance();
        System.out.println("---------------------------");
        ATM.prompt();
    }
}

class History {
    static void transactionHistory() {
        System.out.println("---------------------");
        System.out.println("Transaction History :");
        if (ATM.history.isEmpty()) {
            System.out.println("Your account is empty");
        } else {
            for (String transaction : ATM.history) {
                System.out.println(transaction);
            }
        }
        System.out.println("---------------------");
        ATM.prompt();
    }
}

public class ATM {
    public static String name;
    public static int balance = 0;
    public static String accNumber;
    public static ArrayList<String> history = new ArrayList<>();

    static void updateBalance(int dCash) {
        balance += dCash;
    }

    static void showBalance() {
        System.out.println(balance);
    }

    public static void homepage() {
        Scanner sc = new Scanner(System.in);
        System.out.println("WELCOME TO ATM INTERFACE");
        System.out.println("--------------------------");
        System.out.println("Select option :");
        System.out.println("1. Register");
        System.out.println("2. Exit");
        System.out.println("Enter choice:");
        int choice = sc.nextInt();
        if (choice == 1) {
            BankAccount.register();
        } else {
            if (choice == 2) {
                System.exit(0);
            } else {
                System.out.println("Select a value only from the given options:");
                homepage();
            }
        }
    }

    static void prompt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("WELCOME " + ATM.name + "! TO ATM SYSTEM");
        System.out.println("---------------------");
        System.out.println("Select option : ");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Transfer");
        System.out.println("4. Check balance");
        System.out.println("5. Transaction History");
        System.out.println("6. Exit");
        System.out.print("Enter your choice : ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                Transaction.withdraw();
                break;
            case 2:
                Transaction.deposit();
                break;
            case 3:
                Transaction.transfer();
                break;
            case 4:
                Check.checkBalance();
                break;
            case 5:
                History.transactionHistory();
                break;
            case 6:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice, please try again.");
                prompt();
                break;
        }
    }

    public static void main(String[] args) {
        homepage();
    }
}
