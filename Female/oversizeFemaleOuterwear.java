package Female;

public class oversizeFemaleOuterwear extends WinterFemaleOuterwear {
    private String material;
    public oversizeFemaleOuterwear(String brand,String size, String color, double price, String gender, String season, int amount,String type,String kind,String material) {
        super(brand, size, color, price, "female", "\n"+
                "winter","Down jackets","oversize",10);
        this.material=material;
    }

}