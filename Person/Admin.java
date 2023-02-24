package Person;
import java.sql.Connection;
import java.util.Scanner;

public class Admin extends Person implements Showable {
    public Admin(int id, String username, String password) {
        super(id, username, password);
    }

    /*public static boolean isAdmin(String username, String password) {
        //database
    }
    public static Admin getInstanceFromDatabase(Connection connection) {
        //get admin from database
    }*/
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
    public void showMenu(Connection connection) {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                    --- Admin menu ---
                    1 - Show database
                    2 - Add another admin
                    3 - Delete object
                    4 - Delete salesman
                    5 - Exit
                    """);
            Scanner scanner = new Scanner(System.in);
            int option = Integer.parseInt(scanner.next());
            switch (option) {
                case 1:
                    //database showing
                    break;
                case 2:
                    //database add admin
                    break;
                case 3:
                    //delete object in database
                    break;
                case 4:
                    //delete salesman in database
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
