package ru.clinicWeb_jsp_jstl;

import org.junit.Test;
import org.mockito.Mockito;
import ru.clinicWeb_jsp_jstl.models.Client;
import ru.clinicWeb_jsp_jstl.models.Pet;
import ru.clinicWeb_jsp_jstl.servlets.ClientCreateServlet;
import ru.clinicWeb_jsp_jstl.servlets.ClientEditServlet;
import ru.clinicWeb_jsp_jstl.store.ClientCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.*;

public class UserCRUDServletTest extends Mockito {

	final ClientCache cache = ClientCache.getInstance();

	@Test
	public void createClient() throws ServletException, IOException {

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		when(request.getParameter("nameClient")).thenReturn("test");
		when(request.getParameter("petType")).thenReturn("test");
		when(request.getParameter("name")).thenReturn("test");
		when(request.getParameter("age")).thenReturn("test");

		assertTrue(cache.values().isEmpty());

		new ClientCreateServlet().doPost(request, response);

		// Были хотя бы раз вызваны
		verify(request, atLeast(1)).getParameter("nameClient");
		verify(request, atLeast(1)).getParameter("petType");
		verify(request, atLeast(1)).getParameter("name");
		verify(request, atLeast(1)).getParameter("age");

		assertFalse(cache.values().isEmpty());

		cache.delete(cache.findByName("test").getId());
	}

	@Test
	public void editClient() throws ServletException, IOException {

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);

		cache.add(new Client(1, "ClientName", new Pet("cat", "petName", "5")));

		when(request.getParameter("id")).thenReturn("test");
		when(request.getParameter("nameClient")).thenReturn("test");
		when(request.getParameter("petType")).thenReturn("test");
		when(request.getParameter("name")).thenReturn("test");
		when(request.getParameter("age")).thenReturn("test");
		when(request.getRequestDispatcher("/view/client/EditClient.jsp")).thenReturn(dispatcher);

		assertFalse(cache.values().isEmpty());
		new ClientEditServlet().doPost(request, response);
//
//		// Были хотя бы раз вызваны
//		verify(request, atLeast(1)).getParameter("nameClient");
//		verify(request, atLeast(1)).getParameter("petType");
//		verify(request, atLeast(1)).getParameter("name");
//		verify(request, atLeast(1)).getParameter("age");
//
//		cache.delete(cache.findByName("test").getId());
	}
}