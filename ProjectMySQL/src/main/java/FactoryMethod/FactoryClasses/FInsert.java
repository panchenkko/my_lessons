package FactoryMethod.FactoryClasses;

import FactoryMethod.DBWorker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FInsert {

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
}
