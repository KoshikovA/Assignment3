package Male;

import Female.Outerwear;

public class SpringAutumnMale extends Outerwear {
    private String good;
    public SpringAutumnMale(String brand, String size, String color, double price, String gender, String season, int amount, String type, String good) {
        super(brand, size, color, price, "male" ,"Spring,Autumn","coat",1);
        this.good = good;
    }

    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }
}
