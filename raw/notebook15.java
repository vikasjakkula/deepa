/*A bank wants to develop a simple ATM System. The ATM should allow a customer to perform basic banking operations.

The ATM should provide the following operations:

Withdraw Money – withdraw a specified amount from the account.
Deposit Money – deposit a specified amount into the account.
Check Balance – display the current account balance.
Change PIN – allow the customer to change the ATM PIN.
Requirements
Create an interface named ATM.
Declare the following methods in the interface:
withdrawMoney()
depositMoney()
checkBalance()
changePIN()
Create a class BankATM that implements the ATM interface.
Initialize the account with a balance of ₹10,000.
Set the default ATM PIN as 1234.
Implement all four methods.
In main(), create an object of BankATM.
Perform the following operations:
Display the initial balance.
Withdraw ₹2,000.
Deposit ₹5,000.
Display the updated balance.
Change the PIN from 1234 to 5678.
Display suitable messages for each operation.
*/

interface ATM {
    void checkBalance();
    void withdrawMoney(double amount);
    void depositMoney(double amount);
    void changePIN(int newPIN);
}

class BankATM implements ATM {
    private double balance = 10000;
    private int PIN = 1234;
    @Override
    public void checkBalance() {
        System.out.println("Current balance: ₹"+ balance);
    }
    @Override
    public void withdrawMoney(double amount) {
        balance = balance - amount;
        System.out.println("Withdrawn successfully: ₹"+ balance);
    }
    @Override
    public void depositMoney(double amount) {
        balance = balance + amount;
        System.out.println("Deposited money Successfuly: ₹"+ balance);
    }
    @Override
    public void changePIN(int newPIN) {
        PIN=newPIN;
        System.out.println("PIN CHANGED SUCCESSFULLY: "+ PIN);
        System.out.println("PIN "+ PIN+ " CHANGED TO: "+ newPIN);
    }
}

class notebook15 {
    public static void main(String[] args) {
        BankATM B = new BankATM();
        B.checkBalance();
        B.withdrawMoney(2000);
        B.depositMoney(5000);
        B.changePIN(5678);
        B.checkBalance();
    }
}