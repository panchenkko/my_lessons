package ru.clinicPetWeb.servlets;

import ru.clinicPetWeb.store.ClientCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class ClientSearchServlet extends HttpServlet {

    private final ClientCache CLIENT_CACHE = ClientCache.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("clients", this.CLIENT_CACHE.values());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/client/SearchClient.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientName = request.getParameter("clientName");
        String petType = request.getParameter("petType");
        String petName = request.getParameter("petName");
        String petSex = request.getParameter("petSex");
        String petAge = request.getParameter("petAge");

//        if (!Objects.equals(clientName, "") && !Objects.equals(petType, "") &&
//            !Objects.equals(petName, "") && !Objects.equals(petSex, "") && !Objects.equals(petAge, "")) {
//            for ()
//        }
    }

}
