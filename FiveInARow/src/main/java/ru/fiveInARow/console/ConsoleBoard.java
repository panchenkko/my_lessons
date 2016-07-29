package ru.fiveInARow.console;

import ru.fiveInARow.exceptions.NullMethodException;
import ru.fiveInARow.interfaces.IBoard;
import ru.fiveInARow.interfaces.ICell;

/**
 * Реализация поля в консоли
 */
public class ConsoleBoard implements IBoard {

	private ICell[][] cells;

    @Override
    public void checkingClick(int x, int y) throws NullMethodException {
        throw new NullMethodException("Данный метод в консольной версии не используется!");
    }

    @Override
    public void cancelCheckedClick() throws NullMethodException {
        throw new NullMethodException("Данный метод в консольной версии не используется!");
    }

    @Override
	public void drawBoard(ICell[][] cells) {
		this.cells = cells;
		this.redraw();
	}

	@Override
	public void drawSelect() {
		System.out.println("********** SELECT **********");
		this.redraw();
	}

	@Override
	public void drawLosing() {
		this.redraw();
		System.out.println("********** YOU LOSE **********");
	}

	private void redraw() {
		int space, numRow = 0;
		for (ICell[] row : cells) {
			// Нумерация столбцов
			if (numRow < 1) {
				for (int i = 1; i <= row.length; i++) {
					if (i == 1) space = 4;
					else if (i > 10) space = 2;
					else space = 3;
					for (int j = 0; j < space; j++)
						System.out.print(" ");
					System.out.print("\033[30m" + i + "\033[0m");
				}
				System.out.println();
			}
			// Нумерация рядов
			if (numRow < 9) System.out.print(" ");
			System.out.print("\033[30m" + ++numRow + "\033[0m ");

			for (ICell cell : row) {
				cell.draw(System.out);
			}
			System.out.println();
		}
		System.out.println();
	}
}
