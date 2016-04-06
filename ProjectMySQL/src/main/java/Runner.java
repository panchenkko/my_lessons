import Weather.Snow;
import Weather.Rain;
import Weather.Blizzard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Runner {

    public int insertWeather(Scanner sc, DBWorker dbWorker) {
        System.out.print("Температура: ");
        int temperature = sc.nextInt();
        System.out.print("Атмосферное давление: ");
        int atmospherePressure = sc.nextInt();

        try (PreparedStatement preparedStatement = dbWorker.getConnection().prepareStatement(
                "INSERT INTO weather (temperature, atmospherepressure) VALUES(?,?)",
                Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, temperature);
            preparedStatement.setInt(2, atmospherePressure);
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int insertSnow(Scanner sc, DBWorker dbWorker, int numLastColumn) {
        System.out.print("Размер снежинки (в см) : ");
        int size = sc.nextInt();
        System.out.print("Скорость падения снега: ");
        int speed = sc.nextInt();

        try (PreparedStatement preparedStatement = dbWorker.getConnection().prepareStatement(
                "INSERT INTO snow (weather_id, size, speed) VALUES(?,?,?)",
                Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, numLastColumn);
            preparedStatement.setInt(2, size);
            preparedStatement.setInt(3, speed);
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void insertRain(Scanner sc, DBWorker dbWorker, int numLastColumn) {
        System.out.print("Скорость падения дождя (м/сек) : ");
        int speedRain = sc.nextInt();
        System.out.print("Насколько всё плохо: ");
        String powerLevelRain = sc.next();

        try (PreparedStatement preparedStatement = dbWorker.getConnection().prepareStatement(
                "INSERT INTO rain (weather_id, speed, powerlevel) VALUES(?,?,?)",
                Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, numLastColumn);
            preparedStatement.setInt(2, speedRain);
            preparedStatement.setString(3, powerLevelRain);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertBlizzard(Scanner sc, DBWorker dbWorker, int num) {
        System.out.print("Насколько всё плохо: ");
        String powerLevelBlizzard = sc.next();
        try (PreparedStatement preparedStatement2 = dbWorker.getConnection().prepareStatement(
                "INSERT INTO blizzard (snow_id, powerlevel) VALUES(?,?)",
                Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement2.setInt(1, num);
            preparedStatement2.setString(2, powerLevelBlizzard);
            preparedStatement2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectSnow(DBWorker dbWorker) {
        String select = "SELECT w.temperature, w.atmospherepressure, s.size, s.speed " +
                        "FROM weather AS w RIGHT JOIN snow AS s ON w.id = s.weather_id";
        try {
            Statement statement = dbWorker.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            while (resultSet.next()) {
                Snow snow = new Snow();

                snow.setTemperature(resultSet.getInt("temperature"));
                snow.setAtmospherePressure(resultSet.getInt("atmospherePressure"));
                snow.setSize(resultSet.getInt("size"));
                snow.setSpeed(resultSet.getInt("speed"));
                // Так же можно по нумерации колонок
//                weather.setTemperature(resultSet.getInt(2));
//                weather.setAtmospherePressure(resultSet.getInt(3));

                System.out.println(snow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectRain(DBWorker dbWorker) {
        String select = "SELECT w.temperature, w.atmospherepressure, r.powerlevel, r.speed " +
                        "FROM weather AS w RIGHT JOIN rain AS r ON w.id = r.weather_id";
        try {
            Statement statement = dbWorker.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            while (resultSet.next()) {
                Rain rain = new Rain();

                rain.setTemperature(resultSet.getInt("temperature"));
                rain.setAtmospherePressure(resultSet.getInt("atmospherePressure"));
                rain.setPowerLevel(resultSet.getString("powerLevel"));
                rain.setSpeed(resultSet.getInt("speed"));

                System.out.println(rain);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectBlizzard(DBWorker dbWorker) {
        String select = "SELECT w.temperature, w.atmospherepressure, s.size, b.powerlevel, s.speed " +
                        "FROM snow AS s LEFT JOIN weather AS w ON w.id = s.weather_id " +
                        "RIGHT JOIN blizzard as b ON s.id = b.snow_id";
        try {
            Statement statement = dbWorker.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            while (resultSet.next()) {
                Blizzard blizzard = new Blizzard();

                blizzard.setTemperature(resultSet.getInt("temperature"));
                blizzard.setAtmospherePressure(resultSet.getInt("atmospherePressure"));
                blizzard.setSize(resultSet.getInt("size"));
                blizzard.setPowerLevel(resultSet.getString("powerLevel"));
                blizzard.setSpeed(resultSet.getInt("speed"));

                System.out.println(blizzard);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTable(Scanner sc, DBWorker dbWorker) {
//        System.out.print("1. Снег\n" + "2. Дождь\n" + "3. Снегопад \n" + "Ответ: ");
//        int answer = sc.nextInt();
        try {
            Statement statement = dbWorker.getConnection().createStatement();

//            switch (answer) {
//                case 1: break;
//                case 2: break;
//                case 3: statement.execute(
//                            "DELETE FROM blizzard USING snow, weather " +
//                            "WHERE blizzard.snow_id = snow.id AND snow.weather_id = weather.id");
//                    break;
//                default: System.out.println("Вы не выбрали таблицу!");
//            }

            statement.execute("DELETE FROM blizzard");
            statement.execute("DELETE FROM rain");
            statement.execute("DELETE FROM snow");
            statement.execute("DELETE FROM weather");
            // Обнуление счетчика auto_increment
            statement.execute("ALTER SEQUENCE blizzard_id_seq RESTART WITH 1");
            statement.execute("ALTER SEQUENCE rain_id_seq RESTART WITH 1");
            statement.execute("ALTER SEQUENCE snow_id_seq RESTART WITH 1");
            statement.execute("ALTER SEQUENCE weather_id_seq RESTART WITH 1");

            System.out.println("Удалено!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Runner runner = new Runner();
        Scanner sc = new Scanner(System.in);

        DBWorker dbWorker = new DBWorker();

        int table;
        int numLastColumn;

        do {
            System.out.println();
            System.out.print(
                    "1. Заполнить таблицу \"Снег\" \n" +
                    "2. Заполнить таблицу \"Дождь\" \n" +
                    "3. Заполнить таблицу \"Снегопад\" \n \n" +

                    "4. Вывести таблицу \"Снег\" \n" +
                    "5. Вывести таблицу \"Дождь\" \n" +
                    "6. Вывести таблицу \"Снегопад\" \n \n" +

                    "0. Удалить все данные из всех таблиц \n \n" +

                    "7. Выйти \n \n" +
                            "Ответ: "
            );
            table = sc.nextInt();
            System.out.println();

            if (table == 1 || table == 2 || table == 3) {
                numLastColumn = runner.insertWeather(sc, dbWorker);
                if (table == 2)
                    runner.insertRain(sc, dbWorker, numLastColumn);
                else {
                    int num = runner.insertSnow(sc, dbWorker, numLastColumn);

                    if (table == 3)
                        runner.insertBlizzard(sc, dbWorker, num);
                }
            }

            System.out.println();
            switch (table) {
                case 4: runner.selectSnow(dbWorker);
                        break;
                case 5: runner.selectRain(dbWorker);
                        break;
                case 6: runner.selectBlizzard(dbWorker);
                        break;
                case 0: runner.deleteTable(sc, dbWorker);
                        break;
                case 7: System.out.println("Пока");
                    break;
            }
        } while(table != 7);
    }
}
