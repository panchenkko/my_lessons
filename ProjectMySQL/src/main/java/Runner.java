import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Runner {

    public static void main(String[] args) {
        DBWorker dbWorker = new DBWorker();

        String query = "SELECT * FROM weather";

        try {
            Statement statement = dbWorker.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Weather weather = new Weather();

                weather.setTemperature(resultSet.getInt(2));
                weather.setAtmospherePressure(resultSet.getInt(3));

                System.out.println(weather);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

//        Snow snow = new Snow(-10, 768, 1, 100);
//        System.out.println("Снег: \n" + snow);
//        System.out.println();
//        Rain rain = new Rain(0, 780, 110, "Ливень");
//        System.out.println("Дождь: \n" + rain);
//        System.out.println();
//        Blizzard blizzard = new Blizzard(-20, 800, 5, 150, "Штормовой");
//        System.out.println("Снежная буря: \n" + blizzard);
    }
}
