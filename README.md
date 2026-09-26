# Java OOP — Complete Study Notes

These notes cover every concept you need for **Notebooks 1–6**, plus a full worked solution for each notebook. Every solution below was compiled and run; the outputs shown are the real outputs.

> **How to use:** read Part 1 once so you understand the ideas. Then work through Part 2 one notebook at a time. Try each question yourself before you look at the answer. Just before the exam, revise Part 3 (traps) and Part 4 (rapid-fire).

---

## Contents

**Part 1: Concepts**
1. [Class & Object](#1-class--object)
2. [Instance Variables & Defaults](#2-instance-variables--defaults)
3. [`static`: variable, method, block](#3-static--variable-method-block)
4. [Instance Initializer Block](#4-instance-initializer-block)
5. [Constructors, `this`, Overloading, Chaining](#5-constructors)
6. [The Shadowing Bug](#6-the-shadowing-bug)
7. [Encapsulation](#7-encapsulation)
8. [Access Modifiers & Packages](#8-access-modifiers--packages)
9. [Inheritance & `super`](#9-inheritance--super)
10. [Overriding vs Overloading](#10-method-overriding-vs-overloading)
11. [Abstract Class & Abstract Method](#11-abstract-class--abstract-method)
12. [`final`: variable, method, class](#12-final--variable-method-class)
13. [Runtime Polymorphism](#13-runtime-polymorphism)
14. [Requirement → Keyword Cheat Sheet](#14-requirement--keyword-cheat-sheet)
15. [Constructor Design Problem (Factory Methods)](#15-constructor-design-problem-factory-methods)

**Part 2: Notebook Solutions**
- [Notebook 1: SecureBank (Banking System)](#notebook-1--securebank-banking-system)
- [Notebook 2: Online Course Management](#notebook-2--online-course-management)
- [Notebook 3: AutoTest Labs (Vehicle Testing)](#notebook-3--autotest-labs-vehicle-testing)
- [Notebook 4: Online Shopping (Product/Electronics + Order/PremiumOrder)](#notebook-4--online-shopping)
- [Notebook 5: Bank Account Management](#notebook-5--bank-account-management)
- [Notebook 6: Hospital Patient Management](#notebook-6--hospital-patient-management)

**Part 3:** [Coding Traps](#part-3--coding-traps)
**Part 4:** [Rapid-Fire Revision + Viva Questions](#part-4--rapid-fire-revision--viva-questions)

---

# PART 1 — CONCEPTS

## 1. Class & Object

A **class** is a blueprint and takes no memory. An **object** is a real thing made from the class with `new`, and it does take memory.

```java
class Employee { int id; String name; }

Employee e1 = new Employee();
```

- The object lives in the **heap**. `e1` is a **reference**: it holds the object's address.
- `Employee b = e1;` gives you **2 references but only 1 object**. A change made through `b` is visible through `e1`.
- A reference that was never given an object with `new` is `null`. Calling anything on it throws a `NullPointerException`.

---

## 2. Instance Variables & Defaults

An instance variable is a field of the class. There is **one copy per object**, and Java gives it a default value automatically.

| Type | Default |
|---|---|
| `int`, `long`, `short`, `byte` | `0` |
| `double`, `float` | `0.0` |
| `char` | `'\u0000'` (blank) |
| `boolean` | `false` |
| `String` / any object | `null` |

| | Instance variable | Local variable |
|---|---|---|
| Declared | in the class | inside a method/constructor |
| Default value | **yes** | **no** → compile error if used unassigned |
| Memory | heap (inside the object) | stack |

---

## 3. `static`: variable, method, block

`static` means the member **belongs to the class, not to each object**.

### Static variable: one copy shared by all objects
```java
class Vehicle {
    static int vehicleCount = 0;   // ONE counter for the whole class
    Vehicle() { vehicleCount++; }  // every new object adds 1
}
```
If you leave out `static`, every object gets its own counter that starts at 0 and only ever reaches 1. That's why the notebooks say *"the counter should belong to the class rather than to individual objects"*.

### Static method: call it with the class name, no object needed
```java
static void getVehicleCount() {
    System.out.println("Total Vehicles Created: " + vehicleCount);
}

Vehicle.getVehicleCount();   // no object required
```
**Rules:**
- A static method **cannot** use instance variables or `this` directly, because there's no object. Error: `non-static variable x cannot be referenced from a static context`.
- It **can** use static variables and other static methods.
- `main` is static, which is why you have to create objects inside `main` before you can use their fields.

### Static block: runs once, when the class is first loaded
```java
static { System.out.println("Class loaded"); }
```

| | `static { }` | `{ }` (instance block) |
|---|---|---|
| Runs | once, when the class loads | every time an object is created |
| Can use instance vars / `this` | ❌ | ✅ |

### Why `Account.getAccountCount()` works (a common exam question)
The method is `static`, so it belongs to the class itself and is loaded together with it. Calling it doesn't need an object, and the counter it reads is also `static` (one shared copy). So the class name is enough.

---

## 4. Instance Initializer Block

An instance initializer is an unnamed `{ }` block inside the class body.

```java
class Employee {
    int id;
    { id = 100; }          // instance initializer
    Employee() { }
}
```
1. It runs **once per object**, for every object.
2. Order: **default values → field initializers and `{ }` blocks (top to bottom) → constructor body.**
3. It runs **before the constructor body**, so the constructor can overwrite what it set.
4. You can have several blocks. They run in the order they're written.
5. It has no name, no return type and no parameters.
6. It can use `this` and instance variables.
7. With `this(...)` chaining it still runs **only once** per object.

**Use it for** code that every constructor needs. You write it once instead of repeating it in each constructor.

```java
int x = 1;      // step 1
{ x = 2; }      // step 2
int y = x + 5;  // step 3  -> y = 7
A() { x = 3; }  // step 4 (constructor body runs last)
// final values: x = 3, y = 7
```

---

## 5. Constructors

### Rules
1. The name is the same as the class name.
2. **No return type**, not even `void`.
3. It runs automatically when you call `new`.
4. It can be overloaded and it can be `private`. It **cannot** be `static`, `final` or `abstract`.
5. `void Employee(){}` is a **method**, not a constructor.

### Default constructor
The compiler adds a no-arg constructor **only if you wrote zero constructors**. As soon as you write any constructor, the free one is gone. If you still need `new X()`, you have to write the no-arg constructor yourself.

### Types
- **No-arg:** `Employee() { }`
- **Parameterized:** `Employee(int id, String name) { ... }`
- **Copy:** `Employee(Employee o) { this.id = o.id; this.name = o.name; }`

### `this`
`this` refers to the current object.
```java
Employee(int id, String name) {
    this.id = id;      // left side = my field, right side = the parameter
    this.name = name;
}
```
You need it here because the parameter has the same name as the field, and inside the constructor **the parameter wins** (this is called shadowing). `this` cannot be used in a `static` method.

### Constructor overloading
Java picks the constructor by its **signature: the number, types and order of the parameters**. Parameter **names and meaning are ignored**.
```java
Employee(int id, String name)         // OK
Employee(int id, String department)   // ERROR: already defined, same (int, String)
```
Java tries matches in this order: **exact match → widening (int→long→double) → boxing → varargs**.
So `new Patient(301, 5000)` picks `(int,int)` over `(int,double)`. Write `5000.0` if you want the double version.

### Chaining with `this(...)` (same class)
- It must be the **first statement** in the constructor.
- Only one `this(...)` call per constructor.
- No cycles, or you get a `recursive constructor invocation` error.

```java
Product(String name, double price) {
    this(name, price, 10);        // reuse the bigger constructor, default stock = 10
}
Product(String name, double price, int stock) {
    this.productName = name; this.price = price; this.stock = stock;
}
```

**Output order of chained constructors: the deepest one prints first (the reverse of the call order).**
```
Product() -> this(0,"Unknown") -> this(0,"Unknown",0)
new Product()  prints  C  B  A
```
The called constructor finishes its whole body before control comes back, so your own `println` runs **last**.

> ⚠️ Classic exams (Java 8–21) treat a statement before `this(...)`/`super(...)` as a compile error. Java 25 relaxes this a little, but always put `this(...)`/`super(...)` on the first line.

---

## 6. The Shadowing Bug

This one comes up again and again.

```java
Employee(int employeeId, String name) {
    employeeId = employeeId;   // WRONG: prints 0 and null
    name = name;
}
```
- **Why it compiles:** the parameter shadows the field, so both sides of `employeeId = employeeId` mean the *parameter*. Assigning a variable to itself is legal. It's a logic error, not a syntax error.
- **Why the values aren't stored:** the field is never touched, so it keeps its default (`0`, `null`).
- **Fix:** `this.employeeId = employeeId;`
- In the BankAccount version it also compiles because an `int` argument **widens** to `long`/`double`.

---

## 7. Encapsulation

Encapsulation means making fields `private` and giving controlled access through `public` getters and setters.

```java
private double salary;

public double getSalary() { return salary; }

public void setSalary(double salary) {
    if (salary < 0) return;        // validation
    this.salary = salary;
}
```
- The getter for a boolean field is named `isX()`, for example `isPermanent()`.
- **Why `private`?** Without it, anyone can write `e.salary = -50000;` and nothing stops them. A setter lets you **validate** the value first.
- **Benefits:** data hiding, validation, the internals can change without breaking other code, and you get read-only fields (getter only, no setter).
- Notebook 6's `updateMedicalRecord()` is exactly this: the record is `private`, and the **only** way to change it is through a method that you control.

---

## 8. Access Modifiers & Packages

| Modifier | Same class | Same package | Subclass (other package) | Anywhere |
|---|:-:|:-:|:-:|:-:|
| `private` | ✅ | ❌ | ❌ | ❌ |
| *(default — no keyword)* | ✅ | ✅ | ❌ | ❌ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| `public` | ✅ | ✅ | ✅ | ✅ |

**How to read the requirement wording:**
- *"not directly accessible from outside the class"* → **`private`** (and give a getter or update method)
- *"accessible only to classes within the same package"* → **default** (write no modifier)
- *"accessible within the class, its subclasses, and the same package"* → **`protected`**
- *"accessible from anywhere"* → **`public`**

**Packages**
```java
package hospital;          // must be the FIRST line of the file
```
- A package is a folder that groups related classes. Default access means "visible to classes in this folder only".
- To compile and run: `javac -d . HospitalDemo.java` then `java hospital.HospitalDemo`.
- Only **one `public` class per file**, and the file name must match it.
- Class-level modifiers: a top-level class can only be `public` or default (never `private` or `protected`).

---

## 9. Inheritance & `super`

```java
class Electronics extends Product { ... }
```
- The child gets all the **non-private** fields and methods of the parent. This is an **"is-a"** relationship: Electronics *is a* Product.
- Java classes support **single inheritance only**: one `extends` per class.
- **Constructors are NOT inherited.** The child has to write its own and call the parent's with `super(...)`.

### `super(...)`
```java
Electronics(String name, double price, String brand) {
    super(name, price);          // runs Product(String,double) FIRST
    this.brand = brand;
    this.warrantyYears = 1;      // default values
    this.discountPercentage = 5;
}
```
- It must be the **first statement**. You can have `this(...)` or `super(...)`, never both.
- If you don't write it, Java inserts `super()` (no-arg) for you. If the parent has no no-arg constructor, you get a compile error. That's why every child in the notebooks calls `super(id, brand, model)` explicitly.
- **Construction order: the parent is always built first, then the child.**
- `super.method()` calls the parent's version of a method, which is useful inside an override.

### Static counter + inheritance
Put `count++` in the **parent constructor**. Every child constructor calls `super(...)`, so every object of every child type (SavingsAccount, StudentAccount, FlyingCar…) is counted automatically. That's why the "Challenge" classes need no change to the parent.

---

## 10. Method Overriding vs Overloading

| | Overloading | Overriding |
|---|---|---|
| Where | same class (or inherited) | child class redefines a parent method |
| Name | same | same |
| Parameters | **must differ** | **must be the same** |
| Return type | anything | same (or a subtype) |
| Decided at | **compile time** | **run time** (by the actual object) |
| Also called | static polymorphism | dynamic / runtime polymorphism |

```java
@Override
double calculateInterest() { return balance * 0.04; }
```
- `@Override` asks the compiler to check that you really are overriding. If you misspell the method name, you get a compile error instead of a silent new method.
- **You cannot override** `final` methods, `static` methods (those are hidden, not overridden), `private` methods (they're invisible to the child) or constructors.
- An override **cannot reduce visibility**. If the parent method is `public`, the child's must be `public` too.
- Notebook 4 says *"do not use overriding"*, so give the child **new method names** (`showElectronicsDetails()`, `showPremiumOrder()`) and call the inherited `showProduct()` / `showOrder()` inside them.

---

## 11. Abstract Class & Abstract Method

```java
abstract class Account {
    abstract double calculateInterest();   // no body, ends with ;
}
```
**Abstract class**
- It **cannot be instantiated**: `new Account(...)` gives `Account is abstract; cannot be instantiated`.
- It *can* still have constructors (called through `super(...)`), fields, normal methods, static methods and final methods.
- Use it when the requirement says *"general idea"*, *"should not create a generic object"* or *"serve as a base class"*.

**Abstract method**
- It declares **what** must exist but not **how**: no body.
- If a class has an abstract method, the class **must** be abstract.
- Every concrete child **must** override it, or you get the error `X is not abstract and does not override abstract method ...`. That forced override is the whole point: each account or vehicle type has to supply its own logic.
- Use it when the requirement says *"define that the operation must exist but don't give a common implementation"* or *"each type calculates differently"*.

**Illegal combinations:** `abstract final`, `abstract static`, `abstract private`. An abstract method or class exists *to be* overridden or extended, and those modifiers forbid exactly that.

---

## 12. `final`: variable, method, class

| Used on | Meaning | Error if you break it |
|---|---|---|
| **variable** | assign once, never change | `cannot assign a value to final variable accountNumber` |
| **method** | inherited but **cannot be overridden** | `verifyKYC() in X cannot override verifyKYC() in Account` (overridden method is final) |
| **class** | **cannot be extended** | `cannot inherit from final BankPolicy` |

```java
final long accountNumber;         // a "blank final": assigned in the constructor, then locked
final void verifyKYC() { ... }    // same KYC for everyone, no child can replace it
final class BankPolicy { ... }    // nobody can write "extends BankPolicy"
```
- A `final` field must be assigned **exactly once**: either where it's declared or in **every** constructor.
- `static final` makes a constant, e.g. `static final String HOSPITAL_NAME = "City Care";`
- A final class **can still be instantiated**, since `new BankPolicy()` is fine. It just can't have children.
- `final` on an object reference locks the *reference*, not the object's contents.

---

## 13. Runtime Polymorphism

```java
Account a1 = new SavingsAccount(20001, "Asha", 40000, 5000);   // upcasting
a1.calculateInterest();   // runs SavingsAccount's version
```
- **Reference type** (`Account`) decides **what you are allowed to call**. This is checked at compile time.
- **Object type** (`SavingsAccount`) decides **which version runs**. This is decided at run time and is called *dynamic method dispatch*.
- So `a1.withdraw()` is OK because `Account` has it. `a1.refuel()` on a `Vehicle` reference is a **compile error**, because `Vehicle` has no `refuel`, even though the object might be a PetrolCar.

```java
Account[] accounts = { a1, a2, a3 };
for (Account a : accounts) {
    a.verifyKYC();          // final, so always Account's version
    a.calculateInterest();  // a different version for each object
    a.displayDetails();
}
```
**Why it's useful:** one loop and one reference type handle every account type, including ones added later (StudentAccount, FlyingCar), with **no changes** to the loop or to the parent class.

---

## 14. Requirement → Keyword Cheat Sheet

This is the most useful table for Notebooks 1, 2, 3, 5 and 6.

| Requirement wording | Keyword |
|---|---|
| "should not be possible to create a general X object" / "base class, not used to create objects" | `abstract class` |
| "declare the operation but not provide implementation" / "each type calculates differently" | `abstract` method + `@Override` in children |
| "must never change once assigned" (ID, account number) | `final` variable |
| "children inherit it but cannot override / replace it" | `final` method |
| "no one may create a subclass" / "should not be extended / inherited further" | `final class` |
| "belongs to the class, not objects" / "shared by all" / "stored only once" | `static` variable |
| "callable without creating an object" / "call using class name" | `static` method |
| "count how many objects created" | `static int count` + `count++` in the **parent constructor** + `static` getter |
| "not directly accessible from outside the class" | `private` + getter / update method |
| "class + subclasses + same package" | `protected` |
| "only classes within the same package" | default (no modifier) |
| "same reference works with different objects" | runtime polymorphism (`Parent p = new Child()`) |
| "reuse parent initialization in child" | `super(...)` |
| "reuse another constructor of the same class" | `this(...)` |

---

## 15. Constructor Design Problem (Factory Methods)

Suppose you want both `(id, name)` and `(id, department)`. Both are `(int, String)`, so they **cannot coexist**: overloading looks at types, not meaning.

**The fix is static factory methods.** Methods can have different names; constructors can't.
```java
static Employee withName(int id, String name)      { return new Employee(id, name); }
static Employee withDepartment(int id, String dep) { return new Employee(id, "Not Assigned", dep); }
```

| Case | Types | Result |
|---|---|---|
| Employee (id,name) vs (id,dept) | (int,String) both | ✘ → factory |
| Order (id,restaurant,item) vs (id,customer,item) | (int,String,String) | ✘ → factory |
| Hotel (id,guest,roomType) vs (id,guest,company) | (int,String,String) | ✘ → factory |
| Vehicle packageAmount vs securityDeposit | (int,String,String,double) | ✘ → factory |
| Patient (id,age) vs (id,deposit) | (int,int) vs (int,double) | ✔, but an int literal picks `age` |
| Course (id,name) / (id,name,course) / (id,name,sem) | all differ | ✔ fine |

### Recipe for any constructor question
1. Make the fields `private`.
2. The **longest constructor is the master**. It does all the `this.f = f;` assignments.
3. Every other constructor **delegates** with `this(...)` (or `super(...)` in a child) and passes that case's defaults.
4. The no-arg constructor passes the "Not Assigned" defaults.
5. "Automatically admitted/confirmed/booked" means pass `true` down the chain.
6. For a new requirement: different parameter types → new constructor; same types → **static factory**.
7. Add getters/setters and a `display()` method.

---

# PART 2 — NOTEBOOK SOLUTIONS

## Notebook 1 — SecureBank (Banking System)

### Requirement → keyword mapping
| Requirement | What we used |
|---|---|
| `new Account()` not allowed | `abstract class Account` |
| Account number never changes | `final long accountNumber` |
| Interest differs per type, parent defines it must exist | `abstract double calculateInterest();` |
| Same KYC for all, children can't replace it | `final void verifyKYC()` |
| Count all accounts, `Account.getAccountCount()` | `static int accountCount` + `static` method, `++` in the constructor |
| Nobody can extend `BankPolicy` | `final class BankPolicy` |

### Changes to the given Part A skeleton
- `long accountNumber` → `final long accountNumber`
- `int accountCount = 0` → `static int accountCount = 0`
- constructor: add `accountCount++;`
- `void verifyKYC()` → `final void verifyKYC()`
- `void calculateInterest();` → `abstract double calculateInterest();` (a plain `abstract void` is also acceptable)
- `void getAccountCount()` → `static void getAccountCount()`

### Full code (`SecureBank.java`)
```java
abstract class Account {

    final long accountNumber;                  // final variable: can never change
    String accountHolderName;
    double balance;

    private static int accountCount = 0;       // static: one counter for the whole class

    Account(long accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        accountCount++;                        // every child constructor comes through here
    }

    void deposit(double amount) {
        balance = balance + amount;
        System.out.println("Deposited " + amount + " | Balance: " + balance);
    }

    void withdraw(double amount) {
        balance = balance - amount;
    }

    final void verifyKYC() {                   // final method: inherited, cannot be overridden
        System.out.println("KYC Verified");
    }

    abstract double calculateInterest();       // abstract method: no body

    void displayDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
    }

    static void getAccountCount() {            // static method: Account.getAccountCount()
        System.out.println("Total Accounts Created: " + accountCount);
    }
}

class SavingsAccount extends Account {
    double minimumBalance;

    SavingsAccount(long accountNumber, String name, double balance, double minimumBalance) {
        super(accountNumber, name, balance);
        this.minimumBalance = minimumBalance;
    }

    @Override
    void withdraw(double amount) {
        if (balance - amount < minimumBalance) {
            System.out.println("Withdrawal of " + amount + " rejected: minimum balance " + minimumBalance + " required");
        } else {
            balance = balance - amount;
            System.out.println("Withdrew " + amount + " | Balance: " + balance);
        }
    }

    @Override
    double calculateInterest() {
        double interest = balance * 0.04;
        System.out.println("Savings interest (4%): " + interest);
        return interest;
    }
}

class CurrentAccount extends Account {
    double overdraftLimit;

    CurrentAccount(long accountNumber, String name, double balance, double overdraftLimit) {
        super(accountNumber, name, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    void withdraw(double amount) {
        if (balance - amount < -overdraftLimit) {
            System.out.println("Withdrawal of " + amount + " rejected: overdraft limit " + overdraftLimit + " exceeded");
        } else {
            balance = balance - amount;
            System.out.println("Withdrew " + amount + " | Balance: " + balance);
        }
    }

    @Override
    double calculateInterest() {
        System.out.println("Current account interest (0%): 0.0");
        return 0;
    }
}

class FixedDepositAccount extends Account {
    int depositPeriod;                 // in years
    double interestRate = 7;

    FixedDepositAccount(long accountNumber, String name, double balance, int depositPeriod) {
        super(accountNumber, name, balance);
        this.depositPeriod = depositPeriod;
    }

    @Override
    void withdraw(double amount) {
        System.out.println("Withdrawal not allowed before maturity (" + depositPeriod + " years)");
    }

    @Override
    double calculateInterest() {
        double interest = balance * interestRate / 100;
        System.out.println("FD interest (7%): " + interest);
        return interest;
    }
}

class StudentAccount extends Account {       // Challenge: added without touching Account
    double dailyWithdrawalLimit;

    StudentAccount(long accountNumber, String name, double balance, double dailyWithdrawalLimit) {
        super(accountNumber, name, balance);
        this.dailyWithdrawalLimit = dailyWithdrawalLimit;
    }

    @Override
    void withdraw(double amount) {
        if (amount > dailyWithdrawalLimit) {
            System.out.println("Withdrawal of " + amount + " rejected: daily limit " + dailyWithdrawalLimit);
        } else {
            balance = balance - amount;       // no minimum balance
            System.out.println("Withdrew " + amount + " | Balance: " + balance);
        }
    }

    @Override
    double calculateInterest() {
        double interest = balance * 0.03;
        System.out.println("Student interest (3%): " + interest);
        return interest;
    }
}

final class BankPolicy {                       // final class: no subclasses
    int minimumKycAge = 18;
    double maxDailyWithdrawal = 100000;
    String bankCode = "SB001";
}

public class SecureBank {
    public static void main(String[] args) {
        System.out.println("===== Part C =====");
        SavingsAccount s = new SavingsAccount(10001, "Rahul", 50000, 5000);
        CurrentAccount c = new CurrentAccount(10002, "Anita", 30000, 20000);
        FixedDepositAccount f = new FixedDepositAccount(10003, "Ramesh", 100000, 3);

        s.deposit(10000);      // 60000
        s.withdraw(20000);     // 40000
        s.withdraw(38000);     // 2000 < 5000 -> rejected
        c.deposit(5000);       // 35000
        c.withdraw(45000);     // -10000, inside 20000 overdraft
        f.withdraw(1000);      // blocked

        s.calculateInterest();
        c.calculateInterest();
        f.calculateInterest();

        s.displayDetails();
        c.displayDetails();
        f.displayDetails();

        Account.getAccountCount();

        System.out.println("===== Part E =====");
        Account a1 = new SavingsAccount(20001, "Asha", 40000, 5000);
        Account a2 = new CurrentAccount(20002, "Vikram", 25000, 15000);
        Account a3 = new FixedDepositAccount(20003, "Neha", 80000, 5);

        a1.calculateInterest();
        a2.calculateInterest();
        a3.calculateInterest();

        Account[] accounts = new Account[3];
        accounts[0] = a1;
        accounts[1] = a2;
        accounts[2] = a3;

        for (Account a : accounts) {
            a.verifyKYC();
            a.calculateInterest();
            a.displayDetails();
        }

        System.out.println("===== Challenge =====");
        Account st = new StudentAccount(30001, "Kavya", 8000, 2000);
        st.withdraw(3000);
        st.withdraw(1500);
        st.calculateInterest();
        Account.getAccountCount();
    }
}
```

### Output
```
===== Part C =====
Deposited 10000.0 | Balance: 60000.0
Withdrew 20000.0 | Balance: 40000.0
Withdrawal of 38000.0 rejected: minimum balance 5000.0 required
Deposited 5000.0 | Balance: 35000.0
Withdrew 45000.0 | Balance: -10000.0
Withdrawal not allowed before maturity (3 years)
Savings interest (4%): 1600.0
Current account interest (0%): 0.0
FD interest (7%): 7000.0
Account Number: 10001
Account Holder: Rahul
Balance: 40000.0
Account Number: 10002
Account Holder: Anita
Balance: -10000.0
Account Number: 10003
Account Holder: Ramesh
Balance: 100000.0
Total Accounts Created: 3
===== Part E =====
Savings interest (4%): 1600.0
Current account interest (0%): 0.0
FD interest (7%): 5600.0
KYC Verified
Savings interest (4%): 1600.0
Account Number: 20001
Account Holder: Asha
Balance: 40000.0
KYC Verified
Current account interest (0%): 0.0
Account Number: 20002
Account Holder: Vikram
Balance: 25000.0
KYC Verified
FD interest (7%): 5600.0
Account Number: 20003
Account Holder: Neha
Balance: 80000.0
===== Challenge =====
Withdrawal of 3000.0 rejected: daily limit 2000.0
Withdrew 1500.0 | Balance: 6500.0
Student interest (3%): 195.0
Total Accounts Created: 7
```

### Walking through the logic
- **Savings:** 50000 + 10000 = 60000, minus 20000 = **40000**. Withdrawing 38000 would leave 2000, which is below the 5000 minimum, so it's **rejected**. Interest = 40000 × 4% = **1600**.
- **Current:** 30000 + 5000 = 35000. Withdrawing 45000 gives **−10000**. The overdraft limit is 20000, so the lowest allowed balance is −20000 and −10000 is allowed. The check is `balance - amount < -overdraftLimit` → reject.
- **FD:** withdrawal is blocked. Interest = 100000 × 7% = **7000**.
- The count after Part C is **3**. After Part E (+3) and the Challenge (+1) it's **7**. The count is static and goes up in the parent constructor, so **StudentAccount is counted without changing `Account`**.

### Part D answers
1. **`Account a = new Account(10004, "John", 10000);`** → **compile error**: `Account is abstract; cannot be instantiated`. A generic account has no defined interest rule. The bank wants only concrete types to exist, and `abstract` enforces that.
2. **`s.accountNumber = 50000;`** → **compile error**: `cannot assign a value to final variable accountNumber`. An account number identifies the account for its whole lifetime. If it could change, transactions could land in the wrong account.
3. **Overriding `verifyKYC()` in SavingsAccount** → **not allowed**: `verifyKYC() in SavingsAccount cannot override verifyKYC() in Account; overridden method is final`. KYC is a regulatory process and has to be identical for every account.
4. **`class SpecialPolicy extends BankPolicy {}`** → **does not compile**: `cannot inherit from final BankPolicy`. A subclass could change policy values or behaviour, so `final` blocks it. (Creating `new BankPolicy()` is still fine.)
5. **`Account.getAccountCount()`** works because the method is `static`: it belongs to the class, not to an object, and reads a static counter. Also note it can be called even though `Account` is abstract, because static members don't need an object.

### Concept identification
| Concept | Where in the program |
|---|---|
| Inheritance | `SavingsAccount/CurrentAccount/FixedDepositAccount/StudentAccount extends Account` |
| Abstract class | `abstract class Account`: blocks `new Account()` |
| Abstract method | `abstract double calculateInterest();` |
| Method overriding | `withdraw()` and `calculateInterest()` in each child, marked `@Override` |
| Static variable | `static int accountCount` |
| Static method | `static void getAccountCount()` |
| Final variable | `final long accountNumber` |
| Final method | `final void verifyKYC()` |
| Final class | `final class BankPolicy` |
| Runtime polymorphism | `Account a1 = new SavingsAccount(...)` and the loop over `Account[]`, where each object runs its own `calculateInterest()` |

---

## Notebook 2 — Online Course Management

### ⚠️ The trick in this question
Requirement 3 says *"every course calculates its fee differently"*, so `Course` must be **abstract**. Requirement 5 says *"a course should not be inherited further"*, which means **final**. But **one class can't be both**: `abstract final` gives `illegal combination of modifiers: abstract and final`, because abstract *needs* children and final *forbids* them.

**Correct design:** `abstract class Course` holds the rules. The concrete course type, `final class JavaCourse extends Course`, is the one that can't be inherited *further*.

| # | Requirement | Keyword |
|---|---|---|
| 1 | Course ID never changes | `final int courseId` |
| 2 | College name common to all | `static String collegeName` |
| 3 | Fee calculation differs per course | `abstract double calculateFee()` |
| 4 | Display course ID without an object | `static void displayCourseId(int id)` |
| 5 | Not inherited further | `final class JavaCourse` |

### Code (`CourseDemo.java`)
```java
abstract class Course {
    final int courseId;                          // 1. never changes after creation
    String courseName;
    static String collegeName = "ABC Engineering College";   // 2. one copy for all courses

    Course(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    abstract double calculateFee();              // 3. every course type calculates differently

    static void displayCourseId(int id) {        // 4. callable without an object
        System.out.println("Course ID: " + id);
    }
}

final class JavaCourse extends Course {         // 5. cannot be inherited further
    int durationMonths;
    double feePerMonth;

    JavaCourse(int courseId, String courseName, int durationMonths, double feePerMonth) {
        super(courseId, courseName);
        this.durationMonths = durationMonths;
        this.feePerMonth = feePerMonth;
    }

    @Override
    double calculateFee() {
        return durationMonths * feePerMonth;
    }
}

public class CourseDemo {
    public static void main(String[] args) {
        JavaCourse j = new JavaCourse(501, "Core Java", 3, 5000);

        System.out.println("College: " + Course.collegeName);
        Course.displayCourseId(j.courseId);
        System.out.println("Course Name: " + j.courseName);
        System.out.println("Java Course Fee: " + j.calculateFee());
    }
}
```

### Output
```
College: ABC Engineering College
Course ID: 501
Course Name: Core Java
Java Course Fee: 15000.0
```

**Why does `displayCourseId` take a parameter?** A static method has no object, so it has no `this.courseId` to read. The ID has to be passed in. (If it tried to read `courseId` directly, you'd get `non-static variable courseId cannot be referenced from a static context`.)

---

## Notebook 3 — AutoTest Labs (Vehicle Testing)

This has the same structure as Notebook 1: `Vehicle` = `Account`, `start()` = `verifyKYC()`, `accelerate()` = `calculateInterest()`, `SecurityConfiguration` = `BankPolicy`.

### Changes to the Part A skeleton
- `int vehicleId` → `final int vehicleId`
- `int vehicleCount = 0` → `static int vehicleCount = 0`, plus `vehicleCount++;` in the constructor
- `void start()` → `final void start()`
- `void accelerate();` → `abstract void accelerate();`
- `void getVehicleCount()` → `static void getVehicleCount()`

### Code (`AutoTestLabs.java`)
```java
abstract class Vehicle {

    final int vehicleId;                 // final: can never change

    String brand;
    String model;
    int speed;

    static int vehicleCount = 0;         // static: shared by all objects

    Vehicle(int vehicleId, String brand, String model) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        vehicleCount++;
    }

    final void start() {                 // final: children cannot override
        System.out.println("Vehicle safety check completed");
        System.out.println("Vehicle started");
    }

    void stop() {
        speed = 0;
        System.out.println("Vehicle stopped");
    }

    abstract void accelerate();          // abstract: every child must implement

    void displayDetails() {
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println(brand + " " + model + " : " + speed + " km/h");
    }

    static void getVehicleCount() {      // static: Vehicle.getVehicleCount()
        System.out.println("Total Vehicles Created: " + vehicleCount);
    }
}

class PetrolCar extends Vehicle {
    double fuelLevel;

    PetrolCar(int vehicleId, String brand, String model) {
        super(vehicleId, brand, model);
    }

    void refuel(double litres) {
        fuelLevel = fuelLevel + litres;
        System.out.println("Fuel level: " + fuelLevel + " litres");
    }

    @Override
    void accelerate() {
        speed = speed + 10;
    }
}

class ElectricCar extends Vehicle {
    int batteryLevel;

    ElectricCar(int vehicleId, String brand, String model) {
        super(vehicleId, brand, model);
    }

    void chargeBattery(int percentage) {
        batteryLevel = Math.min(100, batteryLevel + percentage);
        System.out.println("Battery level: " + batteryLevel + "%");
    }

    @Override
    void accelerate() {
        speed = speed + 20;
    }
}

class Bike extends Vehicle {
    boolean helmetAvailable = true;

    Bike(int vehicleId, String brand, String model) {
        super(vehicleId, brand, model);
    }

    @Override
    void accelerate() {
        speed = speed + 5;
    }
}

class FlyingCar extends Vehicle {       // Challenge: added without changing Vehicle
    int altitude;

    FlyingCar(int vehicleId, String brand, String model) {
        super(vehicleId, brand, model);
    }

    void takeOff(int altitude) {
        this.altitude = altitude;
        System.out.println("Took off to " + altitude + " m");
    }

    void land() {
        altitude = 0;
        System.out.println("Landed");
    }

    @Override
    void accelerate() {
        speed = speed + 50;
    }
}

final class SecurityConfiguration {     // final: nobody can extend it
    int maximumSpeedLimit = 200;
    String testingCenterCode = "ATL-01";
    String safetyStandard = "ISO 26262";
}

public class AutoTestLabs {
    public static void main(String[] args) {
        System.out.println("===== Part C =====");
        PetrolCar p = new PetrolCar(101, "Toyota", "Fortuner");
        ElectricCar e = new ElectricCar(102, "Tesla", "Model 3");
        Bike b = new Bike(103, "Yamaha", "R15");

        p.start();
        e.start();
        b.start();

        for (int i = 0; i < 2; i++) p.accelerate();   // 20
        for (int i = 0; i < 3; i++) e.accelerate();   // 60
        for (int i = 0; i < 4; i++) b.accelerate();   // 20

        p.displayDetails();
        e.displayDetails();
        b.displayDetails();

        Vehicle.getVehicleCount();

        System.out.println("===== Part E =====");
        Vehicle v1 = new PetrolCar(201, "Honda", "City");
        Vehicle v2 = new ElectricCar(202, "Tata", "Nexon EV");
        Vehicle v3 = new Bike(203, "Royal Enfield", "Hunter");

        v1.accelerate();   // PetrolCar version  -> 10
        v2.accelerate();   // ElectricCar version -> 20
        v3.accelerate();   // Bike version        -> 5

        Vehicle[] vehicles = new Vehicle[3];
        vehicles[0] = v1;
        vehicles[1] = v2;
        vehicles[2] = v3;

        for (Vehicle v : vehicles) {
            v.start();
            v.accelerate();
            v.displayDetails();
        }

        System.out.println("===== Challenge =====");
        FlyingCar fc = new FlyingCar(301, "Alef", "Model A");
        fc.start();
        fc.takeOff(500);
        fc.accelerate();
        fc.land();
        fc.displayDetails();
        Vehicle.getVehicleCount();
    }
}
```

### Output
```
===== Part C =====
Vehicle safety check completed
Vehicle started
Vehicle safety check completed
Vehicle started
Vehicle safety check completed
Vehicle started
Vehicle ID: 101
Brand: Toyota
Model: Fortuner
Toyota Fortuner : 20 km/h
Vehicle ID: 102
Brand: Tesla
Model: Model 3
Tesla Model 3 : 60 km/h
Vehicle ID: 103
Brand: Yamaha
Model: R15
Yamaha R15 : 20 km/h
Total Vehicles Created: 3
===== Part E =====
Vehicle safety check completed
Vehicle started
Vehicle ID: 201
Brand: Honda
Model: City
Honda City : 20 km/h
Vehicle safety check completed
Vehicle started
Vehicle ID: 202
Brand: Tata
Model: Nexon EV
Tata Nexon EV : 40 km/h
Vehicle safety check completed
Vehicle started
Vehicle ID: 203
Brand: Royal Enfield
Model: Hunter
Royal Enfield Hunter : 10 km/h
===== Challenge =====
Vehicle safety check completed
Vehicle started
Took off to 500 m
Landed
Vehicle ID: 301
Brand: Alef
Model: Model A
Alef Model A : 50 km/h
Total Vehicles Created: 7
```

### Walking through the logic
- **Part C:** Petrol 2×10 = **20**, Electric 3×20 = **60**, Bike 4×5 = **20**. These match the expected speeds.
- **Part E:** each vehicle is accelerated **twice**, once in the individual calls and once inside the loop. So Honda = 2×10 = **20**, Nexon = 2×20 = **40**, Hunter = 2×5 = **10**. Don't expect 10/20/5 here.
- The count is 3 after Part C and **7** at the end (3 + 3 + FlyingCar).
- `v1.refuel(10)` would **not compile**: the reference type `Vehicle` has no `refuel`. You'd need `((PetrolCar) v1).refuel(10);`.

### Part D answers
1. **`new Vehicle(104,"ABC","XYZ")`** → `Vehicle is abstract; cannot be instantiated`. "Vehicle" is only a general idea; it has no `accelerate()` body.
2. **`p.vehicleId = 999;`** → `cannot assign a value to final variable vehicleId`. The ID must stay the same so test records match the correct vehicle.
3. **Overriding `start()` in ElectricCar** → not allowed: `start() in ElectricCar cannot override start() in Vehicle; overridden method is final`. The safety-check procedure must be the same for every vehicle.
4. **`extends SecurityConfiguration`** → `cannot inherit from final SecurityConfiguration`. The security settings can't be altered through a subclass.
5. **`Vehicle.getVehicleCount()`** → it's `static`, so it belongs to the class. It needs no object and reads the shared static counter.

**Concept identification:** the same table as Notebook 1, with Vehicle names: `extends Vehicle`, `abstract class Vehicle`, `abstract void accelerate()`, `@Override accelerate()`, `static int vehicleCount`, `static getVehicleCount()`, `final int vehicleId`, `final void start()`, `final class SecurityConfiguration`, and the `Vehicle[]` loop for runtime polymorphism.

---

## Notebook 4 — Online Shopping

The rules here are **no overriding, no arrays, no abstract/interface**. This notebook tests **inheritance + constructor overloading + `super(...)`**.

### Q1 — Product / Electronics

**Key ideas**
- Put the default in **one place**: `Product(name, price)` → `this(name, price, 10)`.
- Each child constructor **must** call `super(...)` first, then set its own fields and defaults (`warrantyYears = 1`, `discountPercentage = 5`).
- `purchase()` checks the stock **before** changing anything, and prints the bill **before** reducing the stock.

```java
class Product {
    String productName;
    double price;
    int stock;

    Product(String productName, double price) {
        this(productName, price, 10);            // default stock = 10
    }

    Product(String productName, double price, int stock) {
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }

    void showProduct() {
        System.out.println("Product Name: " + productName);
        System.out.println("Price: " + price);
        System.out.println("Stock: " + stock);
    }

    double calculateTotal(int quantity) {
        return price * quantity;
    }

    boolean checkStock(int quantity) {
        return quantity <= stock;
    }

    void updateStock(int quantity) {
        stock = stock - quantity;
    }
}

class Electronics extends Product {
    String brand;
    int warrantyYears;
    double discountPercentage;

    Electronics(String productName, double price, String brand) {
        super(productName, price);               // stock = 10
        this.brand = brand;
        this.warrantyYears = 1;
        this.discountPercentage = 5;
    }

    Electronics(String productName, double price, int stock,
                String brand, int warrantyYears) {
        super(productName, price, stock);
        this.brand = brand;
        this.warrantyYears = warrantyYears;
        this.discountPercentage = 5;
    }

    Electronics(String productName, double price, int stock,
                String brand, int warrantyYears,
                double discountPercentage) {
        super(productName, price, stock);
        this.brand = brand;
        this.warrantyYears = warrantyYears;
        this.discountPercentage = discountPercentage;
    }

    void showElectronicsDetails() {
        showProduct();                           // inherited method
        System.out.println("Brand: " + brand);
        System.out.println("Warranty: " + warrantyYears + " years");
        System.out.println("Discount: " + discountPercentage + "%");
    }

    double calculateDiscount(int quantity) {
        return calculateTotal(quantity) * discountPercentage / 100;
    }

    double calculateFinalAmount(int quantity) {
        return calculateTotal(quantity) - calculateDiscount(quantity);
    }

    void purchase(int quantity) {
        if (checkStock(quantity)) {
            System.out.println();
            System.out.println("Quantity Purchased: " + quantity);
            System.out.println("Total Amount: " + calculateTotal(quantity));
            System.out.println("Discount Amount: " + calculateDiscount(quantity));
            System.out.println("Final Amount: " + calculateFinalAmount(quantity));
            updateStock(quantity);
            System.out.println("Remaining Stock: " + stock);
        } else {
            System.out.println("Insufficient stock");
        }
        System.out.println();
        System.out.println();
    }
}

public class OnlineShopping {
    public static void main(String[] args) {
        Electronics e1 = new Electronics("Smartphone", 30000, "Samsung");
        e1.showElectronicsDetails();
        e1.purchase(2);

        Electronics e2 = new Electronics("Laptop", 65000, 5, "Dell", 2);
        e2.showElectronicsDetails();
        e2.purchase(1);

        Electronics e3 = new Electronics("Television", 80000, 4, "Sony", 3, 10);
        e3.showElectronicsDetails();
        e3.purchase(2);
    }
}
```

**Output** (matches the sample exactly)
```
Product Name: Smartphone
Price: 30000.0
Stock: 10
Brand: Samsung
Warranty: 1 years
Discount: 5.0%

Quantity Purchased: 2
Total Amount: 60000.0
Discount Amount: 3000.0
Final Amount: 57000.0
Remaining Stock: 8


Product Name: Laptop
Price: 65000.0
Stock: 5
Brand: Dell
Warranty: 2 years
Discount: 5.0%

Quantity Purchased: 1
Total Amount: 65000.0
Discount Amount: 3250.0
Final Amount: 61750.0
Remaining Stock: 4


Product Name: Television
Price: 80000.0
Stock: 4
Brand: Sony
Warranty: 3 years
Discount: 10.0%

Quantity Purchased: 2
Total Amount: 160000.0
Discount Amount: 16000.0
Final Amount: 144000.0
Remaining Stock: 2
```

**The maths**
- Smartphone: 30000×2 = 60000; 5% = 3000 → final 57000; stock 10−2 = 8
- Laptop: 65000×1 = 65000; 5% = 3250 → final 61750; stock 5−1 = 4
- TV: 80000×2 = 160000; 10% = 16000 → final 144000; stock 4−2 = 2

**Why `30000` prints as `30000.0`:** `price` is a `double`, so the `int` literal is widened. Doubles always print with `.0`.

---

### Q2 — Order / PremiumOrder

**Key ideas**
- `Order(id, name)` → `this(id, name, 0)`, so the default amount is 0.
- `addPurchase()` increases the amount and **then** calls `applyFreeDelivery()`.
- Final Bill = Amount + Tax(5%) + Delivery − Discount.

```java
class Order {
    int orderId;
    String customerName;
    double orderAmount;

    Order(int orderId, String customerName) {
        this(orderId, customerName, 0);          // default orderAmount = 0
    }

    Order(int orderId, String customerName, double orderAmount) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderAmount = orderAmount;
    }

    void showOrder() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Order Amount: " + orderAmount);
    }

    double calculateTax() {
        return orderAmount * 5 / 100;
    }

    double getAmountWithTax() {
        return orderAmount + calculateTax();
    }
}

class PremiumOrder extends Order {
    String membershipType;
    double discountPercentage;
    double deliveryCharge;

    PremiumOrder(int orderId, String customerName, String membershipType) {
        super(orderId, customerName);            // orderAmount = 0
        this.membershipType = membershipType;
        this.discountPercentage = 5;
        this.deliveryCharge = 100;
    }

    PremiumOrder(int orderId, String customerName, double orderAmount,
                 String membershipType) {
        super(orderId, customerName, orderAmount);
        this.membershipType = membershipType;
        this.discountPercentage = 5;
        this.deliveryCharge = 100;
    }

    PremiumOrder(int orderId, String customerName, double orderAmount,
                 String membershipType, double discountPercentage,
                 double deliveryCharge) {
        super(orderId, customerName, orderAmount);
        this.membershipType = membershipType;
        this.discountPercentage = discountPercentage;
        this.deliveryCharge = deliveryCharge;
    }

    double calculateDiscount() {
        return orderAmount * discountPercentage / 100;
    }

    double calculateFinalBill() {
        return orderAmount + calculateTax() + deliveryCharge - calculateDiscount();
    }

    void applyFreeDelivery() {
        if (orderAmount >= 5000) {
            deliveryCharge = 0;
        }
    }

    void addPurchase(double amount) {
        orderAmount = orderAmount + amount;
        applyFreeDelivery();
    }

    void showPremiumOrder() {
        showOrder();                             // inherited method
        System.out.println("Membership Type: " + membershipType);
        System.out.println("Discount Percentage: " + discountPercentage + "%");
        System.out.println("Delivery Charge: " + deliveryCharge);
        System.out.println("Tax: " + calculateTax());
        System.out.println("Discount Amount: " + calculateDiscount());
        System.out.println("Final Bill: " + calculateFinalBill());
    }
}

public class OrderSystem {
    static void process(PremiumOrder o) {
        System.out.println("--- Initial Order ---");
        o.showOrder();
        o.applyFreeDelivery();
        System.out.println("--- Final Order ---");
        o.showPremiumOrder();
        System.out.println();
    }

    public static void main(String[] args) {
        PremiumOrder o1 = new PremiumOrder(101, "Ravi", "Silver");
        o1.addPurchase(3000);
        process(o1);

        PremiumOrder o2 = new PremiumOrder(102, "Ananya", 6000, "Gold");
        process(o2);

        PremiumOrder o3 = new PremiumOrder(103, "Kiran", 10000, "Platinum", 15, 200);
        process(o3);
    }
}
```

**Output**
```
--- Initial Order ---
Order ID: 101
Customer Name: Ravi
Order Amount: 3000.0
--- Final Order ---
Order ID: 101
Customer Name: Ravi
Order Amount: 3000.0
Membership Type: Silver
Discount Percentage: 5.0%
Delivery Charge: 100.0
Tax: 150.0
Discount Amount: 150.0
Final Bill: 3100.0

--- Initial Order ---
Order ID: 102
Customer Name: Ananya
Order Amount: 6000.0
--- Final Order ---
Order ID: 102
Customer Name: Ananya
Order Amount: 6000.0
Membership Type: Gold
Discount Percentage: 5.0%
Delivery Charge: 0.0
Tax: 300.0
Discount Amount: 300.0
Final Bill: 6000.0

--- Initial Order ---
Order ID: 103
Customer Name: Kiran
Order Amount: 10000.0
--- Final Order ---
Order ID: 103
Customer Name: Kiran
Order Amount: 10000.0
Membership Type: Platinum
Discount Percentage: 15.0%
Delivery Charge: 0.0
Tax: 500.0
Discount Amount: 1500.0
Final Bill: 9000.0
```

**The maths**
| Order | Amount | Tax 5% | Delivery | Discount | Final |
|---|---|---|---|---|---|
| Ravi (0 + 3000) | 3000 | 150 | 100 (below 5000) | 5% = 150 | 3000+150+100−150 = **3100** |
| Ananya | 6000 | 300 | 0 (≥ 5000) | 5% = 300 | **6000** |
| Kiran | 10000 | 500 | 200 → **0** | 15% = 1500 | 10000+500−1500 = **9000** |

Kiran's delivery was set to 200 in the constructor, but `applyFreeDelivery()` changes it to 0 because 10000 ≥ 5000.

---

## Notebook 5 — Bank Account Management

| # | Requirement | Keyword |
|---|---|---|
| 1 | Common interest method, actual calculation in child types | `abstract class BankAccount` + `abstract double calculateInterest()` |
| 2 | Bank name shared by all | `static String bankName` |
| 3 | Display bank name without an object | `static void displayBankName()` |
| 4 | `SavingsAccount` cannot be extended | `final class SavingsAccount` |
| 5 | Account number not accessible from outside | `private long accountNumber` + public getter |

```java
abstract class BankAccount {
    private long accountNumber;                  // 5. hidden from outside the class
    String accountHolder;
    double balance;
    static String bankName = "ABC Bank";         // 2. one copy shared by all accounts

    BankAccount(long accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public long getAccountNumber() {             // 5. controlled access
        return accountNumber;
    }

    public void displayAccountNumber() {
        System.out.println("Account Number: " + accountNumber);
    }

    static void displayBankName() {              // 3. no object needed
        System.out.println("Bank: " + bankName);
    }

    abstract double calculateInterest();         // 1. children must implement
}

final class SavingsAccount extends BankAccount { // 4. cannot be extended
    SavingsAccount(long accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }

    @Override
    double calculateInterest() {
        return balance * 5 / 100;
    }
}

class CurrentAccount extends BankAccount {
    CurrentAccount(long accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }

    @Override
    double calculateInterest() {
        return 0;
    }
}

public class BankDemo {
    public static void main(String[] args) {
        BankAccount.displayBankName();
        SavingsAccount s = new SavingsAccount(100245, "Anil", 50000);
        s.displayAccountNumber();
        System.out.println("Account Holder: " + s.accountHolder);
        System.out.println("Interest: " + s.calculateInterest());
    }
}
```

**Output** (matches the expected output)
```
Bank: ABC Bank
Account Number: 100245
Account Holder: Anil
Interest: 2500.0
```
- Interest 2500 = 50000 × 5%. The question doesn't give a balance or rate, so any pair that gives 2500 is fine (e.g. 50000 @ 5%).
- Try `s.accountNumber` from `main` → `accountNumber has private access in BankAccount`. That's the proof that requirement 5 is met.
- `private` fields are **not inherited in a usable way**. `SavingsAccount` can't read `accountNumber` directly either; it has to use `getAccountNumber()`.

---

## Notebook 6 — Hospital Patient Management

This notebook is all about **access specifiers + non-access modifiers**. Map each numbered requirement to one keyword:

| # | Requirement | Declaration |
|---|---|---|
| 1 | Patient ID never changed | `private final int patientId` |
| 2 | Hospital name stored once, accessible without an object | `private static final String HOSPITAL_NAME` + `public static getHospitalName()` |
| 3 | Medical record confidential | `private String medicalRecord` |
| 4 | Category: class + subclasses + same package | `protected String patientCategory` |
| 5 | Room number: same package only | `int roomNumber` (**default**, no keyword) |
| 6, 10 | Each patient type calculates the bill | `public abstract double calculateBill()` |
| 7 | Show hospital name without an object | `public static void displayHospitalName()` |
| 8 | Base class, no direct objects | `abstract class Patient` |
| 9 | InPatient, OutPatient | `class InPatient extends Patient`, `class OutPatient extends Patient` |
| 11 | Modify medical record | `public void updateMedicalRecord(String)` (the only way in) |
| 12 | Show all details | `public void displayPatientDetails()` |

Requirement 5 mentions a "hospital package", so we use `package hospital;`. That's what gives *default* access its meaning.

### Code (`HospitalDemo.java`)
```java
package hospital;

abstract class Patient {                             // 8. base class, no direct objects

    private final int patientId;                     // 1. set once, never changed
    private static final String HOSPITAL_NAME = "City Care Hospital";  // 2. one copy
    private String medicalRecord;                    // 3. only inside Patient
    protected String patientCategory;                // 4. class + subclasses + package
    int roomNumber;                                  // 5. default: same package only

    Patient(int patientId, String patientCategory, int roomNumber, String medicalRecord) {
        this.patientId = patientId;
        this.patientCategory = patientCategory;
        this.roomNumber = roomNumber;
        this.medicalRecord = medicalRecord;
    }

    public static String getHospitalName() {         // 2. read without an object
        return HOSPITAL_NAME;
    }

    public static void displayHospitalName() {       // 7. display without an object
        System.out.println("Hospital: " + HOSPITAL_NAME);
    }

    public int getPatientId() {
        return patientId;
    }

    public void updateMedicalRecord(String newRecord) {   // 11. the only way to change it
        if (newRecord == null || newRecord.isEmpty()) {
            System.out.println("Invalid medical record");
            return;
        }
        this.medicalRecord = newRecord;
    }

    public abstract double calculateBill();          // 6 & 10. each type implements

    public void displayPatientDetails() {            // 12.
        System.out.println("Patient ID       : " + patientId);
        System.out.println("Hospital Name    : " + HOSPITAL_NAME);
        System.out.println("Patient Category : " + patientCategory);
        System.out.println("Room Number      : " + roomNumber);
        System.out.println("Medical Record   : " + medicalRecord);
        System.out.println("Bill Amount      : " + calculateBill());
        System.out.println();
    }
}

class InPatient extends Patient {                    // 9.
    private int daysAdmitted;
    private double roomChargePerDay;

    InPatient(int patientId, int roomNumber, String medicalRecord,
              int daysAdmitted, double roomChargePerDay) {
        super(patientId, "In-Patient", roomNumber, medicalRecord);
        this.daysAdmitted = daysAdmitted;
        this.roomChargePerDay = roomChargePerDay;
    }

    @Override
    public double calculateBill() {
        return daysAdmitted * roomChargePerDay;
    }
}

class OutPatient extends Patient {                   // 9.
    private double consultationFee;
    private double medicineCharge;

    OutPatient(int patientId, String medicalRecord,
               double consultationFee, double medicineCharge) {
        super(patientId, "Out-Patient", 0, medicalRecord);   // no room
        this.consultationFee = consultationFee;
        this.medicineCharge = medicineCharge;
    }

    @Override
    public double calculateBill() {
        return consultationFee + medicineCharge;
    }
}

public class HospitalDemo {
    public static void main(String[] args) {
        Patient.displayHospitalName();               // no object needed
        System.out.println();

        Patient p1 = new InPatient(1001, 204, "Fractured leg", 5, 2000);
        Patient p2 = new OutPatient(1002, "Viral fever", 500, 300);

        p1.displayPatientDetails();
        p2.displayPatientDetails();

        p1.updateMedicalRecord("Fractured leg - plaster applied");
        p1.displayPatientDetails();
    }
}
```

**Compile & run** (because of `package hospital;`):
```
javac -d . HospitalDemo.java
java hospital.HospitalDemo
```
(Without the package line, a plain `javac HospitalDemo.java` + `java HospitalDemo` also works.)

### Output
```
Hospital: City Care Hospital

Patient ID       : 1001
Hospital Name    : City Care Hospital
Patient Category : In-Patient
Room Number      : 204
Medical Record   : Fractured leg
Bill Amount      : 10000.0

Patient ID       : 1002
Hospital Name    : City Care Hospital
Patient Category : Out-Patient
Room Number      : 0
Medical Record   : Viral fever
Bill Amount      : 800.0

Patient ID       : 1001
Hospital Name    : City Care Hospital
Patient Category : In-Patient
Room Number      : 204
Medical Record   : Fractured leg - plaster applied
Bill Amount      : 10000.0
```

### Explaining your choices (viva-ready)
- **InPatient bill** = 5 days × 2000 = 10000. **OutPatient** = 500 + 300 = 800.
- `displayPatientDetails()` calls `calculateBill()`, and **each object runs its own version**. That's runtime polymorphism inside the parent.
- `p1.medicalRecord` from `main` → `medicalRecord has private access in Patient`. That's why `updateMedicalRecord()` exists, and it can **validate** the new value (encapsulation).
- `new Patient(...)` → `Patient is abstract; cannot be instantiated`.
- **Why `protected` for category?** InPatient and OutPatient set or read it. A subclass in *another* package can too, but an unrelated class outside the package can't.
- **Why default for room number?** Only hospital-package classes (wards, billing) should see room allocation.
- **Why `static final` for the hospital name?** `static` stores it once. `final` stops it from ever being changed.

---

# PART 3 — CODING TRAPS

## Compile errors you'll meet

| Error message | Cause | Fix |
|---|---|---|
| `X is abstract; cannot be instantiated` | `new` on an abstract class | create a concrete child instead |
| `X is not abstract and does not override abstract method m() in Y` | child forgot to implement the abstract method | implement it with `@Override` |
| `cannot assign a value to final variable id` | changing a final field | don't; assign it only in the constructor |
| `m() in B cannot override m() in A; overridden method is final` | overriding a final method | remove the override |
| `cannot inherit from final X` | `extends` a final class | don't extend it |
| `illegal combination of modifiers: abstract and final` | `abstract final class` | abstract parent + final child |
| `x has private access in X` | using a private field from outside | use the getter / update method |
| `non-static variable x cannot be referenced from a static context` | field or `this` used inside a static method / `main` | create an object first, or pass a parameter |
| `constructor Book in class Book cannot be applied to given types` | `new Book()` but only parameterized constructors exist, **or** child with no `super(...)` when the parent has no no-arg constructor | write the no-arg constructor, or call `super(args)` |
| `constructor Book(int,String) is already defined` | two constructors with the same parameter **types** | use a static factory method |
| `call to this/super must be first statement in constructor` | a statement before `this(...)`/`super(...)` | move the call to line 1 |
| `recursive constructor invocation` | A calls B, B calls A | chain in one direction only |
| `variable x might not have been initialized` | local variable with no value, or a final field not set in every constructor | assign it |
| `class X is public, should be declared in a file named X.java` | public class name ≠ file name | rename the file or drop `public` |
| `invalid method declaration; return type required` | constructor name ≠ class name (typo or wrong capital letter) | fix the spelling |
| `cannot find symbol: method refuel()` | calling a child-only method through a parent reference | cast: `((PetrolCar) v).refuel(5)` |
| `possible lossy conversion from double to float` | `float f = 0.0;` | use `double`, or write `0.0f` |

## Logic traps (it compiles, but the output is wrong)
1. **`id = id;`** prints `0 / null`. Always write `this.id = id;`.
2. **`count` not static** → prints `Total: 1`. The counter must be `static`.
3. **`count++` in each child instead of the parent** → a new child type (StudentAccount) is not counted. Put it in the parent constructor.
4. **Wrong constructor picked:** `new Patient(301, 5000)` picks `(int,int)`. Write `5000.0`.
5. **Chaining order:** `this(...)`/`super(...)` runs first, so your own `println` prints last (`C B A`).
6. **The constructor overwrites the instance block**, because the block runs before the constructor body.
7. **Reducing stock before printing** → the bill shows the wrong "Remaining Stock", or stock goes negative. Check first, then print, then reduce.
8. **Forgetting `applyFreeDelivery()` after `addPurchase()`** → the delivery charge stays at 100 even after the total crosses 5000.
9. **Part E speeds** (Notebook 3): each vehicle accelerates twice → 20/40/10, not 10/20/5.
10. **Integer division:** `amount * 5 / 100` is fine for a double `amount`, but `5 / 100 * amount` = **0** when written as ints. Multiply first, or use `0.05`.

## Habits that save marks
- The longest constructor is the master; the others are one-line `this(...)` / `super(...)` calls.
- Always add `@Override` on overriding methods.
- The getter for a boolean is `isX()`.
- Validate in setters/update methods (`if (amount < 0) return;`).
- Aligned labels: `System.out.println("Book ID   : " + bookId);`
- Only one `public` class per file, and it's the one with `main`, matching the file name.
- Delete old `.class` files if you get stale-class confusion.

---

# PART 4 — RAPID-FIRE REVISION + VIVA QUESTIONS

## One-liners
- Writing any constructor removes the default constructor.
- A constructor has no return type. It can be `private`, but not `static`/`final`/`abstract`.
- Constructors are **not inherited**. The child calls the parent's constructor with `super(...)`.
- The parent constructor always runs **before** the child constructor.
- `this(...)` and `super(...)` must be first, and you can't use both in one constructor.
- Instance block: once per object, before the constructor body, in textual order.
- Static block: once, when the class loads.
- Local variables get no default value.
- Overloading never looks at parameter names, only types, count and order.
- `this` / instance fields are illegal in a static context.
- Abstract class: it can have constructors, fields and concrete methods, but you can't use `new` on it.
- An abstract method means the class must be abstract, and every concrete child must override it.
- `final` variable = constant, `final` method = no override, `final` class = no subclass.
- `abstract` + `final` together is illegal.
- A `static` method can't be overridden (it's hidden); a `private` method is not inherited.
- The reference type decides **what** you can call; the object type decides **which version** runs.

## Likely viva questions (short answers)
1. **Why can't we create an object of an abstract class?** It's incomplete: its abstract methods have no body, so calling one would have nothing to run.
2. **Can an abstract class have a constructor?** Yes. It runs through `super(...)` when a child object is created, to set up the common fields.
3. **Abstract class vs final class?** Abstract means it *must* be extended; final means it *can't* be extended. They're opposites.
4. **Difference between a static and an instance variable?** Static has one copy per class. Instance has one copy per object.
5. **Why use `final` for an ID?** So it's assigned once, in the constructor, and can never be changed afterwards.
6. **Why make `verifyKYC()` / `start()` final?** A mandatory standard procedure has to be the same everywhere, and final stops any child from replacing it.
7. **What is runtime polymorphism?** A parent reference points to a child object, and the overridden method that runs is picked at run time from the actual object.
8. **Overloading vs overriding?** Overloading: same name, different parameters, decided at compile time. Overriding: same signature in the child, decided at run time.
9. **Why `@Override`?** The compiler checks that you really are overriding, which catches spelling and signature mistakes.
10. **Why is `protected` needed when we have default?** Default doesn't reach subclasses in other packages; `protected` does.
11. **How do we add StudentAccount/FlyingCar without changing the parent?** Extend it, call `super(...)`, and override the abstract method. The parent constructor already counts it, and the polymorphic loop already handles it. This is the Open/Closed idea.
12. **Why a getter instead of a public field?** It gives read access without write access, and lets you add validation or rules later.

---

*Good luck! 🚀 Solve each notebook yourself first, then compare with these answers.*
