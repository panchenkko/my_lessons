package ru.clinicPetWeb.store;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import ru.clinicPetWeb.models.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Repository
public class HibernateStorage implements Storage {

    private final SessionFactory factory;

    private List<Client> found = new ArrayList<>();
    private List<Client> checkFound = new ArrayList<>();

    private final String HQL_SELECT_ALL = "FROM Client AS client INNER JOIN FETCH client.pet AS pet";

    public HibernateStorage() {
        this.factory = new Configuration().configure().buildSessionFactory();
    }

    public interface Command<T> {
        T process(Session session);
    }

    private <T> T transaction(final Command<T> command) {
        final Session session = this.factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            return command.process(session);
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
	public Collection<Client> valuesFound() {
		return this.found;
	}

    /**
     * Лямбда - (имя входного параметра -> {действия с этим параметром});
     */
	@Override
	public Collection<Client> values() {
        return transaction(session -> session.createQuery(HQL_SELECT_ALL + " " + "ORDER BY client.id").list());
	}

	@Override
	public void add(Client client) {
        transaction(session -> session.save(client));
	}

	@Override
	public void edit(Client client) {
        transaction(session -> {
            session.update(client);
            return null;
        });
	}

	@Override
	public void delete(int id) {
        transaction(session -> {
            session.delete(get(id));
            return null;
        });
        checkEmptyTable();
	}

    // Если таблица пустая скидываем счетчик первичных ключей
    public boolean checkEmptyTable() {
        return transaction(session -> {
            List list = session.createQuery("from Client").list();
            return list.size() == 0;
        });
    }

	@Override
	public void foldCounters() {
        this.found.clear();

        transaction(session -> {
            session.createQuery("delete from Client").executeUpdate();
            session.createQuery("delete from Pet").executeUpdate();
            //TODO Выяснить, как можно скинуть счетчик первичных ключей в hibernate
            return null;
        });
	}

	@Override
	public Client get(int id) {
        return transaction(session -> (Client) session.get(Client.class, id));
	}

	@Override
	public void find(String idClient, String clientName, String petName, String petAge) {
        this.found.clear();

        if (!idClient.equals("")) {
            findIdClient(Integer.valueOf(idClient));
        } else {
            findThreeParameters(clientName, petName, petAge);

            if (this.found.isEmpty()) {
                findTwoParameters(clientName, petName, petAge);

                if (found.isEmpty()) {
                    findOneParameters(clientName, petName, petAge);
                }
            }
        }
	}

    public void findIdClient(int idClient) {
        transaction(session -> {
            final Query query = session.createQuery(HQL_SELECT_ALL + " " + "WHERE client.id=:id");
            query.setInteger("id", idClient);
            this.checkFound = query.list();
            for (Client client : this.checkFound) {
                if (client.getId() == idClient)
                    this.found.add(client);
            }
            return null;
        });
    }

    public void findThreeParameters(String clientName, String petName, String petAge) {
        transaction(session -> {
            final Query query = session.createQuery(HQL_SELECT_ALL + " " +
                    "WHERE client.name=:clientName AND pet.name=:petName AND pet.age=:petAge");
            query.setString("clientName", clientName);
            query.setString("petName", petName);
            query.setString("petAge", petAge);
            this.checkFound = query.list();
            if (!this.checkFound.isEmpty()) {
                this.found.addAll(this.checkFound);
            }
            return null;
        });
    }

    public void findTwoParameters(String clientName, String petName, String petAge) {
        transaction(session -> {
            final Query query = session.createQuery(HQL_SELECT_ALL);
            this.checkFound = query.list();
            for (Client client : this.checkFound) {
                if (client.getName().equals(clientName) && client.getPet().getName().equals(petName)) {
                    this.found.add(client);
                } else if (client.getName().equals(clientName) && client.getPet().getAge().equals(petAge)
                        && !petAge.equals("")) {
                    this.found.add(client);
                } else if (client.getPet().getName().equals(petName) && client.getPet().getAge().equals(petAge)
                        && !petAge.equals("")) {
                    this.found.add(client);
                }
            }
            return null;
        });
    }

    public void findOneParameters(String clientName, String petName, String petAge) {
        transaction(session -> {
            final Query query = session.createQuery(HQL_SELECT_ALL);
            this.checkFound = query.list();
            for (Client client : this.checkFound) {
                     if (client.getName().equals(clientName)) this.found.add(client);
                else if (client.getPet().getName().equals(petName)) this.found.add(client);
                else if (client.getPet().getAge().equals(petAge) && !petAge.equals("")) this.found.add(client);
            }
            return null;
        });
    }

	@Override
	public int generateId() {
		return 0;
	}

	@Override
	public void close() {
        this.factory.close();
	}
}
