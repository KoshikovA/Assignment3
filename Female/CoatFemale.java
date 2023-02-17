package Female;

import Female.SpringAutumnFemale;

public class CoatFemale extends SpringAutumnFemale {
    private String kind;
    public CoatFemale(String brand, String size, String color, double price, String gender, String season, int amount, String type, String good, String kind){
        super(brand, size,color, price,"Female" , "Spring,Autumn", amount, "coat", "coat");
        this.kind  = kind;

    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
