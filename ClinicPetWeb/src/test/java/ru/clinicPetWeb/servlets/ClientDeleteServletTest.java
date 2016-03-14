package ru.clinicPetWeb.servlets;

import org.junit.Test;
import org.mockito.Mockito;
import ru.clinicPetWeb.models.Client;
import ru.clinicPetWeb.models.Pet;
import ru.clinicPetWeb.store.ClientCache;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;

public class ClientDeleteServletTest extends Mockito {

    final ClientCache clientCache = ClientCache.getInstance();

    @Test
    public void testClinicDeleteServlet() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        clientCache.add(new Client(1, "clientName1", new Pet("petType1", "petName1", "petSex1", "age1")));
        clientCache.add(new Client(2, "clientName2", new Pet("petType2", "petName2", "petSex2", "age2")));
        clientCache.add(new Client(3, "clientName3", new Pet("petType3", "petName3", "petSex3", "age3")));

        when(req.getParameter("id")).thenReturn("1");

        assertEquals(3, clientCache.size());

        new ClientDeleteServlet().doGet(req, resp);

        assertEquals(2, clientCache.size());
    }
}