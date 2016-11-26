package ru.clinicPetWeb.store;

import org.springframework.stereotype.Repository;
import ru.clinicPetWeb.models.Client;
import ru.clinicPetWeb.models.Pet;
import ru.clinicPetWeb.service.Settings;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class JdbcStorage implements Storage {

	private Connection connection;

	private final List<Client> clients = new ArrayList<>();
	private final List<Client> found = new ArrayList<>();

    private final String SQL_SELECT_ALL = "SELECT * FROM client INNER JOIN pet ON client.pet_id = pet.uid";

	public JdbcStorage() {
		final Settings settings = Settings.getInstance();
		try {
            Class.forName(settings.value("jdbc.driver_class"));
			this.connection = DriverManager.getConnection(
                    settings.value("jdbc.url"),
                    settings.value("jdbc.username"),
                    settings.value("jdbc.password")
            );

            if (connection.isClosed())
                System.out.println("Соединение с БД закрыто!");
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		} catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Client> valuesFound() {
        return found;
    }

    @Override
	public Collection<Client> values() {
        clients.clear();

		try (final Statement statement = this.connection.createStatement();
		     final ResultSet rs = statement.executeQuery(SQL_SELECT_ALL + " " + "ORDER BY client.uid")) {
			while (rs.next()) {
				clients.add(new Client(rs.getInt("uid"), rs.getString("name"),
							new Pet(rs.getInt("pet_id"), rs.getString("type"),
                                    rs.getString("petName"), rs.getString("sex"), rs.getString("age")))
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clients;
	}

    @Override
	public void add(Client client) {
        try (final PreparedStatement statement = this.connection.prepareStatement
                ("insert into pet (type, petName, sex, age) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, client.getPet().getType());
            statement.setString(2, client.getPet().getName());
            statement.setString(3, client.getPet().getSex());
            statement.setString(4, client.getPet().getAge());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    client.getPet().setId(generatedKeys.getInt(1));
                }
            }

            addClient(client);
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public void addClient(Client client) {
        try (final PreparedStatement statement =
                     this.connection.prepareStatement("insert into client (name, pet_id) values (?,?)")) {
            statement.setString(1, client.getName());
            statement.setInt(2, client.getPet().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void edit(Client client) {
        PreparedStatement editPet = null;
        PreparedStatement editClient = null;

        String stringEditPet = "UPDATE pet SET type = (?), petName = (?), sex = (?), age = (?) WHERE uid = (?)";
        String stringEditClient = "UPDATE client SET name = (?) WHERE uid = (?)";

        try {
            this.connection.setAutoCommit(false);

            editPet = this.connection.prepareStatement(stringEditPet);
            editClient = this.connection.prepareStatement(stringEditClient);

            editPet.setString(1, client.getPet().getType());
            editPet.setString(2, client.getPet().getName());
            editPet.setString(3, client.getPet().getSex());
            editPet.setString(4, client.getPet().getAge());
            editPet.setInt(5, client.getPet().getId());
            editPet.executeUpdate();

            editClient.setString(1, client.getName());
            editClient.setInt(2, client.getPet().getId());
            editClient.executeUpdate();

            this.connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (this.connection != null)
                    this.connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (editPet != null) editPet.close();
                if (editClient != null) editClient.close();
                this.connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}

	@Override
	public void delete(int id) {
        int sum = 0;

        try (final Statement statement = this.connection.createStatement();
             final ResultSet rs = statement.executeQuery(SQL_SELECT_ALL)) {
            while (rs.next()) {
                if (rs.getInt("uid") == id) {
                    sum = rs.getInt("pet_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (final PreparedStatement statement = this.connection.prepareStatement
                ("DELETE FROM client WHERE uid = (?)")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        deletePet(sum);
	}

    public void deletePet(int id) {
        try (final PreparedStatement statement = this.connection.prepareStatement
                ("DELETE FROM pet WHERE uid = (?)")) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) throw new SQLException("Ошибка! Удаление клиента не удалось!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        checkEmptyTable();
    }

    // Если таблица пустая скидываем счетчик первичных ключей
    public void checkEmptyTable() {
        try (final Statement statement = this.connection.createStatement();
             final ResultSet rs = statement.executeQuery("select count(*) as count from client")) {
            rs.next();
            int count = rs.getInt("count");
            if (count == 0) foldCounters();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Скидываем счётчик
    @Override
    public void foldCounters() {
        found.clear();

        try (final Statement statement = this.connection.createStatement()) {
            statement.addBatch("DELETE FROM client");
            statement.addBatch("DELETE FROM pet");
            statement.addBatch("ALTER SEQUENCE client_uid_seq1 RESTART WITH 1");
            statement.addBatch("ALTER SEQUENCE pet_uid_seq RESTART WITH 1");
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public Client get(int id) {
		try (final PreparedStatement statement =
                     this.connection.prepareStatement(SQL_SELECT_ALL + " " + "where client.uid = (?)")) {
			statement.setInt(1, id);
			try (final ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					return new Client(rs.getInt("uid"), rs.getString("name"),
                               new Pet(rs.getInt("pet_id"), rs.getString("type"),
                                       rs.getString("petName"), rs.getString("sex"), rs.getString("age")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		throw new IllegalStateException(String.format("Пользователь под номером %s не найден!", id));
	}

    @Override
    public void find(String idClient, String clientName, String petName, String petAge) {
        this.found.clear();

        if (!idClient.equals("")) {
            findIdClient(Integer.valueOf(idClient));
        } else {
            findThreeParameters(clientName, petName, petAge);

            if (found.isEmpty()) {
                findTwoParameters(clientName, petName, petAge);

                if (found.isEmpty()) {
                    findOneParameters(clientName, petName, petAge);
                }
            }
        }
    }

    public void foundAdd(ResultSet rs) throws SQLException {
        found.add(new Client(rs.getInt("uid"), rs.getString("name"),
                        new Pet(rs.getInt("pet_id"), rs.getString("type"),
                                rs.getString("petName"), rs.getString("sex"), rs.getString("age")))
        );
    }

    public void findIdClient(int idClient) {
        try (final Statement statement = this.connection.createStatement();
             final ResultSet rs = statement.executeQuery
                     ("select * from client right join pet on client.pet_id = pet.uid")) {
            while (rs.next()) {
                if (rs.getInt("uid") == idClient) {
                    foundAdd(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void findThreeParameters(String clientName, String petName, String petAge) {
        try (final Statement statement = this.connection.createStatement();
             final ResultSet rs = statement.executeQuery(SQL_SELECT_ALL)) {
            while (rs.next()) {
                if (findClientName(rs.getInt("uid"), clientName) && findPetName(rs.getInt("uid"), petName) &&
                        findAge(rs.getInt("uid"), petAge) && !petAge.equals("")) {
                    foundAdd(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void findTwoParameters(String clientName, String petName, String petAge) {
        try (final Statement statement = this.connection.createStatement();
             final ResultSet rs = statement.executeQuery(SQL_SELECT_ALL)) {
            while (rs.next()) {
                if (findClientName(rs.getInt("uid"), clientName) && findPetName(rs.getInt("uid"), petName)) {
                    foundAdd(rs);
                } else if (findClientName(rs.getInt("uid"), clientName) && findAge(rs.getInt("uid"), petAge)
                        && !petAge.equals("")) {
                    foundAdd(rs);
                } else if (findPetName(rs.getInt("uid"), petName) && findAge(rs.getInt("uid"), petAge)
                        && !petAge.equals("")) {
                    foundAdd(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void findOneParameters(String clientName, String petName, String petAge) {
        try (final Statement statement = this.connection.createStatement();
             final ResultSet rs = statement.executeQuery(SQL_SELECT_ALL)) {
            while (rs.next()) {
                if (findClientName(rs.getInt("uid"), clientName)) {
                    foundAdd(rs);
                } else if (findPetName(rs.getInt("uid"), petName)) {
                    foundAdd(rs);
                } else if (findAge(rs.getInt("uid"), petAge) && !petAge.equals("")) {
                    foundAdd(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean findClientName(int id, String clientName) {
        try (final PreparedStatement statement = this.connection.prepareStatement
                (SQL_SELECT_ALL + " " + "where client.uid = (?)")) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    if (clientName.equals(rs.getString("name")))
                        return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean findPetName(int id, String petName) {
        try (final PreparedStatement statement = this.connection.prepareStatement
                (SQL_SELECT_ALL + " " + "where client.uid = (?)")) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    if (petName.equals(rs.getString("petName")))
                        return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean findAge(int id, String age) {
        try (final PreparedStatement statement = this.connection.prepareStatement
                (SQL_SELECT_ALL + " " + "where client.uid = (?)")) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    if (age.equals(rs.getString("age")))
                        return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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

//    /**
//     * Интерфейс Команда, чтобы вынести повторяющийся код:
//     *
//     * try (final PreparedStatement statement = this.connection.prepareStatement(SqlQuery)) {
//     *      ----Отличающийся код----
//     * } catch (SQLException e) {
//     *     e.printStackTrace();
//     * }
//     *
//     */
//    interface Command {
//        void execute(PreparedStatement statement) throws SQLException;
//    }
//
//    /**
//     * Выполнить PreparedStatement.
//     * Использует интерфейс Команда для разделения повторяющейся и отличающейся части кода
//     * @param SqlQuery SQL-запрос
//     * @param command Реализация интерфейса Команда
//     */
//    private void executePreparedStatement(String SqlQuery, Command command) {
//        try (final PreparedStatement statement = this.connection.prepareStatement(SqlQuery)) {
//            command.execute(statement);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
