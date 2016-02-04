package ru.minesweeperLesson.minesweeper.Logics;

import ru.minesweeperLesson.minesweeper.Interfaces.ICell;
import ru.minesweeperLesson.minesweeper.Interfaces.ISuperLogic;

/**
 * Реализация легкого уровня игры
 */
public class Easy implements ISuperLogic {

	private ICell[][] cells;

	@Override
	public void loadBoard(ICell[][] ICells) {
		this.cells = ICells;
	}

	@Override
	public int sumBombs() {
		return 10;
	}

	@Override
	public int sizeRow() {
		return 9;
	}

	@Override
	public int sizeColumn() {
		return 9;
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
//		for (ICell[] row : cells) {
//			for (ICell cell : row) {
//				finish = ((cell.isSuggestBomb() && cell.isBomb()) ||
//				          (cell.isSuggestEmpty() && !cell.isBomb()));
//			}
//		}
		int check = 0;
		for (ICell[] row : cells) {
			for (ICell ICell : row) {
				if ((ICell.isSuggestBomb() && ICell.isBomb()) ||
					(ICell.isSuggestEmpty() && !ICell.isBomb())) {
					check++;
				}
			}
		}
		if (check == (sizeRow() * sizeColumn()))
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
}
