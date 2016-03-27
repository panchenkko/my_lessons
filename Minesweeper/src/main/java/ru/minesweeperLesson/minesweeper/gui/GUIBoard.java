package ru.minesweeperLesson.minesweeper.gui;

import ru.minesweeperLesson.minesweeper.interfaces.IBoard;
import ru.minesweeperLesson.minesweeper.interfaces.ICell;

import javax.swing.*;
import java.awt.*;

public class GUIBoard extends JPanel implements IBoard {

	public static final int PADDING = 40;

	public boolean real = false;

	public ICell<Graphics>[][] cells;

	public static int getPADDING() {
		return PADDING;
	}

	@Override
	public int returnSumBomb() {
		int sumBomb = 0;
		for (int x = 0; x != cells.length; x++) {
			for (int y = 0; y != cells[0].length; y++) {
				if (cells[x][y].isSuggestBomb()) sumBomb++;
			}
		}
		return sumBomb;
	}

	@Override
	public void cancelSuggestBomb(int x, int y) {
		this.cells[x][y].cancelSuggestBomb();
	}

	// Если ячейка какую выбрал пользователь уже помечена как бомба, то возвращаем true
	@Override
	public boolean returnSuggestBomb(int x, int y) {
		return this.cells[x][y].isSuggestBomb();
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		if (this.cells != null) {
			for (int x = 0; x != cells.length; x++) {
				for (int y = 0; y != cells[0].length; y++) {
					cells[x][y].draw(graphics, x, y, real);
				}
			}
		}
	}

	@Override
	public void drawBoard(ICell[][] cells) {
		this.cells = cells;
		real = false;
		this.repaint();
	}

	@Override
	public void drawCell(int x, int y) {
		this.repaint();
	}

	@Override
	public void drawBang() {
		real = true;
		this.repaint();
	}

	@Override
	public void drawCongratulate() {
//		Main.setLabel("CONGRATULATE");
		this.repaint();
	}

	public void drawLosing() {
//		Main.setLabel("YOU LOSE");
		this.repaint();
		Main.setLosing();
	}
}