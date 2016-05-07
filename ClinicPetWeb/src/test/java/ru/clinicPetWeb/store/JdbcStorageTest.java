package ru.clinicPetWeb.store;

import org.junit.*;
import org.mockito.Mockito;
import ru.clinicPetWeb.models.Client;
import ru.clinicPetWeb.models.Pet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcStorageTest extends Mockito {

    private static JdbcStorage jdbcStorage = null;

    private Statement statement;
    private ResultSet rs;

    @AfterClass
    public static void tearDownClass() throws Exception {
        jdbcStorage.deleteAll();
    }

    @Before
    public void setUp() throws Exception {
        jdbcStorage = new JdbcStorage();

        rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        statement = mock(Statement.class);
        when(statement.executeQuery
                ("select * from client right join pet on client.uid = pet.client_id")).thenReturn(rs);
    }

    @After
    public void tearDown() throws Exception {
        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenReturn(statement);
    }

    @Test
    public void testValuesFound() throws Exception {
        jdbcStorage.valuesFound();
    }

    @Test
    public void testValues() throws Exception {
        jdbcStorage.add(new Client(rs.getInt("1"), rs.getString("clientName"),
                        new Pet(rs.getString("petType"), rs.getString("petName"),
                                rs.getString("petSex"), rs.getString("petAge")))
        );
        jdbcStorage.values();
    }

    @Test
    public void testAdd() throws Exception {
        jdbcStorage.add(new Client(rs.getInt("1"), rs.getString("clientName"),
                        new Pet(rs.getString("petType"), rs.getString("petName"),
                                rs.getString("petSex"), rs.getString("petAge")))
        );
    }

    @Test
    public void testEdit() throws Exception {
        jdbcStorage.edit(new Client(rs.getInt("1"), rs.getString("clientName"),
                        new Pet(rs.getString("petType"), rs.getString("petName"),
                                rs.getString("petSex"), rs.getString("petAge")))
        );
    }

    @Test
    public void testDelete() throws Exception {
        jdbcStorage.delete(1);
    }

    @Test
    public void testDeleteAll() throws Exception {
        jdbcStorage.deleteAll();
    }

    @Test
    public void testGet() throws Exception {
        jdbcStorage.add(new Client(rs.getInt("1"), rs.getString("clientName"),
                        new Pet(rs.getString("petType"), rs.getString("petName"),
                                rs.getString("petSex"), rs.getString("petAge")))
        );
        jdbcStorage.get(1);
    }

    @Test
    public void testFind() throws Exception {
        jdbcStorage.add(new Client(rs.getInt("1"), rs.getString("clientName"),
                        new Pet(rs.getString("petType"), rs.getString("petName"),
                                rs.getString("petSex"), rs.getString("petAge")))
        );

        jdbcStorage.add(new Client(rs.getInt("2"), rs.getString("clientName"),
                        new Pet(rs.getString("petType"), rs.getString("petName"),
                                rs.getString("petSex"), rs.getString("")))
        );

        // Нет совпадений
        jdbcStorage.find("", "", "", "");

        // Доп. проверка для бранчей
        jdbcStorage.find("", "clientName", "petName", "");
    }

    @Test
    public void testFoundAdd() throws Exception {
        jdbcStorage.foundAdd(rs);
    }

    @Test
    public void testFindIdClient() throws Exception {
        // id
        jdbcStorage.findIdClient(1);
        jdbcStorage.findIdClient(2);
    }

    @Test
    public void testFindThreeParameters() throws Exception {
        // Совпадение по трём полям
        jdbcStorage.findThreeParameters("clientName", "petName", "petAge");
    }

    @Test
    public void testFindTwoParameters() throws Exception {
        // Совпадение по двум полям
        jdbcStorage.findTwoParameters("", "petName", "petAge");
        jdbcStorage.findTwoParameters("clientName", "", "petAge");
        jdbcStorage.findTwoParameters("clientName", "petName", "");
    }

    @Test
    public void testFindOneParameters() throws Exception {
        // Совпадение по одному полю
        jdbcStorage.findOneParameters("clientName", "", "");
        jdbcStorage.findOneParameters("", "petName", "");
        jdbcStorage.findOneParameters("", "", "1");
    }

    @Test
    public void testFindClientName() throws Exception {

    }

    @Test
    public void testFindPetName() throws Exception {
    }

    @Test
    public void testFindAge() throws Exception {
    }

    @Test
    public void testGenerateId() throws Exception {
        jdbcStorage.generateId();
    }

    @Test
    public void testClose() throws Exception {
        jdbcStorage.close();
    }
}