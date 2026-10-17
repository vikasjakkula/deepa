# OOP using Java (PC303CS) — Unit I & II Final Notes

Osmania University, BE II year CSE, syllabus w.e.f. 2025-26. CIE 30 + SEE 70.

**Sources:**
- `OOPJ Syllabus one page.jpg` (official syllabus)
- `OOPJ-unit1.pdf` (faculty Unit I notes)
- `UNIT-2_Java-notes.pdf` (faculty Unit II notes, for interfaces and packages)
- the old notebook-based notes (kept only where useful)

Exception handling and multithreading are not in the faculty PDF; that PDF follows an older syllabus. Those two topics come from the standard textbook (Herbert Schildt, *The Complete Reference Java*), which the syllabus lists as reading 1.

**Every program in this file was compiled and run on Java 25. The outputs shown are real.**

**How to read this file**
1. **Part A** first: the three expected long questions, with full 2-page answers.
2. **Part B**: Unit I. **Part C**: Unit II.
3. **Part D**: tips for program-writing questions and common compile errors.
4. **Part E**: two-mark answers and the last-day revision sheet.

**Topics removed from Unit I by your faculty (not covered here):** Applet, arrays, garbage collection, nested and inner classes, exploring the String class, command-line arguments.
**Streams and file I/O** are in the Unit II PDF, but the 2025-26 syllabus moves them to Unit III, so they are not covered here.

---

## Contents

- [Part A — Expected Long Questions](#part-a--expected-long-questions)
  - [Q1. How is abstraction achieved in Java (5 ways)](#q1-how-is-abstraction-achieved-in-java-explain-all-the-ways-with-examples)
  - [Q2. Multiple inheritance using interfaces and its advantages](#q2-multiple-inheritance-using-interfaces--advantages-with-examples)
  - [Q3. Object class: toString(), equals(), clone()](#q3-object-class--tostring-equals-and-clone)
- [Part B — Unit I](#part-b--unit-i)
- [Part C — Unit II](#part-c--unit-ii)
- [Part D — Program Questions and Compile Errors](#part-d--program-questions-and-compile-errors)
- [Part E — Two-Mark Answers and Last-Day Revision](#part-e--two-mark-answers-and-last-day-revision)

---

# Part A — Expected Long Questions

> Each answer below is written to fill about 4 sides (2 pages). In the exam, write it in this order: definition, explanation, program, output, table, advantages, conclusion.

---

## Q1. How is abstraction achieved in Java? Explain all the ways with examples.

### Definition
**Abstraction** means *showing only the essential features of an object and hiding the internal details of how it works.*
The user knows **what** an object does, but not **how** it does it.

### Real-life examples
- **Car:** we use the steering, brake and accelerator. We do not need to know how the engine, gearbox or brake system works inside.
- **ATM:** we enter the PIN and amount and get cash. The bank's internal processing is hidden.
- **TV remote:** we press a button to change the channel. The circuit inside is hidden.

### Why abstraction is needed
- It reduces complexity: the user deals only with what is needed.
- It improves security: internal data and logic are hidden.
- The internal code can be changed without affecting the user's code.
- It increases reusability and makes programs easy to maintain.

### Two kinds of abstraction
| Kind | Meaning | Achieved by |
|---|---|---|
| **Data abstraction** | Hide the data, give access only through methods | private variables, packages |
| **Process (control) abstraction** | Hide how a task is done, show only what is done | abstract classes, interfaces, methods |

### The 5 ways to achieve abstraction in Java

| # | Way | Level of abstraction |
|---|---|---|
| 1 | Abstract classes and abstract methods | Partial (0 to 100%) |
| 2 | Interfaces | Full (100%) |
| 3 | Encapsulation with access modifiers (data hiding) | Hides data |
| 4 | Packages | Hides classes |
| 5 | Methods (procedural abstraction) | Hides the logic of a task |

---

### Way 1: Abstract class and abstract method
- An **abstract class** is declared with the `abstract` keyword. **Objects cannot be created** from it.
- An **abstract method** has **no body**. It only says *what* to do, and each child class writes *how*.
- An abstract class can also have normal methods, constructors and variables. That is why it gives **partial abstraction**.
- A child class must override every abstract method, or it must also be declared abstract.

```java
abstract class Vehicle {
    String name;

    Vehicle(String name) {
        this.name = name;
    }

    abstract void start();              // WHAT to do (no body)

    void fuelUp() {                     // normal method
        System.out.println(name + " is filling fuel");
    }
}

class Car extends Vehicle {
    Car(String name) {
        super(name);
    }

    void start() {                      // HOW to do it
        System.out.println(name + " starts with a key");
    }
}

class Bike extends Vehicle {
    Bike(String name) {
        super(name);
    }

    void start() {
        System.out.println(name + " starts with a kick");
    }
}

class AbstractDemo {
    public static void main(String[] args) {
        Vehicle v1 = new Car("Swift");
        Vehicle v2 = new Bike("Pulsar");
        v1.start();
        v1.fuelUp();
        v2.start();
        // Vehicle v3 = new Vehicle("X");  // ERROR: Vehicle is abstract; cannot be instantiated
    }
}
```
**Output**
```text
Swift starts with a key
Swift is filling fuel
Pulsar starts with a kick
```
**How this hides details:** `main` only knows that every Vehicle can `start()`. How a car or bike starts is hidden inside the child classes.

---

### Way 2: Interface (100% abstraction)
- An **interface** is a collection of **abstract methods** and **constants**. It is declared with the `interface` keyword.
- Its methods are `public abstract` by default. Its variables are `public static final` by default.
- A class uses `implements` and must give a body for **every** method.
- The interface fixes **what** must be done. Each class decides **how**. This is **full abstraction**.

```java
interface RemoteControl {
    void powerOn();
    void changeChannel(int number);
}

class SonyTV implements RemoteControl {
    public void powerOn() {
        System.out.println("Sony TV is ON");
    }

    public void changeChannel(int number) {
        System.out.println("Sony TV moved to channel " + number);
    }
}

class SamsungTV implements RemoteControl {
    public void powerOn() {
        System.out.println("Samsung TV is ON");
    }

    public void changeChannel(int number) {
        System.out.println("Samsung TV moved to channel " + number);
    }
}

class InterfaceDemo {
    public static void main(String[] args) {
        RemoteControl r = new SonyTV();
        r.powerOn();
        r.changeChannel(5);

        r = new SamsungTV();
        r.powerOn();
        r.changeChannel(9);
    }
}
```
**Output**
```text
Sony TV is ON
Sony TV moved to channel 5
Samsung TV is ON
Samsung TV moved to channel 9
```
**How this hides details:** the user works with the `RemoteControl` reference only. Which TV is inside, and how it works, is hidden.

---

### Way 3: Encapsulation with access modifiers (data hiding)
- Make the variables **`private`**, so no one outside the class can read or change them directly.
- Give **`public` methods** to use the data safely, and check (validate) the values inside them.
- The user sees only the methods (`withdraw`, `getBalance`). The data and the checks are hidden.

```java
class BankAccount {
    private double balance;            // hidden data
    private int pin;                   // hidden data

    BankAccount(double balance, int pin) {
        this.balance = balance;
        this.pin = pin;
    }

    public void withdraw(double amount, int enteredPin) {
        if (enteredPin != pin) {
            System.out.println("Wrong PIN");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance");
            return;
        }
        balance = balance - amount;
        System.out.println("Withdrawn: " + amount);
    }

    public double getBalance() {
        return balance;
    }
}

class EncapsulationDemo {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount(5000, 1234);
        acc.withdraw(2000, 1234);
        acc.withdraw(500, 1111);
        System.out.println("Balance: " + acc.getBalance());
        // acc.balance = 1000000;   // ERROR: balance has private access in BankAccount
    }
}
```
**Output**
```text
Withdrawn: 2000.0
Wrong PIN
Balance: 3000.0
```

---

### Way 4: Packages
- A **package** groups related classes. Classes and members with **default access** (no keyword) **cannot be seen outside the package**.
- So helper classes can be hidden inside a package. Only the `public` class is given to the user.

```java
// File: shapes/Circle.java
package shapes;

public class Circle {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public void showArea() {
        double area = 3.14 * Helper.square(radius);
        System.out.println("Area of circle = " + area);
    }
}

class Helper {                          // default class: hidden outside the package
    static double square(double x) {
        return x * x;
    }
}
```
```java
// File: Main.java (outside the package)
import shapes.Circle;

class Main {
    public static void main(String[] args) {
        Circle c = new Circle(2);
        c.showArea();
        // Helper.square(2);  // ERROR: Helper is not public in shapes; cannot be accessed from outside package
    }
}
```
Compile and run: `javac -d . shapes/Circle.java Main.java`, then `java Main`.

**Output**
```text
Area of circle = 12.56
```
**How this hides details:** the user of `shapes` sees only `Circle`. The `Helper` class is hidden.

---

### Way 5: Methods (procedural abstraction)
- A **method** gives a name to a task. The caller only knows the **method name, its parameters and the return type**. The steps inside are hidden.
- Java's library is full of this: we call `Math.sqrt(49)` or `System.out.println()` without knowing their code.

```java
class Calculator {
    // The user only knows: give n, get n!
    static long factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);    // hidden logic (recursion)
    }
}

class MethodDemo {
    public static void main(String[] args) {
        System.out.println("Factorial of 5 = " + Calculator.factorial(5));
        System.out.println("Square root of 49 = " + Math.sqrt(49));
    }
}
```
**Output**
```text
Factorial of 5 = 120
Square root of 49 = 7.0
```

---

### Comparison: abstract class vs interface
| Point | Abstract class | Interface |
|---|---|---|
| Keyword | `abstract class` | `interface` |
| Abstraction | Partial (0–100%) | Full (100%) |
| Methods | Abstract and normal | Abstract (plus `default` and `static` since Java 8) |
| Variables | Any kind | Only `public static final` (constants) |
| Constructor | Allowed | Not allowed |
| Inheritance | `extends`, only one class | `implements`, many interfaces |
| Use when | Related classes share common code | Unrelated classes must follow the same rule |

### Advantages of abstraction
1. **Simplicity:** the user deals only with the essential features.
2. **Security:** important data and logic stay hidden.
3. **Easy maintenance:** the internal code can change without changing the user's code.
4. **Reusability:** one abstract class or interface works for many classes.
5. **Loose coupling:** code depends on *what* is done, not on *how*.
6. **Supports polymorphism:** a parent or interface reference can hold any child object.

### Conclusion
Abstraction shows **what** an object does and hides **how** it does it. Java achieves it in five ways:
- **abstract classes** (partial),
- **interfaces** (full),
- **encapsulation** with `private` data,
- **packages**, which hide classes,
- **methods**, which hide the steps of a task.

Together these make Java programs simple, secure and easy to change.

---

## Q2. Multiple Inheritance using Interfaces — Advantages with Examples

### Definition
**Multiple inheritance** means one class gets the properties and behaviour of **more than one parent**.

```text
   A       B
    \     /
     \   /
       C          C inherits from both A and B
```

### Why Java does NOT allow multiple inheritance with classes
- It is the **diamond problem (ambiguity problem)**.
- Suppose classes `A` and `B` both have a method `show()` with different bodies, and `C extends A, B`.
- When we call `c.show()`, Java cannot decide whose `show()` to run, A's or B's.
- To avoid this confusion, Java does not allow a class to extend two classes.

```java
class A {
    void show() { System.out.println("A"); }
}
class B {
    void show() { System.out.println("B"); }
}
// class C extends A, B { }   // ERROR: '{' expected (a class can extend only one class)
```

### How interfaces solve it
- An interface method has **no body** (it is abstract). It only says what to do.
- Even if two interfaces have the same method, the class writes **only one body**, so there is **no ambiguity**.
- So Java allows **a class to implement any number of interfaces**. This is how Java supports multiple inheritance.

### Syntax forms
```java
class C implements A, B { }                    // one class, many interfaces
class C extends P implements A, B { }          // one class + many interfaces
interface C extends A, B { }                   // interface extends many interfaces
```

---

### Example 1: a class implementing two interfaces
```java
interface Printable {
    void print();
}

interface Showable {
    void show();
}

class Document implements Printable, Showable {
    public void print() {
        System.out.println("Printing the document");
    }

    public void show() {
        System.out.println("Showing the document on screen");
    }
}

class MultipleDemo1 {
    public static void main(String[] args) {
        Document d = new Document();
        d.print();
        d.show();
    }
}
```
**Output**
```text
Printing the document
Showing the document on screen
```

### Example 2: real-life SmartPhone (one class, three interfaces)
```java
interface Phone {
    void call(String number);
}

interface Camera {
    void takePhoto();
}

interface MusicPlayer {
    void playMusic(String song);
}

class SmartPhone implements Phone, Camera, MusicPlayer {
    public void call(String number) {
        System.out.println("Calling " + number);
    }

    public void takePhoto() {
        System.out.println("Photo captured");
    }

    public void playMusic(String song) {
        System.out.println("Playing " + song);
    }
}

class MultipleDemo2 {
    public static void main(String[] args) {
        SmartPhone s = new SmartPhone();
        s.call("9876543210");
        s.takePhoto();
        s.playMusic("Naatu Naatu");

        Camera c = s;                  // the same object seen as a Camera
        c.takePhoto();
    }
}
```
**Output**
```text
Calling 9876543210
Photo captured
Playing Naatu Naatu
Photo captured
```

### Example 3: the same method in both interfaces (no diamond problem)
```java
interface Father {
    void habit();
}

interface Mother {
    void habit();
}

class Child implements Father, Mother {
    public void habit() {              // ONE body satisfies both interfaces
        System.out.println("Child has its own habit");
    }
}

class MultipleDemo3 {
    public static void main(String[] args) {
        Child c = new Child();
        c.habit();
        Father f = c;
        f.habit();
        Mother m = c;
        m.habit();
    }
}
```
**Output**
```text
Child has its own habit
Child has its own habit
Child has its own habit
```
There is only one body, so there is no confusion about which method runs.

### Example 4: extends one class AND implements two interfaces
```java
class Person {
    String name;

    Person(String name) {
        this.name = name;
    }

    void showName() {
        System.out.println("Name: " + name);
    }
}

interface Exam {
    int examMarks();
}

interface Sports {
    int sportsMarks();
}

class Student extends Person implements Exam, Sports {
    Student(String name) {
        super(name);
    }

    public int examMarks() {
        return 80;
    }

    public int sportsMarks() {
        return 15;
    }

    void showTotal() {
        showName();
        System.out.println("Exam marks: " + examMarks());
        System.out.println("Sports marks: " + sportsMarks());
        System.out.println("Total: " + (examMarks() + sportsMarks()));
    }
}

class MultipleDemo4 {
    public static void main(String[] args) {
        Student s = new Student("Vikas");
        s.showTotal();
    }
}
```
**Output**
```text
Name: Vikas
Exam marks: 80
Sports marks: 15
Total: 95
```

### Example 5: an interface extending two interfaces
```java
interface Walk {
    void walk();
}

interface Swim {
    void swim();
}

interface Amphibian extends Walk, Swim {     // interface multiple inheritance
    void breathe();
}

class Frog implements Amphibian {
    public void walk() {
        System.out.println("Frog walks on land");
    }

    public void swim() {
        System.out.println("Frog swims in water");
    }

    public void breathe() {
        System.out.println("Frog breathes through skin and lungs");
    }
}

class MultipleDemo5 {
    public static void main(String[] args) {
        Frog f = new Frog();
        f.walk();
        f.swim();
        f.breathe();
    }
}
```
**Output**
```text
Frog walks on land
Frog swims in water
Frog breathes through skin and lungs
```

### Extra point: default methods with the same name (Java 8+)
If two interfaces have a **default** method with the same name, the class **must override it**. Inside, it can choose a parent's version with `InterfaceName.super.method()`.
```java
interface X {
    default void hello() { System.out.println("Hello from X"); }
}
interface Y {
    default void hello() { System.out.println("Hello from Y"); }
}
class Z implements X, Y {
    public void hello() {
        X.super.hello();               // the programmer decides, so no ambiguity
        System.out.println("Hello from Z");
    }
}
```
Calling `new Z().hello()` prints `Hello from X` and then `Hello from Z`.

### Advantages of multiple inheritance using interfaces
1. **Multiple inheritance without the diamond problem:** there is only one method body, so there is no ambiguity.
2. **One class, many roles:** a SmartPhone is a Phone, a Camera and a MusicPlayer at the same time.
3. **Full abstraction:** interfaces give only *what* to do. The class decides *how*.
4. **Loose coupling:** code can use an interface reference (`Camera c = s;`) without knowing the actual class.
5. **Runtime polymorphism:** one interface reference can point to objects of different classes.
6. **A class can extend one class and still implement many interfaces**, so it gets both code reuse and multiple types.
7. **Standard contracts:** different vendors and programmers can implement the same interface in their own way, as with JDBC drivers.
8. **Easy to extend:** a new class can implement the interface without changing old code.
9. **Shared constants:** variables in interfaces are `public static final`, so all implementing classes can use them.

### Class inheritance vs interface inheritance
| Point | Using classes | Using interfaces |
|---|---|---|
| Keyword | `extends` | `implements` |
| Multiple parents | Not allowed | Allowed |
| Method bodies in parent | Yes, which causes ambiguity | No, only abstract (so no ambiguity) |
| Diamond problem | Occurs | Does not occur |

### Conclusion
Java does not allow multiple inheritance with classes because of the diamond problem. It supports multiple inheritance through **interfaces**: a class can `implement` many interfaces, and an interface can `extend` many interfaces. Interfaces only declare methods, so the class writes one body and there is no ambiguity. This gives flexibility, reusability, polymorphism and loose coupling.

---

## Q3. Object Class — toString(), equals() and clone()

### The Object class
- `Object` is in the `java.lang` package. It is the **root (super) class of every class in Java**.
- If a class does not extend any class, Java automatically makes it extend `Object`. So `class Student { }` really means `class Student extends Object { }`.
- So every object in Java gets the methods of `Object`.

### Methods of the Object class
| Method | Purpose |
|---|---|
| `public String toString()` | Returns the object as a String |
| `public boolean equals(Object obj)` | Checks whether two objects are equal |
| `public int hashCode()` | Returns a number (hash code) for the object |
| `protected Object clone()` | Creates and returns a copy of the object |
| `public final Class getClass()` | Returns the runtime class of the object |
| `protected void finalize()` | Called before garbage collection (deprecated) |
| `wait()`, `notify()`, `notifyAll()` | Used for inter-thread communication |

---

### 1. toString()
- It returns a **String that represents the object**.
- Java calls it **automatically** when we print an object with `System.out.println(obj)` or join it with a String.
- **Default version** (from Object) returns `ClassName@hashcodeInHex`, for example `Student@1b6d3586`. This is not useful to the user.
- **Override it** to return meaningful data.

**Without overriding**
```java
class Student {
    int rollNo;
    String name;

    Student(int rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }
}

class ToStringDemo1 {
    public static void main(String[] args) {
        Student s = new Student(101, "Ravi");
        System.out.println(s);
    }
}
```
**Output** (the number after `@` changes on every run)
```text
Student@1b6d3586
```

**With overriding**
```java
class Student {
    int rollNo;
    String name;

    Student(int rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Roll No: " + rollNo + ", Name: " + name;
    }
}

class ToStringDemo2 {
    public static void main(String[] args) {
        Student s1 = new Student(101, "Ravi");
        Student s2 = new Student(102, "Sita");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println("Topper -> " + s2);
    }
}
```
**Output**
```text
Roll No: 101, Name: Ravi
Roll No: 102, Name: Sita
Topper -> Roll No: 102, Name: Sita
```

---

### 2. equals()
- `==` compares **references**: are both variables pointing to the **same object** in memory?
- `equals()` is meant to compare **content**: do both objects hold the **same data**?
- **Default `equals()`** in Object works exactly like `==`. So we **override** it to compare the fields.
- The `String` class already overrides `equals()`. That is why `"java".equals("java")` is true.
- **Rule:** when you override `equals()`, also override `hashCode()`, so that equal objects give the same hash code.

| `==` | `equals()` |
|---|---|
| Operator | Method |
| Compares addresses (references) | Compares content (after overriding) |
| Cannot be changed | Can be overridden |
| Works with primitives and objects | Works only with objects |

```java
class Student {
    int rollNo;
    String name;

    Student(int rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {                     // same object
            return true;
        }
        if (!(obj instanceof Student)) {        // null or a different class
            return false;
        }
        Student other = (Student) obj;          // downcast
        return rollNo == other.rollNo && name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return rollNo;
    }
}

class EqualsDemo {
    public static void main(String[] args) {
        Student s1 = new Student(101, "Ravi");
        Student s2 = new Student(101, "Ravi");
        Student s3 = new Student(102, "Sita");
        Student s4 = s1;

        System.out.println(s1 == s2);          // different objects
        System.out.println(s1.equals(s2));     // same data
        System.out.println(s1.equals(s3));     // different data
        System.out.println(s1 == s4);          // same object
    }
}
```
**Output**
```text
false
true
false
true
```

**Contract of equals()** (write the names in the exam)
- **Reflexive:** `a.equals(a)` is true.
- **Symmetric:** if `a.equals(b)` then `b.equals(a)`.
- **Transitive:** if `a.equals(b)` and `b.equals(c)` then `a.equals(c)`.
- **Consistent:** the result does not change unless the data changes.
- **Null:** `a.equals(null)` is false.

---

### 3. clone()
- `clone()` creates an **exact copy** of an object: a new object with the same field values.
- The class **must implement the `Cloneable` interface**. Cloneable is a **marker interface**: it has no methods and just gives permission.
- If the class does not implement Cloneable, `clone()` throws **`CloneNotSupportedException`**.
- `clone()` is `protected` in Object, so we override it as `public` and call `super.clone()`.
- **Advantage:** we don't have to write code to copy each field by hand, and no constructor is called.

**Steps**
1. `class X implements Cloneable`
2. Override: `public X clone() throws CloneNotSupportedException { return (X) super.clone(); }`
3. Call it: `X copy = original.clone();` and handle or declare `CloneNotSupportedException`.

```java
class Student implements Cloneable {
    int rollNo;
    String name;

    Student(int rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }

    @Override
    public Student clone() throws CloneNotSupportedException {
        return (Student) super.clone();
    }
}

class CloneDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Student s1 = new Student(101, "Ravi");
        Student s2 = s1.clone();               // copy

        s2.name = "Kiran";                     // change only the copy

        System.out.println("s1: " + s1.rollNo + " " + s1.name);
        System.out.println("s2: " + s2.rollNo + " " + s2.name);
        System.out.println("Same object? " + (s1 == s2));
    }
}
```
**Output**
```text
s1: 101 Ravi
s2: 101 Kiran
Same object? false
```

#### Shallow copy vs deep copy
- **Shallow copy** (the default `super.clone()`): the primitive fields are copied, but **object fields are shared**. Both copies point to the same inner object.
- **Deep copy:** the inner objects are **also cloned**, so the two copies are fully independent.

```java
class Address implements Cloneable {
    String city;

    Address(String city) {
        this.city = city;
    }

    @Override
    public Address clone() throws CloneNotSupportedException {
        return (Address) super.clone();
    }
}

class Employee implements Cloneable {
    String name;
    Address address;

    Employee(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    Employee shallowCopy() throws CloneNotSupportedException {
        return (Employee) super.clone();
    }

    Employee deepCopy() throws CloneNotSupportedException {
        Employee copy = (Employee) super.clone();
        copy.address = address.clone();        // clone the inner object too
        return copy;
    }
}

class DeepShallowDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Employee e1 = new Employee("Ravi", new Address("Hyderabad"));

        Employee shallow = e1.shallowCopy();
        shallow.address.city = "Chennai";      // also changes e1
        System.out.println("After shallow copy change, e1 city: " + e1.address.city);

        e1.address.city = "Hyderabad";
        Employee deep = e1.deepCopy();
        deep.address.city = "Mumbai";          // does NOT change e1
        System.out.println("After deep copy change, e1 city: " + e1.address.city);
        System.out.println("Deep copy city: " + deep.address.city);
    }
}
```
**Output**
```text
After shallow copy change, e1 city: Chennai
After deep copy change, e1 city: Hyderabad
Deep copy city: Mumbai
```

| Shallow copy | Deep copy |
|---|---|
| Copies only the top-level fields | Copies the fields and the inner objects |
| Inner objects are shared | Inner objects are separate |
| A change through one copy affects the other | Changes don't affect each other |
| Default `super.clone()` | We clone each inner object ourselves |

### One program using all three (quick revision)
```java
class Book implements Cloneable {
    String title;
    double price;

    Book(String title, double price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return title + " (Rs." + price + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Book)) {
            return false;
        }
        Book b = (Book) obj;
        return title.equals(b.title) && price == b.price;
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    @Override
    public Book clone() throws CloneNotSupportedException {
        return (Book) super.clone();
    }
}

class ObjectClassDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Book b1 = new Book("Java", 450.0);
        Book b2 = b1.clone();

        System.out.println("b1 = " + b1);
        System.out.println("b2 = " + b2);
        System.out.println("b1 == b2 : " + (b1 == b2));
        System.out.println("b1.equals(b2) : " + b1.equals(b2));
    }
}
```
**Output**
```text
b1 = Java (Rs.450.0)
b2 = Java (Rs.450.0)
b1 == b2 : false
b1.equals(b2) : true
```

### Conclusion
`Object` is the parent of every Java class:
- **`toString()`** gives a readable description of an object.
- **`equals()`** compares the contents of two objects. Override `hashCode()` along with it.
- **`clone()`** makes a copy of an object, using the `Cloneable` interface. A shallow copy shares inner objects; a deep copy duplicates them.

---

# Part B — Unit I

## 1. OOP Principles

**OOP** (Object-Oriented Programming) is a way of writing programs using **objects**. Each object combines **data** (variables) and **code** (methods).

| Principle | Meaning | In Java |
|---|---|---|
| **Encapsulation** | Wrap data and code into one unit (class), and hide the data | `class`, `private` fields + public methods |
| **Inheritance** | A new class gets the properties of an existing class | `extends`, `implements` |
| **Polymorphism** | One name, many forms (same method, different behaviour) | overloading, overriding |
| **Abstraction** | Show essential features, hide internal details | abstract class, interface |

Other terms:
- **Class** = a blueprint.
- **Object** = an instance of a class.
- **Message passing** = objects communicate by calling methods on each other.
- **Dynamic binding** = which method to call is decided at run time.

## 2. Benefits of OOP
1. **Reusability:** inheritance removes repeated code.
2. **Data hiding and security:** private data can't be changed by other parts of the program.
3. **Modularity:** the program is divided into objects, so the work is easy to divide in a team.
4. **Easy maintenance and upgrade:** a small system can grow into a large one easily.
5. **Real-world mapping:** objects in the problem map directly to objects in the program.
6. **Less development time and cost**, because standard working modules are reused.
7. **Many objects can exist together** without interfering with each other.
8. **Message passing** makes communication between objects simple.

## 3. Java Buzzwords (Features of Java)
| Buzzword | One-line explanation |
|---|---|
| **Simple** | Syntax like C/C++. No pointers, operator overloading, structures or unions. |
| **Secure** | No pointers. Bytecode is verified, and programs run inside the JVM. |
| **Portable** | The same bytecode runs on any machine. |
| **Object-oriented** | Everything is written inside classes and objects. |
| **Robust** | Strong type checking, exception handling and automatic memory management. |
| **Architecture-neutral / platform independent** | "Write once, run anywhere", thanks to the JVM. |
| **Multithreaded** | Many tasks can run at the same time (threads). |
| **Interpreted** | Bytecode is interpreted by the JVM. |
| **High performance** | The JIT (Just-In-Time) compiler converts bytecode to machine code quickly. |
| **Distributed** | Supports networking (TCP/IP) and RMI, to call methods across a network. |
| **Dynamic** | Classes are loaded at run time, and memory is allocated dynamically. |

## 4. Bytecode, JVM, JRE, JDK
```text
Hello.java --javac--> Hello.class (bytecode) --JVM--> machine code --> output
```
- **Bytecode:** the intermediate, platform-independent code in the `.class` file, produced by `javac`.
- **JVM** (Java Virtual Machine): loads, verifies and runs bytecode. Each OS has its own JVM. This is why Java is platform independent.
- **JRE** = JVM + the libraries needed to **run** programs.
- **JDK** = JRE + development tools (`javac`, `java`, `javadoc`), needed to **write** programs.

**Why is Java platform independent?** `javac` produces bytecode, not machine code. Any machine that has a JVM can run the same bytecode.

## 5. A simple Java application program
```java
class Simple {
    public static void main(String[] args) {
        System.out.println("Hello Java");
    }
}
```
Save the file as `Simple.java`, compile with `javac Simple.java` and run with `java Simple`. Output: `Hello Java`

**Meaning of `public static void main(String[] args)`**

| Word | Meaning |
|---|---|
| `public` | the JVM can call it from outside the class |
| `static` | it is called without creating an object |
| `void` | it returns nothing |
| `main` | the starting point of the program |
| `String[] args` | holds command-line values |

## 6. Data Types
**Primitive (8 types)**

| Type | Size | Default | Example |
|---|---|---|---|
| `byte` | 1 byte | 0 | `byte b = 10;` |
| `short` | 2 bytes | 0 | `short s = 500;` |
| `int` | 4 bytes | 0 | `int i = 1000;` |
| `long` | 8 bytes | 0L | `long l = 99999L;` |
| `float` | 4 bytes | 0.0f | `float f = 2.5f;` |
| `double` | 8 bytes | 0.0 | `double d = 3.14;` |
| `char` | 2 bytes (Unicode) | '\u0000' | `char c = 'A';` |
| `boolean` | 1 bit (JVM-dependent) | false | `boolean ok = true;` |

**Non-primitive (reference) types:** String, class, interface and array. Their default value is `null`.

## 7. Variables
A **variable** is a named memory location that holds a value. Syntax: `dataType name = value;`

| Type | Declared | Copies | Default value |
|---|---|---|---|
| **Local** | inside a method or block | created on each call | none (must be assigned before use) |
| **Instance** | inside a class, outside methods | one per object | yes |
| **Static (class)** | with `static` inside a class | one per class | yes |
| **Final** | with `final` | constant, assigned once | — |

## 8. Operators and Expressions
An **expression** is a combination of operators and operands that produces a value, for example `a + b * 2`.

| Type | Operators |
|---|---|
| Arithmetic | `+ - * / % ++ --` |
| Relational | `< > <= >= == !=` (the result is true or false) |
| Logical | `&&  \|\|  !` |
| Assignment | `= += -= *= /= %=` |
| Bitwise | `&  \|  ^  ~  <<  >>  >>>` |
| Conditional (ternary) | `condition ? value1 : value2` |

```java
class OperatorDemo {
    public static void main(String[] args) {
        int a = 25;
        int b = 20;
        System.out.println("a + b = " + (a + b));
        System.out.println("a % b = " + (a % b));
        System.out.println("a > b : " + (a > b));
        System.out.println("a & b = " + (a & b));
        System.out.println("a | b = " + (a | b));
        System.out.println("a << 1 = " + (a << 1));
    }
}
```
**Output**
```text
a + b = 45
a % b = 5
a > b : true
a & b = 16
a | b = 29
a << 1 = 50
```
Working: 25 = 11001 and 20 = 10100. So 11001 & 10100 = 10000 = 16, and 11001 | 10100 = 11101 = 29.

## 9. Control Statements
| Type | Statements |
|---|---|
| Selection | `if`, `if-else`, nested `if`, `if-else-if`, `switch` |
| Iteration (loops) | `while` (entry-controlled), `do-while` (exit-controlled, runs at least once), `for`, for-each |
| Jump | `break`, `continue`, `return` |

```java
class ControlDemo {
    public static void main(String[] args) {
        int marks = 72;
        if (marks >= 75) {
            System.out.println("Distinction");
        } else if (marks >= 60) {
            System.out.println("First class");
        } else {
            System.out.println("Pass");
        }

        int day = 3;
        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            default:
                System.out.println("Other day");
        }
    }
}
```
**Output**
```text
First class
Wednesday
```
**while vs do-while:** `while` checks the condition first, so it may run 0 times. `do-while` checks it at the end, so it runs at least once.

## 10. Type Conversion and Casting
| Type conversion (widening) | Type casting (narrowing) |
|---|---|
| Done **automatically** by the compiler | Done **by the programmer** with `(type)` |
| Small type → big type | Big type → small type |
| No data loss | May lose data |
| `int x = 30; double y = x;` → 30.0 | `double d = 9.78; int i = (int) d;` → 9 |

Widening order: `byte → short → int → long → float → double` (and `char → int`).

```java
class CastDemo {
    public static void main(String[] args) {
        int i = 30;
        double d = i;                 // widening (automatic)
        System.out.println(d);

        double price = 9.78;
        int rounded = (int) price;    // narrowing (casting)
        System.out.println(rounded);

        int big = 130;
        byte small = (byte) big;      // overflow: 130 - 256 = -126
        System.out.println(small);
    }
}
```
**Output**
```text
30.0
9
-126
```

## 11. Classes and Objects
- **Class:** a blueprint or template that describes the state (variables) and behaviour (methods) of objects.
- **Object:** an instance of a class. It has **state** (fields), **behaviour** (methods) and **identity**.
- An object is created with `new`:
  1. **declaration:** `Dog d;`
  2. **instantiation:** `new`
  3. **initialization:** the constructor runs.
- Objects live in **heap** memory. References and local variables live on the **stack**.

```java
class Dog {
    String name;
    int age;

    void bark() {
        System.out.println(name + " is barking");
    }
}

class DogDemo {
    public static void main(String[] args) {
        Dog d1 = new Dog();
        d1.name = "Tommy";
        d1.age = 3;
        d1.bark();
        System.out.println("Age: " + d1.age);
    }
}
```
**Output**
```text
Tommy is barking
Age: 3
```

## 12. Constructors
A **constructor** is a special method that **initializes an object** when it is created.

**Rules**
- Its name is the same as the class name.
- It has **no return type** (not even `void`).
- It is called automatically by `new`.
- It can be overloaded, and it can be `private`.
- It **cannot** be `static`, `final` or `abstract`, and it is **not inherited**.
- If you write no constructor, Java gives a **default constructor**. Once you write any constructor, the default one is not given.

**Types:**
- default (no-argument),
- parameterized,
- copy constructor.

```java
class Box {
    double length;
    double width;

    Box() {                              // default constructor
        length = 1;
        width = 1;
    }

    Box(double length, double width) {   // parameterized constructor
        this.length = length;
        this.width = width;
    }

    Box(Box other) {                     // copy constructor
        this.length = other.length;
        this.width = other.width;
    }

    double area() {
        return length * width;
    }
}

class ConstructorDemo {
    public static void main(String[] args) {
        Box b1 = new Box();
        Box b2 = new Box(4, 5);
        Box b3 = new Box(b2);
        System.out.println("b1 area = " + b1.area());
        System.out.println("b2 area = " + b2.area());
        System.out.println("b3 area = " + b3.area());
    }
}
```
**Output**
```text
b1 area = 1.0
b2 area = 20.0
b3 area = 20.0
```

| Constructor | Method |
|---|---|
| Same name as the class | Any name |
| No return type | Must have a return type |
| Called automatically by `new` | Called explicitly |
| Not inherited | Inherited |

## 13. Methods
A **method** is a named block of code that does a task, and it runs only when called. Its main benefit is code reuse.

Syntax: `accessModifier returnType methodName(parameters) { body }`
- If the return type is not `void`, the method must `return` a value.
- A **static** method is called with the class name. An instance method needs an object.
- **Variable arguments:** `int sum(int... nums)` accepts any number of ints.

## 14. Access Control (Access Modifiers)
| Modifier | Same class | Same package | Subclass (other package) | Anywhere |
|---|:-:|:-:|:-:|:-:|
| `private` | Yes | No | No | No |
| default (no keyword) | Yes | Yes | No | No |
| `protected` | Yes | Yes | Yes | No |
| `public` | Yes | Yes | Yes | Yes |

- A top-level class can be only `public` or default. `private class Sample` gives an error.
- Order from least to most visible: **private < default < protected < public**.

## 15. this Keyword
`this` is a reference to the **current object**. It has six uses:
1. `this.x = x;` refers to the current object's variable when a parameter has the same name.
2. `this.method()` calls a method of the current class (the compiler adds it automatically).
3. `this(...)` calls another constructor of the same class (constructor chaining). It **must be the first statement**.
4. `this` can be passed as an argument in a method call.
5. `this` can be passed as an argument in a constructor call.
6. `return this;` returns the current object.

```java
class Student {
    int rollNo;
    String name;
    String course;

    Student(int rollNo, String name) {
        this(rollNo, name, "Java");         // use 3: constructor chaining
    }

    Student(int rollNo, String name, String course) {
        this.rollNo = rollNo;               // use 1: field vs parameter
        this.name = name;
        this.course = course;
    }

    void display() {
        System.out.println(rollNo + " " + name + " " + course);
    }
}

class ThisDemo {
    public static void main(String[] args) {
        Student s1 = new Student(111, "Ankit");
        Student s2 = new Student(112, "Sumit", "Python");
        s1.display();
        s2.display();
    }
}
```
**Output**
```text
111 Ankit Java
112 Sumit Python
```
**Common mistake:** writing `rollNo = rollNo;` without `this`. It assigns the parameter to itself, so the field stays 0 or null.

## 16. Overloading Methods and Constructors
**Method overloading:** several methods with the **same name but different parameters** (a different number, type or order). The return type alone is not enough. The method to call is decided at **compile time**, so this is compile-time polymorphism.

```java
class Calculator {
    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }

    double add(double a, double b) {
        return a + b;
    }
}

class OverloadDemo {
    public static void main(String[] args) {
        Calculator c = new Calculator();
        System.out.println(c.add(10, 20));
        System.out.println(c.add(10, 20, 30));
        System.out.println(c.add(2.5, 3.5));
    }
}
```
**Output**
```text
30
60
6.0
```
**Constructor overloading:** more than one constructor with different parameter lists. See the `Box` example in section 12.

## 17. static Keyword
`static` members belong to the **class**, not to objects. There is only **one copy**, shared by all objects. `static` can be used on variables, methods, blocks and nested classes.

- **Static variable:** a common property, such as a college name or an object counter. It gets memory once, when the class is loaded.
- **Static method:** called with `ClassName.method()`, no object needed. It **cannot use instance variables or `this`/`super` directly**.
- **Static block:** runs **once**, when the class is loaded, **before `main`**.
- **Why is `main` static?** So the JVM can call it without creating an object.

```java
class Student {
    int rollNo;
    String name;
    static String college = "NGIT";       // one copy for all
    static int count = 0;

    static {
        System.out.println("Static block runs first");
    }

    Student(int rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
        count++;
    }

    static void showCount() {
        System.out.println("Students created: " + count);
    }

    void display() {
        System.out.println(rollNo + " " + name + " " + college);
    }
}

class StaticDemo {
    public static void main(String[] args) {
        Student s1 = new Student(1, "Karan");
        Student s2 = new Student(2, "Aryan");
        s1.display();
        s2.display();
        Student.showCount();
    }
}
```
**Output**
```text
Static block runs first
1 Karan NGIT
2 Aryan NGIT
Students created: 2
```

| Static | Instance |
|---|---|
| One copy per class | One copy per object |
| Accessed with the class name | Accessed through an object |
| Memory given when the class loads | Memory given at `new` |
| Cannot use `this` | Can use `this` |

## 18. final Keyword
| Used on | Effect | Error if broken |
|---|---|---|
| Variable | Becomes a constant (assigned once) | `cannot assign a value to final variable` |
| Method | Cannot be overridden | `overridden method is final` |
| Class | Cannot be inherited | `cannot inherit from final` |

```java
final class Bank {                         // no class can extend Bank
    final double RATE = 7.5;               // constant

    final void showRate() {                // cannot be overridden
        System.out.println("Rate: " + RATE);
    }
}
```
- A **blank final** variable is declared without a value and must be assigned in the constructor.
- `static final` = a class constant, for example `static final double PI = 3.14;`.

## 19. Inheritance
**Inheritance** is the process by which one class **acquires the properties and methods** of another class, using `extends`. Its main benefit is **code reusability**.
- **Parent** = base class = superclass. **Child** = derived class = subclass.
- The child gets everything from the parent; the parent gets nothing from the child.

**Types of inheritance**
```text
Single        Multilevel      Hierarchical        Multiple*        Hybrid*
  A             A                 A              A     B            A
  |             |               /   \             \   /            / \
  B             B              B     C              C             B   C
                |                                                  \ /
                C                                                   D
```
\* Java does **not** support multiple and hybrid inheritance with classes. It supports them through **interfaces** (see Q2).

```java
class Animal {                              // parent
    void eat() {
        System.out.println("Animal eats");
    }
}

class Dog extends Animal {                  // single: Dog -> Animal
    void bark() {
        System.out.println("Dog barks");
    }
}

class Puppy extends Dog {                   // multilevel: Puppy -> Dog -> Animal
    void weep() {
        System.out.println("Puppy weeps");
    }
}

class Cat extends Animal {                  // hierarchical: Dog and Cat -> Animal
    void meow() {
        System.out.println("Cat meows");
    }
}

class InheritanceDemo {
    public static void main(String[] args) {
        Puppy p = new Puppy();
        p.eat();
        p.bark();
        p.weep();
        Cat c = new Cat();
        c.eat();
        c.meow();
    }
}
```
**Output**
```text
Animal eats
Dog barks
Puppy weeps
Animal eats
Cat meows
```

### Member access rules in inheritance
- `private` members of the parent are **not accessible** in the child. Use public getters to reach them.
- default members are accessible only if the child is in the **same package**.
- `protected` and `public` members are accessible in the child, even in another package.
- Constructors are **not inherited**, but the parent's constructor **always runs first**.

### super keyword
1. `super.variable` uses the parent's variable when the child has a variable with the same name.
2. `super.method()` calls the parent's version of an overridden method.
3. `super(...)` calls the parent's constructor. It **must be the first statement** in the child's constructor.

If you don't write `super(...)`, Java adds `super()` automatically. If the parent has no no-argument constructor, this is a compile error.

```java
class Person {
    String name = "Person";

    Person(String msg) {
        System.out.println("Person constructor: " + msg);
    }

    void show() {
        System.out.println("I am a person");
    }
}

class Employee extends Person {
    String name = "Employee";

    Employee() {
        super("called from Employee");     // use 3
        System.out.println("Employee constructor");
    }

    void show() {
        super.show();                      // use 2
        System.out.println("I am an employee");
        System.out.println(super.name + " / " + name);   // use 1
    }
}

class SuperDemo {
    public static void main(String[] args) {
        Employee e = new Employee();
        e.show();
    }
}
```
**Output**
```text
Person constructor: called from Employee
Employee constructor
I am a person
I am an employee
Person / Employee
```

| `this` | `super` |
|---|---|
| Refers to the current class object | Refers to the parent class part |
| `this()` calls a constructor of the same class | `super()` calls the parent's constructor |
| Resolves field vs parameter names | Resolves child vs parent names |

### final with inheritance
- A `final` method is inherited but **cannot be overridden**.
- A `final` class **cannot be extended**.

## 20. Polymorphism — Dynamic Binding
**Polymorphism** means *one name, many forms*.

| Compile-time (static, ad hoc) | Run-time (dynamic, pure) |
|---|---|
| Method **overloading** | Method **overriding** |
| Same class | Parent and child classes |
| Different parameters | Same name and parameters |
| Decided by the compiler | Decided by the JVM at run time |
| **Early (static) binding** | **Late (dynamic) binding** |

**Dynamic binding / dynamic method dispatch:** when a **parent reference holds a child object**, the overridden method of the **actual object** (the child) runs. Java decides this at **run time**.

## 21. Method Overriding
**Overriding** means redefining, in the child class, a method that is already in the parent, with the **same name, parameters and return type**.

**Rules**
1. Use the same name, the same parameters and the same return type (or a subclass type).
2. The access **cannot be reduced**. For example, `protected` can become `public`, but not `private`.
3. `static`, `final` and `private` methods **cannot** be overridden. Constructors cannot be overridden either.
4. An abstract method **must** be overridden.
5. Use `super.method()` to call the parent's version.
6. Exceptions: if the parent's method throws no checked exception, the child's method cannot throw a checked exception.
7. Write `@Override`, so the compiler checks the signature.

```java
class Shape {
    void draw() {
        System.out.println("Drawing a shape");
    }
}

class Circle extends Shape {
    @Override
    void draw() {
        System.out.println("Drawing a circle");
    }
}

class Square extends Shape {
    @Override
    void draw() {
        System.out.println("Drawing a square");
    }
}

class OverrideDemo {
    public static void main(String[] args) {
        Shape s;                  // parent reference
        s = new Circle();
        s.draw();                 // Circle's draw (decided at run time)
        s = new Square();
        s.draw();                 // Square's draw
        s = new Shape();
        s.draw();
    }
}
```
**Output**
```text
Drawing a circle
Drawing a square
Drawing a shape
```

| Overloading | Overriding |
|---|---|
| Same class | Parent and child classes |
| Parameters must differ | Parameters must be the same |
| Return type can differ | Return type is the same (or covariant) |
| Compile-time polymorphism | Run-time polymorphism |
| `static`/`final`/`private` methods can be overloaded | They cannot be overridden |

## 22. Abstract Classes and Methods
- An **abstract method** is declared with `abstract` and has **no body**: `abstract void area();`
- An **abstract class** is declared with `abstract`. **No object can be created** from it, but a **reference** to it can be created.

**Rules**
1. A class with even one abstract method **must** be abstract.
2. An abstract class can have **zero** abstract methods.
3. It can have normal methods, `final` methods, `static` methods, variables and **constructors**. The constructor runs when a child object is created.
4. Every child must override all the abstract methods, or the child must itself be abstract.
5. `abstract` cannot be combined with `final`, `static` or `private`.

```java
abstract class Shape {
    abstract double area();

    void show() {
        System.out.println("Area = " + area());
    }
}

class Rectangle extends Shape {
    double l = 4;
    double b = 5;

    double area() {
        return l * b;
    }
}

class Triangle extends Shape {
    double base = 6;
    double height = 4;

    double area() {
        return 0.5 * base * height;
    }
}

class AbstractShapeDemo {
    public static void main(String[] args) {
        Shape obj = new Rectangle();   // base reference, child object
        obj.show();
        obj = new Triangle();
        obj.show();
    }
}
```
**Output**
```text
Area = 20.0
Area = 12.0
```

---

# Part C — Unit II

## 1. Interfaces

### Defining an interface
An **interface** is a **specification of method prototypes**: a set of abstract methods and constants that a class promises to implement.
```java
interface InterfaceName {
    int CONSTANT = 10;               // public static final by default
    void method1();                  // public abstract by default
    int method2(int x);
}
```

**Rules (from the faculty notes)**
- All methods are `public abstract` by default.
- All variables are `public static final` (constants) by default.
- We **cannot create an object** of an interface, but we **can create a reference** to it.
- An implementing class must implement **all** the methods. If it does not, the class must be declared `abstract` (a partial implementation).
- The implemented methods must be declared `public`.
- A class can implement **many** interfaces, separated by commas.
- An interface **cannot implement** another interface, but it **can extend** one or more interfaces.
- Since Java 8 an interface can also have `default` and `static` methods. Since Java 9 it can have `private` methods.

### Implementing an interface
```java
class ClassName extends SuperClass implements Interface1, Interface2 { ... }
```

**Program (from the faculty notes, cleaned up)**
```java
interface Shape {
    double PI = 3.14;
    void area();
    void perimeter();
}

class Circle implements Shape {
    double r;

    Circle(double r) {
        this.r = r;
    }

    public void area() {
        System.out.println("Area of circle = " + (PI * r * r));
    }

    public void perimeter() {
        System.out.println("Perimeter of circle = " + (2 * PI * r));
    }
}

class Rectangle implements Shape {
    double l;
    double b;

    Rectangle(double l, double b) {
        this.l = l;
        this.b = b;
    }

    public void area() {
        System.out.println("Area of rectangle = " + (l * b));
    }

    public void perimeter() {
        System.out.println("Perimeter of rectangle = " + (2 * (l + b)));
    }
}

class InterfaceShapeDemo {
    public static void main(String[] args) {
        Shape s = new Circle(10);
        s.area();
        s.perimeter();
        s = new Rectangle(4, 5);
        s.area();
        s.perimeter();
    }
}
```
**Output**
```text
Area of circle = 314.0
Perimeter of circle = 62.800000000000004
Area of rectangle = 20.0
Perimeter of rectangle = 18.0
```
(The value 62.800000000000004 is normal: `double` arithmetic is not exact.)

### Partial implementation
```java
abstract class Incomplete implements Shape {
    public void area() {
        System.out.println("Only area is written");
    }
    // perimeter() is not written, so the class must be abstract
}
```

### Extending interfaces
One interface can inherit another using `extends`. A class that implements the child interface must implement **all** the methods in the chain.
```java
interface A {
    void meth1();
}

interface B extends A {
    void meth2();
}

class MyClass implements B {
    public void meth1() {
        System.out.println("Implement meth1()");
    }

    public void meth2() {
        System.out.println("Implement meth2()");
    }
}

class IFExtend {
    public static void main(String[] args) {
        MyClass ob = new MyClass();
        ob.meth1();
        ob.meth2();
    }
}
```
**Output**
```text
Implement meth1()
Implement meth2()
```
If you remove `meth1()` from MyClass, you get a compile-time error.

### Variables in interfaces (shared constants)
An interface with only constants lets many classes share them, like `#define` in C.
```java
interface Grades {
    int PASS_MARK = 40;
    int DISTINCTION = 75;
}

class Result implements Grades {
    void check(int marks) {
        if (marks >= DISTINCTION) {
            System.out.println(marks + " : Distinction");
        } else if (marks >= PASS_MARK) {
            System.out.println(marks + " : Pass");
        } else {
            System.out.println(marks + " : Fail");
        }
    }
}

class ConstantsDemo {
    public static void main(String[] args) {
        Result r = new Result();
        r.check(80);
        r.check(55);
        r.check(30);
    }
}
```
**Output**
```text
80 : Distinction
55 : Pass
30 : Fail
```

### Nested interface
An interface declared **inside a class or another interface**. Outside its class, use it as `Outer.Inner`.
```java
class A {
    public interface NestedIF {
        boolean isPositive(int x);
    }
}

class B implements A.NestedIF {
    public boolean isPositive(int x) {
        return x > 0;
    }
}

class NestedIFDemo {
    public static void main(String[] args) {
        A.NestedIF nif = new B();
        System.out.println(nif.isPositive(10));
        System.out.println(nif.isPositive(-12));
    }
}
```
**Output**
```text
true
false
```

### Applying interfaces
The same interface can have **different implementations**. For example, a stack can be fixed-size or growable, but its interface (`push`, `pop`) stays the same:
```java
interface IntStack {
    void push(int item);
    int pop();
}
```
`FixedStack implements IntStack` and `DynStack implements IntStack` can be swapped without changing the code that uses `IntStack`.

### Class vs interface
| Class | Interface |
|---|---|
| Keyword `class` | Keyword `interface` |
| Objects can be created | Objects cannot be created |
| Methods have bodies | Methods are abstract (except `default`/`static`) |
| Variables can have any modifier | Variables are `public static final` |
| Has constructors | No constructors |
| `extends` only one class | A class `implements` many interfaces |
| Multiple inheritance not possible | Multiple inheritance possible |

For abstract class vs interface, see [Q1](#comparison-abstract-class-vs-interface).

---

## 2. Packages

### Defining a package
A **package** is a **container (folder) of related classes and interfaces**. It keeps class names in separate groups, called namespaces.

**Advantages**
1. Related classes are grouped together (for example, all I/O classes are in `java.io`).
2. Name conflicts are avoided: `java.util.Date` and `java.sql.Date` can both exist.
3. **Access protection:** default members are hidden from other packages.
4. **Reusability:** a package works like a library, and its classes can be reused many times.
5. Classes are stored in separate folders, so they are not deleted by accident.

### Types of packages
**1. Built-in packages**

| Package | Contains |
|---|---|
| `java.lang` | String, Math, System, Thread, wrapper classes (imported automatically) |
| `java.util` | Scanner, ArrayList, Date, collections |
| `java.io` | Input/output stream classes, File |
| `java.awt` | GUI components |
| `javax.swing` | Advanced GUI components |
| `java.net` | Networking (sockets) |
| `java.sql` | Database connectivity (JDBC) |

**2. User-defined packages:** packages created by the programmer.

### Creating a package
1. Write `package packageName;` as the **first statement** of the file.
2. Make the class and the members you want to share `public`.
3. Compile with `javac -d . FileName.java`. The `-d .` option creates the package folder in the current directory.

```java
// File: pack/Addition.java
package pack;

public class Addition {
    private double d1;
    private double d2;

    public Addition(double a, double b) {
        d1 = a;
        d2 = b;
    }

    public void sum() {
        System.out.println("Sum = " + (d1 + d2));
    }
}
```

### Accessing (importing) a package: 3 ways
```java
import pack.Addition;     // 1. import one class
import pack.*;            // 2. import all classes of the package
pack.Addition a = new pack.Addition(10, 20);   // 3. fully qualified name, no import
```

```java
// File: Use.java
import pack.Addition;

class Use {
    public static void main(String[] args) {
        Addition obj = new Addition(10, 20);
        obj.sum();
    }
}
```
Compile and run: `javac -d . pack/Addition.java Use.java`, then `java Use`. Output: `Sum = 30.0`

- **Sub-package:** `package pack1.pack2;`, imported with `import pack1.pack2.*;`
- **CLASSPATH:** an environment variable that tells Java **where to find classes and packages**. Example: `set CLASSPATH=.;E:\sub;%CLASSPATH%`
- `import pack.*;` does **not** import the sub-packages of `pack`.

### Access protection (package level)
| Member | Same class | Same package (subclass or not) | Other package, subclass | Other package, non-subclass |
|---|:-:|:-:|:-:|:-:|
| `private` | Yes | No | No | No |
| default | Yes | Yes | No | No |
| `protected` | Yes | Yes | Yes | No |
| `public` | Yes | Yes | Yes | Yes |

Scope names used in the faculty notes:
- `private` = **class scope**
- default = **package scope**
- `public` = **global scope**
- `protected` = package scope plus subclasses in other packages

---

## 3. Exception Handling
*(Source: standard textbook. The faculty Unit II PDF does not cover this topic.)*

### What is an exception?
- An **exception** is an **abnormal condition (a run-time error)** that interrupts the normal flow of a program. Examples: dividing by zero, a file not found, a null object.
- **Exception handling** is the mechanism for catching these errors so that the program **does not stop suddenly** and continues normally.

### Benefits of exception handling
1. The program does not terminate abnormally; the normal flow is maintained.
2. Error-handling code is **separated** from normal code, so the program is clean and readable.
3. Errors are **grouped by type** (an exception class hierarchy).
4. Errors are **passed up the call stack** to the method that can handle them (`throws`).
5. Meaningful messages are shown to the user.
6. Resources (files, connections) can be closed safely in `finally`.

### Exception hierarchy (classification)
```text
                  Object
                    |
                Throwable
               /         \
          Error           Exception
    (OutOfMemoryError,   /          \
     StackOverflowError) IOException  RuntimeException
                         SQLException   ArithmeticException
                      (checked)       NullPointerException
                                      ArrayIndexOutOfBoundsException
                                      NumberFormatException
                                      (unchecked)
```
- **Error:** a serious problem in the JVM or system. It cannot be handled by the program.
- **Exception:** a problem in the program that **can be handled**.

### Checked vs unchecked exceptions
| Checked exceptions | Unchecked exceptions |
|---|---|
| Checked by the **compiler** at compile time | Not checked at compile time; happen at **run time** |
| Must be handled with `try-catch` or declared with `throws` | Handling is optional |
| Subclasses of `Exception` (except RuntimeException) | Subclasses of `RuntimeException` (and `Error`) |
| Usually caused by outside conditions | Usually caused by programming mistakes |
| `IOException`, `FileNotFoundException`, `SQLException`, `ClassNotFoundException`, `InterruptedException` | `ArithmeticException`, `NullPointerException`, `ArrayIndexOutOfBoundsException`, `NumberFormatException`, `ClassCastException` |

### The five keywords
| Keyword | Use |
|---|---|
| `try` | Encloses the code that may cause an exception |
| `catch` | Handles the exception thrown in `try` |
| `finally` | Always runs (whether an exception happened or not), used for clean-up |
| `throw` | Throws an exception object explicitly: `throw new ArithmeticException("msg");` |
| `throws` | Declares, in the method header, that the method may throw an exception |

```java
try {
    // risky code
} catch (ExceptionType1 e) {
    // handler 1
} catch (ExceptionType2 e) {
    // handler 2
} finally {
    // always runs
}
```

### try, catch and finally
```java
class TryCatchDemo {
    public static void main(String[] args) {
        try {
            int a = 10;
            int b = 0;
            int c = a / b;                         // ArithmeticException
            System.out.println("Result: " + c);    // skipped
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Finally block always runs");
        }
        System.out.println("Program continues normally");
    }
}
```
**Output**
```text
Error: / by zero
Finally block always runs
Program continues normally
```

### Multiple catch blocks
Write the **child exception first and the parent (`Exception`) last**. Otherwise you get a compile error: "exception has already been caught".
```java
class MultiCatchDemo {
    public static void main(String[] args) {
        try {
            String s = null;
            System.out.println(s.length());        // NullPointerException
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic problem");
        } catch (NullPointerException e) {
            System.out.println("Null object used");
        } catch (Exception e) {
            System.out.println("Some other problem");
        }

        try {
            int n = Integer.parseInt("12a");       // NumberFormatException
            System.out.println(n);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format");
        }
    }
}
```
**Output**
```text
Null object used
Invalid number format
```

### throw: throwing an exception yourself
```java
class ThrowDemo {
    static void checkAge(int age) {
        if (age < 18) {
            throw new ArithmeticException("Not eligible to vote");
        }
        System.out.println("Eligible to vote");
    }

    public static void main(String[] args) {
        checkAge(20);
        try {
            checkAge(15);
        } catch (ArithmeticException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}
```
**Output**
```text
Eligible to vote
Caught: Not eligible to vote
```

### throws: declaring an exception
```java
class ThrowsDemo {
    static void waitAWhile() throws InterruptedException {    // checked exception
        Thread.sleep(100);
        System.out.println("Waited 100 ms");
    }

    public static void main(String[] args) {
        try {
            waitAWhile();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}
```
**Output**
```text
Waited 100 ms
```

| `throw` | `throws` |
|---|---|
| Used **inside** a method | Used in the method **header** |
| Followed by an **object**: `throw new X()` | Followed by **class names**: `throws X, Y` |
| Throws one exception | Can declare many exceptions |
| Mostly for unchecked or custom exceptions | Mostly for checked exceptions |

### Rethrowing an exception
A catch block can do some work (such as logging) and then **throw the same exception again** to the caller.
```java
class RethrowDemo {
    static void divide(int a, int b) {
        try {
            System.out.println(a / b);
        } catch (ArithmeticException e) {
            System.out.println("Logged inside divide()");
            throw e;                                // rethrow
        }
    }

    public static void main(String[] args) {
        try {
            divide(10, 0);
        } catch (ArithmeticException e) {
            System.out.println("Handled in main: " + e.getMessage());
        }
    }
}
```
**Output**
```text
Logged inside divide()
Handled in main: / by zero
```

### Nested try (extra)
A `try` block inside another `try` block. If the inner catch cannot handle the exception, the outer catch handles it.

### Built-in exceptions
| Exception | When it occurs |
|---|---|
| `ArithmeticException` | Dividing an integer by zero |
| `NullPointerException` | Using a `null` reference |
| `ArrayIndexOutOfBoundsException` | An invalid array index |
| `NumberFormatException` | `Integer.parseInt("abc")` |
| `ClassCastException` | A wrong downcast |
| `StringIndexOutOfBoundsException` | An invalid string index |
| `IOException` (checked) | Input/output failure |
| `FileNotFoundException` (checked) | The file does not exist |
| `ClassNotFoundException` (checked) | The class is not found when loading |
| `InterruptedException` (checked) | A sleeping or waiting thread is interrupted |

### Creating your own exception subclass (user-defined exception)
1. Create a class that **extends `Exception`** (checked) or `RuntimeException` (unchecked).
2. Pass the message to the parent with `super(message)`.
3. Throw it with `throw`, and declare it with `throws`.

```java
class InsufficientBalanceException extends Exception {
    InsufficientBalanceException(String message) {
        super(message);
    }
}

class Account {
    private double balance = 1000;

    void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Balance is only " + balance);
        }
        balance = balance - amount;
        System.out.println("Withdrawn " + amount + ", left " + balance);
    }
}

class CustomExceptionDemo {
    public static void main(String[] args) {
        Account acc = new Account();
        try {
            acc.withdraw(400);
            acc.withdraw(800);
        } catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Thank you for banking");
        }
    }
}
```
**Output**
```text
Withdrawn 400.0, left 600.0
Error: Balance is only 600.0
Thank you for banking
```

---

## 4. Multithreading
*(Source: standard textbook. The faculty Unit II PDF does not cover this topic.)*

### Basic terms
- **Thread:** a lightweight sub-process, the smallest unit of execution.
- **Multithreading:** running **two or more threads at the same time** within one program.
- **Multitasking** has two kinds: **process-based** (several programs, which is heavyweight) and **thread-based** (several threads in one program, which is lightweight).

| Process | Thread |
|---|---|
| A program in execution | A part of a process |
| Heavyweight | Lightweight |
| Has its own memory | Shares the process's memory |
| Switching between processes is costly | Switching between threads is cheap |

**Advantages of multithreading:** better CPU use, faster programs, a responsive user interface, shared memory, and low cost of switching between threads.

### Java thread model: the life cycle of a thread
```text
 NEW --start()--> RUNNABLE <--scheduler--> RUNNING --run() ends--> TERMINATED (DEAD)
                     ^                        |
                     |                        | sleep() / wait() / join() / I/O
                     +---- BLOCKED / WAITING <+
                          (notify(), time over, I/O done)
```
| State | Meaning |
|---|---|
| **New** | The Thread object is created, but `start()` has not been called |
| **Runnable** | `start()` has been called; the thread is waiting for the CPU |
| **Running** | The thread scheduler has given it the CPU, and `run()` is executing |
| **Blocked / Waiting** | Paused by `sleep()`, `wait()`, `join()` or I/O |
| **Terminated** | `run()` has finished |

### The main thread
When a Java program starts, the JVM creates one thread automatically: the **main thread**. It runs `main()`. Child threads are created from it, and it is usually the last thread to finish.
```java
class MainThreadDemo {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        System.out.println("Current thread: " + t.getName());
        t.setName("MyMain");
        System.out.println("After renaming: " + t.getName());
        System.out.println("Priority: " + t.getPriority());
    }
}
```
**Output**
```text
Current thread: main
After renaming: MyMain
Priority: 5
```

### Creating a thread: 2 ways
**Way 1: extend the `Thread` class.** Override `run()`, then call `start()`.
```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Child thread running: " + getName());
    }
}

class ThreadDemo1 {
    public static void main(String[] args) throws InterruptedException {
        MyThread t = new MyThread();
        t.setName("Worker");
        t.start();                    // start() creates a new thread and calls run()
        t.join();
        System.out.println("Main thread ends");
    }
}
```
**Output**
```text
Child thread running: Worker
Main thread ends
```

**Way 2: implement the `Runnable` interface** (preferred, because the class can still extend another class).
```java
class MyTask implements Runnable {
    public void run() {
        System.out.println("Runnable task running");
    }
}

class ThreadDemo2 {
    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask();
        Thread t = new Thread(task);
        t.start();
        t.join();
        System.out.println("Main thread ends");
    }
}
```
**Output**
```text
Runnable task running
Main thread ends
```

| Extending Thread | Implementing Runnable |
|---|---|
| The class cannot extend any other class | The class can still extend another class |
| Use `t.start()` directly | Pass the object to `new Thread(obj)` |
| Less flexible | More flexible (preferred) |

**Why call `start()` and not `run()`?** `start()` creates a **new thread** and then calls `run()` in it. Calling `run()` directly runs it as a normal method in the **same** thread.

### Creating multiple threads, isAlive() and join()
- `isAlive()` returns `true` if the thread is still running.
- `join()` makes the calling thread **wait until that thread finishes**.

```java
class Worker extends Thread {
    Worker(String name) {
        super(name);
    }

    public void run() {
        System.out.println(getName() + " started");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.out.println(getName() + " interrupted");
        }
        System.out.println(getName() + " finished");
    }
}

class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        Worker t1 = new Worker("Thread-A");
        Worker t2 = new Worker("Thread-B");

        t1.start();
        t1.join();                                 // wait for A first
        t2.start();

        System.out.println("Is A alive? " + t1.isAlive());
        System.out.println("Is B alive? " + t2.isAlive());

        t2.join();
        System.out.println("Is B alive after join? " + t2.isAlive());
        System.out.println("Main thread ends last");
    }
}
```
**Output**
```text
Thread-A started
Thread-A finished
Is A alive? false
Is B alive? true
Thread-B started
Thread-B finished
Is B alive after join? false
Main thread ends last
```
(The line "Thread-B started" may print just before or just after the two "alive" lines. Thread timing can vary.)

### Thread priorities
- Every thread has a priority from **1 to 10**: `MIN_PRIORITY = 1`, `NORM_PRIORITY = 5` (the default), `MAX_PRIORITY = 10`.
- `setPriority(int)` and `getPriority()` set and read it.
- A higher-priority thread *usually* gets the CPU first. This is only a **hint** to the scheduler, so the output order is **not guaranteed**.

```java
class PriorityDemo {
    public static void main(String[] args) {
        Thread low = new Thread();
        Thread high = new Thread();
        low.setPriority(Thread.MIN_PRIORITY);
        high.setPriority(Thread.MAX_PRIORITY);
        System.out.println("Low priority: " + low.getPriority());
        System.out.println("High priority: " + high.getPriority());
        System.out.println("Main priority: " + Thread.currentThread().getPriority());
    }
}
```
**Output**
```text
Low priority: 1
High priority: 10
Main priority: 5
```

### Synchronization
- When two threads use the **same shared object** at the same time, the data can become wrong or mixed. This is a **race condition**.
- **Synchronization** allows **only one thread at a time** to use a shared resource. Each object has a **lock (monitor)**. A thread must get the lock to enter `synchronized` code.
- There are two forms: a **synchronized method** (`synchronized void m()`) and a **synchronized block** (`synchronized (obj) { ... }`).

```java
class Printer {
    synchronized void printTable(int n) {          // remove synchronized to see mixed output
        System.out.println(n + " x 1 = " + (n * 1));
        pause();
        System.out.println(n + " x 2 = " + (n * 2));
        pause();
        System.out.println(n + " x 3 = " + (n * 3));
    }

    void pause() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}

class TableThread extends Thread {
    Printer p;
    int n;

    TableThread(Printer p, int n) {
        this.p = p;
        this.n = n;
    }

    public void run() {
        p.printTable(n);
    }
}

class SyncDemo {
    public static void main(String[] args) throws InterruptedException {
        Printer shared = new Printer();            // one shared object
        TableThread t1 = new TableThread(shared, 5);
        TableThread t2 = new TableThread(shared, 7);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
```
**Output** (one table completes before the other starts; which comes first may vary)
```text
5 x 1 = 5
5 x 2 = 10
5 x 3 = 15
7 x 1 = 7
7 x 2 = 14
7 x 3 = 21
```
Without `synchronized`, the lines of the two tables get mixed, for example `5 x 1`, `7 x 1`, `5 x 2`, `7 x 2` and so on.

### Inter-thread communication
Threads talk to each other using three methods of the **Object** class. They can only be called **inside synchronized code**.

| Method | Work |
|---|---|
| `wait()` | Releases the lock and waits until another thread calls `notify()` |
| `notify()` | Wakes up one waiting thread |
| `notifyAll()` | Wakes up all waiting threads |

**Producer–consumer example** (one item at a time)
```java
class Shop {
    int item;
    boolean available = false;

    synchronized void put(int value) throws InterruptedException {
        if (available) {
            wait();                    // wait until the consumer takes it
        }
        item = value;
        available = true;
        System.out.println("Produced: " + item);
        notify();                      // tell the consumer
    }

    synchronized void get() throws InterruptedException {
        if (!available) {
            wait();                    // wait until the producer puts it
        }
        System.out.println("Consumed: " + item);
        available = false;
        notify();                      // tell the producer
    }
}

class Producer extends Thread {
    Shop shop;

    Producer(Shop shop) {
        this.shop = shop;
    }

    public void run() {
        try {
            shop.put(1);
            shop.put(2);
        } catch (InterruptedException e) {
            System.out.println("Producer interrupted");
        }
    }
}

class Consumer extends Thread {
    Shop shop;

    Consumer(Shop shop) {
        this.shop = shop;
    }

    public void run() {
        try {
            shop.get();
            shop.get();
        } catch (InterruptedException e) {
            System.out.println("Consumer interrupted");
        }
    }
}

class InterThreadDemo {
    public static void main(String[] args) {
        Shop shop = new Shop();
        new Producer(shop).start();
        new Consumer(shop).start();
    }
}
```
**Output**
```text
Produced: 1
Consumed: 1
Produced: 2
Consumed: 2
```
(In real projects, use `while` instead of `if` around `wait()`. For the exam, `if` is easier to explain.)

| `wait()` | `sleep()` |
|---|---|
| A method of `Object` | A static method of `Thread` |
| **Releases** the lock | Does **not** release the lock |
| Must be inside synchronized code | Can be called anywhere |
| Woken by `notify()`/`notifyAll()` | Wakes up after the time is over |

### Deadlock
**Deadlock** happens when two threads each hold one lock and **wait forever for the lock held by the other**.

```text
Thread-1 holds Lock-A ---- wants Lock-B
Thread-2 holds Lock-B ---- wants Lock-A      -> both wait forever
```
```java
class DeadlockDemo {
    public static void main(String[] args) {
        Object lockA = new Object();
        Object lockB = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("T1 locked A");
                try { Thread.sleep(100); } catch (InterruptedException e) { }
                synchronized (lockB) {
                    System.out.println("T1 locked B");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lockB) {
                System.out.println("T2 locked B");
                try { Thread.sleep(100); } catch (InterruptedException e) { }
                synchronized (lockA) {
                    System.out.println("T2 locked A");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
```
**Output:** `T1 locked A` and `T2 locked B` print (in either order), and then **the program hangs forever**.

**How to avoid deadlock**
- Take locks **in the same order** in every thread.
- Avoid nested locks where possible.
- Use timeouts.
- Keep synchronized code short.

---

# Part D — Program Questions and Compile Errors

## Requirement → keyword cheat sheet
Use this table when a question gives a story and asks you to "choose suitable modifiers".

| If the question says... | Write |
|---|---|
| "should not create a general X object" | `abstract class X` |
| "each type calculates it differently" | `abstract` method + `@Override` in each child |
| "a rule every class must follow" / "unrelated classes" | `interface` + `implements` |
| "must never change once assigned" | `final` variable |
| "children cannot override it" | `final` method |
| "no one can extend it" | `final class` |
| "common to all objects" / "stored only once" | `static` variable |
| "call without creating an object" | `static` method |
| "count how many objects were created" | `static int count` + `count++` in the **parent** constructor |
| "not accessible from outside the class" | `private` + getter/setter |
| "class, subclasses and same package" | `protected` |
| "only the same package" | default (no keyword) |
| "initialize parent data from the child" | `super(...)` |
| "reuse another constructor of the same class" | `this(...)` |
| "print the object directly" | override `toString()` |
| "compare two objects by data" | override `equals()` (and `hashCode()`) |
| "make a copy of the object" | `implements Cloneable` + `clone()` |
| "invalid value must stop the operation" | `throw` + a custom exception class |

## Steps to write any OOP program in the exam
1. Write the **parent** first: its fields, then its constructor, then its methods (`abstract` ones end with `;`).
2. Write the **children**: `extends Parent`, `super(...)` as the first line of the constructor, and `@Override` on each overridden method.
3. Write `main`: create the objects, call the methods in the order given, and print with `System.out.println`.
4. Write the expected **output** below the program.
5. Add short comments next to important lines, for example `// final: ID never changes`.

## Common compile errors
| Error message | Reason and fix |
|---|---|
| `X is abstract; cannot be instantiated` | You wrote `new` on an abstract class. Create a child object instead. |
| `X is not abstract and does not override abstract method m()` | Implement all the abstract or interface methods. |
| `attempting to assign weaker access privileges; was public` | An interface method was implemented without `public`. |
| `cannot assign a value to final variable` | A final variable was changed. |
| `overridden method is final` | You tried to override a final method. |
| `cannot inherit from final X` | You extended a final class. |
| `illegal combination of modifiers: abstract and final` | These are opposites. Remove one. |
| `x has private access in X` | Use a getter. |
| `non-static variable cannot be referenced from a static context` | Create an object first. |
| `call to super must be first statement in constructor` | Move `super(...)` to line 1. |
| `constructor X in class X cannot be applied to given types` | Wrong arguments, or `super(args)` is missing. |
| `unreported exception X; must be caught or declared to be thrown` | It's a checked exception: add `try-catch` or `throws`. |
| `exception X has already been caught` | A parent catch was placed before a child catch. Put the child first. |
| `incompatible types: possible lossy conversion from double to int` | Add a cast: `(int) value`. |

## Logic traps (the code compiles but gives the wrong output)
1. `id = id;` in a constructor leaves the field at 0 or null. Write `this.id = id;`.
2. A counter that is not `static` always prints 1.
3. `5 / 100 * amount` gives 0, because of integer division. Write `amount * 5 / 100`.
4. Calling `run()` instead of `start()` does not create a new thread.
5. A wrong downcast compiles but throws `ClassCastException` at run time. Check with `instanceof` first.
6. Comparing objects with `==` checks the address, not the data. Use `equals()`.

---

# Part E — Two-Mark Answers and Last-Day Revision

## Unit I two-mark questions
1. **What is OOP?** A programming method based on objects, which combine data and methods. Its principles are encapsulation, inheritance, polymorphism and abstraction.
2. **Class vs object?** A class is a blueprint. An object is an instance of a class, created with `new`, that has state and behaviour.
3. **List any four Java buzzwords.** Simple, secure, portable, object-oriented, robust, platform independent, multithreaded, distributed, dynamic.
4. **What is bytecode?** The platform-independent intermediate code (the `.class` file) produced by `javac` and executed by the JVM.
5. **Why is Java platform independent?** Bytecode can run on any machine that has a JVM: "write once, run anywhere".
6. **JDK vs JRE vs JVM?** The JVM runs bytecode. JRE = JVM + libraries, to run programs. JDK = JRE + tools, to develop programs.
7. **Primitive data types?** byte, short, int, long, float, double, char, boolean.
8. **Type conversion vs casting?** Conversion is automatic widening (int → double). Casting is manual narrowing with `(type)`, and it may lose data.
9. **What is a constructor?** A special method with the class name and no return type, called automatically by `new` to initialize the object.
10. **Types of constructors?** Default (no-argument), parameterized, copy.
11. **Uses of `this`?** It refers to the current object's variable, calls a current class method, calls another constructor with `this()`, is passed as an argument, and can be returned.
12. **What is method overloading?** Several methods with the same name but different parameter lists in one class. It is compile-time polymorphism.
13. **Static variable vs instance variable?** A static variable has one copy shared by all objects. An instance variable has a separate copy for each object.
14. **Why is `main` static?** So the JVM can call it without creating an object.
15. **What is a static block?** A block that runs once when the class is loaded, before `main`, and is used to initialize static data.
16. **Uses of `final`?** A final variable is a constant, a final method cannot be overridden, and a final class cannot be inherited.
17. **List the access modifiers.** private (class), default (package), protected (package + subclasses), public (everywhere).
18. **What is inheritance? Its types?** Acquiring the properties of one class in another using `extends`. Types: single, multilevel, hierarchical (multiple and hybrid only through interfaces).
19. **Why doesn't Java support multiple inheritance with classes?** Because of the diamond problem: ambiguity about which parent's method to call.
20. **Uses of `super`?** It refers to a parent variable, calls a parent method, and calls the parent constructor with `super()`.
21. **What is method overriding?** Redefining a parent's method in the child with the same signature. It is run-time polymorphism.
22. **What is dynamic binding?** The method call is linked to the method body at run time, based on the actual object (dynamic method dispatch).
23. **What is an abstract class?** A class declared `abstract` that cannot be instantiated. It may have abstract methods (without a body) and normal methods.
24. **Can an abstract class have a constructor?** Yes. It runs when a child object is created, through `super()`.
25. **Ways to achieve abstraction?** Abstract classes, interfaces, encapsulation (private data), packages, methods.

## Unit II two-mark questions
1. **What is an interface?** A specification of method prototypes: abstract methods plus `public static final` constants. A class implements it using `implements`.
2. **Can we create an object of an interface?** No, but we can create a reference to it that points to an implementing object.
3. **What is a nested interface?** An interface declared inside a class or another interface, used as `Outer.Inner`.
4. **How does Java support multiple inheritance?** A class can implement many interfaces, and an interface can extend many interfaces.
5. **What is a marker interface?** An interface with no methods, such as `Cloneable` or `Serializable`. It gives the class a special permission.
6. **What is a package? Name its types.** A container of related classes and interfaces. Types: built-in and user-defined.
7. **How do you create and compile a package?** Write `package name;` as the first line, then compile with `javac -d . File.java`.
8. **Ways to access a package?** `import pack.ClassName;`, `import pack.*;`, or the fully qualified name `pack.ClassName`.
9. **What is CLASSPATH?** An environment variable that tells the JVM and compiler where to find classes and packages.
10. **What is an exception?** An abnormal run-time condition that disrupts the normal flow of the program.
11. **Checked vs unchecked exceptions?** Checked exceptions are checked at compile time and must be handled (IOException). Unchecked exceptions happen at run time and handling is optional (ArithmeticException).
12. **Name the exception-handling keywords.** try, catch, finally, throw, throws.
13. **throw vs throws?** `throw` throws an exception object inside a method. `throws` declares the exceptions in the method header.
14. **What is the use of finally?** It always runs, whether or not an exception occurs, and is used to close resources.
15. **Error vs exception?** An error is a serious system problem that cannot be handled (OutOfMemoryError). An exception is a program problem that can be handled.
16. **How do you create a user-defined exception?** `class MyException extends Exception { MyException(String m) { super(m); } }`, then throw it with `throw new MyException("...")`.
17. **What is rethrowing?** Catching an exception and throwing it again (`throw e;`) so the caller can also handle it.
18. **What is a thread?** A lightweight sub-process: the smallest unit of execution within a program.
19. **Two ways to create a thread?** Extend the `Thread` class, or implement the `Runnable` interface. In both cases, override `run()` and call `start()`.
20. **States of a thread?** New, Runnable, Running, Blocked/Waiting, Terminated.
21. **isAlive() vs join()?** `isAlive()` checks whether a thread is still running. `join()` waits for a thread to finish.
22. **Thread priority range?** 1 to 10 (MIN = 1, NORM = 5, MAX = 10).
23. **What is synchronization?** Allowing only one thread at a time to use a shared resource, using the `synchronized` keyword.
24. **wait() vs sleep()?** `wait()` releases the lock and needs `notify()`. `sleep()` keeps the lock and wakes up after the given time.
25. **What is deadlock?** Two threads waiting forever for each other's locks.

## Object class two-mark questions
1. **What is the Object class?** The root class of all Java classes, in `java.lang`.
2. **What does the default toString() print?** `ClassName@hashcode`. Override it to print useful data.
3. **== vs equals()?** `==` compares references. `equals()` compares content (after overriding).
4. **Why override hashCode() with equals()?** Equal objects must give the same hash code (needed by HashMap and HashSet).
5. **What is required for clone()?** The class must implement `Cloneable`. Otherwise `CloneNotSupportedException` is thrown.
6. **Shallow vs deep copy?** A shallow copy shares the inner objects. A deep copy also copies the inner objects.

## Last-day revision sheet

**Expected long answers: memory hooks**
- **Q1, abstraction (5 ways):** Abstract class (partial), Interface (100%), Encapsulation (private data), Packages (hidden classes), Methods (hidden logic). Write a definition, real-life examples, all 5 ways each with a program, an abstract class vs interface table, the advantages and a conclusion.
- **Q2, multiple inheritance:**
  - The diamond problem, and why classes fail.
  - How interfaces solve it (no bodies, so one implementation).
  - The 3 syntax forms.
  - Examples: Printable/Showable, SmartPhone, same method in two interfaces, extends + implements, interface extends interfaces.
  - The 9 advantages and a comparison table.
- **Q3, Object class:** the method table → toString (default vs overridden) → equals (== vs equals, 5 contract rules, hashCode) → clone (Cloneable, steps, shallow vs deep) → one combined program.

**Unit I in lines**
- The OOP pillars: encapsulation, inheritance, polymorphism, abstraction.
- Buzzwords: simple, secure, portable, OO, robust, architecture-neutral, multithreaded, interpreted, high performance, distributed, dynamic.
- `.java` → `javac` → `.class` (bytecode) → JVM → output.
- The 8 primitive types. Widening is automatic; narrowing needs a `(cast)`.
- Constructor: class name, no return type, not inherited. Types: default, parameterized, copy.
- `this` refers to the current object. `this()` must be the first line.
- Overloading means the same name with different parameters (compile time).
- static means one copy per class; a static block runs before main. final means a constant, no override, or no subclass.
- Access: private < default < protected < public.
- Inheritance: single, multilevel, hierarchical (multiple only through interfaces). `super()` must be the first line.
- Overriding means the same signature in the child, decided at run time (dynamic binding). It can't be done with static, final or private methods.
- An abstract class has no objects but can have constructors. An abstract method has no body.

**Unit II in lines**
- Interface: methods are public abstract, variables are public static final. A class implements many interfaces; an interface extends many.
- Package: `package x;` as the first line, `javac -d .`, and 3 ways to import. CLASSPATH tells Java where to find it.
- Exceptions:
  - The hierarchy is Throwable → Error / Exception → RuntimeException.
  - Checked exceptions are found at compile time; unchecked at run time.
  - The keywords are try, catch, finally, throw, throws.
  - Catch the child exception before the parent.
  - A custom exception extends Exception.
- Threads:
  - Create one by extending Thread or implementing Runnable, and call `start()`, not `run()`.
  - The states are New, Runnable, Running, Blocked, Dead.
  - `join()` waits, `isAlive()` checks, and priority runs from 1 to 10.
  - `synchronized` gives one thread at a time.
  - `wait`/`notify` work inside synchronized code.
  - Deadlock means a circular wait for locks.

**Final checklist**
- [ ] I can write Q1 with all 5 ways and 5 programs.
- [ ] I can write Q2 with the diamond problem, 5 examples and 9 advantages.
- [ ] I can write Q3: toString, equals with ==, clone with shallow vs deep.
- [ ] I can explain constructors, this, static, final, super and overriding with programs.
- [ ] I can define, implement and extend interfaces, and create and import packages.
- [ ] I can write try-catch-finally, throw/throws and a custom exception.
- [ ] I can create threads both ways and explain join, priority, synchronization, wait/notify and deadlock.
