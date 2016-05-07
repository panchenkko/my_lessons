package ru.minesweeperLesson.minesweeper.levels;

import ru.minesweeperLesson.minesweeper.interfaces.TheNumOfTheField;

public class Expert implements TheNumOfTheField {

    @Override
    public int sumBombs() {
        return 99;
    }

    @Override
    public int sumRow() {
        return 30;
    }

    @Override
    public int sumColumn() {
        return 16;
    }
}
