/*HimalayaBookStore has 4 books.
Let us C by yashwant kanetkar is costing 600 and the books orders 1000copies for it.
In the same way it recieves a store
1. The last Queen - 900 copies
2. Wings On Fire - 800 copies
3. Palace of Illusions - 700 copies
4. Let us C - 1000 copies

Tata Motors is hosting a car show in which it displays all the latest models of their cars.
All the cars are performing basic function like accelerate and brake.
It displays Harrier, Sierra, Tiago, etc
Harrier is running at the speed of 220km/hr while sierra is racing only at 110km/h, Tiago could barely touch 100km/hr and stops

    String[] name = {"Let us C", "The last Queen", "Wings On Fire", "Palace of Illusions"};
    String[] author = {"yashwant kanetkar", "Chitra Banerjee Divakaruni", "Sutherland", "Chitra Banerjee Divakaruni"};
    double[] price = {789.00, 899.00, 1499.34, 648.25};
    int[] copies = {1000, 900, 800, 700}; */


// Solution

class notebook01 {
    public static void main(String[] args) {
        // Book store
        Book b1 = new Book("Let us C", "Yashwant Kanetkar", 789.00);
        Book b2 = new Book("The Last Queen", "Chitra Banerjee Divakaruni", 899.00);
        Book b3 = new Book("Wings On Fire", "Sutherland", 1499.34);
        Book b4 = new Book("Palace of Illusions", "Chitra Banerjee Divakaruni", 648.25);

        b1.restock(1000);
        b2.restock(900);
        b3.restock(800);
        b4.restock(700);

        b1.display();
        b2.display();
        b3.display();
        b4.display();

        // Car show
        Car c1 = new Car("Harrier");
        Car c2 = new Car("Sierra");
        Car c3 = new Car("Tiago");

        c1.accelerate(220);
        c2.accelerate(110);
        c3.accelerate(100);
        c3.brake();

        // Student
        student s = new student();
        s.study();
        s.displaydetails();
        s.writeExam();
    }
}

class Book {
    String name;
    String author;
    double price;
    int copies;

    Book(String name, String author, double price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    void buy() {
        copies--;
    }

    void restock(int noOfCopies) {
        copies = copies + noOfCopies;
    }

    void discount(double discount) {
        price = price - price * discount;
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Author: " + author);
        System.out.println("Price: " + price);
        System.out.println("Copies: " + copies);
        System.out.println();
    }
}

class Car {
    String model;
    int speed;

    Car(String model) {
        this.model = model;
    }

    void accelerate(int speed) {
        this.speed = speed;
        System.out.println(model + " is running at " + speed + " km/hr");
    }

    void brake() {
        speed = 0;
        System.out.println(model + " stopped");
    }
}

class student {
    String name = "vikas";
    int age = 18;
    float marks = 99.99f;

    void study() {
        System.out.println("studying studying studying.");
    }

    void displaydetails() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Marks: " + marks);
    }

    void writeExam() {
        System.out.println("writing exam");
    }
}
