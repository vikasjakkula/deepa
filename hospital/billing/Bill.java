package billing;

public class Bill {
    int patientId;
    double consultationFee;
    double medicineFee;

    public Bill(int patientId, double consultationFee, double medicineFee) {
        this.patientId = patientId;
        this.consultationFee = consultationFee;
        this.medicineFee = medicineFee;
    }

    public void calculateBill() {
        double totalBill = consultationFee + medicineFee;
        System.out.println("Patient ID: " + patientId);
        System.out.println("Consultation Fee: Rs." + consultationFee);
        System.out.println("Medicine Fee: Rs." + medicineFee);
        System.out.println("Total Bill: Rs." + totalBill);
    }
}
