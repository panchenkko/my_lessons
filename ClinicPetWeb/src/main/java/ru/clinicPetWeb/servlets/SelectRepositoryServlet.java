package ru.clinicPetWeb.servlets;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.clinicPetWeb.exceptions.StorageNullException;
import ru.clinicPetWeb.service.ClassName;
import ru.clinicPetWeb.store.ClientCache;
import ru.clinicPetWeb.store.Storages;
import ru.clinicPetWeb.tools.DBTool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SelectRepositoryServlet extends HttpServlet {

    private final ClientCache CLIENT_CACHE = ClientCache.getInstance();

    private static final String PAGE_OK = "/views/selectRepository.jsp";

    public static final String URL_PAGE_INDEX = "/client/index";

    public static final String URL_PAGE_REPOSITORY = "/";

    private static final Logger logger = Logger.getLogger(ClassName.getCurrentClassName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            request.getRequestDispatcher(PAGE_OK).forward(request, response);
            logger.trace("RequestDispatcher(" + PAGE_OK + ").forward(request, response);");
        } catch (Exception e) {
            logger.fatal("PAGE FATAL ERROR! ", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            checkNullStorage(request, response);
        } catch (StorageNullException e) {
            logger.error("PAGE ERROR: " + e.getMessage());
            response.sendRedirect(String.format("%s%s", request.getContextPath(), URL_PAGE_REPOSITORY));
        }
    }

    public DBTool configurationIoC() {
        // Передаем конфигурации спринга
        ApplicationContext context = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
        // Получаем бин с помощью спринга, для работы со всеми реализованными хранилищами
        Storages storages = context.getBean(Storages.class);
        // Передаем в параметры конструктора класс со списком всех хранилищ
        return new DBTool(storages);
    }

    public void checkNullStorage(HttpServletRequest request, HttpServletResponse response) throws StorageNullException, IOException {
        DBTool tool = configurationIoC();

        String selected = request.getParameter("storage");

        if (selected != null) {
            CLIENT_CACHE.setStorage(tool.returnStorage(selected));
            if (CLIENT_CACHE.getStorage() != null) {
                response.sendRedirect(String.format("%s%s", request.getContextPath(), URL_PAGE_INDEX));
            } else {
                throw new StorageNullException("Хранилище не выбрано!");
            }
        } else {
            throw new StorageNullException("Значение не передано!");
        }
    }
}
