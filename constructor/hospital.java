class Inpatient extends Patient {
    Inpatient(int id) {
        super(id);
    }

    void calculateBill() {
        bill = 5000;
    }
    
}

class Outpatient extends Patient {
    Outpatient(int id) {
        super(id);
    }

    void calculateBill() {
        bill = 1000;
    }
}

abstract class Patient {
    final int id;
    static final String name = "Yashodha";
    protected String category;
    int roomNum;
    private int medicalRecord;
    double bill;

    Patient(int id) {
        this.id = id;
    }

    abstract void calculateBill();

    void updateMedicalRecord(int record) {
        medicalRecord = record;
    }

    void displayPatientDetails() {
        System.out.println("patient ID: " + id);
        System.out.println("Hospital name: " + name);
        System.out.println("patient category: " + category);
        System.out.println("Room number: " + roomNum);
        System.out.println("Medical Record: " + medicalRecord);
        System.out.println("Bill: " + bill);
    }
}