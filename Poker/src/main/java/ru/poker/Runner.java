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

    // Дисквалификация игрока, в случае если не хватает денег для резервной ставки
    public boolean disqualification(Table table, Gamer gamer) {
        if (gamer.getMoney() < table.getReserveMoney()) {
            gamer.setInGame(false);
            table.inscription("\033[1;31m" +
                    " ИГРОК \"" + gamer.getId() + ". " + gamer.getName() + "\" ДИСКВАЛИФИЦИРОВАН " + "\033[0m");
            return true;
        }
        return false;
    }

    public void progress(Scanner sc, BaseAction action, Table table) {
        action.progress();
//        while (table.getSumCartOnTable() != 5) {

//            action.progress();
//        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final Runner runner = new Runner();
        runner.generate(sc);
    }
}
