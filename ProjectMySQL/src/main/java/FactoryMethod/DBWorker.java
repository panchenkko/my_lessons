package FactoryMethod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBWorker {

    private static final String URL = "jdbc:postgresql://localhost:5432/Weather";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    private Connection connection;

    public DBWorker() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
