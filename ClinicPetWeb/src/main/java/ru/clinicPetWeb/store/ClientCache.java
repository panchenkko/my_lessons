package ru.clinicPetWeb.store;

import ru.clinicPetWeb.models.Client;

import java.util.Collection;

/**
 * Шаблон проектирования "Singleton".
 * Создание объекта этого класса, происходит только в этом классе
 * Создать можно только один объект этого класса
 */
public class ClientCache implements Storage {

	private static final ClientCache INSTANCE = new ClientCache();

    private Storage storage;

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    // Возвращаем объект класса
	public static ClientCache getInstance() {
		return INSTANCE;
	}

    @Override
    public Collection<Client> valuesFound() {
        return this.storage.valuesFound();
    }

    @Override
	public Collection<Client> values() {
		return this.storage.values();
	}

    @Override
	public void add(final Client client) {
		this.storage.add(client);
	}

    @Override
	public void edit(final Client client) {
		this.storage.edit(client);
	}

    @Override
	public void find(final String idClient, final String clientName, final String petName, final String petAge) {
		this.storage.find(idClient, clientName, petName, petAge);
	}

    @Override
	public int generateId() {
		return this.storage.generateId();
	}

    @Override
    public void delete(final int id) {
		this.storage.delete(id);
	}

    @Override
    public void foldCounters() {
        this.storage.foldCounters();
    }

    @Override
	public Client get(final int id) {
		return this.storage.get(id);
	}

    @Override
    public void close() {
        storage.close();
    }
}
