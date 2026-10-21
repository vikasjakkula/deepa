abstract class Products {
    import java.util.ArrayList;
    import java.util.List;

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
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Products)) return false;
        Products other = (Products) obj;
        return productID == other.productID;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(productID);
    }

    @Override
    public String toString() {
        return productName + " (" + brand + ") - price: " + retailPrice + ", quantity: " + quantity;
    }
}

class Items extends Products {
    Items(int productID, String productName, String brand, double retailPrice, int quantity) {
        super(productID, productName, brand, retailPrice, quantity);
    }
}

public class main {
    private static void addToCart(List<Products> cart, Products product) {
        for (Products item : cart) {
            if (item.equals(product)) {
                item.quantity += product.quantity;
                return;
            }
        }
        cart.add(product);
    }

    public static void main(String[] args) {
        List<Products> cart = new ArrayList<>();
        addToCart(cart, new Items(101, "xyz", "ios", 99.9, 1));
        addToCart(cart, new Items(101, "xyz", "ios", 99.9, 1));

        for (Products product : cart) {
            System.out.println(product);
        }
    }
}
