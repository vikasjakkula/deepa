/*A courier company is developing a Package Delivery Management System.
The system should follow these requirements:
1.	Every package must have a unique tracking ID that cannot be changed after registration. 
2.	The courier company name is common to all packages. 
3.	The company name should be stored only once and accessed without creating a package object. 
4.	Sender and receiver contact information must be confidential. 
5.	The package category should be accessible within the package class, subclasses, and same package. 
6.	The delivery hub number should be accessible only within the courier package. 
7.	Different types of packages calculate delivery charges differently. 
8.	The package class should define the common requirement for delivery-charge calculation. 
9.	The package class should act as a base class and should not be directly instantiated. 
10.	The system should support: 
•	DomesticPackage 
•	InternationalPackage 
11.	Provide updateReceiverDetails(). 
12.	Provide displayPackageDetails() showing tracking ID, company name, category, hub number, receiver information, and delivery charge. 
Task
Write a complete Java program satisfying all requirements and demonstrate:


class DomesticPackage extends PackageDelivery {
    @Override
    private void calculateDeliveryCharge() {
        System.out.println("Domestic package delivery charge: " + deliveryCharge);
    }
}

class InternationalPackage extends PackageDelivery {
    @Override
    private void calculateDeliveryCharge() {
        System.out.println("International package delivery charge: " + deliveryCharge);
    }
}

abstract class PackageDelivery {
    private int trackid;
    private static String companyName;
    private String senderContact;
    private String receiverContact;
    protected String category;
    int hubNumber;
    
    private void updateReceiverDetails() {
        
    }
    private void displayPackageDetails() {
        System.out.println("tracking ID: "+ trackid);
        System.out.println("company name: " + companyName);
        System.out.println("receiver information: " + senderContact);
        System.out.println("receiver information: " + receiverContact);
        System.out.println("category: " + category);
        System.out.println("hub number: " + hubNumber);
    }
    
    abstract class calculateDeliveryCharge {
        super(trackid, companyName, category, hubNumber, receiverInfo);
    }
}*/


// Mistakes in the above code:
// 1. private methods cannot be overridden
// 2. calculateDeliveryCharge should be an abstract method, not an abstract class
// 3. super() can only be used inside a constructor
// 4. trackid should be final
// 5. there is no constructor and deliveryCharge is not declared

// Solution

class notebook10 {
    public static void main(String[] args) {
        PackageDelivery.displayCompanyName();
        System.out.println();

        PackageDelivery p1 = new DomesticPackage("DOM101", "Ravi", "Sneha", 12, 5);
        PackageDelivery p2 = new InternationalPackage("INT201", "Arjun", "John", 3, 2);

        p1.displayPackageDetails();
        p2.displayPackageDetails();

        p1.updateReceiverDetails("Sneha (new address)");
        p1.displayPackageDetails();
    }
}

abstract class PackageDelivery {
    private final String trackingId; // cannot be changed
    static String companyName = "SpeedPost Couriers"; // stored only once
    private String senderContact; // confidential
    private String receiverContact; // confidential
    protected String category;
    int hubNumber; // default, only same package
    double weight;

    PackageDelivery(String trackingId, String senderContact, String receiverContact, int hubNumber, double weight) {
        this.trackingId = trackingId;
        this.senderContact = senderContact;
        this.receiverContact = receiverContact;
        this.hubNumber = hubNumber;
        this.weight = weight;
    }

    static void displayCompanyName() {
        System.out.println("Company: " + companyName);
    }

    abstract double calculateDeliveryCharge();

    void updateReceiverDetails(String receiverContact) {
        this.receiverContact = receiverContact;
    }

    void displayPackageDetails() {
        System.out.println("Tracking ID: " + trackingId);
        System.out.println("Company Name: " + companyName);
        System.out.println("Category: " + category);
        System.out.println("Hub Number: " + hubNumber);
        System.out.println("Sender: " + senderContact);
        System.out.println("Receiver: " + receiverContact);
        System.out.println("Delivery Charge: " + calculateDeliveryCharge());
        System.out.println();
    }
}

class DomesticPackage extends PackageDelivery {

    DomesticPackage(String trackingId, String sender, String receiver, int hubNumber, double weight) {
        super(trackingId, sender, receiver, hubNumber, weight);
        category = "Domestic";
    }

    @Override
    double calculateDeliveryCharge() {
        return weight * 50;
    }
}

class InternationalPackage extends PackageDelivery {

    InternationalPackage(String trackingId, String sender, String receiver, int hubNumber, double weight) {
        super(trackingId, sender, receiver, hubNumber, weight);
        category = "International";
    }

    @Override
    double calculateDeliveryCharge() {
        return weight * 800 + 1500;
    }
}
