import java.util.ArrayList;
import java.util.List;

// BankAccount class
class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount + " to account " + accountNumber);
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrew $" + amount + " from account " + accountNumber);
            return true;
        }
        System.out.println("Withdrawal failed for account " + accountNumber);
        return false;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

// Customer class
class Customer {
    private String name;
    private List<BankAccount> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
        System.out.println("Added account " + account.getAccountNumber() + " to " + name);
    }

    public List<BankAccount> getAccounts() {
        return new ArrayList<>(accounts); // Return a copy for encapsulation
    }

    public String getName() {
        return name;
    }
}

// ATM class
class ATM {
    public boolean withdraw(BankAccount account, double amount) {
        System.out.println("Processing withdrawal request...");
        if (account.withdraw(amount)) {
            System.out.println("Dispensing $" + amount);
            return true;
        }
        System.out.println("Transaction canceled");
        return false;
    }

    public void displayBalance(BankAccount account) {
        System.out.println("Account " + account.getAccountNumber() 
                          + " balance: $" + account.getBalance());
    }
}

// Main class to run the banking system
public class BankingSystem {
    public static void main(String[] args) {
        // Create a customer
        Customer customer = new Customer("John Doe");
        
        // Create accounts
        BankAccount savings = new BankAccount("SAV-123456", 1500.0);
        BankAccount checking = new BankAccount("CHK-789012", 500.0);
        
        // Add accounts to customer
        customer.addAccount(savings);
        customer.addAccount(checking);
        
        // Create ATM
        ATM atm = new ATM();
        
        System.out.println("\n=== Performing Transactions ===");
        // Display initial balances
        atm.displayBalance(savings);
        atm.displayBalance(checking);
        
        // Perform transactions
        System.out.println("\n--- Transaction 1 ---");
        atm.withdraw(savings, 200.0);
        
        System.out.println("\n--- Transaction 2 ---");
        atm.withdraw(checking, 600.0); // Should fail
        
        System.out.println("\n--- Transaction 3 ---");
        savings.deposit(300.0);
        
        // Display final balances
        System.out.println("\n=== Final Balances ===");
        atm.displayBalance(savings);
        atm.displayBalance(checking);
    }
}
