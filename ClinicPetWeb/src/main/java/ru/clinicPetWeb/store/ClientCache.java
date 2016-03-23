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
public class ClientCache {

	private final AtomicInteger ids = new AtomicInteger();

	private static final ClientCache INSTANCE = new ClientCache();

	// Карта для многопоточности
	private final ConcurrentHashMap<Integer, Client> clients = new ConcurrentHashMap<>();


	private final AtomicInteger idFound = new AtomicInteger();

	private final ConcurrentHashMap<Integer, Client> found = new ConcurrentHashMap<>();

	public Collection<Client> valuesFound() {
		return this.found.values();
	}


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

	public void find(String clientName, String petName, String petAge) {
		for (final Client client : this.clients.values()) {
			if (client.getName().equals(clientName) &&
				client.getPet().getName().equals(petName) &&
				client.getPet().getAge().equals(petAge) ||

				client.getName().equals(clientName) &&
				client.getPet().getName().equals(petName) ||

				client.getName().equals(clientName) &&
				client.getPet().getAge().equals(petAge) ||

				client.getPet().getName().equals(petName) &&
				client.getPet().getAge().equals(petAge) ||

				client.getName().equals(clientName) ||
				client.getPet().getName().equals(petName)) {

				this.found.put(this.idFound.incrementAndGet(), client);
			}
		}
		throw new IllegalStateException("Ничего не найдено!");
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
