/*
There are 10 products in a shop with different inventory.
A customer can purchase anything with any quantity, after which an order gets generated
and a bill is produced. The customer can pay the bill, which would show paid status on the order.

Classes: Product, Customer, Order, main
- Create 10 products with different stock
- Customer c = new Customer();  c.addToCart(p1, 3);
- Order o = new Order(c.cart, c);
- float bill = 0.0f;  for (Product p : cart) ...
- Think in has-a (attributes) and methods (input, output parameters)
- Order id = Math.random()
- Code should be loosely coupled
- Show the time taken for execution

Has-a:
Order HAS-A Customer and a list of Items
Item HAS-A Product and a quantity
Customer HAS-A cart (list of Items)

Loosely coupled:
Customer pays using the Payment interface, not a fixed class.
New payment types (Card, NetBanking) can be added without changing Customer or Order.
*/

import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) {
        long start = System.nanoTime();

        // 10 products with different stock
        Product p1 = new Product(101, "Laptop", 65000, 5);
        Product p2 = new Product(102, "Mobile", 25000, 10);
        Product p3 = new Product(103, "Headphones", 2000, 20);
        Product p4 = new Product(104, "Keyboard", 1500, 15);
        Product p5 = new Product(105, "Mouse", 800, 25);
        Product p6 = new Product(106, "Monitor", 12000, 4);
        Product p7 = new Product(107, "Printer", 9000, 3);
        Product p8 = new Product(108, "Pen Drive", 600, 50);
        Product p9 = new Product(109, "Speaker", 3500, 8);
        Product p10 = new Product(110, "Webcam", 2500, 6);

        Product[] shop = {p1, p2, p3, p4, p5, p6, p7, p8, p9, p10};
        System.out.println("----- PRODUCTS IN SHOP -----");
        for (Product p : shop) {
            System.out.println(p);
        }

        Customer c = new Customer("Vikas");
        System.out.println("----- ADDING TO CART -----");
        c.addToCart(p1, 1);
        c.addToCart(p3, 2);
        c.addToCart(p5, 3);
        c.addToCart(p7, 5);     // more than stock
        c.addToCart(p3, 1);     // same product again, quantity is added
        c.removeFromCart(p5);

        Order o = new Order(c.cart, c);
        o.placeOrder();

        System.out.println("----- BILL -----");
        o.printBill();

        System.out.println("----- PAYMENT -----");
        c.payTheBill(o, new UpiPayment("vikas@upi"));
        o.showStatus();

        System.out.println("----- STOCK AFTER ORDER -----");
        System.out.println(p1);
        System.out.println(p3);

        long end = System.nanoTime();
        System.out.println("Time taken: " + (end - start) / 1000000.0 + " ms");
    }
}

class Product {
    final int id;
    String name;
    double price;
    int stock;

    Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    boolean isAvailable(int quantity) {
        return quantity <= stock;
    }

    void reduceStock(int quantity) {
        stock = stock - quantity;
    }

    @Override
    public String toString() {
        return id + " - " + name + " - Rs." + price + " - Stock: " + stock;
    }
}

// Product quantity in stock and quantity the customer buys are kept separate
class Item {
    Product product;
    int quantity;

    Item(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    double getAmount() {
        return product.price * quantity;
    }
}

class Customer {
    String name;
    List<Item> cart = new ArrayList<>();

    Customer(String name) {
        this.name = name;
    }

    void addToCart(Product p, int quantity) {
        for (Item item : cart) {
            if (item.product.id == p.id) {
                if (!p.isAvailable(item.quantity + quantity)) {
                    System.out.println("Only " + p.stock + " " + p.name + " in stock");
                    return;
                }
                item.quantity = item.quantity + quantity;
                System.out.println(p.name + " quantity updated to " + item.quantity);
                return;
            }
        }
        if (!p.isAvailable(quantity)) {
            System.out.println("Only " + p.stock + " " + p.name + " in stock");
            return;
        }
        cart.add(new Item(p, quantity));
        System.out.println(quantity + " x " + p.name + " added to cart");
    }

    void removeFromCart(Product p) {
        for (Item item : cart) {
            if (item.product.id == p.id) {
                cart.remove(item);
                System.out.println(p.name + " removed from cart");
                return;
            }
        }
    }

    void payTheBill(Order o, Payment payment) {
        if (payment.pay(o.calculateBill())) {
            o.paymentStatus = "Paid";
        } else {
            o.paymentStatus = "Failed";
        }
    }
}

class Order {
    int orderId;
    List<Item> items;
    Customer customer;
    String paymentStatus = "Pending";
    String deliveryStatus = "Not Shipped";

    Order(List<Item> items, Customer customer) {
        this.orderId = (int) (Math.random() * 9000) + 1000;
        this.items = items;
        this.customer = customer;
    }

    void placeOrder() {
        for (Item item : items) {
            item.product.reduceStock(item.quantity);
        }
        deliveryStatus = "Order Placed";
        System.out.println("Order " + orderId + " placed for " + customer.name);
    }

    double calculateBill() {
        double bill = 0;
        for (Item item : items) {
            bill = bill + item.getAmount();
        }
        return bill;
    }

    void printBill() {
        System.out.println("Order ID: " + orderId);
        for (Item item : items) {
            System.out.println(item.product.name + " x " + item.quantity + " = Rs." + item.getAmount());
        }
        System.out.println("Total Bill: Rs." + calculateBill());
    }

    void showStatus() {
        System.out.println("Order " + orderId + " | Payment: " + paymentStatus + " | Delivery: " + deliveryStatus);
    }
}

// Loose coupling: Customer depends on this interface, not on a fixed payment class
interface Payment {
    boolean pay(double amount);
}

class CodPayment implements Payment {
    public boolean pay(double amount) {
        System.out.println("Pay Rs." + amount + " in cash on delivery");
        return true;
    }
}

class UpiPayment implements Payment {
    String upiId;

    UpiPayment(String upiId) {
        this.upiId = upiId;
    }

    public boolean pay(double amount) {
        System.out.println("Rs." + amount + " paid using UPI " + upiId);
        return true;
    }
}
