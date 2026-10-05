/*
In Java, an interface is used to define a contract that classes agree to follow.

An interface mainly tells a class:

“If you implement me, you must provide these operations.”

For example, suppose different objects in your application must support payment.

interface Payment { void pay(double amount); }

The interface says that every payment type must provide the pay(double amount) method.

Different classes can implement it differently.

class CreditCardPayment implements Payment {

public void pay(double amount) {
    System.out.println("Paid " + amount + " using Credit Card");
}

}

class UPIPayment implements Payment {

public void pay(double amount) {
    System.out.println("Paid " + amount + " using UPI");
}

}

The keyword implements is used with interfaces.

class UPIPayment implements Payment

This means that UPIPayment agrees to provide all the required operations defined by Payment.

An interface itself cannot normally be instantiated.

Payment p = new Payment();   // Error

However, an interface reference can point to an object of a class that implements the interface.

Payment p = new UPIPayment();

Now we can write:

p.pay(500);

The output will be:

Paid 500.0 using UPI

This connects interfaces directly to polymorphism.

For example:

Payment p1 = new CreditCardPayment(); Payment p2 = new UPIPayment();

p1.pay(1000); p2.pay(500);

Both references are of type Payment, but they produce different behavior depending on the actual object.

This means:

One interface reference type Many implementing classes Different behavior

We can also write a method such as:

static void makePayment(Payment payment, double amount) { payment.pay(amount); }

Now we can call:

makePayment(new CreditCardPayment(), 1000); makePayment(new UPIPayment(), 500);

The makePayment() method does not need to know exactly how the payment is performed.

It only needs an object that follows the Payment contract.
*/