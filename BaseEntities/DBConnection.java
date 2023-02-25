package BaseEntities;

import Person.Salesman;
import Person.User;


import javax.print.attribute.standard.JobMessageFromOperator;
import javax.sql.rowset.RowSetWarning;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.MessageFormat;

public class DBConnection {
    private static DBConnection dbInstance;
    private DBConnection() {}
    //Singleton pattern to get only single instance to use all the way in application
    public static DBConnection getInstance() {
        if (dbInstance == null) {
            dbInstance = new DBConnection();
        }
        return dbInstance;
    }
    //Getting access to Singleton object
    //Throws an exception, if Singleton object doesn't exist yet
    public static DBConnection getAccess() {
        try {
            return dbInstance;
        }
        catch (NullPointerException e) {
            System.out.println("The Singleton object doesn't have an instance yet");
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection(String name, String user, String password) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop?characterEncoding=latin1", user, password);
            if (connection != null) {
                System.out.println("Connection established");
            }
            else {
                System.out.println("Connection failed");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }
    public User insertUser(Connection connection, String username, String password, String table) throws SQLException {
        if (!IsExists(connection, username, password, table)) {
            System.out.println("This username already exists. Please choose another one");
            return null;
        }
        try {
            String insertUser = " insert into usertable (username, password, card) values (?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertUser);
            insertStatement.setString(1, username);
            insertStatement.setString(2, password);
            insertStatement.setString(3, "123456789");
            insertStatement.execute();
        }
        catch (SQLException e) {
            System.out.println("Error occurred when inserting user");
            throw new RuntimeException(e);
        }
        return new User(0, username, password);
    }

    public Salesman insertSalesman(Connection connection, String username, String password, String companyName, String table) throws SQLException {
        if (!IsExists(connection, username, password, table)) {
            return null;
        }
        try {
            String insertUser = " insert into salesmantable (Username, passwordd, CompanyName) values (?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertUser);
            insertStatement.setString(1, username);
            insertStatement.setString(2, password);
            insertStatement.setString(3, companyName);
            insertStatement.execute();
        }
        catch (SQLException e) {
            System.out.println("Error occurred when inserting user");
            throw new RuntimeException(e);
        }
        return new Salesman(0, username, password, companyName);
    }

    public boolean IsExists(Connection connection, String username, String password, String table) throws SQLException {
        try {
            PreparedStatement checkExist = connection.prepareStatement("SELECT * FROM " + table + " WHERE (username, password) = (?,?)");
            checkExist.setString(1, username);
            checkExist.setString(2, password);
            ResultSet resultSet = checkExist.executeQuery();
            if (resultSet.next()) {
                return false;
            }
        }
        catch (SQLException e) {
            System.out.println("Error occurred when checking username");
            throw new RuntimeException(e);
        }
        return true;
    }
    public void insertCreditCard(Connection connection, String card) {
        String insertSQL = " insert into usertable (card) values (?)";
        try {
            PreparedStatement insertStmt = connection.prepareStatement(insertSQL);
            insertStmt.setString(1, card);
            insertStmt.execute();
        }
        catch (SQLException e) {
            System.out.println("An error occurred when inserting card");
            throw  new RuntimeException(e);
        }
    }
    public void showProducts(Connection connection, String table, String gender) throws SQLException {
        Statement statement = connection.createStatement();
        String showSQL = "SELECT * FROM " + table + " WHERE gender = " + gender;
        ResultSet resultSet = statement.executeQuery(showSQL);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String brand = resultSet.getString("brand");
            String size = resultSet.getString("size");
            String color = resultSet.getString("color");
            int amount = resultSet.getInt("amount");
            String kind = resultSet.getString("kind");
            String season = resultSet.getString("season");
            int price = resultSet.getInt("price");
            System.out.printf("""
                        ID: %s
                            %s
                            %s
                            %s
                            %s
                            %s
                            %s
                            %s
                        """, id, brand, size, color, amount, kind, season, price);
        }
    }
    public void showTable(Connection connection, String tableName) throws SQLException {
        String showTableSQL = "SELECT * FROM " + tableName;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(showTableSQL);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String brand = resultSet.getString("brand");
            String size = resultSet.getString("size");
            String color = resultSet.getString("color");
            int amount = resultSet.getInt("amount");
            int price = resultSet.getInt("price");
            String msg = MessageFormat.format("""
                    ID: {0}
                        Brand: {1}
                        Size: {2}
                        Color: {3}
                        Amount: {4}
                        Price: {5}
                    """, id, brand, size, color, amount, price);
            System.out.println(msg);
        }
    }
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public void deleteObject(Connection connection) throws SQLException {
        System.out.println("Choose a table where you wanna delete an object:");
        System.out.println("1 - Jackets, 2 - Coats");

        int option = 0;
        String table;
        try {
            option = Integer.parseInt(reader.readLine());
        }
        catch (IOException e) {
            System.out.println("An error occurred when deleting object");
        }
        if (option == 1) {
            table = "jacketdatafull — копия";
        }
        else table = "coatdatafull";
        try {
            showTable(connection, table);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        boolean delete = true;
        while (delete) {
            System.out.println("Choose an ID to delete or type -1 to exit");
            int id;
            try {
                id = Integer.parseInt(reader.readLine());
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (id == -1) {
                break;
            }
            String deleteSQL = "DELETE FROM " + table + "WHERE id=" + id;
            Statement statement = connection.createStatement();
            statement.executeUpdate(deleteSQL);
        }
    }
    public void deleteSalesman(Connection connection) throws SQLException, InterruptedException {
        while (true) {
            System.out.println("Choose by id in order to delete a salesman or type -1 to exit");
            Thread.sleep(3000);
            showSalesman(connection);
            int option;
            try {
                option = Integer.parseInt(reader.readLine());
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (option == -1) {
                break;
            }
            String deleteSalesmanSQL = "DELETE FROM salesman WHERE id=" + option;
            Statement statement = connection.createStatement();
            statement.executeUpdate(deleteSalesmanSQL);
            System.out.println("Deletion finished successfully");
        }
    }
    public void deleteUser(Connection connection) throws SQLException, InterruptedException {
        while (true) {
            System.out.println("Choose by id in order to delete an user or -1 for exit");
            Thread.sleep(3000);
            showUser(connection);
            int option;
            try {
                option = Integer.parseInt(reader.readLine());
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (option == -1) {
                break;
            }

            String deleteUserSQL = "DELETE FROM usertable WHERE id=" + option;
            Statement statement = connection.createStatement();
            statement.executeUpdate(deleteUserSQL);
            System.out.println("Deletion finished successfully");
        }
    }
    private void showSalesman(Connection connection) throws SQLException {
        String showSalesmansSQL = "SELECT * FROM salesman";
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(showSalesmansSQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while(resultSet.next()) {
            String companyName = resultSet.getString("companyname");
            int id = resultSet.getInt("id");
            System.out.printf("""
                    ID: %s
                        Company: %s
                    """, id, companyName);
        }
    }
    private void showUser(Connection connection) throws SQLException {
        String showUsersSQL = "SELECT * FROM usertable";
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(showUsersSQL);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            System.out.printf("""
                    ID: %s
                        Username: %s
                        Password: %s
                    """, id, username, password);
        }
    }
}
