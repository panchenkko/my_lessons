package ru.minesweeperLesson.minesweeper.levels;

import ru.minesweeperLesson.minesweeper.interfaces.ITheNumOfTheField;

public class Expert implements ITheNumOfTheField {

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
