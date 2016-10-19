package ru.fiveInARow;

import ru.fiveInARow.exceptions.NotEmptyCellsException;
import ru.fiveInARow.interfaces.ICell;
import ru.fiveInARow.interfaces.ILogic;

import java.util.Random;

public class Logic implements ILogic {

    private ICell[][] cells;

    private int score = 0;

    private boolean found = false;

    /**
     * Повышение счёта игрока
     */
    @Override
    public void incScore() {
        this.score++;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Получаем ссылку на все ячейки
     * @param cells присваиваем ссылку полю класса
     */
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

    /**
     * Кол-во создаваемых шариков при каждом ходе игрока
     * @return получение кол-ва шариков
     */
    @Override
    public int sumCellsPainted() {
        return 3;
    }

    /**
     * Кол-во одинаковых шариков, какое должен собрать игрок, чтобы они исчезли
     * @return получение кол-ва
     */
    @Override
    public int sumInARow() {
        return 5;
    }

    /**
     * Подсчет кол-ва пустых ячеек на поле
     * @return получение кол-ва
     */
    @Override
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
     * Подсчитываем кол-во больших шаров на поле и после спрашиваем: Если кол-во больших шаров равно всему кол-ву
     * ячеек в поле минус один, то завершаем игру. Пользователь проиграл.
     * @return возвращаем истину, если пользователь проиграл
     */
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

    /**
     * Проверяем, действительно ли первая клетка является большим шаром,
     * а вторая пустой ячейкой или ячейкой с маленьким шаром.
     * @param x Координаты первого шара
     * @param y Координаты первого шара
     * @param x2 Координаты второго шара
     * @param y2 Координаты второго шара
     * @return возвращаем истину, если условия совпадают с правдой
     */
    @Override
    public boolean checkingCells(int x, int y, int x2, int y2) {
        boolean checking = false;
        if (this.cells[x][y].isBigCellPainted() &&
                (this.cells[x2][y2].isSuggestEmpty() || this.cells[x2][y2].isSmallCellPainted()))
            checking = true;
        return checking;
    }

    /**
     * Очищаем проверенные ячейки, какие были помечены в прошлом ходе
     */
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
     * Очищаем проверенные ячейки, какие были помечены при клике на ячейку
     */
    @Override
    public void clearCellProgressChecked() {
        for (ICell[] row : this.cells) {
            for (ICell cell : row) {
                if (cell.isProgressChecked())
                    cell.cancelProgressChecked();
            }
        }
    }

    /**
     * При первом ходе создаем сначала sumCellsPainted() больших шаров,
     * после sumCellsPainted() маленьких шаров
     */
    @Override
    public void paintingCellsInStartGame() {
        try {
            createBalls(true, true);
        } catch (NotEmptyCellsException e) {/*NOP*/}
    }

    /**
     * Второй ячейке присваиваем значение первой, а первую делаем пустую
     * @param x Координаты первого шара
     * @param y Координаты первого шара
     * @param x2 Координаты второго шара
     * @param y2 Координаты второго шара
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
     * С маленьких шаров делаем большие
     */
    @Override
    public void createBigCells() {
        for (int i = 0; i < sumRow(); i++) {
            for (int j = 0; j < sumColumn(); j++) {
                if (this.cells[i][j].isSmallCellPainted()) {
                    this.cells[i][j].cancelSmallCellPainting();
                    this.cells[i][j].bigCellPainting();

                    checkingCells(i, j);
                }
            }
        }
    }

    /**
     * Создаем 3 новых маленьких шара, если ещё есть пустые ячейки
     */
    @Override
    public void createSmallCells() throws NotEmptyCellsException {
        createBalls(true, false);
    }

    /**
     * Метод, какой создаёт шары. Остается только ввести аргументы, какие шары тебе необходимы.
     * @param createSmallBalls Вводим true, если нужно создать маленькие шары
     * @param createBigBalls Вводим true, если нужно создать большие шары
     * @throws NotEmptyCellsException происходит исключение, если нет места, куда создавать шары
     */
    public void createBalls(boolean createSmallBalls, boolean createBigBalls) throws NotEmptyCellsException {
        Random random = new Random(System.currentTimeMillis());

        int checkingBigBalls = 0, checkingSmallBalls = 0;

        if (createSmallBalls) checkingSmallBalls = sumCellsPainted();
        if (createBigBalls) checkingBigBalls = sumCellsPainted();

        while ((checkingBigBalls > 0 || checkingSmallBalls > 0) && !finish() && sumEmptyCells() > 0) {
            int row = random.nextInt(sumRow());
            int column = random.nextInt(sumColumn());
            if (!this.cells[row][column].isBigCellPainted() && !this.cells[row][column].isSmallCellPainted()) {
                int color = random.nextInt(6);

                this.cells[row][column].generateColor(color);

                if (checkingSmallBalls > 0) {
                    this.cells[row][column].smallCellPainting();
                    checkingSmallBalls--;
                } else
                if (checkingBigBalls > 0) {
                    this.cells[row][column].bigCellPainting();
                    checkingBigBalls--;
                }
            }
        }

        if (sumEmptyCells() == 0 && (checkingSmallBalls > 0 || checkingBigBalls > 0))
            throw new NotEmptyCellsException("Недостаточно клеток. Вы на грани проигрыша!");
    }

    /**
     * Проверка на заграждение пути. Если шару при перемещении будут заграждать путь большие шары, то он не сдвинется.
     * @param x Координаты первого шара
     * @param y Координаты первого шара
     * @param x2 Координаты второго шара
     * @param y2 Координаты второго шара
     * @return если шару ничего не заграждает путь, то возвращаем истину
     */
    @Override
    public boolean progressCheck(int x, int y, int x2, int y2) {
        this.found = false;
        pathToBall(x, y, x2, y2);
        return this.found;
    }

    private void pathToBall(int x, int y, int x2, int y2) {
        checkingUp(x, y, x2, y2);
        checkingDown(x, y, x2, y2);
        checkingLeft(x, y, x2, y2);
        checkingRight(x, y, x2, y2);
    }

    private void checking(int x, int y, int x2, int y2) {
        if (!cells[x][y].isProgressChecked() && !cells[x][y].isBigCellPainted()) {
            if (x == x2 && y == y2) {
                this.found = true;
            } else {
                cells[x][y].progressChecked();
                pathToBall(x, y, x2, y2);
            }
        }
    }

    private void checkingUp(int x, int y, int x2, int y2) {
        y--;
        if (y >= 0) {
            checking(x, y, x2, y2);
        }
    }
    private void checkingDown(int x, int y, int x2, int y2) {
        y++;
        if (y < sumRow()) {
            checking(x, y, x2, y2);
        }
    }
    private void checkingLeft(int x, int y, int x2, int y2) {
        x--;
        if (x >= 0) {
            checking(x, y, x2, y2);
        }
    }
    private void checkingRight(int x, int y, int x2, int y2) {
        x++;
        if (x < sumColumn()) {
            checking(x, y, x2, y2);
        }
    }

    /**
     * Проверяем со всех сторон, если есть в какой-то из них sumInARow() больших шаров, одинакового цвета,
     * то вызываются методы, какие их помечают, а в конце этого метода вызываем метод для очистки всех помеченных шаров.
     * @param x2 Координаты второго шара
     * @param y2 Координаты второго шара
     * @return возвращаем истину, если игрок не собрал n в ряд и нужно создавать n новых маленьких шаров
     */
    @Override
    public boolean checkingCells(int x2, int y2) {
        boolean isCreateNewSmallCell = true;

        if (_9_00_(x2, y2) + _15_00_(x2, y2) >= sumInARow() - 1) {
            isCreateNewSmallCell = false;
            checkCell_9_00_(x2, y2);
            checkCell_15_00_(x2, y2);
        }

        if (_10_30_(x2, y2) + _16_30_(x2, y2) >= sumInARow() - 1) {
            isCreateNewSmallCell = false;
            checkCell_10_30_(x2, y2);
            checkCell_16_30_(x2, y2);
        }

        if (_12_00_(x2, y2) + _18_00_(x2, y2) >= sumInARow() - 1) {
            isCreateNewSmallCell = false;
            checkCell_12_00_(x2, y2);
            checkCell_18_00_(x2, y2);
        }

        if (_13_30_(x2, y2) + _19_30_(x2, y2) >= sumInARow() - 1) {
            isCreateNewSmallCell = false;
            checkCell_13_30_(x2, y2);
            checkCell_19_30_(x2, y2);
        }

        if (!isCreateNewSmallCell) {
            cells[x2][y2].checked();
            clearBalls();
        }

        return isCreateNewSmallCell;
    }

    /**
     * Очищаем помеченные клетки и с каждым шаром повышаем счёт игрока
     */
    @Override
    public void clearBalls() {
        for (int i = 0; i < sumRow(); i++) {
            for (int j = 0; j < sumColumn(); j++) {
                if (cells[i][j].isChecked()) {
                    incScore();
                    cells[i][j].cancelBigCellPainting();
                    cells[i][j].suggestEmpty();
                }
            }
        }
    }

    /**
     * Методы для пометки шаров, какие нужно очистить
     * @param x Координаты шара, вокруг какого идет проверка
     * @param y Координаты шара, вокруг какого идет проверка
     */

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

    /**
     * Методы для проверки. Совпадает ли кол-во одинаковых шаров с указанным кол-вом,
     * чтобы вызывать методы. Если да, то вызываются методы сверху, они помечают, шары, после метод ещё выше стирает
     * все помеченные шары.
     * @param x Координаты шара, вокруг какого идет проверка
     * @param y Координаты шара, вокруг какого идет проверка
     * @return возврат кол-ва одинаковых шаров в определенной стороне шара
     */

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