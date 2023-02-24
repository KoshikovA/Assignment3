package Male;

public class SportsOuterwearMale extends WinterMaleOuterwear {
    private String material;
    public SportsOuterwearMale(String brand, String size, String color, double price, String gender, String season, int amount, String type, String kind, String material) {
        super("Prada", size, color, price, "male", "\n"+
                "winter",5,"Down jackets","sports");
        this.material=material;
    }
    public String getMaterial() {
        return material;
    }
    public String setMaterial() {
        return material;
    }
}

