package ru.clinicPetWeb.store;

import org.springframework.stereotype.Repository;
import ru.clinicPetWeb.models.Client;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MemoryStorage implements Storage {

    private final AtomicInteger ids = new AtomicInteger();

    private final ConcurrentHashMap<Integer, Client> clients = new ConcurrentHashMap<>();

    private final AtomicInteger idFound = new AtomicInteger();

    private final ConcurrentHashMap<Integer, Client> found = new ConcurrentHashMap<>();

    @Override
    public Collection<Client> values() {
        return this.clients.values();
    }

    @Override
    public Collection<Client> valuesFound() {
        return this.found.values();
    }

    @Override
    public void add(Client client) {
        this.clients.put(client.getId(), client);
    }

    @Override
    public void edit(Client client) {
        this.clients.replace(client.getId(), client);
    }

    @Override
    public void delete(int id) {
        this.clients.remove(id);
    }

    @Override
    public void foldCounters() {
        found.clear();
        clients.clear();
    }

    @Override
    public Client get(int id) {
        return this.clients.get(id);
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

    public void findIdClient(int idClient) {
        for (final Client client : this.clients.values()) {
            if (client.getId() == idClient) {
                this.found.put(this.idFound.incrementAndGet(), client);
            }
        }
    }

    public void findThreeParameters(String clientName, String petName, String petAge) {
        for (final Client client : this.clients.values()) {
            if (client.getName().equals(clientName) &&
                client.getPet().getName().equals(petName) &&
                client.getPet().getAge().equals(petAge) && !petAge.equals("")) {
                this.found.put(this.idFound.incrementAndGet(), client);
            }
        }
    }

    public void findTwoParameters(String clientName, String petName, String petAge) {
        for (final Client client : this.clients.values()) {
            if (client.getName().equals(clientName) && client.getPet().getName().equals(petName)) {
                this.found.put(this.idFound.incrementAndGet(), client);
            } else if (client.getName().equals(clientName) && client.getPet().getAge().equals(petAge)
                    && !petAge.equals("")) {
                this.found.put(this.idFound.incrementAndGet(), client);
            } else if (client.getPet().getName().equals(petName) && client.getPet().getAge().equals(petAge)
                    && !petAge.equals("")) {
                this.found.put(this.idFound.incrementAndGet(), client);
            }
        }
    }

    public void findOneParameters(String clientName, String petName, String petAge) {
        for (final Client client : this.clients.values()) {
            if (client.getName().equals(clientName)) {
                this.found.put(this.idFound.incrementAndGet(), client);
            } else if (client.getPet().getName().equals(petName)) {
                this.found.put(this.idFound.incrementAndGet(), client);
            } else if (client.getPet().getAge().equals(petAge) && !petAge.equals("")) {
                this.found.put(this.idFound.incrementAndGet(), client);
            }
        }
    }

    @Override
    public int generateId() {
        return this.ids.incrementAndGet();
    }

    @Override
    public void close() {
    }
}
