package ru.clinicWebLesson.clinicPetWeb;

import ru.clinicLesson.clinic.Client;
import ru.clinicLesson.clinic.Pet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClinicServlet extends HttpServlet {

    final List<Client> clients = new CopyOnWriteArrayList<Client>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.append(
                "<!DOCTYPE html>" +
                        "<html>" +
                        "<head>" +
                        "     <title>Clinic Pets</title>" +
                        "</head>" +
                        "<body>" +
                        "     <form action='" + req.getContextPath() + "/' method='post'>" +
                        "         Client Name: </br><input type='text' name='nameClient' style='margin-bottom: 15px;'></br>" +
                        "         PetType: </br><input type='text' name='petType' style='margin-bottom: 10px;'></br>" +
                        "         Name: </br><input type='text' name='name' style='margin-bottom: 10px;'></br>" +
                        "         Age: </br><input type='text' name='age' style='margin-bottom: 5px;'></br>" +
                        "         <input type='submit' value='Submit'>" +
                        "     <form>" +
                        this.viewPets() +
                        "<h3><b>Search Clients</b></h3>" +
                        "     <form action='" + req.getContextPath() + "/' method='post'>" +
                        "         Enter the name client or pet: " +
                        "    </br><input type='text' name='search' style='margin-bottom: 15px;'></br>" +
                        "         <input type='submit' value='Submit'>" +
                        "     <form>" +
                        this.viewSearch(req) +
                        "</body>" +
                        "</html>"
        );
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getParameter("nameClient").equals("") && !req.getParameter("petType").equals("") &&
            !req.getParameter("name").equals("") && !req.getParameter("age").equals(""))
        this.clients.add(
                new Client(req.getParameter("nameClient"),
                new Pet(req.getParameter("petType"), req.getParameter("name"), req.getParameter("age"))));
        doGet(req, resp);
    }

    private String viewPets() {
        StringBuilder sb = new StringBuilder();
        sb.append("<h3><b>Clients</b></h3>");
        sb.append("<table style='border: 1px solid black'>");
        for (Client client : this.clients) {
            sb.append("<tr><td style='border: 1px solid black'>")
                    .append("<b>Name Client:</b> " + client.getName() + ", <b>" +
                            client.getPet().getPetType() + ":</b> " +
                            client.getPet().getName() + ", <b>age:</b> " +
                            client.getPet().getAge()).append("</td></tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }

    private String viewSearch(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        if (req.getParameter("search") != "") {
            sb.append("<table style='border: 1px solid black'>");
            for (Client client : this.clients) {
                if (Objects.equals(req.getParameter("search"), client.getName()) ||
                    Objects.equals(req.getParameter("search"), client.getPet().getName())) {
                    sb.append("<h3><b>Your Client: </b></h3>");
                    sb.append("<tr><td style='border: 1px solid black'>")
                            .append("<b>Name Client:</b> " + client.getName() + ", <b>" +
                                    client.getPet().getPetType() + ":</b> " +
                                    client.getPet().getName() + ", <b>age:</b> " +
                                    client.getPet().getAge()).append("</td></tr>");
                }
            }
                sb.append("</table>");
        }
        return sb.toString();
    }
}
