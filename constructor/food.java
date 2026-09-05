class Patient
{
    int patientId;
    String name;
    int age;
    String doctor;
    String roomType;
    double deposit;
    boolean admitted;
    // in emergency condition arrive before their complete identity is known.
    Patient()
    {
        patientId = 0;
        name = "Unknown";
    }
    Patient()
    void display
    
}

class test
{
    public static void main(Sting[] args){
        Patient p1 = new Patient();
        
        Patient p2 = new Patient(301, "Aditi", 25);
        
        Patient p3 = new Patient(302, "Neha", 42, "Dr. Sharma");
        
        Patient p4 = new Patient(303, "Rakesh", 55, "Dr. Verma", "Private", 25000);
        
        Patient p5 = new Patient(304, "Kunal", 63, "Dr. Roy", "ICU", 50000, true);
    }
}