package ru.clinicPetWeb.servlets;

import org.junit.Test;
import org.mockito.Mockito;
import ru.clinicPetWeb.store.ClientCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexServletTest extends Mockito {

    final ClientCache clientCache = ClientCache.getInstance();

    @Test
    public void testClinicDeleteServlet() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        IndexServlet indexServlet = new IndexServlet();

        when(req.getRequestDispatcher("/index.jsp")).thenReturn(dispatcher);

        indexServlet.doGet(req, resp);

        verify(dispatcher).forward(req,resp);

        when(req.getParameter("petType")).thenReturn("");
        when(req.getParameter("petSex")).thenReturn(null);
        when(req.getParameter("petAge")).thenReturn(null);

        indexServlet.doPost(req, resp);

        when(req.getParameter("petType")).thenReturn("test");
        when(req.getParameter("petSex")).thenReturn("test");
        when(req.getParameter("petAge")).thenReturn("5");

        indexServlet.doPost(req, resp);

        /**
         * УДАЛЯЕТ ВСЕ ДАННЫЕ ИЗ ВСЕХ ТАБЛИЦ
         */
        clientCache.deleteAll();

        indexServlet.destroy();
    }
}