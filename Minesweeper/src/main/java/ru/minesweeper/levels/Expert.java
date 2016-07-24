package ru.minesweeper.levels;

import ru.minesweeper.interfaces.ITheNumOfTheField;
import ru.minesweeper.interfaces.ITheNumOfTheField;

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
