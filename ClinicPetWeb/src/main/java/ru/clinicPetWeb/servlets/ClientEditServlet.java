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
    public static final String PAGE_EDIT_JSP = "EditClient.jsp";

    private static final Logger logger = Logger.getLogger(ClassName.getCurrentClassName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        try {
            Client client = CLIENT_CACHE.get(Integer.valueOf(request.getParameter("id")));
            logger.info("EDITING CLIENT [" +
                        "ID=" +    client.getId() + ", " +
                        "NAME='" + client.getName() + '\'' + ", " +
                        "PET=" +   client.getPet().getType() + ", " +
                          '\'' +   client.getPet().getName() + '\'' + ", " +
                                   client.getPet().getSex() + ", " +
                                   client.getPet().getAge() + "]");
            request.setAttribute("client", client);
            logger.trace("setAttribute(" + ATTRIBUTE_MODEL_TO_EDIT + ");");
            request.getRequestDispatcher("/views/client/" + PAGE_EDIT_JSP).forward(request, response);
            logger.trace("RequestDispatcher(" + PAGE_EDIT_JSP + ").forward(request, response);");
        } catch (Exception e) {
            logger.error("PAGE ERROR! " + "Redirect(" + request.getContextPath() + "/client/index);", e);
            response.sendRedirect(String.format("%s%s", request.getContextPath(), "/client/index"));
        }
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        try {
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

            CLIENT_CACHE.edit(new Client(client_id, clientName,
                              new Pet(pet_id, petType, petName, petSex, petAge)));
            logger.info("CLIENT EDITED [" +
                        "ID=" +    client_id + ", " +
                        "NAME='" + clientName + '\'' + ", " +
                        "PET=" +   petType + ", " + '\'' +   petName + '\'' + ", " + petSex + ", " + petAge + "]");
        } catch (Exception e) {
            logger.error("EDIT ERROR! ", e);
        }
		response.sendRedirect(String.format("%s%s", request.getContextPath(), "/client/index"));
        logger.trace("Redirect(" + request.getContextPath() + "/client/index);");
	}
}
