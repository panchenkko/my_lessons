package ru.clinicPetWeb.store;

import ru.clinicPetWeb.models.Client;
import ru.clinicPetWeb.models.Pet;
import ru.clinicPetWeb.service.Settings;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

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

    @Override
    public Collection<Client> valuesFound() {
        return found;
    }

    @Override
	public Collection<Client> values() {
        clients.clear();
		try (final Statement statement = this.connection.createStatement();
		     final ResultSet rs = statement.executeQuery
                     ("select * from client right join pet on client.uid = pet.client_id")) {
			while (rs.next()) {
				clients.add(new Client(rs.getInt("uid"), rs.getString("name"),
							new Pet(rs.getString("type"), rs.getString("petName"),
                                    rs.getString("sex"), rs.getString("age")))
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clients;
	}

    @Override
	public void add(Client client) {
        int num = 0;
        try (final PreparedStatement statement = this.connection.prepareStatement
                ("insert into client (name) values (?)", Statement.RETURN_GENERATED_KEYS)) {
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
				("insert into pet (client_id, type, petName, sex, age) values (?,?,?,?,?)")) {
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
                ("UPDATE client SET name = (?) WHERE uid = (?)")) {
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
                ("UPDATE pet SET type = (?), petName = (?), sex = (?), age = (?) WHERE client_id = (?)")) {
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
                ("DELETE FROM pet WHERE client_id = (?)")) {
            statement.setInt(1, id);
            statement.executeUpdate();
            deleteClient(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

    public void deleteClient(int id) {
        try (final PreparedStatement statement = this.connection.prepareStatement
                ("DELETE FROM client WHERE uid = (?)")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        try (final Statement statement = this.connection.createStatement()) {
            statement.addBatch("DELETE FROM pet;");
            statement.addBatch("DELETE FROM client");
            statement.addBatch("ALTER SEQUENCE pet_uid_seq RESTART WITH 1");
            statement.addBatch("ALTER SEQUENCE client_uid_seq RESTART WITH 1");
            statement.executeBatch();
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
                           new Pet(rs.getString("type"), rs.getString("petName"),
                                   rs.getString("sex"), rs.getString("age")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new IllegalStateException(String.format("User %s does not exists", id));
	}

    @Override
    public void find(String idClient, String clientName, String petName, String petAge) {
        this.found.clear();

        if (!Objects.equals(idClient, ""))
            findIdClient(Integer.valueOf(idClient));
        else
        if (!findThreeParameters(clientName, petName, petAge))
            if (!findTwoParameters(clientName, petName, petAge))
                findOneParameters(clientName, petName, petAge);
    }

    public void foundAdd(ResultSet rs) throws SQLException {
        found.add(new Client(rs.getInt("uid"), rs.getString("name"),
                        new Pet(rs.getString("type"), rs.getString("petName"),
                                rs.getString("sex"), rs.getString("age")))
        );
    }

    public void findIdClient(int idClient) {
        try (final Statement statement = this.connection.createStatement();
             final ResultSet rs = statement.executeQuery
                     ("select * from client right join pet on client.uid = pet.client_id")) {
            while (rs.next()) {
                if (rs.getInt("uid") == idClient) {
                    foundAdd(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean findThreeParameters(String clientName, String petName, String petAge) {
        boolean check = false;
        try (final Statement statement = this.connection.createStatement();
             final ResultSet rs = statement.executeQuery
                     ("select * from client right join pet on client.uid = pet.client_id")) {
            while (rs.next()) {
                if (findClientName(rs.getInt("uid"), clientName) && findPetName(rs.getInt("uid"), petName) &&
                        findAge(rs.getInt("uid"), petAge) && !Objects.equals(petAge, "")) {
                    foundAdd(rs);
                    check = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean findTwoParameters(String clientName, String petName, String petAge) {
        boolean check = false;
        try (final Statement statement = this.connection.createStatement();
             final ResultSet rs = statement.executeQuery
                     ("select * from client right join pet on client.uid = pet.client_id")) {
            while (rs.next()) {
                if (findClientName(rs.getInt("uid"), clientName) && findPetName(rs.getInt("uid"), petName)) {
                    foundAdd(rs);
                    check = true;
                } else
                if (findClientName(rs.getInt("uid"), clientName) && findAge(rs.getInt("uid"), petAge)
                        && !Objects.equals(petAge, "")) {
                    foundAdd(rs);
                    check = true;
                } else
                if (findPetName(rs.getInt("uid"), petName) && findAge(rs.getInt("uid"), petAge)
                        && !Objects.equals(petAge, "")) {
                    foundAdd(rs);
                    check = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public void findOneParameters(String clientName, String petName, String petAge) {
        try (final Statement statement = this.connection.createStatement();
             final ResultSet rs = statement.executeQuery
                     ("select * from client right join pet on client.uid = pet.client_id")) {
            while (rs.next()) {
                if (findClientName(rs.getInt("uid"), clientName)) {
                    foundAdd(rs);
                } else
                if (findPetName(rs.getInt("uid"), petName)) {
                    foundAdd(rs);
                } else
                if (findAge(rs.getInt("uid"), petAge) && !Objects.equals(petAge, "")) {
                    foundAdd(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean findClientName(int id, String clientName) {
        try (final PreparedStatement statement = this.connection.prepareStatement
                ("select * from client where uid = (?)")) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    if (Objects.equals(rs.getString("name"), clientName))
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
                ("select * from pet where client_id = (?)")) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    if (Objects.equals(rs.getString("petName"), petName))
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
                ("select * from pet where client_id = (?)")) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    if (Objects.equals(rs.getString("age"), age))
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
