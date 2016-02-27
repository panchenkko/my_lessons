package ru.clinicWeb_jsp_jstl.store;

import ru.clinicWeb_jsp_jstl.models.Client;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Шаблон проектирования "Singleton".
 * Создание объекта этого класса, происходит только в этом классе
 * СОздать можно только один объект этого класса
 */
public class ClientCache {

	private static final ClientCache INSTANCE = new ClientCache();

	// Карта для многопоточности
	private final ConcurrentHashMap<Integer, Client> clients = new ConcurrentHashMap<Integer, Client>();

	// Возвращаем объект класса
	public static ClientCache getInstance() {
		return INSTANCE;
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

	public Client findByName(final String name) {
		for (final Client client : clients.values()) {
			if (client.getName().equals(name) || client.getPet().getName().equals(name)) {
				return client;
			}
		}
		throw new IllegalStateException(String.format("Login %s not found", name));
	}

	public void delete(final int id) {
		this.clients.remove(id);
	}

	public Client get(final int id) {
		return this.clients.get(id);
	}
}
