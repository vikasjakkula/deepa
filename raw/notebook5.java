/*

Bank Account Management System

A bank is developing an **Online Account Management System**.

Design and implement the system in Java based on the following requirements. 
**Identify and use the appropriate Java keywords/modifiers for each requirement.**

1. The `BankAccount` class should define a common method for calculating 
interest, but the actual calculation must be provided by different types of
accounts such as `SavingsAccount` and `CurrentAccount`.

2. The bank name should be **shared by all bank accounts** instead of storing 
a separate copy for every account.


3. The bank name should be displayed through a method that can be called
**without creating an object** of the `BankAccount` class.

4. The `SavingsAccount` class should **not be extended by any other class**.

5. The account number should **not be directly accessible from outside the 
`BankAccount` class**. Provide a suitable method to access and display it.

Create the required classes and display the following details:

**Expected Output:**

```text
Bank: ABC Bank
Account Number: 100245
Account Holder: Anil
Interest: 2500.0
```
*/


// Solution

class notebook5 {
    public static void main(String[] args) {
        BankAccount.displayBankName();

        SavingsAccount s = new SavingsAccount(100245, "Anil", 50000);
        s.displayDetails();
    }
}

abstract class BankAccount {
    private int accountNumber; // private so outside classes cannot access it
    String accountHolder;
    double balance;
    static String bankName = "ABC Bank"; // one copy shared by all accounts

    BankAccount(int accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    int getAccountNumber() {
        return accountNumber;
    }

    static void displayBankName() {
        System.out.println("Bank: " + bankName);
    }

    abstract double calculateInterest();

    void displayDetails() {
        System.out.println("Account Number: " + getAccountNumber());
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Interest: " + calculateInterest());
    }
}

final class SavingsAccount extends BankAccount {

    SavingsAccount(int accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }

    @Override
    double calculateInterest() {
        return balance * 5 / 100;
    }
}

class CurrentAccount extends BankAccount {

    CurrentAccount(int accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }

    @Override
    double calculateInterest() {
        return balance * 1 / 100;
    }
}
