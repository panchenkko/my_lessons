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

        when(req.getParameter("id")).thenReturn("1");

        new ClientDeleteServlet().doGet(req, resp);

        clientCache.delete(1);
    }
}