package ru.clinicPetWeb;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import ru.clinicPetWeb.models.Client;
import ru.clinicPetWeb.models.Pet;
import ru.clinicPetWeb.store.HibernateStorage;

import java.util.List;

public class Check {

    private final SessionFactory factory;
    private List<Client> clients;

    public Check() {
        this.factory = new Configuration().configure().buildSessionFactory();
    }

    public void values() {
        final Session session = this.factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Client.class, "client");
            criteria.createCriteria("client.pet", "pet");
            this.clients = criteria.list();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            tx.commit();
            session.close();
        }
        for (Client client : this.clients) {
            System.out.println(client);
        }
    }

    public static void main(String[] args) {
        Client client = new Client(1, "Vlad", new Pet("cat", "Bars", "male", "12"));
        HibernateStorage storage = new HibernateStorage();

        Check check = new Check();
        check.values();
    }
}
