/*A food delivery application needs to calculate the total order amount for customers.
The application should support the following situations:
1.	Calculate the total amount when only the food price is provided. 
2.	Calculate the total amount when food price and quantity are provided. 
3.	Calculate the total amount when food price, quantity, and discount percentage are provided. 
Task
Develop a Java program that calculates the total order amount for all the above situations.
The program should allow the same calculation operation to work with different sets of input values.
Sample:
Input:
Price = 200

Output:
Total Amount = 200.0
Input:
Price = 200
Quantity = 3

Output:
Total Amount = 600.0
Input:
Price = 200
Quantity = 3
Discount = 10%

Output:
Total Amount = 540.0
*/


// Solution
// Method overloading: same method name but different parameters

import java.util.Scanner;

class notebook11 {

    static double calculateTotal(double price) {
        return price;
    }

    static double calculateTotal(double price, int quantity) {
        return price * quantity;
    }

    static double calculateTotal(double price, int quantity, double discount) {
        double total = price * quantity;
        return total - (total * discount / 100);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();
        System.out.print("Enter discount: ");
        double discount = sc.nextDouble();

        System.out.println("Total Amount = " + calculateTotal(price));
        System.out.println("Total Amount = " + calculateTotal(price, quantity));
        System.out.println("Total Amount = " + calculateTotal(price, quantity, discount));

        sc.close();
    }
}
