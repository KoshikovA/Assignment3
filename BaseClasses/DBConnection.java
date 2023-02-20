package BaseClasses;

import com.mysql.jdbc.Driver;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
}
