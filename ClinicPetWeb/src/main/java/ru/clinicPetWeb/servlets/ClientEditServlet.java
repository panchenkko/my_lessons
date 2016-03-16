package ru.clinicPetWeb.servlets;

import ru.clinicPetWeb.models.Client;
import ru.clinicPetWeb.models.Pet;
import ru.clinicPetWeb.store.ClientCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientEditServlet extends HttpServlet {

	private final ClientCache CLIENT_CACHE = ClientCache.getInstance();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("client", this.CLIENT_CACHE.get(Integer.valueOf(request.getParameter("id"))));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/client/EditClient.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		this.CLIENT_CACHE.edit(
				new Client(Integer.valueOf(request.getParameter("id")), request.getParameter("clientName"),
				new Pet(request.getParameter("petType"),
						request.getParameter("petName"),
						request.getParameter("petSex"),
						request.getParameter("petAge")))
		);
		response.sendRedirect(String.format("%s%s", request.getContextPath(), "/client/index"));
	}
}
