package ru.clinicPetWeb.store;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.clinicPetWeb.models.Client;
import ru.clinicPetWeb.models.Pet;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class MemoryStorageTest extends Mockito {

    private Storage storage;

    @Before
    public void setUp() throws Exception {
        storage = new MemoryStorage();
    }

    /**
     * Генерируем нового клиента и уникальные данные для него
     * @return и возвращаем нового клиента
     */
    public Client clientAdd() {
        int id = storage.generateId();
        return new Client(id, "clientName" + id,
               new Pet(id, "petType"  + id, "petName"  + id, "petSex" + id, "petAge" + id));
    }

    @Test
    public void testValues() throws Exception {
        // Проверяем, что метод возвращает коллекцию с нужным размером
        Client client1 = clientAdd();
        Client client2 = clientAdd();

        storage.add(client1);
        storage.add(client2);

        assertThat(storage.values(), hasItems(client1, client2));
    }

    @Test
    public void testAdd() throws Exception {
        // Проверяем, что при добавлении клиента, он и правда добавляется в список
        storage.add(clientAdd());

        assertThat(storage.values().size(), is(1));
    }

    @Test
    public void testEdit() throws Exception {
        // Проверяем, что при редактировании данных клиента, изменяются нужные данные и ничего не происходит лишнего
        Client oldClient = clientAdd(); // Клиент с данными : id=1, name=clientName1, petId=2, petName=petName1 и т. д.
        Client newClient = clientAdd(); // Клиент с данными : id=2, name=clientName2, petId=2, petName=petName2 и т. д.
        // Ниже изменяем id нового клиента на id старого, таким образом у клиента новые данные, а id тот же
        newClient.setId(1);
        newClient.getPet().setId(1);

        storage.add(oldClient);

        assertThat(storage.values(), hasItem(oldClient));

        storage.edit(newClient);

        assertThat(storage.values(), hasItem(newClient));
        assertThat(storage.values(), not(hasItem(oldClient)));
    }

    @Test
    public void testDelete() throws Exception {
        // Проверяем, что при удалении клиента, он удаляется, а другие клиенты остаются
        Client client1 = clientAdd();
        Client client2 = clientAdd();

        storage.add(client1);
        storage.add(client2);

        storage.delete(1);

        assertThat(storage.values().size(), is(1));
        assertThat(storage.values(), not(hasItem(client1)));
    }

    @Test
    public void testDeleteAll() throws Exception {
        // Проверяем, что при очистки списка, он будет пуст
        Client client1 = clientAdd();
        Client client2 = clientAdd();

        storage.add(client1);
        storage.add(client2);

        assertThat(storage.values().size(), is(2));

        storage.foldCounters();

        assertThat(storage.values().size(), is(0));
    }

    @Test
    public void testGet() throws Exception {
        // Проверяем, что при получении определенного клиента из списка мы получаем нужного клиента
        Client client = clientAdd();

        storage.add(client);

        assertEquals(storage.get(1), client);
    }

    @Test
    public void testGenerateId() throws Exception {
        // Проверяем, что генератор id для клиента работает исправно
        assertThat(storage.generateId(), is(1));
        assertThat(storage.generateId(), is(2));
        assertThat(storage.generateId(), is(3));
    }

    @Test
    public void testClose() throws Exception {
        storage.close();
    }
}