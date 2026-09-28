/*

1. Online Shopping System

Develop a Java program for an Online Shopping System using inheritance and constructor overloading.

Create a parent class named "Product" with the following data members:

String productName;
double price;
int stock;

The "Product" class must contain the following overloaded constructors:

Product(String productName, double price)

Product(String productName, double price, int stock)

If the first constructor is used, initialize "stock" to "10".

The "Product" class should contain the following methods:

void showProduct()

Displays the product name, price, and available stock.

double calculateTotal(int quantity)

Returns:

price × quantity

boolean checkStock(int quantity)

Returns "true" if the required quantity is available; otherwise returns "false".

void updateStock(int quantity)

Reduces the available stock after a successful purchase.

---

Child Class: Electronics

Create a class "Electronics" that inherits from "Product".

Add the following data members:

String brand;
int warrantyYears;
double discountPercentage;

The "Electronics" class must contain the following overloaded constructors:

Electronics(String productName, double price, String brand)

Electronics(String productName, double price, int stock,
            String brand, int warrantyYears)

Electronics(String productName, double price, int stock,
            String brand, int warrantyYears,
            double discountPercentage)

Use "super(...)" to initialize the inherited data members.

Use the following default values where necessary:

- "warrantyYears = 1"
- "discountPercentage = 5"

---

Additional Methods in "Electronics"

Create a method:

void showElectronicsDetails()

It should display:

Product Name
Price
Stock
Brand
Warranty
Discount Percentage

You may call the inherited "showProduct()" method inside this method.

---

Create a method:

double calculateDiscount(int quantity)

The discount should be calculated on the total purchase amount.

For example:

Price = 50000
Quantity = 2

Total = 100000

Discount = 10%

Discount Amount = 10000

---

Create another method:

double calculateFinalAmount(int quantity)

The final amount should be:

Final Amount = Total Amount - Discount Amount

---

Create a method:

void purchase(int quantity)

The method should perform the following operations:

1. Check whether sufficient stock is available.
2. If stock is available:
   - Calculate the total price.
   - Calculate the discount.
   - Calculate the final amount.
   - Display the bill.
   - Reduce the stock.
3. If sufficient stock is not available, display:

Insufficient stock

---

Main Program

In the "main()" method, create the following objects using different overloaded constructors.

Product 1

Product Name: Smartphone
Price: 30000
Brand: Samsung

Use the constructor:

Electronics(String productName, double price, String brand)

Purchase:

Quantity = 2

---

Product 2

Product Name: Laptop
Price: 65000
Stock: 5
Brand: Dell
Warranty: 2 years

Use the constructor:

Electronics(String productName, double price, int stock,
            String brand, int warrantyYears)

Purchase:

Quantity = 1

---

Product 3

Product Name: Television
Price: 80000
Stock: 4
Brand: Sony
Warranty: 3 years
Discount: 10%

Use the constructor:

Electronics(String productName, double price, int stock,
            String brand, int warrantyYears,
            double discountPercentage)

Purchase:

Quantity = 2

---

Sample Output

Product Name: Smartphone
Price: 30000.0
Stock: 10
Brand: Samsung
Warranty: 1 years
Discount: 5.0%

Quantity Purchased: 2
Total Amount: 60000.0
Discount Amount: 3000.0
Final Amount: 57000.0
Remaining Stock: 8


Product Name: Laptop
Price: 65000.0
Stock: 5
Brand: Dell
Warranty: 2 years
Discount: 5.0%

Quantity Purchased: 1
Total Amount: 65000.0
Discount Amount: 3250.0
Final Amount: 61750.0
Remaining Stock: 4


Product Name: Television
Price: 80000.0
Stock: 4
Brand: Sony
Warranty: 3 years
Discount: 10.0%

Quantity Purchased: 2
Total Amount: 160000.0
Discount Amount: 16000.0
Final Amount: 144000.0
Remaining Stock: 2

Concepts Tested

- Classes and objects
- Single inheritance
- Inherited data members and methods
- Constructor overloading in the parent class
- Constructor overloading in the child class
- Constructor chaining using "super()"
- Default values through constructors
- Methods with parameters and return values
- Stock validation
- Discount calculation
- Updating object state after purchase
- Calling inherited methods from the child class

Restrictions

- Do not use method overriding.
- Do not use arrays or collections.
- Do not use interfaces or abstract classes.
- At least two overloaded constructors must be present in the parent class.
- At least three overloaded constructors must be present in the child class.
- The child constructors must use "super(...)" to initialize parent-class data.*/

// Solution

class notebook4 {
    public static void main(String[] args) {
        Electronics p1 = new Electronics("Smartphone", 30000, "Samsung");
        p1.showElectronicsDetails();
        p1.purchase(2);

        Electronics p2 = new Electronics("Laptop", 65000, 5, "Dell", 2);
        p2.showElectronicsDetails();
        p2.purchase(1);

        Electronics p3 = new Electronics("Television", 80000, 4, "Sony", 3, 10);
        p3.showElectronicsDetails();
        p3.purchase(2);
    }
}

class Product {
    String productName;
    double price;
    int stock;

    Product(String productName, double price) {
        this.productName = productName;
        this.price = price;
        this.stock = 10;
    }

    Product(String productName, double price, int stock) {
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }

    void showProduct() {
        System.out.println("Product Name: " + productName);
        System.out.println("Price: " + price);
        System.out.println("Stock: " + stock);
    }

    double calculateTotal(int quantity) {
        return price * quantity;
    }

    boolean checkStock(int quantity) {
        return quantity <= stock;
    }

    void updateStock(int quantity) {
        stock = stock - quantity;
    }
}

class Electronics extends Product {
    String brand;
    int warrantyYears = 1;
    double discountPercentage = 5;

    Electronics(String productName, double price, String brand) {
        super(productName, price);
        this.brand = brand;
    }

    Electronics(String productName, double price, int stock, String brand, int warrantyYears) {
        super(productName, price, stock);
        this.brand = brand;
        this.warrantyYears = warrantyYears;
    }

    Electronics(String productName, double price, int stock, String brand, int warrantyYears, double discountPercentage) {
        super(productName, price, stock);
        this.brand = brand;
        this.warrantyYears = warrantyYears;
        this.discountPercentage = discountPercentage;
    }

    void showElectronicsDetails() {
        showProduct();
        System.out.println("Brand: " + brand);
        System.out.println("Warranty: " + warrantyYears + " years");
        System.out.println("Discount: " + discountPercentage + "%");
        System.out.println();
    }

    double calculateDiscount(int quantity) {
        return calculateTotal(quantity) * discountPercentage / 100;
    }

    double calculateFinalAmount(int quantity) {
        return calculateTotal(quantity) - calculateDiscount(quantity);
    }

    void purchase(int quantity) {
        if (checkStock(quantity)) {
            System.out.println("Quantity Purchased: " + quantity);
            System.out.println("Total Amount: " + calculateTotal(quantity));
            System.out.println("Discount Amount: " + calculateDiscount(quantity));
            System.out.println("Final Amount: " + calculateFinalAmount(quantity));
            updateStock(quantity);
            System.out.println("Remaining Stock: " + stock);
        } else {
            System.out.println("Insufficient stock");
        }
        System.out.println();
    }
}
