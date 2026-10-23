/*
JAVA OOP ASSESSMENT
ShopZone - Level 1: Simple  (full question in question.txt)

1. Product: ID, Name, Brand, Retail Price, Available Quantity.
   ID must be supplied at creation and should not change. A generic Product should not be created.
2. Every concrete product provides: displayProductDetails(), calculateDiscount(), calculateTax(),
   calculateDeliveryCharge(), getReturnPeriod(), getDescription(), getPostPurchaseMessage()
3. Electronics (Manufacturer, Warranty Period, Power Consumption)
   Laptop 10% / 18% / Rs.500 / 15 days
   Television 8% / 18% / Rs.800 / 10 days
   BluetoothSpeaker 12% / 18% / Rs.150 / 7 days
4. Grocery (Manufacturing Date, Expiry Date, Weight)
   Milk 2% / 5% / Rs.30 / 0 days
   Bread 3% / 5% / Rs.20 / 0 days
   Juice 5% / 12% / Rs.40 / 2 days
5. Clothing (Size, Material, Gender Category)
   Shirt 20% / 12% / Rs.80 / 30 days
   Jeans 15% / 12% / Rs.100 / 30 days
6. Medicine (Medicine Name, Manufacturer, Expiry Date, Dosage) with its own values.
7. BluetoothSpeaker: connectBluetooth(), disconnectBluetooth()
8. Milk, Bread, Juice, Medicine: getExpiryDate(), checkExpiry()
9-10. Every product gives a meaningful description and a post-purchase message.
11-13. ShoppingCart: addProduct(), removeProduct(), displayCart(), calculateTotal(), checkout()
   addProduct(product) and addProduct(product, quantity) - quantity must not exceed stock.
   Final Price = Original Price - Discount + Tax + Delivery Charge
14-22. Product[], same reference with different objects, search by ID / name,
   statistics (highest, lowest, average price, total inventory value) and the main application.

Note: Tax is calculated on (price - discount). Laptop: (65000 - 6500) * 18% = 10530
*/

import java.util.ArrayList;
import java.util.List;

// Part 20 calls the main class ShoppingApplication. It is named ShopZone here so it matches the file name.
class ShopZone {
    public static void main(String[] args) {
        Laptop laptop1 = new Laptop("E101", "Dell Laptop", "Dell", 65000, 10, "Dell", 12, 65, "Intel Core i5", "8GB RAM");
        Laptop laptop2 = new Laptop("E102", "HP Laptop", "HP", 55000, 5, "HP", 12, 60, "Intel Core i3", "8GB RAM");
        Television tv = new Television("E103", "Sony Television", "Sony", 45000, 4, "Sony", 24, 120, "55 inch");
        BluetoothSpeaker speaker = new BluetoothSpeaker("E104", "JBL Speaker", "JBL", 3500, 15, "JBL", 12, 10);

        Milk milk = new Milk("G101", "Amul Milk", "Amul", 68, 20, "10-10-2026", "12-10-2026", "1 Litre");
        Bread bread = new Bread("G102", "Modern Bread", "Modern", 45, 15, "20-10-2026", "25-10-2026", "400 g");
        Juice juice = new Juice("G103", "Real Juice", "Dabur", 120, 12, "01-10-2026", "01-04-2027", "1 Litre");

        Shirt shirt = new Shirt("C101", "Cotton Shirt", "Allen Solly", 1500, 10, "L", "Cotton", "Men");
        Jeans jeans = new Jeans("C102", "Denim Jeans", "Levi's", 2500, 8, "32", "Denim", "Men");

        Medicine paracetamol = new Medicine("M101", "Paracetamol", "Cipla", 30, 50, "Cipla", "01-06-2027", "500 mg");
        Medicine cetirizine = new Medicine("M102", "Cetirizine", "Dr. Reddy's", 25, 40, "Dr. Reddy's", "01-03-2027", "10 mg");

        Product[] products = {laptop1, laptop2, tv, speaker, milk, bread, juice, shirt, jeans, paracetamol, cetirizine};

        // Same reference, different objects
        System.out.println("========== SHOPZONE PRODUCTS ==========");
        Product currentProduct;
        currentProduct = laptop1;
        currentProduct.displayProductDetails();
        currentProduct = milk;
        currentProduct.displayProductDetails();
        currentProduct = shirt;
        currentProduct.displayProductDetails();

        // One loop, different results
        System.out.println("========== POLYMORPHISM ==========");
        Product[] sample = {laptop1, milk, shirt, speaker};
        for (Product p : sample) {
            System.out.println(p.getClass().getSimpleName() + " Discount: Rs." + p.calculateDiscount());
        }

        System.out.println("========== ALL PRODUCTS ==========");
        for (Product p : products) {
            System.out.println(p.id + " - " + p.name + " - Rs." + p.price + " - Return: " + p.getReturnPeriod() + " days");
        }

        System.out.println("========== SEARCH ==========");
        System.out.println("Search ID: E101");
        searchById(products, "E101");
        System.out.println("Search Name: Milk");
        searchByName(products, "Milk");
        System.out.println("Search ID: X999");
        searchById(products, "X999");

        System.out.println("========== STATISTICS ==========");
        showStatistics(products);

        System.out.println("========== BLUETOOTH ==========");
        speaker.connectBluetooth();
        speaker.disconnectBluetooth();

        System.out.println("========== EXPIRY ==========");
        Expirable[] expiryItems = {milk, bread, juice, paracetamol};
        for (Expirable e : expiryItems) {
            e.checkExpiry();
            System.out.println("Expiry Date: " + e.getExpiryDate());
        }

        // Overloading: addProduct(product) and addProduct(product, quantity)
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(laptop1);
        cart.addProduct(milk, 2);
        cart.addProduct(shirt);
        cart.addProduct(speaker);
        cart.addProduct(paracetamol);
        cart.addProduct(tv, 50);
        cart.addProduct(tv);
        cart.removeProduct(tv);

        System.out.println("========== SHOPPING CART ==========");
        cart.displayCart();

        System.out.println("========== BILL ==========");
        cart.checkout();
    }

    static void searchById(Product[] products, String id) {
        for (Product p : products) {
            if (p.id.equals(id)) {
                p.displayProductDetails();
                return;
            }
        }
        System.out.println("Product not found.");
    }

    static void searchByName(Product[] products, String name) {
        for (Product p : products) {
            if (p.name.toLowerCase().contains(name.toLowerCase())) {
                p.displayProductDetails();
                return;
            }
        }
        System.out.println("Product not found.");
    }

    static void showStatistics(Product[] products) {
        Product highest = products[0];
        Product lowest = products[0];
        double totalPrice = 0;
        double inventoryValue = 0;
        for (Product p : products) {
            if (p.price > highest.price) {
                highest = p;
            }
            if (p.price < lowest.price) {
                lowest = p;
            }
            totalPrice = totalPrice + p.price;
            inventoryValue = inventoryValue + p.price * p.quantity;
        }
        System.out.println("Highest priced product : " + highest.name + " - Rs." + highest.price);
        System.out.println("Lowest priced product  : " + lowest.name + " - Rs." + lowest.price);
        System.out.printf("Average product price  : Rs.%.2f%n", totalPrice / products.length);
        System.out.println("Total inventory value  : Rs." + inventoryValue);
    }
}

interface Bluetooth {
    void connectBluetooth();
    void disconnectBluetooth();
}

interface Expirable {
    String getExpiryDate();
    void checkExpiry();
}

abstract class Product {
    final String id;
    String name;
    String brand;
    double price;
    int quantity;

    Product(String id, String name, String brand, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
    }

    void displayProductDetails() {
        System.out.println("Product ID : " + id);
        System.out.println("Product Name : " + name);
        System.out.println("Brand : " + brand);
        System.out.println("Price : " + price);
        System.out.println("Available Qty: " + quantity);
        System.out.println(getDescription());
    }

    abstract double calculateDiscount();
    abstract double calculateTax();
    abstract double calculateDeliveryCharge();
    abstract int getReturnPeriod();
    abstract String getDescription();
    abstract String getPostPurchaseMessage();

    @Override
    public String toString() {
        return id + " - " + name;
    }
}

abstract class Electronics extends Product {
    String manufacturer;
    int warrantyPeriod;
    int powerConsumption;

    Electronics(String id, String name, String brand, double price, int quantity, String manufacturer, int warrantyPeriod, int powerConsumption) {
        super(id, name, brand, price, quantity);
        this.manufacturer = manufacturer;
        this.warrantyPeriod = warrantyPeriod;
        this.powerConsumption = powerConsumption;
    }

    // Same 18% for all electronics, so written only once here
    @Override
    double calculateTax() {
        return (price - calculateDiscount()) * 18 / 100;
    }
}

class Laptop extends Electronics {
    String processor;
    String ram;

    Laptop(String id, String name, String brand, double price, int quantity, String manufacturer, int warrantyPeriod, int powerConsumption, String processor, String ram) {
        super(id, name, brand, price, quantity, manufacturer, warrantyPeriod, powerConsumption);
        this.processor = processor;
        this.ram = ram;
    }

    @Override
    double calculateDiscount() {
        return price * 10 / 100;
    }

    @Override
    double calculateDeliveryCharge() {
        return 500;
    }

    @Override
    int getReturnPeriod() {
        return 15;
    }

    @Override
    String getDescription() {
        return "Laptop - " + processor + ", " + ram;
    }

    @Override
    String getPostPurchaseMessage() {
        return "Please register your warranty within 30 days.";
    }
}

class Television extends Electronics {
    String screenSize;

    Television(String id, String name, String brand, double price, int quantity, String manufacturer, int warrantyPeriod, int powerConsumption, String screenSize) {
        super(id, name, brand, price, quantity, manufacturer, warrantyPeriod, powerConsumption);
        this.screenSize = screenSize;
    }

    @Override
    double calculateDiscount() {
        return price * 8 / 100;
    }

    @Override
    double calculateDeliveryCharge() {
        return 800;
    }

    @Override
    int getReturnPeriod() {
        return 10;
    }

    @Override
    String getDescription() {
        return "Television - " + screenSize + ", " + powerConsumption + "W";
    }

    @Override
    String getPostPurchaseMessage() {
        return "Please register your warranty within 30 days.";
    }
}

class BluetoothSpeaker extends Electronics implements Bluetooth {
    BluetoothSpeaker(String id, String name, String brand, double price, int quantity, String manufacturer, int warrantyPeriod, int powerConsumption) {
        super(id, name, brand, price, quantity, manufacturer, warrantyPeriod, powerConsumption);
    }

    @Override
    public void connectBluetooth() {
        System.out.println("Searching for nearby Bluetooth devices...");
        System.out.println(name + " connected.");
    }

    @Override
    public void disconnectBluetooth() {
        System.out.println(name + " disconnected.");
    }

    @Override
    double calculateDiscount() {
        return price * 12 / 100;
    }

    @Override
    double calculateDeliveryCharge() {
        return 150;
    }

    @Override
    int getReturnPeriod() {
        return 7;
    }

    @Override
    String getDescription() {
        return "Bluetooth Speaker - " + powerConsumption + "W, " + warrantyPeriod + " months warranty";
    }

    @Override
    String getPostPurchaseMessage() {
        return "Keep the speaker charged and away from water.";
    }
}

abstract class Grocery extends Product implements Expirable {
    String manufactureDate;
    String expiryDate;
    String weight;

    Grocery(String id, String name, String brand, double price, int quantity, String manufactureDate, String expiryDate, String weight) {
        super(id, name, brand, price, quantity);
        this.manufactureDate = manufactureDate;
        this.expiryDate = expiryDate;
        this.weight = weight;
    }

    // 5% for most grocery items, Juice overrides it
    @Override
    double calculateTax() {
        return (price - calculateDiscount()) * 5 / 100;
    }

    @Override
    public String getExpiryDate() {
        return expiryDate;
    }

    @Override
    public void checkExpiry() {
        System.out.println("Checking whether the product has expired...");
        System.out.println(name + " expires on " + expiryDate);
    }
}

class Milk extends Grocery {
    Milk(String id, String name, String brand, double price, int quantity, String manufactureDate, String expiryDate, String weight) {
        super(id, name, brand, price, quantity, manufactureDate, expiryDate, weight);
    }

    @Override
    double calculateDiscount() {
        return price * 2 / 100;
    }

    @Override
    double calculateDeliveryCharge() {
        return 30;
    }

    @Override
    int getReturnPeriod() {
        return 0;
    }

    @Override
    String getDescription() {
        return "Milk - " + weight;
    }

    @Override
    String getPostPurchaseMessage() {
        return "Keep refrigerated and consume before expiry.";
    }
}

class Bread extends Grocery {
    Bread(String id, String name, String brand, double price, int quantity, String manufactureDate, String expiryDate, String weight) {
        super(id, name, brand, price, quantity, manufactureDate, expiryDate, weight);
    }

    @Override
    double calculateDiscount() {
        return price * 3 / 100;
    }

    @Override
    double calculateDeliveryCharge() {
        return 20;
    }

    @Override
    int getReturnPeriod() {
        return 0;
    }

    @Override
    String getDescription() {
        return "Bread - " + weight + " | Expires: " + expiryDate;
    }

    @Override
    String getPostPurchaseMessage() {
        return "Store in a cool and dry place.";
    }
}

class Juice extends Grocery {
    Juice(String id, String name, String brand, double price, int quantity, String manufactureDate, String expiryDate, String weight) {
        super(id, name, brand, price, quantity, manufactureDate, expiryDate, weight);
    }

    @Override
    double calculateDiscount() {
        return price * 5 / 100;
    }

    @Override
    double calculateTax() {
        return (price - calculateDiscount()) * 12 / 100;
    }

    @Override
    double calculateDeliveryCharge() {
        return 40;
    }

    @Override
    int getReturnPeriod() {
        return 2;
    }

    @Override
    String getDescription() {
        return "Juice - " + weight + " | Expires: " + expiryDate;
    }

    @Override
    String getPostPurchaseMessage() {
        return "Refrigerate after opening.";
    }
}

abstract class Clothing extends Product {
    String size;
    String material;
    String gender;

    Clothing(String id, String name, String brand, double price, int quantity, String size, String material, String gender) {
        super(id, name, brand, price, quantity);
        this.size = size;
        this.material = material;
        this.gender = gender;
    }

    // Same 12% for all clothing
    @Override
    double calculateTax() {
        return (price - calculateDiscount()) * 12 / 100;
    }

    @Override
    int getReturnPeriod() {
        return 30;
    }

    @Override
    String getPostPurchaseMessage() {
        return "Please retain the tags for returns.";
    }
}

class Shirt extends Clothing {
    Shirt(String id, String name, String brand, double price, int quantity, String size, String material, String gender) {
        super(id, name, brand, price, quantity, size, material, gender);
    }

    @Override
    double calculateDiscount() {
        return price * 20 / 100;
    }

    @Override
    double calculateDeliveryCharge() {
        return 80;
    }

    @Override
    String getDescription() {
        return material + " Shirt - Size " + size;
    }
}

class Jeans extends Clothing {
    Jeans(String id, String name, String brand, double price, int quantity, String size, String material, String gender) {
        super(id, name, brand, price, quantity, size, material, gender);
    }

    @Override
    double calculateDiscount() {
        return price * 15 / 100;
    }

    @Override
    double calculateDeliveryCharge() {
        return 100;
    }

    @Override
    String getDescription() {
        return material + " Jeans - Size " + size + " | " + gender;
    }
}

class Medicine extends Product implements Expirable {
    String manufacturer;
    String expiryDate;
    String dosage;

    Medicine(String id, String name, String brand, double price, int quantity, String manufacturer, String expiryDate, String dosage) {
        super(id, name, brand, price, quantity);
        this.manufacturer = manufacturer;
        this.expiryDate = expiryDate;
        this.dosage = dosage;
    }

    @Override
    double calculateDiscount() {
        return price * 5 / 100;
    }

    @Override
    double calculateTax() {
        return (price - calculateDiscount()) * 12 / 100;
    }

    @Override
    double calculateDeliveryCharge() {
        return 50;
    }

    @Override
    int getReturnPeriod() {
        return 0;
    }

    @Override
    String getDescription() {
        return name + " - " + dosage + " | Expires: " + expiryDate;
    }

    @Override
    String getPostPurchaseMessage() {
        return "Store according to the manufacturer's instructions.";
    }

    @Override
    public String getExpiryDate() {
        return expiryDate;
    }

    @Override
    public void checkExpiry() {
        System.out.println("Checking whether the product has expired...");
        System.out.println(name + " expires on " + expiryDate);
    }
}

// Product quantity = stock, CartItem qty = how many the customer wants
class CartItem {
    Product product;
    int qty;

    CartItem(Product product, int qty) {
        this.product = product;
        this.qty = qty;
    }
}

class ShoppingCart {
    List<CartItem> items = new ArrayList<>();

    void addProduct(Product p) {
        addProduct(p, 1);
    }

    void addProduct(Product p, int qty) {
        for (CartItem item : items) {
            if (item.product.id.equals(p.id)) {
                if (item.qty + qty > p.quantity) {
                    System.out.println("Not enough stock for " + p.name);
                    return;
                }
                item.qty = item.qty + qty;
                System.out.println(qty + " x " + p.name + " added to cart.");
                return;
            }
        }
        if (qty > p.quantity) {
            System.out.println("Not enough stock for " + p.name);
            return;
        }
        items.add(new CartItem(p, qty));
        System.out.println(qty + " x " + p.name + " added to cart.");
    }

    void removeProduct(Product p) {
        for (CartItem item : items) {
            if (item.product.id.equals(p.id)) {
                items.remove(item);
                System.out.println(p.name + " removed from cart.");
                return;
            }
        }
        System.out.println(p.name + " is not in the cart.");
    }

    void displayCart() {
        for (CartItem item : items) {
            System.out.println("Product : " + item.product.name);
            System.out.println("Quantity: " + item.qty);
            System.out.println("Price : Rs." + item.product.price);
        }
    }

    double calculateTotal() {
        double total = 0;
        for (CartItem item : items) {
            Product p = item.product;
            total = total + p.price * item.qty - p.calculateDiscount() * item.qty
                    + p.calculateTax() * item.qty + p.calculateDeliveryCharge();
        }
        return total;
    }

    void checkout() {
        double subtotal = 0;
        double totalDiscount = 0;
        double totalTax = 0;
        double totalDelivery = 0;

        for (CartItem item : items) {
            Product p = item.product;
            double original = p.price * item.qty;
            double discount = p.calculateDiscount() * item.qty;
            double tax = p.calculateTax() * item.qty;
            double delivery = p.calculateDeliveryCharge();
            double finalAmount = original - discount + tax + delivery;

            System.out.println("Product : " + p.name);
            System.out.println("Quantity : " + item.qty);
            System.out.println("Original Amount: Rs." + original);
            System.out.println("Discount : Rs." + discount);
            System.out.println("Tax : Rs." + tax);
            System.out.println("Delivery : Rs." + delivery);
            System.out.println("Final Amount : Rs." + finalAmount);
            System.out.println("Return Period : " + p.getReturnPeriod() + " days");
            System.out.println("Message : " + p.getPostPurchaseMessage());

            subtotal = subtotal + original;
            totalDiscount = totalDiscount + discount;
            totalTax = totalTax + tax;
            totalDelivery = totalDelivery + delivery;
            p.quantity = p.quantity - item.qty;
        }

        System.out.println("--------------------------------");
        System.out.printf("Subtotal : Rs.%.2f%n", subtotal);
        System.out.printf("Total Discount : Rs.%.2f%n", totalDiscount);
        System.out.printf("Total Tax : Rs.%.2f%n", totalTax);
        System.out.printf("Total Delivery Charge: Rs.%.2f%n", totalDelivery);
        System.out.printf("Grand Total : Rs.%.2f%n", calculateTotal());
        System.out.println("--------------------------------");
        System.out.println("Thank you for shopping with ShopZone!");
        items.clear();
    }
}
