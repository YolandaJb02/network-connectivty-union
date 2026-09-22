/*Yolanda Exalus 
 * COP 3330
 * Assignment 3
 * 
 */


public class BankAccount {

    // ======= Fields =======
    private String accountNumber;
    private String owner;
    private double balance;
    private boolean active;

    // ======= Constructors =======
    public BankAccount() {
        accountNumber = "";
        owner = "";
        balance = 0.0;
        active = false;
    }

    public BankAccount(String accountNumber, String owner) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = 0.0;
        this.active = false;
    }

    public BankAccount(String accountNumber, String owner, double balance, boolean active) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
        this.active = active;
    }

    // ======= Getters =======
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }

    // ======= Setters =======
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    // ======= Methods =======

    public boolean deposit(double amount) {
        if (!active || amount <= 0) {
            return false;
        }
        balance += amount;
        return true;
    }

    public boolean withdraw(double amount) {
        if (!active || amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

    public boolean open() {
        if (active) {
            return false;
        }
        balance = 0.0;
        active = true;
        return true;
    }

    public boolean close() {
        if (!active) {
            return false;
        }
        balance = 0.0;
        active = false;
        return true;
    }

    public boolean transfer(BankAccount other, double amount) {
        if (!this.active || !other.active || amount <= 0 || amount > this.balance) {
            return false;
        }
        this.balance -= amount;
        other.balance += amount;
        return true;
    }

    public void printInfo() {
        String status = active ? "Active" : "Inactive";
        System.out.printf("%s %s %.2f %s%n", accountNumber, owner, balance, status);
    }
}
