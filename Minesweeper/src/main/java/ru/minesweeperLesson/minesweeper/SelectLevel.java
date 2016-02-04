package ru.minesweeperLesson.minesweeper;

import ru.minesweeperLesson.minesweeper.Interfaces.Cell;
import ru.minesweeperLesson.minesweeper.Interfaces.SuperLogic;
import ru.minesweeperLesson.minesweeper.Interfaces.SelectLevelInterface;
import ru.minesweeperLesson.minesweeper.Logics.Easy;
import ru.minesweeperLesson.minesweeper.Logics.Expert;
import ru.minesweeperLesson.minesweeper.Logics.Medium;

/**
 * Реализация проверки выбраного уровня пользователем
 */
public class SelectLevel implements SelectLevelInterface, SuperLogic {

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
    public void loadBoard(Cell[][] cells) {
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
    public int sizeRow() {
        int row = 9;
        if (easy == null && medium == null && expert != null)
            row = expert.sizeRow();
        else if (easy == null && expert == null && medium != null)
            row = medium.sizeRow();
        else if (easy != null)
            row = easy.sizeRow();
        return row;
    }

    @Override
    public int sizeColumn() {
        int column = 9;
        if (easy == null && medium == null && expert != null)
            column = expert.sizeColumn();
        else if (easy == null && expert == null && medium != null)
            column = medium.sizeColumn();
        else if (easy != null)
            column = easy.sizeColumn();
        return column;
    }

    @Override
    public Cell[][] sizeField() {
        Cell[][] cells = new Cell[9][9];
        if (easy == null && medium == null && expert != null)
            cells = expert.sizeField();
        else if (easy == null && expert == null && medium != null)
            cells = medium.sizeField();
        else if (easy != null)
            cells = easy.sizeField();
        return cells;
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
}
