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

import static org.junit.Assert.*;

public class ClientEditServletTest extends Mockito {

    final ClientCache clientCache = ClientCache.getInstance();
    private ClientEditServlet clientEdit = new ClientEditServlet();


    @Test
    public void testEditClient() throws ServletException, IOException {
//        HttpServletRequest req = mock(HttpServletRequest.class);
//        HttpServletResponse resp = mock(HttpServletResponse.class);
//        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
//
//        clientCache.add(new Client(1, "clientName", new Pet("petType", "petName", "petSex1", "age")));
//
//        when(req.getRequestDispatcher("/views/client/EditClient.jsp")).thenReturn(dispatcher);
//        when(req.getParameter("id")).thenReturn("1");
//
//        clientEdit.doGet(req,resp);
//
//        verify(dispatcher).forward(req,resp);
//
//        when(req.getParameter("nameClient")).thenReturn("newClientName");
//        when(req.getParameter("petType")).thenReturn("newPetType");
//        when(req.getParameter("name")).thenReturn("newName");
//        when(req.getParameter("age")).thenReturn("newAge");
//
//        clientEdit.doPost(req,resp);
//
//        assertEquals("newClientName", clientCache.get(1).getName());
//        assertEquals("newPetType", clientCache.get(1).getPet().getPetType());
//        assertEquals("newName", clientCache.get(1).getPet().getName());
//        assertEquals("newAge", clientCache.get(1).getPet().getAge());

//        try {
//            clientCache.delete(clientCache.find("Testing Exception").getId());
//        } catch (IllegalStateException ise) {
//            System.out.println("Not found!");
//        }
//
//        clientCache.delete(clientCache.find("newClientName").getId());
    }
}