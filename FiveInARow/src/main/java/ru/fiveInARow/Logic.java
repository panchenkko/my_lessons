package ru.fiveInARow;

import ru.fiveInARow.console.ConsoleCell;
import ru.fiveInARow.interfaces.ICell;
import ru.fiveInARow.interfaces.ILogic;

import java.util.Random;

public class Logic implements ILogic {

    private ICell[][] cells;

    @Override
    public void loadBoard(ICell[][] cells) {
        this.cells = cells;
    }

    @Override
    public int sumRow() {
        return 9;
    }

    @Override
    public int sumColumn() {
        return 9;
    }

    @Override
    public ICell[][] sizeField() {
        return new ICell[sumRow()][sumColumn()];
    }

    // Если пользователь всё разгадал, возвращаем истину
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

    // Проверяем, действительно ли клетки какие выбрал пользователь полностью закрашенные
    @Override
    public boolean checkingCells(int x, int y, int x2, int y2) {
        boolean test = false;
        if (this.cells[x][y].isPaintCell() && this.cells[x2][y2].isPaintCell())
            test = true;
        return test;
    }

    @Override
    public void painting() {
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

    /**
     * Открываем пустые ячейки
     */
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
                            default: checkingCells(i, j, false);
                        }
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
//    public void painting() {
//        if (easy == null && medium == null && expert != null)
//            expert.painting();
//        else if (easy == null && expert == null && medium != null)
//            medium.painting();
//        else if (easy != null)
//            easy.painting();
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
//    public void checkingCells(int x, int y, boolean bomb) {
//        if (easy == null && medium == null && expert != null)
//            expert.checkingCells(x, y ,bomb);
//        else if (easy == null && expert == null && medium != null)
//            medium.checkingCells(x, y ,bomb);
//        else if (easy != null)
//            easy.checkingCells(x, y ,bomb);
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
