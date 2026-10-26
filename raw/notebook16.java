/*
Problem Statement 1: Problem Statement: EcoHarvest – Agricultural Produce Ordering System
1. Agricultural Produce Management
EcoHarvest is an agricultural produce ordering system that supplies grains, vegetables, fruits, and spices from local farmers. Develop a Java application using object-oriented programming concepts to manage produce details, stock availability, customer orders, discounts, and billing.
Create a parent class named FarmProduce containing the following fields:
•	produceId
•	produceName
•	pricePerKg
•	availableKg
•	farmerName
Include a constructor to initialize these fields and methods to display produce details, calculate the total stock value, and compare two produce objects based on their produce IDs.
2. Produce Categories
Create the following subclasses by extending the FarmProduce class.
Subclass	Additional Fields	Discount
Grain	grainType, organic	5%
Vegetable	season, perishable	10%
Fruit	fruitVariety, grade	8%
Spice	spiceType, origin	12%
Each subclass must contain a constructor to initialize its additional fields along with the inherited fields. Override the payable amount calculation method to apply the appropriate discount.
3. Farmer and Produce Details
Associate each produce item with a farmer using the farmerName field.
Use suitable farmer names and additional attribute values for the four produce items. For example, organic rice may be associated with a local grain farmer, while fresh tomatoes may be associated with a vegetable farmer.
Display the farmer's name and all category-specific attributes whenever produce details are displayed.
4. Initial Produce Inventory
Create and initialize the following produce objects. Use suitable values for their additional fields.
Produce	ID	Price per kg	Available Stock
Organic Rice	701	₹60	100 kg
Fresh Tomatoes	702	₹40	80 kg
Alphonso Mangoes	703	₹120	50 kg
Turmeric Powder	704	₹200	30 kg
Display the produce ID, name, price per kilogram, available stock, farmer name, category-specific attributes, and discount percentage.
5. Produce Collection and Inventory Statistics
Store all produce objects in a collection, such as an ArrayList<FarmProduce>.
Implement methods to:
•	Display all available produce details.
•	Calculate the total number of produce types.
•	Calculate the total available stock in kilograms.
•	Calculate the total inventory value using the price per kilogram and available stock.
The initial inventory statistics must be:
•	Total produce types: 4
•	Total stock: 260 kg
•	Total stock value: ₹20,000
Calculate the total stock value by multiplying the price per kilogram by the available kilograms for each produce item and adding the results.
6. Produce Searching and Comparison
Implement the following operations:
1.	Search for produce using its ID.
2.	Search for produce by name without considering uppercase and lowercase differences.
3.	Display an appropriate message when the requested produce is not found.
4.	Compare two produce objects based on their produce IDs.
5.	Display whether the two objects represent the same produce item.
Implement suitable methods in the FarmProduce class to perform the comparison.
7. Customer Order Cart Management
Create an order cart to maintain the produce selected by a customer.
Each cart entry must contain the produce details and the ordered quantity in kilograms.
The application must support the following operations:
•	Add produce to the order cart.
•	Increase the quantity when the same produce is ordered again.
•	Display the produce names and quantities in the cart.
•	Calculate the subtotal, discount, and final amount for each entry.
•	Calculate the total quantity ordered and the final bill.
Repeated orders for the same produce must be merged into a single cart entry.
8. Order Quantity Validation and Confirmation
Use if-else statements to validate every order request.
The application must reject an order when:
•	The requested quantity is zero or negative.
•	The requested quantity exceeds the available stock.
•	The requested produce does not exist in the inventory.
A rejected order must not change the cart or available stock. Update the stock only after a valid order has been confirmed.
Exception handling is not required.
9. Repeated Produce Orders
Demonstrate repeated ordering of the same produce item.
When a customer first orders 10 kg of Organic Rice and subsequently orders an additional 5 kg, the application must merge both orders into one cart entry.
The final cart entry must contain:
•	Produce name: Organic Rice
•	Total quantity: 15 kg
•	Price per kg: ₹60
•	Discount: 5%
Calculate the bill using the combined quantity rather than treating the two orders as separate entries.
10. Produce Price and Discount Calculation
Calculate the bill using the following formulas:
•	Subtotal = Price per kg × Quantity ordered
•	Discount amount = Subtotal × Discount percentage / 100
•	Final amount = Subtotal − Discount amount
Apply the discount according to the produce category.
Produce Category	Discount
Grain	5%
Vegetable	10%
Fruit	8%
Spice	12%
For example, ordering 15 kg of Organic Rice must produce the following calculation:
•	Subtotal = ₹60 × 15 = ₹900
•	Discount = ₹900 × 5 / 100 = ₹45
•	Final amount = ₹900 − ₹45 = ₹855
Display the subtotal, discount percentage, discount amount, and final amount for every cart entry.
11. Sellable Interface Implementation
Create an interface named Sellable containing a method to calculate the payable amount.
The FarmProduce parent class must implement the Sellable interface. Each subclass must override the calculation method to apply its respective discount.
Use suitable return types and method parameters to calculate the payable amount based on the ordered quantity.
12. Inheritance, Method Overriding, and Runtime Polymorphism
Demonstrate inheritance by deriving Grain, Vegetable, Fruit, and Spice from the FarmProduce class.
Override the payable amount calculation method in every subclass.
Store subclass objects in parent-class references and invoke the calculation method. The appropriate subclass implementation must execute at runtime.
Display the calculated payable amounts to demonstrate runtime polymorphism.
13. Customer Ordering Scenario
Assume a customer places the following order:
1.	10 kg of Organic Rice.
2.	5 kg of Fresh Tomatoes.
3.	3 kg of Alphonso Mangoes.
4.	2 kg of Turmeric Powder.
5.	An additional 5 kg of Organic Rice.
Add these orders to the cart in the given sequence.
The application must merge the repeated Organic Rice order into one entry.
After processing all orders, display the cart, total quantity ordered, final bill, and updated stock after order confirmation.
14. Expected Order Cart
The order cart must contain four entries after all valid orders have been added.
Produce	Quantity	Price per kg
Organic Rice	15 kg	₹60
Fresh Tomatoes	5 kg	₹40
Alphonso Mangoes	3 kg	₹120
Turmeric Powder	2 kg	₹200
Total	25 kg	
Display the produce name, ordered quantity, price per kilogram, subtotal, discount, and final amount for every cart entry.
15. Expected Customer Bill
Generate the final bill using the ordered quantities and produce-specific discounts.
Produce	Quantity	Subtotal	Discount	Final Amount
Organic Rice	15 kg	₹900	₹45	₹855
Fresh Tomatoes	5 kg	₹200	₹20	₹180
Alphonso Mangoes	3 kg	₹360	₹28.80	₹331.20
Turmeric Powder	2 kg	₹400	₹48	₹352
Total	25 kg	₹1,860	₹141.80	₹1,718.20
Display the subtotal, discount amount, and final payable amount for each produce item. At the end, display the total subtotal, total discount, and final amount payable by the customer.
16. Updated Produce Stock After Ordering
After confirming the order, update the available stock.
Produce	Initial Stock	Ordered Quantity	Remaining Stock
Organic Rice	100 kg	15 kg	85 kg
Fresh Tomatoes	80 kg	5 kg	75 kg
Alphonso Mangoes	50 kg	3 kg	47 kg
Turmeric Powder	30 kg	2 kg	28 kg
Total	260 kg	25 kg	235 kg
Display the updated stock after the order is confirmed.
Calculate the remaining total stock and the total inventory value using the updated quantities.
17. Insufficient Stock and Invalid Order Validation
After completing the above order, only 85 kg of Organic Rice remain available.
Attempt to order 90 kg of Organic Rice. The application must reject the order and display:
Order rejected: Insufficient stock available.
The available rice stock must remain 85 kg, and the rejected order must not be added to the cart.
Also test the following invalid operations:
•	Attempt to order zero kilograms.
•	Attempt to order a negative quantity.
•	Attempt to order more than the available stock.
•	Search for a produce ID or name that does not exist.
Display appropriate messages and verify that invalid orders do not modify the cart or available stock.
18. Produce-Specific Attributes and Polymorphism
Display the additional attributes of each produce category.
•	Grains: grain type and organic status.
•	Vegetables: season and perishability.
•	Fruits: fruit variety and grade.
•	Spices: spice type and origin.
Use overridden display methods or suitable category-specific methods wherever appropriate.
Demonstrate polymorphism by accessing different produce objects through FarmProduce references and invoking their overridden methods.
19. Independent Produce Object Copying
Create an independent copy of the Organic Rice object to demonstrate object copying.
Initially, the copied object must contain the same produce details as the original. Change the available stock in the copied object to a different value and display both objects.
For example:
•	Original rice stock: 85 kg
•	Copied rice stock initially: 85 kg
•	Modified copied rice stock: 150 kg
Verify that modifying the copied object does not change the original object's available stock.
The copied object must not be added to the actual produce collection, order cart, stock calculations, or final bill.
20. Main Application and Student Deliverables
Implement the main() method to perform the following operations in sequence:
1.	Create and initialize all produce objects.
2.	Store the produce objects in a collection and display their details.
3.	Display initial inventory statistics.
4.	Search for produce by ID and name.
5.	Compare produce objects using their IDs.
6.	Create the order cart and process the specified orders.
7.	Merge repeated orders for the same produce.
8.	Validate order quantities and stock availability.
9.	Calculate discounts and payable amounts using runtime polymorphism.
10.	Display the order cart and final bill.
11.	Confirm the order and update the stock.
12.	Display updated stock statistics.
13.	Test invalid orders and verify that stock remains unchanged.
14.	Demonstrate independent produce object copying.
Student Deliverables: Develop a complete Java program using classes, objects, constructors, inheritance, interfaces, method overriding, runtime polymorphism, collections, searching, object comparison, order cart management, validation, stock updates, discount calculations, billing, and independent object copying. Include all required classes and the main() method. Display clear output for each operation and ensure that all expected totals and validation rules are satisfied.


2. Problem Statement 2: PawPal – Pet Grooming Service Management System
1. Core Service Management
Develop a Java application named PawPal to manage pet grooming services, customer bookings, service charges, discounts, and appointment slot availability.
Create a parent class named GroomingService with the following fields:
•	serviceId – unique service identification number
•	serviceName – name of the grooming service
•	basePrice – price per appointment
•	availableSlots – number of available appointment slots
•	groomerName – name of the assigned groomer
Provide constructors to initialize these fields and methods to display service details, calculate the service charge, and manage available slots.
2. Service Categories and Inheritance
Create the following subclasses by extending GroomingService.
Subclass	Additional Fields	Discount
BathingService	waterType, shampooType	5%
HairStylingService	styleType, coatLength	10%
DentalCareService	cleaningMethod, recommendedAge	8%
FullGroomingService	includesBath, includesNailCare	12%
Each subclass must have a constructor to initialize its additional fields along with the inherited fields.
3. Service-Specific Details
Initialize the services with suitable values for all additional fields.
Service	ID	Price per Appointment	Available Slots
Pet Bathing	801	₹600	20
Hair Styling	802	₹900	15
Dental Cleaning	803	₹1,200	10
Full Grooming	804	₹1,500	8
Assign a suitable groomer name to each service.
4. Initial Service Collection
Store all service objects in a collection, such as an ArrayList<GroomingService>.
Display the details of every service, including its ID, name, base price, available slots, groomer name, and category-specific information.
Calculate and display the total number of available appointment slots across all services.
5. Collection Statistics
Implement functionality to:
•	Count the number of available service categories.
•	Calculate the total available appointment slots.
•	Display the base price of each service.
•	Identify the service with the highest base price.
•	Display the service with the lowest number of available slots.
6. Service Searching and Comparison
Implement the following operations:
•	Search for a service using its serviceId.
•	Search for a service using its name, ignoring uppercase and lowercase differences.
•	Compare two service objects based on their service IDs.
•	Display an appropriate message when a service is not found.
For example, searching for pet bathing, Pet Bathing, or PET BATHING should identify the same service.
7. Booking Cart Management
Create a booking cart to store the services selected by a customer and their booking quantities.
Each booking entry must contain the selected service and the number of appointments requested.
Implement the following operations:
•	Add a service to the booking cart.
•	Display all services currently in the cart.
•	Display the number of appointments booked for each service.
•	Calculate the total number of appointments in the cart.
•	Remove a booking if required.
If the customer books the same service more than once, merge the quantities into a single cart entry instead of creating duplicate entries.
8. Booking Validation and Confirmation
Validate every booking before adding it to the cart.
•	Reject zero or negative appointment quantities.
•	Reject bookings for nonexistent service IDs.
•	Reject bookings that exceed the available appointment slots.
•	Do not modify the cart or available slots when a booking is rejected.
•	Update available slots only after the booking is successfully confirmed.
Display suitable messages for successful and rejected booking requests.
9. Repeated Booking Management
Demonstrate merging repeated bookings for the same service.
For example, if a customer initially books 1 Pet Bathing appointment and later books 1 more Pet Bathing appointment, the cart must contain a single Pet Bathing entry with a quantity of 2.
The application must validate the combined quantity against the available slots before confirming an additional booking.
10. Service Charge and Discount Calculation
Calculate the amount payable for each booking using the following formulas:
Subtotal
Subtotal = Base Price × Number of Appointments
Discount
Discount = Subtotal × Discount Percentage / 100
Final Service Charge
Final Charge = Subtotal − Discount
Apply the appropriate discount to each service:
•	Pet Bathing: 5%
•	Hair Styling: 10%
•	Dental Cleaning: 8%
•	Full Grooming: 12%
Display the subtotal, discount amount, and final charge for each service.
11. Interface Implementation
Create an interface named Bookable containing a method to calculate the service charge.
Implement the Bookable interface in the GroomingService class. Each subclass must override the calculation method to apply its respective discount.
Use the interface reference to invoke the service charge calculation method.
12. Inheritance, Method Overriding, and Runtime Polymorphism
Demonstrate the following object-oriented programming concepts:
•	Inheritance: All four service categories inherit common fields and methods from GroomingService.
•	Method overriding: Each subclass overrides the service charge calculation method.
•	Runtime polymorphism: Store different subclass objects in GroomingService references and invoke the overridden method.
•	Interface-based programming: Use Bookable references to calculate the charges for different services.
The discount applied must depend on the actual service object being processed.
13. Booking Scenario
A customer visits PawPal and books the following appointments:
Service	Number of Appointments	Price per Appointment
Pet Bathing	2	₹600
Hair Styling	1	₹900
Dental Cleaning	1	₹1,200
Full Grooming	2	₹1,500
Process the bookings, merge repeated bookings for the same service where applicable, validate slot availability, and generate the final bill.
Calculate the subtotal and discount separately for every service before calculating the overall bill.
14. Expected Booking Summary
The application must display the following booking summary:
========== PAWPAL ==========

Booking Summary:

Pet Bathing       2   Rs.1200.00
Hair Styling      1   Rs. 900.00
Dental Cleaning   1   Rs.1200.00
Full Grooming     2   Rs.3000.00

Total Appointments: 6
Subtotal: Rs.6300.00
Total Discount: Rs.606.00
Final Amount: Rs.5694.00
15. Expected Bill Calculation
Calculate the bill using the following values:
Service	Subtotal	Discount Rate	Discount	Final Charge
Pet Bathing	₹1,200.00	5%	₹60.00	₹1,140.00
Hair Styling	₹900.00	10%	₹90.00	₹810.00
Dental Cleaning	₹1,200.00	8%	₹96.00	₹1,104.00
Full Grooming	₹3,000.00	12%	₹360.00	₹2,640.00
Total	₹6,300.00	—	₹606.00	₹5,694.00
The total number of appointments is 6, the subtotal is ₹6,300.00, the total discount is ₹606.00, and the final amount payable is ₹5,694.00.
16. Updated Appointment Availability
After confirming the bookings, update the available slots as follows:
Service	Initial Slots	Booked Appointments	Remaining Slots
Pet Bathing	20	2	18
Hair Styling	15	1	14
Dental Cleaning	10	1	9
Full Grooming	8	2	6
Display the remaining slots for every service after the booking process is completed.
17. Insufficient Slots and Invalid Input
Test the application using the following cases:
1.	Attempt to book 19 Pet Bathing appointments when only 18 slots remain. Reject the booking.
2.	Attempt to book 0 Hair Styling appointments. Reject the booking.
3.	Attempt to book -2 Dental Cleaning appointments. Reject the booking.
4.	Attempt to book a service using a nonexistent service ID. Display an appropriate error message.
5.	Attempt to add more appointments than the available slots. Reject the booking without modifying the cart or availability.
After every rejected booking, verify that the previously confirmed bookings and remaining slot counts are unchanged.
18. Service Attributes and Polymorphic Behaviour
Display the additional fields of each service category:
•	BathingService: water type and shampoo type.
•	HairStylingService: style type and coat length.
•	DentalCareService: cleaning method and recommended age.
•	FullGroomingService: whether bathing and nail care are included.
Use overridden methods to calculate the discounted service charges without writing separate billing logic for each category in the main method.
19. Independent Service Object Copying
Create an independent copy of an existing service object, such as Pet Bathing.
Demonstrate the following:
1.	Create the original Pet Bathing object with its current available slot count.
2.	Create a separate copy of the object.
3.	Change the available slots in the copied object.
4.	Display the slot counts of both objects.
5.	Verify that changing the copied object's slots does not change the original object's slots.
For this demonstration, ensure that the copied object is independent of the original object. The copied object must not be added to the active service collection or included in booking and billing calculations.
20. Main Application and Student Deliverables
Develop a main class named PawPal containing the main() method.
The application must demonstrate:
•	Constructors and object creation.
•	Inheritance using the parent class and four subclasses.
•	Interface implementation using Bookable.
•	Method overriding and runtime polymorphism.
•	Collections for storing and managing services.
•	Searching by service ID and case-insensitive name.
•	Comparing service objects using their IDs.
•	Booking cart creation and repeated booking merging.
•	Booking validation and confirmation.
•	Discount calculation and bill generation.
•	Updating appointment slot availability.
•	Handling invalid booking quantities and insufficient slots.
•	Creating and modifying an independent object copy.
Student Requirement: Implement all the required classes, the Bookable interface, and the main() method. Display the booking summary, bill, and remaining appointment slots in the specified format. Exception handling is not required.

3. Problem Statement 3: ParcelPro – Courier Shipment Management System
1. Core Shipment Management
Develop a Java application named ParcelPro to manage courier shipments, calculate delivery charges, apply discounts, and generate shipment invoices.
Create a parent class named CourierShipment with the following fields:
•	shipmentId – unique identification number of the shipment service
•	destination – delivery destination
•	weightKg – weight of each parcel in kilograms
•	ratePerKg – shipping rate per kilogram
•	courierCompany – name of the courier company
Provide constructors to initialize these fields and methods to display shipment details, calculate shipping charges, and manage shipment information.
2. Shipment Categories and Inheritance
Create the following subclasses by extending CourierShipment.
Subclass	Additional Fields	Discount
LocalShipment	deliveryZone, sameDay	0%
NationalShipment	destinationState, trackingEnabled	5%
ExpressShipment	priorityLevel, guaranteedDelivery	8%
InternationalShipment	destinationCountry, customsRequired	10%
Each subclass must have a constructor to initialize its additional fields along with the inherited fields.
3. Shipment Service Details
Initialize the shipment services with the following details:
Service	ID	Rate per kg
Local Courier	901	₹50
National Courier	902	₹80
Express Courier	903	₹120
International Courier	904	₹250
Assign suitable courier company names and initialize the additional fields for each shipment category.
4. Initial Shipment Collection
Store all shipment service objects in a collection, such as an ArrayList<CourierShipment>.
Display the details of each available service, including its shipment ID, service name, rate per kilogram, courier company, and category-specific information.
5. Collection Statistics
Implement functionality to:
•	Count the available shipment service categories.
•	Display the rate per kilogram for each service.
•	Identify the service with the highest rate per kilogram.
•	Identify the service with the lowest rate per kilogram.
6. Shipment Service Searching and Comparison
Implement the following operations:
•	Search for a shipment service using its shipmentId.
•	Search for a shipment service using its name, ignoring uppercase and lowercase differences.
•	Compare two shipment service objects based on their shipment IDs.
•	Display an appropriate message when a service cannot be found.
For example, searching for local courier, Local Courier, or LOCAL COURIER should identify the same service.
7. Shipment Cart Management
Create a shipment cart to store customer shipment orders. Each order must contain:
•	The selected shipment service.
•	The destination.
•	The weight of each parcel.
•	The number of parcels.
•	The calculated shipping charge.
The cart must support multiple shipment types.
Implement functionality to add shipments, display cart details, and calculate the total number of parcels.
If a customer places repeated orders for the same service with the same destination and parcel weight, merge the orders into one entry by adding their parcel quantities.
8. Shipment Validation and Confirmation
Validate every shipment order before adding it to the cart.
•	Reject zero or negative parcel quantities.
•	Reject zero or negative parcel weights.
•	Reject orders for nonexistent shipment service IDs.
•	Reject repeated entries if their destination or parcel weight is incompatible with the existing entry for that service.
•	Confirm valid orders before updating the shipment cart.
When an order is rejected, do not modify the existing cart entries or their quantities.
Display suitable messages for successful and rejected shipment orders.
9. Repeated Shipment Order Management
Demonstrate merging repeated shipment orders.
For example, if a customer initially orders 1 local parcel weighing 3 kg for a particular destination and later orders 1 more local parcel weighing 3 kg for the same destination, the cart must contain a single entry with 2 parcels.
However, if the second order has a different destination or parcel weight, it must not be merged into that entry. Reject the incompatible repeated entry as required by the validation rules.
10. Shipping Charge and Discount Calculation
Calculate shipping charges using the following formulas:
Subtotal
Subtotal = Rate per kg × Weight per parcel × Number of parcels
Discount
Discount = Subtotal × Discount Percentage / 100
Final Shipping Charge
Final Charge = Subtotal − Discount
Apply the following discounts:
•	Local Courier: 0%
•	National Courier: 5%
•	Express Courier: 8%
•	International Courier: 10%
Display the subtotal, discount amount, and final shipping charge for each shipment category.
11. Interface Implementation
Create an interface named Shippable containing a method to calculate shipping charges.
Implement the Shippable interface in the CourierShipment class. Each subclass must override the calculation method to apply the appropriate discount.
Use an interface reference to invoke the shipping charge calculation method for different shipment types.
12. Inheritance, Method Overriding, and Runtime Polymorphism
Demonstrate the following object-oriented programming concepts:
•	Inheritance: All four shipment categories inherit common fields and methods from CourierShipment.
•	Method overriding: Each subclass overrides the shipping charge calculation method.
•	Runtime polymorphism: Store different subclass objects in CourierShipment references and invoke the overridden method.
•	Interface-based programming: Use Shippable references to calculate shipping charges for different shipment categories.
The discount applied must depend on the actual shipment object being processed.
13. Shipment Scenario
A customer places the following shipment orders:
Shipment Service	Number of Parcels	Weight per Parcel
Local Courier	2	3 kg
National Courier	1	5 kg
Express Courier	2	2 kg
International Courier	1	4 kg
Process the orders, validate the parcel quantities and weights, and generate the shipment invoice.
Calculate the subtotal and discount separately for each shipment category before calculating the overall bill.
14. Expected Shipment Summary
The application must display the following summary:
========== PARCELPRO ==========

Shipment Summary:

Local Courier          2 parcels   Rs. 300.00
National Courier       1 parcel    Rs. 400.00
Express Courier        2 parcels   Rs. 480.00
International Courier  1 parcel    Rs.1000.00

Total Parcels: 6
Subtotal: Rs.2180.00
Total Discount: Rs.216.40
Final Amount: Rs.1963.60
15. Expected Invoice Calculation
Calculate the invoice using the following values:
Service	Subtotal	Discount Rate	Discount	Final Charge
Local Courier	₹300.00	0%	₹0.00	₹300.00
National Courier	₹400.00	5%	₹20.00	₹380.00
Express Courier	₹480.00	8%	₹38.40	₹441.60
International Courier	₹1,000.00	10%	₹100.00	₹900.00
Total	₹2,180.00	—	₹158.40	₹2,021.60
Important: The expected output in Section 14 contains an arithmetic inconsistency. Based on the specified discount rates, the correct total discount is ₹158.40, and the correct final amount is ₹2,021.60, not ₹216.40 and ₹1,963.60. The program must calculate the invoice using the stated discount rates.
16. Shipment Order Details
Display the following information for every confirmed shipment order:
•	Shipment service name and ID.
•	Destination.
•	Number of parcels.
•	Weight per parcel.
•	Total shipment weight.
•	Rate per kilogram.
•	Subtotal, discount, and final shipping charge.
For each cart entry, calculate the total shipment weight as:
Total Shipment Weight = Weight per Parcel × Number of Parcels
17. Invalid Quantities, Weights, and Services
Test the application using the following cases:
1.	Attempt to book 0 parcels. Reject the order.
2.	Attempt to book -2 parcels. Reject the order.
3.	Attempt to ship a parcel weighing 0 kg. Reject the order.
4.	Attempt to ship a parcel with a negative weight. Reject the order.
5.	Attempt to select a nonexistent shipment service ID. Display an appropriate error message.
6.	Attempt to add a repeated order with a different destination or parcel weight. Reject the incompatible entry without modifying the existing cart.
Verify that rejected orders do not affect previously confirmed shipment orders or their calculated charges.
18. Shipment Attributes and Polymorphic Behaviour
Display the additional fields for each shipment category:
•	LocalShipment: delivery zone and whether same-day delivery is available.
•	NationalShipment: destination state and whether tracking is enabled.
•	ExpressShipment: priority level and whether delivery is guaranteed.
•	InternationalShipment: destination country and whether customs processing is required.
Use overridden methods to calculate the discounted shipping charges without writing separate billing logic for each category in the main method.
19. Independent Shipment Service Object Copying
Create an independent copy of an existing shipment service object, such as LocalShipment.
Demonstrate the following:
1.	Create the original shipment service object.
2.	Create a separate copy of the object.
3.	Modify a field in the copied object, such as ratePerKg or courierCompany.
4.	Display the values of the original and copied objects.
5.	Verify that modifying the copied object does not affect the original object.
Ensure that the copied object is independent of the original object and is not added to the active shipment service collection or included in invoice calculations.
20. Main Application and Student Deliverables
Develop a main class named ParcelPro containing the main() method.
The application must demonstrate:
•	Constructors and object creation.
•	Inheritance using the parent class and four subclasses.
•	Interface implementation using Shippable.
•	Method overriding and runtime polymorphism.
•	Collections for storing and managing shipment services.
•	Searching by shipment ID and case-insensitive service name.
•	Comparing shipment service objects based on their IDs.
•	Shipment cart creation and repeated order merging.
•	Validation of parcel quantities, weights, destinations, and service IDs.
•	Shipping charge and discount calculation.
•	Invoice generation.
•	Independent shipment service object copying.
Student Deliverables: Implement all the required classes, the Shippable interface, and the main() method. Display the shipment summary and invoice in the specified format, using the correct discount calculations. Exception handling is not required.

4. Problem Statement 4: SolarWise – Solar Panel Maintenance Management System
1. Core Service Management
Develop a Java application named SolarWise to manage solar maintenance services, customer bookings, service charges, discounts, and technician availability.
Create a parent class named EnergyService with the following fields:
•	serviceId – unique identification number of the service
•	serviceName – name of the maintenance service
•	basePrice – price per service booking
•	availableSlots – number of available technician appointment slots
•	technicianName – name of the assigned technician
Provide constructors to initialize these fields and methods to display service details, calculate service charges, and update available slots.
2. Service Categories and Inheritance
Create the following subclasses by extending EnergyService.
Subclass	Additional Fields	Discount
PanelInspection	panelCount, inspectionType	5%
InverterService	inverterCapacity, firmwareUpdate	8%
BatteryMaintenance	batteryType, backupTest	10%
SystemAudit	systemCapacity, performanceReport	12%
Each subclass must have a constructor to initialize its additional fields along with the inherited fields.
3. Service-Specific Details
Initialize the services with the following details:
Service	ID	Price	Available Slots
Panel Inspection	1001	₹1,500	10
Inverter Servicing	1002	₹2,500	8
Battery Maintenance	1003	₹1,800	12
Complete System Audit	1004	₹4,000	5
Assign suitable technician names and initialize the additional fields for each service category.
4. Initial Service Collection
Store all service objects in a collection, such as an ArrayList<EnergyService>.
Display the details of every service, including its ID, name, base price, available slots, technician name, and category-specific information.
5. Collection Statistics
Implement functionality to:
•	Count the number of available service categories.
•	Calculate the total number of available appointment slots.
•	Display the base price of each service.
•	Identify the service with the highest base price.
•	Identify the service with the lowest number of available slots.
6. Service Searching and Comparison
Implement the following operations:
•	Search for a service using its serviceId.
•	Search for a service using its name, ignoring uppercase and lowercase differences.
•	Compare two service objects based on their service IDs.
•	Display an appropriate message when a service is not found.
For example, searching for panel inspection, Panel Inspection, or PANEL INSPECTION should identify the same service.
7. Booking Cart Management
Create a booking cart to store the maintenance services selected by a customer and their booking quantities.
Each booking entry must contain the selected service and the number of appointments booked.
Implement the following operations:
•	Add a service to the booking cart.
•	Display all services currently in the cart.
•	Display the number of bookings for each service.
•	Calculate the total number of bookings.
•	Remove a booking if required.
If a customer books the same service more than once, merge the quantities into a single cart entry instead of creating duplicate entries.
8. Booking Validation and Confirmation
Validate every booking before adding it to the cart.
•	Reject zero or negative booking quantities.
•	Reject bookings for nonexistent service IDs.
•	Reject bookings that exceed the available appointment slots.
•	Do not modify the cart or available slots when a booking is rejected.
•	Update available slots only after the booking is successfully confirmed.
Display suitable messages for successful and rejected booking requests.
9. Repeated Booking Management
Demonstrate merging repeated bookings for the same service.
For example, if a customer initially books 1 Panel Inspection and later books 1 more Panel Inspection, the cart must contain a single entry with a quantity of 2.
Before confirming an additional booking, validate the combined quantity against the remaining available slots.
10. Service Charge and Discount Calculation
Calculate the amount payable for each service using the following formulas:
Subtotal
Subtotal = Base Price × Number of Bookings
Discount
Discount = Subtotal × Discount Percentage / 100
Final Service Charge
Final Amount = Subtotal − Discount
Apply the following discounts:
•	Panel Inspection: 5%
•	Inverter Servicing: 8%
•	Battery Maintenance: 10%
•	Complete System Audit: 12%
Display the subtotal, discount amount, and final amount for each service.
11. Interface Implementation
Create an interface named Maintainable containing a method to calculate service charges.
Implement the Maintainable interface in the EnergyService class. Each subclass must override the calculation method to apply its respective discount.
Use an interface reference to invoke the service charge calculation method for different maintenance services.
12. Inheritance, Method Overriding, and Runtime Polymorphism
Demonstrate the following object-oriented programming concepts:
•	Inheritance: All four service categories inherit common fields and methods from EnergyService.
•	Method overriding: Each subclass overrides the service charge calculation method.
•	Runtime polymorphism: Store different subclass objects in EnergyService references and invoke the overridden method.
•	Interface-based programming: Use Maintainable references to calculate charges for different service categories.
The discount applied must depend on the actual service object being processed.
13. Booking Scenario
A customer books the following maintenance services:
Service	Number of Bookings	Price per Booking
Panel Inspection	2	₹1,500
Inverter Servicing	1	₹2,500
Battery Maintenance	1	₹1,800
Complete System Audit	1	₹4,000
Process the bookings, validate slot availability, and generate the final bill.
Calculate the subtotal and discount separately for each service before calculating the overall bill.
14. Expected Booking Summary
The application must display the following booking summary:
========== SOLARWISE ==========

Service Bookings:

Panel Inspection       2   Rs.3000.00
Inverter Servicing     1   Rs.2500.00
Battery Maintenance    1   Rs.1800.00
Complete System Audit  1   Rs.4000.00

Total Bookings: 5
Subtotal: Rs.11300.00
Total Discount: Rs.1010.00
Final Amount: Rs.10290.00
15. Expected Bill Calculation
Calculate the bill using the following values:
Service	Subtotal	Discount Rate	Discount	Final Amount
Panel Inspection	₹3,000.00	5%	₹150.00	₹2,850.00
Inverter Servicing	₹2,500.00	8%	₹200.00	₹2,300.00
Battery Maintenance	₹1,800.00	10%	₹180.00	₹1,620.00
Complete System Audit	₹4,000.00	12%	₹480.00	₹3,520.00
Total	₹11,300.00	—	₹1,010.00	₹10,290.00
The total number of bookings is 5, the subtotal is ₹11,300.00, the total discount is ₹1,010.00, and the final amount payable is ₹10,290.00.
16. Updated Technician Availability
After confirming the bookings, update the available slots as follows:
Service	Initial Slots	Bookings	Remaining Slots
Panel Inspection	10	2	8
Inverter Servicing	8	1	7
Battery Maintenance	12	1	11
Complete System Audit	5	1	4
Display the remaining slots for every service after the booking process is completed.
17. Insufficient Slots and Invalid Input
Test the application using the following cases:
1.	Attempt to book 9 Panel Inspections when only 8 slots remain. Reject the booking.
2.	Attempt to book 0 Inverter Servicing appointments. Reject the booking.
3.	Attempt to book -2 Battery Maintenance appointments. Reject the booking.
4.	Attempt to book a service using a nonexistent service ID. Display an appropriate error message.
5.	Attempt to book more appointments than the available slots. Reject the booking without changing the cart or availability.
After every rejected booking, verify that previously confirmed bookings and remaining slot counts are unchanged.
18. Service Attributes and Polymorphic Behaviour
Display the additional fields for each service category:
•	PanelInspection: panel count and inspection type.
•	InverterService: inverter capacity and whether a firmware update is included.
•	BatteryMaintenance: battery type and whether a backup test is included.
•	SystemAudit: system capacity and whether a performance report is provided.
Use overridden methods to calculate discounted charges without writing separate billing logic for each service category in the main method.
19. Independent Service Object Copying
Create an independent copy of an existing service object, such as PanelInspection.
Demonstrate the following:
1.	Create the original service object with its current available slot count.
2.	Create a separate copy of the object.
3.	Change the availableSlots field in the copied object.
4.	Display the slot counts of both objects.
5.	Verify that changing the copied object's slots does not change the original object's slots.
Ensure that the copied object is independent of the original object. Do not add the copied object to the active service collection or include it in booking and billing calculations.
20. Main Application and Student Deliverables
Develop a main class named SolarWise containing the main() method.
The application must demonstrate:
•	Constructors and object creation.
•	Inheritance using the parent class and four subclasses.
•	Interface implementation using Maintainable.
•	Method overriding and runtime polymorphism.
•	Collections for storing and managing services.
•	Searching by service ID and case-insensitive service name.
•	Comparing service objects based on their IDs.
•	Booking cart creation and repeated booking merging.
•	Booking validation and confirmation.
•	Discount calculation and bill generation.
•	Updating technician appointment availability.
•	Handling invalid booking quantities and insufficient slots.
•	Creating and modifying an independent object copy.

Student Deliverables: Implement all the required classes, the Maintainable interface, and the main() method. Display the booking summary, bill, and remaining slots in the specified format. Exception handling is not required.

5. Problem Statement 5: TuneLab – Music Recording Studio Management System
1. Core Studio Service Management
Develop a Java application named TuneLab to manage recording studio services, session bookings, hourly charges, discounts, and available studio hours.
Create a parent class named StudioService with the following fields:
•	serviceId – unique identification number of the studio service
•	serviceName – name of the recording service
•	hourlyRate – charge per hour
•	availableHours – number of available studio hours
•	studioName – name of the recording studio
Provide constructors to initialize these fields and methods to display service details, calculate session charges, and update available studio hours.
2. Studio Service Categories and Inheritance
Create the following subclasses by extending StudioService.
Subclass	Additional Fields	Discount
AudioRecording	microphoneType, trackCapacity	5%
MusicMixing	mixingConsole, trackCount	10%
PodcastRecording	microphoneCount, soundproofed	8%
VoiceOverRecording	voiceBoothType, audioFormat	6%
Each subclass must have a constructor to initialize its additional fields along with the inherited fields.
3. Studio Service Details
Initialize the services with the following details:
Service	ID	Hourly Rate	Available Hours
Audio Recording	1101	₹2,000	20
Music Mixing	1102	₹1,500	15
Podcast Recording	1103	₹1,200	12
Voice-over Recording	1104	₹1,000	10
Assign suitable studio names and initialize the additional fields for each service category.
4. Initial Studio Service Collection
Store all studio service objects in a collection, such as an ArrayList<StudioService>.
Display the details of every service, including its ID, name, hourly rate, available hours, studio name, and category-specific information.
Calculate and display the total available studio hours across all services.
5. Collection Statistics
Implement functionality to:
•	Count the number of available studio service categories.
•	Calculate the total available studio hours.
•	Display the hourly rate of each service.
•	Identify the service with the highest hourly rate.
•	Identify the service with the lowest number of available hours.
6. Service Searching and Comparison
Implement the following operations:
•	Search for a studio service using its serviceId.
•	Search for a studio service using its name, ignoring uppercase and lowercase differences.
•	Compare two studio service objects based on their service IDs.
•	Display an appropriate message when a service is not found.
For example, searching for audio recording, Audio Recording, or AUDIO RECORDING should identify the same service.
7. Session Cart Management
Create a session cart to store the studio services selected by a customer and the number of hours booked.
Each cart entry must contain the selected service and the number of hours booked.
Implement the following operations:
•	Add a service to the session cart.
•	Display all services currently in the cart.
•	Display the number of hours booked for each service.
•	Calculate the total number of booked hours.
•	Remove a session booking if required.
If a customer books the same service more than once, merge the hours into a single cart entry instead of creating duplicate entries.
8. Session Validation and Confirmation
Validate every session booking before adding it to the cart.
•	Reject zero or negative session durations.
•	Reject bookings for nonexistent service IDs.
•	Reject bookings that exceed the available studio hours.
•	Do not modify the cart or available hours when a booking is rejected.
•	Update available hours only after the booking is successfully confirmed.
Display suitable messages for successful and rejected bookings.
9. Repeated Session Booking Management
Demonstrate merging repeated bookings for the same service.
For example, if a customer initially books 1 hour of Audio Recording and later books 1 additional hour of Audio Recording, the cart must contain a single Audio Recording entry with 2 hours.
Before confirming an additional booking, validate the combined duration against the remaining available studio hours.
10. Session Charge and Discount Calculation
Calculate the amount payable for each service using the following formulas:
Subtotal
Subtotal = Hourly Rate × Number of Hours
Discount
Discount = Subtotal × Discount Percentage / 100
Final Session Charge
Final Amount = Subtotal − Discount
Apply the following discounts:
•	Audio Recording: 5%
•	Music Mixing: 10%
•	Podcast Recording: 8%
•	Voice-over Recording: 6%
Display the subtotal, discount amount, and final charge for each service.
11. Interface Implementation
Create an interface named Recordable containing a method to calculate session charges.
Implement the Recordable interface in the StudioService class. Each subclass must override the calculation method to apply its respective discount.
Use an interface reference to invoke the session charge calculation method for different studio services.
12. Inheritance, Method Overriding, and Runtime Polymorphism
Demonstrate the following object-oriented programming concepts:
•	Inheritance: All four service categories inherit common fields and methods from StudioService.
•	Method overriding: Each subclass overrides the session charge calculation method.
•	Runtime polymorphism: Store different subclass objects in StudioService references and invoke the overridden method.
•	Interface-based programming: Use Recordable references to calculate charges for different recording services.
The discount applied must depend on the actual service object being processed.
13. Recording Session Scenario
A customer books the following recording sessions:
Service	Number of Hours	Hourly Rate
Audio Recording	2	₹2,000
Music Mixing	3	₹1,500
Podcast Recording	2	₹1,200
Voice-over Recording	1	₹1,000
Process the bookings, validate studio hour availability, and generate the final invoice.
Calculate the subtotal and discount separately for each service before calculating the overall bill.
14. Expected Session Summary
The application must display the following session summary:
========== TUNELAB ==========

Session Bookings:

Audio Recording       2 hrs   Rs.4000.00
Music Mixing          3 hrs   Rs.4500.00
Podcast Recording     2 hrs   Rs.2400.00
Voice-over Recording  1 hr    Rs.1000.00

Total Hours: 8
Subtotal: Rs.11900.00
Total Discount: Rs.902.00
Final Amount: Rs.10998.00
15. Expected Invoice Calculation
Calculate the invoice using the following values:
Service	Subtotal	Discount Rate	Discount	Final Amount
Audio Recording	₹4,000.00	5%	₹200.00	₹3,800.00
Music Mixing	₹4,500.00	10%	₹450.00	₹4,050.00
Podcast Recording	₹2,400.00	8%	₹192.00	₹2,208.00
Voice-over Recording	₹1,000.00	6%	₹60.00	₹940.00
Total	₹11,900.00	—	₹902.00	₹10,998.00
The total booked duration is 8 hours, the subtotal is ₹11,900.00, the total discount is ₹902.00, and the final amount payable is ₹10,998.00.
16. Updated Studio Hour Availability
After confirming the bookings, update the available hours as follows:
Service	Initial Hours	Booked Hours	Remaining Hours
Audio Recording	20	2	18
Music Mixing	15	3	12
Podcast Recording	12	2	10
Voice-over Recording	10	1	9
Display the remaining hours for every service after the booking process is completed.
17. Insufficient Hours and Invalid Input
Test the application using the following cases:
1.	Attempt to book 19 Audio Recording hours when only 18 hours remain. Reject the booking.
2.	Attempt to book 0 Music Mixing hours. Reject the booking.
3.	Attempt to book -2 Podcast Recording hours. Reject the booking.
4.	Attempt to book a service using a nonexistent service ID. Display an appropriate error message.
5.	Attempt to book more hours than the available studio hours. Reject the booking without changing the cart or availability.
After every rejected booking, verify that previously confirmed bookings and remaining studio hours are unchanged.
18. Service Attributes and Polymorphic Behaviour
Display the additional fields for each service category:
•	AudioRecording: microphone type and track capacity.
•	MusicMixing: mixing console and track count.
•	PodcastRecording: microphone count and whether the studio is soundproofed.
•	VoiceOverRecording: voice booth type and audio format.
Use overridden methods to calculate discounted session charges without writing separate billing logic for each category in the main method.
19. Independent Studio Service Object Copying
Create an independent copy of an existing studio service object, such as AudioRecording.
Demonstrate the following:
1.	Create the original service object with its current available hours.
2.	Create a separate copy of the object.
3.	Change the availableHours field in the copied object.
4.	Display the available hours of both objects.
5.	Verify that changing the copied object's available hours does not change the original object's available hours.
Ensure that the copied object is independent of the original object. Do not add the copied object to the active service collection or include it in session and billing calculations.
20. Main Application and Student Deliverables
Develop a main class named TuneLab containing the main() method.
The application must demonstrate:
•	Constructors and object creation.
•	Inheritance using the parent class and four subclasses.
•	Interface implementation using Recordable.
•	Method overriding and runtime polymorphism.
•	Collections for storing and managing studio services.
•	Searching by service ID and case-insensitive service name.
•	Comparing service objects based on their IDs.
•	Session cart creation and repeated booking merging.
•	Session validation and confirmation.
•	Discount calculation and invoice generation.
•	Updating available studio hours.
•	Handling invalid session durations and insufficient hours.
•	Creating and modifying an independent object copy.
Student Deliverables: Implement all the required classes, the Recordable interface, and the main() method. Display the session summary, invoice, and remaining studio hours in the specified format. Exception handling is not required.

6. Problem Statement 6: RecycleRight – Electronic Waste Recycling Management System
1. Recyclable Material Management
Develop a Java application named RecycleRight to manage electronic waste collection, calculate recycling charges, apply environmental rebates, and track available processing capacity.
Create a parent class RecyclableMaterial with the following private data members:
•	materialId
•	materialName
•	collectionRatePerKg
•	availableCapacityKg
•	recyclingCenter
Implement suitable constructors, getter methods, and a method to display material details.
2. Material Categories and Inheritance
Create the following subclasses by extending RecyclableMaterial.
Subclass	Additional Fields	Rebate
ComputerWaste	computerType, dataWipingRequired	10%
MobileWaste	deviceType, batteryIncluded	15%
BatteryWaste	batteryChemistry, hazardous	5%
PrinterWaste	printerType, cartridgeIncluded	8%
Each subclass must have a constructor to initialize its inherited and additional fields. Use inheritance to reuse the common properties and methods of the parent class.
3. Initial Material Data
Initialize the following material categories with the specified values.
Material	ID	Collection Rate/kg	Capacity (kg)
Computer Waste	1201	₹40	100
Mobile Waste	1202	₹80	50
Battery Waste	1203	₹50	80
Printer Waste	1204	₹30	60
Assign suitable values to the additional fields and recyclingCenter while creating the objects.
4. Collection of Material Objects
Store all recyclable material objects in an appropriate Java collection.
The application must:
•	Add all four material objects to the collection.
•	Display the ID, name, collection rate, available capacity, and recycling center of each material.
•	Display the additional details specific to each subclass.
•	Use a loop to process and display the collection contents.
5. Collection Statistics
Implement methods to display the following information:
•	Total number of material categories.
•	Total available processing capacity across all categories.
•	Collection rate per kilogram for each category.
•	Total capacity remaining after a confirmed collection.
Initially, the total processing capacity must be 290 kg.
6. Material Searching and Comparison
Implement the following operations:
•	Search for a material using its ID.
•	Search for a material using its name without considering uppercase or lowercase differences.
•	Display an appropriate message when the requested material is not found.
•	Compare two material objects using their IDs and determine whether they represent the same material category.
Demonstrate these operations using suitable test cases.
7. Collection Cart Management
Create a collection cart to store the materials selected by a customer and their requested weights in kilograms.
The cart must support:
•	Adding multiple material categories to one collection request.
•	Storing the material object and requested weight for each entry.
•	Displaying the selected materials and their weights.
•	Merging repeated entries for the same material by adding their weights.
•	Displaying the total weight of all materials in the cart.
The cart must not update the available processing capacity until the customer confirms the collection.
8. Weight Validation and Confirmation
Validate each collection request before processing it.
The application must:
•	Reject zero or negative weights.
•	Reject a requested weight that exceeds the material's available processing capacity.
•	Display a suitable message for invalid requests.
•	Verify the total requested weight for each material after merging repeated entries.
•	Confirm the collection only when all requested quantities are valid.
•	Update processing capacity only after successful confirmation.
If any requested quantity is invalid, the application must not partially update the processing capacities.
9. Repeated Entry Management
Allow customers to add the same material category more than once.
For example, if a customer adds 2 kg and then 3 kg of Computer Waste, the cart must merge the entries into a single entry of 5 kg.
The application must:
•	Identify repeated materials using their IDs.
•	Merge their quantities before billing.
•	Avoid creating duplicate billing entries.
•	Validate the merged quantity against available capacity.
10. Collection Charge and Environmental Rebate Calculation
Create suitable methods to calculate the subtotal, environmental rebate, and final collection charge.
Use the following formulas:
•	Subtotal = Collection rate per kg × Requested weight
•	Rebate = Subtotal × Rebate percentage / 100
•	Final Charge = Subtotal − Rebate
Each subclass must apply its specified rebate percentage.
Display the subtotal, rebate, and final charge for every material category. Calculate the total subtotal, total rebate, and final amount payable by the customer.
11. Interface Implementation
Create an interface named Recyclable containing a method to calculate the collection charge.
Implement the Recyclable interface in the RecyclableMaterial class.
Provide suitable method implementations in the parent class and override the calculation method in each subclass to apply the appropriate environmental rebate.
Demonstrate how an interface reference can refer to objects of different recyclable material subclasses.
12. Inheritance, Method Overriding, and Runtime Polymorphism
Demonstrate the following object-oriented programming concepts:
•	Inheritance: Each material subclass extends RecyclableMaterial.
•	Method overriding: Each subclass overrides the collection charge calculation method.
•	Runtime polymorphism: Use a parent-class or interface reference to invoke the overridden method of the actual subclass object.
•	Constructors: Initialize inherited and subclass-specific data members.
•	Encapsulation: Keep data members private where appropriate and provide getter methods.
The application must calculate rebates according to the actual material category rather than using a single fixed percentage for every material.
13. Customer Collection Scenario
A customer submits the following electronic waste collection request.
Material	Requested Weight
Computer Waste	5 kg
Mobile Waste	2 kg
Battery Waste	3 kg
Printer Waste	4 kg
The application must calculate the collection charge and environmental rebate for each category, generate the collection summary, and update the processing capacities after successful confirmation.
14. Collection Summary
Display the following information after confirmation:
•	Material name and requested weight.
•	Subtotal for each material.
•	Environmental rebate for each material.
•	Final charge for each material.
•	Total weight collected.
•	Total subtotal.
•	Total environmental rebate.
•	Final amount payable.
Format monetary values to two decimal places.
The output should follow this format:
========== RECYCLERIGHT ==========

Collection Summary:

Computer Waste     5 kg   Rs.200.00
Mobile Waste       2 kg   Rs.160.00
Battery Waste      3 kg   Rs.150.00
Printer Waste      4 kg   Rs.120.00

Total Weight: 14 kg
Subtotal: Rs.630.00
Total Rebate: Rs.61.10
Final Amount: Rs.568.90
15. Correct Charge Calculation
Use the following table to verify the billing calculations.
Material	Weight	Subtotal	Rebate	Final Charge
Computer Waste	5 kg	₹200.00	₹20.00	₹180.00
Mobile Waste	2 kg	₹160.00	₹24.00	₹136.00
Battery Waste	3 kg	₹150.00	₹7.50	₹142.50
Printer Waste	4 kg	₹120.00	₹9.60	₹110.40
Total	14 kg	₹630.00	₹61.10	₹568.90
Ensure that the displayed totals match the calculated values.
16. Updating Processing Capacity
After the collection is confirmed, update the available processing capacity by subtracting the collected weight from the original capacity.
Display the updated capacities in the following format:
Updated Processing Capacity:

Computer Waste: 95 kg
Mobile Waste: 48 kg
Battery Waste: 77 kg
Printer Waste: 56 kg
The total remaining processing capacity must be 276 kg.
Do not update the original capacities before successful confirmation.
17. Invalid Collection Requests
Test the application using the following invalid cases:
•	Adding 0 kg of Computer Waste.
•	Adding −2 kg of Mobile Waste.
•	Requesting 101 kg of Computer Waste when only 100 kg is available.
•	Requesting 51 kg of Mobile Waste when only 50 kg is available.
•	Searching for an unknown material ID.
•	Searching for a material name that does not exist.
Display suitable messages for each invalid case. Ensure that invalid requests do not result in incorrect billing or processing capacity updates.
18. Subclass-Specific Information and Polymorphic Display
Display the additional fields of each material category.
Examples include:
•	Computer Waste: computer type and whether data wiping is required.
•	Mobile Waste: device type and whether a battery is included.
•	Battery Waste: battery chemistry and whether the battery is hazardous.
•	Printer Waste: printer type and whether a cartridge is included.
Use overridden methods where appropriate to display category-specific information. Demonstrate runtime polymorphism by invoking the required methods through common parent-class or interface references.
19. Independent Object Copying
Create an independent copy of an existing RecyclableMaterial object.
Perform the following operations:
•	Copy the original material's details into a separate object.
•	Modify the processing capacity of the copied object.
•	Display the original object's capacity.
•	Display the copied object's modified capacity.
•	Verify that changing the copy does not affect the original object.
For example, create a copy of Computer Waste with an initial capacity of 100 kg and modify the copy's capacity to 75 kg. The original object's capacity must remain 100 kg before any actual collection is processed.
The copied object must not be included in the actual material collection, customer billing, or processing capacity calculations.
20. Main Application and Student Deliverables
Implement all the required classes, the Recyclable interface, and the main() method in the RecycleRight application.
The main() method must:
1.	Create and initialize the four material objects.
2.	Store the objects in a collection.
3.	Display material details and collection statistics.
4.	Demonstrate searching by ID and case-insensitive name.
5.	Compare material objects using their IDs.
6.	Add the customer's requested materials to the collection cart.
7.	Merge repeated entries and validate requested weights.
8.	Calculate subtotals, environmental rebates, and final charges using runtime polymorphism.
9.	Confirm the collection and update processing capacities.
10.	Display the final collection summary and updated capacities.
11.	Test invalid collection requests and missing material searches.
12.	Demonstrate independent object copying.
Student Deliverables: Submit the complete Java source code and the expected program output. Demonstrate constructors, inheritance, interfaces, method overriding, runtime polymorphism, collections, searching, object comparison, collection management, validation, rebate calculations, billing, and independent object copying.
*/