package ru.clinicPetWeb.servlets;

import ru.clinicPetWeb.store.ClientCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientViewServlet extends HttpServlet {

	private final ClientCache CLIENT_CACHE = ClientCache.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		req.setAttribute("clients", this.CLIENT_CACHE.values());
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/client/ClientView.jsp");
		dispatcher.forward(req, resp);
	}
}
