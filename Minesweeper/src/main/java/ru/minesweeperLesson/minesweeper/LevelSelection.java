package ru.minesweeperLesson.minesweeper;

import ru.minesweeperLesson.minesweeper.interfaces.ICell;
import ru.minesweeperLesson.minesweeper.interfaces.ILogic;
import ru.minesweeperLesson.minesweeper.interfaces.ISelectLevel;
import ru.minesweeperLesson.minesweeper.levels.Easy;
import ru.minesweeperLesson.minesweeper.levels.Expert;
import ru.minesweeperLesson.minesweeper.levels.Medium;

/**
 * Реализация проверки выбраного уровня пользователем
 */
public class LevelSelection implements ISelectLevel, ILogic {

    private Easy easy;
    private Medium medium;
    private Expert expert;

    @Override
    public Easy easy() {
        return easy = new Easy();
    }

    @Override
    public Medium medium() {
        return medium = new Medium();
    }

    @Override
    public Expert expert() {
        return expert = new Expert();
    }

    @Override
    public void loadBoard(ICell[][] cells) {
        if (easy == null && medium == null && expert != null)
            expert.loadBoard(cells);
        else if (easy == null && expert == null && medium != null)
            medium.loadBoard(cells);
        else if (easy != null)
            easy.loadBoard(cells);
    }

    @Override
    public int sumBombs() {
        int sum = 0;
        if (easy == null && medium == null && expert != null)
            sum = expert.sumBombs();
        else if (easy == null && expert == null && medium != null)
            sum = medium.sumBombs();
        else if (easy != null)
            sum = easy.sumBombs();
        return sum;
    }

    @Override
    public int sumRow() {
        int row = 9;
        if (easy == null && medium == null && expert != null)
            row = expert.sumRow();
        else if (easy == null && expert == null && medium != null)
            row = medium.sumRow();
        else if (easy != null)
            row = easy.sumRow();
        return row;
    }

    @Override
    public int sumColumn() {
        int column = 9;
        if (easy == null && medium == null && expert != null)
            column = expert.sumColumn();
        else if (easy == null && expert == null && medium != null)
            column = medium.sumColumn();
        else if (easy != null)
            column = easy.sumColumn();
        return column;
    }

    @Override
    public ICell[][] sizeField() {
        ICell[][] ICells = new ICell[9][9];
        if (easy == null && medium == null && expert != null)
            ICells = expert.sizeField();
        else if (easy == null && expert == null && medium != null)
            ICells = medium.sizeField();
        else if (easy != null)
            ICells = easy.sizeField();
        return ICells;
    }

    @Override
    public boolean checkTheFirstMove() {
        if (easy == null && medium == null && expert != null)
            return expert.checkTheFirstMove();
        else if (easy == null && expert == null && medium != null)
            return medium.checkTheFirstMove();
        else if (easy != null)
            return easy.checkTheFirstMove();
        return false;
    }

    @Override
    public void bombsGeneration() {
        if (easy == null && medium == null && expert != null)
            expert.bombsGeneration();
        else if (easy == null && expert == null && medium != null)
            medium.bombsGeneration();
        else if (easy != null)
            easy.bombsGeneration();
    }

    @Override
    public boolean shouldBang(int x, int y) {
        boolean bang = false;
        if (easy == null && medium == null && expert != null)
            bang = expert.shouldBang(x, y);
        else if (easy == null && expert == null && medium != null)
            bang = medium.shouldBang(x, y);
        else if (easy != null)
            bang = easy.shouldBang(x, y);
        return bang;
    }

    @Override
    public boolean finish() {
        boolean finish = false;
        if (easy == null && medium == null && expert != null)
            finish = expert.finish();
        else if (easy == null && expert == null && medium != null)
            finish = medium.finish();
        else if (easy != null)
            finish = easy.finish();
        return finish;
    }

    @Override
    public void suggest(int x, int y, boolean bomb) {
        if (easy == null && medium == null && expert != null)
            expert.suggest(x, y ,bomb);
        else if (easy == null && expert == null && medium != null)
            medium.suggest(x, y ,bomb);
        else if (easy != null)
            easy.suggest(x, y ,bomb);
    }

    @Override
    public void openEmptyCells(int x, int y) {
        if (easy == null && medium == null && expert != null)
            expert.openEmptyCells(x, y);
        else if (easy == null && expert == null && medium != null)
            medium.openEmptyCells(x, y);
        else if (easy != null)
            easy.openEmptyCells(x, y);
    }

    @Override
    public void clearAroundCell(int x, int y) {
        if (easy == null && medium == null && expert != null)
            expert.clearAroundCell(x, y);
        else if (easy == null && expert == null && medium != null)
            medium.clearAroundCell(x, y);
        else if (easy != null)
            easy.clearAroundCell(x, y);
    }

    @Override
    public int checkingAroundCell(int x, int y) {
        return 0;
    }
}
