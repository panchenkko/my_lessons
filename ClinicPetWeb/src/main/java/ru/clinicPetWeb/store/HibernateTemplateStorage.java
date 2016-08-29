package ru.clinicPetWeb.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.clinicPetWeb.models.Client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class HibernateTemplateStorage implements Template {

    private final HibernateTemplate template;

    private List<Client> found = new ArrayList<>();
    private List<Client> checkFound = new ArrayList<>();

    private final String HQL_SELECT_ALL = "FROM Client AS client INNER JOIN FETCH client.pet AS pet";

    @Autowired
    public HibernateTemplateStorage(final HibernateTemplate template) {
        this.template = template;
    }

    @Override
    public Collection<Client> values() {
        return (List<Client>) this.template.find(HQL_SELECT_ALL + " " + "ORDER BY client.id");
    }

    @Override
    public Collection<Client> valuesFound() {
        return this.found;
    }

    @Transactional
    @Override
    public void add(Client client) {
        this.template.save(client);
    }

    @Transactional
    @Override
    public void edit(Client client) {
        this.template.update(client);
    }

    @Transactional
    @Override
    public void delete(int id) {
        this.template.delete(get(id));
    }

    @Override
    public void foldCounters() {
        this.found.clear();
        this.template.clear();
    }

    @Override
    public Client get(int id) {
        return this.template.get(Client.class, id);
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

                if (this.found.isEmpty()) {
                    findOneParameters(clientName, petName, petAge);
                }
            }
        }
    }

    public void findIdClient(int idClient) {
        this.found = (List<Client>) this.template.findByNamedParam(HQL_SELECT_ALL + " " + "WHERE client.id=:id", "id", idClient);
    }

    public void findThreeParameters(String clientName, String petName, String petAge) {
        // Массив имен параметров
        String[] paramNames = {"clientName", "petName", "petAge"};
        // Массив значений (в том же порядке)
        Object[] values = {clientName, petName, petAge};

        this.found = (List<Client>) this.template.findByNamedParam(HQL_SELECT_ALL + " " +
                        "WHERE client.name=:clientName AND pet.name=:petName AND pet.age=:petAge", paramNames, values);
    }

    public void findTwoParameters(String clientName, String petName, String petAge) {
        this.checkFound = (List<Client>) this.template.find(HQL_SELECT_ALL);

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
    }

    public void findOneParameters(String clientName, String petName, String petAge) {
        this.checkFound = (List<Client>) this.template.find(HQL_SELECT_ALL);

        for (Client client : this.checkFound) {
            if (client.getName().equals(clientName)) this.found.add(client);
            else if (client.getPet().getName().equals(petName)) this.found.add(client);
            else if (client.getPet().getAge().equals(petAge) && !petAge.equals("")) this.found.add(client);
        }
    }

    @Override
    public int generateId() {
        return 0;
    }

    @Override
    public void close() {
    }
}
