package ru.clinicPetWeb.tools;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.clinicPetWeb.exceptions.ClientNullException;
import ru.clinicPetWeb.models.Client;
import ru.clinicPetWeb.models.Pet;
import ru.clinicPetWeb.store.Storage;
import ru.clinicPetWeb.store.Storages;

import java.util.Scanner;

public class DBTool {

    private Storages storages;
    private Storage storage;
    private Scanner sc;

    // Для консольной версии
    public DBTool(final Storages storages, final Scanner sc) {
        this.storages = storages;
        this.sc = sc;

        cycleQuestion();
    }

    // Для веб версии
    public DBTool(final Storages storages) {
        this.storages = storages;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Передаем конфигурации спринга
        ApplicationContext context = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
        // Получаем бин с помощью спринга, для работы со всеми реализованными хранилищами
        Storages storage = context.getBean(Storages.class);
        // Передаем в параметры конструктора бин и в нём же вызываем метод, для выбора хранилища
        DBTool tool = new DBTool(storage, sc);
        // Псоле того, как пользователь выбрал хранилище, с каким он будет работать, вызываем меню всевозможных действий
        tool.actions();

        sc.close();
    }

    /**
     * Спрашиваем пользователя с каким хранилищем он хочет работать
     * @return возвращаем ответ в виде строки
     */
    public String questionStorage() {
        System.out.println();
        System.out.println("Выберите с каким хранилищем вы хотите работать: " + "\n");
        System.out.println("1. Memory");
        System.out.println("2. JDBC");
        System.out.println("3. Hibernate");
        System.out.println("4. HibernateTemplate" + "\n");
        System.out.print("Ответ: ");
        return sc.next().toLowerCase();
    }

    /**
     * Получаем с аргумента ответ пользователя о хранилище, и настраиваем данные для работы с ним
     * @return возвращаем хранилище выбранное пользователем
     */
    public Storage returnStorage(String answer) {
        switch (answer) {
            case "memory": return storages.memoryStorage;
            case "jdbc": return storages.jdbcStorage;
            case "hibernate": return storages.hibernateStorage;
            case "hibernatetemplate": return storages.hibernateTemplate;
            default: return null;
        }
    }

    /**
     * Спрашиваем пользователя о хранилище, до тех пор пока он не выберет одно из предоставляемых.
     * Если пользователь выбрал, выходим из цикла.
     */
    public void cycleQuestion() {
        Storage storage;
        while (this.storage == null) {
            storage = returnStorage(questionStorage());
            if (storage != null) {
                this.storage = storage;
            }
        }
    }

    /**
     * Метод для проверки существования клиента
     * @param id Передаем id клиента, и проверяем существует ли клиент с данным номером
     * @throws ClientNullException Если клиента с таким id нету, генерируем исключение и говорим пользователю об этом
     */
    public void checkClientNull(final int id) throws ClientNullException {
        for (Client client : storage.values()) {
            if (client.getId() == id) {
                return;
            }
        }
        throw new ClientNullException(String.format("\n" + "Пользователь под номером %s не найден!", id));
    }

    /**
     * Спрашиваем пользователя, какое действие с хранилищем он хочет сделать
     * @return Передаем ответ в текстовом формате и нижнем регистре
     */
    public String questionSelectFromMenu() {
        System.out.println();
        System.out.println("Выберите действие: " + "\n");
        System.out.println("1. Список");
        System.out.println("2. Добавить");
        System.out.println("3. Редактировать");
        System.out.println("4. Поиск");
        System.out.println("5. Удалить");
        System.out.println("6. Удалить всё");
        System.out.println("7. Выход" + "\n");
        System.out.print("Ответ: ");
        return sc.next().toLowerCase();
    }

    /**
     * Спрашиваем пользователя о действиях с хранилищем, до момента пока он не захочет выйти из программы
     */
    public void actions() {
        String exit = "";
        while (!exit.toLowerCase().equals("да") && !exit.toLowerCase().equals("yes")) {

            String answer = questionSelectFromMenu();

            try {
                switch (answer) {
                    case "список":        values();
                                          break;
                    case "добавить":      add();
                                          break;
                    case "редактировать": edit();
                                          break;
                    case "поиск":         find();
                                          break;
                    case "удалить":       delete();
                                          break;
                    case "удалить всё":   foldCounters();
                                          break;
                    case "выход":         return;
                    default:
                        System.out.println("Подсказка: Введите полное слово пункта меню.");
                        continue;
                }

                System.out.println();
                System.out.print("Выйти? (да/нет) : ");
                exit = sc.next();
            } catch (ClientNullException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Вносим нового клиента в хранилище
     */
    public void add() {
        int id = generateId();
        System.out.print("Введите имя клиента: ");
        String clientName = sc.next();
        System.out.print("Введите тип питомца: ");
        String petType = sc.next();
        System.out.print("Введите имя питомца: ");
        String petName = sc.next();
        System.out.print("Введите пол питомца: ");
        String petSex = sc.next();
        System.out.print("Введите возраст питомца: ");
        String petAge = sc.next();

        storage.add(new Client(id, clientName, new Pet(id, petType, petName, petSex, petAge)));
    }

    /**
     * Получаем список клиентов, какие были найдены при поиске
     */
    public void valuesFound() {
        System.out.println();
        System.out.println("Найдено: ");

        for (Client client : storage.valuesFound()) {
            System.out.println("    " + client);
        }
    }

    /**
     * Получаем список всех клиентов
     */
    public void values() {
        storage.values().forEach(System.out::println);
        // Код выше эквивалентен коду ниже.
//        for (Client client : storage.values()) {
//            System.out.println(client);
//        }
    }

    /**
     * Редактируем данные определенного клиента
     * @throws ClientNullException Если клиента, с id какой передал пользователь нету, то говорим об этом пользователю
     * и выходим из редактирования
     */
    public void edit() throws ClientNullException {
        System.out.print("Введите id клиента: ");
        int id = sc.nextInt();

        checkClientNull(id);

        System.out.println();
        System.out.println(String.format("Редактирование клиента id которого %s :", id));
        System.out.println("    " + get(id));

        System.out.println();
        System.out.print("Введите новое имя клиента: ");
        String clientName = sc.next();
        System.out.print("Введите новый тип питомца: ");
        String petType = sc.next();
        System.out.print("Введите новое имя питомца: ");
        String petName = sc.next();
        System.out.print("Введите новый пол питомца: ");
        String petSex = sc.next();
        System.out.print("Введите новый возраст питомца: ");
        String petAge = sc.next();

        storage.edit(new Client(id, clientName, new Pet(id, petType, petName, petSex, petAge)));

        System.out.println();
        System.out.println("Редактирование клиента успешно выполнено :");
        System.out.println("    " + get(id));
    }

    /**
     * Удаляем клиента
     * @throws ClientNullException Если клиента, с id какой передал пользователь нету, то говорим об этом пользователю
     * и выходим из удаления
     */
    public void delete() throws ClientNullException {
        System.out.print("Введите id клиента: ");
        int id = sc.nextInt();

        checkClientNull(id);

        storage.delete(id);
    }

    /**
     * Збрасываем счетчики и очищаем все списки
     */
    public void foldCounters() {
        storage.foldCounters();
    }

    /**
     * Получаем клиента с переданным id
     * @param id получаем id клиента, какого нужнжо вывести
     * @return возвращаем объект клиента
     */
    public Client get(final int id) {
        return storage.get(id);
    }

    /**
     * Производим поиск клиентов, по введенным данным
     */
    public void find() {
        System.out.print("Введите искомый id клиента (или введите \"дальше\", чтобы пропустить) : ");
        String id = sc.next();
        System.out.print("Введите искомое имя клиента (или введите \"дальше\", чтобы пропустить) : ");
        String clientName = sc.next();
        System.out.print("Введите искомое имя питомца (или введите \"дальше\", чтобы пропустить) : ");
        String petName = sc.next();
        System.out.print("Введите искомый возраст питомца (или введите \"дальше\", чтобы пропустить) : ");
        String petAge = sc.next();

        if (!NumberUtils.isNumber(id)) id = "";
        if (clientName.equals("дальше")) clientName = "";
        if (petName.equals("дальше")) petName = "";
        if (petAge.equals("дальше")) petAge = "";

        storage.find(id, clientName, petName, petAge);

        String searchingClient = "Client{" + id + ". " +
                                         "name='" + clientName + '\'' +
                                         ", pet=" + "Pet{" + id + ". " +
                                                         "type='" + '\'' +
                                                         ", name='" + petName + '\'' +
                                                         ", sex='" + '\'' +
                                                         ", age='" + petAge + '\'' +
                                                         '}' +
                                 '}';

        System.out.println();
        System.out.println("Данные, по каким производился поиск:");
        System.out.println("    " + searchingClient);

        valuesFound();
    }

    /**
     * Генерируем уникальное id для клиента
     * @return возвращаем новое id
     */
    public int generateId() {
        return storage.generateId();
    }

    public void close() {
        storage.close();
    }
}
