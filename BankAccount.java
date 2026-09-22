/*Yolanda Exalus 
COP 3330
Programming Assignment 3
BankAccount.java
 */


public class BankAccount {

    //  Fields (Attributes) 
    private String accountNumber;
    private String owner;
    private double balance;
    private boolean active;

    //  Methods 

    // Withdraw money if account is active and funds are sufficient
    public boolean withdraw(double amount) {
        if (!active || amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

    // Deposit money if account is active and amount is valid
    public boolean deposit(double amount) {
        if (!active || amount <= 0) {
            return false;
        }
        balance += amount;
        return true;
    }

    // Open an inactive account (balance becomes 0)
    public boolean open() {
        if (active) {
            return false;
        }
        balance = 0.0;
        active = true;
        return true;
    }

    // Close an active account (balance becomes 0)
    public boolean close() {
        if (!active) {
            return false;
        }
        balance = 0.0;
        active = false;
        return true;
    }

    // Print account info: number, owner, balance, and status
    public void printInfo() {
        String status = active ? "Active" : "Inactive";
        System.out.printf("%s %s %.2f %s%n", accountNumber, owner, balance, status);
    }

    //  Getters 
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwner() {
        return owner;
    }
2
    public double getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }

    //  Setters 
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Transfer Method
    public boolean transfer(BankAccount other, double amount) {
        if (!this.active || !other.active || amount <= 0 || amount > this.balance) {
            return false;
        }
        this.balance -= amount;
        other.balance += amount;
        return true;
    }
}
