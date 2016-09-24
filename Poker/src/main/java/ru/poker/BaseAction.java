package ru.poker;

import ru.poker.Classes.Cart;
import ru.poker.Classes.Gamer;
import ru.poker.Classes.Table;
import ru.poker.Interfaces.IGenerator;
import ru.poker.Interfaces.IProgress;

public class BaseAction implements IProgress {

    private final IGenerator generator;
    private final Table table;

    public BaseAction(final Table table, final IGenerator generator) {
        this.generator = generator;
        this.table = table;
    }

    @Override
    public void initGame() {
        final Cart[] deck = generator.generateDeck();
        this.table.loadDeck(deck);
        final Gamer[] gamers = generator.generateGamers();
        this.table.loadGamers(gamers);

        this.table.writingDeck();
    }

    @Override
    public void progress() {
        this.table.checkMoneyInGamers(); // фильтруем игроков, какие не имеют достаточной суммы для старта
        this.table.drawGamersOutGame(); // выводим список игроков, у каких недостаточная сумма для входа в игру
        this.table.initialAmount(); // у всех игроков за столом снимаем стартовую сумму
        this.table.randomCartForGamer(); // раздаём карты игрокам
        this.table.random3CartForTable(); // получаем первые 3 случайные карты из колоды
        this.table.gamerInput(); // обработка хода каждого игрока за столом
        this.table.random4CartForTable(); // получаем 4-ю случайную карту из колоды
        this.table.gamerInput(); // обработка хода каждого игрока за столом
        this.table.random5CartForTable(); // получаем 5-ю случайную карту из колоды
        this.table.gamerInput(); // обработка хода каждого игрока за столом
        this.table.finish();
        this.table.listAllGamers();
    }
}