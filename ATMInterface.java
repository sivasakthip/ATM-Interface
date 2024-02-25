package com.ATMInterface;

import java.util.*;

class BankAccount {
    private double balance;
    private List<String> transactionHistory;
    
    public BankAccount(double balance) {
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit: $"+ amount);
    }
    
    public boolean withdraw(double amount) {
        if(amount > balance) {
            System.out.println("Insufficient Funds");
            return false;
        } else {
            balance -= amount;
            transactionHistory.add("Withdrawal: $"+ amount);
            return true;
        }
    }
    
    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}

class ATM{
    private BankAccount bankAccount;
    private int pin;
    
    public ATM(BankAccount bankAccount, int pin) {
        this.bankAccount = bankAccount;
        this.pin = pin;
    }
    
    // Display Option
    public void displayOptions() {
        System.out.println("Welcome to ABC ATM :) ");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Print Transaction History");
        System.out.println("5. Exit");
    }
    
    public boolean authenticate(int enteredPin) {
        return enteredPin == pin;
    }
    
    public void withdraw(double amount) {
        if(bankAccount.withdraw(amount)) {
            System.out.println("Withdrawal Successful. Remaining balance: $"+ bankAccount.getBalance());
        }
    }
    
    public void deposit(double amount) {
        bankAccount.deposit(amount);
        System.out.println("Deposit Successful. Current balance: $"+ bankAccount.getBalance());
    }
    
    public void checkBalance() {
        System.out.println("Current Balance: $"+ bankAccount.getBalance());
    }
    
    public void printTransactionHistory() {
        List<String> transactions = bankAccount.getTransactionHistory();
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("Transaction History:");
            for(String transaction: transactions) {
                System.out.println(transaction);
            }
        }
    }
}

public class ATMInterface {
    
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);
        ATM atm = new ATM(account, 1214);
        Scanner in = new Scanner(System.in); 
        System.out.println("Please Enter Your PIN Number");
        int enteredPin = in.nextInt();
        if(atm.authenticate(enteredPin)) {
            while(true) {
                atm.displayOptions();
                
                System.out.println("Enter the Option: ");
                
                int option = in.nextInt();
                
                switch (option) {
                    case 1:
                        System.out.println("Enter the Amount to Withdraw: ");
                        double withdrawAmount = in.nextDouble();
                        atm.withdraw(withdrawAmount);
                        break;
                    case 2:
                        System.out.println("Enter the Amount to Deposit");
                        double depositAmount = in.nextDouble();
                        atm.deposit(depositAmount);
                        break;
                    case 3:
                        atm.checkBalance();
                        break;
                    case 4:
                        atm.printTransactionHistory();
                        break;
                    case 5:
                        System.out.println("Exiting. Thank You");
                        System.exit(0);
                    default:
                        System.out.println("Invalid Option. Please try again.");
                }
            } 
        } else {
            System.out.println("Incorrect PIN");
        }
        in.close();
    }
}