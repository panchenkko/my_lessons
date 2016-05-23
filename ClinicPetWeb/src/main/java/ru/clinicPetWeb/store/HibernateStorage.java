package ru.clinicPetWeb.store;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.clinicPetWeb.models.Client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class HibernateStorage implements Storage {

    private final SessionFactory factory;

    final List<Client> found = new ArrayList<>();

    public HibernateStorage() {
        this.factory = new Configuration().configure().buildSessionFactory();
    }

    public interface Command<T> {
        T process(Session session);
    }

    private <T> T transaction(final Command<T> command) {
        final Session session = factory.openSession();
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
		return found;
	}

	@Override
	public Collection<Client> values() {
        // session - это входной параметр, а дальше после стрелки говорим, что хотим сделать
        return transaction(session -> session.createQuery("from Client").list());
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
	}

	@Override
	public void foldCounter() {
	}

	@Override
	public Client get(int id) {
        return transaction(session -> (Client) session.get(Client.class, id));
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

    public void foundAdd(Query query) {
//        found.add(new Client(rs.getInt("uid"), rs.getString("name"),
//                        new Pet(rs.getString("type"), rs.getString("petName"),
//                                rs.getString("sex"), rs.getString("age")))
//        );
    }

    public void findIdClient(int idClient) {
    }

    public boolean findThreeParameters(String clientName, String petName, String petAge) {
        return false;
    }

    public boolean findTwoParameters(String clientName, String petName, String petAge) {
        return false;
    }

    public void findOneParameters(String clientName, String petName, String petAge) {
    }

    public boolean findClientName(int id, String clientName) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            final Query query = session.createQuery("from Client as client where client.name=:name");
            query.setString("name", clientName);
//            return (Client) query.iterate().next();
        } finally {
            tx.commit();
            session.close();
        }
        return false;
    }

    public boolean findPetName(int id, String petName) {
        return false;
    }

    public boolean findAge(int id, String age) {
        return false;
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
