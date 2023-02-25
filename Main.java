import BaseEntities.DBConnection;
import Person.Admin;
import Person.Salesman;
import Person.User;

import java.io.*;
import java.sql.*;
import java.util.Scanner;
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws SQLException, IOException, InterruptedException {
        DBConnection db = DBConnection.getInstance();
        Connection connection = db.getConnection("Alim", "root", "golubinovka");
        Admin admin = Admin.getInstance("admin", "admin");
        boolean a = true;
        while (a) {
            System.out.println("Welcome to our Clothing Store");
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
                        String username = reader.readLine();
                        System.out.println("Password:");
                        String password = reader.readLine();
                        boolean flag = db.IsExists(connection, username, password, "usertable");
                        if (flag) {
                            continue;
                        }

                        System.out.println("Welcome to the clothing store!");
                        User user = new User(0, username, password);
                        user.showMenu(connection);
                    }
                    else {
                        System.out.println("Username:");
                        String username = reader.readLine();
                        System.out.println("Password:");
                        String password = reader.readLine();
                        User user = db.insertUser(connection, username, password, "usertable");
                        if (user == null) {
                            Thread.sleep(3000);
                            break;
                        }
                        user.showMenu(connection);
                    }
                    break;
                case 2:
                    String usernameForAdmin, passwordForAdmin;
                    System.out.println("Username:");
                    usernameForAdmin = reader.readLine();
                    System.out.println("Password:");
                    passwordForAdmin = reader.readLine();
                    if (Admin.IsAdmin(usernameForAdmin, passwordForAdmin)) {
                        admin.showMenu(connection);
                    }
                    else {
                        System.out.println("You are not an admin");
                        System.out.println("Redirecting to the main menu...");
                        Thread.sleep(5000);
                        }
                    break;
                case 3:
                    System.out.println("1 - Sign in");
                    System.out.println("2 - Sign up");
                    option = Integer.parseInt(scanner.next());
                    if (option == 1) {
                        String usernameForSalesMan, passwordForSalesMan, companyName;
                        System.out.println("Username:");
                        usernameForSalesMan = reader.readLine();
                        System.out.println("Password:");
                        passwordForSalesMan = reader.readLine();
                        System.out.println("Company name: ");
                        companyName = reader.readLine();

                        boolean flagForSalesman = db.IsExists(connection, usernameForSalesMan, passwordForSalesMan, "salesmantable");
                        if (!flagForSalesman) {
                            continue;
                        }
                        Salesman salesman = new Salesman(0, usernameForSalesMan, passwordForSalesMan, companyName);
                        salesman.showMenu(connection);
                    }
                    else if (option == 2) {
                        String usernameForSalesMan, passwordForSalesMan, companyName;
                        System.out.println("Username:");
                        usernameForSalesMan = scanner.nextLine();
                        System.out.println("Password:");
                        passwordForSalesMan = scanner.nextLine();
                        System.out.println("Company name: ");
                        companyName = scanner.nextLine();

                        Salesman saler = db.insertSalesman(connection, usernameForSalesMan, passwordForSalesMan, companyName, "salesmantable");
                        saler.showMenu(connection);

                        System.out.println("Congratulations! You've been registered!");

                }
                    else {
                        System.out.println("Invalid option, try again");
                        continue;
                    }
                default:
                    System.out.println("--Invalid option--");
                    System.out.println("Please, try again");
                    break;
            }
        }
        connection.close();
    }
}

