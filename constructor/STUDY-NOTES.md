# Exam Notes — Class & Object, Instance Variables, Instance Initializer, Constructor, Encapsulation

---

## 1. Class & Object

Class = blueprint (no memory). Object = real thing made by `new` (has memory).

```java
class Employee { int id; String name; }

Employee e1 = new Employee();
```
- object lives in **heap**; `e1` is a **reference** holding the address.
- `Employee b = e1;` → **2 references, 1 object**.
- reference without `new` = `null` → using it = `NullPointerException`.

---

## 2. Instance Variables & Defaults

Fields of a class — **one copy per object**, auto-initialized:

| Type | Default |
|---|---|
| int, long, short, byte | `0` |
| double, float | `0.0` |
| char | blank |
| boolean | `false` |
| String / object | `null` |

| | Instance variable | Local variable |
|---|---|---|
| Where | in class | in method/constructor |
| Default | **yes** | **no** → compile error |
| Memory | heap | stack |

`static int count;` → **one copy for the whole class**.

---

## 3. Instance Initializer Block

Unnamed `{ }` inside the class body.

```java
class Employee {
    int id;
    { id = 100; }          // instance initializer
    Employee() { }
}
```
1. Runs **once per object**, for every object.
2. Order: **defaults → field initializers + `{ }` blocks (top to bottom) → constructor body.**
3. Runs **before the constructor body**, so the constructor can overwrite it.
4. Many blocks allowed; run in written order.
5. No name, no return type, no parameters; `return;` inside = error.
6. Can use `this` and instance variables.
7. With `this(...)` chaining it still runs **only once** per object.

**Use:** code common to all constructors → write once instead of repeating.

| | `static { }` | `{ }` |
|---|---|---|
| Runs | once, at class load | every object creation |
| Instance vars / `this` | not allowed | allowed |

```java
int x = 1;      // 1
{ x = 2; }      // 2
int y = x + 5;  // 3  -> y = 7
A() { x = 3; }  // 4
// final: x = 3, y = 7
```

---

## 4. Constructor

**Rules**
1. Same name as class.
2. **No return type** (not even `void`).
3. Runs automatically on `new`.
4. Can be overloaded; can be `private`; **cannot** be static/final/abstract.
5. `void Employee(){}` = a **method**, not a constructor.

**Default constructor:** compiler gives it only if you wrote **zero** constructors. Write one → it's gone → you must write the no-arg one yourself.

**Types:** no-arg, parameterized, copy constructor.
```java
Employee(Employee o) { this.id = o.id; this.name = o.name; }
```

### `this`
`this` = the object being created/used.
```java
Employee(int id, String name) {
    this.id = id;      // left = my field, right = value passed in
    this.name = name;
}
```
Needed because the parameter has the same name as the field and **the parameter wins** (shadowing). `this` is illegal in a `static` method.

### Overloading
Java chooses by **signature = number + types + order** of parameters. It **ignores names and meaning**.
```java
Employee(int id, String name)         // OK
Employee(int id, String department)   // ERROR: already defined
```
Match order: **exact → widening (int→long→double) → boxing → varargs.**
So `new Patient(301, 5000)` picks `(int,int)`. Write `5000.0` for double.

### Chaining — `this(...)`
Must be the **first statement**; only one; no cycles.
```java
Employee() { this(0, "Not Assigned"); }
```
**Prints unwind deepest-first (reverse of call order).**
```
Product() -> this(0,"Unknown") -> this(0,"Unknown",0)
new Product()  =>  C  B  A     // id=0, name="Unknown", price=0.0
```
Device (4 constructors) → **D C B A**, `id=100, name="Unknown", price=0.0`.
`this(...)` placed after a statement → **compile error**.

---

## 5. Shadowing Bug (asked 3 times)

```java
Employee(int employeeId, String name) {
    employeeId = employeeId;   // wrong -> prints 0 and null
    name = name;
}
```
- **Compiles because** the parameter shadows the field, so both sides are the parameter, and `x = x` is a legal assignment — logic error, not syntax error.
- **Values not stored because** the field is never touched; it keeps its default (`0`, `null`).
- **Fix:** `this.employeeId = employeeId;`
- BankAccount version also compiles because `int` **widens** to `long`/`double`.

---

## 6. Encapsulation

Fields `private` + `public` getters/setters.
```java
private double salary;
public double getSalary() { return salary; }
public void setSalary(double salary) {
    if (salary < 0) return;
    this.salary = salary;
}
```
Boolean getter is `isPermanent()`.

**Why private?** Otherwise anyone writes `e.salary = -50000;` and nothing stops it. A setter lets you **validate**.

**Benefits:** data hiding, validation, internals can change freely, read-only fields (getter only).

| private | default | public |
|---|---|---|
| same class only | same package | anywhere |

---

## 7. Design Problem (guaranteed)

Want `(id, name)` **and** `(id, department)` → both are `(int, String)` → **cannot coexist**, because overloading looks at **types**, not meaning.

**Fix — static factory methods** (methods can have different names, constructors can't):
```java
static Employee withName(int id, String name)      { return new Employee(id, name); }
static Employee withDepartment(int id, String dep) { return new Employee(id, "Not Assigned", dep); }
```

| Case | Types | Result |
|---|---|---|
| Employee (id,name) vs (id,dept) | (int,String) both | ✘ → factory |
| Order/Food (id,restaurant,item) vs (id,customer,item) | (int,String,String) | ✘ → factory |
| Hotel (id,guest,roomType) vs (id,guest,company) | (int,String,String) | ✘ → factory |
| Vehicle packageAmount vs securityDeposit | (int,String,String,double) | ✘ → factory |
| Patient (id,age) vs (id,deposit) | (int,int) vs (int,double) | ✔ but int literal picks `age` |
| Course (id,name)/(id,name,course)/(id,name,sem) | all differ | ✔ fine |

---

## 8. Recipe — solves all 8 practice questions

1. Fields → `private`.
2. **Longest constructor = master**, does all `this.f = f;`.
3. All others **delegate** with `this(...)` + that case's defaults.
4. No-arg constructor → all "Not Assigned" defaults.
5. "Automatically admitted/confirmed/booked" → pass `true` in the chain.
6. New requirement → different types = new constructor; same types = **static factory**.
7. Add getters/setters + `display()`.

```java
System.out.println("Employee ID : " + employeeId);
```

---

## 9. Rapid-Fire

- Writing any constructor removes the default constructor.
- Constructor has no return type; can be private; not static/final/abstract.
- Recursive `this(...)` = compile error.
- Instance block: once per object, before constructor body, textual order.
- Static block: once at class load, before `main`.
- Local variables get no default.
- Overloading never uses parameter names.
- `this` illegal in static context.
- Boolean getter = `isX()`.
