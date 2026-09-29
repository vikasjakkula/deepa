/*

Banking System

A bank named SecureBank wants to develop a system to manage different types of bank accounts.

The bank currently supports:

- Savings Account
- Current Account
- Fixed Deposit Account

More account types may be added in the future.

Every account has common information such as:

- Account Number
- Account Holder Name
- Balance

Every account should support common operations such as:

- Deposit money
- Withdraw money
- Display account details
- Calculate interest

However, the way interest is calculated is different for different account types.

The bank does not want programmers to create a generic account object directly. Only specific account types such as Savings Account, Current Account, or Fixed Deposit Account should be created.

For example, the following should not be allowed:

Account a = new Account();

Use an appropriate Java concept to enforce this requirement.

---

Unique Account Number

Every bank account is given an account number when it is created.

For example:

Account Number: 10001

Once assigned, the account number must never change during the lifetime of the object.

For example, this should not be allowed:

accountNumber = 99999;

Use an appropriate Java keyword to enforce this rule.

---

Interest Calculation

Every account must provide a method to calculate interest.

The general bank account should define that such an operation must exist, but it should not provide a common implementation because the interest calculation differs for each account type.

For example:

Savings Account → 4% interest
Current Account → 0% interest
Fixed Deposit Account → 7% interest

Each concrete account type should therefore provide its own implementation.

---

Standard KYC Verification

Before performing banking operations, every account must use the same KYC verification process defined by SecureBank.

The bank does not want Savings Account, Current Account, or Fixed Deposit Account to replace this verification logic with their own implementation.

The standard operation displays:

KYC Verified

Use an appropriate Java keyword so that child classes inherit this method but cannot override it.

---

Total Account Counter

SecureBank wants to know how many account objects have been created.

For example:

SavingsAccount s1 = new SavingsAccount(...);
CurrentAccount c1 = new CurrentAccount(...);
FixedDepositAccount f1 = new FixedDepositAccount(...);

The system should display:

Total Accounts Created: 3

The counter should belong to the class rather than to individual objects.

Provide a method that can be called as:

Account.getAccountCount();

Use the appropriate Java concept.

---

Savings Account

A Savings Account should maintain:

minimumBalance

It should allow:

withdraw(double amount)

but the withdrawal should be rejected if the balance falls below the required minimum balance.

The account should calculate interest at:

4%

---

Current Account

A Current Account should maintain:

overdraftLimit

It should allow withdrawal even if the balance becomes negative, as long as the withdrawal does not exceed the permitted overdraft limit.

The Current Account does not earn interest.

---

Fixed Deposit Account

A Fixed Deposit Account should maintain:

depositPeriod

and:

interestRate

The account should calculate interest based on the fixed deposit rate.

Assume:

Interest Rate: 7%

A Fixed Deposit Account should not allow normal withdrawal before maturity.

---

Bank Policy Configuration

SecureBank provides a class called:

BankPolicy

It stores values such as:

Minimum KYC Age
Maximum Daily Withdrawal
Bank Code

Other parts of the program may create and use "BankPolicy" objects.

However, programmers must not be allowed to create subclasses of "BankPolicy".

For example, the following should not be allowed:

class CustomBankPolicy extends BankPolicy {
}

Use an appropriate Java keyword to enforce this restriction.

---

Part A – Complete the Parent Class

Complete the following code using appropriate Java keywords.

abstract class Account {

    long accountNumber;

    String accountHolderName;
    double balance;

    int accountCount = 0;

    Account(long accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;

        // increase the total number of accounts
    }

    void deposit(double amount) {
        balance = balance + amount;
    }

    void withdraw(double amount) {
        balance = balance - amount;
    }

    void verifyKYC() {
        System.out.println("KYC Verified");
    }

    void calculateInterest();

    void displayDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
    }

    void getAccountCount() {
        System.out.println("Total Accounts Created: " + accountCount);
    }
}

Modify the class wherever required.

---

Part B – Create the Child Classes

Create:

SavingsAccount
CurrentAccount
FixedDepositAccount

All should inherit from "Account".

Implement the appropriate additional attributes and methods.

Each account type must provide its own implementation of:

calculateInterest()

Use "@Override" where appropriate.

---

Part C – Test the Accounts

Create:

SavingsAccount s = new SavingsAccount(10001, "Rahul", 50000, 5000);

CurrentAccount c = new CurrentAccount(10002, "Anita", 30000, 20000);

FixedDepositAccount f = new FixedDepositAccount(10003, "Ramesh", 100000, 3);

Perform the following:

1. Deposit ₹10,000 into Rahul's Savings Account.
2. Withdraw ₹20,000 from Rahul's account.
3. Try withdrawing an amount that would make the balance fall below the minimum balance.
4. Deposit ₹5,000 into Anita's Current Account.
5. Withdraw an amount that uses part of the overdraft facility.
6. Calculate interest for all three accounts.
7. Display account details.
8. Display the total number of account objects created.

Expected:

Total Accounts Created: 3

---

Part D – Predict the Errors

Try each statement separately and explain the result.

1.

Account a = new Account(10004, "John", 10000);

Why should this fail?

2.

s.accountNumber = 50000;

Why should the application prevent this?

3.

Inside "SavingsAccount", try:

void verifyKYC() {
    System.out.println("Savings Account KYC");
}

Should this be allowed?

4.

Try:

class SpecialPolicy extends BankPolicy {
}

Should this compile?

5.

Why can this method be called using the class name?

Account.getAccountCount();

---

Part E – Polymorphism

Create:

Account a1 = new SavingsAccount(20001, "Asha", 40000, 5000);

Account a2 = new CurrentAccount(20002, "Vikram", 25000, 15000);

Account a3 = new FixedDepositAccount(20003, "Neha", 80000, 5);

Call:

a1.calculateInterest();
a2.calculateInterest();
a3.calculateInterest();

Observe which implementation executes.

Now for each of a1, a2 and a3, call one by one:

a1.verifyKYC();
a1.calculateInterest();
a1.displayDetails();

(and the same for a2 and a3)

Observe how the same "Account" reference can work with different account objects.

---

Concept Identification

After completing the program, identify where each concept has been used:

Inheritance
Abstract class
Abstract method
Method overriding
Static variable
Static method
Final variable
Final method
Final class
Runtime polymorphism

Explain the role of each concept using examples from your program.

---

Challenge

SecureBank introduces a new type of account:

StudentAccount

A Student Account:

- Has no minimum balance requirement.
- Provides 3% interest.
- Has a daily withdrawal limit.
- Should inherit all common Account functionality.

Add "StudentAccount" to the system without changing the basic design of the "Account" class.

Finally, verify that:

Account.getAccountCount();

also counts Student Account objects correctly.*/
// Solution
class notebook {
    public static void main(String[] args) {
        SavingsAccount s = new SavingsAccount(10001, "Rahul", 50000, 5000);
        CurrentAccount c = new CurrentAccount(10002, "Anita", 30000, 20000);
        FixedDepositAccount f = new FixedDepositAccount(10003, "Ramesh", 100000, 3);
        s.deposit(10000);
        s.withdraw(20000);
        s.withdraw(36000); // balance will go below 5000 so not allowed
        c.deposit(5000);
        c.withdraw(45000); // balance becomes -10000, uses overdraft
        s.calculateInterest();
        c.calculateInterest();
        f.calculateInterest();
        s.displayDetails();
        c.displayDetails();
        f.displayDetails();
        Account.getAccountCount();
        // Part D: 1. new Account() -> error, Account is abstract  2. s.accountNumber = 50000 -> error, it is final
        // 3. overriding verifyKYC() -> error, it is final  4. extends BankPolicy -> error, final class
        // 5. getAccountCount() is static so we can call it using the class name
        // Part E - parent reference, child object
        Account a1 = new SavingsAccount(20001, "Asha", 40000, 5000);
        Account a2 = new CurrentAccount(20002, "Vikram", 25000, 15000);
        Account a3 = new FixedDepositAccount(20003, "Neha", 80000, 5);
        a1.verifyKYC();
        a1.calculateInterest();
        a1.displayDetails();
        a2.verifyKYC();
        a2.calculateInterest();
        a2.displayDetails();
        a3.verifyKYC();
        a3.calculateInterest();
        a3.displayDetails();
        // Challenge
        StudentAccount st = new StudentAccount(30001, "Kiran", 2000, 1000);
        st.calculateInterest();
        Account.getAccountCount();
    }
}

abstract class Account {
    final long accountNumber; // final so it cannot be changed
    String accountHolderName;
    double balance;
    static int accountCount = 0; // static so it is shared by all accounts
    Account(long accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        accountCount++;
    }
    void deposit(double amount) {
        balance = balance + amount;
    }
    abstract void withdraw(double amount);
    final void verifyKYC() {
        System.out.println("KYC Verified");
    }
    abstract void calculateInterest();
    void displayDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println();
    }
    static void getAccountCount() {
        System.out.println("Total Accounts Created: " + accountCount);
    }
}

class SavingsAccount extends Account {
    double minimumBalance;
    SavingsAccount(long accountNumber, String name, double balance, double minimumBalance) {
        super(accountNumber, name, balance);
        this.minimumBalance = minimumBalance;
    }
    @Override
    void withdraw(double amount) {
        if (balance - amount < minimumBalance)
            System.out.println("Withdraw failed, minimum balance must be " + minimumBalance);
        else
            balance = balance - amount;
    }
    @Override
    void calculateInterest() {
        System.out.println("Savings Interest: " + balance * 4 / 100);
    }
}

class CurrentAccount extends Account {
    double overdraftLimit;
    CurrentAccount(long accountNumber, String name, double balance, double overdraftLimit) {
        super(accountNumber, name, balance);
        this.overdraftLimit = overdraftLimit;
    }
    @Override
    void withdraw(double amount) {
        if (balance - amount < -overdraftLimit)
            System.out.println("Withdraw failed, overdraft limit crossed");
        else
            balance = balance - amount;
    }
    @Override
    void calculateInterest() {
        System.out.println("Current Account Interest: 0.0");
    }
}

class FixedDepositAccount extends Account {
    int depositPeriod;
    double interestRate = 7;
    FixedDepositAccount(long accountNumber, String name, double balance, int depositPeriod) {
        super(accountNumber, name, balance);
        this.depositPeriod = depositPeriod;
    }
    @Override
    void withdraw(double amount) {
        System.out.println("Cannot withdraw before maturity");
    }
    @Override
    void calculateInterest() {
        System.out.println("FD Interest: " + balance * interestRate * depositPeriod / 100);
    }
}

class StudentAccount extends Account {
    double dailyLimit;
    StudentAccount(long accountNumber, String name, double balance, double dailyLimit) {
        super(accountNumber, name, balance);
        this.dailyLimit = dailyLimit;
    }
    @Override
    void withdraw(double amount) {
        if (amount > dailyLimit)
            System.out.println("Withdraw failed, daily limit is " + dailyLimit);
        else
            balance = balance - amount;
    }
    @Override
    void calculateInterest() {
        System.out.println("Student Interest: " + balance * 3 / 100);
    }
}

final class BankPolicy {
    int minimumKycAge = 18;
    double maximumDailyWithdrawal = 50000;
    String bankCode = "SECB001";
}
