package ru.clinicPetWeb.servlets;

import org.junit.Test;
import org.mockito.Mockito;
import ru.clinicPetWeb.store.ClientCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientSearchServletTest extends Mockito {

    final ClientCache clientCache = ClientCache.getInstance();

    @Test
    public void testEditClient() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        ClientSearchServlet clientSearch = new ClientSearchServlet();

//        clientCache.add(new Client(1, "clientName", new Pet("petType", "petName", "petSex", "petAge")));

        when(req.getRequestDispatcher("/views/client/SearchClient.jsp")).thenReturn(dispatcher);

        clientSearch.doGet(req, resp);

        verify(dispatcher).forward(req,resp);

        when(req.getParameter("idClient")).thenReturn("1");
        when(req.getParameter("clientName")).thenReturn("test");
        when(req.getParameter("petName")).thenReturn("test");
        when(req.getParameter("petAge")).thenReturn("5");

        clientSearch.doPost(req, resp);

        when(req.getParameter("idClient")).thenReturn("");
        when(req.getParameter("clientName")).thenReturn("test");
        when(req.getParameter("petName")).thenReturn("test");
        when(req.getParameter("petAge")).thenReturn("5");

        clientSearch.doPost(req, resp);

        when(req.getParameter("idClient")).thenReturn("");
        when(req.getParameter("clientName")).thenReturn("");
        when(req.getParameter("petName")).thenReturn("test");
        when(req.getParameter("petAge")).thenReturn("5");

        clientSearch.doPost(req, resp);

        when(req.getParameter("idClient")).thenReturn("");
        when(req.getParameter("clientName")).thenReturn("");
        when(req.getParameter("petName")).thenReturn("");
        when(req.getParameter("petAge")).thenReturn("5");

        clientSearch.doPost(req, resp);

    }
}