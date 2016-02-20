package ru.clinicWebLesson.clinicPetWeb.store;

import ru.clinicLesson.clinic.Client;
import ru.clinicWebLesson.clinicPetWeb.MemoryStorage;

import java.util.Collection;

public class UserCache implements Storage {

	private static final UserCache INSTANCE = new UserCache();

	private final Storage storage = new MemoryStorage();

	public static UserCache getInstance() {
		return INSTANCE;
	}

	@Override
	public Collection<Client> values() {
		return INSTANCE.values();
	}

	@Override
	public int add(final Client client) {
		return this.storage.add(client);
	}

	@Override
	public void edit(final Client client) {
		this.storage.edit(client);
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
	public Client findByLogin(final String login) {
		return this.storage.findByLogin(login);
	}

	@Override
	public int generateId() {
		return this.storage.generateId();
	}

	@Override
	public void close() {
		this.storage.close();
	}
}
