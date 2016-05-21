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

    @Override
	public Collection<Client> valuesFound() {
		return found;
	}

	@Override
	public Collection<Client> values() {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return session.createQuery("from Client").list();
        } finally {
            tx.commit();
            session.close();
        }
	}

	@Override
	public void add(Client client) {
        final Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.save(client);
            session.getTransaction().commit();
        } finally {
//            if (session != null && session.isOpen())
                session.close();
        }
	}

	@Override
	public void edit(Client client) {
        final Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.update(client);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
	}

	@Override
	public void delete(int id) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(new Client(id, null, null));
        } finally {
            tx.commit();
            session.close();
        }
	}

	@Override
	public void foldCounter() {

	}

	@Override
	public Client get(int id) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return (Client) session.get(Client.class, id);
        } finally {
            tx.commit();
            session.close();
        }
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
