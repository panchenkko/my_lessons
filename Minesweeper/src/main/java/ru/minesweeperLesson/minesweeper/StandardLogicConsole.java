package ru.minesweeperLesson.minesweeper;

import ru.minesweeperLesson.minesweeper.console.ConsoleCell;
import ru.minesweeperLesson.minesweeper.interfaces.ICell;
import ru.minesweeperLesson.minesweeper.interfaces.ILogic;
import ru.minesweeperLesson.minesweeper.interfaces.ISelectLevel;
import ru.minesweeperLesson.minesweeper.interfaces.ITheNumOfTheField;
import ru.minesweeperLesson.minesweeper.levels.Easy;
import ru.minesweeperLesson.minesweeper.levels.Expert;
import ru.minesweeperLesson.minesweeper.levels.Medium;

import java.util.Random;

/**
 * Стандартная логика игры
 */

/**
 * Для изменения логики игры в каком-то из уровней, достаточно:
 *
 * 1. Расширить класс уровня добавив интерфейс ILogic. После изменить логику игры в доступных методах
 *
 * 2. После перейти в этот класс и проверить на null уровень в методе,
 *    где была изменена логика (как это релизовано в 5-7-ом методах),
 *    если данный уровень был выбран, то вызываем метод находящийся в этом уровне
 *    (в каком уже изменена логика), иначе запускаем стандартную логику находящуюся в этом классе
 */

public class StandardLogicConsole implements ISelectLevel, ILogic, ITheNumOfTheField {

    private Easy easy;
    private Medium medium;
    private Expert expert;

    private ICell[][] cells;

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
        this.cells = cells;
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
        int row = 0;
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
        int column = 0;
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
        return new ICell[sumRow()][sumColumn()];
    }

    @Override
    public boolean shouldBang(int x, int y) {
        final ICell selected = this.cells[x][y];
        // Если это бомба, и пользователь не предположил что это бомба, то мы взрываемся
        return selected.isBomb() && !selected.isSuggestBomb();
    }

    @Override
    public boolean finish() {
        boolean finish = false;
        int check = 0;
        for (ICell[] row : this.cells)
            for (ICell cell : row)
                if ((cell.isSuggestBomb() && cell.isBomb()) ||
                        (cell.isSuggestEmpty() && !cell.isBomb()) || (!cell.isSuggestBomb() && cell.isBomb())
                        || cell.isSuggest1() || cell.isSuggest2() || cell.isSuggest3() || cell.isSuggest4()
                        || cell.isSuggest5() || cell.isSuggest6() || cell.isSuggest7() || cell.isSuggest8()) {
                    check++;
                }
        if (check == (sumRow() * sumColumn()))
            finish = true;
        return finish;
    }

    @Override
    public void suggest(int x, int y, boolean bomb) {
        if (!bomb)
            this.cells[x][y].suggestEmpty();
        if (bomb && !this.cells[x][y].isSuggestEmpty())
            this.cells[x][y].suggestBomb();
        else if (bomb && this.cells[x][y].isSuggestEmpty())
            System.out.println("Вы уже открыли эту клетку!\n");
    }

    @Override
    public boolean checkTheFirstMove() {
        boolean check = true;
        root: for (int i = 0; i < sumRow(); i++)
            for (int j = 0; j < sumColumn(); j++)
                if (this.cells[i][j].isBomb()) {
                    check = false;
                    break root;
                }
        return check;
    }

    @Override
    public void clearAroundCell(int x, int y) {
        if (cells.length > 3) {
            if (y > 0) suggest(x, y - 1, false);
            if (y + 1 < sumColumn()) suggest(x, y + 1, false);
            if (x > 0) suggest(x - 1, y, false);
            if (x + 1 < sumRow()) suggest(x + 1, y, false);
            if (x > 0 && y > 0) suggest(x - 1, y - 1, false);
            if (x + 1 < sumRow() && y + 1 < sumColumn()) suggest(x + 1, y + 1, false);
            if (x > 0 && y + 1 < sumColumn()) suggest(x - 1, y + 1, false);
            if (x + 1 < sumRow() && y > 0) suggest(x + 1, y - 1, false);
        }
    }

    @Override
    public void bombsGeneration() {
        Random random = new Random();
        int sumBombs = sumBombs();
        while (sumBombs > 0) {
            int row = random.nextInt(sumRow());
            int column = random.nextInt(sumColumn());
            if (!this.cells[row][column].isBomb() && !this.cells[row][column].isSuggestEmpty()) {
                this.cells[row][column] = new ConsoleCell(true);
                sumBombs--;
            }
        }
    }

    @Override
    public int checkingAroundCell(int x, int y) {
        int checking = 0;

        if (y > 0 && cells[x][y - 1].isBomb()) checking++;
        if (x > 0 && cells[x - 1][y].isBomb()) checking++;
        if (y > 0 && x > 0 && cells[x - 1][y - 1].isBomb()) checking++;
        if (y + 1 < sumColumn() && cells[x][y + 1].isBomb()) checking++;
        if (x + 1 < sumRow() && cells[x + 1][y].isBomb()) checking++;
        if (x + 1 < sumRow() && y + 1 < sumColumn() && cells[x + 1][y + 1].isBomb()) checking++;
        if (x + 1 < sumRow() && y > 0 && cells[x + 1][y - 1].isBomb()) checking++;
        if (x > 0 && y + 1 < sumColumn() && cells[x - 1][y + 1].isBomb()) checking++;

        return checking;
    }

    @Override
    public void openEmptyCells() {
        int check = 1, sumEmpty = 0;

        while (check != sumEmpty) {
            check = sumEmpty;
            // Проходим поле и проверяем пустые ячейки
            for (int i = 0; i < sumRow(); i++)
                for (int j = 0; j < sumColumn(); j++) {
                    // Если ячейка пустая и мы её ещё не проверяли
                    if (cells[i][j].isSuggestEmpty() && !cells[i][j].isChecked()) {

                        // Если возле ячейки нет бомб
                        if (checkingAroundCell(i, j) == 0) {
                            sumEmpty++;
                            // Открываем рядом стоящие ячейки
                            clearAroundCell(i, j);
                            // Помечаем данную ячейку как просмотренную
                            cells[i][j].checked();
                        }

                        // Выводим данную ячейку
                        switch (checkingAroundCell(i, j)) {
                            case 8: this.cells[i][j].suggest8();
                                    break;
                            case 7: this.cells[i][j].suggest7();
                                    break;
                            case 6: this.cells[i][j].suggest6();
                                    break;
                            case 5: this.cells[i][j].suggest5();
                                    break;
                            case 4: this.cells[i][j].suggest4();
                                    break;
                            case 3: this.cells[i][j].suggest3();
                                    break;
                            case 2: this.cells[i][j].suggest2();
                                    break;
                            case 1: this.cells[i][j].suggest1();
                                    break;
                            default: suggest(i, j, false);
                        }
                    }
                }
        }
    }

//    @Override
//    public ICell[][] sizeField() {
//        ICell[][] ICells = null;
//        if (easy == null && medium == null && expert != null)
//            ICells = expert.sizeField();
//        else if (easy == null && expert == null && medium != null)
//            ICells = medium.sizeField();
//        else if (easy != null)
//            ICells = easy.sizeField();
//        return ICells;
//    }
//
//    @Override
//    public boolean checkTheFirstMove() {
//        if (easy == null && medium == null && expert != null)
//            return expert.checkTheFirstMove();
//        else if (easy == null && expert == null && medium != null)
//            return medium.checkTheFirstMove();
//        else if (easy != null)
//            return easy.checkTheFirstMove();
//        return false;
//    }
//
//    @Override
//    public void bombsGeneration() {
//        if (easy == null && medium == null && expert != null)
//            expert.bombsGeneration();
//        else if (easy == null && expert == null && medium != null)
//            medium.bombsGeneration();
//        else if (easy != null)
//            easy.bombsGeneration();
//    }
//
//    @Override
//    public boolean shouldBang(int x, int y) {
//        boolean bang = false;
//        if (easy == null && medium == null && expert != null)
//            bang = expert.shouldBang(x, y);
//        else if (easy == null && expert == null && medium != null)
//            bang = medium.shouldBang(x, y);
//        else if (easy != null)
//            bang = easy.shouldBang(x, y);
//        return bang;
//    }
//
//    @Override
//    public boolean finish() {
//        boolean finish = false;
//        if (easy == null && medium == null && expert != null)
//            finish = expert.finish();
//        else if (easy == null && expert == null && medium != null)
//            finish = medium.finish();
//        else if (easy != null)
//            finish = easy.finish();
//        return finish;
//    }
//
//    @Override
//    public void suggest(int x, int y, boolean bomb) {
//        if (easy == null && medium == null && expert != null)
//            expert.suggest(x, y ,bomb);
//        else if (easy == null && expert == null && medium != null)
//            medium.suggest(x, y ,bomb);
//        else if (easy != null)
//            easy.suggest(x, y ,bomb);
//    }
//
//    @Override
//    public void openEmptyCells() {
//        if (easy == null && medium == null && expert != null)
//            expert.openEmptyCells();
//        else if (easy == null && expert == null && medium != null)
//            medium.openEmptyCells();
//        else if (easy != null)
//            easy.openEmptyCells();
//    }
//
//    @Override
//    public void clearAroundCell(int x, int y) {
//        if (easy == null && medium == null && expert != null)
//            expert.clearAroundCell(x, y);
//        else if (easy == null && expert == null && medium != null)
//            medium.clearAroundCell(x, y);
//        else if (easy != null)
//            easy.clearAroundCell(x, y);
//    }
//
//    @Override
//    public int checkingAroundCell(int x, int y) {
//        return 0;
//    }
}
