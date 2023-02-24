package Male;

public class ClassicalOuterwearMale extends WinterMaleOuterwear {
    private String material;
    public ClassicalOuterwearMale(String brand, String size, String color, double price, String gender, String season, int amount, String kind, String type, String material) {
        super("Zara", size, color,price , "male", "winter",5,"Down Jackets","classical");
        this.material=material;
    }
    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
