// Hospital Patient Registration - same pattern: one master constructor, rest delegate

class Patient {

    private int patientId;
    private String name;
    private int age;
    private String doctor;
    private String roomType;
    private double deposit;
    private boolean admitted;

    // Situation A : emergency patient, nothing known yet
    Patient() {
        this(0, "Unknown", 0, "Not Assigned", "Emergency", 0.0, true);
    }

    // Situation B : outpatient -> id + name + age
    Patient(int patientId, String name, int age) {
        this(patientId, name, age, "Not Assigned", "OPD", 0.0, false);
    }

    // Situation C : doctor already assigned, still an outpatient
    Patient(int patientId, String name, int age, String doctor) {
        this(patientId, name, age, doctor, "OPD", 0.0, false);
    }

    // Health check package : id + name + deposit
    // (int,String,double) differs from Situation B's (int,String,int) -> allowed
    Patient(int patientId, String name, double deposit) {
        this(patientId, name, 0, "Health Check Department", "Day Care", deposit, false);
    }

    // Situation D : planned admission -> automatically admitted
    Patient(int patientId, String name, int age, String doctor,
            String roomType, double deposit) {
        this(patientId, name, age, doctor, roomType, deposit, true);
    }

    // Situation E : MASTER constructor - transferred patient, everything known
    Patient(int patientId, String name, int age, String doctor,
            String roomType, double deposit, boolean admitted) {
        this.patientId = patientId;
        this.name      = name;
        this.age       = age;
        this.doctor    = doctor;
        this.roomType  = roomType;
        this.deposit   = deposit;
        this.admitted  = admitted;
    }

    // Design question:
    // (patientId, age) is (int,int) and (patientId, deposit) is (int,double).
    // The TYPES differ, so both constructors would compile. But new Patient(301, 5000)
    // would silently choose (int,int) because an int literal is an exact match.
    // Safer answer: use clearly named factory methods.
    static Patient forAge(int patientId, int age) {
        return new Patient(patientId, "Unknown", age);
    }

    static Patient withDeposit(int patientId, double deposit) {
        return new Patient(patientId, "Unknown", deposit);
    }

    int getPatientId()   { return patientId; }
    String getName()     { return name; }
    boolean isAdmitted() { return admitted; }

    void setDeposit(double deposit) {
        if (deposit < 0) {
            System.out.println("Deposit cannot be negative");
            return;
        }
        this.deposit = deposit;
    }

    void displayPatient() {
        System.out.println("Patient ID : " + patientId);
        System.out.println("Name       : " + name);
        System.out.println("Age        : " + age);
        System.out.println("Doctor     : " + doctor);
        System.out.println("Room Type  : " + roomType);
        System.out.println("Deposit    : " + deposit);
        System.out.println("Admitted   : " + admitted);
        System.out.println("--------------------------------");
    }
}

public class food {
    public static void main(String[] args) {

        Patient p1 = new Patient();                                     // A
        Patient p2 = new Patient(301, "Aditi", 25);                     // B
        Patient p3 = new Patient(302, "Neha", 42, "Dr. Sharma");        // C
        Patient p4 = new Patient(303, "Rakesh", 55, "Dr. Verma",
                                 "Private", 25000);                     // D
        Patient p5 = new Patient(304, "Kunal", 63, "Dr. Roy",
                                 "ICU", 50000, true);                   // E
        Patient p6 = new Patient(305, "Vivek", 1500.0);                 // health check

        p1.displayPatient();
        p2.displayPatient();
        p3.displayPatient();
        p4.displayPatient();
        p5.displayPatient();
        p6.displayPatient();
    }
}
