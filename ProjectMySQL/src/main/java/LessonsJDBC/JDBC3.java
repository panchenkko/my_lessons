package LessonsJDBC;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;
import java.util.Calendar;

public class JDBC3 {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static final String INSERT_NEW = "INSERT INTO testtableforjdbc3 VALUES(?,?,?,?,?,?)";

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement;

        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            preparedStatement = connection.prepareStatement(INSERT_NEW);

            preparedStatement.setString(2, "My first title");
            preparedStatement.setString(3, "My description");
            preparedStatement.setFloat(4, 0.2f);
            preparedStatement.setBoolean(5, true);
            preparedStatement.setDate(6, new Date(Calendar.getInstance().getTimeInMillis()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Неудалось загрузить класс драйвера!");
        }

    }
}
