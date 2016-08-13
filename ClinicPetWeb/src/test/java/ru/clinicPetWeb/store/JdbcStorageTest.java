package ru.clinicPetWeb.store;

import org.junit.*;
import org.mockito.Mockito;
import ru.clinicPetWeb.models.Client;
import ru.clinicPetWeb.models.Pet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class JdbcStorageTest extends Mockito {

    private Storage storage;

    private final String SQL_SELECT_ALL = "SELECT * FROM client INNER JOIN pet ON client.pet_id = pet.uid";

    private Statement statement;
    private ResultSet rs;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        storage = new JdbcStorage();

        rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        when(rs.getInt("uid")).thenReturn(1).thenReturn(2);
        when(rs.getString("clientName")).thenReturn("clientName1").thenReturn("clientName2");

        when(rs.getInt("pet_id")).thenReturn(1).thenReturn(2);
        when(rs.getString("petType")).thenReturn("petType1").thenReturn("petType2");
        when(rs.getString("petName")).thenReturn("petName1").thenReturn("petName2");
        when(rs.getString("petSex")).thenReturn("petSex1").thenReturn("petSex2");
        when(rs.getString("petAge")).thenReturn("5").thenReturn("10");

//        statement = mock(Statement.class);
//        when(statement.executeQuery(SQL_SELECT_ALL)).thenReturn(rs);
//
//        connection = mock(Connection.class);
//        when(connection.createStatement()).thenReturn(statement);
    }

    @After
    public void tearDown() throws Exception {
        storage.foldCounters();
    }

    public Client clientAdd() throws Exception {
        return new Client(rs.getInt("uid"), rs.getString("clientName"),
               new Pet(rs.getInt("pet_id"), rs.getString("petType"),
                       rs.getString("petName"), rs.getString("petSex"), rs.getString("petAge")));
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
        storage.add(clientAdd());
        storage.add(clientAdd());

        assertThat(storage.values().size(), is(2));

        storage.delete(1);

        assertThat(storage.values().size(), is(1));
    }

    @Test
    public void testDeleteAll() throws Exception {
        // Проверяем, что при очистки базы данных, она будет пуста
        storage.add(clientAdd());
        storage.add(clientAdd());

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
        // Проверяем, что для jdbcStorage генерация id не должна работать
        assertEquals(storage.generateId(), 0);
        assertEquals(storage.generateId(), 0);
    }

    @Test
    public void testClose() throws Exception {
        storage.close();
    }
}