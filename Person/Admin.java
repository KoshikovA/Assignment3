package Person;
import BaseEntities.DBConnection;
import Interfaces.Showable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin extends Person implements Showable {
    private DBConnection dbConnection = DBConnection.getAccess();
    private static Admin admin;
    private Admin(String username, String password) {
        super(0, username, password);
    }
    public static Admin getInstance(String username, String password) {
        if (admin == null) {
            admin = new Admin(username, password);
        }
        return admin;
    }
    public static Admin getAccess(String username, String password) {
        try {
            return admin;
        }
        catch (NullPointerException e) {
            System.out.println("Admin doesn't have an instance yet");
            throw new RuntimeException(e);
        }
    }
    public static boolean IsAdmin(String username, String password) {
        String adminUsername = admin.getUsername();
        String adminPassword = admin.getPassword();
        return adminUsername.equals(username) && adminPassword.equals(adminPassword);
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
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public int getId() {
        return super.getId();
    }
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    @Override
    public void showMenu(Connection connection) throws InterruptedException {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                    --- Admin menu ---
                    1 - Show database
                    2 - Delete user
                    3 - Delete object
                    4 - Delete salesman
                    5 - Exit
                    """);
            Scanner scanner = new Scanner(System.in);
            int option = Integer.parseInt(scanner.next());
            switch (option) {
                case 1:
                    System.out.println("""
                            Choose table:
                            1 - Coats
                            2 - Jackets
                            """);
                    int tableID;
                    String table;

                    try {
                        tableID = Integer.parseInt(reader.readLine());
                    }
                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                    if (tableID == 1) {
                        table = "coatdatafull";
                    }
                    else {
                        table = "jacketdatafull — копия";
                    }


                    try {
                        dbConnection.showTable(connection, table);
                    }
                    catch (SQLException e) {
                        System.out.println("An error occurred when showing table");
                        throw new RuntimeException(e);
                    }
                    break;

                case 2:
                    try {
                        dbConnection.deleteUser(connection);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                case 3:
                    try {
                        dbConnection.deleteObject(connection);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 4:
                    try {
                        dbConnection.deleteSalesman(connection);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 5:
                    isRunning = false;
                    break;
                default:
                    System.out.println("--Invalid option--");
                    System.out.println("Try again");
                    break;
            }
        }
    }
}
