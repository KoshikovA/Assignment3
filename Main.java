import BaseClasses.DBConnection;
import Person.Admin;
import Person.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws SQLException, IOException, InterruptedException {
        DBConnection db = new DBConnection();
        Connection connection = db.getConnection("Alim", "root", "golubinovka");
        boolean a = true;
        while (a) {
            System.out.println("Welcome to our BaseClasses.Clothing Store");
            System.out.println("Please choose an option to get into the system");
            System.out.println("1 - User" +  "\n" + "2 - Admin" + "\n" + "3 - Salesman");
            int number = Integer.parseInt(scanner.next());
            switch (number) {
                case 1:
                    int option;
                    System.out.println("1 - Sign in");
                    System.out.println("2 - Sign up");
                    option = Integer.parseInt(scanner.next());
                    if (option == 1) {
                        System.out.println("Username:");
                        String username = scanner.nextLine();
                        System.out.println("Password:");
                        String password = scanner.nextLine();
                        //Creating user using database
                        User user = new User(0, username, password);
                        user.showMenu();
                    }
                    else {
                        System.out.println("Username:");
                        String username = scanner.nextLine();
                        System.out.println("Password:");
                        String password = scanner.nextLine();
                        //Register user to database needed
                        User user = new User(0, username, password);
                        user.showMenu();
                    }
                    break;
                    /*
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
                    int option = scanner.nextInt();
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
                    option = Integer.parseInt(scanner.next());
                    if (option == 1 ) {
                        gender = "male";
                    }
                    else gender = "Female";
                    System.out.println("1 - Classic, 2 - Sports, 3 - Oversize");
                    option = Integer.parseInt(scanner.next());
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
                     */
                case 2:
                    String usernameForAdmin, passwordForAdmin;
                    System.out.println("Username:");
                    usernameForAdmin = scanner.nextLine();
                    System.out.println("Password:");
                    passwordForAdmin = scanner.nextLine();
                    if (Admin.isAdmin(usernameForAdmin, passwordForAdmin)) {
                        //get admin from database and call menu
                        Admin.showMenu();
                    }
                    else {
                        System.out.println("You are not an admin");
                        System.out.println("Redirecting to the main menu...");
                        Thread.sleep(5000);
                        }
                    break;
                case 3:
                    String usernameForSeller, passwordForSeller;
                    System.out.println("Username:");
                    usernameForSeller = scanner.nextLine();
                    System.out.println("Password:");
                    passwordForSeller = scanner.nextLine();
                    if (usernameForSeller.equals()) {
                        System.out.println("Welcome, good job!");
                        a = false;
                    }
                    else {
                        System.out.println("You are not a customer");
                    }
                    break;
                default:
                    System.out.println("--Invalid option--");
                    System.out.println("Please, try again");
                    break;
            }
        }
    }
    private static boolean IsExist() {
        //Check with use of database
        return false;
    }
}

