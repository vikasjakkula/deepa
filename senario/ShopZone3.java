import java.util.List;
import java.util.ArrayList;

class ShopZone3 {
    public static void main(String[] args) {
        // Part 4: create supplements using parent reference
        Supplement wheyProtein = new ProteinPowder(901, "Whey Protein", 2000, 40, "MuscleBlaze", "Chocolate", 1.0);
        Supplement multivitamin = new Vitamin(902, "Multivitamin", 500, 60, "HealthKart", "Vitamin A-Z", 60);
        Supplement oatsEnergyBar = new EnergyBar(903, "Oats Energy Bar", 50, 200, "Yoga Bar", 180, true);
        Supplement greenTea = new HerbalTea(904, "Green Tea", 300, 50, "Organic India", "Tulsi", false);

        SupplementStore store = new SupplementStore();
        store.addSupplement(wheyProtein);
        store.addSupplement(multivitamin);
        store.addSupplement(oatsEnergyBar);
        store.addSupplement(greenTea);

        System.out.println("----- SUPPLEMENT DETAILS -----");
        store.displayAll();

        // Part 5: statistics
        System.out.println("----- INVENTORY STATISTICS -----");
        store.displayStatistics();

        // Part 6: search and compare
        System.out.println("----- SEARCH -----");
        System.out.println("Search ID 902:");
        showSearchResult(store.searchById(902));
        System.out.println("Search name green tea:");
        showSearchResult(store.searchByName("green tea"));
        System.out.println("Search ID 999:");
        showSearchResult(store.searchById(999));
        System.out.println("Search name Protein Bar:");
        showSearchResult(store.searchByName("Protein Bar"));

        System.out.println("----- COMPARE -----");
        Supplement found = store.searchById(901);
        System.out.println("Whey Protein and Multivitamin same? " + wheyProtein.equals(multivitamin));
        System.out.println("Whey Protein and search result 901 same? " + wheyProtein.equals(found));

        // Parts 7, 8, 9, 13: add to cart, Whey Protein is merged
        System.out.println("----- ADDING TO CART -----");
        OrderCart cart = new OrderCart();
        cart.addToCart(wheyProtein, 2);
        cart.addToCart(multivitamin, 3);
        cart.addToCart(oatsEnergyBar, 10);
        cart.addToCart(greenTea, 4);
        cart.addToCart(wheyProtein, 1);

        // Parts 11, 12: same method call, different discount at runtime
        System.out.println("----- RUNTIME POLYMORPHISM -----");
        for (Supplement s : store.supplements) {
            System.out.printf("%s payable for 2 units = Rs.%.2f%n", s.getSupplementName(), s.calculatePayable(2));
        }

        // Part 14: cart
        System.out.println("----- ORDER CART -----");
        cart.displayCart();
        System.out.println("Total Quantity: " + cart.getTotalQuantity() + " units");

        // Parts 10, 15: bill
        System.out.println("----- BILL -----");
        cart.printBill();

        // Part 16: confirm and update stock
        System.out.println("----- CONFIRM ORDER -----");
        cart.confirmOrder();
        System.out.println("----- UPDATED STOCK -----");
        store.displayStock();
        store.displayStatistics();
    }

    static void showSearchResult(Supplement s) {
        if (s == null) {
            System.out.println("Supplement not found.");
        } else {
            System.out.println(s);
        }
    }
}

interface Billable {
    double calculatePayable(int quantity);
}

abstract class Supplement implements Billable {
    private int supplementId;
    private String supplementName;
    private double pricePerUnit;
    private int availableUnits;
    private String brandName;

    Supplement(int supplementId, String supplementName, double pricePerUnit, int availableUnits, String brandName) {
        this.supplementId = supplementId;
        this.supplementName = supplementName;
        this.pricePerUnit = pricePerUnit;
        this.availableUnits = availableUnits;
        this.brandName = brandName;
    }

    int getSupplementId() {
        return supplementId;
    }

    String getSupplementName() {
        return supplementName;
    }

    double getPricePerUnit() {
        return pricePerUnit;
    }

    int getAvailableUnits() {
        return availableUnits;
    }

    String getBrandName() {
        return brandName;
    }

    void setAvailableUnits(int units) {
        this.availableUnits = units;
    }

    abstract double getDiscountPercent();

    public double calculatePayable(int quantity) {
        double subtotal = pricePerUnit * quantity;
        return subtotal - subtotal * getDiscountPercent() / 100;
    }

    double getStockValue() {
        return pricePerUnit * availableUnits;
    }

    void displayDetails() {
        System.out.println("Supplement ID   : " + supplementId);
        System.out.println("Supplement Name : " + supplementName);
        System.out.println("Price per unit  : Rs." + pricePerUnit);
        System.out.println("Available Stock : " + availableUnits + " units");
        System.out.println("Brand Name      : " + brandName);
        System.out.println("Discount        : " + getDiscountPercent() + "%");
    }

    // Part 6: two supplements are the same if their IDs are the same
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Supplement)) return false;
        Supplement other = (Supplement) obj;
        return this.supplementId == other.supplementId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(supplementId);
    }

    @Override
    public String toString() {
        return supplementId + " - " + supplementName + " - Rs." + pricePerUnit + " - Stock: " + availableUnits + " - " + brandName;
    }
}

class ProteinPowder extends Supplement {
    String flavour;
    double weightInKg;

    ProteinPowder(int supplementId, String supplementName, double pricePerUnit, int availableUnits, String brandName, String flavour, double weightInKg) {
        super(supplementId, supplementName, pricePerUnit, availableUnits, brandName);
        this.flavour = flavour;
        this.weightInKg = weightInKg;
    }

    @Override
    double getDiscountPercent() {
        return 10;
    }

    @Override
    void displayDetails() {
        super.displayDetails();
        System.out.println("Flavour         : " + flavour);
        System.out.println("Weight          : " + weightInKg + " kg");
    }
}

class Vitamin extends Supplement {
    String vitaminType;
    int tabletsPerBottle;

    Vitamin(int supplementId, String supplementName, double pricePerUnit, int availableUnits, String brandName, String vitaminType, int tabletsPerBottle) {
        super(supplementId, supplementName, pricePerUnit, availableUnits, brandName);
        this.vitaminType = vitaminType;
        this.tabletsPerBottle = tabletsPerBottle;
    }

    @Override
    double getDiscountPercent() {
        return 5;
    }

    @Override
    void displayDetails() {
        super.displayDetails();
        System.out.println("Vitamin Type    : " + vitaminType);
        System.out.println("Tablets/Bottle  : " + tabletsPerBottle);
    }
}

class EnergyBar extends Supplement {
    int calories;
    boolean glutenFree;

    EnergyBar(int supplementId, String supplementName, double pricePerUnit, int availableUnits, String brandName, int calories, boolean glutenFree) {
        super(supplementId, supplementName, pricePerUnit, availableUnits, brandName);
        this.calories = calories;
        this.glutenFree = glutenFree;
    }

    @Override
    double getDiscountPercent() {
        return 8;
    }

    @Override
    void displayDetails() {
        super.displayDetails();
        System.out.println("Calories        : " + calories);
        System.out.println("Gluten Free     : " + glutenFree);
    }
}

class HerbalTea extends Supplement {
    String herbName;
    boolean caffeineFree;

    HerbalTea(int supplementId, String supplementName, double pricePerUnit, int availableUnits, String brandName, String herbName, boolean caffeineFree) {
        super(supplementId, supplementName, pricePerUnit, availableUnits, brandName);
        this.herbName = herbName;
        this.caffeineFree = caffeineFree;
    }

    @Override
    double getDiscountPercent() {
        return 15;
    }

    @Override
    void displayDetails() {
        super.displayDetails();
        System.out.println("Herb Name       : " + herbName);
        System.out.println("Caffeine Free   : " + caffeineFree);
    }
}

// Supplement stock and ordered quantity are kept separate
class CartItem {
    Supplement supplement;
    int quantity;

    CartItem(Supplement supplement, int quantity) {
        this.supplement = supplement;
        this.quantity = quantity;
    }

    double getSubtotal() {
        return supplement.getPricePerUnit() * quantity;
    }

    double getFinalAmount() {
        return supplement.calculatePayable(quantity);
    }

    double getDiscountAmount() {
        return getSubtotal() - getFinalAmount();
    }
}

class OrderCart {
    List<CartItem> item = new ArrayList<>();

    boolean addToCart(Supplement s, int quantity) {
        if (s == null) {
            System.out.println("Order rejected: Supplement not found.");
            return false;
        } else if (quantity <= 0) {
            System.out.println("Order rejected: Invalid quantity.");
            return false;
        }
        for (CartItem c : item) {
            if (c.supplement.equals(s)) {
                if (c.quantity + quantity > s.getAvailableUnits()) {
                    System.out.println("Order rejected: Insufficient stock available.");
                    return false;
                }
                c.quantity = c.quantity + quantity;
                System.out.println(s.getSupplementName() + " quantity merged in cart. New quantity: " + c.quantity);
                return true;
            }
        }
        if (quantity > s.getAvailableUnits()) {
            System.out.println("Order rejected: Insufficient stock available.");
            return false;
        }
        item.add(new CartItem(s, quantity));
        System.out.println(quantity + " x " + s.getSupplementName() + " added to cart.");
        return true;
    }

    void displayCart() {
        for (CartItem c : item) {
            System.out.println(c.supplement.getSupplementName() + " - Qty: " + c.quantity + " - Rs." + c.supplement.getPricePerUnit() + " per unit");
        }
    }

    int getTotalQuantity() {
        int total = 0;
        for (CartItem c : item) {
            total = total + c.quantity;
        }
        return total;
    }

    void printBill() {
        double totalSubtotal = 0;
        double totalDiscount = 0;
        double totalFinal = 0;
        for (CartItem c : item) {
            System.out.println(c.supplement.getSupplementName());
            System.out.println("Quantity     : " + c.quantity + " units");
            System.out.printf("Subtotal     : Rs.%.2f%n", c.getSubtotal());
            System.out.printf("Discount     : %.0f%% = Rs.%.2f%n", c.supplement.getDiscountPercent(), c.getDiscountAmount());
            System.out.printf("Final Amount : Rs.%.2f%n", c.getFinalAmount());
            System.out.println();
            totalSubtotal = totalSubtotal + c.getSubtotal();
            totalDiscount = totalDiscount + c.getDiscountAmount();
            totalFinal = totalFinal + c.getFinalAmount();
        }
        System.out.println("Total Quantity : " + getTotalQuantity() + " units");
        System.out.printf("Total Subtotal : Rs.%.2f%n", totalSubtotal);
        System.out.printf("Total Discount : Rs.%.2f%n", totalDiscount);
        System.out.printf("Final Amount   : Rs.%.2f%n", totalFinal);
    }

    // Stock is reduced only here, after the order is confirmed
    void confirmOrder() {
        for (CartItem c : item) {
            Supplement s = c.supplement;
            s.setAvailableUnits(s.getAvailableUnits() - c.quantity);
        }
        System.out.println("Order confirmed. Stock updated.");
    }
}

class SupplementStore {
    List<Supplement> supplements = new ArrayList<>();

    void addSupplement(Supplement s) {
        supplements.add(s);
    }

    void displayAll() {
        for (Supplement s : supplements) {
            s.displayDetails();
            System.out.println();
        }
    }

    void displayStock() {
        for (Supplement s : supplements) {
            System.out.println(s.getSupplementName() + " : " + s.getAvailableUnits() + " units");
        }
    }

    int getTotalTypes() {
        return supplements.size();
    }

    int getTotalStock() {
        int total = 0;
        for (Supplement s : supplements) {
            total = total + s.getAvailableUnits();
        }
        return total;
    }

    double getTotalValue() {
        double total = 0;
        for (Supplement s : supplements) {
            total = total + s.getStockValue();
        }
        return total;
    }

    void displayStatistics() {
        System.out.println("Total supplement types : " + getTotalTypes());
        System.out.println("Total stock            : " + getTotalStock() + " units");
        System.out.printf("Total stock value      : Rs.%.2f%n", getTotalValue());
    }

    Supplement searchById(int id) {
        for (Supplement s : supplements) {
            if (s.getSupplementId() == id) {
                return s;
            }
        }
        return null;
    }

    Supplement searchByName(String name) {
        for (Supplement s : supplements) {
            if (s.getSupplementName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }
}
