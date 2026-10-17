# Java OOP — Complete Study Notes (2nd Year)

## 0. What is OOP?
- Programming style where everything is modelled as **objects** (data + behavior together).
- **4 pillars:** Encapsulation · Inheritance · Polymorphism · Abstraction.
- **Why:** code reuse, data security (hiding), easy maintenance, real-world modelling.
- **OOP vs POP:** POP (C) = functions + data separate, top-down. OOP (Java) = objects, bottom-up, data hiding.

---

## 1. Class & Object
- **Class** = blueprint. **Object** = instance made with `new`. Object has **state** (variables), **behavior** (methods), **identity** (address).
- Variables: **instance** (one per object) · **static** (one shared copy) · **local** (inside method).
- `this` = current object. Uses: `this.x = x` (name clash), `this()` (call own constructor), pass/return current object.
- Memory: reference variable → **stack**, object → **heap**. Unused objects removed by **Garbage Collector**.

```java
class Student {
    String name; int marks;                      // instance variables
    static String college = "ABC";               // shared by all objects
    void setData(String name, int marks) { this.name = name; this.marks = marks; }
    void show() { System.out.println(name + " " + marks + " " + college); }
}
Student s = new Student();      // s = reference, new Student() = object
s.setData("Ravi", 90); s.show();
```

- **Ways to create object:** `new` · `clone()` · Reflection (`Class.forName()`) · Deserialization.
- **Types of classes:** normal · abstract · final · nested/inner/anonymous · POJO (fields + getters/setters) · Singleton (only one object).

---

## 2. Constructor
- Same name as class, **no return type**, auto-called by `new`, job = initialize object.
- Can be overloaded, can have access modifiers. **Cannot** be `static`/`final`/`abstract`. Not inherited.
- Types: **default** (compiler adds only if you write none) · **no-arg** · **parameterized** · **copy**.
- **Chaining:** `this(...)` → another constructor of same class. `super(...)` → parent constructor.
  Must be the **first statement**, only one per constructor. Write neither → compiler inserts `super()`.
- **Parent constructor always runs before child constructor.**

```java
class Car {
    String brand; int speed;
    Car()                { System.out.println("Default car"); }
    Car(String b)        { this(); brand = b; System.out.println("1 param"); }
    Car(String b, int s) { this(b); speed = s; System.out.println("2 param"); }
}
class ElectricCar extends Car {
    int battery;
    ElectricCar(String b, int s, int bt) { super(b, s); battery = bt; System.out.println("EC"); }
}
new ElectricCar("Tesla", 0, 98);
// Output: Default car → 1 param → 2 param → EC   (whole parent chain finishes first)
```

- ⚠️ **Mistake in your Car.txt:** `ElectricCar(...)` calls `super()` (no-arg), so `brand`, `model`, `speed` stay `null`/`0`. Use `super(brand, model, speed)`.
- Full order when `new` runs: **static block** (once, when class loads) → **instance block** → **constructor**; parent's before child's.

---

## 3. Encapsulation
- Data + methods in one unit, data **hidden**: fields `private`, access only via `public` **getters/setters**.
- Benefits: validation, read-only fields (getter only), security, internals can change safely.

```java
class Account {
    private double balance;                                          // hidden
    public double getBalance() { return balance; }                   // getter
    public void deposit(double amt) { if (amt > 0) balance += amt; } // setter + validation
}
```

| Modifier | Same class | Same package | Subclass (other pkg) | Everywhere |
|---|---|---|---|---|
| `private` | ✔ | ✘ | ✘ | ✘ |
| default (none) | ✔ | ✔ | ✘ | ✘ |
| `protected` | ✔ | ✔ | ✔ | ✘ |
| `public` | ✔ | ✔ | ✔ | ✔ |

---

## 4. static & final
- **static** = belongs to class, not object; one copy; access as `ClassName.member`.
  - static variable → counters/common data · static method → can't use `this`/non-static members (`main` is static so JVM runs it without object) · static block → runs once at class load.
- **final** variable = constant (assign once, e.g. `MAX`) · final method = can't be overridden · final class = can't be extended (e.g. `String`).
- `static final double PI = 3.14;` → class-level constant.

```java
class Counter { static int count = 0; Counter() { count++; } }
new Counter(); new Counter();
System.out.println(Counter.count);   // 2
```

---

## 5. Inheritance
- Child (**subclass**) gets parent's (**superclass**) fields + methods using `extends`. **IS-A** relation, code reuse. Every class is a child of `Object`.
- Types: **single** (A→B) · **multilevel** (A→B→C) · **hierarchical** (A→B, A→C) · **hybrid** (mix, via interfaces).
  **Multiple inheritance of classes ✘** (ambiguity/diamond problem) → done with interfaces.
- `super` uses: `super.var` · `super.method()` (parent's version) · `super(...)` (parent constructor).
- Not inherited: constructors, `private` members (exist in object, reachable through parent's methods).
- **HAS-A** (composition/aggregation): class holds an object of another class (`Car has Engine`).

```java
class Animal { String type = "Animal"; void eat() { System.out.println("eating"); } }
class Dog extends Animal {
    String type = "Dog";
    void show() { System.out.println(type + " " + super.type); }   // Dog Animal
}
Dog d = new Dog(); d.eat(); d.show();
```

---

## 6. Polymorphism
"Many forms" — same name, different behavior.

| | Compile-time (Static) | Runtime (Dynamic) |
|---|---|---|
| Achieved by | **Method Overloading** | **Method Overriding** |
| Where | Same class | Parent & child |
| Rule | Same name, **different params** (count/type/order); return type alone ✘ | Same name & params; child gives new body |
| Decided by | Compiler (reference type) | JVM (actual object type) |

```java
class Calc {                                   // overloading
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }
}
class Animal { void sound() { System.out.println("..."); } }
class Cat extends Animal { @Override void sound() { System.out.println("Meow"); } }
Animal a = new Cat();          // UPCASTING: parent reference, child object (automatic)
a.sound();                     // Meow → dynamic method dispatch
Cat c = (Cat) a;               // DOWNCASTING: explicit, needed for Cat-only methods
if (a instanceof Cat) { }      // check first → avoids ClassCastException
```

- **Overriding rules:** same signature, same/child return type, access not more restrictive, can't override `final`/`static`/`private`, can't throw bigger checked exception. `@Override` makes compiler check.
- `static` methods are **hidden** not overridden; **variables are never overridden** (decided by reference type).

---

## 7. Abstraction
- Show **what** it does, hide **how**. Done with **abstract class** and **interface**.

| | Abstract class | Interface |
|---|---|---|
| Keyword | `abstract class` + `extends` | `interface` + `implements` |
| Methods | abstract + normal | abstract (public by default); `default` & `static` with body (Java 8), `private` (Java 9) |
| Variables | any | `public static final` only |
| Constructor | yes (runs via child's `super()`) | no |
| Multiple inheritance | no | yes |
| Abstraction | 0–100% | 100% |
| Object | ✘ `new Shape()` | ✘ `new Drawable()` |

```java
abstract class Shape {
    abstract double area();                              // child MUST implement
    void show() { System.out.println("Area: " + area()); }
}
interface Drawable { void draw(); }                       // auto public abstract
class Circle extends Shape implements Drawable {
    double r = 2;
    double area() { return 3.14 * r * r; }
    public void draw() { System.out.println("Drawing"); }   // must be public
}
```

- Interface can `extends` many interfaces; class = `extends` one class + `implements` many interfaces.
- **Functional interface** = exactly one abstract method (`Runnable`, `Comparable`) → lambda: `Runnable r = () -> System.out.println("run");`
- **Marker interface** = empty (`Serializable`, `Cloneable`).

---

## 8. Object class (parent of all)
- `toString()` → string form (override to print object nicely) · `equals(Object)` → content compare (`==` compares references) · `hashCode()` · `getClass()` · `clone()` · `finalize()` (GC calls before deleting).

```java
@Override public String toString() { return name + " " + marks; }   // System.out.println(s) uses it
```

---

## 9. Packages
- Folder-like group of related classes → no name clashes, access control, reuse.
- First line: `package shop.orders;` · Import: `import java.util.Scanner;` or `import java.util.*;` · `java.lang` (String, Math, System) is auto-imported.
- Built-in: `java.lang`, `java.util` (Scanner, ArrayList), `java.io` (files), `java.awt`/`javax.swing` (GUI), `java.net`, `java.sql`.
- Compile/run: `javac -d . Test.java` → `java shop.orders.Test`.

---

## 10. Inner (Nested) classes
- Class inside a class. **Static nested** (`new Outer.Inner()`) · **Inner** (needs outer object: `out.new Inner()`) · **Local** (inside a method) · **Anonymous** (no name, one-time use — common for interfaces/threads).

```java
interface Greet { void hi(); }
Greet g = new Greet() { public void hi() { System.out.println("Hi"); } };   // anonymous class
```

---

## 11. Exception Handling
- Exception = runtime problem that breaks normal flow. `Throwable` → **Error** (StackOverflowError, OutOfMemoryError – don't handle) & **Exception**.
- **Checked** (compiler forces handling): IOException, SQLException, ClassNotFoundException.
  **Unchecked** (`RuntimeException`): ArithmeticException, NullPointerException, ArrayIndexOutOfBoundsException, NumberFormatException, ClassCastException.
- `try` (risky code) · `catch` (handle) · `finally` (always runs – close files) · `throw` (throw one exception object) · `throws` (declare, pass to caller).
- Multiple catch: specific first, `Exception` last. Custom exception = `class X extends Exception`.

```java
try { int x = 10 / 0; }
catch (ArithmeticException e) { System.out.println("Error: " + e.getMessage()); }
finally { System.out.println("always runs"); }

class AgeException extends Exception { AgeException(String m) { super(m); } }
static void check(int age) throws AgeException {
    if (age < 18) throw new AgeException("Not eligible");
}
```

---

## 12. Multithreading (short)
- Thread = independent path of execution, many run together. Create by `extends Thread` or `implements Runnable` (preferred – class can still extend another).
- `start()` → new thread runs `run()`. Calling `run()` directly = normal method call (no new thread).
- Lifecycle: New → Runnable → Running → Blocked/Waiting (`sleep`, `wait`, `join`) → Terminated.
- `synchronized` → one thread at a time on a method/block (prevents data corruption). `sleep(ms)`, `join()` (wait to finish), `setPriority()`.

```java
class Task implements Runnable { public void run() { System.out.println("running"); } }
new Thread(new Task()).start();
```

---

## 13. Other must-knows (1 line each)
- **String** immutable & final (change = new object). `StringBuilder` mutable & fast; `StringBuffer` = synchronized. `equals()` content, `==` reference.
- **Wrapper classes:** `int→Integer`, `char→Character`… Autoboxing `Integer x = 5;` unboxing `int y = x;` `Integer.parseInt("12")`.
- **Collections (java.util):** `ArrayList` (dynamic array) · `LinkedList` · `HashSet` (no duplicates) · `HashMap` (key→value). `List<Integer> l = new ArrayList<>();`
- **Generics:** type-safe: `class Box<T> { T value; }` → `Box<Integer> b`.
- **Garbage collection:** JVM auto-frees unreferenced objects (`obj = null`), `System.gc()` only requests.
- **Input:** `Scanner sc = new Scanner(System.in); int n = sc.nextInt();`

---

## 14. Differences asked in exams
| Pair | Difference |
|---|---|
| Class vs Object | blueprint vs real instance in memory |
| Method vs Constructor | any name, has return type, called manually vs class name, no return type, auto-called by `new` |
| `this` vs `super` | current object / own constructor vs parent members / parent constructor |
| Overloading vs Overriding | same class, different params, compile-time vs child class, same signature, runtime |
| Abstract class vs Interface | partial abstraction, has constructor, single vs full abstraction, no constructor, multiple |
| `static` vs instance | class-level, one copy, `Class.x` vs per object, `obj.x` |
| `throw` vs `throws` | throws one exception inside method vs declares exceptions in signature |
| `final` vs `finally` vs `finalize` | keyword (constant/no override/no extend) vs block after try-catch vs method called by GC |
| `==` vs `equals()` | compares references vs compares content |
| Checked vs Unchecked | compiler forces handling (IOException) vs found at runtime (NullPointerException) |
| `start()` vs `run()` | creates new thread vs plain method call |

---

## 15. Exam pattern: Inheritance + Constructor Overloading (Q1 from your file, solved)
**Steps:** ① parent: fields → overloaded constructors (small one chains to big with `this(...)`) → methods. ② child `extends`: extra fields → every constructor starts with `super(...)`, fill defaults → extra methods reuse parent methods. ③ `main`: objects with different constructors, call methods in the asked order, print exact labels.

```java
class Product {
    String productName; double price; int stock;
    Product(String productName, double price) { this(productName, price, 10); }   // default stock
    Product(String productName, double price, int stock) {
        this.productName = productName; this.price = price; this.stock = stock;
    }
    void showProduct() { System.out.println("Product Name: " + productName + "\nPrice: " + price + "\nStock: " + stock); }
    double calculateTotal(int q)  { return price * q; }
    boolean checkStock(int q)     { return q <= stock; }
    void updateStock(int q)       { stock -= q; }
}

class Electronics extends Product {
    String brand; int warrantyYears; double discountPercentage;
    Electronics(String n, double p, String b)                         { super(n, p);    brand = b; warrantyYears = 1; discountPercentage = 5; }
    Electronics(String n, double p, int s, String b, int w)           { super(n, p, s); brand = b; warrantyYears = w; discountPercentage = 5; }
    Electronics(String n, double p, int s, String b, int w, double d) { super(n, p, s); brand = b; warrantyYears = w; discountPercentage = d; }

    void showElectronicsDetails() {
        showProduct();                                                  // inherited method
        System.out.println("Brand: " + brand + "\nWarranty: " + warrantyYears + " years\nDiscount: " + discountPercentage + "%");
    }
    double calculateDiscount(int q)    { return calculateTotal(q) * discountPercentage / 100; }
    double calculateFinalAmount(int q) { return calculateTotal(q) - calculateDiscount(q); }
    void purchase(int q) {
        if (!checkStock(q)) { System.out.println("Insufficient stock"); return; }
        System.out.println("\nQuantity Purchased: " + q + "\nTotal Amount: " + calculateTotal(q)
            + "\nDiscount Amount: " + calculateDiscount(q) + "\nFinal Amount: " + calculateFinalAmount(q));
        updateStock(q);
        System.out.println("Remaining Stock: " + stock + "\n");
    }
}

public class Test {
    public static void main(String[] args) {
        Electronics e1 = new Electronics("Smartphone", 30000, "Samsung");        e1.showElectronicsDetails(); e1.purchase(2);
        Electronics e2 = new Electronics("Laptop", 65000, 5, "Dell", 2);          e2.showElectronicsDetails(); e2.purchase(1);
        Electronics e3 = new Electronics("Television", 80000, 4, "Sony", 3, 10);  e3.showElectronicsDetails(); e3.purchase(2);
    }
}
```
Q2 (Order → PremiumOrder) follows the exact same steps: `super(orderId, customerName[, orderAmount])`, defaults `discountPercentage = 5`, `deliveryCharge = 100`, and `addPurchase()` calls `applyFreeDelivery()` after adding.

---

## 4 Pillars in one line
**Encapsulation** → hide data · **Inheritance** → reuse code · **Polymorphism** → one name, many forms · **Abstraction** → hide implementation
