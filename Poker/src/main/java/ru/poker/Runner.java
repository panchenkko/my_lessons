package ru.poker;

import ru.poker.Classes.Cart;
import ru.poker.Classes.Gamer;
import ru.poker.Classes.Table;
import ru.poker.Interfaces.IGenerator;

import java.util.Scanner;

public class Runner {

    public void generate(final Scanner sc) {
        final Table table = new Table();
        table.inputPurse(sc);
        final BaseAction action = new BaseAction(table, new IGenerator() {
            @Override
            public Cart[] generateDeck() {
                return emptyData();
            }

            @Override
            public Gamer[] generateGamers() {
                return inputGamers(sc);
            }
        });
        action.initGame();
        progress(action, table);
    }

    public Cart[] emptyData() {
        Cart[] deck = new Cart[52];
        for (int i = 0; i < deck.length; i++) {
            deck[i] = new Cart("", "", false);
        }
        return deck;
    }

    public Gamer[] inputGamers(Scanner sc) {
        System.out.print("Введите кол-во игроков за столом: ");
        int sum = sc.nextInt();

        Gamer[] gamers = new Gamer[sum];

        int id = 0;
        while (id < sum) {
            System.out.println();
            System.out.println("======== ВВЕДИТЕ ИНФОРМАЦИЮ ПРО НОВОГО ИГРОКА ========");
            System.out.println();

            System.out.print("Имя: ");
            String name = sc.next();

            System.out.print("Количество денег ($) : ");
            int money = sc.nextInt();

            gamers[id] = new Gamer(++id, name, money, true);
        }
        return gamers;
    }

    public void progress(BaseAction action, Table table) {
//        while (table.getSumCartOnTable() != 5) {
//            action.progress();
//        }
        action.progress();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final Runner runner = new Runner();
        runner.generate(sc);
    }
}
