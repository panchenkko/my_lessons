package ru.clinicWebLesson.clinicPetWeb.store;

import ru.clinicLesson.clinic.Client;
import ru.clinicWebLesson.clinicPetWeb.store.Storage;
import ru.parsentev.models.User;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryStorage implements Storage {

	private final AtomicInteger ids = new AtomicInteger();

	private final ConcurrentHashMap<Integer, Client> client = new ConcurrentHashMap<Integer, Client>();

	@Override
	public Collection<Client> values() {
		return this.client.values();
	}

	@Override
	public int add(final Client client) {
		this.client.put(client.getId(), client);
		return client.getId();
	}

	@Override
	public void edit(final Client client) {
		this.client.replace(client.getId(), client);
	}

	@Override
	public void delete(final int id) {
		this.client.remove(id);
	}

	@Override
	public Client get(final int id) {
		return this.client.get(id);
	}

	@Override
	public Client findByLogin(final String login) {
		for (final Client user : client.values()) {
			if (client.getLogin().equals(login)) {
				return user;
			}
		}
		throw new IllegalStateException(String.format("Login %s not found", login));
	}

	@Override
	public int generateId() {
		return this.ids.incrementAndGet();
	}

	@Override
	public void close() {
	}
}
