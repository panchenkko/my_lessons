package ru.clinicPetWeb.store;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.clinicPetWeb.models.Client;
import ru.clinicPetWeb.models.Pet;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class MemoryStorageFindMethodsTest {

    private static MemoryStorage storage;

    private Client client1;
    private Client client2;
    private Client client3;

    public MemoryStorageFindMethodsTest() {
        // Создаем трёх клиентов
        client1 = new Client(1, "clientName", new Pet(1, "petType", "petName", "petSex", "5"));
        client2 = new Client(2, "clientName", new Pet(2, "petType", "petName", "petSex", ""));
        client3 = new Client(3, "clientName", new Pet(3, "petType", "petName", "petSex", "5"));
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        storage = new MemoryStorage();
    }

    @Before
    public void setUp() throws Exception {
        // Вносим всех клиентов в базу
        storage.add(client1);
        storage.add(client2);
        storage.add(client3);
    }

    @After
    public void tearDown() throws Exception {
        storage.foldCounters();
    }

    @Test
    public void testFindIdClient() throws Exception {
        // Проверяем, что если клиент совпал по id, то он вносится в список найденных
        storage.findIdClient(1);

        assertThat(storage.valuesFound(), hasItem(client1));
        assertThat(storage.valuesFound().size(), is(1));
    }

    @Test
    public void testFindThreeParameters() throws Exception {
        // Проверяем, что если клиент совпал по всем трём полям, то он вносится в список найденных
        storage.findThreeParameters("clientName", "petName", "5");

        assertThat(storage.valuesFound().size(), is(2));
    }

    @Test
    public void testFindTwoParameters1() throws Exception {
        // Проверяем, что если клиент совпал по двум полям, то он вносится в список найденных
        storage.findTwoParameters("", "petName", "5");

        assertThat(storage.valuesFound().size(), is(2));
    }

    @Test
    public void testFindTwoParameters2() throws Exception {
        // Проверяем, что если клиент совпал по двум полям, то он вносится в список найденных
        storage.findTwoParameters("clientName", "", "5");

        assertThat(storage.valuesFound().size(), is(2));
    }

    @Test
    public void testFindTwoParameters3() throws Exception {
        // Проверяем, что если клиент совпал по двум полям, то он вносится в список найденных
        storage.findTwoParameters("clientName", "petName", "");

        assertThat(storage.valuesFound().size(), is(3));
    }

    @Test
    public void testFindOneParameters1() throws Exception {
        // Проверяем, что если клиент совпал по одному полю, то он вносится в список найденных
        storage.findOneParameters("clientName", "", "");

        assertThat(storage.valuesFound().size(), is(3));
    }

    @Test
    public void testFindOneParameters2() throws Exception {
        // Проверяем, что если клиент совпал по одному полю, то он вносится в список найденных
        storage.findOneParameters("", "petName", "");

        assertThat(storage.valuesFound().size(), is(3));
    }

    @Test
    public void testFindOneParameters3() throws Exception {
        // Проверяем, что если клиент совпал по одному полю, то он вносится в список найденных
        storage.findOneParameters("", "", "5");

        assertThat(storage.valuesFound().size(), is(2));
    }

    @Test
    public void testNotFound() throws Exception {
        // Проверяем, что совпадений не будет найдено
        storage.find("", "", "", "");

        assertThat(storage.valuesFound().size(), is(0));
    }

    /**
     *
     * Доп. методы для большего % покрытия кода
     *
     */

    @Test
    public void testFindForOtherBranches() throws Exception {
        storage.find(String.valueOf(client2.getId()), "", "", "");
        assertThat(storage.values(), hasItem(client2));

        storage.find("", client1.getName(), client1.getPet().getName(), client1.getPet().getAge());
        assertThat(storage.values(), hasItem(client1));

        storage.find("", client2.getName(), client2.getPet().getName(), client2.getPet().getAge());
        assertThat(storage.values(), hasItem(client3));
    }

    @Test
    public void testFindThreeParametersForOtherBranches() throws Exception {
        storage.findThreeParameters("clientName", "", "5");
        assertThat(storage.valuesFound().size(), is(0));
    }

    @Test
    public void testFindTwoParametersForOtherBranches() throws Exception {
        storage.findTwoParameters("clientName", "", "");
        assertThat(storage.valuesFound().size(), is(0));

        storage.findTwoParameters("", "petName", "");
        assertThat(storage.valuesFound().size(), is(0));
    }
}