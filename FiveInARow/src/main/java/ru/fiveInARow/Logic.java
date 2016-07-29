package ru.fiveInARow;

import ru.fiveInARow.exceptions.NotEmptyCellsException;
import ru.fiveInARow.interfaces.ICell;
import ru.fiveInARow.interfaces.ILogic;

import java.util.ArrayList;
import java.util.Random;

public class Logic implements ILogic {

    private ICell[][] cells;

    private int check = 0;

    private int score = 0;

    @Override
    public int score() {
        return this.score++;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

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

    @Override
    public int sumSmallCellsPainted() {
        return 5;
    }

    @Override
    public int sumInARow() {
        return 5;
    }

    @Override
    public boolean finish() {
        boolean finish = false;
        int check = 0;
        for (ICell[] row : this.cells) {
            for (ICell cell : row) {
                if (cell.isBigCellPainted()) check++;
            }
        }

        if (check >= sumRow() * sumColumn() - 1)
            finish = true;
        return finish;
    }

    public int sumEmptyCells() {
        int emptyCells = 0;
        for (ICell[] row : this.cells) {
            for (ICell cell : row) {
                if (cell.isSuggestEmpty()) emptyCells++;
            }
        }
        return emptyCells;
    }

    /**
     * Проверяем, действительно ли первая клетка является закрашенной,
     * а вторая пустой или неполностью закрашенной
     */
    @Override
    public boolean checkingCells(int x, int y, int x2, int y2) {
        boolean checking = false;
        if (this.cells[x][y].isBigCellPainted() && this.cells[x2][y2].isSuggestEmpty() ||
            this.cells[x2][y2].isSmallCellPainted())
            checking = true;
        return checking;
    }

    @Override
    public void clearCellChecked() {
        for (ICell[] row : this.cells) {
            for (ICell cell : row) {
                if (cell.isChecked())
                    cell.cancelChecked();
            }
        }
    }

    /**
     * При первом ходе создаем сначала n полностью закрашенных ячеек,
     * после n не полностью закрашенных
     */
    @Override
    public void paintingCellsInStartGame() {
        Random random = new Random(System.currentTimeMillis());
        int checking = sumSmallCellsPainted();
        while (checking > 0) {
            int row = random.nextInt(sumRow());
            int column = random.nextInt(sumColumn());
            if (!this.cells[row][column].isBigCellPainted() && !this.cells[row][column].isSmallCellPainted()) {
                int color = random.nextInt(6);

                this.cells[row][column].generateColor(color);
                this.cells[row][column].bigCellPainting();

                checking--;
            }
        }

        checking = sumSmallCellsPainted();
        while (checking > 0) {
            int row = random.nextInt(sumRow());
            int column = random.nextInt(sumColumn());
            if (!this.cells[row][column].isBigCellPainted() && !this.cells[row][column].isSmallCellPainted()) {
                int color = random.nextInt(6);

                this.cells[row][column].generateColor(color);
                this.cells[row][column].smallCellPainting();

                checking--;
            }
        }
    }

    /**
     * Второй ячейке присваиваем значение первой, а первую делаем пустую
     */
    @Override
    public void movePaintedCell(int x, int y, int x2, int y2) {
        this.cells[x2][y2].cancelSmallCellPainting();
        this.cells[x2][y2].cancelAllColor();

        this.cells[x2][y2].bigCellPainting();
        this.cells[x2][y2].generateColor(this.cells[x][y].returnColor());

        this.cells[x][y].cancelBigCellPainting();
        this.cells[x][y].cancelSmallCellPainting();
        this.cells[x][y].suggestEmpty();
    }

    /**
     * Не полностью закрашенные ячейки, закрашиваем полностью
     */
    @Override
    public void createBigCells() {
        for (int i = 0; i < sumRow(); i++)
            for (int j = 0; j < sumColumn(); j++)
                if (this.cells[i][j].isSmallCellPainted()) {
                    this.cells[i][j].cancelSmallCellPainting();
                    this.cells[i][j].bigCellPainting();

                    clearCells(i, j);
                }
    }

    /**
     * Создаем 3 новых не полностью закрашенных ячейки, если ещё есть пустые ячейки
     */
    @Override
    public void createSmallCells() throws NotEmptyCellsException {
        Random random = new Random(System.currentTimeMillis());
        int checking = sumSmallCellsPainted();
        while (checking > 0 && !finish() && sumEmptyCells() > 0) {
            int row = random.nextInt(sumRow());
            int column = random.nextInt(sumColumn());
            if (!this.cells[row][column].isBigCellPainted() && !this.cells[row][column].isSmallCellPainted()) {
                int color = random.nextInt(6);

                this.cells[row][column].generateColor(color);
                this.cells[row][column].smallCellPainting();

                checking--;
            }
        }
        if (sumEmptyCells() == 0 && checking > 0)
            throw new NotEmptyCellsException("Недостаточно клеток. Вы на грани проигрыша!");
    }

    //TODO Доделать проверку на наличие свободного пути, для перемещения ячейки на желаемое место
    @Deprecated
//    @Override
//    public boolean progressCheck(int x, int y, int x2, int y2) {
//        boolean checkLeft = checking(x, y - 1, x2, y2);
//        boolean checkUp = checking(x - 1, y, x2, y2);
//        boolean checkRight = checking(x, y + 1, x2, y2);
//        boolean checkDown = checking(x + 1, y, x2, y2);
//        return checkLeft || checkUp || checkRight || checkDown;
//    }
//
//    private boolean checking(int x, int y, int x2, int y2) {
//        if (x >= 0 && y >= 0 && x < sumRow() && y < sumColumn()) {
//            if (!cells[x][y].isProgressChecked() && (cells[x][y].isSuggestEmpty() || cells[x][y].isSmallCellPainted())) {
//                cells[x][y].progressChecked();
////                try {
////                    Thread.sleep(100);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
//                if (x == x2 && y == y2) return true;
//                progressCheck(x, y, x2, y2);
//            }
//        }
//        return false;
//    }
    @Override
    public boolean progressCheck(int x, int y, int x2, int y2) {
        if (checkingUp(x, y, x2, y2) || checkingDown(x, y, x2, y2) ||
            checkingLeft(x, y, x2, y2) || checkingRight(x, y, x2, y2)) {
            return true;
        }
        return false;
    }

    private boolean checkingUp(int x, int y, int x2, int y2) {
        if (!cells[x][y].isChecked() && !cells[x][y].isBigCellPainted()) {
            x--;
            if (x == x2 && y == y2)
                return true;
            progressCheck(x, y, x2, y2);
        }
        return false;
    }
    private boolean checkingDown(int x, int y, int x2, int y2) {
        if (!cells[x][y].isChecked() && !cells[x][y].isBigCellPainted()) {
            x++;
            if (x == x2 && y == y2)
                return true;
            progressCheck(x, y, x2, y2);
        }
        return false;
    }
    private boolean checkingLeft(int x, int y, int x2, int y2) {
        if (!cells[x][y].isChecked() && !cells[x][y].isBigCellPainted()) {
            y--;
            if (x == x2 && y == y2)
                return true;
            progressCheck(x, y, x2, y2);
        }
        return false;
    }
    private boolean checkingRight(int x, int y, int x2, int y2) {
        if (!cells[x][y].isChecked() && !cells[x][y].isBigCellPainted()) {
            y++;
            if (x == x2 && y == y2)
                return true;
            progressCheck(x, y, x2, y2);
        }
        return false;
    }


    @Override
    public boolean clearCells(int x2, int y2) {
        boolean isCreateNewSmallCell = false;

        if (_9_00_(x2, y2) + _15_00_(x2, y2) >= sumInARow() - 1) {
            isCreateNewSmallCell = true;
            checkCell_9_00_(x2, y2);
            checkCell_15_00_(x2, y2);
        }

        if (_10_30_(x2, y2) + _16_30_(x2, y2) >= sumInARow() - 1) {
            isCreateNewSmallCell = true;
            checkCell_10_30_(x2, y2);
            checkCell_16_30_(x2, y2);
        }

        if (_12_00_(x2, y2) + _18_00_(x2, y2) >= sumInARow() - 1) {
            isCreateNewSmallCell = true;
            checkCell_12_00_(x2, y2);
            checkCell_18_00_(x2, y2);
        }

        if (_13_30_(x2, y2) + _19_30_(x2, y2) >= sumInARow() - 1) {
            isCreateNewSmallCell = true;
            checkCell_13_30_(x2, y2);
            checkCell_19_30_(x2, y2);
        }

        if (isCreateNewSmallCell) {
            cells[x2][y2].checked();
            clearBalls();
        }

        return isCreateNewSmallCell;
    }

    public void clearBalls() {
        for (int i = 0; i < sumRow(); i++) {
            for (int j = 0; j < sumColumn(); j++) {
                if (cells[i][j].isChecked()) {
                    score();
                    cells[i][j].cancelBigCellPainting();
                    cells[i][j].suggestEmpty();
                }
            }
        }
    }

    @Override
    public void checkCell_9_00_(int x, int y) {
        // Переходим на следующую ячейку
        y--;
        // Если ячейка тоже закрашенная и она не проверенная
        if (y >= 0 && cells[x][y].isBigCellPainted() && !cells[x][y].isChecked() &&
            cells[x][y].returnColor() == cells[x][y + 1].returnColor()) {
            cells[x][y].checked();
            checkCell_9_00_(x, y);
        }
    }

    @Override
    public void checkCell_10_30_(int x, int y) {
        // Переходим на следующую ячейку
        x--; y--;
        // Если ячейка тоже закрашенная и она не проверенная
        if (y >= 0 && x >= 0 && cells[x][y].isBigCellPainted() && !cells[x][y].isChecked() &&
            cells[x][y].returnColor() == cells[x + 1][y + 1].returnColor()) {
            cells[x][y].checked();
            checkCell_10_30_(x, y);
        }
    }

    @Override
    public void checkCell_12_00_(int x, int y) {
        // Переходим на следующую ячейку
        x--;
        // Если ячейка тоже закрашенная и она не проверенная
        if (x >= 0 && cells[x][y].isBigCellPainted() && !cells[x][y].isChecked() &&
            cells[x][y].returnColor() == cells[x + 1][y].returnColor()) {
            cells[x][y].checked();
            checkCell_12_00_(x, y);
        }
    }

    @Override
    public void checkCell_13_30_(int x, int y) {
        // Переходим на следующую ячейку
        x--; y++;
        // Если ячейка тоже закрашенная и она не проверенная
        if (x >= 0 && y < sumColumn() && cells[x][y].isBigCellPainted() && !cells[x][y].isChecked() &&
                cells[x][y].returnColor() == cells[x + 1][y - 1].returnColor()) {
            cells[x][y].checked();
            checkCell_13_30_(x, y);
        }
    }

    @Override
    public void checkCell_15_00_(int x, int y) {
        // Переходим на следующую ячейку
        y++;
        // Если ячейка тоже закрашенная и она не проверенная
        if (y < sumColumn() && cells[x][y].isBigCellPainted() && !cells[x][y].isChecked() &&
            cells[x][y].returnColor() == cells[x][y - 1].returnColor()) {
            cells[x][y].checked();
            checkCell_15_00_(x, y);
        }
    }

    @Override
    public void checkCell_16_30_(int x, int y) {
        // Переходим на следующую ячейку
        x++; y++;
        // Если ячейка тоже закрашенная и она не проверенная
        if (x < sumRow() && y < sumColumn() && cells[x][y].isBigCellPainted() && !cells[x][y].isChecked() &&
            cells[x][y].returnColor() == cells[x - 1][y - 1].returnColor()) {
            cells[x][y].checked();
            checkCell_16_30_(x, y);
        }
    }

    @Override
    public void checkCell_18_00_(int x, int y) {
        // Переходим на следующую ячейку
        x++;
        // Если ячейка тоже закрашенная и она не проверенная
        if (x < sumRow() && cells[x][y].isBigCellPainted() && !cells[x][y].isChecked() &&
            cells[x][y].returnColor() == cells[x - 1][y].returnColor()) {
            cells[x][y].checked();
            checkCell_18_00_(x, y);
        }
    }

    @Override
    public void checkCell_19_30_(int x, int y) {
        // Переходим на следующую ячейку
        x++; y--;
        // Если ячейка тоже закрашенная и она не проверенная
        if (x < sumRow() && y >= 0 && cells[x][y].isBigCellPainted() && !cells[x][y].isChecked() &&
            cells[x][y].returnColor() == cells[x - 1][y + 1].returnColor()) {
            cells[x][y].checked();
            checkCell_19_30_(x, y);
        }
    }

    @Override
    public int _9_00_(int x, int y) {
        int check, count = 0;

        do {
            check = count;
            // Переходим на следующую ячейку
            y--;
            // Если ячейка тоже закрашенная и она не проверенная
            if (y >= 0 && cells[x][y].isBigCellPainted() && !cells[x][y].isChecked() &&
                    cells[x][y].returnColor() == cells[x][y + 1].returnColor()) {
                count++;
            }
        } while (count != check);

        return count;
    }

    @Override
    public int _10_30_(int x, int y) {
        int check, count = 0;

        do {
            check = count;
            // Переходим на следующую ячейку
            x--; y--;
            // Если ячейка тоже закрашенная и она не проверенная
            if (x >= 0 && y >= 0 && cells[x][y].isBigCellPainted() && !cells[x][y].isChecked() &&
                    cells[x][y].returnColor() == cells[x + 1][y + 1].returnColor()) {
                count++;
                _10_30_(x, y);
            }
        } while (count != check);

        return check;
    }

    @Override
    public int _12_00_(int x, int y) {
        int check, count = 0;

        do {
            check = count;
            // Переходим на следующую ячейку
            x--;
            // Если ячейка тоже закрашенная и она не проверенная
            if (x >= 0 && cells[x][y].isBigCellPainted() && !cells[x][y].isChecked() &&
                    cells[x][y].returnColor() == cells[x + 1][y].returnColor()) {
                count++;
                _12_00_(x, y);
            }
        } while (count != check);

        return check;
    }

    @Override
    public int _13_30_(int x, int y) {
        int check, count = 0;

        do {
            check = count;
            // Переходим на следующую ячейку
            x--; y++;
            // Если ячейка тоже закрашенная и она не проверенная
            if (x >= 0 && y < sumColumn() && cells[x][y].isBigCellPainted() && !cells[x][y].isChecked() &&
                    cells[x][y].returnColor() == cells[x + 1][y - 1].returnColor()) {
                count++;
                _13_30_(x, y);
            }
        } while (count != check);

        return check;
    }

    @Override
    public int _15_00_(int x, int y) {
        int check, count = 0;

        do {
            check = count;
            // Переходим на следующую ячейку
            y++;
            // Если ячейка тоже закрашенная и она не проверенная
            if (y < sumColumn() && cells[x][y].isBigCellPainted() && !cells[x][y].isChecked() &&
                    cells[x][y].returnColor() == cells[x][y - 1].returnColor()) {
                count++;
                _15_00_(x, y);
            }
        } while (count != check);

        return check;
    }

    @Override
    public int _16_30_(int x, int y) {
        int check, count = 0;

        do {
            check = count;
            // Переходим на следующую ячейку
            x++; y++;
            // Если ячейка тоже закрашенная и она не проверенная
            if (x < sumRow() && y < sumColumn() && cells[x][y].isBigCellPainted() && !cells[x][y].isChecked() &&
                    cells[x][y].returnColor() == cells[x - 1][y - 1].returnColor()) {
                count++;
                _16_30_(x, y);
            }
        } while (count != check);

        return check;
    }

    @Override
    public int _18_00_(int x, int y) {
        int check, count = 0;

        do {
            check = count;
            // Переходим на следующую ячейку
            x++;
            // Если ячейка тоже закрашенная и она не проверенная
            if (x < sumRow() && cells[x][y].isBigCellPainted() && !cells[x][y].isChecked() &&
                    cells[x][y].returnColor() == cells[x - 1][y].returnColor()) {
                count++;
                _18_00_(x, y);
            }
        } while (count != check);

        return check;
    }

    @Override
    public int _19_30_(int x, int y) {
        int check, count = 0;

        do {
            check = count;
            // Переходим на следующую ячейку
            x++; y--;
            // Если ячейка тоже закрашенная и она не проверенная
            if (x < sumRow() && y >= 0 && cells[x][y].isBigCellPainted() && !cells[x][y].isChecked() &&
                    cells[x][y].returnColor() == cells[x - 1][y + 1].returnColor()) {
                count++;
                _19_30_(x, y);
            }
        } while (count != check);

        return check;
    }
}