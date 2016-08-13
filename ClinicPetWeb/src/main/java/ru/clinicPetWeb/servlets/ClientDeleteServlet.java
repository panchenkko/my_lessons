package ru.clinicPetWeb.servlets;

import org.apache.log4j.Logger;
import ru.clinicPetWeb.models.Client;
import ru.clinicPetWeb.service.ClassName;
import ru.clinicPetWeb.store.ClientCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientDeleteServlet extends HttpServlet {

	private static final ClientCache CLIENT_CACHE = ClientCache.getInstance();

    private static final Logger logger = Logger.getLogger(ClassName.getCurrentClassName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Client client = CLIENT_CACHE.get(Integer.valueOf(request.getParameter("id")));

            CLIENT_CACHE.delete(client.getId());

            logger.info("DELETED CLIENT [" +
                    "ID=" +    client.getId() + ", " +
                    "NAME='" + client.getName() + '\'' + ", " +
                    "PET=" +   client.getPet().getType() + ", " +
                    '\'' +     client.getPet().getName() + '\'' + ", " +
                               client.getPet().getSex() + ", " +
                               client.getPet().getAge() + "]");
        } catch (Exception e) {
            logger.error("DELETE ERROR! ", e);
        }
		response.sendRedirect(String.format("%s%s", request.getContextPath(), "/client/index"));
		logger.trace("Redirect(" + request.getContextPath() + "/client/index);");
	}
}
