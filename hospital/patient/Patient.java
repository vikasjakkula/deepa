package patient;
public class Patient {
    int patientId;
    String patientName;
    int age;
    String disease;
    void displayPatient() {
        System.out.println("Patient ID       : "+ patientId);
        System.out.println("Patient Name     : "+ patientName);
        System.out.println("Age              : "+ age);
        System.out.println("Disease          : "+ disease);
    }
}