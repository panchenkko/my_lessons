package ru.clinicPetWeb.servlets;

import ru.clinicPetWeb.models.Client;
import ru.clinicPetWeb.models.Pet;
import ru.clinicPetWeb.store.ClientCache;

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
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		this.CLIENT_CACHE.add(
				new Client(this.ids.incrementAndGet(), req.getParameter("nameClient"),
				new Pet(req.getParameter("petType"),
						req.getParameter("name"),
						req.getParameter("petSex"),
						req.getParameter("age")))
		);
		resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/client/view"));
	}
}
