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
    public static final String PAGE_OK = "index.jsp";
    public static final String PAGE_ERROR = "/views/notFound.jsp";

    private static final Logger logger = Logger.getLogger(ClassName.getCurrentClassName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            request.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, CLIENT_CACHE.values());
            logger.trace("setAttribute(" + ATTRIBUTE_MODEL_TO_VIEW + ");");
            request.getRequestDispatcher("/" + PAGE_OK).forward(request, response);
            logger.trace("RequestDispatcher(" + PAGE_OK + ").forward(request, response);");
        } catch (Exception e) {
            logger.fatal("PAGE FATAL ERROR! ", e);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
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

            logger.info("NEW CLIENT [" +
                        "NAME='" + clientName + '\'' + ", " +
                        "PET=" +   petType + ", " + '\'' +   petName + '\'' + ", " + petSex + ", " + petAge + "]");
        } catch (Exception e) {
            logger.error("ADD CLIENT ERROR! ", e);
        }
        response.sendRedirect(String.format("%s%s", request.getContextPath(), "/client/index"));
        logger.trace("Redirect(" + request.getContextPath() + "/client/index);");
    }

    @Override
    public void destroy() {
        super.destroy();
        CLIENT_CACHE.close();
    }
}
