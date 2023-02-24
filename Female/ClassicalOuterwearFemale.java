package Female;

public class ClassicalOuterwearFemale extends WinterFemaleOuterwear {

    private String material;
    public ClassicalOuterwearFemale(String brand, String size, String color, double price, String gender, String season, int amount, String type, String kind, String material) {
        super(brand, size, color, price, "female", "\n"+
                "winter","down jackets","classical",10);
        this.material=material;
    }
    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}