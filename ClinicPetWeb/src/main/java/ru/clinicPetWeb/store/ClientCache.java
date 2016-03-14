package ru.clinicPetWeb.store;

import ru.clinicPetWeb.models.Client;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Шаблон проектирования "Singleton".
 * Создание объекта этого класса, происходит только в этом классе
 * Создать можно только один объект этого класса
 */
public class ClientCache {

	private final AtomicInteger ids = new AtomicInteger();

	private static final ClientCache INSTANCE = new ClientCache();

	// Карта для многопоточности
	private final ConcurrentHashMap<Integer, Client> clients = new ConcurrentHashMap<Integer, Client>();

	// Возвращаем объект класса
	public static ClientCache getInstance() {
		return INSTANCE;
	}

	public int size() {
		return this.clients.size();
	}

	public Collection<Client> values() {
		return this.clients.values();
	}

	public void add(final Client client) {
		this.clients.put(client.getId(), client);
	}

	public void edit(final Client client) {
		this.clients.replace(client.getId(), client);
	}

	public Client findByLogin(final String searchName) {
		for (final Client client : clients.values()) {
			if (client.getName().equals(searchName)) {
				return client;
			}
		}
		throw new IllegalStateException(String.format("Login %s not found", searchName));
	}

	public int generateId() {
		return this.ids.incrementAndGet();
	}

	public void delete(final int id) {
		this.clients.remove(id);
	}

	public Client get(final int id) {
		return this.clients.get(id);
	}
}
