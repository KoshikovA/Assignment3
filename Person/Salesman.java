package Person;

public class Salesman extends Person {
    public Salesman(int id, String username, String password) {
        super(id, username, password);
    }
    @Override
    public String getPassword() {
        return super.getPassword();
    }
    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }
}
