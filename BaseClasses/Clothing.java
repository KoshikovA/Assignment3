package BaseClasses;

public class Clothing {
    private String color;
    private String size;
    private String gender;
    private double price;
    private String brand;
    private String season;
    private String type;

    public Clothing(String brand, String size, String color, double price, String gender, String season, String type) {
        this.size = size;
        this.color = color;
        this.price = price;
        this.brand = brand;
        this.gender = gender;
        this.season = season;
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getColor() {
        return color;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getSize(){
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getGender(){
        return gender;
    }
    public void setGender(String season) {
        this.season = season;
    }
    public String getSeason() {
        return season;
    }
    public void setSeason(String gender) {
        this.gender = gender;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
