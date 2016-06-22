package ru.clinicPetWeb.servlets;

import ru.clinicPetWeb.store.ClientCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientDeleteServlet extends HttpServlet {

	private static final ClientCache CLIENT_CACHE = ClientCache.getInstance();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CLIENT_CACHE.delete(Integer.valueOf(request.getParameter("id")));
		response.sendRedirect(String.format("%s%s", request.getContextPath(), "/client/index"));
	}
}
