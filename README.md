# Java OOP Exam Notes

Exam-ready notes for Java Object-Oriented Programming. They cover every concept, a step-by-step method for each question type, and complete verified solutions for Notebooks 1 to 6.

**How to use these notes**
1. Read **Part 0** and **Part 1** first. They teach you how to recognise a question type and answer it.
2. Revise **Part 2** (concepts) topic by topic.
3. For each notebook in **Part 3**, try the question yourself first. Then open the hidden solution.
4. Do the **practice question** in Part 4 without looking.
5. The night before the exam, read **Part 5** (traps) and **Part 6** (revision).

> [!NOTE]
> Every program here was compiled and run. All outputs shown are real outputs.

---

## Table of Contents

- [Part 0 - Quick Overview](#part-0---quick-overview)
  - [Four Pillars of OOP](#four-pillars-of-oop)
  - [Requirement to Keyword Cheat Sheet](#requirement-to-keyword-cheat-sheet)
- [Part 1 - Exam Playbook](#part-1---exam-playbook)
  - [Type A - Design Scenario](#type-a---design-scenario)
  - [Type B - Requirements to Modifiers](#type-b---requirements-to-modifiers)
  - [Type C - Constructor Overloading with super](#type-c---constructor-overloading-with-super)
  - [Model Answers for Theory Questions](#model-answers-for-theory-questions)
  - [Likely Twists](#likely-twists)
- [Part 2 - Concepts](#part-2---concepts)
  - [1. Class and Object](#1-class-and-object)
  - [2. Instance Variables and Defaults](#2-instance-variables-and-defaults)
  - [3. Static](#3-static)
  - [4. Initializer Blocks and Creation Order](#4-initializer-blocks-and-creation-order)
  - [5. Constructors and this](#5-constructors-and-this)
  - [6. The Shadowing Bug](#6-the-shadowing-bug)
  - [7. Encapsulation](#7-encapsulation)
  - [8. Access Modifiers and Packages](#8-access-modifiers-and-packages)
  - [9. Inheritance and super](#9-inheritance-and-super)
  - [10. Overriding vs Overloading](#10-overriding-vs-overloading)
  - [11. Abstract Class and Abstract Method](#11-abstract-class-and-abstract-method)
  - [12. Final](#12-final)
  - [13. Polymorphism and Casting](#13-polymorphism-and-casting)
  - [14. Abstract Class vs Interface](#14-abstract-class-vs-interface)
  - [15. Constructor Design Problem](#15-constructor-design-problem)
- [Part 3 - Notebook Solutions](#part-3---notebook-solutions)
  - [Notebook 1 - SecureBank](#notebook-1---securebank)
  - [Notebook 2 - Course Management](#notebook-2---course-management)
  - [Notebook 3 - AutoTest Labs](#notebook-3---autotest-labs)
  - [Notebook 4 Q1 - Product and Electronics](#notebook-4-q1---product-and-electronics)
  - [Notebook 4 Q2 - Order and PremiumOrder](#notebook-4-q2---order-and-premiumorder)
  - [Notebook 5 - Bank Account](#notebook-5---bank-account)
  - [Notebook 6 - Hospital Patients](#notebook-6---hospital-patients)
- [Part 4 - Practice Question](#part-4---practice-question)
- [Part 5 - Traps and Compiler Errors](#part-5---traps-and-compiler-errors)
- [Part 6 - Revision and Viva](#part-6---revision-and-viva)

---

# Part 0 - Quick Overview

## Four Pillars of OOP

| Pillar | Meaning | Java tools |
|---|---|---|
| Encapsulation | hide data, control access | `private` fields + getters/setters |
| Inheritance | child reuses parent | `extends`, `super(...)` |
| Polymorphism | one call, many behaviours | overloading, overriding |
| Abstraction | show *what*, hide *how* | `abstract` class/method, interface |

Where they appear in the notebooks:
- **Encapsulation:** private account number (NB5), private medical record + `updateMedicalRecord()` (NB6)
- **Inheritance:** every child class (`SavingsAccount extends Account`, `Electronics extends Product`)
- **Polymorphism:** the `Account[]` / `Vehicle[]` loops (NB1, NB3), constructor overloading (NB4)
- **Abstraction:** `abstract calculateInterest()`, `abstract accelerate()`, `abstract calculateBill()`

## Requirement to Keyword Cheat Sheet

This is the most important table in the notes. Every Type A and Type B question is built from these phrases.

| If the question says... | Write |
|---|---|
| "should not create a general X object" / "base class only" | `abstract class X` |
| "declare the operation but no implementation" / "each type calculates differently" | `abstract` method + `@Override` in each child |
| "must never change once assigned" | `final` variable |
| "children inherit it but cannot override it" | `final` method |
| "no one can extend / inherit further" | `final class` |
| "common to all" / "stored only once" / "belongs to the class" | `static` variable |
| "call without creating an object" / "call using class name" | `static` method |
| "count how many objects were created" | `static int count` + `count++` in the **parent** constructor |
| "not directly accessible from outside the class" | `private` + getter / update method |
| "class, subclasses and same package" | `protected` |
| "only classes in the same package" | default (no keyword) |
| "same reference works with different objects" | `Parent p = new Child();` |
| "initialize parent data from child" | `super(...)` |
| "reuse another constructor of same class" | `this(...)` |
| "constant shared by all" | `static final` |

---

# Part 1 - Exam Playbook

Every question in the notebooks is one of **three types**. Spot the type first, then follow its recipe.

| Type | Looks like | Notebooks |
|---|---|---|
| A | long scenario with Parts A-E, skeleton to complete | 1, 3 |
| B | numbered requirements, "choose modifiers", expected output | 2, 5, 6 |
| C | overloaded constructors + `super`, billing maths, sample output | 4 (Q1, Q2) |

## Type A - Design Scenario

**How to spot it:** "should not be possible to create a general X", "must never change", "cannot override", "count objects", "no one may extend", and then Part A (complete the class), B (children), C (test), D (predict errors), E (polymorphism), Concept Identification and a Challenge.

**Steps**
1. **Part A:** fix the skeleton with 6 edits:
   - `abstract` on the class
   - `final` on the ID field
   - `static` on the counter, plus `count++` in the constructor
   - `final` on the standard method (KYC / start)
   - `abstract` on the method with no body (end it with `;`)
   - `static` on `getCount()`
2. **Part B:** each child uses `extends Parent`, adds its own fields, has a constructor that calls `super(id, ...)` on its first line, and uses `@Override` on the abstract method (and on any method whose behaviour changes, e.g. `withdraw`).
3. **Final class:** write the config/policy class as `final class X { fields }`.
4. **Part C:** create the objects exactly as given, call the methods in the order asked, print, and finish with `Parent.getCount();`.
5. **Part D:** answer each item with: **compiles? → exact error → why the design wants that** (see the [model answers](#model-answers-for-theory-questions)).
6. **Part E:** `Parent p = new Child(...)`, then an array and a for-each loop. Say "the child's overridden method runs, because it is chosen at run time from the actual object".
7. **Concept identification:** copy the 10-row table from Notebook 1 and change the names.
8. **Challenge:** add a new `class Y extends Parent` with `super(...)` and `@Override`. Don't touch the parent. The counter already counts it.

**Skeleton**
```java
abstract class Parent {
    final int id;
    String name;
    static int count = 0;

    Parent(int id, String name) {
        this.id = id;
        this.name = name;
        count++;
    }

    final void standardProcedure() {
        System.out.println("Standard check done");
    }

    abstract double calculate();

    void displayDetails() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
    }

    static void getCount() {
        System.out.println("Total Created: " + count);
    }
}

class ChildOne extends Parent {
    double extra;

    ChildOne(int id, String name, double extra) {
        super(id, name);
        this.extra = extra;
    }

    @Override
    double calculate() {
        return extra * 0.04;
    }
}

final class Config {
    int limit = 100;
}
```

**Mistakes that lose marks**
- Forgetting `static` on the counter, so it always prints 1.
- Putting `count++` in each child instead of the parent, so the Challenge class isn't counted.
- Missing `super(...)` in the child, which gives the error `constructor Parent in class Parent cannot be applied to given types`.
- An abstract method written with `{ }`. It must end with `;`.
- Not noticing that Part E objects are accelerated/processed **twice** (once individually, once in the loop).

## Type B - Requirements to Modifiers

**How to spot it:** a short numbered list like "must not be changed", "common to all", "not accessible from outside", "subclasses and same package", "should not be extended", then "select appropriate access specifiers and non-access modifiers" and an expected output.

**Steps**
1. Write a **table**: requirement number → declaration. Examiners love this, so put it at the top of your answer.
2. Map each requirement with the [cheat sheet](#requirement-to-keyword-cheat-sheet).
3. If a package is mentioned, add `package name;` as the first line.
4. Write the abstract parent, then two children, then `main`.
5. Make the output match the expected output **exactly**, including labels and spacing.
6. Add a comment with the requirement number next to each declaration, e.g. `// 3. private: confidential`.

**Watch for contradictions.** If one class must be both "abstract" and "not inherited", make an **abstract parent** plus a **final child**. See [Notebook 2](#notebook-2---course-management).

**Skeleton**
```java
package company;

abstract class Base {
    private final int id;                  // never changes
    private static final String ORG = "X"; // one copy, constant
    private String secret;                 // hidden
    protected String category;             // class+sub+package
    int room;                              // package only

    Base(int id, String secret) {
        this.id = id;
        this.secret = secret;
    }

    public static void displayOrg() {      // no object needed
        System.out.println("Org: " + ORG);
    }

    public void updateSecret(String s) {   // controlled change
        if (s != null && !s.isEmpty()) this.secret = s;
    }

    public abstract double calculate();    // each type differs
}

final class TypeOne extends Base {         // cannot be extended
    TypeOne(int id, String secret) { super(id, secret); }

    @Override
    public double calculate() { return 500; }
}
```

## Type C - Constructor Overloading with super

**How to spot it:** "overloaded constructors" in both parent and child, "use super(...)", "default values", "do not use overriding / arrays / abstract", billing or stock maths, and a sample output.

**Steps**
1. **Parent:** write the longest constructor first (it does all the `this.x = x`). The shorter one calls `this(..., default)`.
2. **Child:** every constructor starts with `super(...)` (the short or long parent version), then sets child fields and defaults.
3. **Methods:** one method per formula, returning `double`. Reuse methods (`calculateFinal()` calls `calculateTotal()` and `calculateDiscount()`).
4. **No overriding:** give the child **new method names** (`showElectronicsDetails()`) and call the inherited `showProduct()` inside them.
5. **Order of actions:** check first, then calculate, then print, then update state.
6. Work out the sample output by hand and compare it line by line.

**Skeleton**
```java
class Parent {
    String name; double price; int stock;

    Parent(String name, double price) {
        this(name, price, 10);            // default
    }

    Parent(String name, double price, int stock) {
        this.name = name; this.price = price;
        this.stock = stock;
    }
}

class Child extends Parent {
    String brand; double discount;

    Child(String name, double price, String brand) {
        super(name, price);               // stock = 10
        this.brand = brand;
        this.discount = 5;                // default
    }

    Child(String name, double price, int stock,
          String brand, double discount) {
        super(name, price, stock);
        this.brand = brand;
        this.discount = discount;
    }
}
```

**Mistakes that lose marks**
- Using `@Override` (or a method with the parent's name) when the question forbids overriding.
- Integer maths: `5 / 100 * amount` = 0. Write `amount * 5 / 100`.
- Updating stock or amount *before* printing the bill.
- Forgetting to re-check a condition after changing state (e.g. free delivery after `addPurchase`).

## Model Answers for Theory Questions

Use these sentence patterns and change the names to fit the question.

**"Why should `new Parent(...)` fail?"**
> It does not compile: `Parent is abstract; cannot be instantiated`. Parent is only a general idea. Its abstract method has no body, so only concrete child objects should exist.

**"Why should `obj.id = 999;` be prevented?"**
> It does not compile: `cannot assign a value to final variable id`. The ID identifies the object for its whole lifetime. Changing it would break records, so it is `final` and assigned once in the constructor.

**"Should the child be allowed to override `start()` / `verifyKYC()`?"**
> No. `start() in Child cannot override start() in Parent; overridden method is final`. It is a mandatory standard procedure, so it is `final`. Children inherit and use it but cannot replace it.

**"Should `class X extends Config {}` compile?"**
> No: `cannot inherit from final Config`. Config is a `final` class, so it can be used (`new Config()` works) but not extended. This stops anyone from changing its values or behaviour through a subclass.

**"Why can `Parent.getCount()` be called using the class name?"**
> Because it is `static`. A static method belongs to the class, not to an object, and it reads the static counter, which also belongs to the class. So no object is needed. This works even though the class is abstract.

**"Which implementation of `accelerate()` executes?"**
> The child's version: PetrolCar adds 10, ElectricCar adds 20, Bike adds 5. The reference type is `Vehicle`, but Java picks the overridden method at run time from the **actual object**. This is runtime polymorphism (dynamic method dispatch).

**"Why can the same Parent reference work with different objects?"**
> Every child *is a* Parent (inheritance), so a Parent reference can hold any child object (upcasting). Calls to overridden methods run the child's version. So one loop handles all types, including types added later.

**"Why can't a class be both abstract and final?"**
> `abstract` means "must be extended to be used"; `final` means "cannot be extended". They contradict each other, so the compiler says `illegal combination of modifiers: abstract and final`.

**"Explain each concept used" (Concept Identification)**
> Name the concept, point to the exact line in your program, and give one sentence of purpose. Example: *Static variable: `static int accountCount`. One counter shared by all accounts, so it counts every object created.*

## Likely Twists

| Twist | How to handle |
|---|---|
| "Use an interface" instead of abstract class | `interface X { double calc(); }`, `class Y implements X`, the method must be `public` in Y |
| Private ID but must display it | `private final int id;` + `public int getId()` |
| Call a child-only method on a parent reference | `if (v instanceof PetrolCar) ((PetrolCar) v).refuel(5);` |
| Counter per child type | a separate `static int` in each child |
| Multilevel (`C extends B extends A`) | each constructor calls `super(...)`; creation runs A, then B, then C |
| Validation required | check inside the setter/method: `if (amount <= 0) return;` |
| Print object directly | override `public String toString()` |
| Subclass in another package needs a field | make it `protected`, not default |

---

# Part 2 - Concepts

Each topic follows the same pattern: what it is, an example, the rules, and the exam tip.

## 1. Class and Object

A **class** is a blueprint and takes no memory for objects. An **object** is an instance made with `new`, and it lives in the **heap**.

```java
class Employee { int id; String name; }

Employee e1 = new Employee();   // e1 = reference (address)
Employee e2 = e1;               // 2 references, 1 object
```
- Local variables and references live on the **stack**. Objects live on the **heap**.
- A reference with no object is `null`. Calling a method on it throws a `NullPointerException`.
- **is-a** = inheritance (`Car extends Vehicle`). **has-a** = a field (`Car` has an `Engine`).

## 2. Instance Variables and Defaults

| Type | Default |
|---|---|
| `int long short byte` | `0` |
| `double float` | `0.0` |
| `char` | `'\u0000'` |
| `boolean` | `false` |
| objects / `String` | `null` |

| | Instance variable | Local variable |
|---|---|---|
| Declared in | class | method / constructor |
| Default value | yes | **no** (compile error if used unassigned) |
| Memory | heap | stack |

## 3. Static

`static` means the member belongs to the **class**: one copy shared by all objects.

| | static | instance |
|---|---|---|
| Copies | one per class | one per object |
| Access | `ClassName.member` | `object.member` |
| Created | when the class loads | on `new` |
| Can use `this` | no | yes |

```java
static int count = 0;              // shared counter
static void getCount() {           // Parent.getCount()
    System.out.println("Total: " + count);
}
```
**Rules**
- A static method cannot use instance variables or `this` directly: `non-static variable x cannot be referenced from a static context`.
- `main` is static. That's why you create objects inside `main` before you use their fields.
- Static methods are **not overridden**, they are **hidden**. With `A r = new B(); r.s();` it's A's static `s()` that runs, because the reference type decides.
- `static final` = a constant (`static final String BANK = "ABC";`).

> [!TIP]
> "Stored once / common to all" → static **variable**. "Without creating an object" → static **method**.

## 4. Initializer Blocks and Creation Order

- `static { }` runs **once**, when the class is first loaded.
- `{ }` (the instance block) runs **every time** an object is created, before the constructor body.

**Full creation order for `new Child()`** (verified output):
```text
1 Parent static block      <- first object only
2 Child static block       <- first object only
3 Parent instance block
4 Parent constructor
5 Child instance block
6 Child constructor
```
For a second `new Child()`, only steps 3-6 run.

**Inside one class:** default values → field initializers and `{ }` blocks (top to bottom) → constructor body.
```java
int x = 1;      // 1
{ x = 2; }      // 2
int y = x + 5;  // 3  -> y = 7
A() { x = 3; }  // 4  -> final x = 3, y = 7
```

## 5. Constructors and this

**Rules**
1. The name is the same as the class.
2. **No return type**. `void A(){}` is a method, not a constructor.
3. It runs automatically on `new`.
4. It can be overloaded and can be `private`. It **cannot** be `static`, `final` or `abstract`.
5. The compiler gives a default no-arg constructor **only if you wrote none**.
6. Constructors are **not inherited**.

**Types:** no-arg, parameterized, copy (`A(A o) { this.id = o.id; }`).

**`this` has three uses**
- `this.id = id;`: the field vs the parameter
- `this(...)`: call another constructor of the same class (it must be the first line)
- pass or return the current object: `return this;`

**Overloading resolution:** Java looks at the number, types and order of the parameters. **Names are ignored.** Matches are tried in this order: exact → widening (`int→long→double`) → boxing → varargs. `new Patient(301, 5000)` picks `(int,int)` over `(int,double)`.

**Chaining output order:** the deepest constructor finishes first.
```text
A() -> this(0) -> this(0,"x")   prints: C  B  A
```

> [!WARNING]
> `this(...)` / `super(...)` must be the **first statement** (Java 8-21). Java 25 allows some statements before them, but the exam expects line 1.

## 6. The Shadowing Bug

```java
Employee(int id, String name) {
    id = id;        // WRONG: prints 0 and null
    name = name;
}
```
- **It compiles** because the parameter hides the field, so this assigns the parameter to itself.
- **The field keeps its default** (`0`, `null`).
- **Fix:** `this.id = id;`

## 7. Encapsulation

Encapsulation means `private` fields plus `public` getters/setters that **validate**.
```java
private double salary;
public double getSalary() { return salary; }
public void setSalary(double salary) {
    if (salary < 0) return;      // validation
    this.salary = salary;
}
```
- The getter for a boolean is `isX()`.
- Read-only = getter only. Controlled update = a method like `updateMedicalRecord()`.
- **Why:** without it, anyone can write `e.salary = -500;`.

## 8. Access Modifiers and Packages

| Modifier | Class | Package | Subclass (other pkg) | World |
|---|:-:|:-:|:-:|:-:|
| `private` | ✅ | ❌ | ❌ | ❌ |
| default | ✅ | ✅ | ❌ | ❌ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| `public` | ✅ | ✅ | ✅ | ✅ |

Verified errors when crossing package `hospital` → `billing`:
- default field: `roomNumber is not public in Patient; cannot be accessed from outside package`
- protected field from a **non-subclass**: `patientCategory has protected access in Patient`
- A subclass in another package **can** use a protected field through inheritance.

**Packages**
- `package hospital;` must be the **first line**.
- Compile with `javac -d . File.java`, run with `java hospital.MainClass`.
- One `public` class per file, and the file name must match it.
- A top-level class can only be `public` or default.

## 9. Inheritance and super

```java
class Electronics extends Product { }   // Electronics is-a Product
```
- The child gets the **non-private** fields and methods.
- **Types supported with classes:** single, multilevel (`C→B→A`), hierarchical (`B→A`, `C→A`).
- **Multiple inheritance with classes is not allowed** (`class C extends A, B` is an error) because of the *diamond problem*: which parent's method would win? Interfaces solve this.

**`super` has three uses**
- `super(...)`: call the parent constructor (must be the first line)
- `super.method()`: call the parent's version of an overridden method
- `super.field`: a parent field hidden by a child field

**Rules**
- If you don't write `super(...)`, Java inserts `super()`. If the parent has no no-arg constructor, you get the compile error `constructor Parent in class Parent cannot be applied to given types`.
- You can use `this(...)` or `super(...)` in a constructor, not both.
- The parent part is always built **before** the child part.

> [!TIP]
> Put the static counter's `count++` in the **parent** constructor. Every child passes through `super(...)`, so new child types are counted automatically.

## 10. Overriding vs Overloading

| | Overloading | Overriding |
|---|---|---|
| Parameters | must differ | must be the same |
| Where | same class | child class |
| Decided | compile time | run time |
| Also called | static polymorphism | dynamic polymorphism |

**Overriding rules**
- Same name and parameters. The return type is the same or a subtype (covariant).
- **Cannot reduce visibility.** `public` in the parent → `public` in the child, or you get `show() in B cannot override show() in A` (attempting to assign weaker access privileges).
- `final`, `static` and `private` methods and constructors **cannot** be overridden.
- Always write `@Override`, so the compiler catches typos.

## 11. Abstract Class and Abstract Method

```java
abstract class Account {
    abstract double calculateInterest();   // no body, ends with ;
}
```
- An abstract class **cannot be instantiated**, but it can have constructors, fields, normal methods, static methods and final methods.
- A class with any abstract method **must** be abstract.
- Every concrete child **must** implement it, or you get `X is not abstract and does not override abstract method m() in Y`.
- **Illegal:** `abstract final`, `abstract static` and `abstract private` (verified: `illegal combination of modifiers`).

## 12. Final

| On | Meaning | Error when broken |
|---|---|---|
| variable | assign once | `cannot assign a value to final variable id` |
| method | no override | `m() in B cannot override m() in A` + `overridden method is final` |
| class | no subclass | `cannot inherit from final X` |

- **Blank final:** declared without a value and assigned once in **every** constructor.
- A `final` class can still be instantiated with `new`.
- A `final` reference can't point to a new object, but the object itself can still change.

## 13. Polymorphism and Casting

```java
Account a = new SavingsAccount(...);   // upcasting (automatic)
a.calculateInterest();                 // SavingsAccount version runs
```
- The **reference type** decides **what you can call** (checked at compile time).
- The **object type** decides **which overridden version runs** (decided at run time).
- A child-only method needs a **downcast**:
```java
if (v instanceof PetrolCar) {
    ((PetrolCar) v).refuel(5);
}
```
- A wrong downcast compiles but fails at run time with `ClassCastException: class Bike cannot be cast to class PetrolCar`. Always check with `instanceof` first.

## 14. Abstract Class vs Interface

| | Abstract class | Interface |
|---|---|---|
| Keyword | `extends` (one only) | `implements` (many) |
| Fields | any | `public static final` only |
| Constructor | yes | no |
| Methods | abstract + normal | abstract (+ default/static) |
| Use when | related classes share code | unrelated classes share a rule |

## 15. Constructor Design Problem

`(int id, String name)` and `(int id, String department)` **cannot both exist**, because both are `(int, String)`. Overloading ignores parameter names.

**Fix: static factory methods** (unlike constructors, methods can have different names):
```java
static Employee withName(int id, String n) {
    return new Employee(id, n, "Not Assigned");
}
static Employee withDept(int id, String d) {
    return new Employee(id, "Not Assigned", d);
}
```

**Recipe for any constructor question**
1. Make the fields `private`.
2. The longest constructor is the **master** and does all the assignments.
3. The others delegate with `this(...)` / `super(...)` and pass the defaults.
4. Different parameter types → new constructor. Same types → static factory.

---

# Part 3 - Notebook Solutions

> [!IMPORTANT]
> Try each question first. The code and output are hidden. Tap **Show** to open them.

## Notebook 1 - SecureBank

**Type:** [A - Design Scenario](#type-a---design-scenario)

**Requirement → keyword**
| Requirement | Solution |
|---|---|
| `new Account()` not allowed | `abstract class Account` |
| Account number never changes | `final long accountNumber` |
| Interest differs per type | `abstract double calculateInterest();` |
| KYC same for all | `final void verifyKYC()` |
| Count all accounts | `static int accountCount` + `static getAccountCount()` |
| No subclass of BankPolicy | `final class BankPolicy` |

**Part A: the 6 edits to the skeleton**
- `final long accountNumber`
- `static int accountCount = 0` + `accountCount++;` in the constructor
- `final void verifyKYC()`
- `abstract double calculateInterest();` (`abstract void` also acceptable)
- `static void getAccountCount()`

**Class design**
```text
Account (abstract)
├── SavingsAccount      minimumBalance, 4%
├── CurrentAccount      overdraftLimit, 0%
├── FixedDepositAccount depositPeriod, 7%, no withdrawal
└── StudentAccount      daily limit, 3%  (Challenge)
BankPolicy (final)
```

<details>
<summary>Show full solution code</summary>

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

</details>

<details>
<summary>Show output</summary>

```text
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

</details>

**Logic walkthrough**
- **Savings:** 50000 + 10000 = 60000 − 20000 = **40000**. Withdrawing 38000 would leave 2000, which is below the 5000 minimum, so it's **rejected**. Interest = 40000 × 4% = **1600**.
- **Current:** 30000 + 5000 = 35000. Withdrawing 45000 gives **−10000**, which is allowed because the limit is −20000. Reject if `balance - amount < -overdraftLimit`.
- **FD:** withdrawal is blocked before maturity. Interest = 100000 × 7% = **7000**.
- **Count:** 3 after Part C, 6 after Part E, **7** after the Challenge.

**Part D answers**
1. `new Account(...)` → `Account is abstract; cannot be instantiated`
2. `s.accountNumber = 50000;` → `cannot assign a value to final variable accountNumber`
3. Overriding `verifyKYC()` → not allowed, the method is final (`overridden method is final`)
4. `extends BankPolicy` → `cannot inherit from final BankPolicy`
5. `Account.getAccountCount()` works because it's a static method that reads a static counter

**Concept identification**
| Concept | Where |
|---|---|
| Inheritance | `SavingsAccount extends Account` (all 4 children) |
| Abstract class | `abstract class Account` |
| Abstract method | `abstract double calculateInterest()` |
| Overriding | `withdraw()`, `calculateInterest()` with `@Override` |
| Static variable | `static int accountCount` |
| Static method | `static void getAccountCount()` |
| Final variable | `final long accountNumber` |
| Final method | `final void verifyKYC()` |
| Final class | `final class BankPolicy` |
| Runtime polymorphism | `Account a1 = new SavingsAccount(...)` + `Account[]` loop |

**If the scenario changes:** Insurance policies, Employees, Shapes or Library items all use the same design. Rename the fields, keep the 6 edits.

## Notebook 2 - Course Management

**Type:** [B - Requirements to Modifiers](#type-b---requirements-to-modifiers)

> [!WARNING]
> **The trap:** requirement 3 needs `abstract` (fee differs per course), but requirement 5 says "not inherited further" (`final`). One class can't be both: `illegal combination of modifiers: abstract and final`.
> **Answer:** `abstract class Course` + `final class JavaCourse extends Course`.

| # | Requirement | Keyword |
|---|---|---|
| 1 | Course ID never changes | `final int courseId` |
| 2 | College name common | `static String collegeName` |
| 3 | Fee differs per course | `abstract double calculateFee()` |
| 4 | Show ID without object | `static void displayCourseId(int id)` |
| 5 | Not inherited further | `final class JavaCourse` |

<details>
<summary>Show full solution code</summary>

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

</details>

<details>
<summary>Show output</summary>

```text
College: ABC Engineering College
Course ID: 501
Course Name: Core Java
Java Course Fee: 15000.0
```

</details>

**Why does `displayCourseId` take a parameter?** A static method has no object, so there's no `this.courseId` to read. The ID must be passed in.

## Notebook 3 - AutoTest Labs

**Type:** [A - Design Scenario](#type-a---design-scenario). This is the same design as Notebook 1 with different names:

| Notebook 1 | Notebook 3 |
|---|---|
| `Account` | `Vehicle` |
| `final accountNumber` | `final vehicleId` |
| `final verifyKYC()` | `final start()` |
| `abstract calculateInterest()` | `abstract accelerate()` |
| `BankPolicy` | `SecurityConfiguration` |
| `StudentAccount` | `FlyingCar` |

**Class design**
```text
Vehicle (abstract)
├── PetrolCar    fuelLevel, refuel(), +10
├── ElectricCar  batteryLevel, chargeBattery(), +20
├── Bike         helmetAvailable, +5
└── FlyingCar    altitude, takeOff(), land()  (Challenge)
SecurityConfiguration (final)
```

<details>
<summary>Show full solution code</summary>

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

</details>

<details>
<summary>Show output</summary>

```text
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

</details>

**Logic walkthrough**
- **Part C:** Petrol 2 × 10 = **20**, Electric 3 × 20 = **60**, Bike 4 × 5 = **20**.
- **Part E:** each vehicle accelerates **twice** (once individually, once in the loop), so the speeds are **20 / 40 / 10**.
- **Count:** 3, then **7** at the end.
- `v1.refuel(10)` does not compile, because `Vehicle` has no `refuel`. Use `((PetrolCar) v1).refuel(10)`.

**Part D answers**
1. `new Vehicle(...)` → `Vehicle is abstract; cannot be instantiated`
2. `p.vehicleId = 999;` → `cannot assign a value to final variable vehicleId`
3. `start()` in ElectricCar → not allowed, the method is final
4. `extends SecurityConfiguration` → `cannot inherit from final SecurityConfiguration`
5. `Vehicle.getVehicleCount()` → a static method, so no object is needed

## Notebook 4 Q1 - Product and Electronics

**Type:** [C - Constructor Overloading with super](#type-c---constructor-overloading-with-super)

**Key points**
- `Product(name, price)` → `this(name, price, 10)`: the default stock lives in one place.
- Each `Electronics` constructor calls `super(...)` first, then sets `warrantyYears = 1` and `discountPercentage = 5` when they aren't given.
- No overriding: use `showElectronicsDetails()`, which calls the inherited `showProduct()`.
- `purchase()`: check stock → calculate → print the bill → reduce stock.

<details>
<summary>Show full solution code</summary>

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

</details>

<details>
<summary>Show output (matches the sample exactly)</summary>

```text
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

</details>

**Maths**
| Item | Total | Discount | Final | Stock |
|---|---|---|---|---|
| Phone | 60000 | 5% = 3000 | 57000 | 10→8 |
| Laptop | 65000 | 5% = 3250 | 61750 | 5→4 |
| TV | 160000 | 10% = 16000 | 144000 | 4→2 |

`30000` prints as `30000.0` because `price` is a `double`.

## Notebook 4 Q2 - Order and PremiumOrder

**Type:** [C - Constructor Overloading with super](#type-c---constructor-overloading-with-super)

**Key points**
- `Order(id, name)` → `this(id, name, 0)`
- Final Bill = Amount + Tax (5%) + Delivery − Discount
- `addPurchase()` adds to the amount and **then** calls `applyFreeDelivery()`
- Delivery is free when the amount ≥ 5000

<details>
<summary>Show full solution code</summary>

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

</details>

<details>
<summary>Show output</summary>

```text
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

</details>

**Maths**
| Order | Amt | Tax | Deliv | Disc | Final |
|---|---|---|---|---|---|
| Ravi | 3000 | 150 | 100 | 150 | 3100 |
| Ananya | 6000 | 300 | 0 | 300 | 6000 |
| Kiran | 10000 | 500 | 0 | 1500 | 9000 |

Kiran's delivery was 200, but it becomes 0 because 10000 ≥ 5000.

## Notebook 5 - Bank Account

**Type:** [B - Requirements to Modifiers](#type-b---requirements-to-modifiers)

| # | Requirement | Keyword |
|---|---|---|
| 1 | Interest defined, children calculate | `abstract` class + method |
| 2 | Bank name shared | `static String bankName` |
| 3 | Show bank name without object | `static void displayBankName()` |
| 4 | SavingsAccount not extendable | `final class SavingsAccount` |
| 5 | Account number hidden | `private` + public getter |

<details>
<summary>Show full solution code</summary>

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

</details>

<details>
<summary>Show output (matches expected)</summary>

```text
Bank: ABC Bank
Account Number: 100245
Account Holder: Anil
Interest: 2500.0
```

</details>

- Interest 2500 = 50000 × 5%.
- `s.accountNumber` from `main` → `accountNumber has private access in BankAccount`. That proves requirement 5.
- Even `SavingsAccount` can't read the private field directly. It uses the getter.

## Notebook 6 - Hospital Patients

**Type:** [B - Requirements to Modifiers](#type-b---requirements-to-modifiers). The focus is access specifiers and packages.

| # | Requirement | Declaration |
|---|---|---|
| 1 | ID never changes | `private final int patientId` |
| 2, 7 | Hospital name once, no object | `private static final` + `public static` method |
| 3 | Medical record confidential | `private String medicalRecord` |
| 4 | Class + subclass + package | `protected String patientCategory` |
| 5 | Same package only | `int roomNumber` (default) |
| 6, 10 | Bill differs per type | `public abstract double calculateBill()` |
| 8 | Base class, no objects | `abstract class Patient` |
| 9 | Two patient types | `InPatient`, `OutPatient` |
| 11 | Modify record | `public void updateMedicalRecord(String)` |
| 12 | Display all | `public void displayPatientDetails()` |

**Run:** `javac -d . HospitalDemo.java` then `java hospital.HospitalDemo`

<details>
<summary>Show full solution code</summary>

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

</details>

<details>
<summary>Show output</summary>

```text
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

</details>

**Viva points**
- Bills: InPatient 5 × 2000 = **10000**, OutPatient 500 + 300 = **800**.
- `displayPatientDetails()` calls `calculateBill()`, and each object runs its own version. That's polymorphism inside the parent.
- **protected** for category: subclasses in any package can use it.
- **default** for room number: only hospital-package classes see it.
- **static final** for the hospital name: stored once and can never change.

---

# Part 4 - Practice Question

**Type A. Try it fully before opening the answer.**

> **City Library System**
>
> A library lends Books and DVDs. More item types may be added later.
> Every item has an **item ID**, which must never change after creation, and a **title**.
> No one should be able to create a general `LibraryItem` object.
> Every item calculates its late fee differently: Book Rs 2/day, DVD Rs 5/day.
> All items use the same `checkMembership()` step, which prints "Membership verified". Children must not replace it.
> The library wants to know how many items were created, using `LibraryItem.getItemCount()`.
> `LibraryRules` stores max books per member and loan days, and must not be extended.
>
> **Tasks:**
> (a) Write the classes. (b) Put a Book, a DVD and an EBook (Challenge: no late fee) into a `LibraryItem[]`, and loop over it to check membership, display details and print the late fee for 4 days. (c) Predict the errors for: `new LibraryItem(9,"t")`, `b.itemId = 7`, overriding `checkMembership()`, `extends LibraryRules`, and a child that forgets `calculateLateFee`.

<details>
<summary>Show solution code</summary>

```java
abstract class LibraryItem {
    final int itemId;                 // never changes
    String title;
    static int itemCount = 0;         // shared by all items

    LibraryItem(int itemId, String title) {
        this.itemId = itemId;
        this.title = title;
        itemCount++;
    }

    final void checkMembership() {    // same for every item
        System.out.println("Membership verified");
    }

    abstract double calculateLateFee(int daysLate);

    void displayDetails() {
        System.out.println("Item ID: " + itemId);
        System.out.println("Title: " + title);
    }

    static void getItemCount() {
        System.out.println("Total Items: " + itemCount);
    }
}

class Book extends LibraryItem {
    String author;

    Book(int itemId, String title, String author) {
        super(itemId, title);
        this.author = author;
    }

    @Override
    double calculateLateFee(int daysLate) {
        return daysLate * 2;          // Rs 2 per day
    }
}

class DVD extends LibraryItem {
    int durationMinutes;

    DVD(int itemId, String title, int durationMinutes) {
        super(itemId, title);
        this.durationMinutes = durationMinutes;
    }

    @Override
    double calculateLateFee(int daysLate) {
        return daysLate * 5;          // Rs 5 per day
    }
}

class EBook extends LibraryItem {     // Challenge
    double fileSizeMb;

    EBook(int itemId, String title, double fileSizeMb) {
        super(itemId, title);
        this.fileSizeMb = fileSizeMb;
    }

    @Override
    double calculateLateFee(int daysLate) {
        return 0;                     // auto-returned, no fee
    }
}

final class LibraryRules {
    int maxBooksPerMember = 3;
    int loanDays = 14;
}

public class CityLibrary {
    public static void main(String[] args) {
        LibraryItem[] items = {
            new Book(1, "Wings of Fire", "A.P.J. Abdul Kalam"),
            new DVD(2, "Interstellar", 169),
            new EBook(3, "Java Basics", 2.5)
        };
        for (LibraryItem it : items) {
            it.checkMembership();
            it.displayDetails();
            System.out.println("Late fee (4 days): "
                    + it.calculateLateFee(4));
        }
        LibraryItem.getItemCount();
    }
}
```

</details>

<details>
<summary>Show output</summary>

```text
Membership verified
Item ID: 1
Title: Wings of Fire
Late fee (4 days): 8.0
Membership verified
Item ID: 2
Title: Interstellar
Late fee (4 days): 20.0
Membership verified
Item ID: 3
Title: Java Basics
Late fee (4 days): 0.0
Total Items: 3
```

</details>

<details>
<summary>Show predict-the-error answers (verified)</summary>

1. `LibraryItem is abstract; cannot be instantiated`
2. `cannot assign a value to final variable itemId`
3. `checkMembership() in X cannot override checkMembership() in LibraryItem` (overridden method is final)
4. `cannot inherit from final LibraryRules`
5. `X is not abstract and does not override abstract method calculateLateFee(int) in LibraryItem`

</details>

---

# Part 5 - Traps and Compiler Errors

**Compile errors: message → fix**

| Error message | Fix |
|---|---|
| `X is abstract; cannot be instantiated` | create a concrete child |
| `X is not abstract and does not override abstract method` | implement it with `@Override` |
| `cannot assign a value to final variable` | assign only in the constructor |
| `overridden method is final` | remove the override |
| `cannot inherit from final X` | don't extend it |
| `illegal combination of modifiers: abstract and final` | abstract parent + final child |
| `x has private access in X` | use the getter |
| `has protected access` | access from a subclass or the same package |
| `is not public in X; cannot be accessed from outside package` | default field; use the same package or `protected` |
| `non-static variable cannot be referenced from a static context` | create an object first |
| `constructor X in class X cannot be applied to given types` | wrong arguments, or missing `super(args)` |
| `constructor X(int,String) is already defined` | same parameter types; use a factory method |
| `call to super must be first statement` | move `super(...)` to line 1 |
| `recursive constructor invocation` | don't chain in a circle |
| `variable x might not have been initialized` | give the local/final variable a value |
| `class X is public, should be declared in a file named X.java` | rename the file |
| `invalid method declaration; return type required` | constructor name typo |
| `cannot find symbol: method refuel` | downcast the parent reference |
| `attempting to assign weaker access privileges` | keep the same or wider access |

**Logic traps (it compiles, but the output is wrong)**
1. `id = id;` → prints `0` / `null`. Use `this.id = id;`.
2. A counter that isn't `static` → always prints 1.
3. `count++` in the children, not the parent → new types aren't counted.
4. `new Patient(301, 5000)` → picks `(int,int)`. Write `5000.0` for the double version.
5. Chained constructors print the deepest one first (C B A).
6. The constructor overwrites values set by the instance block.
7. Reducing stock before printing the bill.
8. Forgetting `applyFreeDelivery()` after `addPurchase()`.
9. Part E objects are accelerated twice → 20 / 40 / 10.
10. `5 / 100 * x` = 0 with integers. Write `x * 5 / 100`.
11. A wrong downcast → `ClassCastException` at run time. Check with `instanceof` first.

---

# Part 6 - Revision and Viva

## Rapid-fire
- Writing any constructor removes the default constructor.
- A constructor has no return type and is not inherited. It can be private, but not static, final or abstract.
- `this(...)` / `super(...)` must come first, and you can't use both.
- Parent static → child static → parent instance+constructor → child instance+constructor.
- Local variables get no default value.
- Overloading ignores parameter names.
- No `this` in a static context.
- An abstract class has constructors but can't be used with `new`.
- An abstract method means the class is abstract, and every concrete child must override it.
- final variable = constant, final method = no override, final class = no child.
- `abstract` + `final` / `static` / `private` → illegal.
- Static methods are hidden, not overridden. Private methods are not inherited.
- Reference type = what you can call. Object type = which version runs.
- Classes can't do multiple inheritance (diamond problem); interfaces allow it.

## Viva Questions
1. **What is OOP?** Programming with objects that combine data and behaviour. Its four pillars are encapsulation, inheritance, polymorphism and abstraction.
2. **Class vs object?** A class is a blueprint. An object is an instance of it in the heap.
3. **Why can't we instantiate an abstract class?** It's incomplete: its abstract methods have no body.
4. **Can an abstract class have a constructor?** Yes. It runs via `super(...)` to set up the common fields.
5. **Abstract vs final class?** Abstract must be extended; final can't be. They're opposites.
6. **Static vs instance variable?** Static has one copy per class. Instance has one copy per object.
7. **Why can `main` not use instance fields directly?** `main` is static, so there's no object.
8. **Why is an ID `final`?** So it's assigned once and never changed.
9. **Why is `verifyKYC()` / `start()` final?** A mandatory standard procedure can't be replaced by a child.
10. **What is runtime polymorphism?** A parent reference to a child object, where the overridden method is chosen at run time.
11. **Overloading vs overriding?** Different parameters, decided at compile time vs same signature in the child, decided at run time.
12. **Why `@Override`?** The compiler checks it really overrides, which catches typos.
13. **Why can't static methods be overridden?** They belong to the class and are chosen by the reference type (method hiding).
14. **`this` vs `super`?** `this` = the current object/class. `super` = the parent part.
15. **Why must `super(...)` be first?** The parent must be fully built before the child uses it.
16. **What if the parent has no no-arg constructor?** The child must call `super(args)` explicitly, or it's a compile error.
17. **private vs default vs protected?** Class only; package only; package + subclasses.
18. **Why is protected needed when default exists?** Default doesn't reach subclasses in other packages.
19. **What is encapsulation?** Private data plus controlled public methods with validation.
20. **Upcasting vs downcasting?** Child→parent is automatic. Parent→child needs a cast and an `instanceof` check.
21. **Why no multiple inheritance of classes?** The diamond problem: ambiguous inherited methods.
22. **Abstract class vs interface?** Abstract class = shared code, one parent. Interface = a contract, many can be implemented.
23. **How do you add a new type without changing the parent?** Extend it, call `super`, override the abstract method. This is the open/closed idea.
24. **Why does the counter go in the parent constructor?** Every child passes through it, so every object is counted.
25. **Can a final class be instantiated?** Yes. It just can't be extended.

## Exam Day Checklist
- [ ] Identify the question type (A / B / C) before writing.
- [ ] For B, write the **requirement → keyword** table first.
- [ ] Parent first: fields → constructor → final/static/abstract methods.
- [ ] Children: `extends`, `super(...)` on line 1, `@Override`.
- [ ] `main`: create the objects exactly as the question gives them.
- [ ] Check the output labels and numbers against the expected output.
- [ ] Theory parts: **compiles? → exact error → why**.
- [ ] Only one `public` class, and it's the one with `main`.
