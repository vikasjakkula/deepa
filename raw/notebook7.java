/*

A hospital is developing a *Patient Management System* to manage different
types of patients.

The system should follow these requirements:

1. Every patient must have a unique *patient ID. Once assigned during 
registration, the patient ID **must not be changed*.

2. The *hospital name* is common to all patients. It should be stored 
only once and should be accessible without creating a patient object.

3. The patient's *medical record* contains confidential information and 
should *not be directly accessible from outside the patient class*.

4. The *patient category* should be accessible within the patient class, 
its subclasses, and classes belonging to the same package.

5. The *room number* should be accessible only to classes within the same hospital package.

6. Every type of patient must calculate its bill differently. Therefore, calculateBill() should define a common requirement for all patient types, while each specific patient type must provide its own implementation.

7. The hospital should provide a method to display the common *hospital name*
without requiring a patient object.

8. The patient class should serve as a *base class* for different types of
patients and should not be used to create objects directly.

9. The system should support at least two types of patients:

   * InPatient
   * OutPatient

10. Each patient type must provide its own implementation for calculating the bill.

11. Provide a updateMedicalRecord() method to modify the patient's medical record.

12. Provide a displayPatientDetails() method to display the patient ID,
hospital name, patient category, room number, medical record, and calculated bill.

### Task

Write a complete Java program that satisfies all the above requirements.

While designing the program, select appropriate *access specifiers
and non-access modifiers* based on the requirements.*/


// Solution

class notebook7 {
    public static void main(String[] args) {
        Patient.displayHospitalName();
        System.out.println();

        Patient p1 = new InPatient(2001, "Fractured leg", 301, 5, 1800);
        Patient p2 = new OutPatient(2002, "Allergy", 15, 400, 150);

        p1.displayPatientDetails();
        p2.displayPatientDetails();

        p2.updateMedicalRecord("Allergy treated");
        p2.displayPatientDetails();
    }
}

abstract class Patient {
    private final int patientId;
    static String hospitalName = "Sunrise Hospital";
    private String medicalRecord;
    protected String patientCategory;
    int roomNumber;

    Patient(int patientId, String medicalRecord, int roomNumber, String patientCategory) {
        this.patientId = patientId;
        this.medicalRecord = medicalRecord;
        this.roomNumber = roomNumber;
        this.patientCategory = patientCategory;
    }

    static void displayHospitalName() {
        System.out.println("Hospital: " + hospitalName);
    }

    abstract double calculateBill();

    void updateMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    void displayPatientDetails() {
        System.out.println("Patient ID: " + patientId);
        System.out.println("Hospital Name: " + hospitalName);
        System.out.println("Category: " + patientCategory);
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Medical Record: " + medicalRecord);
        System.out.println("Bill: " + calculateBill());
        System.out.println();
    }
}

class InPatient extends Patient {
    int days;
    double chargePerDay;

    InPatient(int patientId, String medicalRecord, int roomNumber, int days, double chargePerDay) {
        super(patientId, medicalRecord, roomNumber, "In-Patient");
        this.days = days;
        this.chargePerDay = chargePerDay;
    }

    @Override
    double calculateBill() {
        return days * chargePerDay;
    }
}

class OutPatient extends Patient {
    double consultationFee;
    double medicineCost;

    OutPatient(int patientId, String medicalRecord, int roomNumber, double consultationFee, double medicineCost) {
        super(patientId, medicalRecord, roomNumber, "Out-Patient");
        this.consultationFee = consultationFee;
        this.medicineCost = medicineCost;
    }

    @Override
    double calculateBill() {
        return consultationFee + medicineCost;
    }
}
