# Traps you will actually hit while writing the code

## Compile errors

| Error message | Cause | Fix |
|---|---|---|
| `constructor Book in class Book cannot be applied to given types` | you called `new Book()` but only wrote parameterized constructors | write the no-arg constructor yourself |
| `constructor Book(int,String) is already defined` | two constructors with the same parameter **types** | use a static factory method |
| `call to this must be first statement in constructor` | you put a statement before `this(...)` | move `this(...)` to line 1 |
| `recursive constructor invocation` | A calls B, B calls A | chain in one direction only |
| `variable x might not have been initialized` | local variable used without a value | locals get **no** default — assign it |
| `class X is public, should be declared in a file named X.java` | public class name ≠ file name | drop `public`, or rename the file |
| `invalid method declaration; return type required` | you wrote a constructor whose name ≠ class name | fix the spelling / capital letter |
| `cannot find symbol : variable salary` | you accessed a `private` field from `main` | use the getter |
| `non-static variable this cannot be referenced from a static context` | used a field or `this` directly inside `main` | make an object first |
| `incompatible types: possible lossy conversion from double to float` | `float salary = 0.0;` | use `double`, or write `0.0f` |

## Logic traps (compiles, wrong output)

1. **`id = id;`** → prints `0 / null`. Always write `this.id = id;`
2. **Wrong constructor picked.** `new Patient(301, 5000)` chooses `(int,int)`, not `(int,double)`. Write `5000.0`.
3. **Chaining order.** `this(...)` runs *first*, so your own `println` prints *last* → output is reversed (`C B A`).
4. **Constructor overwrites the instance block**, because the block runs before the constructor body.
5. **Static counter in an instance block** counts every object, including ones made by factory methods and copy constructors.
6. **Derived flag forgotten.** "automatically admitted / confirmed / booked" → pass `true` down the chain, don't leave the default `false`.
7. **Static block prints late.** It runs when the class is *first used*, not when the program starts — so it can appear in the middle of your output.
8. **Repeating assignments in every constructor.** Only the master constructor should assign; others must delegate with `this(...)`.

## Habits that save marks

- Fields `private`, then `this.f = f;` in the master constructor only.
- Write the **longest constructor first**, then the shorter ones as `this(...)` one-liners.
- Boolean getter is `isX()`, not `getX()`.
- Validate inside setters (`if (price < 0) return;`).
- Print with `+` concatenation and aligned labels: `System.out.println("Book ID   : " + bookId);`
- Only one class in a file may be `public`; a class with `main` need not be public.
- Delete `.class` files before recompiling if you get stale-class confusion.
