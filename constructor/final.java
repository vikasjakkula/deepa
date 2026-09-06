// ONE FILE = WHOLE SYLLABUS.  Compile: javac final.java     Run: java Master
// (file is named final.java but no class is public, so the names need not match)

// ===== 1. THE MASTER PATTERN : 6 overloaded constructors + chaining + encapsulation =====
class Book {

    // encapsulation : fields are private
    private int bookId;
    private String title;
    private String author;
    private double price;
    private int copies;
    private boolean available;

    private static int totalBooks;          // one copy for the whole class
    { totalBooks++; }                       // instance initializer : once per object

    // Case A : nothing known
    Book() {
        this(0, "Not Assigned", "Unknown", 0.0, 0, false);
    }

    // Case B : id + title
    Book(int bookId, String title) {
        this(bookId, title, "Unknown", 0.0, 0, false);
    }

    // Case C : id + title + author
    Book(int bookId, String title, String author) {
        this(bookId, title, author, 0.0, 0, false);
    }

    // Case D : id + title + price   -> (int,String,double) differs from Case C, so allowed
    Book(int bookId, String title, double price) {
        this(bookId, title, "Unknown", price, 1, true);
    }

    // Case E : everything except the flag -> flag is derived
    Book(int bookId, String title, String author, double price, int copies) {
        this(bookId, title, author, price, copies, copies > 0);
    }

    // MASTER : the only constructor that really assigns. this.field = parameter
    Book(int bookId, String title, String author, double price, int copies, boolean available) {
        this.bookId    = bookId;
        this.title     = title;
        this.author    = author;
        this.price     = price;
        this.copies    = copies;
        this.available = available;
    }

    // copy constructor
    Book(Book o) {
        this(o.bookId, o.title, o.author, o.price, o.copies, o.available);
    }

    // (int,String) is already taken by Case B, so "id + author" is impossible as a
    // constructor -> static factory method, because METHODS can have different names
    static Book byAuthor(int bookId, String author) {
        return new Book(bookId, "Not Assigned", author);
    }

    // ----- behaviour -----
    void purchase() {
        if (copies == 0) { System.out.println(title + " : out of stock"); return; }
        copies--;
        available = copies > 0;
    }

    void restock(int n) {
        if (n <= 0) { System.out.println("invalid quantity"); return; }
        copies += n;
        available = true;
    }

    // ----- getters / setters -----
    int getBookId()        { return bookId; }
    String getTitle()      { return title; }
    int getCopies()        { return copies; }
    boolean isAvailable()  { return available; }

    void setPrice(double price) {
        if (price < 0) { System.out.println("price cannot be negative"); return; }
        this.price = price;
    }

    static int getTotalBooks() { return totalBooks; }

    void display() {
        System.out.println("Book ID   : " + bookId);
        System.out.println("Title     : " + title);
        System.out.println("Author    : " + author);
        System.out.println("Price     : " + price);
        System.out.println("Copies    : " + copies);
        System.out.println("Available : " + available);
        System.out.println("----------------------------");
    }
}

// ===== 2. SHADOWING : the same constructor written wrong, then right =====
class Shadow {
    int id; String name;
    Shadow(int id, String name) { id = id; name = name; }              // parameter = parameter
    void show() { System.out.println("Shadow -> " + id + " , " + name); }
}

class Right {
    int id; String name;
    Right(int id, String name) { this.id = id; this.name = name; }      // field = parameter
    void show() { System.out.println("Right  -> " + id + " , " + name); }
}

// ===== 3. CHAINING TRACE : prints unwind deepest-first =====
class Chain {
    int id; String name; double price;
    Chain()                              { this(0, "Unknown");   System.out.println("A"); }
    Chain(int id, String name)           { this(id, name, 0);     System.out.println("B"); }
    Chain(int id, String name, double p) { this.id = id; this.name = name; this.price = p;
                                           System.out.println("C"); }
}

// ===== 4. INITIALIZATION ORDER =====
class Init {
    static { System.out.println("static block (once)"); }
    int x = 1;                                  // 1
    { x = 2; System.out.println("instance block"); }   // 2
    int y = x + 5;                              // 3  -> y = 7
    Init() { x = 3; }                           // 4  -> x = 3
}

// ===== 5. RUN EVERYTHING =====
class Master {
    public static void main(String[] args) {

        System.out.println("=== 1. overloaded constructors ===");
        Book b1 = new Book();                                            // A
        Book b2 = new Book(101, "Java");                                 // B
        Book b3 = new Book(102, "DBMS", "Korth");                        // C
        Book b4 = new Book(103, "Python", 499.0);                        // D
        Book b5 = new Book(104, "OS", "Galvin", 650.0, 3);               // E
        Book b6 = new Book(105, "CN", "Tanenbaum", 700.0, 2, true);      // master
        Book b7 = Book.byAuthor(106, "Bjarne");                          // factory
        Book b8 = new Book(b6);                                          // copy

        b1.display();
        b5.display();
        b7.display();

        System.out.println("=== behaviour ===");
        b5.purchase();  b5.purchase();  b5.purchase();
        System.out.println(b5.getTitle() + " copies=" + b5.getCopies()
                         + " available=" + b5.isAvailable());
        b5.purchase();                 // out of stock
        b5.restock(5);
        System.out.println(b5.getTitle() + " copies=" + b5.getCopies());
        b5.setPrice(-10);              // rejected by the setter
        System.out.println("total books created = " + Book.getTotalBooks());

        System.out.println("\n=== 2. shadowing ===");
        new Shadow(201, "Ananya").show();     // 0 , null
        new Right(201, "Ananya").show();      // 201 , Ananya

        System.out.println("\n=== 3. chaining ===");
        Chain c = new Chain();                // C B A
        System.out.println("id=" + c.id + " name=" + c.name + " price=" + c.price);

        System.out.println("\n=== 4. initialization order ===");
        Init i = new Init();
        System.out.println("x=" + i.x + " y=" + i.y);   // x=3 y=7
    }
}
