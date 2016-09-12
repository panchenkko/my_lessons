package ru.clinicPetWeb.servlets;

import org.apache.log4j.Logger;
import ru.clinicPetWeb.exceptions.CRUDException;
import ru.clinicPetWeb.exceptions.ClientNullException;
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
    public static final String PAGE_OK = "/views/client/index.jsp";
    public static final String PAGE_ERROR = "/views/notFound.jsp";

    public static final String URL_PAGE_INDEX = "/client/index";

    private static final Logger logger = Logger.getLogger(ClassName.getCurrentClassName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            request.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, CLIENT_CACHE.values());
            logger.trace("setAttribute(" + ATTRIBUTE_MODEL_TO_VIEW + ");");
            request.getRequestDispatcher(PAGE_OK).forward(request, response);
            logger.trace("RequestDispatcher(" + PAGE_OK + ").forward(request, response);");
        } catch (Exception e) {
            logger.fatal("PAGE FATAL ERROR! ", e);
        }
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

        Client client = new Client(CLIENT_CACHE.generateId(), clientName,
                        new Pet(CLIENT_CACHE.generateId(), petType, petName, petSex, petAge));

        addAndCheck(client);

        response.sendRedirect(String.format("%s%s", request.getContextPath(), URL_PAGE_INDEX));
        logger.trace("Redirect(" + request.getContextPath() + URL_PAGE_INDEX + ");");
    }

    public void addAndCheck(Client client) {
        int oldSize = CLIENT_CACHE.values().size();
        try {
            CLIENT_CACHE.add(client);

            if (oldSize < CLIENT_CACHE.values().size()) {
                logger.info("NEW CLIENT [ " + client + " ]");
            } else {
                throw new CRUDException("Клиент не был добавлен!");
            }
        } catch (CRUDException e) {
            logger.error("ADD CLIENT ERROR! ", e);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        CLIENT_CACHE.close();
    }
}
