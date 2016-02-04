package ru.minesweeperLesson.minesweeper.Console;

import ru.minesweeperLesson.minesweeper.Interfaces.IBoard;
import ru.minesweeperLesson.minesweeper.Interfaces.ICell;

/**
 * Реализация поля в консоли
 */
public class ConsoleBoard implements IBoard {

	private ICell[][] cells;

	public void drawBoard(ICell[][] cells) {
		this.cells = cells;
		this.redraw(false);
	}

	public void drawCell(int x, int y) {
		System.out.println("********** SELECT **********");
		this.redraw(false);
	}

	public void drawBang() {
		System.out.println("********** BANG **********");
		this.redraw(true);
	}

	public void drawCongratulate() {
		System.out.println("********** CONGRATULATE **********");
	}

	public void drawLosing() {
		System.out.println("********** YOU LOSE **********");
	}

	private void redraw(boolean real) {
		for (ICell[] row : cells) {
			for (ICell ICell : row) {
				ICell.draw(System.out, real);
			}
			System.out.println();
		}
		System.out.println();
	}
}
