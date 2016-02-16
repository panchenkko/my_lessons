package ru.clinicLesson.clinic;

import java.util.*;

public class Clinic {

    private final List<Client> clients;

    public Clinic() {
        this.clients = new ArrayList<Client>();
    }

    public void menu(Scanner sc) {
        System.out.println(
                "\n\033[30m<-- ВВЕДИТЕ ЦИФРУ -->\033[0m"
              + "\n1. Добавить клиента"
              + "\n2. Поиск"
              + "\n3. Редактировать данные клиента"
              + "\n4. Удалить клиента"
              + "\n5. Вывести всех клиентов"
              + "\n6. Справочник"
              + "\n7. Выйти"
        );
        System.out.print("\nВаш ответ: ");
        char answer = (char) sc.nextInt();
        System.out.println();
        switch (answer) {
            case 1: addClient(sc);
                    break;
            case 2: findClientByPetName(sc);
                    break;
            case 3: editing(sc);
                    break;
            case 4: removeClient(sc);
                    break;
            case 5: showClients();
                    break;
            case 6: directory();
                    break;
            case 7: return;
        }
        menu(sc);
    }

    public void addClient(Scanner sc) {
        String answer;
        do {
            System.out.print("\nИмя клиента: ");
            String clientName = sc.next();
            System.out.print("Тип питомца: ");
            String petType = sc.next();
            System.out.print("Имя питомца: ");
            String petName = sc.next();
            System.out.print("Полных лет питомцу: ");
            String age = sc.next();

            this.clients.add(new Client(clientName, new Pet(petType, petName, age)));

            do {
                System.out.print("\nДобавить нового клиента (да/нет)? ");
                answer = sc.next();
            } while (!Objects.equals(answer, "да") && !Objects.equals(answer, "нет"));
        } while (Objects.equals(answer, "да"));
    }

    public void findClientByPetName(Scanner sc) {
        System.out.print("Введите имя клиента или питомца: ");
        String name = sc.next();
        System.out.println();
        for (Client client : this.clients) {
            if (Objects.equals(name, client.getName()) || Objects.equals(name, client.getPet().getName())) {
                output(client);
            }
        }
    }

    public int inputEditing(Scanner sc) {
        String answerEditing;
        char answer = 0;
        do {
            System.out.print("\nВы хотите редактировать данные этого клиента (да/нет)? ");
            answerEditing = sc.next();
        } while (!Objects.equals(answerEditing, "да")
                && !Objects.equals(answerEditing, "нет")
                && !Objects.equals(answerEditing, "отмена"));
        if (Objects.equals(answerEditing, "да")) {
            System.out.println(
                    "\n\033[30m<-- ВВЕДИТЕ ЦИФРУ -->\033[0m"
                            + "\nРедактировать: " +
                            "\n    1. Имя клиента" +
                            "\n    2. Тип питомца" +
                            "\n    3. Имя питомца" +
                            "\n    4. Полные года питомца"
            );
            System.out.print("\nВаш ответ: ");
            answer = (char) sc.nextInt();
        }
        return answer;
    }

    public void editing(Scanner sc) {
        showClients();
        System.out.println();
        System.out.print("Введите имя клиента или питомца: ");
        String name = sc.next();
        System.out.println();
        for (Client client : this.clients) {
            if (Objects.equals(name, client.getName()) || Objects.equals(name, client.getPet().getName())) {
                output(client);

                switch (inputEditing(sc)) {
                    case 1:
                        System.out.print("\nВведите имя клиента: ");
                        String clientName = sc.next();
                        client.setName(clientName);
                        break;
                    case 2:
                        System.out.print("\nВведите тип питомца: ");
                        String petType = sc.next();
                        client.getPet().setPetType(petType);
                        break;
                    case 3:
                        System.out.print("\nВведите имя питомца: ");
                        String petName = sc.next();
                        client.getPet().setName(petName);
                        break;
                    case 4:
                        System.out.print("\nВведите возраст питомца: ");
                        String petAge = sc.next();
                        client.getPet().setAge(petAge);
                        break;
                    default:
                        System.out.println();
                }
            }
        }
    }

    public void removeClient(Scanner sc) {
        showClients();
        System.out.println();
        System.out.print("Введите имя клиента или питомца: ");
        String name = sc.next();
        if (Objects.equals(name, "отмена"))
            return;
        System.out.println();
        for (Client client : this.clients) {
            if (Objects.equals(name, client.getName()) || Objects.equals(name, client.getPet().getName())) {
                output(client);
                System.out.print("\nУдалить (да/нет)? ");
                String answer = sc.next();
                if (Objects.equals(answer, "да")) {
                    this.clients.remove(client);
                    menu(sc);
                    return;
                }
            }
        }
    }

    public void showClients() {
        for (Client client : this.clients) {
                output(client);
        }
    }

    public void directory() {
        System.out.println("\033[1;37m<<<\033[0m Данная программа содержит информацию о клиентах \033[1;37m>>>" +
                                   "\n<<<\033[0m клиники домашних животных. Внесите пожалуйста   \033[1;37m>>>" +
                                   "\n<<<\033[0m данные о имени клиента и его питомце.           \033[1;37m>>>" +
                                   "\n<<<\033[0m После ввода данных, вам выпадет меню            \033[1;37m>>>" +
                                   "\n<<<\033[0m и вы сможете варьировать введенными данными     \033[1;37m>>>" +
                                   "\n<<<\033[0m выбрав что-нибудь из списка меню.               \033[1;37m>>>" +
                   "\n<<<\033[1m       Если передумаете, напишите \"\033[4mотмена\033[0m\"       \033[1;37m>>>\033[0m"
        );
    }

    public void output(Client client) {
        System.out.println(
                "Клиент: \033[4m" + client.getName() +
                "\033[0m, " + client.getPet().getPetType() + ": \033[4m" + client.getPet().getName() +
                "\033[0m. Полных лет питомцу: \033[4m" + client.getPet().getAge() + "\033[0m"
        );
    }
}