package ru.clinicPetWeb.store;

import ru.clinicPetWeb.models.Client;
import ru.clinicPetWeb.models.Pet;
import ru.clinicPetWeb.service.Settings;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JdbcStorage implements Storage {

	private final Connection connection;

	public JdbcStorage() {
		final Settings settings = Settings.getInstance();
		try {
			this.connection = DriverManager.getConnection(
                    settings.value("jdbc.url"),
                    settings.value("jdbc.username"),
                    settings.value("jdbc.password")
            );
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

    @Override
    public Collection<Client> valuesFound() {
        return null;
    }

    @Override
	public Collection<Client> values() {
		final List<Client> users = new ArrayList<>();
		try (final Statement statement = this.connection.createStatement();
		     final ResultSet rs = statement.executeQuery("select * from client")) {
			while (rs.next()) {
				users.add(new Client(rs.getInt("uid"), rs.getString("name"),
                          new Pet(rs.getString("type"), rs.getString("name"),
                                  rs.getString("sex"), rs.getString("age")))
                );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

    @Override
    public int size() {
        return 0;
    }

    @Override
	public void add(Client client) {
		try (final PreparedStatement statement = this.connection.prepareStatement
				("insert into client (name) values (?)", Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, client.getName());
			statement.executeUpdate();
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					generatedKeys.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new IllegalStateException("Could not create new user");
	}

	@Override
	public void edit(Client client) {

	}

	@Override
	public void delete(int id) {

	}

	@Override
	public Client get(int id) {
		try (final PreparedStatement statement = this.connection.prepareStatement("select * from client where uid=(?)")) {
			statement.setInt(1, id);
			try (final ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					return new Client(rs.getInt("uid"), rs.getString("name"), null);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new IllegalStateException(String.format("User %s does not exists", id));
	}

    @Override
    public void find(String clientName, String petName, String petAge) {

    }

	@Override
	public int generateId() {
		return 0;
	}

	@Override
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
