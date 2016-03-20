package ru.minesweeper.levels;

import ru.minesweeper.interfaces.TheNumOfTheField;

/**
 * Реализация среднего уровня игры
 */
public class Medium implements TheNumOfTheField {

    @Override
    public int sumBombs() {
        return 40;
    }

    @Override
    public int sumRow() {
        return 16;
    }

    @Override
    public int sumColumn() {
        return 16;
    }
}
