package Interfaces;

import java.sql.Connection;

public interface Showable {
    public void showMenu(Connection connection) throws InterruptedException;
}
