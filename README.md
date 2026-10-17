# Java OOP — Understand It, Then Write It

This is not theory prep. It is a working guide built from everything Deepa mam gave us:
**notebooks 1–15** in `raw/`, **ShopZone Level 1 and Level 2** in `senario/`, the **hospital packages** project, and the **constructor / encapsulation** practice folders.

The goal: read any OOP question, know which Java tool it is asking for, and write the code without getting stuck.

**How this file is laid out**
1. [Read the question → pick the code](#1-read-the-question--pick-the-code) — the translation table
2. [Output patterns](#2-output-patterns--expected-output-says-x-write-y) — "the expected output looks like X, so write Y"
3. [Concepts one by one](#3-concepts-one-by-one) — idea, code, interview questions
4. [How a scenario question is solved](#4-how-a-scenario-question-is-solved-shopzone) — ShopZone Level 1 and 2
5. [Every notebook mapped](#5-every-notebook-mapped)
6. [Common mistakes](#6-mistakes-that-cost-marks)
7. [The big ✅ / ❌ tables](#7-the-big--tables) — what Java allows and what it rejects

---

## 1. Read the question → pick the code

Questions never say "use `final`". They describe a **rule in plain English**. This table translates.

| The question says… | Write this | Seen in |
|---|---|---|
| "must never change after creation", "once assigned", "unique ID" | `final int id;` set in the constructor | NB 1, 2, 3, 6, 10, 13, L1 |
| "common to all", "stored only once", "shared", "company/hospital/bank name" | `static String hospitalName = "...";` | NB 2, 5, 6, 10, 13 |
| "accessible without creating an object", "call using the class name" | `static void displayName() { }` | NB 1, 2, 3, 5, 6, 13 |
| "count how many objects were created" | `static int count;` and `count++` inside the constructor | NB 1, 3, 13 |
| "confidential", "not directly accessible from outside" | `private` field + a public method to read/change it | NB 5, 6, 8, 10, 13, 15 |
| "accessible within the class, subclasses and same package" | `protected` | NB 6, 8, 10, 13 |
| "accessible only within the same package" | no keyword (default) | NB 6, 8, 10, 13 |
| "accessible from anywhere" | `public` | NB 8 |
| "should not be created directly", "only specific types should be created", "base class only" | `abstract class` | NB 1, 3, 6, 10, 13, L1 |
| "every type must provide its own…", "declare it but don't implement it" | `abstract double calculateBill();` | NB 1, 3, 6, 10, 13 |
| "child classes must not change this procedure", "same for all" | `final void checkIn() { }` | NB 1, 3, 13 |
| "no class should inherit from it", "must not be extended" | `final class HotelRules { }` | NB 1, 2, 3, 5, 13 |
| "X is a type of Y", "supports Laptop, Milk, Shirt…" | `class Laptop extends ElectronicProduct` | NB 1, 3, 4, 6, L1, L2 |
| "the same method should behave differently for each type" | override the method with `@Override` | NB 1, 3, 6, 13, L1, L2 |
| "the same operation with different sets of input" | method overloading | NB 11, L1 |
| "create objects in different ways", "if stock is not given, use 10" | constructor overloading + `this(...)` | NB 4, 12 |
| "use super(...) to initialise inherited members" | `super(...)` as the first line of the child constructor | NB 4, 12 |
| "a contract", "must provide these operations", "can do X" (Bluetooth, Payment) | `interface` + `implements` | NB 14, 15, L1 |
| "one reference, different objects", "store all products together" | `Product p = new Laptop();` / `List<Product>` | NB 1, 3, 7, L1, L2 |
| "display in a readable format" | override `toString()` | L2 |
| "two objects with the same ID are the same product" | override `equals(Object obj)` | L2 |
| "create a separate copy" | `implements Cloneable` + override `clone()` | L2 |
| "maintain a list", "add to cart", "store all products" | `List<Product> list = new ArrayList<>();` | L1, L2 |
| "search by name, ignore uppercase/lowercase" | `name.equalsIgnoreCase(input)` | L2 |
| "if already in the cart, increase the quantity" | loop the list, `if (item.equals(p))` then add quantity | L2 |
| "organise into packages", "import the class" | `package hospital.patient;` + `import hospital.patient.Patient;` | hospital |

---

## 2. Output patterns — "expected output says X, write Y"

Most marks are lost on output that is *almost* right. Match the expected output character by character.

### Pattern A — one value per line with labels
```
Patient ID: 101
Patient Name: Rahul
```
Write a `display()` method with one `println` per field:
```java
void displayPatient() {
    System.out.println("Patient ID: " + patientId);
    System.out.println("Patient Name: " + patientName);
}
```

### Pattern B — everything on one line, separated by ` - `
```
101 - Laptop - Rs.60000.0 - Qty: 1 - Warranty: 24 months
```
Override `toString()` and **return** the line (do not print inside it):
```java
@Override
public String toString() {
    return productId + " - " + name + " - Rs." + price + " - Qty: " + quantity + " - " + getExtraInfo();
}
```
Now `System.out.println(product);` prints exactly that.

### Pattern C — one part of the line changes per type
`Warranty: 24 months` for electronics, `Expiry: 31-12-2026` for grocery, `Size: 9` for clothing.
Put the common part in the parent's `toString()` and ask each child for its own piece:
```java
abstract String getExtraInfo();          // in Product

@Override
String getExtraInfo() {                  // in GroceryProduct
    return "Expiry: " + expiryDate;
}
```

### Pattern D — numbers: `.0` vs `.00` vs no decimals
| Expected | Write |
|---|---|
| `Rs.60000.0` | `double` printed normally: `"Rs." + price` |
| `Rs.71600.00` | `System.out.printf("Rs.%.2f%n", total);` |
| `Rs.60000` | `int`, or `(int) price` |
| `Total Amount = 540.0` | method returns `double` |

### Pattern E — headers and blank lines
```
===== PATIENT DETAILS =====
...

===== DOCTOR DETAILS =====
```
Headers are printed from `main()`, and a blank line is just `System.out.println();`.

### Pattern F — the same call, different results (polymorphism)
```
Laptop Discount: Rs.6500.0
Milk Discount: Rs.1.36
```
One parent reference, each child decides:
```java
Product current = laptop;
System.out.println("Laptop Discount: Rs." + current.calculateDiscount());
current = milk;
System.out.println("Milk Discount: Rs." + current.calculateDiscount());
```

### Pattern G — messages after an action
```
Laptop added to cart.
Bluetooth Headphones quantity merged in cart.
```
Print the message **inside the method that does the action** (`addProduct`), not in `main`.

### Pattern H — a total at the end
```
Number of products: 4
Total product value: Rs.71600.00
```
Loop the list and add:
```java
double total = 0;
for (Product p : products) {
    total = total + p.getTotalValue();
}
System.out.println("Number of products: " + products.size());
System.out.printf("Total product value: Rs.%.2f%n", total);
```

### Pattern I — "before and after" (state changes)
```
Before Deposit:
Balance: 5000.0
After Deposit:
Balance: 7000.0
```
Call `display()` → call the method that changes the field → call `display()` again (NB 8).

### Pattern J — the original stays, the copy changes
```
Original : ... Qty: 1
Copy     : ... Qty: 3
```
`clone()` the object, change only the copy, print both (L2 section 13).

---

## 3. Concepts one by one

### 3.1 Class, object, fields, methods
- **Class** = blueprint (fields + methods). **Object** = one real thing made from it with `new`.
- Fields describe the object (`name`, `price`); methods are what it can do (`buy()`, `restock()`).
```java
class Book {
    String name;
    double price;
    int copies;

    void restock(int noOfCopies) {
        copies = copies + noOfCopies;
    }
}

Book b1 = new Book();
b1.name = "Let Us C";
b1.restock(1000);
```
**Interview:** *Where do objects live?* Heap. The variable `b1` holds a reference to it.

### 3.2 Constructors
- Same name as the class, **no return type**, runs automatically on `new`.
- If you write none, Java gives a default one. If you write **any**, the default one is gone.
```java
class Patient {
    int patientId;
    String patientName;

    Patient(int patientId, String patientName) {
        this.patientId = patientId;       // this.field = parameter
        this.patientName = patientName;
    }
}
```
- **Overloading** — several constructors, different parameters. Reuse with `this(...)`:
```java
Product(String name, double price) {
    this(name, price, 10);                // stock not given → 10
}

Product(String name, double price, int stock) {
    this.name = name;
    this.price = price;
    this.stock = stock;
}
```
- `this(...)` and `super(...)` must be the **first line**, so you can use only one of them.

**Interview:** *Can a constructor be private?* Yes (used in singletons). *Can it be final, static or abstract?* No.

### 3.3 Instance initializer and static blocks
- `{ }` inside a class (no name) = **instance block**, runs for **every object**, before the constructor body.
- `static { }` = **static block**, runs **once** when the class is loaded.

Real output of a parent/child example:
```
1. Parent static block
2. Child static block
3. Parent instance block
4. Parent constructor
5. Child instance block
6. Child constructor
--- second object ---
3. Parent instance block
4. Parent constructor
5. Child instance block
6. Child constructor
```
**Remember:** static blocks once (parent first) → then for every object: parent block, parent constructor, child block, child constructor.

### 3.4 `this` keyword
- Means "the current object".
- `this.name = name` — separates the field from the parameter. Without it, the field stays `null`/`0`.
- `this(...)` — calls another constructor of the same class.
- Cannot be used inside a `static` method.

### 3.5 Access modifiers
| Modifier | Same class | Same package | Child in another package | Anywhere |
|---|---|---|---|---|
| `private` | ✅ | ❌ | ❌ | ❌ |
| default (nothing) | ✅ | ✅ | ❌ | ❌ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| `public` | ✅ | ✅ | ✅ | ✅ |

Order from tight to open: **private → default → protected → public**.

**The packages trap (hospital project):** a method with no modifier cannot be called from a class in another package. `displayPatient()` had to become `public` before `HospitalApp` could use it.

### 3.6 Encapsulation
- Hide the data (`private`), allow controlled access through methods.
- The method can **check** before changing — that is the whole point.
```java
class BankATM {
    private double balance = 10000;

    public void withdrawMoney(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance");
        } else {
            balance = balance - amount;
            System.out.println("Withdrawn: Rs." + amount);
        }
    }

    public double getBalance() {
        return balance;
    }
}
```
**Interview:** *Why not make the field public?* Then anyone can set `balance = -5000`; a method can refuse it.

### 3.7 `static`
- Belongs to the **class**, one copy shared by all objects.
- Static method: call with the class name, no object needed. It **cannot** use instance fields directly or `this`.
```java
abstract class Room {
    static int bookingCount;

    Room() {
        bookingCount++;
    }

    static int getBookingCount() {
        return bookingCount;
    }
}

System.out.println("Total Bookings: " + Room.getBookingCount());
```
**Interview:** *Why is `main` static?* So the JVM can call it without creating an object.

### 3.8 `final`
| On | Meaning | Example |
|---|---|---|
| variable | value set once, never changed | `final int roomNumber;` |
| method | child cannot override it | `final void checkIn()` |
| class | nobody can extend it | `final class HotelRules` |

A `final` field without a value ("blank final") **must** be set in every constructor.

### 3.9 Inheritance
- `class Child extends Parent` — the child gets the parent's non-private fields and methods.
- Java allows **one** parent class. Many interfaces are allowed.
- The child constructor calls the parent constructor first with `super(...)`.
```java
class InPatient extends Patient {
    int days;

    InPatient(int id, String record, int room, int days) {
        super(id, record, room);          // parent sets the common part
        this.days = days;                  // child sets its own part
    }
}
```
- `super.method()` calls the parent's version from inside the child.

**Types:** single (A→B), multilevel (A→B→C), hierarchical (A→B, A→C). Multiple (B and C → D) only through interfaces.

### 3.10 Overloading vs overriding
| | Overloading | Overriding |
|---|---|---|
| Where | same class | parent and child |
| Name | same | same |
| Parameters | **different** | **same** |
| Return type | can differ | same (or a child type) |
| Decided at | compile time | run time |
| Example | `addProduct(p)` and `addProduct(p, 2)` | `calculateBill()` in each room type |

```java
static double calculateTotal(double price) { return price; }
static double calculateTotal(double price, int qty) { return price * qty; }
static double calculateTotal(double price, int qty, double discount) {
    double total = price * qty;
    return total - (total * discount / 100);
}
```
**Rules:** changing only the return type is **not** overloading. An overriding method cannot be *less* visible than the parent's.

### 3.11 Abstract class
- `abstract class Product` — cannot do `new Product()`, but `Product p = new Laptop();` is fine.
- Can have normal methods, abstract methods, fields, constructors.
- Every concrete child must implement every abstract method.
```java
abstract class Patient {
    abstract double calculateBill();          // no body

    void displayPatientDetails() {            // normal method, uses the abstract one
        System.out.println("Bill: " + calculateBill());
    }
}
```
**Interview:** *Why does an abstract class have a constructor if you can't create it?* The child's `super(...)` uses it to set the common fields.

### 3.12 Interfaces
- A **contract**: "if you implement me, you must provide these methods" (NB 14).
- Methods are `public abstract` by default; fields are `public static final` (constants only).
- No constructor, no normal instance fields.
```java
interface ATM {
    void checkBalance();
    void withdrawMoney(double amount);
}

class BankATM implements ATM {
    private double balance = 10000;

    @Override
    public void checkBalance() {              // must be public
        System.out.println("Current balance: Rs." + balance);
    }

    @Override
    public void withdrawMoney(double amount) {
        balance = balance - amount;
    }
}
```
- One class, many interfaces: `class Speaker extends ElectronicProduct implements Bluetooth, Rechargeable`.
- Interface reference: `Payment p = new UPIPayment(); p.pay(500);`

| Abstract class | Interface |
|---|---|
| `extends` (only one) | `implements` (many) |
| can have instance fields | only constants |
| can have constructors | no constructors |
| any access modifier on members | methods are public |
| "is a" (Laptop is a Product) | "can do" (Speaker can do Bluetooth) |

### 3.13 Polymorphism
- **Parent reference, child object:** `Product p = new Laptop();`
- `p.calculateDiscount()` runs **Laptop's** version — decided at run time by the real object.
- Through `p` you can only call methods the **parent** declares. For child-only methods, cast:
```java
Product headphones = new ElectronicProduct(...);
ElectronicProduct hp = (ElectronicProduct) headphones;
hp.showBluetooth();
```
- Casting to the wrong type compiles but crashes with `ClassCastException`. Check first with `if (p instanceof ElectronicProduct)`.

### 3.14 The `Object` class: `toString()`, `equals()`, `clone()`
Every class secretly `extends Object`, so every object already has these. Override them to make them useful.

| Method | Default behaviour | After you override |
|---|---|---|
| `toString()` | `Product@1b6d3586` | `101 - Laptop - Rs.60000.0` |
| `equals(Object)` | same as `==` (same object in memory) | your rule, e.g. same product ID |
| `clone()` | not allowed unless `Cloneable` | a separate copy |

```java
class Product implements Cloneable {
    int productId;
    int quantity;

    @Override
    public boolean equals(Object obj) {       // parameter MUST be Object
        Product p = (Product) obj;
        return this.productId == p.productId;
    }

    @Override
    public Product clone() throws CloneNotSupportedException {
        return (Product) super.clone();
    }
}
```
- `==` asks "same object?". `equals()` asks "same value?".
- `clone()` is a **shallow** copy: numbers and Strings are copied, objects inside (like `Manufacturer`) are shared.

### 3.15 Lists (`ArrayList`)
```java
List<Product> cart = new ArrayList<Product>();
cart.add(p);                 // add
cart.size();                 // how many
cart.isEmpty();              // empty?
for (Product item : cart) {  // visit each one
    System.out.println(item);
}
System.out.println(cart);    // prints [a, b, c] using toString()
```
**Find by ID:**
```java
Product findProduct(int id) {
    for (Product p : products) {
        if (p.productId == id) {
            return p;
        }
    }
    System.out.println("Product " + id + " not found.");
    return null;
}
```
**Merge repeated items:**
```java
void addProduct(Product p) {
    for (Product item : cart) {
        if (item.equals(p)) {
            item.quantity = item.quantity + p.quantity;
            System.out.println(p.name + " quantity merged in cart.");
            return;
        }
    }
    cart.add(p);
    System.out.println(p.name + " added to cart.");
}
```

### 3.16 Packages and imports
- `package hospital.billing;` must be the first statement and must match the folder path.
- Import a **class**, not a folder: `import hospital.billing.Bill;`
- Anything used from another package must be `public`: the class, the constructor and the methods.

---

## 4. How a scenario question is solved (ShopZone)

Scenario questions are long, but they always break into the same five steps.

**Step 1 — find the nouns.** Product, Manufacturer, Laptop, Milk, Shirt, ShoppingCart → these become classes.

**Step 2 — find "is a" vs "has a".**
- Laptop **is a** Product → `extends`.
- Product **has a** Manufacturer → a field: `Manufacturer manufacturer;`
- Cart **has many** Products → `List<Product> cart`.
- Speaker **can do** Bluetooth → `interface Bluetooth`.

**Step 3 — put common data in the parent, special data in the child.**

| Level 1 class | Fields it adds | Rules |
|---|---|---|
| `Product` (abstract) | id (final), name, brand, price, quantity | abstract discount/tax/delivery/return methods |
| `ElectronicProduct` | manufacturer, warranty, power | Laptop 10%/18%/₹500/15 days … |
| `GroceryProduct` | mfg date, expiry, weight | implements `Expirable` |
| `ClothingProduct` | size, material, gender | Shirt 20%, Jeans 15% |
| `Medicine` | dosage, expiry | implements `Expirable` |
| `BluetoothSpeaker` | — | implements `Bluetooth` |
| `ShoppingCart` | `List` of items | overloaded `addProduct`, `calculateTotal`, `checkout` |

| Level 2 class | What it adds |
|---|---|
| `Manufacturer` | name, country — a **has-a** relationship |
| `Product` | `toString()`, `equals()` by ID, `clone()`, `getTotalValue()` |
| `ShopZone` | product list: add, display, find by ID, search by name (ignore case), statistics |
| `ShoppingCart` | merges repeated products, generates the bill |

**Step 4 — billing is always the same formula.**
```
amount   = price × quantity
discount = amount × discount% / 100
tax      = (amount − discount) × tax% / 100      (Level 1)
final    = amount − discount + tax + delivery
```
Level 2 check: Headphones 3000 × 3 = 9000, 10% = 900, payable **8100**.

**Step 5 — `main()` follows the question's numbered list, line by line.** Print each header exactly as shown.

---

## 5. Every notebook mapped

| File | Scenario | Main concepts |
|---|---|---|
| `notebook.java` (NB 1) | SecureBank accounts | abstract class/method, final var/method/class, static counter, overriding, polymorphism |
| `notebook01.java` | Book store, car show, student | class, object, fields, methods, constructor |
| `notebook2.java` | Course management | final, static, abstract + final child |
| `notebook3.java` | AutoTest Labs vehicles | same design as NB 1, FlyingCar extension |
| `notebook4.java` | Product + Electronics | constructor overloading, `this(...)`, `super(...)`, no overriding |
| `notebook5.java` | ABC Bank | abstract method, static name, final class, private + getter |
| `notebook6.java` / `7` / `9` | Hospital patients | all four access modifiers, abstract bill |
| `notebook8.java` | Bank account | public / private / protected / default, before–after output |
| `notebook10.java` | Courier packages | modifiers + fixing a broken attempt |
| `notebook11.java` | Food delivery | method overloading |
| `notebook12.java` | Order + PremiumOrder | constructor overloading, free-delivery logic |
| `notebook13.java` | GrandStay hotel | the full pattern: abstract, final, static, private, protected, default |
| `notebook14.java` | Payment | interface, `implements`, interface reference |
| `notebook15.java` | ATM | interface + private balance |
| `senario/question.txt` | ShopZone Level 1 | inheritance tree, interfaces, overloading, cart, billing, arrays/search |
| `senario/question2.txt` + `Test.java` | ShopZone Level 2 | has-a, toString/equals/clone, List, search, merge, copy |
| `hospital/` | Hospital packages | `package`, `import`, `public` across packages |
| `constructor/Trace.java` | Device / Order | initialization order, constructor chaining |
| `encapsulation/` | SBI, calculator | packages and access modifiers |

---

## 6. Mistakes that cost marks

| Mistake | What happens | Fix |
|---|---|---|
| `@Override` with no method under it | red "enum" syntax error | write the method right below it |
| `abstract calculateBill() { ... }` | compile error | abstract methods have no body; children write the body |
| method without a return type | "return type required" | `double calculateBill()` |
| `print` instead of `println` | two messages on one line | `println` |
| printing a value you never saved: `"..." + (balance - 2000)` | balance never changes | `balance = balance - amount;` then print |
| interface method not `public` in the class | "weaker access" error | `public void checkBalance()` |
| interface with fields like `balance` | they are constants, can't change | put changing data in the class |
| `equals(Product p)` instead of `equals(Object obj)` | overloads instead of overriding; lists ignore it | `equals(Object obj)` |
| `clone()` without `implements Cloneable` | crashes at run time | add `implements Cloneable` |
| `p.name == "Laptop"` | often false | `p.name.equalsIgnoreCase("Laptop")` |
| discount on price, not price × quantity | wrong bill | `price * quantity * percent / 100` |
| `this.name = name` forgotten | fields print `null` / `0` | always use `this.` when names match |
| `new Product()` on an abstract class | "cannot be instantiated" | create a child: `new Laptop(...)` |
| child calls parent constructor that doesn't exist | "implicit super constructor is undefined" | call `super(...)` with the right values |
| `import hospital.patient;` | import error | `import hospital.patient.Patient;` |
| method with no modifier used from another package | "not visible" | make it `public` |
| `public class X` in a file not named `X.java` | compile error | rename the file or the class |
| two classes with the same name in one folder | "type already defined" | run each file alone with `java File.java` |

---

## 7. The big ✅ / ❌ tables

### Table 1 — Keywords: what can go where

| Combination | Allowed? | Why |
|---|---|---|
| `abstract class` with zero abstract methods | ✅ | still can't be created directly |
| `abstract class` with a constructor | ✅ | children call it with `super(...)` |
| `abstract class` with `final` methods | ✅ | children inherit but can't change them |
| `abstract class` with `static` methods | ✅ | called with the class name |
| `new` on an abstract class | ❌ | it's incomplete |
| abstract class as a **reference** type | ✅ | `Product p = new Laptop();` |
| `abstract final class` | ❌ | abstract needs a child, final forbids one |
| `abstract static` method | ❌ | static can't be overridden |
| `abstract private` method | ❌ | private can't be seen by the child to implement |
| `abstract` method with a body `{ }` | ❌ | abstract means no body |
| class with an abstract method but not marked abstract | ❌ | the class must be `abstract` |
| child not implementing all abstract methods | ❌ | unless the child is also abstract |
| overriding a `final` method | ❌ | final = locked |
| overloading a `final` method | ✅ | different parameters = a new method |
| extending a `final` class | ❌ | final class = no children |
| creating objects of a `final` class | ✅ | only inheritance is blocked |
| `final` variable assigned twice | ❌ | one assignment only |
| `final` field set in the constructor (blank final) | ✅ | that is the normal way |
| `final` field never set anywhere | ❌ | must be assigned once |
| changing the inside of a `final` object reference | ✅ | the reference is fixed, the object isn't |
| `final` local variable | ✅ | constant inside the method |
| `static` local variable | ❌ | static only at class level |
| access modifier on a local variable | ❌ | locals have no access modifiers |
| static method using an instance field directly | ❌ | no object to read it from |
| instance method using a static field | ✅ | static is shared by everyone |
| `this` or `super` inside a static method | ❌ | there is no current object |
| calling a static method with the class name | ✅ | `Room.getBookingCount()` |
| calling a static method with an object | ✅ | works, but class name is the correct style |
| "overriding" a static method | ❌ | it hides, it doesn't override |
| overloading `main` | ✅ | JVM still starts from `main(String[] args)` |

### Table 2 — Constructors, `this`, `super`

| Situation | Allowed? | Why |
|---|---|---|
| constructor with a return type (even `void`) | ❌ | then it becomes a normal method |
| `private` constructor | ✅ | blocks `new` from outside (singleton) |
| `final` / `static` / `abstract` constructor | ❌ | none of these make sense for constructors |
| constructors are inherited by the child | ❌ | the child calls them with `super(...)` |
| overriding a constructor | ❌ | not inherited, so can't be overridden |
| constructor overloading | ✅ | different parameter lists |
| `this(...)` as the first line | ✅ | constructor chaining |
| `super(...)` as the first line | ✅ | calls the parent constructor |
| `this(...)` or `super(...)` not on the first line | ❌ | must be first |
| both `this(...)` and `super(...)` in one constructor | ❌ | only one can be first |
| using the default constructor after writing your own | ❌ | Java stops providing it |
| parent has only a parameterized constructor, child doesn't call `super(...)` | ❌ | Java tries `super()` and fails |
| instance block `{ }` in a class | ✅ | runs for every object before the constructor body |
| `static { }` block | ✅ | runs once when the class loads |

### Table 3 — Access modifiers

| Situation | Allowed? | Why |
|---|---|---|
| top-level `public class` | ✅ | file name must match |
| top-level default class | ✅ | visible in its package |
| top-level `private` or `protected` class | ❌ | only inner classes can be |
| two `public` classes in one file | ❌ | only one public top-level class per file |
| using a `private` field from another class | ❌ | private = this class only |
| child using the parent's `private` field directly | ❌ | not visible; use a method |
| child (other package) using a `protected` field | ✅ | protected reaches children |
| child (other package) using a default field | ❌ | default = same package only |
| same-package class using a default field | ✅ | |
| overriding with **wider** access (protected → public) | ✅ | |
| overriding with **narrower** access (public → private) | ❌ | can't reduce visibility |
| `private` method overridden by the child | ❌ | the child just makes a new method |

### Table 4 — Inheritance and interfaces

| Situation | Allowed? | Why |
|---|---|---|
| `class A extends B` | ✅ | single inheritance |
| `class A extends B, C` | ❌ | one parent class only |
| `class A implements X, Y` | ✅ | many interfaces |
| `class A extends B implements X, Y` | ✅ | `extends` must come first |
| `interface X extends Y, Z` | ✅ | interfaces can extend many interfaces |
| `interface X implements Y` | ❌ | interfaces `extend` other interfaces |
| `new` on an interface | ❌ | it's only a contract |
| interface as a reference type | ✅ | `Payment p = new UPIPayment();` |
| interface with a constructor | ❌ | nothing to construct |
| interface field that changes | ❌ | fields are `public static final` |
| interface method with a body | ✅ | only as `default` or `static` (Java 8+) |
| implementing class leaves an interface method out | ❌ | unless the class is abstract |
| implementing an interface method without `public` | ❌ | interface methods are public |
| `protected` member inside an interface | ❌ | public (or private helpers in Java 9+) |
| `@Override` on an interface method in the class | ✅ | recommended |
| a class can be both a child and implement interfaces | ✅ | `BluetoothSpeaker extends ElectronicProduct implements Bluetooth` |

### Table 5 — Overloading, overriding, polymorphism

| Situation | Allowed? | Why |
|---|---|---|
| overloading with different parameter count/types | ✅ | |
| overloading by return type only | ❌ | the call would be ambiguous |
| overriding with the same name and parameters | ✅ | |
| overriding with a different return type | ❌ | must match (a child class type is allowed) |
| `@Override` on a method the parent doesn't have | ❌ | "must override a supertype method" |
| `@Override` with no method below it | ❌ | syntax error |
| parent reference holding a child object | ✅ | `Product p = new Laptop();` |
| child reference holding a parent object | ❌ | `Laptop l = new Product();` won't compile |
| calling a child-only method through a parent reference | ❌ | cast first |
| casting to the correct child type | ✅ | `(ElectronicProduct) headphones` |
| casting to the wrong child type | ❌ | compiles, crashes with `ClassCastException` |
| overridden method chosen at run time | ✅ | the real object decides |
| overloaded method chosen at compile time | ✅ | the parameters decide |

### Table 6 — `Object` methods, Strings and lists

| Situation | Allowed / correct? | Why |
|---|---|---|
| `toString()` that **returns** a String | ✅ | `println(obj)` uses it |
| `toString()` that prints instead of returning | ❌ | must return a String |
| `toString()` without `public` | ❌ | `Object`'s version is public |
| `equals(Object obj)` | ✅ | true override |
| `equals(Product p)` | ❌ | overload, not override |
| `==` to compare Strings | ❌ | compares memory, not text |
| `equals()` / `equalsIgnoreCase()` for Strings | ✅ | compares text |
| `clone()` with `implements Cloneable` | ✅ | |
| `clone()` without `Cloneable` | ❌ | `CloneNotSupportedException` |
| changing the clone's quantity changes the original | ❌ | separate objects |
| clone shares inner objects (like `Manufacturer`) | ✅ | shallow copy |
| `List<Product> list = new ArrayList<Product>();` | ✅ | |
| `new List<Product>()` | ❌ | `List` is an interface |
| adding a Laptop to a `List<Product>` | ✅ | a Laptop is a Product |
| `for (Product p : list)` | ✅ | for-each loop |
| `System.out.println(list)` | ✅ | prints `[...]` using each `toString()` |

### Table 7 — Concepts covered, notebook by notebook

| Concept | NB1 | 01 | 2 | 3 | 4 | 5 | 6/7/9 | 8 | 10 | 11 | 12 | 13 | 14 | 15 | L1 | L2 | hospital |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| Class & object | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| Constructor | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ❌ | ✅ | ✅ | ❌ | ❌ | ✅ | ✅ | ✅ |
| Constructor overloading / `this()` | ❌ | ❌ | ❌ | ❌ | ✅ | ❌ | ❌ | ❌ | ❌ | ❌ | ✅ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ |
| `super(...)` | ✅ | ❌ | ✅ | ✅ | ✅ | ✅ | ✅ | ❌ | ✅ | ❌ | ✅ | ✅ | ❌ | ❌ | ✅ | ✅ | ❌ |
| Inheritance | ✅ | ❌ | ✅ | ✅ | ✅ | ✅ | ✅ | ❌ | ✅ | ❌ | ✅ | ✅ | ❌ | ❌ | ✅ | ✅ | ❌ |
| Abstract class / method | ✅ | ❌ | ✅ | ✅ | ❌ | ✅ | ✅ | ❌ | ✅ | ❌ | ❌ | ✅ | ❌ | ❌ | ✅ | ✅ | ❌ |
| Overriding | ✅ | ❌ | ✅ | ✅ | ❌ | ✅ | ✅ | ❌ | ✅ | ❌ | ❌ | ✅ | ✅ | ✅ | ✅ | ✅ | ❌ |
| Overloading | ❌ | ❌ | ❌ | ❌ | ✅ | ❌ | ❌ | ❌ | ❌ | ✅ | ✅ | ❌ | ❌ | ❌ | ✅ | ❌ | ❌ |
| `static` var / method | ✅ | ❌ | ✅ | ✅ | ❌ | ✅ | ✅ | ❌ | ✅ | ✅ | ❌ | ✅ | ❌ | ❌ | ❌ | ❌ | ❌ |
| `final` var / method / class | ✅ | ❌ | ✅ | ✅ | ❌ | ✅ | ✅ | ❌ | ✅ | ❌ | ❌ | ✅ | ❌ | ❌ | ✅ | ✅ | ❌ |
| Access modifiers | ✅ | ❌ | ❌ | ❌ | ❌ | ✅ | ✅ | ✅ | ✅ | ❌ | ❌ | ✅ | ❌ | ✅ | ❌ | ❌ | ✅ |
| Encapsulation (private + methods) | ❌ | ❌ | ❌ | ❌ | ❌ | ✅ | ✅ | ✅ | ✅ | ❌ | ❌ | ✅ | ❌ | ✅ | ❌ | ❌ | ❌ |
| Interface / `implements` | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ✅ | ✅ | ✅ | ❌ | ❌ |
| Polymorphism (parent reference) | ✅ | ❌ | ❌ | ✅ | ❌ | ❌ | ✅ | ❌ | ✅ | ❌ | ❌ | ✅ | ✅ | ❌ | ✅ | ✅ | ❌ |
| `toString` / `equals` / `clone` | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ✅ | ❌ |
| `List` / `ArrayList` | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ✅ | ✅ | ❌ |
| Has-a (object inside object) | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ✅ | ✅ | ❌ |
| Packages & `import` | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ✅ |

**How to use Table 7:** a ❌ row with few ✅ is a weak spot. Right now the thinnest are **interfaces**, **`toString/equals/clone`**, **lists** and **packages** — practise those first.
