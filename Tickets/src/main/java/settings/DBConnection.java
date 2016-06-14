package settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/Tickets";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    private Connection connection;

    public DBConnection() {
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);

            if (!connection.isClosed())
                System.out.println("Соединение с БД установлено!");
            if (connection.isClosed())
                System.out.println("Соединение с БД закрыто!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
