package ru.poker.Classes;

import ru.poker.*;

import java.util.Random;
import java.util.Scanner;

public class Table {

    private Gamer[] gamers; // Список игроков
    private Cart[] deck; // Колода

    private int sumCartOnTable = 0; // Сколько на данный момент карт на столе
    private int purse; // Сумма входа (Минимальная сумма, какая должна быть у игрока)

    private int reserveMoney = 0; // Минимальная сумма какую должен внести игрок

    private int bank = 0; // Банк (достанется игроку, какой выиграл партию)
    private int numCircle = 0; // Номер круга игры (определяет сколько карточек отображать на столе)

    private int[] winners; // игроки, какие выиграли партию

    // Карточки на стол
    private Cart cart1;
    private Cart cart2;
    private Cart cart3;
    private Cart cart4;
    private Cart cart5;

    public void loadGamers(Gamer[] gamers) {
        this.gamers = gamers;
    }

    public void loadDeck(Cart[] deck) {
        this.deck = deck;
    }

    public Gamer[] getGamers() {
        return gamers;
    }

    public int getSumCartOnTable() {
        return sumCartOnTable;
    }

    public int getReserveMoney() {
        return reserveMoney;
    }

    public void setPurse(int purse) {
        this.purse = purse;
    }

    public void setReserveMoney(int reserveMoney) {
        this.reserveMoney = reserveMoney;
    }

    // Инициализация карточек
    public void initializationCart1() {
        this.cart1 = new Cart("", 0, "", false);
    }
    public void initializationCart2() {
        this.cart2 = new Cart("", 0, "", false);
    }
    public void initializationCart3() {
        this.cart3 = new Cart("", 0, "", false);
    }
    public void initializationCart4() {
        this.cart4 = new Cart("", 0, "", false);
    }
    public void initializationCart5() {
        this.cart5 = new Cart("", 0, "", false);
    }

    // Создаём колоду карт
    public void writingDeck() {
        for (int i = 0; i < this.deck.length; i++) {
            this.deck[i].setInUse(false);
            heart(i);
            diamonds( i);
            club(i);
            pike(i);
        }
    }
    public void heart(int i) {
        if (i >= 0 && i < 13 ) {
            this.deck[i].setSuit("\033[1;31;40m♥\033[1;40m");
            if (i < 9) {
                this.deck[i].setValue("\033[1;31;40m" + String.valueOf(i + 2) + "\033[1;40m");
                this.deck[i].setValueNum(i + 2);
            }
            switch (i) {
                case 9:  this.deck[i].setValue("\033[1;31;40m" + "В" + "\033[1;40m");
                         this.deck[i].setValueNum(11);
                         break;
                case 10: this.deck[i].setValue("\033[1;31;40m" + "Д" + "\033[1;40m");
                         this.deck[i].setValueNum(12);
                         break;
                case 11: this.deck[i].setValue("\033[1;31;40m" + "К" + "\033[1;40m");
                         this.deck[i].setValueNum(13);
                         break;
                case 12: this.deck[i].setValue("\033[1;31;40m" + "Т" + "\033[1;40m");
                         this.deck[i].setValueNum(14);
                         break;
            }
        }
    }
    public void diamonds(int i) {
        if (i >= 13 && i < 26 ) {
            this.deck[i].setSuit("\033[1;31;40m♦\033[1;40m");
            if (i < 22) {
                deck[i].setValue("\033[1;31;40m" + String.valueOf(i - 11) + "\033[1;40m");
                this.deck[i].setValueNum(i - 11);
            }
            switch (i) {
                case 22: this.deck[i].setValue("\033[1;31;40m" + "В" + "\033[1;40m");
                         this.deck[i].setValueNum(11);
                         break;
                case 23: this.deck[i].setValue("\033[1;31;40m" + "Д" + "\033[1;40m");
                         this.deck[i].setValueNum(12);
                         break;
                case 24: this.deck[i].setValue("\033[1;31;40m" + "К" + "\033[1;40m");
                         this.deck[i].setValueNum(13);
                         break;
                case 25: this.deck[i].setValue("\033[1;31;40m" + "Т" + "\033[1;40m");
                         this.deck[i].setValueNum(14);
                         break;
            }
        }
    }
    public void club(int i) {
        if (i >= 26 && i < 39 ) {
            this.deck[i].setSuit("\033[1;37;40m♣\033[1;40m");
            if (i < 35) {
                this.deck[i].setValue("\033[1;37;40m" + String.valueOf(i - 24) + "\033[1;40m");
                this.deck[i].setValueNum(i - 24);
            }
            switch (i) {
                case 35: this.deck[i].setValue("\033[1;37;40m" + "В" + "\033[1;40m");
                         this.deck[i].setValueNum(11);
                         break;
                case 36: this.deck[i].setValue("\033[1;37;40m" + "Д" + "\033[1;40m");
                         this.deck[i].setValueNum(12);
                         break;
                case 37: this.deck[i].setValue("\033[1;37;40m" + "К" + "\033[1;40m");
                         this.deck[i].setValueNum(13);
                         break;
                case 38: this.deck[i].setValue("\033[1;37;40m" + "Т" + "\033[1;40m");
                         this.deck[i].setValueNum(14);
                         break;
            }
        }
    }
    public void pike(int i) {
        if (i >= 39 && i < 52 ) {
            this.deck[i].setSuit("\033[1;37;40m♠\033[1;40m");
            if (i < 48) {
                this.deck[i].setValue("\033[1;37;40m" + String.valueOf(i - 37) + "\033[1;40m");
                this.deck[i].setValueNum(i - 37);
            }
            switch (i) {
                case 48: this.deck[i].setValue("\033[1;37;40m" + "В" + "\033[1;40m");
                         this.deck[i].setValueNum(11);
                         break;
                case 49: this.deck[i].setValue("\033[1;37;40m" + "Д" + "\033[1;40m");
                         this.deck[i].setValueNum(12);
                         break;
                case 50: this.deck[i].setValue("\033[1;37;40m" + "К" + "\033[1;40m");
                         this.deck[i].setValueNum(13);
                         break;
                case 51: this.deck[i].setValue("\033[1;37;40m" + "Т" + "\033[1;40m");
                         this.deck[i].setValueNum(14);
                         break;
            }
        }
    }

    // Проверка суммы денег у игроков (Достаточна ли она для входа в игру)
    public void checkMoneyInGamers() {
        for (Gamer gamer : this.gamers) {
            if (gamer.getMoney() < this.purse)
                gamer.setInGame(false);
        }
    }

    // Снимаем начальную сумму с каждого игрока
    public void initialAmount() {
        inscription(" СТАРТОВАЯ СУММА ");
        for (Gamer gamer : this.gamers) {
            System.out.println(gamer.getId() + ". " + gamer.getName() + ": " + -this.purse);
            gamer.setMoney(gamer.getMoney() - this.purse);
            this.bank += this.purse;
        }
        ThreadSleep.sleep(2000);
    }

    // Титулка
    public void inscription(String inscription) {
        System.out.println();
        System.out.println(String.format("==============%s==============", inscription));
        System.out.println();
    }

    // Раздаём карты игрокам
    public void randomCartForGamer() {
        for (Gamer gamer : this.gamers) {
            gamer.randomCart(this.deck);
        }
    }

    // Игроки вошедшие за стол
    public void drawGamersInGame() {
        inscription(" ИГРОКИ ЗА СТОЛОМ ");
        for (Gamer gamer : this.gamers) {
            if (gamer.isInGame()) {
                System.out.println(gamer.informationInGame());
            }
        }
    }

    // Игроки не вошедшие за стол
    public void drawGamersOutGame() {
        inscription(" ИГРОКИ НЕ ВОШЕДШИЕ ЗА СТОЛ ");
        int sum = 0;
        for (Gamer gamer : this.gamers) {
            if (!gamer.isInGame()) {
                sum++;
                System.out.println(gamer.informationAll());
            }
        }
        if (sum == 0) System.out.println("За стол сели все игроки.");
        ThreadSleep.sleep(2000);
    }

    public void listAllGamers() {
        inscription(" СПИСОК ВСЕХ ИГРОКОВ ");
        for (Gamer gamer : this.gamers) {
            System.out.println(gamer.informationInEndGame());
        }
    }

    // Скидываем резервную сумму и ставки всех игроков
    public void clearRate() {
        this.reserveMoney = 0;
        for (Gamer gamer : this.gamers) {
            gamer.setRate(0);
        }
    }

    // Проверка на то, что ещё как минимум 2 игрока могут продолжать ставить ставки
    public int checkNotTheEndGame() {
        int inc = 0;
        for (Gamer gamer : this.gamers) {
            if (gamer.isInGame() && (gamer.getMoney() > 0 || gamer.getRate() > 0))
                inc++;
        }
        return inc;
    }

    public void checkCombination(Gamer gamer) {
        final Combination combination = new Combination();
        int royalFlush = combination.royalFlush(gamer.getOneCart(), gamer.getTwoCart(),
                         this.cart1, this.cart2, this.cart3, this.cart4, this.cart5);
        int straightFlush = combination.straightFlush(gamer.getOneCart(), gamer.getTwoCart(),
                            this.cart1, this.cart2, this.cart3, this.cart4, this.cart5);
        int fourOfAKind = combination.fourOfAKind(gamer.getOneCart(), gamer.getTwoCart(),
                          this.cart1, this.cart2, this.cart3, this.cart4, this.cart5);
        int fullHouse = combination.fullHouse(gamer.getOneCart(), gamer.getTwoCart(),
                this.cart1, this.cart2, this.cart3, this.cart4, this.cart5);
        int flush = combination.flush(gamer.getOneCart(), gamer.getTwoCart(),
                this.cart1, this.cart2, this.cart3, this.cart4, this.cart5);
        int straight = combination.straight(gamer.getOneCart(), gamer.getTwoCart(),
                this.cart1, this.cart2, this.cart3, this.cart4, this.cart5);
        int threeOfAKind = combination.threeOfAKind(gamer.getOneCart(), gamer.getTwoCart(),
                this.cart1, this.cart2, this.cart3, this.cart4, this.cart5);
        int twoPairs = combination.twoPairs(gamer.getOneCart(), gamer.getTwoCart(),
                this.cart1, this.cart2, this.cart3, this.cart4, this.cart5);
        int onePair = combination.onePair(gamer.getOneCart(), gamer.getTwoCart(),
                this.cart1, this.cart2, this.cart3, this.cart4, this.cart5);
        int highCards = combination.highCards(gamer.getOneCart(), gamer.getTwoCart());

        /**
         * Проводим сравнение. У какого игрока больше нумерация, тот и выиграл!
         */
             if (royalFlush > 0) {
                 gamer.setStore(royalFlush);
                 gamer.setStoreName("ФЛЕШ РОЯЛЬ");
             }
        else if (straightFlush > 0) {
                 gamer.setStore(straightFlush);
                 gamer.setStoreName("СТРИТ ФЛЕШ");
             }
        else if (fourOfAKind > 0) {
                 gamer.setStore(fourOfAKind);
                 gamer.setStoreName("КАРЕ");
             }
        else if (fullHouse > 0) {
                 gamer.setStore(fullHouse);
                 gamer.setStoreName("ФУЛЛ ХАУС");
             }
        else if (flush > 0) {
                 gamer.setStore(flush);
                 gamer.setStoreName("ФЛЕШ");
             }
        else if (straight > 0) {
                 gamer.setStore(straight);
                 gamer.setStoreName("СТРИТ");
             }
        else if (threeOfAKind > 0) {
                 gamer.setStore(threeOfAKind);
                 gamer.setStoreName("ТРОЙКА");
             }
        else if (twoPairs > 0) {
                 gamer.setStore(twoPairs);
                 gamer.setStoreName("ДВЕ ПАРЫ");
             }
        else if (onePair > 0) {
                 gamer.setStore(onePair);
                 gamer.setStoreName("ПАРА");
             }
        else if (highCards > 0) {
                 gamer.setStore(highCards);
                 gamer.setStoreName("КИКЕР");
             }
    }

    // Получаем id игрока, у какого самая высокая комбинация за столом
    public int returnWinner() {
        int id = 0;
        for (int i = 1; i < this.gamers.length; i++) {
            if (this.gamers[i].getStore() > this.gamers[id].getStore()) {
                id = this.gamers[i].getId();
            }
        }
        return id;
    }

    // Возвращаем количество игроков, у каких одинаковая комбинация
    public void checkMatches() {
        Gamer gamer = this.gamers[returnWinner()];
        int matches = 1;
        for (Gamer gamers : this.gamers) {
            if (gamers.getStore() == gamer.getStore() && gamers.getId() != gamer.getId()) {
                matches++;
            }
        }
        // записываем в массив id игроков, какие выиграли
        this.winners = new int[matches];
        int inc = 0;
        for (Gamer gamers : this.gamers) {
            if (gamers.getStore() == gamer.getStore()) {
                this.winners[inc] = gamers.getId();
                inc++;
            }
        }
//        int matches = 0;
//        for (int i = 0; i < this.gamers.length; i++) {
//            for (int j = i; j < this.gamers.length; j++) {
//                if (this.gamers[i].getStore() == this.gamers[j].getStore())
//                    matches++;
//            }
//        }
    }

    // Выводим список победителей
    public void drawWinners() {
        inscription(" ПОБЕДИТЕЛИ ");
        // Делим банк на всех победителей
        int shareGain = this.bank / this.winners.length;
        for (int id : this.winners) {
            for (Gamer gamer : this.gamers) {
                if (gamer.getId() == id) {
                    System.out.println(gamer.getId() + ". " + gamer.getName());
                    System.out.println("КОМБИНАЦИЯ: " + "\033[1;32m" + gamer.getStoreName() + "\033[0m");
                    System.out.println("ВЫИГРЫШ: " + gamer.getMoney() + " + " + "\033[1;32m" + shareGain + "\033[0m");
                    gamer.setMoney(gamer.getMoney() + shareGain);
                }
            }
        }
        this.bank = 0;
    }

    public void finish() {
        if (this.bank > 0) {
            // Записываем каждому игроку, какая у него комбинация
            for (Gamer gamer : this.gamers) {
                checkCombination(gamer);
            }
        }
        checkMatches();
        drawWinners();
    }

    // Ход каждого игрока
    public void gamerInput() {
        Scanner sc = new Scanner(System.in);
        boolean raise = false, check = false, call = false, fold = false;
        int id = 0;
        int counter = id;
        do {
            if (this.gamers[counter].isInGame()) {
                if (checkNotTheEndGame() >= 2) {
                    while (!raise && !check && !call && !fold) {
                        inscription(" СОСТОЯНИЕ ДРУГИХ ИГРОКОВ ");
                        for (Gamer gamer : this.gamers) {
                            if (gamer.getId() != this.gamers[counter].getId()) {
                                System.out.println(gamer.informationAll());
                            }
                        }
                        ThreadSleep.sleep(1500);
                        drawTable();
                        inscription(" ИГРОК \"" +
                                this.gamers[counter].getId() + ". " +
                                this.gamers[counter].getName() + "\" ");
                        System.out.println("Ваши деньги: " + this.gamers[counter].getMoney());
                        System.out.println("Уже поставлено: " + gamers[counter].getRate());
                        System.out.println("Текущая ставка: " + getReserveMoney());
                        System.out.println();
                        System.out.println(this.gamers[counter].drawCartLogic());
                        System.out.println("1. Повысить ставку" + "\n" +
                                           "2. Пропустить" + "\n" +
                                           "3. Поддержать" + "\n" +
                                           "4. Пас" + "\n");
                        System.out.print("Ответ: ");
                        int answer = sc.nextInt();
                        switch (answer) {
                            case 1: raise = raise(sc, this.gamers[counter]);
                                    id = counter;
                                    break;
                            case 2: check = check(this.gamers[counter]);
                                    break;
                            case 3: call = call(this.gamers[counter]);
                                    break;
                            case 4: fold = fold(this.gamers[counter]);
                                    break;
                        }
                    }
                } else {
                    while (this.numCircle <= 2) {
                        drawTable();
                        this.numCircle++;
                    }
                    if (this.gamers[counter].isInGame() && this.gamers[counter].getMoney() > 0) {
                        inscription("\033[1;32m" + " ИГРОК \"" +
                                this.gamers[counter].getId() + ". " +
                                this.gamers[counter].getName() + "\" ЗАБИРАЕТ ВСЮ СУММУ " + "\033[0m");
                        this.gamers[counter].setMoney(this.gamers[counter].getMoney() + this.bank);
                        this.bank = 0;
                    }
                    return;
                }
                raise = false; check = false; call = false; fold = false;
            }
            counter++;
            if (counter == this.gamers.length) counter = 0;
        } while (counter != id);
        this.numCircle++;
        if (this.numCircle < 3) clearRate();
    }

    // Игрок повышает ставку
    public boolean raise(Scanner sc, Gamer gamer) {
        System.out.println("Минимальная ставка: " + (getReserveMoney() + 1));
        System.out.println();
        System.out.print("Ставка: ");
        int rate = sc.nextInt();
        if (rate > getReserveMoney() - gamer.getRate()) {
            if (gamer.getMoney() >= rate - gamer.getRate()) {
                setReserveMoney(rate);
                gamer.setMoney(gamer.getMoney() - (rate - gamer.getRate()));
                gamer.setRate(rate);
                this.bank += gamer.getRate();
                return true;
            } else {
                inscription("\033[1;31m" + " НЕ ХВАТАЕТ ДЕНЕГ " + "\033[0m");
                gamer.setRate(0);
                ThreadSleep.sleep(1500);
                return false;
            }
        }
        else {
            inscription("\033[1;31m" + " ВАША СТАВКА ДОЛЖНА БЫТЬ БОЛЬШЕ " + "\033[0m");
            gamer.setRate(0);
            ThreadSleep.sleep(1500);
            return false;
        }
    }

    // Игрок пропускает ход
    public boolean check(Gamer gamer) {
        if (gamer.getRate() == getReserveMoney())
            return true;
        else {
            inscription("\033[1;31m" + " ВЫ МОЖЕТЕ ТОЛЬКО ПОДДЕРЖАТЬ СТАВКУ " + "\033[0m");
            ThreadSleep.sleep(1500);
            return false;
        }
    }

    // Игрок поддерживает ставку
    public boolean call(Gamer gamer) {
        if (gamer.getMoney() >= getReserveMoney() - gamer.getRate()) {
            gamer.setMoney(gamer.getMoney() - (getReserveMoney() - gamer.getRate()));
            this.bank += getReserveMoney() - gamer.getRate();
            gamer.setRate(getReserveMoney());
            inscription("\033[1;32m" + " ВЫ УСПЕШНО ПОДДЕРЖАЛИ СТАВКУ " + "\033[0m");
            ThreadSleep.sleep(1000);
            return true;
        } else {
            inscription("\033[1;31m" + " НЕ ХВАТАЕТ ДЕНЕГ " + "\033[0m");
            ThreadSleep.sleep(1500);
            return false;
        }
    }

    // Игрок выходит с партии
    public boolean fold(Gamer gamer) {
        gamer.setInGame(false);
        inscription(
                "\033[1;31m" + " ИГРОК \"" + gamer.getId() + ". " + gamer.getName() + "\" ВЫШЕЛ ИЗ ИГРЫ " + "\033[0m");
        ThreadSleep.sleep(1500);
        return true;
    }

    // Дисквалификация игрока, в случае если не хватает денег для резервной ставки
    public boolean disqualification(Table table, Gamer gamer) {
        if (gamer.getMoney() < table.getReserveMoney()) {
            gamer.setInGame(false);
            table.inscription("\033[1;31m" +
                    " ИГРОК \"" + gamer.getId() + ". " + gamer.getName() + "\" ДИСКВАЛИФИЦИРОВАН " + "\033[0m");
            ThreadSleep.sleep(1500);
            return true;
        }
        return false;
    }

    public void drawTable() {
        if (this.numCircle <= 2) {
            inscription("===============СТОЛ==============");
            switch (this.numCircle) {
                case 0: System.out.print(draw3CartLogic());
                        break;
                case 1: if (this.cart4 == null) random4CartForTable();
                        System.out.print(draw4CartLogic());
                        break;
                case 2: if (this.cart5 == null) random5CartForTable();
                        System.out.print(draw5CartLogic());
                        break;
            }
            inscription("===============СТОЛ==============");
            System.out.println("ТЕКУЩИЙ БАНК: " + this.bank);
            ThreadSleep.sleep(1500);
        } else {
            this.numCircle = 0;
            inscription("\033[1;32m" + " КОНЕЦ ПАРТИИ " + "\033[0m");
        }
    }

    // Случайные карты на стол
    public void random3CartForTable() {
        this.sumCartOnTable = 3;
        int inc = 0;
        while (inc < this.sumCartOnTable || this.cart1 == null || this.cart2 == null || this.cart3 == null) {
            int rand = new Random().nextInt(51) + 1;
            if (!this.deck[rand].isInUse()) {
                if (this.cart1 == null) {
                    initializationCart1();
                    this.cart1.setSuit(this.deck[rand].getSuit());
                    this.cart1.setValue(this.deck[rand].getValue());
                    this.deck[rand].setInUse(true);
                } else
                if (this.cart2 == null) {
                    initializationCart2();
                    this.cart2.setSuit(this.deck[rand].getSuit());
                    this.cart2.setValue(this.deck[rand].getValue());
                    this.deck[rand].setInUse(true);
                } else
                if (this.cart3 == null) {
                    initializationCart3();
                    this.cart3.setSuit(this.deck[rand].getSuit());
                    this.cart3.setValue(this.deck[rand].getValue());
                    this.deck[rand].setInUse(true);
                }
            }
            inc++;
        }
    }
    public void random4CartForTable() {
        this.sumCartOnTable = 4;
        int inc = 0;
        while (inc < this.sumCartOnTable || this.cart4 == null) {
            int rand = new Random().nextInt(51) + 1;
            if (!this.deck[rand].isInUse()) {
                initializationCart4();
                this.cart4.setSuit(this.deck[rand].getSuit());
                this.cart4.setValue(this.deck[rand].getValue());
                this.deck[rand].setInUse(true);
            }
            inc++;
        }
    }
    public void random5CartForTable() {
        this.sumCartOnTable = 5;
        int inc = 0;
        while (inc < this.sumCartOnTable || this.cart5 == null) {
            int rand = new Random().nextInt(51) + 1;
            if (!this.deck[rand].isInUse()) {
                initializationCart5();
                this.cart5.setSuit(this.deck[rand].getSuit());
                this.cart5.setValue(this.deck[rand].getValue());
                this.deck[rand].setInUse(true);
            }
            inc++;
        }
    }

    // ЛОГИКА ВЫВОДА ТРЁХ КАРТ
    public String draw3CartLogic() {
        if ((this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") ||
             this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart2.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") ||
             this.cart2.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") ||
             this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw3Cart_123_10();
        else
        if ((this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") ||
             this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart2.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") ||
             this.cart2.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw3Cart_12_10();
        else
        if ((this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") ||
             this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") ||
             this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw3Cart_13_10();
        else
        if ((this.cart2.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") ||
             this.cart2.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") ||
             this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw3Cart_23_10();
        else if (this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") ||
                 this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) return draw3Cart_1_10();
        else if (this.cart2.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") ||
                 this.cart2.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) return draw3Cart_2_10();
        else if (this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") ||
                 this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) return draw3Cart_3_10();
        else return draw3CartStandard();
    }

    public String draw3Cart_123_10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue());
    }
    public String draw3Cart_12_10() {
        return String.format(
                "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue());
    }
    public String draw3Cart_13_10() {
        return String.format(
                "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue());
    }
    public String draw3Cart_23_10() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue());
    }
    public String draw3Cart_1_10() {
        return String.format(
                "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue());
    }
    public String draw3Cart_2_10() {
        return String.format(
                "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue());
    }
    public String draw3Cart_3_10() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue());
    }
    public String draw3CartStandard() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue());
    }

    // ЛОГИКА ВЫВОДА ЧЕТЫРЁХ КАРТ
    public String draw4CartLogic() {
        if ((this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart2.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart2.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart4.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart4.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw4Cart_1234_10();
        else
        if ((this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart2.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart2.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw4Cart_123_10();
        else
        if ((this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart2.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart2.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart4.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart4.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw4Cart_124_10();
        else
        if ((this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart4.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart4.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw4Cart_134_10();
        else
        if ((this.cart2.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart2.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart4.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart4.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw4Cart_234_10();
        else
        if ((this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart2.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart2.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw4Cart_12_10();
        else
        if ((this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw4Cart_13_10();
        else
        if ((this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart4.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart4.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw4Cart_14_10();
        else
        if ((this.cart2.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart2.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw4Cart_23_10();
        if ((this.cart2.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart2.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart4.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart4.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw4Cart_24_10();
        if ((this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart4.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart4.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw4Cart_34_10();
        else if (this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) return draw4Cart_1_10();
        else if (this.cart2.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart2.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) return draw4Cart_2_10();
        else if (this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) return draw4Cart_3_10();
        else if (this.cart4.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart4.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) return draw4Cart_4_10();
        else return draw4CartStandard();
    }

    public String draw4Cart_1234_10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue());
    }
    public String draw4Cart_123_10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue());
    }
    public String draw4Cart_124_10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue());
    }
    public String draw4Cart_134_10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue());
    }
    public String draw4Cart_234_10() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue());
    }
    public String draw4Cart_12_10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue());
    }
    public String draw4Cart_13_10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue());
    }
    public String draw4Cart_14_10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue());
    }
    public String draw4Cart_23_10() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue());
    }
    public String draw4Cart_24_10() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue());
    }
    public String draw4Cart_34_10() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue());
    }
    public String draw4Cart_1_10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue());
    }
    public String draw4Cart_2_10() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue());
    }
    public String draw4Cart_3_10() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue());
    }
    public String draw4Cart_4_10() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue());
    }
    public String draw4CartStandard() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue());
    }

    // ЛОГИКА ВЫВОДА ПЯТИ КАРТ
    public String draw5CartLogic() {
        if ((this.cart2.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart2.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart4.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart4.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart5.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart5.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw5Cart_2345_10();
        else
        if ((this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart4.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart4.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart5.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart5.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw5Cart_1345_10();
        else
        if ((this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart2.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart2.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart4.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart4.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart5.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart5.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw5Cart_1245_10();
        else
        if ((this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart2.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart2.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart5.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart5.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw5Cart_1235_10();
        else
        if ((this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart2.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart2.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart4.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart4.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw5Cart_1234_10();
        else
        if ((this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart2.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart2.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw5Cart_123_10();
        else
        if ((this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart2.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart2.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart4.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart4.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw5Cart_124_10();
        else
        if ((this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart2.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart2.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart5.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart5.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw5Cart_125_10();
        else
        if ((this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart4.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart4.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw5Cart_134_10();
        else
        if ((this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart5.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart5.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw5Cart_135_10();
        else
        if ((this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart4.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart4.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart5.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart5.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw5Cart_145_10();
        else
        if ((this.cart2.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart2.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart4.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart4.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw5Cart_234_10();
        else
        if ((this.cart2.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart2.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart5.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart5.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw5Cart_235_10();
        else
        if ((this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart4.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart4.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart5.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart5.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw5Cart_345_10();
        else
        if ((this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart2.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart2.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw5Cart_12_10();
        else
        if ((this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw5Cart_13_10();
        else
        if ((this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart4.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart4.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw5Cart_14_10();
        else
        if ((this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart5.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart5.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw5Cart_15_10();
        else
        if ((this.cart2.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart2.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw5Cart_23_10();
        else
        if ((this.cart2.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart2.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart4.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart4.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw5Cart_24_10();
        else
        if ((this.cart2.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart2.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart5.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart5.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw5Cart_25_10();
        else
        if ((this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart4.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart4.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw5Cart_34_10();
        else
        if ((this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart5.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart5.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw5Cart_35_10();
        else
        if ((this.cart4.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart4.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) &&
            (this.cart5.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart5.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")))
            return draw5Cart_45_10();
        else if (this.cart1.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart1.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) return draw5Cart_1_10();
        else if (this.cart2.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart2.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) return draw5Cart_2_10();
        else if (this.cart3.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart3.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) return draw5Cart_3_10();
        else if (this.cart4.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart4.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) return draw5Cart_4_10();
        else if (this.cart5.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") || this.cart5.getValue().equals("\033[1;37;40m" + "10" + "\033[1;40m")) return draw5Cart_5_10();
        else return draw5CartStandard();
    }

    public String draw5Cart_2345_10() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_1345_10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_1245_10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_1235_10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_1234_10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_123_10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_124_10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_125_10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_134_10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_135_10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_145_10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_234_10() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_235_10() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_345_10() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_12_10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_13_10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_14_10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_15_10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_23_10() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_24_10() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_25_10() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_34_10() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_35_10() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_45_10() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_1_10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_2_10() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_3_10() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_4_10() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5Cart_5_10() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
    public String draw5CartStandard() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart4.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart5.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(), this.cart4.getValue(), this.cart5.getValue());
    }
}