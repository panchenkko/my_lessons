package ru.fiveInARow;

import ru.fiveInARow.interfaces.ICell;
import ru.fiveInARow.interfaces.ILogic;

import java.util.Random;

public class Logic implements ILogic {

    private ICell[][] cells;

    private static int check = 1;

    @Override
    public void loadBoard(ICell[][] cells) {
        this.cells = cells;
    }

    @Override
    public int sumRow() {
        return 4;
    }

    @Override
    public int sumColumn() {
        return 4;
    }

    @Override
    public ICell[][] sizeField() {
        return new ICell[sumRow()][sumColumn()];
    }

    @Override
    public int sumSmallCellsPainted() {
        return 2;
    }

    @Override
    public int sumInARow() {
        return 2;
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
     * и создаем 3 новых не полностью закрашенных ячейки, если ещё есть пустые ячейки
     */
    @Override
    public void createSmallCells() {
        for (int i = 0; i < sumRow(); i++)
            for (int j = 0; j < sumColumn(); j++)
                if (this.cells[i][j].isSmallCellPainted()) {
                    this.cells[i][j].cancelSmallCellPainting();
                    this.cells[i][j].bigCellPainting();
                }
    }

    @Override
    public void createBigCells() {
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

    // Возвращаем количество бомб вокруг ячейки
    @Override
    public void checkingAroundCell(int x, int y) {
//        if (y > 0 && cells[x][y - 1].isBigCellPainted())
//            // Метод проверки только в эту сторону, в начале помечаем как проверенную,
//            // после идет проверка, если опять находит, то опять помечаем как проверенную.
//        if (x > 0 && cells[x - 1][y].isBigCellPainted())
//
//        if (y > 0 && x > 0 && cells[x - 1][y - 1].isBigCellPainted())
//
//        if (y + 1 < sumColumn() && cells[x][y + 1].isBigCellPainted())
//
//        if (x + 1 < sumRow() && cells[x + 1][y].isBigCellPainted())
//
//        if (x + 1 < sumRow() && y + 1 < sumColumn() && cells[x + 1][y + 1].isBigCellPainted())
//
//        if (x + 1 < sumRow() && y > 0 && cells[x + 1][y - 1].isBigCellPainted())
//
//        if (x > 0 && y + 1 < sumColumn() && cells[x - 1][y + 1].isBigCellPainted())

    }

    public int _9_00_(int x, int y) {
        // Переходим на следующую ячейку
        y--;
        // Если ячейка тоже закрашенная и она не проверенная
        if (y > 0 && cells[x][y].isBigCellPainted() && !cells[x][y].isChecked()) {
            cells[x][y].checked();
            check++;
            _9_00_(x, y);
        }
        return check;
    }

    /**
     * Открываем пустые ячейки
     */
    @Override
    public void clearCells() {

        // Проходим поле
        for (int i = 0; i < sumRow(); i++) {
            for (int j = 0; j < sumColumn(); j++) {
                // Если это закрашенная клетка и она не проверенная
                if (cells[i][j].isBigCellPainted() && !cells[i][j].isChecked()) {
                    // Проходим по стрелке 9:00 и проверяем.
                    // Если по пути стрелки было обнаружено больше чем n в ряд закрашенных ячеек,
                    // то проходим поле и очищаем помеченные ячейки
                    if (_9_00_(i, j) >= sumInARow()) {
                        cells[i][j].checked();
                        for (int s = 0; s < sumRow(); s++) {
                            for (int h = 0; h < sumColumn(); h++)
                                if (cells[s][h].isChecked()) {
                                    cells[s][h].cancelBigCellPainting();
//                                    cells[s][h].suggestEmpty();
                                }
                    }
                }
            }
        }

//        int check = 1, sumEmpty = 0;
//
//        while (check != sumEmpty) {
//            check = sumEmpty;
//            // Проходим поле и проверяем пустые ячейки
//            for (int i = 0; i < sumRow(); i++)
//                for (int j = 0; j < sumColumn(); j++) {
//                    // Если ячейка пустая и мы её ещё не проверяли
//                    if (cells[i][j].isSuggestEmpty() && !cells[i][j].isChecked()) {
//
//                        // Если возле ячейки нет бомб
//                        if (checkingAroundCell(i, j) == 0) {
//                            sumEmpty++;
//                            // Открываем рядом стоящие ячейки
//                            clearAroundCell(i, j);
//                            // Помечаем данную ячейку как просмотренную
//                            cells[i][j].checked();
//                        }
//
//                        // Выводим данную ячейку
//                        switch (checkingAroundCell(i, j)) {
//                            case 8: this.cells[i][j].suggest8();
//                                    break;
//                            case 7: this.cells[i][j].suggest7();
//                                    break;
//                            case 6: this.cells[i][j].suggest6();
//                                    break;
//                            case 5: this.cells[i][j].suggest5();
//                                    break;
//                            case 4: this.cells[i][j].suggest4();
//                                    break;
//                            case 3: this.cells[i][j].suggest3();
//                                    break;
//                            case 2: this.cells[i][j].suggest2();
//                                    break;
//                            case 1: this.cells[i][j].suggest1();
//                                    break;
//                            default: isPaintedFirstCell(i, j, false);
//                        }
//                    }
//                }
        }
    }
}