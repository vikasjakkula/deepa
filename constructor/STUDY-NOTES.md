# Java Exam Notes — Class, Object, Constructor, Instance Initializer, Encapsulation

---

## 1. Class & Object

- **Class** = blueprint. **Object** = real instance made with `new`.
- `Employee e1 = new Employee();`
  - `new Employee()` → object on **heap**
  - `e1` → reference variable on **stack** (holds address only)
- `Employee b = a;` → 2 references, **1 object**.

---

## 2. Instance Variables & Default Values

Fields get defaults automatically. **Memorize:**

| Type | Default |
|---|---|
| int / long / short / byte | `0` |
| double / float | `0.0` |
| char | blank |
| boolean | `false` |
| String / any object | `null` |

**Instance vs Local variable**

| | Instance | Local |
|---|---|---|
| Where | in class | in method/constructor |
| Default value | YES | **NO** → using it = compile error |
| Memory | heap (with object) | stack (per call) |

---

## 3. `this` keyword

Refers to the current object. Three uses:
1. `this.name = name;` → fix **shadowing**
2. `this(...)` → **constructor chaining**
3. `return this;` → method chaining

`this` cannot be used inside a `static` method.

---

## 4. Constructor — Rules

1. Same name as class.
2. **No return type** (not even void).
3. Runs automatically on `new`.
4. Can be overloaded.
5. Can be private; **cannot** be static / final / abstract.
6. **Not inherited.**
7. `void Employee(){}` = a **method**, NOT a constructor.

**Default constructor:** compiler gives `Employee(){ super(); }` only if you wrote **zero** constructors.
Write even one → default is gone → `new Employee()` fails unless you write it yourself.

**Types:** no-arg, parameterized, copy constructor.
```java
Employee(Employee o) { this.name = o.name; }   // copy constructor
```

---

## 5. Constructor Overloading

Java chooses by **signature = number + types + order** of parameters.
It **ignores** parameter names and their meaning.

```java
Employee(int id, String name)         // OK
Employee(int id, String department)   // ERROR: already defined
```

> Exam line: **"Overloading depends on the parameter list/types, not the meaning of parameters."**

**Matching preference:** exact → widening (`int→long→double`) → boxing → varargs.
So `new Patient(301, 5000)` picks `(int,int)`, not `(int,double)`. Write `5000.0` for double.

---

## 6. Constructor Chaining — `this(...)`

Rules:
1. Must be the **first statement**.
2. Only **one** per constructor.
3. Cannot use `this(...)` and `super(...)` together.
4. No cycles → compile error "recursive constructor invocation".

**Why first?** So the object is initialized once, in a fixed order, with `super()` running before any field is touched.

```java
Device() { System.out.println("hi"); this(100); }  // COMPILE ERROR
```

**Tracing rule: prints come out deepest-first (reverse of call order).**

```java
Product() { this(0,"Unknown"); print("A"); }
Product(int i,String n) { this(i,n,0); print("B"); }
Product(int i,String n,double p) { ...; print("C"); }

new Product();   →  C  B  A
// id=0, name="Unknown", price=0.0
```

---

## 7. Initialization Order (very important)

**Once, at class load:** static variables + `static { }` blocks — top to bottom.

**Every `new`:**
1. defaults (0 / null / false)
2. `super(...)`
3. instance variable initializers + `{ }` **instance initializer blocks** — textual order
4. **constructor body**

```java
class A {
    int x = 1;      // 1
    { x = 2; }      // 2
    int y = x + 5;  // 3 → y = 7
    A() { x = 3; }  // 4
}                   // final: x=3, y=7
```

**Key insight:** with `this(...)` chaining, instance blocks run **only once**, inside the constructor that actually calls `super()` (the deepest one).

**Use of instance initializer block:** common code shared by all constructors, logic that a one-line initializer can't do, init of `final` fields, init inside anonymous classes.

---

## 8. Encapsulation

Fields `private` + `public` getters/setters.

```java
private double salary;
public double getSalary() { return salary; }
public void setSalary(double salary) {
    if (salary < 0) throw new IllegalArgumentException("negative");
    this.salary = salary;
}
```
Boolean getter is `isPermanent()`.

**Benefits:** data hiding, validation in setter, easy to change internals, read-only fields (getter only).

| Encapsulation | Abstraction |
|---|---|
| hides **data** | hides **implementation** |
| private + getters | abstract class / interface |

| Modifier | Class | Package | Subclass(other pkg) | World |
|---|---|---|---|---|
| private | ✔ | ✘ | ✘ | ✘ |
| default | ✔ | ✔ | ✘ | ✘ |
| protected | ✔ | ✔ | ✔ | ✘ |
| public | ✔ | ✔ | ✔ | ✔ |

**Immutable class:** final class, private final fields, no setters.

---

## 9. Shadowing Bug (asked 3 times in your paper)

```java
Employee(int employeeId, String name) {
    employeeId = employeeId;   // wrong
    name = name;
}
// prints 0 and null
```

**Q: Why does it compile?**
The parameter **shadows** (hides) the field, so both sides of `=` are the parameter. `x = x` is a legal assignment in Java — only a logic error, so no compile error (just a possible warning).

**Q: Why aren't values stored?**
The field is never touched. The parameter dies when the constructor ends, so fields keep defaults → `0`, `null`, `0.0`, `false`.

**Q: Fix without renaming parameters:**
```java
this.employeeId = employeeId;
this.name = name;
```
Same for `BankAccount(long accountNumber, double balance)`. (`new BankAccount(200001, 25000)` also compiles because int **widens** to long/double.)

---

## 10. The Design Problem (guaranteed question)

Wanted: `(id, name)` and `(id, department)` → both are `(int, String)` → **cannot coexist**.

**Reason:** signature = ordered parameter *types*. Names/meaning are invisible to the compiler.

**Fix — static factory methods (best answer):**
```java
private Employee(int id, String value, boolean isDept) { ... }

public static Employee withName(int id, String name)      { return new Employee(id, name, false); }
public static Employee withDepartment(int id, String dep) { return new Employee(id, dep, true); }

Employee e7 = Employee.withDepartment(106, "Research");
```
Works because **methods can have different names; constructors cannot.**

Other fixes: enum/marker parameter, wrapper type (`Department` class), builder pattern.

**Verdict table for your paper:**

| Case | Types | Result |
|---|---|---|
| Employee: (id,name) vs (id,dept) | (int,String) both | ✘ clash → factory |
| Order: (id,restaurant,food) vs (id,customer,food) | (int,String,String) both | ✘ clash → factory |
| Hotel: (id,guest,roomType) vs (id,guest,company) | (int,String,String) both | ✘ clash → factory |
| Vehicle: (…,packageAmount) vs (…,securityDeposit) | (int,String,String,double) both | ✘ clash → factory |
| Patient: (id,age) vs (id,deposit) | (int,int) vs (int,double) | ✔ legal, but int literal picks `age` |
| Course: (id,name) / (id,name,course) / (id,name,sem) | all different | ✔ fine |

Course selection: `new Student(1001,"Aman")` → 2-arg; `(1002,"Sara","B.Tech")` → `(int,String,String)`; `(1003,"Kiran",3)` → `(int,String,int)`. Java matches on **types**.

---

## 11. Device Tracing Answer

```java
Device()                      → this(100)
Device(int)                   → this(id,"Unknown")
Device(int,String)            → this(id,name,0.0)
Device(int,String,double)     → assigns fields
```
- First to receive control: `Device()`
- Output: **D, C, B, A**
- `id=100, name="Unknown", price=0.0`
- `this(...)` after a statement → **compile error**

---

## 12. Exam Recipe (works for every question in your set)

1. Fields → `private`.
2. Write the **longest constructor** = master; it does all `this.f = f;`.
3. All other constructors **delegate** with `this(...)` filling in defaults.
4. No-arg constructor delegates with all "Not Assigned" defaults.
5. Auto flags ("automatically admitted/confirmed/booked") → pass `true` in the chain.
6. New requirement → compare its signature. Different → new constructor. Same → **static factory + explain**.
7. Add getters/setters and `display()`.

Display format:
```java
System.out.println("Employee ID : " + employeeId);
```

---

## 13. Rapid-Fire Facts

- Adding any constructor removes the compiler's default constructor.
- Constructor cannot be static/final/abstract; can be private.
- Constructors are not inherited; `super()` is implicit if you write neither `this()` nor `super()`.
- Recursive chaining = compile error (not StackOverflow).
- Instance blocks run after `super()`, before constructor body, in textual order, once per object.
- Static block runs once at class load, before `main`.
- Local variables have no default → must initialize.
- Overload resolution never uses parameter names.
- `this` illegal in static context.
