package ru.minesweeper.gui;

import ru.minesweeper.interfaces.IBoard;
import ru.minesweeper.interfaces.ICell;

import javax.swing.*;
import java.awt.*;

public class GUIBoard extends JPanel implements IBoard {

	public static final int PADDING = 40;

	public boolean real = false;

	public ICell<Graphics>[][] cells;


	private JLabel statusbar;
	private int row = 17; int column = 25;



	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		if (this.cells != null) {
			for (int x = 0; x != cells.length; x++) {
				for (int y = 0; y != cells[0].length; y++) {
					graphics.setColor(Color.black);
					cells[x][y].draw(graphics, x, y, real);
					graphics.drawRect(x * PADDING, y * PADDING, PADDING, PADDING);
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
		drawLosing();
		this.repaint();
	}

	@Override
	public void drawCongratulate() {
		Main.setLabel("CONGRATULATE");
		this.repaint();
	}

	public void drawLosing() {
		Main.setLabel("YOU LOSE");
		this.repaint();
	}
}