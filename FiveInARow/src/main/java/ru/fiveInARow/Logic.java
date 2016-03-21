package ru.fiveInARow;

import ru.fiveInARow.interfaces.ICell;
import ru.fiveInARow.interfaces.ILogic;

import java.util.Random;

public class Logic implements ILogic {

    private ICell[][] cells;

    private static int check = 0;

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
        return 3;
    }

    @Override
    public int sumInARow() {
        return 5;
    }

    // Если все ячейки кроме одной закрашенные, то возвращаем истину и пользователь проиграл
    @Override
    public boolean finish() {
        boolean finish = false;
        int check = 0;
        for (ICell[] row : this.cells) {
            for (ICell cell : row) {
                if (cell.isSmallCellPainted() || cell.isBigCellPainted()) check++;
            }
        }

        if (check == (sumRow() * sumColumn()) - 1)
            finish = true;
        return finish;
    }

    // Проверяем, действительно ли первая клетка является закрашенной, а вторая пустой
    @Override
    public boolean checkingCells(int x, int y, int x2, int y2) {
        boolean checking = false;
        if (this.cells[x][y].isBigCellPainted() && this.cells[x2][y2].isSuggestEmpty())
            checking = true;
        return checking;
    }

    @Override
    public boolean checkTheFirstMove() {
        boolean check = true;
        root: for (int i = 0; i < sumRow(); i++)
              for (int j = 0; j < sumColumn(); j++)
                  if (this.cells[i][j].isSmallCellPainted()) {
                      check = false;
                      break root;
                  }
        return check;
    }

    /**
     * При первом ходе создаем сначала n полностью закрашенные ячейки,
     * после n не полностью закрашенные
     */
    @Override
    public void paintingCellsInStartGame() {
        Random random = new Random();
        Random randColor = new Random();
        int checking = sumSmallCellsPainted();
        while (checking > 0) {
            int row = random.nextInt(sumRow() - 1);
            int column = random.nextInt(sumColumn() - 1);
            if (!this.cells[row][column].isBigCellPainted() && !this.cells[row][column].isSmallCellPainted()) {
                int color = randColor.nextInt(6);

                this.cells[row][column].generateColor(color);

                this.cells[row][column].cancelSuggestEmpty();
                this.cells[row][column].bigCellPainting();

                checking--;
            }
        }

        checking = sumSmallCellsPainted();
        while (checking > 0) {
            int row = random.nextInt(sumRow() - 1);
            int column = random.nextInt(sumColumn() - 1);
            if (!this.cells[row][column].isBigCellPainted() && !this.cells[row][column].isSmallCellPainted()) {
                int color = randColor.nextInt(6);

                this.cells[row][column].generateColor(color);

                this.cells[row][column].cancelSuggestEmpty();
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
        this.cells[x2][y2].bigCellPainting();
        this.cells[x2][y2].cancelSuggestEmpty();

        selectColorForNewCell(x, y, x2, y2);

        this.cells[x][y].cancelBigCellPainting();
        this.cells[x][y].suggestEmpty();
    }

    // Меняем цвет ячеек, когда пользователь перемещает закрашенную ячейку
    @Override
    public void selectColorForNewCell(int x, int y, int x2, int y2) {
        if (this.cells[x][y].isRedCell())
            this.cells[x2][y2].redCell();
        else
        if (this.cells[x][y].isGreenCell())
            this.cells[x2][y2].greenCell();
        else
        if (this.cells[x][y].isBlueCell())
            this.cells[x2][y2].blueCell();
        else
        if (this.cells[x][y].isYellowCell())
            this.cells[x2][y2].yellowCell();
        else
        if (this.cells[x][y].isMagentaCell())
            this.cells[x2][y2].magentaCell();
        else
        if (this.cells[x][y].isCyanCell())
            this.cells[x2][y2].cyanCell();
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
                }
    }

    /**
     * Создаем 3 новых не полностью закрашенных ячейки, если ещё есть пустые ячейки
     */
    @Override
    public void createSmallCells() {
        Random random = new Random();
        Random randColor = new Random();
        int checking = sumSmallCellsPainted();
        while (checking > 0 && !finish()) {
            int row = random.nextInt(sumRow());
            int column = random.nextInt(sumColumn());
            if (!this.cells[row][column].isBigCellPainted() && !this.cells[row][column].isSmallCellPainted()) {
                int color = randColor.nextInt(6);

                this.cells[row][column].generateColor(color);

                this.cells[row][column].cancelSuggestEmpty();
                this.cells[row][column].smallCellPainting();
                checking--;
            }
        }
    }

    @Override
    public void clearCells(int x2, int y2) {
        if (_9_00_(x2, y2) + _15_00_(x2, y2) >= sumInARow()) {

            checkCell_9_00_(x2, y2);
            checkCell_15_00_(x2, y2);

            cells[x2][y2].checked();
            for (int i = 0; i < sumRow(); i++) {
                for (int j = 0; j < sumColumn(); j++)
                    if (cells[i][j].isChecked()) {
                        cells[i][j].cancelBigCellPainting();
                        cells[i][j].suggestEmpty();
                    }
            }
        }

        if (_10_30_(x2, y2) + _16_30_(x2, y2) >= sumInARow()) {

            checkCell_10_30_(x2, y2);
            checkCell_16_30_(x2, y2);

            cells[x2][y2].checked();
            for (int i = 0; i < sumRow(); i++) {
                for (int j = 0; j < sumColumn(); j++)
                    if (cells[i][j].isChecked()) {
                        cells[i][j].cancelBigCellPainting();
                        cells[i][j].suggestEmpty();
                    }
            }
        }

        if (_12_00_(x2, y2) + _18_00_(x2, y2) >= sumInARow()) {

            checkCell_12_00_(x2, y2);
            checkCell_18_00_(x2, y2);

            cells[x2][y2].checked();
            for (int i = 0; i < sumRow(); i++) {
                for (int j = 0; j < sumColumn(); j++)
                    if (cells[i][j].isChecked()) {
                        cells[i][j].cancelBigCellPainting();
                        cells[i][j].suggestEmpty();
                    }
            }
        }

        if (_13_30_(x2, y2) + _19_30_(x2, y2) >= sumInARow()) {

            checkCell_13_30_(x2, y2);
            checkCell_19_30_(x2, y2);

            cells[x2][y2].checked();
            for (int i = 0; i < sumRow(); i++) {
                for (int j = 0; j < sumColumn(); j++)
                    if (cells[i][j].isChecked()) {
                        cells[i][j].cancelBigCellPainting();
                        cells[i][j].suggestEmpty();
                    }
            }
        }
    }

    public void checkCell_9_00_(int x, int y) {
        // Переходим на следующую ячейку
        y--;
        // Если ячейка тоже закрашенная и она не проверенная
        if (y >= 0 && cells[x][y].isBigCellPainted() && !cells[x][y].isChecked() &&
            cells[x][y].checkColor() == cells[x][y + 1].checkColor()) {
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
            cells[x][y].checkColor() == cells[x + 1][y + 1].checkColor()) {
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
            cells[x][y].checkColor() == cells[x + 1][y].checkColor()) {
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
                cells[x][y].checkColor() == cells[x + 1][y - 1].checkColor()) {
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
            cells[x][y].checkColor() == cells[x][y - 1].checkColor()) {
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
            cells[x][y].checkColor() == cells[x - 1][y - 1].checkColor()) {
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
            cells[x][y].checkColor() == cells[x - 1][y].checkColor()) {
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
            cells[x][y].checkColor() == cells[x - 1][y + 1].checkColor()) {
            cells[x][y].checked();
            checkCell_19_30_(x, y);
        }
    }

    @Override
    public int _9_00_(int x, int y) {
        // Переходим на следующую ячейку
        y--;
        // Если ячейка тоже закрашенная и она не проверенная
        if (y > 0 && cells[x][y].isBigCellPainted() && !cells[x][y].isChecked() &&
            cells[x][y].checkColor() == cells[x][y + 1].checkColor()) {
            check++;
            _9_00_(x, y);
        }
        return check;
    }

    @Override
    public int _10_30_(int x, int y) {
        // Переходим на следующую ячейку
        x--; y--;
        // Если ячейка тоже закрашенная и она не проверенная
        if (y > 0 && x > 0 && cells[x][y].isBigCellPainted() && !cells[x][y].isChecked() &&
                cells[x][y].checkColor() == cells[x + 1][y + 1].checkColor()) {
            cells[x][y].checked();
            check++;
            _10_30_(x, y);
        }
        return check;
    }

    @Override
    public int _12_00_(int x, int y) {
        // Переходим на следующую ячейку
        x--;
        // Если ячейка тоже закрашенная и она не проверенная
        if (x > 0 && cells[x][y].isBigCellPainted() && !cells[x][y].isChecked() &&
                cells[x][y].checkColor() == cells[x + 1][y].checkColor()) {
            cells[x][y].checked();
            check++;
            _12_00_(x, y);
        }
        return check;
    }

    @Override
    public int _13_30_(int x, int y) {
        // Переходим на следующую ячейку
        x--; y++;
        // Если ячейка тоже закрашенная и она не проверенная
        if (x > 0 && y + 1 < sumColumn() && cells[x][y].isBigCellPainted() && !cells[x][y].isChecked() &&
                cells[x][y].checkColor() == cells[x + 1][y - 1].checkColor()) {
            cells[x][y].checked();
            check++;
            _13_30_(x, y);
        }
        return check;
    }

    @Override
    public int _15_00_(int x, int y) {
        // Переходим на следующую ячейку
        y++;
        // Если ячейка тоже закрашенная и она не проверенная
        if (y + 1 < sumColumn() && cells[x][y].isBigCellPainted() && !cells[x][y].isChecked() &&
                cells[x][y].checkColor() == cells[x][y - 1].checkColor()) {
            cells[x][y].checked();
            check++;
            _15_00_(x, y);
        }
        return check;
    }

    @Override
    public int _16_30_(int x, int y) {
        // Переходим на следующую ячейку
        x++; y++;
        // Если ячейка тоже закрашенная и она не проверенная
        if (x + 1 < sumRow() && y + 1 < sumColumn() && cells[x][y].isBigCellPainted() && !cells[x][y].isChecked() &&
                cells[x][y].checkColor() == cells[x - 1][y - 1].checkColor()) {
            cells[x][y].checked();
            check++;
            _16_30_(x, y);
        }
        return check;
    }

    @Override
    public int _18_00_(int x, int y) {
        // Переходим на следующую ячейку
        x++;
        // Если ячейка тоже закрашенная и она не проверенная
        if (x + 1 < sumRow() && cells[x][y].isBigCellPainted() && !cells[x][y].isChecked() &&
                cells[x][y].checkColor() == cells[x - 1][y].checkColor()) {
            cells[x][y].checked();
            check++;
            _18_00_(x, y);
        }
        return check;
    }

    @Override
    public int _19_30_(int x, int y) {
        // Переходим на следующую ячейку
        x++; y--;
        // Если ячейка тоже закрашенная и она не проверенная
        if (x + 1 < sumRow() && y > 0 && cells[x][y].isBigCellPainted() && !cells[x][y].isChecked() &&
                cells[x][y].checkColor() == cells[x - 1][y + 1].checkColor()) {
            cells[x][y].checked();
            check++;
            _19_30_(x, y);
        }
        return check;
    }
}