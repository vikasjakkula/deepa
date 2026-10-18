abstract class Products {
    final int productID;
    String productName;
    String brand;
    double retailPrice;
    int quantity;

    Products(int productID, String productName, String brand, double retailPrice, int quantity) {
        this.productID = productID;
        this.productName = productName;
        this.brand = brand;
        this.retailPrice = retailPrice;
        this.quantity = quantity;
    }
}

class Items extends Products {
    Items(int productID, String productName, String brand, double retailPrice, int quantity) {
        super(productID, productName, brand, retailPrice, quantity);
    }
    @Override
    public String toString() {
        return productName;
    }
}

public class main {
    public static void main(String[] args) {
        Products p1 = new Items(101, "xyz", "ios", 99.9, 1);
        System.out.println(p1);
    }
}
