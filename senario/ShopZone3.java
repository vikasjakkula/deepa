/*
JAVA OOP LAB - FitFuel Health Supplement Store  (full question in question3.txt)

Classes:
Billable (interface)  -> calculatePayable(quantity)
Supplement (abstract) -> 5 private fields, getters, setter, equals, toString, clone
ProteinPowder 10%, Vitamin 5%, EnergyBar 8%, HerbalTea 15%
CartItem        -> HAS-A Supplement + ordered quantity
OrderCart       -> HAS-A List<CartItem>  (validate, merge, bill, confirm)
SupplementStore -> HAS-A List<Supplement> (display, statistics, search)

Expected numbers:
Initial  : 4 types, 350 units, Rs.135000.00
Bill     : Subtotal 9200.00, Discount 895.00, Final 8305.00
Updated  : 330 units, Rs.125800.00
Copy     : original Whey 37, copy 100
*/

import java.util.List;
import java.util.ArrayList;

// Class name must match file name ShopZone3.java -> run with: java ShopZone3
class ShopZone3 {
    // clone() can throw CloneNotSupportedException, so main must declare it
    public static void main(String[] args) throws CloneNotSupportedException {
        // Part 4: parent reference, child object (upcasting)
        // Constructor args must match exactly: 5 parent values + 2 child values, same order and types
        Supplement wheyProtein = new ProteinPowder(901, "Whey Protein", 2000, 40, "MuscleBlaze", "Chocolate", 1.0);
        Supplement multivitamin = new Vitamin(902, "Multivitamin", 500, 60, "HealthKart", "Vitamin A-Z", 60);
        Supplement oatsEnergyBar = new EnergyBar(903, "Oats Energy Bar", 50, 200, "Yoga Bar", 180, true);
        Supplement greenTea = new HerbalTea(904, "Green Tea", 300, 50, "Organic India", "Tulsi", false);

        // Part 5: store all objects in one collection
        SupplementStore store = new SupplementStore();
        store.addSupplement(wheyProtein);
        store.addSupplement(multivitamin);
        store.addSupplement(oatsEnergyBar);
        store.addSupplement(greenTea);

        System.out.println("----- SUPPLEMENT DETAILS -----");
        store.displayAll();

        System.out.println("----- INVENTORY STATISTICS -----");
        store.displayStatistics();

        // Part 6: search returns a Supplement or null, main decides what to print
        System.out.println();
        System.out.println("----- SEARCH -----");
        System.out.println("Search ID 902:");
        showSearchResult(store.searchById(902));
        System.out.println("Search name green tea:");
        showSearchResult(store.searchByName("green tea"));
        System.out.println("Search ID 999:");
        showSearchResult(store.searchById(999));
        System.out.println("Search name Protein Bar:");
        showSearchResult(store.searchByName("Protein Bar"));

        // Part 6: equals() compares IDs, not memory addresses
        System.out.println();
        System.out.println("----- COMPARE -----");
        Supplement found = store.searchById(901);
        System.out.println("Whey Protein and Multivitamin same? " + wheyProtein.equals(multivitamin));
        System.out.println("Whey Protein and search result 901 same? " + wheyProtein.equals(found));

        // Parts 7, 8, 9, 13: second Whey order (1 unit) merges into the first (2 units) -> 3
        System.out.println();
        System.out.println("----- ADDING TO CART -----");
        OrderCart cart = new OrderCart();
        cart.addToCart(wheyProtein, 2);
        cart.addToCart(multivitamin, 3);
        cart.addToCart(oatsEnergyBar, 10);
        cart.addToCart(greenTea, 4);
        cart.addToCart(wheyProtein, 1);

        // Parts 11, 12: same call s.calculatePayable(2), child's discount decides the answer at runtime
        System.out.println();
        System.out.println("----- RUNTIME POLYMORPHISM -----");
        for (Supplement s : store.supplements) {
            System.out.printf("%s payable for 2 units = Rs.%.2f%n", s.getSupplementName(), s.calculatePayable(2));
        }

        // Part 14: void method -> call it alone, never inside println()
        System.out.println();
        System.out.println("----- ORDER CART -----");
        cart.displayCart();
        System.out.println("Total Quantity: " + cart.getTotalQuantity() + " units");

        // Parts 10, 15
        System.out.println();
        System.out.println("----- BILL -----");
        cart.printBill();

        // Part 16: stock changes only now, not in addToCart()
        System.out.println();
        System.out.println("----- CONFIRM ORDER -----");
        cart.confirmOrder();
        System.out.println();
        System.out.println("----- UPDATED STOCK -----");
        store.displayStock();
        store.displayStatistics();

        // Part 17: every invalid order is rejected, cart and stock stay the same
        System.out.println();
        System.out.println("----- INVALID ORDERS -----");
        OrderCart newCart = new OrderCart();
        System.out.println("Order 40 units of Whey Protein:");
        newCart.addToCart(wheyProtein, 40);
        System.out.println("Order 0 units of Multivitamin:");
        newCart.addToCart(multivitamin, 0);
        System.out.println("Order -5 units of Green Tea:");
        newCart.addToCart(greenTea, -5);
        System.out.println("Order 2 units of ID 999:");
        newCart.addToCart(store.searchById(999), 2);
        System.out.println("Order 2 units of Protein Bar:");
        newCart.addToCart(store.searchByName("Protein Bar"), 2);
        System.out.println("Whey Protein stock after invalid orders: " + wheyProtein.getAvailableUnits() + " units");
        System.out.println("Items in cart after invalid orders: " + newCart.item.size());

        // Part 18: parent reference, but each child's own getCategoryInfo() runs
        System.out.println();
        System.out.println("----- SUPPLEMENT-SPECIFIC ATTRIBUTES -----");
        for (Supplement s : store.supplements) {
            System.out.println(s.getSupplementName() + " : " + s.getCategoryInfo());
        }

        // Part 19: clone() returns Object, so cast it back to Supplement
        System.out.println();
        System.out.println("----- COPYING SUPPLEMENT -----");
        Supplement wheyCopy = (Supplement) wheyProtein.clone();
        System.out.println("Original Whey Protein stock: " + wheyProtein.getAvailableUnits() + " units");
        System.out.println("Copied Whey Protein stock initially: " + wheyCopy.getAvailableUnits() + " units");
        wheyCopy.setAvailableUnits(100);
        System.out.println("Modified copied Whey Protein stock: " + wheyCopy.getAvailableUnits() + " units");
        System.out.println("Original Whey Protein stock after change: " + wheyProtein.getAvailableUnits() + " units");
        System.out.println("Same supplement? " + wheyProtein.equals(wheyCopy));
        System.out.println("Same object? " + (wheyProtein == wheyCopy));
        // copy was never added to store, so totals are still 330 units
        System.out.println("Total stock in store: " + store.getTotalStock() + " units");
    }

    // static -> can be called directly from main without creating an object
    static void showSearchResult(Supplement s) {
        if (s == null) {
            System.out.println("Supplement not found.");
        } else {
            System.out.println(s);
        }
    }
}

// Part 11: interface methods are public by default, so the class method must also be public
interface Billable {
    double calculatePayable(int quantity);
}

// abstract -> no one can create a plain "Supplement", only its children
abstract class Supplement implements Billable, Cloneable {
    // private -> children cannot touch these directly, they use getters (do NOT copy these fields into children)
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

    // only stock has a setter, the question says "update the available units"
    void setAvailableUnits(int units) {
        this.availableUnits = units;
    }

    // abstract methods -> every child MUST override these, else red mark on the child class name
    abstract double getDiscountPercent();

    abstract String getCategoryInfo();

    // normal methods -> child overrides only if it wants different behaviour
    // formula is written once here, each child only gives its own discount %
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

    // Part 6: same ID = same supplement
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Supplement)) return false;
        Supplement other = (Supplement) obj;
        return this.supplementId == other.supplementId;
    }

    // equal objects must give the same hashCode
    @Override
    public int hashCode() {
        return Integer.hashCode(supplementId);
    }

    // without this, println(s) prints ProteinPowder@1b6d3586
    @Override
    public String toString() {
        return supplementId + " - " + supplementName + " - Rs." + pricePerUnit + " - Stock: " + availableUnits + " - " + brandName;
    }

    // Part 19: shallow copy is enough, fields are primitives and Strings
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class ProteinPowder extends Supplement {
    String flavour;
    double weightInKg;

    ProteinPowder(int supplementId, String supplementName, double pricePerUnit, int availableUnits, String brandName, String flavour, double weightInKg) {
        // super(...) must be the first line, parent part is built first
        super(supplementId, supplementName, pricePerUnit, availableUnits, brandName);
        this.flavour = flavour;
        this.weightInKg = weightInKg;
    }

    @Override
    double getDiscountPercent() {
        return 10;
    }

    @Override
    String getCategoryInfo() {
        return "Flavour " + flavour + ", Weight " + weightInKg + " kg";
    }

    // super.displayDetails() prints the 5 common fields, then the child adds its own 2
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
    String getCategoryInfo() {
        return "Vitamin Type " + vitaminType + ", " + tabletsPerBottle + " tablets per bottle";
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
    String getCategoryInfo() {
        return calories + " calories, Gluten Free " + glutenFree;
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
    String getCategoryInfo() {
        return "Herb " + herbName + ", Caffeine Free " + caffeineFree;
    }

    @Override
    void displayDetails() {
        super.displayDetails();
        System.out.println("Herb Name       : " + herbName);
        System.out.println("Caffeine Free   : " + caffeineFree);
    }
}

// Supplement stock (40) and ordered quantity (3) are different numbers, so they live in different classes
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

    // polymorphism: the child's discount is used here
    double getFinalAmount() {
        return supplement.calculatePayable(quantity);
    }

    double getDiscountAmount() {
        return getSubtotal() - getFinalAmount();
    }
}

class OrderCart {
    List<CartItem> item = new ArrayList<>();

    // Part 8 order: not found -> bad quantity -> not enough stock -> merge or add
    // every reject does return false BEFORE touching the cart
    boolean addToCart(Supplement s, int quantity) {
        if (s == null) {
            System.out.println("Order rejected: Supplement not found.");
            return false;
        } else if (quantity <= 0) {
            System.out.println("Order rejected: Invalid quantity.");
            return false;
        }
        // Part 9: already in cart -> increase quantity, no new entry
        for (CartItem c : item) {
            if (c.supplement.equals(s)) {
                // check old cart qty + new qty, not only the new qty
                if (c.quantity + quantity > s.getAvailableUnits()) {
                    System.out.println("Order rejected: Insufficient stock available.");
                    return false;
                }
                c.quantity = c.quantity + quantity;
                System.out.println(s.getSupplementName() + " quantity merged in cart. New quantity: " + c.quantity);
                return true;
            }
        }
        // reached only if not found in the loop -> new entry
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

    // total starts at 0 before the loop, add inside, return after
    int getTotalQuantity() {
        int total = 0;
        for (CartItem c : item) {
            total = total + c.quantity;
        }
        return total;
    }

    // Part 10: Subtotal = price x qty, Discount = subtotal x % / 100, Final = subtotal - discount
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

    // Part 16: fields are private, so stock is changed through the setter
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

    // s.displayDetails() -> the child's overridden version runs
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

    // returns a value -> return type int, not void
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

    // money -> double, not int
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

    // return inside if when found; "return null" only AFTER the loop
    // (no else inside the loop, or it stops at the first item)
    Supplement searchById(int id) {
        for (Supplement s : supplements) {
            if (s.getSupplementId() == id) {
                return s;
            }
        }
        return null;
    }

    // equalsIgnoreCase -> "green tea" matches "Green Tea"
    Supplement searchByName(String name) {
        for (Supplement s : supplements) {
            if (s.getSupplementName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }
}
