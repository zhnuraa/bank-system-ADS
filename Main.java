import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    // =========================
    // BankAccount class
    // =========================
    static class BankAccount {
        int accountNumber;
        String username;
        double balance;

        public BankAccount(int accountNumber, String username, double balance) {
            this.accountNumber = accountNumber;
            this.username = username;
            this.balance = balance;
        }

        @Override
        public String toString() {
            return "Account Number: " + accountNumber +
                    ", Username: " + username +
                    ", Balance: " + balance;
        }
    }

    // =========================
    // Data Structures
    // =========================
    static LinkedList<BankAccount> accounts = new LinkedList<>();          // Task 1
    static Stack<String> transactionHistory = new Stack<>();               // Task 3
    static Queue<String> billQueue = new LinkedList<>();                   // Task 4
    static Queue<BankAccount> accountRequests = new LinkedList<>();        // Task 5

    static Scanner scanner = new Scanner(System.in);

    // =========================
    // Main
    // =========================
    public static void main(String[] args) {
        // Task 6: Physical Data Structure using Array
        showArrayDemo();

        // Add some initial accounts to LinkedList for testing
        accounts.add(new BankAccount(1001, "Ali", 150000));
        accounts.add(new BankAccount(1002, "Sara", 220000));

        mainMenu();
    }

    // =========================
    // Task 6 - Array Demo
    // =========================
    public static void showArrayDemo() {
        System.out.println("========================================");
        System.out.println("TASK 6 - PHYSICAL DATA STRUCTURE (ARRAY)");
        System.out.println("========================================");

        BankAccount[] fixedAccounts = new BankAccount[3];
        fixedAccounts[0] = new BankAccount(1, "Aruzhan", 100000);
        fixedAccounts[1] = new BankAccount(2, "Dias", 250000);
        fixedAccounts[2] = new BankAccount(3, "Madina", 175000);

        for (int i = 0; i < fixedAccounts.length; i++) {
            System.out.println((i + 1) + ". " + fixedAccounts[i]);
        }

        System.out.println();
    }

    // =========================
    // Main Menu
    // =========================
    public static void mainMenu() {
        while (true) {
            System.out.println("========================================");
            System.out.println("MINI BANKING SYSTEM");
            System.out.println("1 - Enter Bank");
            System.out.println("2 - Enter ATM");
            System.out.println("3 - Admin Area");
            System.out.println("4 - Exit");
            System.out.print("Choose: ");

            int choice = readInt();

            switch (choice) {
                case 1:
                    bankMenu();
                    break;
                case 2:
                    atmMenu();
                    break;
                case 3:
                    adminMenu();
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // =========================
    // Bank Menu
    // =========================
    public static void bankMenu() {
        while (true) {
            System.out.println("\n========== BANK MENU ==========");
            System.out.println("1 - Submit account opening request");
            System.out.println("2 - Deposit money");
            System.out.println("3 - Withdraw money");
            System.out.println("4 - Add bill payment request");
            System.out.println("5 - Display all accounts");
            System.out.println("6 - Search account by username");
            System.out.println("7 - Back");
            System.out.print("Choose: ");

            int choice = readInt();

            switch (choice) {
                case 1:
                    submitAccountRequest();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    addBillPayment();
                    break;
                case 5:
                    displayAllAccounts();
                    break;
                case 6:
                    searchAccountByUsername();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // =========================
    // ATM Menu
    // =========================
    public static void atmMenu() {
        while (true) {
            System.out.println("\n========== ATM MENU ==========");
            System.out.println("1 - Balance enquiry");
            System.out.println("2 - Withdraw");
            System.out.println("3 - Back");
            System.out.print("Choose: ");

            int choice = readInt();

            switch (choice) {
                case 1:
                    balanceEnquiry();
                    break;
                case 2:
                    withdrawMoney();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // =========================
    // Admin Menu
    // =========================
    public static void adminMenu() {
        while (true) {
            System.out.println("\n========== ADMIN MENU ==========");
            System.out.println("1 - View pending account requests");
            System.out.println("2 - Process next account request");
            System.out.println("3 - View bill payment queue");
            System.out.println("4 - Process next bill payment");
            System.out.println("5 - View transaction history");
            System.out.println("6 - Show last transaction");
            System.out.println("7 - Undo last transaction");
            System.out.println("8 - View all accounts");
            System.out.println("9 - Back");
            System.out.print("Choose: ");

            int choice = readInt();

            switch (choice) {
                case 1:
                    displayPendingRequests();
                    break;
                case 2:
                    processAccountRequest();
                    break;
                case 3:
                    displayBillQueue();
                    break;
                case 4:
                    processBillPayment();
                    break;
                case 5:
                    displayTransactionHistory();
                    break;
                case 6:
                    showLastTransaction();
                    break;
                case 7:
                    undoLastTransaction();
                    break;
                case 8:
                    displayAllAccounts();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // =========================
    // Task 1 - Add account request
    // =========================
    public static void submitAccountRequest() {
        System.out.print("Enter account number: ");
        int accountNumber = readInt();

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter initial balance: ");
        double balance = readDouble();

        BankAccount newRequest = new BankAccount(accountNumber, username, balance);
        accountRequests.add(newRequest);

        System.out.println("Account opening request submitted successfully.");
    }

    // =========================
    // Task 1 - Display accounts
    // =========================
    public static void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        System.out.println("Accounts List:");
        for (int i = 0; i < accounts.size(); i++) {
            BankAccount account = accounts.get(i);
            System.out.println((i + 1) + ". " + account.username + " - Balance: " + account.balance);
        }
    }

    // =========================
    // Task 1 - Search by username
    // =========================
    public static void searchAccountByUsername() {
        System.out.print("Enter username to search: ");
        String username = scanner.nextLine();

        BankAccount account = findAccountByUsername(username);

        if (account != null) {
            System.out.println("Account found:");
            System.out.println(account);
        } else {
            System.out.println("Account not found.");
        }
    }

    // =========================
    // Task 2 - Deposit
    // =========================
    public static void depositMoney() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        BankAccount account = findAccountByUsername(username);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter deposit amount: ");
        double amount = readDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        account.balance += amount;

        String transaction = "Deposit " + amount + " to " + account.username;
        transactionHistory.push(transaction);

        System.out.println("Deposit successful.");
        System.out.println("New balance: " + account.balance);
    }

    // =========================
    // Task 2 - Withdraw
    // =========================
    public static void withdrawMoney() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        BankAccount account = findAccountByUsername(username);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter withdraw amount: ");
        double amount = readDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if (amount > account.balance) {
            System.out.println("Insufficient balance.");
            return;
        }

        account.balance -= amount;

        String transaction = "Withdraw " + amount + " from " + account.username;
        transactionHistory.push(transaction);

        System.out.println("Withdraw successful.");
        System.out.println("New balance: " + account.balance);
    }

    // =========================
    // ATM - Balance enquiry
    // =========================
    public static void balanceEnquiry() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        BankAccount account = findAccountByUsername(username);

        if (account == null) {
            System.out.println("Account not found.");
        } else {
            System.out.println("Current balance of " + account.username + ": " + account.balance);
        }
    }

    // =========================
    // Task 3 - Display history
    // =========================
    public static void displayTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("Transaction history is empty.");
            return;
        }

        System.out.println("Transaction History:");
        for (int i = transactionHistory.size() - 1; i >= 0; i--) {
            System.out.println(transactionHistory.get(i));
        }
    }

    // =========================
    // Task 3 - Peek
    // =========================
    public static void showLastTransaction() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("Last transaction: " + transactionHistory.peek());
    }

    // =========================
    // Task 3 - Pop
    // =========================
    public static void undoLastTransaction() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transaction to undo.");
            return;
        }

        String removed = transactionHistory.pop();
        System.out.println("Undo -> " + removed + " removed from history.");

        // Note:
        // This removes only from stack history.
        // The assignment specifically says undo last transaction (pop),
        // so this implementation matches that requirement.
    }

    // =========================
    // Task 4 - Add bill payment
    // =========================
    public static void addBillPayment() {
        System.out.print("Enter bill name: ");
        String billName = scanner.nextLine();

        billQueue.add(billName);
        transactionHistory.push("Bill payment request added: " + billName);

        System.out.println("Added: " + billName);
    }

    // =========================
    // Task 4 - Process bill payment
    // =========================
    public static void processBillPayment() {
        if (billQueue.isEmpty()) {
            System.out.println("No bill payments in queue.");
            return;
        }

        String processedBill = billQueue.poll();
        transactionHistory.push("Bill payment processed: " + processedBill);

        System.out.println("Processing: " + processedBill);
    }

    // =========================
    // Task 4 - Display bill queue
    // =========================
    public static void displayBillQueue() {
        if (billQueue.isEmpty()) {
            System.out.println("Bill payment queue is empty.");
            return;
        }

        System.out.println("Bill Payment Queue:");
        for (String bill : billQueue) {
            System.out.println("- " + bill);
        }
    }

    // =========================
    // Task 5 - Display pending requests
    // =========================
    public static void displayPendingRequests() {
        if (accountRequests.isEmpty()) {
            System.out.println("No pending account requests.");
            return;
        }

        System.out.println("Pending Account Requests:");
        for (BankAccount request : accountRequests) {
            System.out.println(request);
        }
    }

    // =========================
    // Task 5 - Process request
    // =========================
    public static void processAccountRequest() {
        if (accountRequests.isEmpty()) {
            System.out.println("No account requests to process.");
            return;
        }

        BankAccount approvedAccount = accountRequests.poll();
        accounts.add(approvedAccount);

        transactionHistory.push("Account request approved for " + approvedAccount.username);

        System.out.println("Processed request. Account created for: " + approvedAccount.username);
    }

    // =========================
    // Helper - Find account
    // =========================
    public static BankAccount findAccountByUsername(String username) {
        for (BankAccount account : accounts) {
            if (account.username.equalsIgnoreCase(username)) {
                return account;
            }
        }
        return null;
    }

    // =========================
    // Input Helpers
    // =========================
    public static int readInt() {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (Exception e) {
                System.out.print("Invalid input. Enter an integer: ");
            }
        }
    }

    public static double readDouble() {
        while (true) {
            try {
                double value = Double.parseDouble(scanner.nextLine());
                return value;
            } catch (Exception e) {
                System.out.print("Invalid input. Enter a number: ");
            }
        }
    }
}
