package Male;


import BaseClasses.Outerwear;

public class WinterMaleOuterwear extends Outerwear {
    private String kind;
    public WinterMaleOuterwear(String brand, String size, String color, double price, String gender, String season, int amount, String type, String kind) {
        super(brand, size, color, price,"Male","Winter","Down jackets",10);
        this.kind=kind;
    }
    public String getType() {
        return kind;
    }
    public void setType(String kind) {
        this.kind = kind;
    }
}