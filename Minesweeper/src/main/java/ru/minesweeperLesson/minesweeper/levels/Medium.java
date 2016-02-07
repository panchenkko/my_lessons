package ru.minesweeperLesson.minesweeper.levels;

import ru.minesweeperLesson.minesweeper.interfaces.ICell;
import ru.minesweeperLesson.minesweeper.interfaces.ISuperLogic;

/**
 * Реализация среднего уровня игры
 */
public class Medium implements ISuperLogic {

    private ICell[][] cells;

    @Override
    public void loadBoard(ICell[][] ICells) {
        this.cells = ICells;
    }

    @Override
    public int sumBombs() {
        return 40;
    }

    @Override
    public int sizeRow() {
        return 16;
    }

    @Override
    public int sizeColumn() {
        return 16;
    }

    @Override
    public ICell[][] sizeField() {
        return new ICell[sizeRow()][sizeColumn()];
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
        for (ICell[] row : cells) {
            for (ICell ICell : row) {
                finish = ((ICell.isSuggestBomb() && ICell.isBomb()) ||
                          (ICell.isSuggestEmpty() && !ICell.isBomb()));
            }
        }
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
}
