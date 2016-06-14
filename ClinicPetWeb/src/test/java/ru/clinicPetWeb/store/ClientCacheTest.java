package ru.clinicPetWeb.store;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.clinicPetWeb.models.Client;
import ru.clinicPetWeb.models.Pet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClientCacheTest extends Mockito {

    private static ClientCache clientCache = null;

    private Statement statement;
    private ResultSet rs;

    @Before
    public void setUp() throws Exception {
        clientCache = new ClientCache();

        rs = Mockito.mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        statement = Mockito.mock(Statement.class);
        when(statement.executeQuery
                ("select * from client right join pet on client.uid = pet.client_id")).thenReturn(rs);
    }

    @After
    public void tearDown() throws Exception {
        Connection connection = Mockito.mock(Connection.class);
        when(connection.createStatement()).thenReturn(statement);
    }

    @Test
    public void testGet() throws Exception {
        clientCache.add(new Client(rs.getInt("1"), rs.getString("clientName"),
                        new Pet(rs.getInt("pet_id"), rs.getString("petType"), rs.getString("petName"),
                                rs.getString("petSex"), rs.getString("petAge")))
        );
        clientCache.get(1);
    }
}