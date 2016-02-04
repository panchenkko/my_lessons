package ru.minesweeperLesson.minesweeper.GUI;

import ru.minesweeperLesson.minesweeper.Interfaces.IBoard;
import ru.minesweeperLesson.minesweeper.Interfaces.ICell;

import javax.swing.*;
import java.awt.*;

public class GUIBoard extends JPanel implements IBoard {

	public static final int PADDING = 50;

	public ICell<Graphics>[][] cells;

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		if (this.cells != null) {
			for (int x = 0; x != cells.length; x++) {
				for (int y = 0; y != cells[0].length; y++) {
					graphics.setColor(Color.black);
					cells[x][y].draw(graphics, false);
					graphics.drawRect(x * PADDING, y * PADDING, PADDING, PADDING);
				}
			}
		}
	}

	public void drawBoard(ICell[][] ICells) {
		this.cells = ICells;
		this.repaint();
	}

	public void drawCell(int x, int y) {
		this.repaint();
	}

	public void drawBang() {
		this.repaint();
	}

	public void drawCongratulate() {}

	public void drawLosing() {}
}