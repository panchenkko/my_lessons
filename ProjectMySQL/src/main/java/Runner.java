import Weather.Snow;
import Weather.Rain;
import Weather.Blizzard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Runner {

    public void insertWeather(Scanner sc, DBWorker dbWorker, Integer incRain, Integer incSnow) {
        System.out.print("Температура: ");
        int temperature = sc.nextInt();
        System.out.print("Атмосферное давление: ");
        int atmospherePressure = sc.nextInt();

        if (incRain != null) {
            try (PreparedStatement preparedStatement = dbWorker.getConnection().prepareStatement(
                    "INSERT INTO weather (temperature, atmospherepressure, rain_id) VALUES(?,?,?)")) {
                preparedStatement.setInt(1, temperature);
                preparedStatement.setInt(2, atmospherePressure);
                preparedStatement.setInt(3, incRain);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else
        if (incSnow != null) {
            try (PreparedStatement preparedStatement = dbWorker.getConnection().prepareStatement(
                    "INSERT INTO weather (temperature, atmospherepressure, snow_id) VALUES(?,?,?)")) {
                preparedStatement.setInt(1, temperature);
                preparedStatement.setInt(2, atmospherePressure);
                preparedStatement.setInt(3, incSnow);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Integer insertSnow(Scanner sc, DBWorker dbWorker, Integer incBlizzard) {
        System.out.print("Размер снежинки (в см) : ");
        int size = sc.nextInt();
        System.out.print("Скорость падения снега: ");
        int speed = sc.nextInt();

        if (incBlizzard == null) {
            try (PreparedStatement preparedStatement = dbWorker.getConnection().prepareStatement(
                    "INSERT INTO snow (size, speed) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, size);
                preparedStatement.setInt(2, speed);
                preparedStatement.executeUpdate();

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try (PreparedStatement preparedStatement = dbWorker.getConnection().prepareStatement(
                    "INSERT INTO snow (size, speed, blizzard_id) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, size);
                preparedStatement.setInt(2, speed);
                preparedStatement.setInt(3, incBlizzard);
                preparedStatement.executeUpdate();

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Integer insertRain(Scanner sc, DBWorker dbWorker) {
        System.out.print("Скорость падения дождя (м/сек) : ");
        int speedRain = sc.nextInt();
        System.out.print("Насколько всё плохо: ");
        String powerLevelRain = sc.next();

        try (PreparedStatement preparedStatement = dbWorker.getConnection().prepareStatement(
                "INSERT INTO rain (speed, powerlevel) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, speedRain);
            preparedStatement.setString(2, powerLevelRain);
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer insertBlizzard(Scanner sc, DBWorker dbWorker) {
        System.out.print("Насколько всё плохо: ");
        String powerLevelBlizzard = sc.next();
        try (PreparedStatement preparedStatement = dbWorker.getConnection().prepareStatement(
                "INSERT INTO blizzard (powerlevel) VALUES(?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, powerLevelBlizzard);
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void selectSnow(DBWorker dbWorker) {
        String select = "SELECT s.id, w.temperature, w.atmospherepressure, s.size, s.speed " +
                        "FROM weather AS w RIGHT JOIN snow AS s ON w.snow_id = s.id";
        try (Statement statement = dbWorker.getConnection().createStatement();
             ResultSet rs = statement.executeQuery(select)) {
            while (rs.next()) {
                Snow snow = new Snow();

                snow.setId(rs.getInt("id"));
                snow.setTemperature(rs.getInt("temperature"));
                snow.setAtmospherePressure(rs.getInt("atmospherePressure"));
                snow.setSize(rs.getInt("size"));
                snow.setSpeed(rs.getInt("speed"));

                System.out.println(snow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectRain(DBWorker dbWorker) {
        String select = "SELECT r.id, w.temperature, w.atmospherepressure, r.powerlevel, r.speed " +
                        "FROM weather AS w RIGHT JOIN rain AS r ON w.rain_id = r.id";
        try (Statement statement = dbWorker.getConnection().createStatement();
             ResultSet rs = statement.executeQuery(select)) {
            while (rs.next()) {
                Rain rain = new Rain();

                rain.setId(rs.getInt("id"));
                rain.setTemperature(rs.getInt("temperature"));
                rain.setAtmospherePressure(rs.getInt("atmospherePressure"));
                rain.setPowerLevel(rs.getString("powerLevel"));
                rain.setSpeed(rs.getInt("speed"));

                System.out.println(rain);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectBlizzard(DBWorker dbWorker) {
        String select = "SELECT b.id, w.temperature, w.atmospherepressure, s.size, b.powerlevel, s.speed " +
                        "FROM snow AS s LEFT JOIN weather AS w ON w.snow_id = s.id " +
                        "RIGHT JOIN blizzard as b ON s.blizzard_id = b.id";
        try (Statement statement = dbWorker.getConnection().createStatement();
             ResultSet rs = statement.executeQuery(select)) {
            while (rs.next()) {
                Blizzard blizzard = new Blizzard();

                blizzard.setId(rs.getInt("id"));
                blizzard.setTemperature(rs.getInt("temperature"));
                blizzard.setAtmospherePressure(rs.getInt("atmospherePressure"));
                blizzard.setSize(rs.getInt("size"));
                blizzard.setPowerLevel(rs.getString("powerLevel"));
                blizzard.setSpeed(rs.getInt("speed"));

                System.out.println(blizzard);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Scanner sc, DBWorker dbWorker) {
        System.out.print("1. Удалить все данные\n" +
                         "2. Удалить выбранный ряд\n\n" + "Ответ: ");
        int whatToDelete = sc.nextInt();

        if (whatToDelete == 1) {
            deleteAll(dbWorker);
            SPACE();
            System.out.println("Удалено!");
        } else
        if (whatToDelete == 2) {
            System.out.print("Выберите таблицу: \n" +
                    "1. Снег\n" +
                    "2. Дождь\n" +
                    "3. Снегопад \n\n" +
                    "Ответ: ");
            int answer = sc.nextInt();
            SPACE();
            if (answer == 1 || answer == 2 || answer == 3) {
                System.out.print("Укажите номер ряда: ");
                int numRow = sc.nextInt();
                switch (answer) {
                    case 1: deleteSnow(dbWorker, numRow);
                            break;
                    case 2: deleteRain(dbWorker, numRow);
                            break;
                    case 3: deleteBlizzard(dbWorker, numRow);
                            break;
                    default: SPACE();
                        System.out.println("Вы не выбрали таблицу!");
                }
                SPACE();
                System.out.println("Удалено!");
            } else {
                SPACE();
                System.out.println("Попробуйте ещё раз.");
            }
        } else {
            SPACE();
            System.out.println("Попробуйте ещё раз.");
        }
    }

    public void deleteAll(DBWorker dbWorker) {
        try (Statement statement = dbWorker.getConnection().createStatement()) {
            statement.execute("DELETE FROM weather");
            statement.execute("DELETE FROM rain");
            statement.execute("DELETE FROM snow");
            statement.execute("DELETE FROM blizzard");
            // Обнуление счетчика auto_increment
            statement.execute("ALTER SEQUENCE weather_id_seq RESTART WITH 1");
            statement.execute("ALTER SEQUENCE rain_id_seq RESTART WITH 1");
            statement.execute("ALTER SEQUENCE snow_id_seq RESTART WITH 1");
            statement.execute("ALTER SEQUENCE blizzard_id_seq RESTART WITH 1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSnow(DBWorker dbWorker, int numRow) {
        try {
            PreparedStatement statement = dbWorker.getConnection().prepareStatement(
                    "DELETE FROM weather WHERE snow_id = (?)");
            statement.setInt(1, numRow);
            statement.executeUpdate();

            statement = dbWorker.getConnection().prepareStatement(
                    "DELETE FROM snow WHERE id = (?)");
            statement.setInt(1, numRow);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRain(DBWorker dbWorker, int numRow) {
        try {
            PreparedStatement statement = dbWorker.getConnection().prepareStatement(
                    "DELETE FROM weather WHERE rain_id = (?)");
            statement.setInt(1, numRow);
            statement.executeUpdate();
            statement = dbWorker.getConnection().prepareStatement(
                    "DELETE FROM rain WHERE id = (?)");
            statement.setInt(1, numRow);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBlizzard(DBWorker dbWorker, int numRow) {
        try {
            PreparedStatement statement = dbWorker.getConnection().prepareStatement(
                    "DELETE FROM weather WHERE snow_id = (?)");
            statement.setInt(1, numRow);
            statement.executeUpdate();
            statement = dbWorker.getConnection().prepareStatement(
                    "DELETE FROM snow WHERE blizzard_id = (?)");
            statement.setInt(1, numRow);
            statement.executeUpdate();
            statement = dbWorker.getConnection().prepareStatement(
                    "DELETE FROM blizzard WHERE id = (?)");
            statement.setInt(1, numRow);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int selectMenu(Scanner sc) {
        System.out.print(
                "1. Заполнить таблицу \"Снег\" \n" +
                        "2. Заполнить таблицу \"Дождь\" \n" +
                        "3. Заполнить таблицу \"Снегопад\" \n \n" +

                        "4. Вывести таблицу \"Снег\" \n" +
                        "5. Вывести таблицу \"Дождь\" \n" +
                        "6. Вывести таблицу \"Снегопад\" \n \n" +

                        "0. Удалить данные \n \n" +

                        "7. Выйти \n \n" +
                        "Ответ: "
        );
        return sc.nextInt();
    }

    public void insert(int table, Runner runner, Scanner sc, DBWorker dbWorker) {
        if (table == 1) {
            int numLastColumn = runner.insertSnow(sc, dbWorker, null);
            runner.insertWeather(sc, dbWorker, null, numLastColumn);
        } else
        if (table == 2) {
            int numLastColumn = runner.insertRain(sc, dbWorker);
            runner.insertWeather(sc, dbWorker, numLastColumn, null);
        }
        else {
            int numLast1 = runner.insertBlizzard(sc, dbWorker);
            int numLast2 = runner.insertSnow(sc, dbWorker, numLast1);
            runner.insertWeather(sc, dbWorker, null, numLast2);
        }
    }

    public void SPACE() {
        System.out.println();
        System.out.println("=================================");
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DBWorker dbWorker = new DBWorker();
        Runner runner = new Runner();
        int table;
        do {
            runner.SPACE();
            table = runner.selectMenu(sc);
            runner.SPACE();
            if (table == 1 || table == 2 || table == 3) {
                runner.insert(table, runner, sc, dbWorker);
            } else {
                switch (table) {
                    case 4: runner.selectSnow(dbWorker);
                            break;
                    case 5: runner.selectRain(dbWorker);
                            break;
                    case 6: runner.selectBlizzard(dbWorker);
                            break;
                    case 0: runner.delete(sc, dbWorker);
                            break;
                    case 7: System.out.println("Пока");
                            break;
                }
            }
        } while(table != 7);
    }
}