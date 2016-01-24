package ru.clinicLesson.clinic;

import java.util.*;

public class Clinic {

    private final List<Client> clients;

    public Clinic() {
        this.clients = new ArrayList<>();
    }

    public void output(Client client) {
        System.out.println(
                "Хозяин: " + client.getId() +
                        ", Питомец: " + client.getPet().getName() +
                        ", Полных лет питомцу: " + client.getPet().getAge()
        );
    }

    public void menu(Scanner sc) {
        String answer;
        System.out.println(
                "\n1. Внести данные"
              + "\n2. Поиск"
              + "\n3. Редактировать данные"
              + "\n4. Удалить данные"
              + "\n5. Вывести все данные"
              + "\n6. Выйти"
        );
        System.out.print("\nОтвет: ");
        answer = sc.next();
        System.out.println();
        switch (answer) {
            case "1":
                addClient(sc);
                menu(sc);
                break;
            case "2":
                if (!clients.isEmpty()) {
                    findClientByPetName(sc);
                    menu(sc);
                }
                else {
                    System.out.println("\nВнесите данные!");
                    menu(sc);
                }
                break;
            case "3":
                if (!clients.isEmpty()) {
                    editing(sc);
                    menu(sc);
                }
                else {
                    System.out.println("\nВнесите данные!");
                    menu(sc);
                }
                break;
            case "4":
                if (!clients.isEmpty()) {
                    removeClient(sc);
                    menu(sc);
                }
                else {
                    System.out.println("\nВнесите данные!");
                    menu(sc);
                }
                break;
            case "5":
                if (!clients.isEmpty()) {
                    showClients();
                    menu(sc);
                }
                else {
                    System.out.println("\nДанные отсутствуют!");
                    menu(sc);
                }
                break;
            case "6": break;
            default:
                System.out.println();
                menu(sc);
        }
    }

    public void addClient(Scanner sc) {
        String answer;
        do {
            System.out.print("Имя хозяина: ");
            String clientName = sc.next();
            System.out.print("Имя питомца: ");
            String petName = sc.next();
            System.out.print("Полных лет питомцу: ");
            String age = sc.next();

            clients.add(new Client(clientName, new Pet(petName, age)));

            System.out.print("\nПродолжить (да/нет)? ");
            answer = sc.next();
            System.out.println();
        } while (!Objects.equals(answer, "нет"));
    }

    public void findClientByPetName(Scanner sc) {
        System.out.print("Поиск хозяина или питомца: ");
        String name = sc.next();
        System.out.println();
        for (Client client : clients) {
            if (Objects.equals(name, client.getId()) || Objects.equals(name, client.getPet().getName())) {
                output(client);
            }
        }
    }

    public void editing(Scanner sc) {
        showClients();
        System.out.println();
        System.out.print("Введите имя хозяина или питомца: ");
        String name = sc.next();
        System.out.println();
        for (Client client : clients) {
            if (Objects.equals(name, client.getId()) || Objects.equals(name, client.getPet().getName())) {
                output(client);

                System.out.print("\nРедактировать (да/нет)? ");
                String answer = sc.next();
                if (Objects.equals(answer, "да")) {
                    System.out.println(
                            "Редактировать: " +
                                    "\n    1. Имя хозяина" +
                                    "\n    2. Имя питомца" +
                                    "\n    3. Полные года питомца"
                    );
                    System.out.print("\nОтвет: ");
                    answer = sc.next();
                    switch (answer) {
                        case "1":
                            System.out.print("\nВведите имя хозяина: ");
                            String clientName = sc.next();
                            client.setId(clientName);
                            break;
                        case "2":
                            System.out.print("\nВведите имя питомца: ");
                            String petName = sc.next();
                            client.getPet().setName(petName);
                            break;
                        case "3":
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
    }

    public void removeClient(Scanner sc) {
        showClients();
        System.out.println();
        System.out.print("Введите имя клиента или питомца: ");
        String name = sc.next();
        System.out.println();
        for (Client client : clients) {
            if (Objects.equals(name, client.getId()) || Objects.equals(name, client.getPet().getName())) {
                output(client);
                System.out.print("\nУдалить (да/нет)? ");
                String answer = sc.next();
                if (Objects.equals(answer, "да")) {
                    clients.remove(client);
                    menu(sc);
                    return;
                }
            }
        }
    }

    public void showClients() {
        for (Client client : clients) {
                output(client);
        }
    }
}