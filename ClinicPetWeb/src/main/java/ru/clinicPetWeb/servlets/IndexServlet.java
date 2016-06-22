package ru.clinicPetWeb.servlets;

import org.apache.log4j.Logger;
import ru.clinicPetWeb.models.Client;
import ru.clinicPetWeb.models.Pet;
import ru.clinicPetWeb.service.ClassName;
import ru.clinicPetWeb.store.ClientCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexServlet extends HttpServlet {

    private final ClientCache CLIENT_CACHE = ClientCache.getInstance();

    public static final String ATTRIBUTE_MODEL_TO_VIEW = "clients";
    public static final String PAGE_INDEX_JSP = "index.jsp";

    private static final Logger logger = Logger.getLogger(ClassName.getCurrentClassName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, CLIENT_CACHE.values());
        logger.trace("setAttribute(" + ATTRIBUTE_MODEL_TO_VIEW + ");");
        request.getRequestDispatcher("/" + PAGE_INDEX_JSP).forward(request, response);
        logger.trace("RequestDispatcher(/" + PAGE_INDEX_JSP + ").forward(request, response);");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String clientName = request.getParameter("clientName");
        String petType = request.getParameter("petType");
        String petName = request.getParameter("petName");
        String petSex = request.getParameter("petSex");
        String petAge = request.getParameter("petAge");

        if (petType.equals("")) petType = " - ";
        if (petSex == null) petSex = " - ";
        if (petAge == null) petAge = " - ";

        CLIENT_CACHE.add(new Client(CLIENT_CACHE.generateId(), clientName,
                         new Pet(CLIENT_CACHE.generateId(), petType, petName, petSex, petAge)));
        logger.info("New Client[" + clientName + ", " + "pet: " + petName + "]");
        response.sendRedirect(String.format("%s%s", request.getContextPath(), "/client/index"));
        logger.trace("Redirect(" + request.getContextPath() + "/client/index);");
    }

    @Override
    public void destroy() {
        super.destroy();
        CLIENT_CACHE.close();
    }
}
