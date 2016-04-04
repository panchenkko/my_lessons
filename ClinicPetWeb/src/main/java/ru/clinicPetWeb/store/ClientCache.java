package ru.clinicPetWeb.store;

import ru.clinicPetWeb.models.Client;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Шаблон проектирования "Singleton".
 * Создание объекта этого класса, происходит только в этом классе
 * Создать можно только один объект этого класса
 */
public class ClientCache implements Storage {

	private static final ClientCache INSTANCE = new ClientCache();

    private final Storage storage = new MemoryStorage();


    @Override
	public Collection<Client> valuesFound() {
		return this.storage.valuesFound();
	}


	// Возвращаем объект класса
	public static ClientCache getInstance() {
		return INSTANCE;
	}

    @Override
	public Collection<Client> values() {
		return this.storage.values();
	}

    @Override
    public int size() {
        return this.storage.size();
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
	public void find(String clientName, String petName, String petAge) {
		this.storage.find(clientName, petName, petAge);
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
	public Client get(final int id) {
		return this.storage.get(id);
	}

    @Override
    public void close() {

    }
}
