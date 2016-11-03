package ru.clinicPetWeb.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.clinicPetWeb.exceptions.CRUDException;
import ru.clinicPetWeb.exceptions.ClientNullException;
import ru.clinicPetWeb.exceptions.StorageNullException;
import ru.clinicPetWeb.models.Client;
import ru.clinicPetWeb.models.Pet;
import ru.clinicPetWeb.service.ClassName;
import ru.clinicPetWeb.store.Storage;
import ru.clinicPetWeb.store.Storages;
import ru.clinicPetWeb.tools.DBTool;

@Controller
@RequestMapping("/") // Указываем, по какому запросу будет обрабатываться данный контроллер (URL)
public class AdminController {

    @Autowired
    private Storages storages;

    private Storage storage;

    private static final Logger logger = Logger.getLogger(ClassName.getCurrentClassName());

    public static final String PAGE_SELECT_REPOSITORY = "/selectRepository";
    public static final String PAGE_INDEX = "/client/index";
    public static final String PAGE_EDIT = "/client/edit";
    public static final String PAGE_SEARCH = "/client/search";

    // Полный путь для удобства. Если произойдет переключение со Spring MVC на сервлеты.
    public static final String PAGE_LOGGING = "/views/selectLevelLogging.jsp";

    public static final String ACTION_DELETE = "/client/delete";

    // При запуске приложения, он сразу попадает сюда и мы переводим его на страницу selectRepository.jsp
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        return PAGE_SELECT_REPOSITORY;
    }

    @RequestMapping(value = PAGE_SELECT_REPOSITORY, method = RequestMethod.GET)
    public String selectRepositoryGet() {
        return PAGE_SELECT_REPOSITORY;
    }

    @RequestMapping(value = PAGE_SELECT_REPOSITORY, method = RequestMethod.POST)
    public String selectRepositoryPost(@ModelAttribute("storage") String selected, ModelMap model) {
        // Передаем в параметры конструктора класс со списком всех хранилищ
        // Вызываем метод для получения выбранного хранилища пользователем
        storage = new DBTool(storages).returnStorage(selected);

        return checkStorage(model);
    }

    public String checkStorage(ModelMap model) {
        try {
            if (storage == null)
                throw new StorageNullException("Хранилище не выбрано!");
            else {
                model.addAttribute("clients", storage.values());
                return PAGE_INDEX;
            }
        } catch (StorageNullException e) {
            logger.error("PAGE ERROR: " + e.getMessage());
            return "redirect:" + PAGE_SELECT_REPOSITORY;
        }
    }

    @RequestMapping(value = PAGE_INDEX, method = RequestMethod.GET)
    public String values(ModelMap model) {
//        storage.values().forEach((client) -> System.out.println("CLIENT: " + client));
        model.addAttribute("clients", storage.values());
        return PAGE_INDEX;
    }

    @RequestMapping(value = PAGE_INDEX, method = RequestMethod.POST)
    public String addClient(@ModelAttribute("clientName") String clientName,
                            @ModelAttribute("petType") String petType,
                            @ModelAttribute("petName") String petName,
                            @ModelAttribute("petSex") String petSex,
                            @ModelAttribute("petAge") String petAge) {

        if (petType.equals("")) petType = " - ";
        if (petSex.equals("")) petSex = " - ";
        if (petAge == null) petAge = " - ";

        Client client = new Client(storage.generateId(), clientName,
                        new Pet(storage.generateId(), petType, petName, petSex, petAge));

        addAndCheck(client);

        return "redirect:" + PAGE_INDEX;
    }

    public void addAndCheck(Client client) {
        int oldSize = storage.values().size();
        try {
            storage.add(client);

            if (oldSize < storage.values().size()) {
                logger.info("NEW CLIENT [ " + client + " ]");
            } else {
                throw new CRUDException("Клиент не был добавлен!");
            }
        } catch (CRUDException e) {
            logger.error("ADD CLIENT ERROR! ", e);
        }
    }

    @RequestMapping(value = ACTION_DELETE, method = RequestMethod.GET)
    public String deleteClient(@ModelAttribute("id") String id) {
        deleteAndCheck(storage.get(Integer.valueOf(id)));
        return "redirect:" + PAGE_INDEX;
    }

    public void deleteAndCheck(Client client) {
        int oldSize = storage.values().size();
        try {
            if (storage.get(client.getId()) != null) {
                storage.delete(client.getId());

                if (oldSize > storage.values().size()) {
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

    @RequestMapping(value = PAGE_LOGGING, method = RequestMethod.GET)
    public String logging() {
        return "redirect:" + PAGE_LOGGING;
    }

    @RequestMapping(value = PAGE_EDIT, method = RequestMethod.GET)
    public String editClientGet(@ModelAttribute("id") String id, ModelMap model) {
        model.addAttribute("client", storage.get(Integer.valueOf(id)));
        return PAGE_EDIT;
    }

    @RequestMapping(value = PAGE_EDIT, method = RequestMethod.POST)
    public String editClientPost(@ModelAttribute("id") String client_id,
                                 @ModelAttribute("pet_id") String pet_id,
                                 @ModelAttribute("clientName") String clientName,
                                 @ModelAttribute("petType") String petType,
                                 @ModelAttribute("petName") String petName,
                                 @ModelAttribute("petSex") String petSex,
                                 @ModelAttribute("petAge") String petAge, ModelMap model) {

        if (petType.equals("")) petType = " - ";
        if (petSex == null) petSex = " - ";
        if (petAge == null) petAge = " - ";

        Client client = new Client(Integer.valueOf(client_id), clientName,
                        new Pet(Integer.valueOf(pet_id), petType, petName, petSex, petAge));

        storage.edit(client);

        model.addAttribute("clients", storage.values());

        return PAGE_INDEX;
    }

    @RequestMapping(value = PAGE_SEARCH, method = RequestMethod.GET)
    public String searchClientsGet(ModelMap model) {
        model.addAttribute("found", storage.valuesFound());
        return PAGE_SEARCH;
    }

    @RequestMapping(value = PAGE_SEARCH, method = RequestMethod.POST)
    public String searchClientsPost(@ModelAttribute("idClient") String client_id,
                                    @ModelAttribute("clientName") String clientName,
                                    @ModelAttribute("petName") String petName,
                                    @ModelAttribute("petAge") String petAge, ModelMap model) {

        storage.find(client_id, clientName, petName, petAge);

        model.addAttribute("found", storage.valuesFound());

        return PAGE_SEARCH;
    }
}
