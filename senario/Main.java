import java.util.*;

class Product {
    final int id;
    String name;
    String brand;
    double price;
    int quantity;
    Product(int id, String name, String brand, double price, int quantity) {
        this.id=id;
        this.name=name;
        this.brand=brand;
        this.price=price;
        this.quantity=quantity;
    }
    @Override
    public String toString() {
        return name + ": " + quantity;
    }
}

class Main {
    static List<Product> cart = new ArrayList<>();
    static void addToCart(Product p, int qty) {
        for(Product item : cart) {
            if(item.id == p.id) {
                item.quantity = item.quantity + qty;
                return;
            }
        }
        p.quantity = qty;
        cart.add(p);
    }
    public static void main(String[] args) {
        Product laptop=new Product(101, "Laptop", "DELL", 89999, 2);
        Product ipad=new Product(102, "ipad", "apple", 159999, 4);
        Product iphone=new Product(103, "iphone", "apple", 299999, 3);
        Product monitor=new Product(104, "monitor", "BEN-Q", 54000, 1);
        addToCart(laptop,2);
        addToCart(laptop,1);
        addToCart(ipad,4);
        addToCart(iphone,3);
        addToCart(monitor,1);
        System.out.println(cart);
    }
}