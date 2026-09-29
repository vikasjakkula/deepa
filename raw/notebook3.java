/*

Vehicle Testing System

A company named AutoTest Labs tests different types of vehicles before they are released into the market.

The company currently tests:

- Petrol Cars
- Electric Cars
- Bikes

More vehicle types may be added in the future.

Every vehicle has some common information:

- Vehicle ID
- Brand
- Model
- Current speed

Every vehicle should be capable of:

- Starting
- Stopping
- Accelerating
- Displaying its details

However, the way a vehicle accelerates may be different for different vehicle types.

The company therefore wants to define the idea of a general Vehicle, but it should not be possible to create a general Vehicle object directly.

For example, the following should not be allowed:

Vehicle v = new Vehicle();

Only specific vehicle objects such as a car, electric car, or bike should be created.

---

Vehicle Identification Rule

Every vehicle receives a unique vehicle ID when it is created.

For example:

Vehicle ID: 101

Once assigned, the vehicle ID must never be changed during the lifetime of the object.

For example, this should not be allowed:

vehicleId = 500;

Use an appropriate Java keyword to enforce this rule.

---

Acceleration Requirement

Every type of vehicle must provide its own implementation for acceleration.

For example:

Petrol Car accelerates by 10 km/h
Electric Car accelerates by 20 km/h
Bike accelerates by 5 km/h

The general Vehicle should declare the acceleration operation but should not provide its implementation.

Every concrete child class must provide its own implementation.

---

Common Start Behaviour

All vehicles use the same standard starting procedure developed by AutoTest Labs.

When a vehicle starts, display:

Vehicle safety check completed
Vehicle started

The company does not want child classes to change this starting procedure.

Therefore, a Petrol Car, Electric Car, or Bike should not be allowed to provide another implementation of the "start()" method.

Use an appropriate Java keyword to enforce this requirement.

---

Static Vehicle Counter

AutoTest Labs also wants to know how many vehicle objects have been created.

For example:

PetrolCar c1 = new PetrolCar(...);
ElectricCar c2 = new ElectricCar(...);
Bike b1 = new Bike(...);

After creating these objects:

Total Vehicles Created: 3

The count should belong to the class rather than to each individual object.

Use an appropriate Java concept for maintaining this count.

Provide a method:

getVehicleCount()

that can be called without creating a Vehicle object.

For example:

Vehicle.getVehicleCount();

---

Petrol Car

A Petrol Car should maintain additional information such as:

Fuel Level

It should provide an operation:

refuel(double litres)

which increases the fuel level.

When a Petrol Car accelerates, increase the speed by:

10 km/h

---

Electric Car

An Electric Car should maintain:

Battery Level

It should provide:

chargeBattery(int percentage)

When an Electric Car accelerates, increase the speed by:

20 km/h

---

Bike

A Bike should maintain:

Helmet Available

When a Bike accelerates, increase its speed by:

5 km/h

---

Security Configuration

AutoTest Labs provides a class called:

SecurityConfiguration

It stores information such as:

Maximum Speed Limit
Testing Center Code
Safety Standard

The company does not want any programmer to create another class by inheriting from "SecurityConfiguration".

For example, this should not be allowed:

class CustomSecurity extends SecurityConfiguration {
}

Use an appropriate Java keyword to enforce this restriction.

---

Part A – Complete the Parent Class

Complete the following class:

abstract class Vehicle {

    // Vehicle ID should never change
    int vehicleId;

    String brand;
    String model;
    int speed;

    // Shared by all objects
    int vehicleCount = 0;

    Vehicle(int vehicleId, String brand, String model) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;

        // Increase vehicle count
    }

    void start() {
        System.out.println("Vehicle safety check completed");
        System.out.println("Vehicle started");
    }

    void stop() {
        speed = 0;
        System.out.println("Vehicle stopped");
    }

    // Every child class must implement this
    void accelerate();

    void displayDetails() {
        System.out.println(vehicleId);
        System.out.println(brand);
        System.out.println(model);
        System.out.println(speed);
    }

    // Should be callable using Vehicle.getVehicleCount()
    void getVehicleCount() {
        System.out.println(vehicleCount);
    }
}

Modify the code using appropriate Java keywords wherever necessary.

---

Part B – Create the Child Classes

Create:

PetrolCar
ElectricCar
Bike

All three should inherit from "Vehicle".

Provide the appropriate additional attributes and methods.

Implement "accelerate()" in each class according to the requirements.

Expected behaviour:

PetrolCar  → speed increases by 10
ElectricCar → speed increases by 20
Bike → speed increases by 5

---

Part C – Test Your Classes

Write a "main()" method that performs the following:

PetrolCar p = new PetrolCar(101, "Toyota", "Fortuner");

ElectricCar e = new ElectricCar(102, "Tesla", "Model 3");

Bike b = new Bike(103, "Yamaha", "R15");

Start all three vehicles.

Accelerate the Petrol Car two times.

Accelerate the Electric Car three times.

Accelerate the Bike four times.

Display their details.

The expected speeds should be:

Toyota Fortuner : 20 km/h

Tesla Model 3 : 60 km/h

Yamaha R15 : 20 km/h

Display the total number of vehicles created using:

Vehicle.getVehicleCount();

Expected:

Total Vehicles Created: 3

---

Part D – Predict the Errors

Try each of the following statements separately and explain whether it compiles.

1.

Vehicle v = new Vehicle(104, "ABC", "XYZ");

Why should this fail?

---

2.

p.vehicleId = 999;

Why should the program prevent this?

---

3.

Inside "ElectricCar", try:

void start() {
    System.out.println("Electric car started differently");
}

Should Java allow this?

Explain.

---

4.

Try:

class CustomSecurityConfiguration extends SecurityConfiguration {
}

Should this compile?

Explain.

---

5.

Try calling:

Vehicle.getVehicleCount();

Why can this method be called using the class name?

---

Part E – Polymorphism Extension

After completing the previous parts, create:

Vehicle v1 = new PetrolCar(201, "Honda", "City");

Vehicle v2 = new ElectricCar(202, "Tata", "Nexon EV");

Vehicle v3 = new Bike(203, "Royal Enfield", "Hunter");

Call:

v1.accelerate();
v2.accelerate();
v3.accelerate();

Observe which implementation of "accelerate()" executes.

Then for each of v1, v2 and v3, call one by one:

v1.start();
v1.accelerate();
v1.displayDetails();

(and the same for v2 and v3)

Observe how the same "Vehicle" reference can work with different types of vehicle objects.

---

Concept Identification

After completing the program, identify where each of the following concepts has been used:

Inheritance
Abstract class
Abstract method
Method overriding
Static variable
Static method
Final variable
Final method
Final class
Runtime polymorphism

Explain the purpose of each one using examples from your own program.

---

Challenge

AutoTest Labs introduces a new vehicle:

FlyingCar

A Flying Car should:

- Have all common Vehicle functionality.
- Maintain an altitude.
- Provide "takeOff(int altitude)".
- Provide "land()".
- Provide its own implementation of "accelerate()".

Add "FlyingCar" to the existing program without modifying the basic design of the "Vehicle" class.

Finally, verify that:

Vehicle.getVehicleCount();

correctly reflects the addition of the Flying Car object.*/


// Solution

class notebook3 {
    public static void main(String[] args) {
        // Part C
        PetrolCar p = new PetrolCar(101, "Toyota", "Fortuner");
        ElectricCar e = new ElectricCar(102, "Tesla", "Model 3");
        Bike b = new Bike(103, "Yamaha", "R15");
        p.start();
        e.start();
        b.start();
        p.accelerate();
        p.accelerate();
        e.accelerate();
        e.accelerate();
        e.accelerate();
        b.accelerate();
        b.accelerate();
        b.accelerate();
        b.accelerate();
        p.displayDetails();
        e.displayDetails();
        b.displayDetails();
        Vehicle.getVehicleCount();
        // Part D
        // 1. new Vehicle(104, "ABC", "XYZ") gives error because Vehicle is abstract
        // 2. p.vehicleId = 999 gives error because vehicleId is final
        // 3. start() cannot be overridden in ElectricCar because it is final
        // 4. extending SecurityConfiguration gives error because it is a final class
        // 5. getVehicleCount() is static, so we can call it with the class name
        // Part E - parent reference, child object
        Vehicle v1 = new PetrolCar(201, "Honda", "City");
        Vehicle v2 = new ElectricCar(202, "Tata", "Nexon EV");
        Vehicle v3 = new Bike(203, "Royal Enfield", "Hunter");
        v1.start();
        v1.accelerate();
        v1.displayDetails();
        v2.start();
        v2.accelerate();
        v2.displayDetails();
        v3.start();
        v3.accelerate();
        v3.displayDetails();
        // Challenge
        FlyingCar fc = new FlyingCar(301, "AeroMobil", "AM4");
        fc.takeOff(500);
        fc.land();
        Vehicle.getVehicleCount();
    }
}

abstract class Vehicle {
    final int vehicleId; // cannot be changed
    String brand;
    String model;
    int speed;
    static int vehicleCount = 0; // shared by all vehicles
    Vehicle(int vehicleId, String brand, String model) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        vehicleCount++;
    }
    final void start() {
        System.out.println("Vehicle safety check completed");
        System.out.println("Vehicle started");
    }
    void stop() {
        speed = 0;
        System.out.println("Vehicle stopped");
    }
    abstract void accelerate();
    void displayDetails() {
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println(brand + " " + model + " : " + speed + " km/h");
        System.out.println();
    }
    static void getVehicleCount() {
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
    }
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
        batteryLevel = batteryLevel + percentage;
    }
    void accelerate() {
        speed = speed + 20;
    }
}

class Bike extends Vehicle {
    boolean helmetAvailable = true;
    Bike(int vehicleId, String brand, String model) {
        super(vehicleId, brand, model);
    }
    void accelerate() {
        speed = speed + 5;
    }
}

class FlyingCar extends Vehicle {
    int altitude;
    FlyingCar(int vehicleId, String brand, String model) {
        super(vehicleId, brand, model);
    }
    void takeOff(int altitude) {
        this.altitude = altitude;
        System.out.println("Flying at " + altitude + " m");
    }
    void land() {
        altitude = 0;
        System.out.println("Landed");
    }
    void accelerate() {
        speed = speed + 50;
    }
}

final class SecurityConfiguration {
    int maximumSpeedLimit = 200;
    String testingCenterCode = "ATL01";
    String safetyStandard = "AIS-100";
}
