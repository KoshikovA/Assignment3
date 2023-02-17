package Female;

public class SpringAutumnFemale extends Outerwear {
    private String good;
    public SpringAutumnFemale(String brand, String size, String color, double price, String gender, String season, int amount, String type, String good) {
        super(brand, size, color, price, "female", "Spring,Autumn", "coat", amount);
        this.good = good;
    }

    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }
}
