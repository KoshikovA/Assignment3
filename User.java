import java.util.ArrayList;
import java.util.Objects;

public class User {
    private int id=0;
    private String username;
    private String password;
    ArrayList<Objects> basket;

    public User(int id,String username,String password){
        this.id=id;
        this.username=username;
        this.password=password;
    }
    public User(){

    }
    public int getId() {
        id++;
        return id;
    }
    public void setId(int id) {
    this.id=id;
    }
    public String getUsername() {
    return username;
    }
    public void setUsername(String username) {
    this.username=username;
    }
    public String getPassword() {
    return password;
    }
    public void  setPassword() {
    this.password=password;
    }


}