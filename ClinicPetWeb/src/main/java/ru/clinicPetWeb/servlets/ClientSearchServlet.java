package ru.clinicPetWeb.servlets;

import org.apache.log4j.Logger;
import ru.clinicPetWeb.service.ClassName;
import ru.clinicPetWeb.store.ClientCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientSearchServlet extends HttpServlet {

    private final ClientCache CLIENT_CACHE = ClientCache.getInstance();

    public static final String ATTRIBUTE_MODEL_TO_SEARCH = "found";
    public static final String PAGE_SEARCH_JSP = "/views/client/search.jsp";

    public static final String URL_PAGE_INDEX = "/client/index";
    public static final String URL_PAGE_SEARCH = "/client/search";

    private static final Logger logger = Logger.getLogger(ClassName.getCurrentClassName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            logger.info("SEARCHING CLIENTS");
            request.setAttribute(ATTRIBUTE_MODEL_TO_SEARCH, CLIENT_CACHE.valuesFound());
            logger.trace("setAttribute(" + ATTRIBUTE_MODEL_TO_SEARCH + ");");
            request.getRequestDispatcher(PAGE_SEARCH_JSP).forward(request, response);
            logger.trace("RequestDispatcher(" + PAGE_SEARCH_JSP + ").forward(request, response);");
        } catch (Exception e) {
            logger.error("PAGE ERROR! " + "Redirect(" + request.getContextPath() + URL_PAGE_INDEX + ");", e);
            response.sendRedirect(String.format("%s%s", request.getContextPath(), URL_PAGE_INDEX));
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String idClient = request.getParameter("idClient");
        String clientName = request.getParameter("clientName");
        String petName = request.getParameter("petName");
        String petAge = request.getParameter("petAge");

        try {
            CLIENT_CACHE.find(idClient, clientName, petName, petAge);

            logger.info("SEARCH SUCCESSFULLY");
        } catch (Exception e) {
            logger.error("SEARCH ERROR! ", e);
        }
        response.sendRedirect(String.format("%s%s", request.getContextPath(), URL_PAGE_SEARCH));
        logger.trace("Redirect(" + request.getContextPath() + URL_PAGE_SEARCH + ");");
    }

}
