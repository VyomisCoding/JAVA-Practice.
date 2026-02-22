// Q2: Bank and Account classes program

import java.util.*;

class Account{
    private int accNumber;
    private String name;
    private double balance;
    
    // Constructor
    public Account(int accNumber, String name, double balance){
        this.accNumber = accNumber;
        this.name = name;
        this.balance = balance;
    }

    // Deposit
    public void deposit(double amount){
        balance += amount;
        System.out.println("Deposited: " + amount + " New Balance: " + balance);
    }

    // Withdraw
    public void withdraw(double amount){
        if(amount > balance){
            System.out.println("Insufficient Balance!");
        }else{
            balance -= amount;
            System.out.println("Withdrawn: " + amount + " New Balance: " + balance);
        }
    }

    public int getAccNumber(){
        return accNumber;
    }

    public void display(){
        System.out.println("Account No: " + accNumber + ", Name: " + name + ", Balance: " + balance);
    }
}

// Bank Class
class Bank {

    private Map<Integer, Account> accounts = new HashMap<>();

    // Add Account
    public void addAccount(Account acc){
        accounts.put(acc.getAccNumber(), acc);
        System.out.println("Account Added!");
    }

    // Remove Account
    public void removeAccount(int accNumber){
        accounts.remove(accNumber);
        System.out.println("Account Removed!");
    }

    public Account getAccount(int accNumber){
        return accounts.get(accNumber);
    }

    public static void main(String[] args){
        Bank bank = new Bank();
        Account a1 = new Account(101, "Rohit", 5000);
        Account a2 = new Account(102, "Anjali", 7000);
        bank.addAccount(a1);
        bank.addAccount(a2);
        a1.deposit(2000);
        a2.withdraw(1000);
        a1.display();
        a2.display();
    }
}