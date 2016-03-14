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

public class ClientViewServletTest extends Mockito {

    final ClientCache clientCache = ClientCache.getInstance();

    @Test
    public void testClinicDeleteServlet() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(req.getRequestDispatcher("/views/client/ClientView.jsp")).thenReturn(dispatcher);

        clientCache.add(new Client(1, "clientName1", new Pet("petType1", "petName1", "petSex1", "age1")));
        clientCache.add(new Client(2, "clientName2", new Pet("petType2", "petName2", "petSex2", "age2")));
        clientCache.add(new Client(3, "clientName3", new Pet("petType3", "petName3", "petSex3", "age3")));

        new ClientViewServlet().doGet(req, resp);

        verify(dispatcher).forward(req,resp);
    }
}