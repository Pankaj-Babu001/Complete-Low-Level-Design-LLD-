#include <iostream>
#include <string>
#include <vector>
#include <memory>

// BankAccount class
class BankAccount {
private:
    std::string accountNumber;
    double balance;

public:
    BankAccount(const std::string& accNum, double initialBalance)
        : accountNumber(accNum), balance(initialBalance) {}

    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    bool withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    double getBalance() const {
        return balance;
    }

    std::string getAccountNumber() const {
        return accountNumber;
    }
};

// Customer class
class Customer {
private:
    std::string name;
    std::vector<std::shared_ptr<BankAccount>> accounts;

public:
    Customer(const std::string& customerName) : name(customerName) {}

    void addAccount(const std::shared_ptr<BankAccount>& account) {
        accounts.push_back(account);
    }

    std::vector<std::shared_ptr<BankAccount>> getAccounts() const {
        return accounts;
    }

    std::string getName() const {
        return name;
    }
};

// ATM class
class ATM {
public:
    bool withdraw(std::shared_ptr<BankAccount> account, double amount) {
        if (account->withdraw(amount)) {
            std::cout << "Dispensing $" << amount << std::endl;
            return true;
        }
        std::cout << "Insufficient funds or invalid amount" << std::endl;
        return false;
    }

    void displayBalance(std::shared_ptr<BankAccount> account) {
        std::cout << "Account " << account->getAccountNumber() 
                  << " balance: $" << account->getBalance() << std::endl;
    }
};

int main() {
    // Create a customer
    Customer customer("John Doe");
    
    // Create accounts
    auto account1 = std::make_shared<BankAccount>("123456", 1000.0);
    auto account2 = std::make_shared<BankAccount>("789012", 500.0);
    
    // Add accounts to customer
    customer.addAccount(account1);
    customer.addAccount(account2);
    
    // Create ATM
    ATM atm;
    
    // Perform transactions
    atm.displayBalance(account1);
    atm.withdraw(account1, 200.0);
    atm.displayBalance(account1);
    
    return 0;
}
