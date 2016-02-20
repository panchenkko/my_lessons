package ru.clinicWebLesson.clinicPetWeb.store;

import ru.clinicLesson.clinic.Client;

import java.util.Collection;

public interface Storage {

	public Collection<Client> values();

	public int add(final Client client);

	public void edit(final Client client);

	public void delete(final int id);

	public Client get(final int id);

	public Client findByLogin(final String login) ;

	public int generateId();

	public void close();
}
