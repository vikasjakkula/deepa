/*

Scenario --1:A bank wants to develop a Java application to manage customer bank accounts.
Create a class BankAccount with the following requirements:
1.	The account number should be accessible from anywhere in the program.
2.	The balance should be accessible only within the BankAccount class.
3.	The account type should be accessible within the same package and by subclasses.
4.	The branch name should be accessible only within the same package.
5.	Create a deposit() method that accepts a deposit amount and adds it to the existing balance.
6.	Create a displayAccountDetails() method to display all four account details.
7.	In the main() method:
o	Create a BankAccount object using the given account details.
o	Display the account details before depositing money.
o	Deposit the specified amount using the deposit() method.
o	Display the updated account details.
o	Change the account number directly and display the details again.
Sample Input
Account Number: 10025
Initial Balance: 5000
Account Type: Savings
Branch Name: Hyderabad Main
Deposit Amount: 2000
New Account Number: 10026
Sample Output
Before Deposit:
Account Number: 10025
Balance: 5000.0
Account Type: Savings
Branch Name: Hyderabad Main

After Deposit:
Account Number: 10025
Balance: 7000.0
Account Type: Savings
Branch Name: Hyderabad Main

After Changing Account Number:
Account Number: 10026
Balance: 7000.0
Account Type: Savings
Branch Name: Hyderabad Main*/


// Solution

public class notebook8 {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(10025, 5000, "Savings", "Hyderabad Main");

        System.out.println("Before Deposit:");
        account.displayAccountDetails();

        account.deposit(2000);
        System.out.println();
        System.out.println("After Deposit:");
        account.displayAccountDetails();

        account.accountNumber = 10026; // allowed because accountNumber is public
        System.out.println();
        System.out.println("After Changing Account Number:");
        account.displayAccountDetails();
    }
}

class BankAccount {
    public int accountNumber; // anywhere
    private double balance; // only inside BankAccount
    protected String accountType; // same package and subclasses
    String branchName; // default, only same package

    BankAccount(int accountNumber, double balance, String accountType, String branchName) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.branchName = branchName;
    }

    void deposit(double amount) {
        balance = balance + amount;
    }

    void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
        System.out.println("Account Type: " + accountType);
        System.out.println("Branch Name: " + branchName);
    }
}
