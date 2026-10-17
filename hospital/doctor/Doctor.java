package doctor;
public class Doctor {
    int doctorId;
    String doctorName;
    String specialization;
    void displayDoctor() {
        System.out.println("Doctor ID: "+ doctorId);
        System.out.println("Doctor Name: "+ doctorName);
        System.out.println("Specialization: "+ specialization);
    }
}