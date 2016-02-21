package ru.clinicWeb_jsp_jstl.servlets;

import ru.clinicWeb_jsp_jstl.models.Client;
import ru.clinicWeb_jsp_jstl.models.Pet;
import ru.clinicWeb_jsp_jstl.store.ClientCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientCreateServlet extends HttpServlet {

	final AtomicInteger ids = new AtomicInteger();

	private final ClientCache CLIENT_CACHE = ClientCache.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.CLIENT_CACHE.add(
				new Client(this.ids.incrementAndGet(), req.getParameter("nameClient"),
				new Pet(req.getParameter("petType"), req.getParameter("name"), req.getParameter("age"))));
		resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/client/view"));
	}
}
