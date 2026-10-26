abstract class Supplyment {
    final int supplementId;
    String supplementName;
    int pricePerUnit;
    int availableUnits;
    String brandName;

    Supplyment(int supplementId, String supplementName, int pricePerUnit, int availableUnits, String brandName) {
        this.supplementId = supplementId;
        this.supplementName = supplementName;
        this.pricePerUnit = pricePerUnit;
        this.availableUnits = availableUnits;
        this.brandName = brandName;
    }
    
    @Override
    public String toString() {
        return supplementId + " | " + supplementName + " | " + pricePerUnit + " | " + availableUnits + " | " + brandName;
    }
}

abstract class ProteinPowder extends Supplyment {
    String flavour;
    int weightInKg;

    ProteinPowder(int supplementId, String supplementName, int pricePerUnit, int availableUnits, String brandName, String flavour, int weightInKg) {
        super(supplementId, supplementName, pricePerUnit, availableUnits, brandName);
        this.flavour = flavour;
        this.weightInKg = weightInKg;
    }
}

abstract class Vitamin extends Supplyment {
    String flavour;
    int weightInKg;

    Vitamin(int supplementId, String supplementName, int pricePerUnit, int availableUnits, String brandName, String flavour, int weightInKg) {
        super(supplementId, supplementName, pricePerUnit, availableUnits, brandName);
        this.flavour = flavour;
        this.weightInKg = weightInKg;
    }
}

abstract class EnergyBar extends Supplyment {
    String flavour;
    int weightInKg;

    EnergyBar(int supplementId, String supplementName, int pricePerUnit, int availableUnits, String brandName, String flavour, int weightInKg) {
        super(supplementId, supplementName, pricePerUnit, availableUnits, brandName);
        this.flavour = flavour;
        this.weightInKg = weightInKg;
    }
}

abstract class HerbalTea extends Supplyment {
    String flavour;
    int weightInKg;

    HerbalTea(int supplementId, String supplementName, int pricePerUnit, int availableUnits, String brandName, String flavour, int weightInKg) {
        super(supplementId, supplementName, pricePerUnit, availableUnits, brandName);
        this.flavour = flavour;
        this.weightInKg = weightInKg;
    }
}