package ru.clinicPetWeb.servlets;

import org.junit.Test;
import org.mockito.Mockito;
import ru.clinicPetWeb.store.ClientCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.*;

public class ClientCreateServletTest extends Mockito {

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
	}
}