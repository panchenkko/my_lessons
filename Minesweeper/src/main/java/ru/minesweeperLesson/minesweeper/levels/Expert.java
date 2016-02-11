package ru.minesweeperLesson.minesweeper.levels;

import ru.minesweeperLesson.minesweeper.console.ConsoleCell;
import ru.minesweeperLesson.minesweeper.interfaces.ICell;
import ru.minesweeperLesson.minesweeper.interfaces.ILogic;

import java.util.Random;

/**
 * Реализация сложного уровня игры
 */
public class Expert implements ILogic {

    private ICell[][] cells;

    @Override
    public void loadBoard(ICell[][] cells) {
        this.cells = cells;
    }

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

    // Если пользователь всё разгадал, возвращаем истину
    @Override
    public boolean finish() {
        boolean finish = false;
        int check = 0;
        for (ICell[] row : this.cells) {
            for (ICell cell : row) {
                if ((cell.isSuggestBomb() && cell.isBomb()) ||
                        (cell.isSuggestEmpty() && !cell.isBomb()) || (!cell.isSuggestBomb() && cell.isBomb())
                        || cell.isSuggest1() || cell.isSuggest2() || cell.isSuggest3() || cell.isSuggest4()
                        || cell.isSuggest5() || cell.isSuggest6() || cell.isSuggest7() || cell.isSuggest8()) {
                    check++;
                }
            }
        }
        if (check == (sumRow() * sumColumn()))
            finish = true;
        return finish;
    }

    // Предположения пользователя (Бомба или пустая клетка)
    @Override
    public void suggest(int x, int y, boolean bomb) {
        if (bomb) {
            this.cells[x][y].suggestBomb();
        } else {
            this.cells[x][y].suggestEmpty();
        }
    }

    // Проверка первого хода. Если на поле нет бомб, возвращаем истину
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

    // Очистка вокруг ячейки при первом ходе
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

    // Генерация бомб на поле
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

    // Возвращаем количество бомб вокруг ячейки
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

    /**
     * Открываем пустые ячейки
     */
    @Override
    public void openEmptyCells(int x, int y) {
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
}
