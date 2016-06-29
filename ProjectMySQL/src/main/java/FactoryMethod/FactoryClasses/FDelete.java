package FactoryMethod.FactoryClasses;

import FactoryMethod.DBWorker;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FDelete {

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
}
