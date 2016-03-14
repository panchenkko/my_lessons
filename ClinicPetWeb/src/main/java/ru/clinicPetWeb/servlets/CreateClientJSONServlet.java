package ru.clinicPetWeb.servlets;

import org.codehaus.jackson.map.ObjectMapper;
import ru.clinicPetWeb.models.Client;
import ru.clinicPetWeb.models.Pet;
import ru.clinicPetWeb.store.ClientCache;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateClientJSONServlet extends HttpServlet {

    private final ClientCache CLIENT_CACHE = ClientCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Content-Type", "application/json; charset=utf-8");
        final ServletOutputStream out = resp.getOutputStream();
        out.print(new ObjectMapper().writeValueAsString(CLIENT_CACHE.values()));
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Content-Type", "application/json; charset=utf-8");
        final Client client = new ObjectMapper().readValue(req.getInputStream(), Client.class);
        this.CLIENT_CACHE.add(
                new Client(CLIENT_CACHE.generateId(), client.getName(),
                new Pet(client.getPet().getPetType(),
                        client.getPet().getName(),
                        client.getPet().getPetSex(),
                        client.getPet().getAge()))
        );
        resp.getOutputStream().write("{'result' : 'true'}".getBytes());
    }
}
