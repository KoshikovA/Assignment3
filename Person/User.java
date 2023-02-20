package Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User extends Person implements Showable {
    private List<Object> basket = new ArrayList<>();
    private double balance;

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

    public void setBasket(List<Object> basket) {
        this.basket = basket;
    }

    public List<Object> getBasket() {
        return basket;
    }

    public int getId() {
        id++;
        return id;
    }
    public void setId(int id) {
    this.id=id;
    }
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public void showMenu() throws InterruptedException {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("--- User menu ---");
            System.out.println("1 - Buy a product");
            System.out.println("2 - Show balance");
            System.out.println("3 - Replenish the balance");
            System.out.println("4 - Exit and buy");
            Scanner scanner = new Scanner(System.in);
            int option = Integer.parseInt(scanner.next());
            switch (option) {
                case 1:
                    //Options required to show data
                    String table;
                    String gender;

                    System.out.println("What do you wanna buy?");
                    System.out.println("1 - Jackets,  2 - Coats");
                    option = Integer.parseInt(scanner.next());

                    //Choosing table
                    if (option == 1) {
                        table = "Jackets";
                    } else if (option == 2) {
                        table = "Coats";
                    }
                    else {
                        System.out.println("--Undefined option--");
                        System.out.println("Try again");
                    }


                    //Choosing gender
                    System.out.println("What gender are you?");
                    System.out.println("1 - Male,  2 - Female");
                    option = Integer.parseInt(scanner.next());
                    if (option == 1) {
                        gender = "Male";
                    } else if (option == 2) {
                        gender = "Female";
                    }
                    else {
                        System.out.println("--Undefined option--");
                    }

                    System.out.println("Initializing...");
                    Thread.sleep(3000);
                    System.out.println("Products according to your preferences:");
                    System.out.println("Choose them by ID");
                    //database
                    break;
                case 2:
                    System.out.println(getBalance());
                    //database
                    break;
                case 3:
                    System.out.println("Your balance " + getBalance());
                    System.out.println("Insert credit card to make transaction");
                    String creditCard = scanner.nextLine();
                    for (int i = 0; i < creditCard.length();i++) {
                        if (!Character.isDigit(creditCard.charAt(i))) {
                            System.out.println("An error occurred, try again later");
                        }
                    }
                    //database
                    break;
                case 4:
                    isRunning = false;
                    System.out.println("Your basket:");
                    //database
                    System.out.println("Total sum: ");
                    //database
                    break;
                default:
                    System.out.println("Invalid option, please try again!");
            }
        }
    }
}