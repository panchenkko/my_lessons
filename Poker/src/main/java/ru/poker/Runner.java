package ru.poker;

import ru.poker.Classes.Cart;
import ru.poker.Classes.Gamer;
import ru.poker.Classes.Table;
import ru.poker.Interfaces.IGenerator;

import java.util.Scanner;

public class Runner {

    public void generate(final Scanner sc) {
        final Table table = new Table();
        inputPurse(sc, table);
        final BaseAction action = new BaseAction(table, new IGenerator() {
            @Override
            public Cart[] generateDeck() {
                return emptyData();
            }

            @Override
            public Gamer[] generateGamers() {
                return inputGamers(sc, table);
            }
        });
        action.initGame();
        progress(sc ,action, table);
    }

    // Заполняем колоду пустыми значениями
    public Cart[] emptyData() {
        Cart[] deck = new Cart[52];
        for (int i = 0; i < deck.length; i++) {
            deck[i] = new Cart("", "", false);
        }
        return deck;
    }

    // Создаем игроков
    public Gamer[] inputGamers(Scanner sc, Table table) {
        System.out.print("Введите кол-во игроков за столом: ");
        int sum = sc.nextInt();

        Gamer[] gamers = new Gamer[sum];

        int id = 0;
        while (id < sum) {
            table.inscription(" ВВЕДИТЕ ИНФОРМАЦИЮ ПРО НОВОГО ИГРОКА ");

            System.out.print("Имя: ");
            String name = sc.next();

            System.out.print("Количество денег ($) : ");
            int money = sc.nextInt();

            gamers[id] = new Gamer(++id, name, money, true);
        }
        return gamers;
    }

    // Ввод начальной суммы входа за стол
    public void inputPurse(Scanner sc, Table table) {
        table.inscription(" ВВЕДИТЕ НАЧАЛЬНУЮ СУММУ ВХОДА ЗА СТОЛ ");
        System.out.print("Сумма: ");
        table.setPurse(sc.nextInt());
    }

    // Ход каждого игрока
    public void gamerInput(Scanner sc, Table table) {
        boolean raise = false, check = false, call = false, fold = false;
        int whoStartCircle = 0;
        for (Gamer gamer : table.getGamers()) {
            if (gamer.isInGame()) {
                while (!raise && !check && !call && !fold) {
                    table.inscription(" ИГРОК \"" + gamer.getId() + ". " + gamer.getName() + "\" ");
                    table.drawTable();
                    System.out.println(gamer.drawCartLogic());
                    System.out.println("1. Повысить ставку" + "\n" +
                                       "2. Пропустить" + "\n" +
                                       "3. Поддержать" + "\n" +
                                       "4. Пас" + "\n");
                    System.out.print("Ответ: ");
                    int answer = sc.nextInt();
                    switch (answer) {
                        case 1: raise = raise(sc, table, gamer);
                                break;
                        case 2: check = check(table, gamer);
                                break;
                        case 3: call = call(table, gamer);
                                break;
                        case 4: fold = fold(table, gamer);
                                break;
                    }
                }
                raise = false; check = false; call = false; fold = false;
            }
        }
    }

    // Игрок повышает ставку
    public boolean raise(Scanner sc, Table table, Gamer gamer) {
        System.out.println("Минимальная ставка: " + (table.getReserveMoney() + 1));
        System.out.println();
        System.out.print("Ставка: ");
        gamer.setRate(sc.nextInt());
        if (gamer.getRate() > table.getReserveMoney()) {
            if (gamer.getMoney() >= gamer.getRate()) {
                gamer.setMoney(gamer.getMoney() - gamer.getRate());
                table.setReserveMoney(gamer.getRate());
                return true;
            } else {
                table.inscription("\033[1;31m" + " НЕ ХВАТАЕТ ДЕНЕГ " + "\033[0m");
                gamer.setRate(0);
                return false;
            }
        }
        else {
            table.inscription("\033[1;31m" + " ВАША СТАВКА ДОЛЖНА БЫТЬ БОЛЬШЕ " + "\033[0m");
            gamer.setRate(0);
            return false;
        }
    }

    // Игрок пропускает ход
    public boolean check(Table table, Gamer gamer) {
        if (gamer.getRate() == table.getReserveMoney())
            return true;
        else {
            table.inscription("\033[1;31m" + " ВЫ МОЖЕТЕ ТОЛЬКО ПОДДЕРЖАТЬ СТАВКУ " + "\033[0m");
            return false;
        }
    }

    // Игрок поддерживает ставку
    public boolean call(Table table, Gamer gamer) {
        if (gamer.getMoney() >= table.getReserveMoney()) {
            gamer.setRate(table.getReserveMoney());
            gamer.setMoney(gamer.getMoney() - gamer.getRate());
            table.inscription("\033[1;32m" + " ВЫ УСПЕШНО ПОДДЕРЖАЛИ СТАВКУ " + "\033[0m");
            return true;
        } else {
            table.inscription("\033[1;31m" + " НЕ ХВАТАЕТ ДЕНЕГ " + "\033[0m");
            return false;
        }
    }

    // Игрок выходит с партии
    public boolean fold(Table table, Gamer gamer) {
        gamer.setInGame(false);
        table.inscription(
                "\033[1;31m" + " ИГРОК \"" + gamer.getId() + ". " + gamer.getName() + "\" ВЫШЕЛ ИЗ ИГРЫ " + "\033[0m");
        return true;
    }

    public void progress(Scanner sc, BaseAction action, Table table) {
        action.progress();
//        while (table.getSumCartOnTable() != 5) {
        gamerInput(sc, table);
//            action.progress();
//        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final Runner runner = new Runner();
        runner.generate(sc);
    }
}
