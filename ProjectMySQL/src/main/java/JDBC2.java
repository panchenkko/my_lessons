import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

public class JDBC2 {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            System.out.println("Неудалось загрузить класс драйвера!");
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            statement.execute("INSERT INTO weather (temperature, atmospherePressure) VALUES (10, 800);");

            statement.executeUpdate("UPDATE weather SET temperature = 30, atmospherePressure = 800;");

//             SELECT
            ResultSet set = statement.executeQuery("SELECT * FROM weather");

            // Создаем пакет нескольких sql запросов
            statement.addBatch("INSERT INTO weather (temperature, atmospherePressure) VALUES (10, 800);");
            statement.addBatch("INSERT INTO weather (temperature, atmospherePressure) VALUES (20, 800);");
            statement.addBatch("INSERT INTO weather (temperature, atmospherePressure) VALUES (30, 800);");
            statement.executeBatch();
//             Стирает старый пакет и после можно создавать новый
            statement.clearBatch();

        } catch (SQLException e) {
            System.out.println("Неудалось загрузить класс драйвера!");
        }
    }
}
