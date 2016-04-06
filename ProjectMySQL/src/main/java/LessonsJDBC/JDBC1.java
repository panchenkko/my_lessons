package LessonsJDBC;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.beans.Statement;
import java.sql.*;

public class JDBC1 {

    private static final String URL = "jdbc:postgresql://localhost:5432/Weather.Weather";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {

        Connection connection; // Хранит соединение с базой данных
//        Statement statement; // Хранит и выполняет sql запросы
//        ResultSet resultSet; // Получает результаты выполнения sql запросов

        try {
            // Регистрируем драйвер
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);

            // Подключаемся к базе данных
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            if (!connection.isClosed()) {
                System.out.println("Соединение с БД установлено!");
            }

            connection.close();

            if (connection.isClosed()) {
                System.out.println("Соединение с БД закрыто!");
            }
        } catch (SQLException e) {
            System.out.println("Неудалось загрузить класс драйвера!");
        }
    }
}
