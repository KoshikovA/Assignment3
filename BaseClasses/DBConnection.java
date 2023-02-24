package BaseClasses;

import Person.Salesman;
import Person.User;



import java.sql.*;

public class DBConnection {
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
}
