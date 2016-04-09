package ru.clinicPetWeb.servlets;

import org.junit.Test;
import org.mockito.Mockito;
import ru.clinicPetWeb.models.Client;
import ru.clinicPetWeb.models.Pet;
import ru.clinicPetWeb.store.ClientCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ClientEditServletTest extends Mockito {

    final ClientCache clientCache = ClientCache.getInstance();

    @Test
    public void testEditClient() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        ClientEditServlet clientEdit = new ClientEditServlet();

//        clientCache.add(new Client(1, "clientName", new Pet("petType", "petName", "petSex1", "petAge")));

//        when(req.getRequestDispatcher("/views/client/EditClient.jsp")).thenReturn(dispatcher);
        when(req.getParameter("id")).thenReturn("1");

        try {
            clientEdit.doGet(req, resp);
        } catch (IllegalStateException ignored) {}

//        verify(dispatcher).forward(req,resp);

        when(req.getParameter("petType")).thenReturn("");
        when(req.getParameter("petSex")).thenReturn(null);
        when(req.getParameter("petAge")).thenReturn(null);

        clientEdit.doPost(req, resp);

        when(req.getParameter("petType")).thenReturn("test");
        when(req.getParameter("petSex")).thenReturn("test");
        when(req.getParameter("petAge")).thenReturn("5");

        clientEdit.doPost(req, resp);
    }
}