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

        int client_id = Integer.valueOf(request.getParameter("id"));
        int pet_id = Integer.valueOf(request.getParameter("pet_id"));

        String clientName = request.getParameter("clientName");

		String petType = request.getParameter("petType");
        String petName = request.getParameter("petName");
		String petSex = request.getParameter("petSex");
		String petAge = request.getParameter("petAge");

		if (petType.equals("")) petType = " - ";
		if (petSex == null) petSex = " - ";
		if (petAge == null) petAge = " - ";

		this.CLIENT_CACHE.edit(new Client(client_id, clientName,
				               new Pet(pet_id, petType, petName, petSex, petAge)));

		response.sendRedirect(String.format("%s%s", request.getContextPath(), "/client/index"));
	}
}
