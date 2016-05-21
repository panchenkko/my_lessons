package ru.clinicPetWeb.store;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import ru.clinicPetWeb.models.Client;
import ru.clinicPetWeb.models.Pet;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryStorageTest extends Mockito {

    private static MemoryStorage memoryStorage = null;

    private final AtomicInteger ids = new AtomicInteger();
    private final ConcurrentHashMap<Integer, Client> clients = new ConcurrentHashMap<>();

    private final AtomicInteger idFound = new AtomicInteger();
    private final ConcurrentHashMap<Integer, Client> found = new ConcurrentHashMap<>();

    @BeforeClass
    public static void setUp() throws Exception {
        memoryStorage = new MemoryStorage();
    }

    @Test
    public void testValuesFound() throws Exception {
        memoryStorage.valuesFound();
    }

    @Test
    public void testValues() throws Exception {
        memoryStorage.values();
    }

    @Test
    public void testAdd() throws Exception {
        memoryStorage.add(new Client(1, "clientName", new Pet("petType", "petName", "petSex", "petAge")));
    }

    @Test
    public void testEdit() throws Exception {
        memoryStorage.add(new Client(1, "clientName", new Pet("petType", "petName", "petSex", "petAge")));
        memoryStorage.edit(new Client(1, "clientNameNew",
                new Pet("petTypeNew", "petNameNew", "petSexNew", "petAgeNew")));
    }

    @Test
    public void testDelete() throws Exception {
        memoryStorage.add(new Client(1, "clientName", new Pet("petType", "petName", "petSex", "petAge")));
        memoryStorage.delete(1);
    }

    @Test
    public void testDeleteAll() throws Exception {
        memoryStorage.foldCounter();
    }

    @Test
    public void testGet() throws Exception {
        memoryStorage.add(new Client(1, "clientName", new Pet("petType", "petName", "petSex", "petAge")));
        memoryStorage.get(1);
    }

    @Test
    public void testFind() throws Exception {
        memoryStorage.add(new Client(1, "clientName", new Pet("petType", "petName", "petSex", "petAge")));
        memoryStorage.add(new Client(2, "clientName", new Pet("petType", "petName", "petSex", "")));

        // id
        memoryStorage.find("1", "clientName", "petName", "petAge");
        memoryStorage.find("2", "clientName", "petName", "petAge");

        // Совпадение по трём полям
        memoryStorage.find("", "clientName", "petName", "petAge");

        // Совпадение по двум полям
        memoryStorage.find("", "", "petName", "petAge");
        memoryStorage.find("", "clientName", "", "petAge");
        memoryStorage.find("", "clientName", "petName", "");

        // Совпадение по одному полю
        memoryStorage.find("", "clientName", "", "");
        memoryStorage.find("", "", "petName", "");
        memoryStorage.find("", "", "", "petAge");

        // Нет совпадений
        memoryStorage.find("", "", "", "");

        // Доп. проверка для бранчей
        memoryStorage.find("", "clientName", "petName", "");
    }

    @Test
    public void testFindThreeParameters() throws Exception {

    }


    @Test
    public void testGenerateId() throws Exception {
        memoryStorage.generateId();
    }

    @Test
    public void testClose() throws Exception {
        memoryStorage.close();
    }
}