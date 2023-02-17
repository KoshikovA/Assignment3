package Male;

import Male.classicalOuterwearMale;

public class   polyesterMale extends classicalOuterwearMale {
    public polyesterMale(String brand,String size, String color, double price, String gender, String season,int amount,String type,String kind, String material) {
        super("Zara", "L", "black", 89000, "male", "\n"+
                "winter", 4, "classical","Down jackets","polyester");
    }
}