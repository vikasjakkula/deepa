class demo {
    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);
        // System.out.println("Enter a: ");
        // int a = scanner.nextInt();
        // System.out.println("Enter b: ");
        // int b = scanner.nextInt();
        // System.out.println(a+b);
    }
}

// Create 3 Tesla Cars,2 Flying Cars and 2 aero india airctafts objects
// charge two cars five times and change the altitude of one car by 
// 1200 and another one by -200
// Display the details of all the vehicles after performing these operations


class Car {
    String brand;
    String model;
    int speed;
    
    Car {
        System.out.println("default constructor");
    }
    
    Car(String b) {
        this()
        System.out.println("1 parameter constructor");
    }
    
    Car(String b, String model) {
        this(b)
        this.model = model;
        System.out.println("1 parameter constructor");
    }
    
    Car(String b, String model, int speed) {
        this(b,model)
        System.out.println("1 parameter constructor");
    }
    
    Car(String brand, String model, int speed) {
        brand = b;
        this.model = model;
        this.speed = speed;
    }
void start() { 
        System.out.println("Car started");
    }

    void accelerate() {
        speed = speed + 10;
        System.out.println("Speed: " + speed);
    }

    void stop() {
        speed = 0;
        System.out.println("Car stopped");
    }
}

class electricCar extends Car {
    int batteryPercentage;
    
    // parameterized constructor
    // electricCar(String brand, String model, int speed, int btl) {
    //     // this.brand = brand;
    //     // this.model = model;
    //     // this.speed = speed;
    //     // this.batteryPercentage = btl;
    //     super(brand,model,speed);
    //     this.batteryPercentage = btl;
    // }
}

public class flyingCar extends Car {
    int altitude;
    void changeAltitude(int level)
    {
        if(altitude+level>1000)
        {
            System.out.println("Cannot go above 1000");
        } else if(altitude+level<200)
        {
            System.out.println("Cannot go below 200");
        }
        
    }
}

class airctafts extends flyingCar {
    
}

class test {
    public static void main(String[] args) {
        electricCar tesla = new electricCar("Tesla","v9",0,98);
        electricCar tesla2 = new electricCar("Tesla2","v9",0,86);
        electricCar tesla3 = new electricCar("Tesla3","v9",0,78);
        
        Car s = new Car();
        
        s1.brand
        
        flyingCar fc = new flyingCar();
        flyingCar fc2 = new flyingCar();
        fc.brand = "Aero india";
        fc.model = "g650";
        for(int i=0; i<5; i++)
        {
            tesla.chargeBattery();
            tesla2.chargeBattery();
        }
        fc.changeAltitude
    }
}