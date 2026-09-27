package SBI;
public class bank {
    public String accountNumber;
    private double balance;
    protected String accountType;
    String branchName;
    public void deposit(double amount) {
        balance = balance + amount;
    }
    public double getBalance() {
        return balance;
    }
}

public class AccessmodifiersDemo {
    public static void main(String[] args) {
        account.getBalance();
        account.deposit(5000);
        System.out.println("Account Number: "+ account.num);
        System.out.println("Account Type: "+ account.type);
        System.out.println("Branch: "+ account.branch);
        System.out.println("Balance: "+ account.balance);
    }
}