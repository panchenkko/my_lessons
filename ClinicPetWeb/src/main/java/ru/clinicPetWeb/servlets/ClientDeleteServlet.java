package ru.clinicPetWeb.servlets;

import org.apache.log4j.Logger;
import ru.clinicPetWeb.exceptions.CRUDException;
import ru.clinicPetWeb.exceptions.ClientNullException;
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

    public static final String URL_PAGE_INDEX = "/client/index";

    private static final Logger logger = Logger.getLogger(ClassName.getCurrentClassName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        deleteAndCheck(CLIENT_CACHE.get(Integer.valueOf(request.getParameter("id"))));

		response.sendRedirect(String.format("%s%s", request.getContextPath(), URL_PAGE_INDEX));
		logger.trace("Redirect(" + request.getContextPath() + URL_PAGE_INDEX + ");");
	}

    public void deleteAndCheck(Client client) {
        int oldSize = CLIENT_CACHE.values().size();
        try {
            if (CLIENT_CACHE.get(client.getId()) != null) {
                CLIENT_CACHE.delete(client.getId());

                if (oldSize > CLIENT_CACHE.values().size()) {
                    logger.info("DELETED CLIENT [ " + client + " ]");
                } else {
                    throw new CRUDException(String.format("Клиент под номером %s не удален!", client.getId()));
                }
            } else {
                throw new ClientNullException(String.format("Клиент под номером %s не найден!", client.getId()));
            }
        } catch (CRUDException | ClientNullException e) {
            logger.error("DELETE ERROR! ", e);
        }
    }
}
