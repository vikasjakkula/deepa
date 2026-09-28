/*

Practice Question: Hotel Room Booking System

A hotel named GrandStay wants to develop a Java program to manage room bookings.

The hotel currently has the following types of rooms:

- Standard Room
- Deluxe Room
- Suite Room

More room types may be added in the future.

Every room has some common information:

- Room Number
- Guest Name
- Number of Nights
- Price Per Night

---

Requirements

1. Every room gets a room number when it is booked. Once assigned, the room
   number must never be changed.

2. The hotel name "GrandStay" is common to all rooms. It should be stored only
   once and should be accessible without creating a room object.

3. The guest's ID proof number is confidential and should not be directly
   accessible from outside the room class. Provide a method to update it.

4. The room category should be accessible within the room class, its
   subclasses, and classes in the same package.

5. The floor number should be accessible only to classes in the same package.

6. Every room type calculates its bill differently, so calculateBill() should
   be declared in the parent class, but each room type must provide its own
   implementation.

7. The general Room class should not be used to create objects directly.

   For example, the following should not be allowed:

   Room r = new Room(101, "Ravi", 2, 1500);

8. All rooms follow the same check-in procedure. When a guest checks in, display:

   ID Verified
   Guest Checked In

   Child classes should not be allowed to change this procedure.

9. The hotel wants to know how many rooms have been booked. The count should
   belong to the class, and should be displayed using:

   Room.getBookingCount();

10. The hotel has a class called HotelRules which stores:

   Check-in Time
   Check-out Time
   Maximum Guests Per Room

   Objects of HotelRules can be created, but no class should be allowed to
   inherit from HotelRules.

---

Room Types

Standard Room
- Bill = nights x price per night

Deluxe Room
- Maintains: breakfastIncluded (true / false)
- Bill = nights x price per night + 500 per night if breakfast is included

Suite Room
- Maintains: serviceCharge
- Bill = nights x price per night + serviceCharge
- A 10% discount is given on the total bill if the guest stays 5 nights or more

---

Methods

Provide the following methods:

- checkIn()
- calculateBill()
- updateIdProof(String idProof)
- displayDetails() -> displays hotel name, room number, guest name, category,
  floor number, nights and bill amount (each on a separate line)
- static getBookingCount()

---

Main Method

Create the following objects:

StandardRoom s = new StandardRoom(101, "Ravi", 2, 1500);
DeluxeRoom d = new DeluxeRoom(205, "Anita", 3, 3000, true);
SuiteRoom st = new SuiteRoom(501, "Kiran", 5, 8000, 2000);

Perform the following:

1. Check in all three guests.
2. Update the ID proof of Ravi.
3. Display the details of all three rooms.
4. Display the total number of bookings.

Expected bills:

Standard Room : 3000.0
Deluxe Room   : 10500.0
Suite Room    : 37800.0

Expected:

Total Bookings: 3

---

Part B - Predict the Errors

Try each statement separately and explain the result.

1. Room r = new Room(101, "Ravi", 2, 1500);
2. s.roomNumber = 999;
3. Overriding checkIn() inside DeluxeRoom
4. class VipRules extends HotelRules { }
5. Why can Room.getBookingCount() be called using the class name?

---

Part C - Polymorphism

Store all three rooms in:

Room[] rooms = new Room[3];

Use a loop to call checkIn(), calculateBill() and displayDetails() for every room.
Observe which calculateBill() runs for each object.

---

Challenge

The hotel introduces a new room type:

FamilyRoom

A Family Room:
- Maintains extraBeds
- Bill = nights x price per night + 800 per extra bed per night
- Should inherit all common Room functionality

Add FamilyRoom without changing the Room class, and verify that
Room.getBookingCount() also counts FamilyRoom objects.

*/
