package ru.fiveInARow.console;

import ru.fiveInARow.interfaces.IBoard;
import ru.fiveInARow.interfaces.ICell;

/**
 * Реализация поля в консоли
 */
public class ConsoleBoard implements IBoard {

	private ICell[][] cells;

	@Override
	public void drawBoard(ICell[][] cells) {
		this.cells = cells;
		this.redraw(false);
	}

	@Override
	public void drawCell(int x, int y) {
		System.out.println("********** SELECT **********");
		this.redraw(false);
	}

	@Override
	public void drawCongratulate() {
		System.out.println("********** CONGRATULATE **********");
	}

	@Override
	public void drawLosing() {
		System.out.println("********** YOU LOSE **********");
	}

	private void redraw(boolean real) {
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
					System.out.print(i);
				}
				System.out.println();
			}
			// Нумерация рядов
			if (numRow < 9) System.out.print(" ");
			System.out.print(++numRow + " ");

			for (ICell cell : row) {
				cell.draw(System.out);
			}
			System.out.println();
		}
		System.out.println();
	}
}
