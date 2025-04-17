package tn.esprit.gaspillagezero.entites.Marketplace;

public enum DiscountType {
    PERCENTAGE_5(5),
    PERCENTAGE_10(10),
    PERCENTAGE_15(15),
    PERCENTAGE_20(20),
    FIXED_AMOUNT(0); // Valeur 0 si pas un pourcentage

    private final int percentage;

    DiscountType(int percentage) {
        this.percentage = percentage;
    }

    public int getPercentage() {
        return percentage;
    }
}

