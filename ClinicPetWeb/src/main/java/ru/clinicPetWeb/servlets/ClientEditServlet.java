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

public class ClientEditServlet extends HttpServlet {

	private final ClientCache CLIENT_CACHE = ClientCache.getInstance();

    public static final String ATTRIBUTE_MODEL_TO_EDIT = "clients";
    public static final String PAGE_EDIT_JSP = "/views/client/edit.jsp";

    public static final String URL_PAGE_INDEX = "/client/index";

    private static final Logger logger = Logger.getLogger(ClassName.getCurrentClassName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

        try {
            Client client = CLIENT_CACHE.get(Integer.valueOf(request.getParameter("id")));

            logger.info("EDITING CLIENT [ " + client + " ]");

            request.setAttribute("client", client);
            logger.trace("setAttribute(" + ATTRIBUTE_MODEL_TO_EDIT + ");");
            request.getRequestDispatcher(PAGE_EDIT_JSP).forward(request, response);
            logger.trace("RequestDispatcher(" + PAGE_EDIT_JSP + ").forward(request, response);");
        } catch (Exception e) {
            logger.error("PAGE ERROR! " + "Redirect(" + request.getContextPath() + URL_PAGE_INDEX + ");", e);
            response.sendRedirect(String.format("%s%s", request.getContextPath(), URL_PAGE_INDEX));
        }
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

        Client client = new Client(client_id, clientName,
                        new Pet(pet_id, petType, petName, petSex, petAge));

        try {
            CLIENT_CACHE.edit(client);

            logger.info("CLIENT EDITED [ " + client + " ]");
        } catch (Exception e) {
            logger.error("EDIT ERROR! ", e);
        }
		response.sendRedirect(String.format("%s%s", request.getContextPath(), URL_PAGE_INDEX));
        logger.trace("Redirect(" + request.getContextPath() + URL_PAGE_INDEX + ");");
	}
}
