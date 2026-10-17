/*
Real-Time Java Project: Hospital Management System
Scenario
A hospital wants to organize its Java application into different packages so that patient-related operations, doctor-related operations, and billing operations are maintained separately.
The application should have the following packages:
hospital
│
├── patient
│   └── Patient.java
│
├── doctor
│   └── Doctor.java
│
├── billing
│   └── Bill.java
│
└── main
    └── HospitalApp.java
Requirements
Package 1: hospital.patient
Create a class Patient with:
•	patientId
•	patientName
•	age
•	disease
Create a method:displayPatient() to display patient details.
Package 2: hospital.doctor
Create a class Doctor with:
•	doctorId
•	doctorName
•	specialization
Create a method:displayDoctor()to display doctor details.
Package 3: hospital.billing
Create a class Bill with:
•	patientId
•	consultationFee
•	medicineFee
Create a method:calculateBill()that calculates:Total Bill = Consultation Fee + Medicine Fee
and displays the bill details.

Package 4: hospital.main
Create a class HospitalApp containing the main() method.
Inside main():
1.	Import the Patient class from hospital.patient.
2.	Import the Doctor class from hospital.doctor.
3.	Import the Bill class from hospital.billing.
4.	Create objects of all three classes.
5.	Display patient details.
6.	Display doctor details.
7.	Calculate and display the patient's bill.

Sample Input
You can directly initialize the objects with:
Patient ID       : 101
Patient Name     : Rahul
Age              : 35
Disease          : Fever

Doctor ID        : D201
Doctor Name      : Dr. Priya
Specialization   : General Medicine

Consultation Fee : 800
Medicine Fee     : 1200
Expected Output
===== PATIENT DETAILS =====
Patient ID: 101
Patient Name: Rahul
Age: 35
Disease: Fever

===== DOCTOR DETAILS =====
Doctor ID: D201
Doctor Name: Dr. Priya
Specialization: General Medicine

===== BILL DETAILS =====
Patient ID: 101
Consultation Fee: Rs.800.0
Medicine Fee: Rs.1200.0
Total Bill: Rs.2000.0
*/

package main;

import patient.Patient;
import doctor.Doctor;
import billing.Bill;

public class HospitalApp {
    public static void main(String[] args) {
        Patient p1 = new Patient(101, "Rahul", 35, "Fever");
        Doctor d1 = new Doctor("D201", "Dr. Priya", "General Medicine");
        Bill b1 = new Bill(101, 800, 1200);

        System.out.println("===== PATIENT DETAILS =====");
        p1.displayPatient();
        System.out.println();

        System.out.println("===== DOCTOR DETAILS =====");
        d1.displayDoctor();
        System.out.println();

        System.out.println("===== BILL DETAILS =====");
        b1.calculateBill();
    }
}
