package ru.clinicPetWeb.servlets;

import ru.clinicPetWeb.store.ClientCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientSearchServlet extends HttpServlet {

    private final ClientCache CLIENT_CACHE = ClientCache.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("clients", this.CLIENT_CACHE.values());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/client/ClientView.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
