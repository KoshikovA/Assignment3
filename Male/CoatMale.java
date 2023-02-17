package Male;

public class CoatMale extends SpringAutumnMale {
    private String kind;
    public CoatMale(String brand, String size, String color, double price, String gender, String season, int amount, String type, String good, String kind){
        super(brand, size,color, price,"Male" , "Spring,Autumn", amount, "coat", "coat");
        this.kind  = kind;

    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
