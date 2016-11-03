package ru.minesweeper;

import ru.minesweeper.interfaces.ICell;
import ru.minesweeper.interfaces.ILogic;
import ru.minesweeper.interfaces.ISelectLevel;
import ru.minesweeper.interfaces.ITheNumOfTheField;
import ru.minesweeper.levels.Easy;
import ru.minesweeper.levels.Expert;
import ru.minesweeper.levels.Medium;

import java.util.Random;

/**
 * Стандартная логика игры
 *
 * Для изменения логики игры в каком-то из уровней, достаточно:
 *
 * 1. Расширить класс уровня добавив интерфейс ILogic. После изменить логику игры в доступных методах
 *
 * 2. После перейти в этот класс и проверить на null уровень в методе,
 *    где была изменена логика (как это релизовано в 5-7-ом методах),
 *    если данный уровень был выбран, то вызываем метод находящийся в этом уровне
 *    (в каком уже изменена логика), иначе запускаем стандартную логику находящуюся в этом классе
 */
public class StandardLogic implements ISelectLevel, ILogic, ITheNumOfTheField {

    private Easy easy;
    private Medium medium;
    private Expert expert;

    private ICell[][] cells;

    @Override
    public Easy easy() {
        this.medium = null;
        this.expert = null;
        return this.easy = new Easy();
    }

    @Override
    public Medium medium() {
        this.easy = null;
        this.expert = null;
        return this.medium = new Medium();
    }

    @Override
    public Expert expert() {
        this.easy = null;
        this.medium = null;
        return this.expert = new Expert();
    }

    @Override
    public void loadBoard(ICell[][] cells) {
        this.cells = cells;
    }

    @Override
    public int sumBombs() {
        int sum = 0;
        if (this.easy == null && this.medium == null && this.expert != null)
            sum = expert.sumBombs();
        else if (this.easy == null && this.expert == null && this.medium != null)
            sum = medium.sumBombs();
        else if (this.easy != null)
            sum = this.easy.sumBombs();
        return sum;
    }

    @Override
    public int sumRow() {
        int row = 0;
        if (this.easy == null && this.medium == null && this.expert != null)
            row = expert.sumRow();
        else if (this.easy == null && this.expert == null && this.medium != null)
            row = medium.sumRow();
        else if (this.easy != null)
            row = this.easy.sumRow();
        return row;
    }

    @Override
    public int sumColumn() {
        int column = 0;
        if (this.easy == null && this.medium == null && this.expert != null)
            column = expert.sumColumn();
        else if (this.easy == null && this.expert == null && this.medium != null)
            column = this.medium.sumColumn();
        else if (this.easy != null)
            column = this.easy.sumColumn();
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
                if ((cell.isSuggestBomb() && cell.isBomb()) || cell.isSuggestEmpty()) {
                    check++;
                }
        if (check == (sumRow() * sumColumn()))
            finish = true;
        return finish;
    }

    @Override
    public void suggest(int x, int y, boolean bomb) {
        if (!bomb) this.cells[x][y].suggestEmpty();
        if (bomb && !this.cells[x][y].isSuggestEmpty()) this.cells[x][y].suggestBomb();
    }

    @Override
    public boolean checkTheFirstMove() {
        boolean check = true;
        root: for (int i = 0; i < sumRow(); i++) {
                  for (int j = 0; j < sumColumn(); j++) {
                      if (this.cells[i][j].isBomb()) {
                          check = false;
                          break root;
                      }
                  }
        }
        return check;
    }

    @Override
    public void clearAroundCell(int x, int y) {
         if (this.cells.length > 3) {
            if (y > 0 && !this.cells[x][y - 1].isSuggestBomb()) suggest(x, y - 1, false);
            if (y + 1 < sumColumn() && !this.cells[x][y + 1].isSuggestBomb()) suggest(x, y + 1, false);
            if (x > 0 && !this.cells[x - 1][y].isSuggestBomb()) suggest(x - 1, y, false);
            if (x + 1 < sumRow() && !this.cells[x + 1][y].isSuggestBomb()) suggest(x + 1, y, false);
            if (x > 0 && y > 0 && !this.cells[x - 1][y - 1].isSuggestBomb()) suggest(x - 1, y - 1, false);
            if (x + 1 < sumRow() && y + 1 < sumColumn() && !this.cells[x + 1][y + 1].isSuggestBomb()) suggest(x + 1, y + 1, false);
            if (x > 0 && y + 1 < sumColumn() && !this.cells[x - 1][y + 1].isSuggestBomb()) suggest(x - 1, y + 1, false);
            if (x + 1 < sumRow() && y > 0 && !this.cells[x + 1][y - 1].isSuggestBomb()) suggest(x + 1, y - 1, false);
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
                this.cells[row][column].thisBomb();
                sumBombs--;
            }
        }
    }

    @Override
    public int checkingAroundCell(int x, int y) {
        int checking = 0;
        // Подсчитываем сколько бомб возле переданной клетки.
        if (y > 0 && this.cells[x][y - 1].isBomb()) checking++;
        if (x > 0 && this.cells[x - 1][y].isBomb()) checking++;
        if (y > 0 && x > 0 && this.cells[x - 1][y - 1].isBomb()) checking++;
        if (y + 1 < sumColumn() && this.cells[x][y + 1].isBomb()) checking++;
        if (x + 1 < sumRow() && this.cells[x + 1][y].isBomb()) checking++;
        if (x + 1 < sumRow() && y + 1 < sumColumn() && this.cells[x + 1][y + 1].isBomb()) checking++;
        if (x + 1 < sumRow() && y > 0 && this.cells[x + 1][y - 1].isBomb()) checking++;
        if (x > 0 && y + 1 < sumColumn() && this.cells[x - 1][y + 1].isBomb()) checking++;

        return checking;
    }

    // Определяем и записываем сколько бомб возле клетки
    public void arrangeValues(int i, int j) {
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
        }
    }

    // Проходим поле и записываем в каждую клетку, сколько возле неё находится бомб
    public void beforeGameArrangeValues() {
        for (int i = 0; i < sumRow(); i++) {
            for (int j = 0; j < sumColumn(); j++) {
                if (!this.cells[i][j].isBomb()) arrangeValues(i, j);
            }
        }
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
                    if (this.cells[i][j].isSuggestEmpty() && !this.cells[i][j].isChecked()) {
                        // Если возле ячейки нет бомб
                        if (checkingAroundCell(i, j) == 0) {
                            sumEmpty++;
                            // Открываем рядом стоящие ячейки
                            clearAroundCell(i, j);
                            // Помечаем данную ячейку как просмотренную
                            this.cells[i][j].checked();
                        }
                    }
                }
        }
    }
}
