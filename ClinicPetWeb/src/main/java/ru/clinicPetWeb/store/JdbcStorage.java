package ru.clinicPetWeb.store;

import ru.clinicPetWeb.models.Client;
import ru.clinicPetWeb.models.Pet;
import ru.clinicPetWeb.service.Settings;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JdbcStorage implements Storage {

	private Connection connection;

	final List<Client> clients = new ArrayList<>();
	final List<Client> found = new ArrayList<>();

	public JdbcStorage() {
		final Settings settings = Settings.getInstance();
		try {
            Class.forName(settings.value("jdbc.driver_class"));
			this.connection = DriverManager.getConnection(
                    settings.value("jdbc.url"),
                    settings.value("jdbc.username"),
                    settings.value("jdbc.password")
            );

			if (!connection.isClosed())
				System.out.println("Соединение с БД установлено!");
            if (connection.isClosed())
                System.out.println("Соединение с БД закрыто!");
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		} catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Интерфейс Команда, чтобы вынести повторяющийся код:
     *
     * try (final PreparedStatement statement = this.connection.prepareStatement(SqlQuery)) {
     *      ----Отличающийся код----
     * } catch (SQLException e) {
     *     e.printStackTrace();
     * }
     *
     */
    interface Command {
        void execute(PreparedStatement statement) throws SQLException;
    }

    /**
     * Выполнить PreparedStatement.
     * Использует интерфейс Команда для разделения повторяющейся и отличающейся части кода
     * @param SqlQuery SQL-запрос
     * @param command Реализация интерфейса Команда
     */
    private void executePreparedStatement(String SqlQuery, Command command) {
        try (final PreparedStatement statement = this.connection.prepareStatement(SqlQuery)) {
            command.execute(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Client> valuesFound() {
        try (final Statement statement = this.connection.createStatement();
             final ResultSet rs = statement.executeQuery("select * from client")) {
            while (rs.next()) {
                found.add(new Client(rs.getInt("uid"), rs.getString("name"),
                          new Pet(rs.getString("type"), rs.getString("name"),
                                  rs.getString("sex"), rs.getString("age")))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }

    @Override
	public Collection<Client> values() {
		try (final Statement statement = this.connection.createStatement();
		     final ResultSet rs = statement.executeQuery
                     ("select * from client right join pet on client.uid = pet.client_id")) {
			while (rs.next()) {
				clients.add(new Client(rs.getInt("uid"), rs.getString("name"),
							new Pet(rs.getString("type"), rs.getString("name"),
                                    rs.getString("sex"), rs.getString("age")))
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clients;
	}

    @Override
    public int size() {
        return clients.size();
    }

    @Override
	public void add(Client client) {
        int num = 0;
        try (final PreparedStatement statement = this.connection.prepareStatement
				("insert into client (name) values (?)",
                        Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, client.getName());
			statement.executeUpdate();
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					num = generatedKeys.getInt(1);
				}
			}
            addPet(client, num);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addPet(Client client, int num) {
		try (final PreparedStatement statement = this.connection.prepareStatement
				("insert into pet (client_id, type, name, sex, age) values (?,?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS)) {
			statement.setInt(1, num);
			statement.setString(2, client.getPet().getPetType());
			statement.setString(3, client.getPet().getName());
			statement.setString(4, client.getPet().getPetSex());
			statement.setString(5, client.getPet().getAge());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void edit(Client client) {
        try (final PreparedStatement statement = this.connection.prepareStatement
                ("UPDATE client SET name = (?) where uid = (?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, client.getName());
            statement.setInt(2, client.getId());
            statement.executeUpdate();
            editPet(client);
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

    public void editPet(Client client) {
        try (final PreparedStatement statement = this.connection.prepareStatement
                ("UPDATE pet SET type = (?), name = (?), sex = (?), age = (?) WHERE client_id = (?)")) {
            statement.setString(1, client.getPet().getPetType());
            statement.setString(2, client.getPet().getName());
            statement.setString(3, client.getPet().getPetSex());
            statement.setString(4, client.getPet().getAge());
            statement.setInt(5, client.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void delete(int id) {
        try (final PreparedStatement statement = this.connection.prepareStatement
                ("DELETE FROM pet WHERE client_id = (?)",
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            deleteClient(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

    public void deleteClient(int id) {
        try (final PreparedStatement statement = this.connection.prepareStatement
                ("DELETE FROM client WHERE uid = (?)",
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public Client get(int id) {
		try (final PreparedStatement statement = this.connection.prepareStatement
                ("select * from client join pet on client.uid = pet.client_id where pet.client_id=(?)")) {
			statement.setInt(1, id);
			try (final ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					return new Client(rs.getInt("uid"), rs.getString("name"),
                           new Pet(rs.getString("type"), rs.getString("name"),
                                   rs.getString("sex"), rs.getString("age")));
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
