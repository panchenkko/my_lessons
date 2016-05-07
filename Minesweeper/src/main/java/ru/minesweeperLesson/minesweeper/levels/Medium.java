package ru.minesweeperLesson.minesweeper.levels;

import ru.minesweeperLesson.minesweeper.interfaces.ITheNumOfTheField;

public class Medium implements ITheNumOfTheField {

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
