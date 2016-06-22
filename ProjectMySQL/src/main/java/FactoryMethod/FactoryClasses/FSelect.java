package FactoryMethod.FactoryClasses;

import FactoryMethod.DBWorker;
import Weather.Blizzard;
import Weather.Rain;
import Weather.Snow;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FSelect {

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
}
