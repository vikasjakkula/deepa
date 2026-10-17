package billing;
public class Bill {
    int patientId;
    double consultationFee;
    double medicineFee;
    void calculateBill() {
        double totalBill = consultationFee + medicineFee;
        System.out.println("Patient ID: ");
        System.out.println("Consultation Fee: Rs.");
        System.out.println("Medicine Fee: Rs.");
        System.out.println("Total Bill: Rs.");
    }
}