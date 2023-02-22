package Female;

import BaseClasses.Outerwear;

public class WinterFemaleOuterwear extends Outerwear {

    private String kind;
    public WinterFemaleOuterwear(String brand, String size, String color, double price, String gender, String season, String type, String kind, int amount) {
        super(brand, size, color, price,"Female","Winter","Down jackets",20);
        this.kind=kind;
    }
    public String getType() {
        return kind;
    }
    public void setType(String kind) {
        this.kind = kind;
    }
}
