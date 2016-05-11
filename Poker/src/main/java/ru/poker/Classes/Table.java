package ru.poker.Classes;
public class Table {

    private Gamer[] gamers;
    private Cart[] deck;

    public void loadGamers(Gamer[] gamers) {
        this.gamers = gamers;
    }

    public void loadDeck(Cart[] deck) {
        this.deck = deck;
    }

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
            }
            switch (i) {
                case 9:  this.deck[i].setValue("\033[1;31;40m" + "В" + "\033[1;40m");
                         break;
                case 10: this.deck[i].setValue("\033[1;31;40m" + "Д" + "\033[1;40m");
                         break;
                case 11: this.deck[i].setValue("\033[1;31;40m" + "К" + "\033[1;40m");
                         break;
                case 12: this.deck[i].setValue("\033[1;31;40m" + "Т" + "\033[1;40m");
                         break;
            }
        }
    }
    public void diamonds(int i) {
        if (i >= 13 && i < 26 ) {
            this.deck[i].setSuit("\033[1;31;40m♦\033[1;40m");
            if (i < 22) {
                deck[i].setValue("\033[1;31;40m" + String.valueOf(i - 11) + "\033[1;40m");
            }
            switch (i) {
                case 22: this.deck[i].setValue("\033[1;31;40m" + "В" + "\033[1;40m");
                         break;
                case 23: this.deck[i].setValue("\033[1;31;40m" + "Д" + "\033[1;40m");
                         break;
                case 24: this.deck[i].setValue("\033[1;31;40m" + "К" + "\033[1;40m");
                         break;
                case 25: this.deck[i].setValue("\033[1;31;40m" + "Т" + "\033[1;40m");
                         break;
            }
        }
    }
    public void club(int i) {
        if (i >= 26 && i < 39 ) {
            this.deck[i].setSuit("\033[1;37;40m♣\033[1;40m");
            if (i < 35) {
                this.deck[i].setValue("\033[1;31;40m" + String.valueOf(i - 24) + "\033[1;40m");
            }
            switch (i) {
                case 35: this.deck[i].setValue("\033[1;31;40m" + "В" + "\033[1;40m");
                         break;
                case 36: this.deck[i].setValue("\033[1;31;40m" + "Д" + "\033[1;40m");
                         break;
                case 37: this.deck[i].setValue("\033[1;31;40m" + "К" + "\033[1;40m");
                         break;
                case 38: this.deck[i].setValue("\033[1;31;40m" + "Т" + "\033[1;40m");
                         break;
            }
        }
    }
    public void pike(int i) {
        if (i >= 39 && i < 52 ) {
            this.deck[i].setSuit("\033[1;37;40m♠\033[1;40m");
            if (i < 48) {
                this.deck[i].setValue("\033[1;31;40m" + String.valueOf(i - 37) + "\033[1;40m");
            }
            switch (i) {
                case 48: this.deck[i].setValue("\033[1;31;40m" + "В" + "\033[1;40m");
                         break;
                case 49: this.deck[i].setValue("\033[1;31;40m" + "Д" + "\033[1;40m");
                         break;
                case 50: this.deck[i].setValue("\033[1;31;40m" + "К" + "\033[1;40m");
                         break;
                case 51: this.deck[i].setValue("\033[1;31;40m" + "Т" + "\033[1;40m");
                         break;
            }
        }
    }

    public void loadTable() {

    }

    public void randomCart() {
        for (Gamer gamer : this.gamers) {
            gamer.randomCart(this.deck);
//            gamer.nullCarts();
//            while (gamer.getOneCart() == null || gamer.getTwoCart() == null) {
//                int rand = new Random().nextInt(51) + 1;
//                if (!this.deck[rand].isInUse()) {
//                    if (gamer.getOneCart() == null) {
//                        gamer.initializationOneCarts();
//                        gamer.getOneCart().setSuit(this.deck[rand].getSuit());
//                        gamer.getOneCart().setValue(this.deck[rand].getValue());
//                        this.deck[rand].setInUse(true);
//                    }
//                    else if (gamer.getTwoCart() == null) {
//                        gamer.initializationTwoCarts();
//                        gamer.getTwoCart().setSuit(this.deck[rand].getSuit());
//                        gamer.getTwoCart().setValue(this.deck[rand].getValue());
//                        this.deck[rand].setInUse(true);
//                    }
//                }
//            }
        }
    }

    public void inscription(String inscription) {
        System.out.println();
        System.out.println(String.format("============== %s ==============", inscription));
        System.out.println();
    }

    public void drawGamers() {
        inscription("ВСЕ ИГРОКИ");
        for (Gamer gamer : this.gamers) {
            System.out.println(gamer.toString());
            System.out.println();
        }
    }

    public void draw3Cart(String suit1, String suit2, String suit3,
                          String value1, String value2, String value3) {
        System.out.println(String.format(
                " ------------- " + "    " + " ------------- " + "    " + " ------------- " + "\n" +
                "|%s           |" + "    " + "|%s           |" + "    " + "|%s           |" + "\n" +
                "|             |" + "    " + "|             |" + "    " + "|             |" + "\n" +
                "|             |" + "    " + "|             |" + "    " + "|             |" + "\n" +
                "|             |" + "    " + "|             |" + "    " + "|             |" + "\n" +
                "|     %s      |" + "    " + "|     %s      |" + "    " + "|     %s      |" + "\n" +
                "|             |" + "    " + "|             |" + "    " + "|             |" + "\n" +
                "|             |" + "    " + "|             |" + "    " + "|             |" + "\n" +
                "|             |" + "    " + "|             |" + "    " + "|             |" + "\n" +
                "|           %s|" + "    " + "|           %s|" + "    " + "|           %s|" + "\n" +
                " ------------- " + "    " + " ------------- " + "    " + " ------------- " + "\n",
                value1, value2, value3, suit1, suit2, suit3, value1, value2, value3));
    }
}
