package ru.minesweeper.levels;

import ru.minesweeper.interfaces.TheNumOfTheField;

/**
 * Реализация сложного уровня игры
 */
public class Expert implements TheNumOfTheField {

    @Override
    public int sumBombs() {
        return 99;
    }

    @Override
    public int sumRow() {
        return 16;
    }

    @Override
    public int sumColumn() {
        return 30;
    }
}
