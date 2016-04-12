package ru.clinicPetWeb.store;

import ru.clinicPetWeb.models.Client;

import java.util.Collection;

public interface Storage {

	Collection<Client> valuesFound();

	Collection<Client> values();

	void add(final Client client);

	void edit(final Client client);

	void delete(final int id);

	/**
	 * Используется в тестах
	 */
	void deleteAll();

	Client get(final int id);

	void find(final String idClient, final String clientName, final String petName, final String petAge) ;

	int generateId();

	void close();
}
