import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
public class Main {
    private static List<Object>  basket = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static final String passwordForAdmin = "123";
    static final String passwordForSeller = "1234";
    public static void main(String[] args) throws SQLException, IOException, InterruptedException {
        DBConnection db = new DBConnection();
        Connection connection = db.getConnection("Alim", "root", "golubinovka");
        System.out.println("Welcome to our Clothing Store");
        boolean a = true;
        while (a) {
            System.out.println("Who are you?" + " Choose a number:");
            System.out.println("1 - Person.User" +  "\n" + "2 - Person.Admin" + " \n" + "3 - Seller");
            int number = sc.nextInt();
            switch (number) {
                case 1:
                    System.out.println("Enter login:");
                    String login = reader.readLine();
                    System.out.println("Enter password:");
                    String password = reader.readLine();
                    String insertUser = " insert into usertable (userName, password) values (?, ?)";
                    PreparedStatement pstmt = connection.prepareStatement(insertUser);
                    pstmt.setString(1, login);
                    pstmt.setString(2, password);
                    pstmt.execute();
                    System.out.println("What type of outwear do you need?"+" \n" +"Choose the season:");
                    System.out.println("1 - Winter" + "\n" + "2 - Autumn/Spring");
                    int option = sc.nextInt();
                    String gender = "";
                    String season = "";
                    String kind = "";
                    String type = "";
                    if (option == 1) {
                        season = "Winter";
                    }
                    else {
                        season = "Autumn/Spring";
                    }
                    System.out.println("1 - Male, 2 - Female");
                    option = Integer.parseInt(sc.next());
                    if (option == 1 ) {
                        gender = "male";
                    }
                    else gender = "Female";
                    System.out.println("1 - Classic, 2 - Sports, 3 - Oversize");
                    option = Integer.parseInt(sc.next());
                    if (option == 1) {
                        kind = "Classic";
                    }
                    else if (option == 2) {
                        kind = "Sports";
                    }
                    else kind = "Oversize";
                    Statement statement = connection.createStatement();

                    String query = "SELECT * FROM myshop.coatdatafull";
                    ResultSet resultSet = statement.executeQuery(query);
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String brand = resultSet.getString("brand");
                        String size = resultSet.getString("size");
                        String color = resultSet.getString("color");
                        String sex = resultSet.getString("gender");
                        int amount = resultSet.getInt("amount");
                        String typeJacket = resultSet.getString("typeJacket");
                        String kindClothe = resultSet.getString("kind");
                        String seasonClothe = resultSet.getString("season");
                        int price = resultSet.getInt("price");
                        if (gender.equals(sex) && seasonClothe.equals(season) && kind.equals(kindClothe)) {
                            System.out.println("ID: " + id + "\n" + " Brand: " + brand + "\n"
                                    + " Size: " + size + "\n"
                                    + " Color: " + color + "\n" + " In stock: "
                                    + amount + "\n" + " instances " + " Price: " + price + "\n");
                        }
                    }
                    System.out.println("Choose items you wanna buy by ID: ");
                    String purshase = reader.readLine();
                    System.out.println("Congratulations on your purchase!");
                    System.out.println("Your session has come to an end, pls wait...");
                    Thread.sleep(5000);
                    break;
                case 2:
                    System.out.println("Enter the password:");
                    String password1 = sc.next();
                    if (password1.equals(passwordForAdmin)) {
                        System.out.println("Welcome to the System, bro:)");
                        a = false;
                        break;
                    }
                    else {
                        System.out.println("You are not an admin");
                        }
                    break;
                case 3:
                    System.out.println("Enter the password:");
                    String password2 = sc.next();
                    if (password2.equals(passwordForSeller)) {
                        System.out.println("Welcome, good job!");
                        a = false;
                    }
                    else {
                        System.out.println("You are not a customer");
                    }
                    }

                }
            }
        }


