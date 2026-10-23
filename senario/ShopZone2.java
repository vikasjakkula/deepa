/*
JAVA OOP ASSESSMENT
Level-2: Moderate Complexity  (full question in question2.txt)

1. Manufacturer: name and country.
   Samsung - South Korea, Apple - USA, Nestle - Switzerland, Nike - USA
2. Product: ID, name, price, quantity, manufacturer.
   Display details, total value (price x quantity), modify quantity,
   compare by product ID (same ID = same product), readable format.
3. Electronic: warranty in months, 10% discount, some have Bluetooth.
   Laptop: 101, Rs.60000, Qty 1, Samsung, 24 months
   Bluetooth Headphones: 102, Rs.3000, Qty 2, Apple, 12 months, Bluetooth available
4. Grocery: expiry date, no discount.
   Rice: 103, Rs.800, Qty 2, Nestle, Expiry 31-12-2026
5. Clothing: size, 15% discount.
   Sports Shoes: 104, Rs.4000, Qty 1, Nike, Size 9
6. Product collection: add, display, find by ID, search by name (ignore case), statistics.
7. Shopping cart: add with existing quantity or a given quantity, display cart.
8. Same product added again -> add to the existing quantity (no new entry).
9. Discount: Electronic 10%, Grocery 0%, Clothing 15% (on price x quantity).
10. Bill: product name, amount payable, subtotal, total discount, final amount.
11. Search by name and by ID.
12. Product-specific operations: Bluetooth, expiry.
13. Copy Sports Shoes, change the copy quantity to 3, the original stays 1.
14. Main: steps 1 to 17.

Expected bill:
Laptop : Rs.54000.00
Bluetooth Headphones : Rs.8100.00
Rice : Rs.1600.00
Sports Shoes : Rs.3400.00
Subtotal : Rs.71600.00
Discount : Rs.6100.00
Final Amount : Rs.65500.00
*/

import java.util.ArrayList;
import java.util.List;

public class ShopZone2 {
    public static void main(String[] args) throws CloneNotSupportedException {
        // 1. Manufacturers
        Manufacturer samsung = new Manufacturer("Samsung", "South Korea");
        Manufacturer apple = new Manufacturer("Apple", "USA");
        Manufacturer nestle = new Manufacturer("Nestle", "Switzerland");
        Manufacturer nike = new Manufacturer("Nike", "USA");

        // 2. Products
        Product laptop = new ElectronicProduct(101, "Laptop", 60000, 1, samsung, 24, false);
        Product headphones = new ElectronicProduct(102, "Bluetooth Headphones", 3000, 2, apple, 12, true);
        Product rice = new GroceryProduct(103, "Rice", 800, 2, nestle, "31-12-2026");
        ClothingProduct shoes = new ClothingProduct(104, "Sports Shoes", 4000, 1, nike, 9);

        // 3. Add to the collection
        ProductStore shop = new ProductStore();
        shop.addProduct(laptop);
        shop.addProduct(headphones);
        shop.addProduct(rice);
        shop.addProduct(shoes);

        // 4. Display all
        System.out.println("----- AVAILABLE PRODUCTS -----");
        shop.displayProducts();

        // 5. Search by name
        shop.searchByName("Laptop");

        // 6. Value of each product (same method, different objects)
        System.out.println("----- POLYMORPHISM -----");
        for (Product p : shop.products) {
            System.out.println(p.name + " total price = Rs." + p.getTotalValue());
        }

        // 7, 8. Special features
        System.out.println("----- SPECIAL FEATURES -----");
        ElectronicProduct hp = (ElectronicProduct) headphones;
        hp.showBluetooth();
        GroceryProduct r = (GroceryProduct) rice;
        r.showExpiry();

        // 9. Statistics
        System.out.println("----- PRODUCT STATISTICS -----");
        shop.showStatistics();

        // Find by ID
        System.out.println("----- FIND BY ID -----");
        System.out.println(shop.findProduct(103));
        shop.findProduct(999);

        // 10, 11, 12. Cart
        System.out.println("----- ADDING TO CART -----");
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(laptop);
        cart.addProduct(headphones);
        cart.addProduct(rice);
        cart.addProduct(shoes);
        cart.addProduct(headphones, 1);

        // 13. Display cart
        System.out.println("----- SHOPPING CART -----");
        cart.displayCart();

        // 14. Bill
        System.out.println("----- BILL DETAILS -----");
        cart.generateBill();

        // 15, 16, 17. Copy
        System.out.println("----- COPYING PRODUCT -----");
        ClothingProduct shoesCopy = (ClothingProduct) shoes.clone();
        System.out.println("Original : " + shoes);
        System.out.println("Copy     : " + shoesCopy);
        shoesCopy.setQuantity(3);
        System.out.println("After changing copy quantity:");
        System.out.println("Original : " + shoes);
        System.out.println("Copy     : " + shoesCopy);
        System.out.println("Same product? " + shoes.equals(shoesCopy));
        System.out.println("Same object?  " + (shoes == shoesCopy));
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

abstract class Product implements Cloneable {
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

    // Same product ID = same product
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Product)) return false;
        Product p = (Product) obj;
        return this.productId == p.productId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(productId);
    }

    // Shallow copy is enough: quantity is a primitive, so the copy gets its own value
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
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

// The ShopZone product collection
class ProductStore {
    List<Product> products = new ArrayList<>();

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

// Product quantity = stock, CartItem quantity = how many the customer buys
class CartItem {
    Product product;
    int quantity;

    CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    double getAmount() {
        return product.price * quantity;
    }

    double getDiscount() {
        return getAmount() * product.getDiscountPercent() / 100;
    }

    double getPayable() {
        return getAmount() - getDiscount();
    }

    @Override
    public String toString() {
        return product.productId + " - " + product.name + " - Rs." + product.price
                + " - Qty: " + quantity + " - " + product.getExtraInfo();
    }
}

class ShoppingCart {
    List<CartItem> items = new ArrayList<>();

    // Add using the product's existing quantity
    void addProduct(Product p) {
        addProduct(p, p.quantity);
    }

    // Add a particular quantity, merge if already present
    void addProduct(Product p, int quantity) {
        for (CartItem item : items) {
            if (item.product.equals(p)) {
                item.quantity = item.quantity + quantity;
                System.out.println(p.name + " quantity merged in cart.");
                return;
            }
        }
        items.add(new CartItem(p, quantity));
        System.out.println(p.name + " added to cart.");
    }

    void displayCart() {
        for (CartItem item : items) {
            System.out.println(item);
        }
    }

    void generateBill() {
        double subtotal = 0;
        double totalDiscount = 0;
        for (CartItem item : items) {
            System.out.printf("%s : Rs.%.2f%n", item.product.name, item.getPayable());
            subtotal = subtotal + item.getAmount();
            totalDiscount = totalDiscount + item.getDiscount();
        }
        System.out.printf("Subtotal : Rs.%.2f%n", subtotal);
        System.out.printf("Discount : Rs.%.2f%n", totalDiscount);
        System.out.printf("Final Amount : Rs.%.2f%n", subtotal - totalDiscount);
    }
}
