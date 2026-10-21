/*
JAVA OOP ASSESSMENT
Level-2: Moderate Complexity  (Sections 1 to 6)

ShopZone is an online shopping application that manages different types of products such as
electronic items, grocery items, and clothing products.

1. Manufacturer Management
For every manufacturer, maintain:
- Manufacturer name
- Country of origin
Manufacturers: Samsung - South Korea, Apple - USA, Nestle - Switzerland, Nike - USA

2. Product Management
Common information for every product:
- Product ID, Product name, Price, Available quantity, Manufacturer details
The application should be able to:
- Create products with the required information.
- Display complete product details.
- Calculate the total value of a product (price x quantity).
- Modify the quantity of a product.
- Compare products based on their product ID.
- Display product information in a readable format.
Two product objects having the same product ID should be treated as the same product.

3. Electronic Products
Extra: Warranty period in months. 10% discount.
Some electronic products provide Bluetooth support (display a suitable message).
Laptop: 101, Rs.60000, Qty 1, Samsung, 24 months
Bluetooth Headphones: 102, Rs.3000, Qty 2, Apple, 12 months, Bluetooth available

4. Grocery Products
Extra: Expiry date. No discount. Display the expiry information.
Rice: 103, Rs.800, Qty 2, Nestle, Expiry 31-12-2026

5. Clothing Products
Extra: Size. 15% discount.
Sports Shoes: 104, Rs.4000, Qty 1, Nike, Size 9

6. Product Collection
- Add Product
- Display Products (ID, name, price, quantity and extra information)
- Find Product by ID (show "not found" if it does not exist)
- Search by Name (should not depend on uppercase / lowercase: Laptop, laptop, LAPTOP)
- Product Statistics: number of products and total value of all products

Main (steps 1 to 9):
1. Create the manufacturer information.
2. Create the four products.
3. Add all products to the ShopZone product collection.
4. Display all available products.
5. Search for the Laptop by name.
6. Display the calculated value of each product based on its quantity.
7. Display Bluetooth information for the Bluetooth Headphones.
8. Display expiry information for Rice.
9. Display product statistics.

Expected Output (till statistics):
----- AVAILABLE PRODUCTS -----
101 - Laptop - Rs.60000.0 - Qty: 1 - Warranty: 24 months
102 - Bluetooth Headphones - Rs.3000.0 - Qty: 2 - Warranty: 12 months
103 - Rice - Rs.800.0 - Qty: 2 - Expiry: 31-12-2026
104 - Sports Shoes - Rs.4000.0 - Qty: 1 - Size: 9
Search result for: Laptop
101 - Laptop - Rs.60000.0 - Qty: 1 - Warranty: 24 months
----- POLYMORPHISM -----
Laptop total price = Rs.60000.0
Bluetooth Headphones total price = Rs.6000.0
Rice total price = Rs.1600.0
Sports Shoes total price = Rs.4000.0
----- SPECIAL FEATURES -----
Bluetooth Headphones supports Bluetooth.
Rice expiry date: 31-12-2026
----- PRODUCT STATISTICS -----
Number of products: 4
Total product value: Rs.71600.00
*/

/*
import java.util.ArrayList;
import java.util.List;

public class ShopZone2 {
    public static void main(String[] args) {
        Manufacturer samsung = new Manufacturer("Samsung", "South Korea");
        Manufacturer apple = new Manufacturer("Apple", "USA");
        Manufacturer nestle = new Manufacturer("Nestle", "Switzerland");
        Manufacturer nike = new Manufacturer("Nike", "USA");

        Product laptop = new ElectronicProduct(101, "Laptop", 60000, 1, samsung, 24, false);
        Product headphones = new ElectronicProduct(102, "Bluetooth Headphones", 3000, 2, apple, 12, true);
        Product rice = new GroceryProduct(103, "Rice", 800, 2, nestle, "31-12-2026");
        Product shoes = new ClothingProduct(104, "Sports Shoes", 4000, 1, nike, 9);

        ShopZone shop = new ShopZone();
        shop.addProduct(laptop);
        shop.addProduct(headphones);
        shop.addProduct(rice);
        shop.addProduct(shoes);

        System.out.println("----- AVAILABLE PRODUCTS -----");
        shop.displayProducts();

        shop.searchByName("Laptop");

        System.out.println("----- POLYMORPHISM -----");
        System.out.println(laptop.name + " total price = Rs." + laptop.getTotalValue());
        System.out.println(headphones.name + " total price = Rs." + headphones.getTotalValue());
        System.out.println(rice.name + " total price = Rs." + rice.getTotalValue());
        System.out.println(shoes.name + " total price = Rs." + shoes.getTotalValue());

        System.out.println("----- SPECIAL FEATURES -----");
        ElectronicProduct hp = (ElectronicProduct) headphones;
        hp.showBluetooth();
        GroceryProduct r = (GroceryProduct) rice;
        r.showExpiry();

        System.out.println("----- PRODUCT STATISTICS -----");
        shop.showStatistics();
    }
}

class Manufacturer {
    String name;
    String country;

    Manufacturer(String name, String country) {
        this.name = name;
        this.country = country;
    }

    @Override
    public String toString() {
        return name + " (" + country + ")";
    }
}

abstract class Product {
    final int productId;
    String name;
    double price;
    int quantity;
    Manufacturer manufacturer;

    Product(int productId, String name, double price, int quantity, Manufacturer manufacturer) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.manufacturer = manufacturer;
    }

    double getTotalValue() {
        return price * quantity;
    }

    void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    abstract String getExtraInfo();

    abstract double getDiscountPercent();

    double getDiscount() {
        return price * quantity * getDiscountPercent() / 100;
    }

    void displayDetails() {
        System.out.println("Product ID: " + productId);
        System.out.println("Name: " + name);
        System.out.println("Price: Rs." + price);
        System.out.println("Quantity: " + quantity);
        System.out.println("Manufacturer: " + manufacturer);
        System.out.println(getExtraInfo());
    }

    @Override
    public String toString() {
        return productId + " - " + name + " - Rs." + price + " - Qty: " + quantity + " - " + getExtraInfo();
    }

    @Override
    public boolean equals(Object obj) {
        Product p = (Product) obj;
        return this.productId == p.productId;
    }
}

class ElectronicProduct extends Product {
    int warrantyMonths;
    boolean bluetooth;

    ElectronicProduct(int productId, String name, double price, int quantity,
                      Manufacturer manufacturer, int warrantyMonths, boolean bluetooth) {
        super(productId, name, price, quantity, manufacturer);
        this.warrantyMonths = warrantyMonths;
        this.bluetooth = bluetooth;
    }

    void showBluetooth() {
        if (bluetooth) {
            System.out.println(name + " supports Bluetooth.");
        } else {
            System.out.println(name + " does not support Bluetooth.");
        }
    }

    @Override
    String getExtraInfo() {
        return "Warranty: " + warrantyMonths + " months";
    }

    @Override
    double getDiscountPercent() {
        return 10;
    }
}

class GroceryProduct extends Product {
    String expiryDate;

    GroceryProduct(int productId, String name, double price, int quantity,
                   Manufacturer manufacturer, String expiryDate) {
        super(productId, name, price, quantity, manufacturer);
        this.expiryDate = expiryDate;
    }

    void showExpiry() {
        System.out.println(name + " expiry date: " + expiryDate);
    }

    @Override
    String getExtraInfo() {
        return "Expiry: " + expiryDate;
    }

    @Override
    double getDiscountPercent() {
        return 0;
    }
}

class ClothingProduct extends Product {
    int size;

    ClothingProduct(int productId, String name, double price, int quantity,
                    Manufacturer manufacturer, int size) {
        super(productId, name, price, quantity, manufacturer);
        this.size = size;
    }

    @Override
    String getExtraInfo() {
        return "Size: " + size;
    }

    @Override
    double getDiscountPercent() {
        return 15;
    }
}

class ShopZone {
    List<Product> products = new ArrayList<Product>();

    void addProduct(Product p) {
        products.add(p);
    }

    void displayProducts() {
        for (Product p : products) {
            System.out.println(p);
        }
    }

    Product findProduct(int productId) {
        for (Product p : products) {
            if (p.productId == productId) {
                return p;
            }
        }
        System.out.println("Product " + productId + " not found.");
        return null;
    }

    void searchByName(String name) {
        System.out.println("Search result for: " + name);
        for (Product p : products) {
            if (p.name.equalsIgnoreCase(name)) {
                System.out.println(p);
                return;
            }
        }
        System.out.println("Product not found.");
    }

    void showStatistics() {
        double total = 0;
        for (Product p : products) {
            total = total + p.getTotalValue();
        }
        System.out.println("Number of products: " + products.size());
        System.out.printf("Total product value: Rs.%.2f%n", total);
    }
}
*/