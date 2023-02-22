package Person;

abstract public class Person {
    private int id = 0;
    private String username;
    private String password;
    public Person(int id, String username, String password) {
        this.id++;
        this.username = username;
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        id++;
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
