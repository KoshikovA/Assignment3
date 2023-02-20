package BaseClasses;

import BaseClasses.Clothing;

public class Outerwear extends Clothing {
        private int amount;
        public Outerwear(String brand,String size, String color, double price, String gender, String season, String type,int amount){
            super(brand,size,color,price,gender, season, type);
            this.amount=amount;
        }
        public int getAmount(){
            return amount;
        }
        public void setAmount(int amount) {
            this.amount = amount;
        }


    }

