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
                        boolean flag = db.IsExists(connection, username);
                        if (!flag) {
                            continue;
                        }
                        User user = new User(0, username, password);
                        user.showMenu(connection);
                    }
                    else {
                        System.out.println("Username:");
                        String username = scanner.nextLine();
                        System.out.println("Password:");
                        String password = scanner.nextLine();
                        User user = db.insertUser(connection, username, password);
                        user.showMenu(connection);
                    }
                    break;/*
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
                    break;*/
                default:
                    System.out.println("--Invalid option--");
                    System.out.println("Please, try again");
                    break;
            }
        }
        connection.close();
    }
}

