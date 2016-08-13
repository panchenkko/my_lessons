package ru.clinicPetWeb.store;

import ru.clinicPetWeb.models.Client;

import java.util.Collection;

public interface Storage {

	Collection<Client> values();

	Collection<Client> valuesFound();

	void add(final Client client);

	void edit(final Client client);

	void delete(final int id);

	void foldCounters();

	Client get(final int id);

	void find(final String idClient, final String clientName, final String petName, final String petAge);

	int generateId();

	void close();
}
