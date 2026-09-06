// Run this file to SEE the two things the exam asks you to predict:
//   1. initialization order  (static -> defaults -> field init + {} block -> constructor)
//   2. constructor chaining  (prints unwind deepest-first)

class Device {
    int id;
    String name;
    double price;

    static { System.out.println("static block  (once, at class load)"); }

    { System.out.println("instance block (runs ONCE per object, before constructor body)"); }

    Device() {
        this(100);
        System.out.println("Constructor A");
    }

    Device(int id) {
        this(id, "Unknown");
        System.out.println("Constructor B");
    }

    Device(int id, String name) {
        this(id, name, 0.0);
        System.out.println("Constructor C");
    }

    Device(int id, String name, double price) {   // master: reaches super() -> block runs here
        this.id = id;
        this.name = name;
        this.price = price;
        System.out.println("Constructor D");
    }
}

class Order {
    int x = 1;       // 1st
    { x = 2; }       // 2nd
    int y = x + 5;   // 3rd  -> y = 7
    Order() { x = 3; }   // 4th -> x = 3
}

public class Trace {
    public static void main(String[] args) {

        System.out.println("=== new Device() ===");
        Device d = new Device();
        System.out.println("id=" + d.id + "  name=" + d.name + "  price=" + d.price);
        // expected:  static block, instance block, D, C, B, A
        //            id=100  name=Unknown  price=0.0

        System.out.println();
        System.out.println("=== field initializer vs block vs constructor ===");
        Order o = new Order();
        System.out.println("x=" + o.x + "  y=" + o.y);   // x=3  y=7

        System.out.println();
        System.out.println("=== shadowing bug ===");
        Bug b = new Bug(201, "Ananya");
        b.show();        // prints 0 and null  -> parameter assigned to itself
        Fixed f = new Fixed(201, "Ananya");
        f.show();        // prints 201 and Ananya -> this.field = parameter
    }
}

class Bug {
    int id;
    String name;
    Bug(int id, String name) {
        id = id;        // wrong: parameter shadows the field, self assignment
        name = name;
    }
    void show() { System.out.println("Bug   -> " + id + " , " + name); }
}

class Fixed {
    int id;
    String name;
    Fixed(int id, String name) {
        this.id = id;   // correct
        this.name = name;
    }
    void show() { System.out.println("Fixed -> " + id + " , " + name); }
}
