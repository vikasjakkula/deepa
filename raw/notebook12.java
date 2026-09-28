/*

Coding Question: Online Shopping Order System

Develop a Java program for an online shopping platform using inheritance and constructor overloading. Do not use method overriding.

Create a parent class "Order" with the following data members:

int orderId;
String customerName;
double orderAmount;

Provide the following overloaded constructors:

Order(int orderId, String customerName)

Order(int orderId, String customerName, double orderAmount)

If the first constructor is used, initialize "orderAmount" to "0".

Add the following methods to "Order":

void showOrder()

Displays the order ID, customer name, and order amount.

double calculateTax()

Returns 5% of the order amount as tax.

double getAmountWithTax()

Returns:

orderAmount + tax

---

Child Class: PremiumOrder

Create a child class "PremiumOrder" that inherits from "Order".

Add the following data members:

String membershipType;
double discountPercentage;
double deliveryCharge;

Provide the following overloaded constructors:

PremiumOrder(int orderId, String customerName, String membershipType)

PremiumOrder(int orderId, String customerName, double orderAmount,
             String membershipType)

PremiumOrder(int orderId, String customerName, double orderAmount,
             String membershipType, double discountPercentage,
             double deliveryCharge)

Use "super(...)" in the child constructors to initialize the parent-class data members.

Use the following default values when required:

discountPercentage = 5
deliveryCharge = 100

---

Methods in "PremiumOrder"

Create a method:

double calculateDiscount()

It should calculate the discount based on "orderAmount".

Create another method:

double calculateFinalBill()

The final bill should be calculated as:

Final Bill = Order Amount + Tax + Delivery Charge - Discount

Create a method:

void showPremiumOrder()

It should display:

Order ID
Customer Name
Order Amount
Membership Type
Discount Percentage
Delivery Charge
Tax
Discount Amount
Final Bill

You may call the inherited "showOrder()" method inside this method.

---

Additional Functionality

Create a method:

void applyFreeDelivery()

If the order amount is greater than or equal to "5000", set the delivery charge to "0".

Create another method:

void addPurchase(double amount)

This method should increase the existing order amount by the given amount.

For example:

Current Order Amount = 4000
Additional Purchase = 1500

Updated Order Amount = 5500

After updating the order amount, check whether the order is eligible for free delivery.

---

Main Method

Create three "PremiumOrder" objects using three different overloaded constructors.

Order 1

Order ID: 101
Customer Name: Ravi
Membership Type: Silver

Add a purchase of:

3000

---

Order 2

Order ID: 102
Customer Name: Ananya
Order Amount: 6000
Membership Type: Gold

---

Order 3

Order ID: 103
Customer Name: Kiran
Order Amount: 10000
Membership Type: Platinum
Discount Percentage: 15
Delivery Charge: 200

For each object:

1. Display the initial order details.
2. Apply free delivery if eligible.
3. Calculate tax.
4. Calculate discount.
5. Calculate the final bill.
6. Display all final order details.

Conditions

- Tax must be 5% of the order amount.
- Orders of ?5000 or more should receive free delivery.
- Discount must be calculated before displaying the final bill.
- Do not use method overriding.
- Do not use arrays or collections.
- Use overloaded constructors in both the parent and child classes.
- Use "super()" in the child-class constructors.

Concepts Tested

This problem tests:

- Inheritance
- Constructor overloading
- Constructor chaining using "super()"
- Inherited variables and methods
- Object state modification
- Methods with parameters
- Methods with return values
- Conditional statements
- Tax and discount calculations
- Real-world online shopping logic*/

// Solution

class notebook12 {
    public static void main(String[] args) {
        PremiumOrder o1 = new PremiumOrder(101, "Ravi", "Silver");
        o1.showOrder();
        o1.addPurchase(3000);
        o1.showPremiumOrder();

        PremiumOrder o2 = new PremiumOrder(102, "Ananya", 6000, "Gold");
        o2.showOrder();
        o2.applyFreeDelivery();
        o2.showPremiumOrder();

        PremiumOrder o3 = new PremiumOrder(103, "Kiran", 10000, "Platinum", 15, 200);
        o3.showOrder();
        o3.applyFreeDelivery();
        o3.showPremiumOrder();
    }
}

class Order {
    int orderId;
    String customerName;
    double orderAmount;

    Order(int orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderAmount = 0;
    }

    Order(int orderId, String customerName, double orderAmount) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderAmount = orderAmount;
    }

    void showOrder() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Order Amount: " + orderAmount);
    }

    double calculateTax() {
        return orderAmount * 5 / 100;
    }

    double getAmountWithTax() {
        return orderAmount + calculateTax();
    }
}

class PremiumOrder extends Order {
    String membershipType;
    double discountPercentage = 5;
    double deliveryCharge = 100;

    PremiumOrder(int orderId, String customerName, String membershipType) {
        super(orderId, customerName);
        this.membershipType = membershipType;
    }

    PremiumOrder(int orderId, String customerName, double orderAmount, String membershipType) {
        super(orderId, customerName, orderAmount);
        this.membershipType = membershipType;
    }

    PremiumOrder(int orderId, String customerName, double orderAmount, String membershipType, double discountPercentage, double deliveryCharge) {
        super(orderId, customerName, orderAmount);
        this.membershipType = membershipType;
        this.discountPercentage = discountPercentage;
        this.deliveryCharge = deliveryCharge;
    }

    double calculateDiscount() {
        return orderAmount * discountPercentage / 100;
    }

    double calculateFinalBill() {
        return getAmountWithTax() + deliveryCharge - calculateDiscount();
    }

    void applyFreeDelivery() {
        if (orderAmount >= 5000) {
            deliveryCharge = 0;
        }
    }

    void addPurchase(double amount) {
        orderAmount = orderAmount + amount;
        applyFreeDelivery();
    }

    void showPremiumOrder() {
        System.out.println();
        showOrder();
        System.out.println("Membership Type: " + membershipType);
        System.out.println("Discount Percentage: " + discountPercentage);
        System.out.println("Delivery Charge: " + deliveryCharge);
        System.out.println("Tax: " + calculateTax());
        System.out.println("Discount Amount: " + calculateDiscount());
        System.out.println("Final Bill: " + calculateFinalBill());
        System.out.println();
    }
}
