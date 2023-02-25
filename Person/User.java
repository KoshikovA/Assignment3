package Person;

import BaseEntities.DBConnection;
import Interfaces.Showable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User extends Person implements Showable {
    private DBConnection dbConnection = DBConnection.getAccess();
    private List<Integer> prices = new ArrayList<>();
    private int balance;

    public User(int id, String username, String password) {
        super(id, username, password);
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public void showMenu(Connection connection) throws InterruptedException {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("--- User menu ---");
            System.out.println("1 - Buy products");
            System.out.println("2 - Show balance");
            System.out.println("3 - Replenish the balance");
            System.out.println("4 - Exit and buy");
            Scanner scanner = new Scanner(System.in);
            int option = Integer.parseInt(scanner.next());
            switch (option) {
                case 1:
                    String table;
                    String gender;

                    System.out.println("What do you wanna buy?");
                    System.out.println("1 - Jackets,  2 - Coats");
                    option = Integer.parseInt(scanner.next());

                    //Choosing table
                    if (option == 1) {
                        table = "jacketdatafull — копия";
                    } else if (option == 2) {
                        table = "coatdatafull";
                    }
                    else {
                        System.out.println("--Undefined option--");
                        System.out.println("Try again later");
                        break;
                    }

                    System.out.println("What gender are you?");
                    System.out.println("1 - Male,  2 - Female");
                    option = Integer.parseInt(scanner.next());
                    if (option == 1) {
                        gender = "male";
                    } else if (option == 2) {
                        gender = "female";
                    }
                    else {
                        System.out.println("--Undefined option--");
                        System.out.println("Try again later");
                        break;
                    }
                    System.out.println("Initializing...");
                    Thread.sleep(3000);
                    System.out.println("Products according to your preferences:");
                    try {
                        dbConnection.showProducts(connection, table, gender);
                    } catch (SQLException e) {
                        System.out.println("An error occurred when showing products");
                        throw new RuntimeException(e);
                    }
                    System.out.println("Choose them by ID in order to buy");
                    System.out.println("Input IDs by delimiter \"\" such as: 1 2 3");
                    String[] productsIDs = scanner.nextLine().split(" ");
                    int[] IDs = new int[productsIDs.length];
                    for (int i = 0;i < productsIDs.length;i++) {
                        IDs[i] = Integer.parseInt(productsIDs[i]);
                    }
                    for (int id : IDs) {
                        String selectProductsSQL = "SELECT * FROM " + table + " WHERE id = ?";
                        try {
                            PreparedStatement selectClothes = connection.prepareStatement(selectProductsSQL);
                            selectClothes.setInt(1, id);
                            ResultSet clothes = selectClothes.executeQuery();
                            while (clothes.next()) {
                                int amount = clothes.getInt("amount");
                                String updateSQL = "UPDATE " + table + " SET amount = " +
                                        (amount - 1) + "WHERE id = " + id;
                                Statement updateAmount = connection.createStatement();
                                updateAmount.executeUpdate(updateSQL);
                                if (amount - 1 == 0) {
                                    String deleteSQL = "DELETE FROM " + table + "WHERE " + amount + " = 0";
                                    Statement deleteObject = connection.createStatement();
                                    deleteObject.executeQuery(deleteSQL);
                                }
                                prices.add(clothes.getInt("price"));
                            }
                        }
                        catch (SQLException e)
                        {
                            System.out.println("An error occurred when choosing clothes");
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("Your clothes have been add to your basket");
                    System.out.println("You'll get it when leaving");
                    break;
                case 2:
                    System.out.println(getBalance());
                    break;
                case 3:
                    System.out.println("Your balance " + getBalance());
                    System.out.println("Insert credit card to make a transaction");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String creditCard = null;
                    try {
                        creditCard = reader.readLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    boolean isCorrectCard = true;
                    for (int i = 0; i < creditCard.length();i++) {
                        if (!Character.isDigit(creditCard.charAt(i))) {
                            System.out.println("An error occurred, try again later");
                            isCorrectCard = false;
                            break;
                        }
                    }
                    if (!isCorrectCard) {
                        break;
                    }
                    dbConnection.insertCreditCard(connection, creditCard);
                    System.out.println("Your card has been inserted successfully");
                    System.out.println("Enter the desired top-up amount:");
                    int balance = Integer.parseInt(scanner.next());
                    this.setBalance(balance);
                    System.out.println("Your balance has been successfully replenished");
                    break;
                case 4:
                    isRunning = false;
                    int sum = 0;
                    for (int price : prices) {
                        sum += price;
                    }
                    if (sum > this.getBalance()) {
                        System.out.println("You can't get your purchase, because you don't have enough money");
                        System.out.println("Please come back after the replenishment");
                        break;
                    }
                    System.out.println("The total amount of your purchase is " + sum);
                    System.out.println("Thank you for shopping");
                    break;
                default:
                    System.out.println("Invalid option, please try again!");
            }
        }
    }
}