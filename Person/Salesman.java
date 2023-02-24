package Person;

import java.util.Scanner;
import java.sql.*;

public class Salesman extends Person implements Showable {
    private String companyName;
    public Salesman(int id, String username, String password,String companyName) {
        super(id, username, password);
        this.companyName = companyName;
    }
    @Override
    public String getPassword() {
        return super.getPassword();
    }
    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }


    @Override
    public void showMenu(Connection connection) throws InterruptedException {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("--- User menu ---");
            System.out.println("1 - Add product");
            System.out.println("2 - Log out");
            Scanner scanner = new Scanner(System.in);
            int option = Integer.parseInt(scanner.next());
            switch (option) {
                case 1:
                    System.out.println("Please, choose category in which you want add your product!");
                    String wishedTable = scanner.nextLine();
                    String coatInsert = " insert into coatdatafull (id, brand, size, color, gender, amount, typeJacket, kind, season, price) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    String jacketInsert = " insert into jacketdatafull (id, brand, size, color, gender, amount, typeJacket, kind, season, price, material) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    if (wishedTable.equals("coatdatafull")){
                        int id = Integer.parseInt(scanner.nextLine());
                        String brand = scanner.nextLine();
                        String size = scanner.nextLine();
                        String color = scanner.nextLine();
                        String gender = scanner.nextLine();
                        int amount = Integer.parseInt(scanner.nextLine());
                        String typeJacket = scanner.nextLine();
                        String kind = scanner.nextLine();
                        String season = scanner.nextLine();
                        int price = Integer.parseInt(scanner.nextLine());

                        try {
                            PreparedStatement coat = connection.prepareStatement(coatInsert);
                            coat.setInt(1, id);
                            coat.setString(2, brand);
                            coat.setString(3, size);
                            coat.setString(4,color);
                            coat.setString(5,gender);
                            coat.setInt(6,amount);
                            coat.setString(7,typeJacket);
                            coat.setString(8,kind);
                            coat.setString(9,season);
                            coat.setInt(10,price);
                            coat.execute();
                            
                            
                        }
                        catch (SQLException e) {
                            System.out.println("An error occurred when inserting clothe");
                            throw new RuntimeException(e);
                        }
                     } else if (wishedTable.equals("jacketdatafull - копия")) {
                        int id = Integer.parseInt(scanner.nextLine());
                        String brand = scanner.nextLine();
                        String size = scanner.nextLine();
                        String color = scanner.nextLine();
                        String gender = scanner.nextLine();
                        int amount = Integer.parseInt(scanner.nextLine());
                        String typeJacket = scanner.nextLine();
                        String kind = scanner.nextLine();
                        String season = scanner.nextLine();
                        int price = Integer.parseInt(scanner.nextLine());
                        String material = scanner.nextLine();

                        try {
                            PreparedStatement coat = connection.prepareStatement(jacketInsert);
                            coat.setInt(1, id);
                            coat.setString(2, brand);
                            coat.setString(3, size);
                            coat.setString(4,color);
                            coat.setString(5,gender);
                            coat.setInt(6,amount);
                            coat.setString(7,typeJacket);
                            coat.setString(8,kind);
                            coat.setString(9,season);
                            coat.setInt(10,price);
                            coat.setString(11,material);
                            coat.execute();

                        }
                        catch (SQLException e) {
                            System.out.println("An error occurred when inserting clothe");
                            throw new RuntimeException(e);
                        }
                    }

                    else{
                        System.out.println("Invalid category, try again!");
                        continue;
                    }

                    break;
                case 2:
                    System.out.println("Saving changes...");
                    Thread.sleep(3000);
                    isRunning = false;
                default:
                    System.out.println("Invalid option, please try again!");
            }
        }

    }
}
