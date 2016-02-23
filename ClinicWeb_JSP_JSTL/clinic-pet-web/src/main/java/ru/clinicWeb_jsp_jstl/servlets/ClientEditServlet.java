package ru.clinicWeb_jsp_jstl.servlets;

import ru.clinicWeb_jsp_jstl.models.Client;
import ru.clinicWeb_jsp_jstl.models.Pet;
import ru.clinicWeb_jsp_jstl.store.ClientCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientEditServlet extends HttpServlet {

	private final ClientCache CLIENT_CACHE = ClientCache.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		req.setAttribute("client", this.CLIENT_CACHE.get(Integer.valueOf(req.getParameter("id"))));
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/client/EditClient.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		this.CLIENT_CACHE.edit(
				new Client(Integer.valueOf(req.getParameter("id")), req.getParameter("nameClient"),
				new Pet(req.getParameter("petType"), req.getParameter("name"), req.getParameter("age"))));
		resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/client/view"));
	}
}
