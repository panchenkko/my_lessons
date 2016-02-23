package ru.clinicWeb_jsp_jstl;

import org.junit.Test;
import org.mockito.Mockito;
import ru.clinicWeb_jsp_jstl.servlets.ClientCreateServlet;
import ru.clinicWeb_jsp_jstl.store.ClientCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.*;

public class UserCRUDServletTest extends Mockito {

	final ClientCache cache = ClientCache.getInstance();

	@Test
	public void createUser() throws ServletException, IOException {

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		when(request.getParameter("nameClient")).thenReturn("test");
		when(request.getParameter("petType")).thenReturn("test");
		when(request.getParameter("name")).thenReturn("test");
		when(request.getParameter("age")).thenReturn("test");

		assertTrue(cache.values().isEmpty());

//		new ClientCreateServlet().doPost(request, response);
//
//		verify(request, atLeast(1)).getParameter("nameClient");
//		verify(request, atLeast(1)).getParameter("petType");
//		verify(request, atLeast(1)).getParameter("name");
//		verify(request, atLeast(1)).getParameter("age");
//		assertFalse(cache.values().isEmpty());
//
//		cache.delete(cache.findByLogin("test").getId());
	}
}