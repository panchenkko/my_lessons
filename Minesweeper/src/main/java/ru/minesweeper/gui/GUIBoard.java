package ru.minesweeper.gui;

import ru.minesweeper.interfaces.IBoard;
import ru.minesweeper.interfaces.ICell;
import ru.minesweeper.interfaces.IBoard;
import ru.minesweeper.interfaces.ICell;

import javax.swing.*;
import java.awt.*;

public class GUIBoard extends JPanel implements IBoard {

	public static final int PADDING = 40;

	public boolean real = false;

	private boolean isFinish = false;

	public ICell<Graphics>[][] cells;

	public static int getPADDING() {
		return PADDING;
	}

	public boolean isFinish() {
		return isFinish;
	}

    public void setIsFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }

    @Override
	public int returnSumBomb() {
		int sumBomb = 0;
		for (int x = 0; x != this.cells.length; x++) {
			for (int y = 0; y != this.cells[0].length; y++) {
				if (this.cells[x][y].isSuggestBomb()) sumBomb++;
			}
		}
		return sumBomb;
	}

	@Override
	public void cancelSuggestBomb(int x, int y) {
		this.cells[x][y].cancelSuggestBomb();
	}

	@Override
	public boolean returnSuggestBomb(int x, int y) {
		return this.cells[x][y].isSuggestBomb();
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		if (this.cells != null) {
			for (int x = 0; x != this.cells.length; x++) {
				for (int y = 0; y != this.cells[0].length; y++) {
                    this.cells[x][y].draw(graphics, x, y, this.real);
				}
			}
		}
	}

	@Override
	public void drawBoard(ICell[][] cells) {
		this.cells = cells;
		this.real = false;
		this.repaint();
	}

	@Override
	public void drawCell(int x, int y) {
		this.repaint();
	}

	@Override
	public void drawBang() {
		this.real = true;
		this.repaint();
	}

	@Override
	public void drawCongratulate() {
		this.repaint();
		this.isFinish = true;
		Main.setLosing("Нажмите 'OK', чтобы закрыть окно.", " Поздравляем! Вы выиграли!");
	}

	@Override
	public void drawLosing() {
		this.repaint();
		this.isFinish = true;
		Main.setLosing("Нажмите 'OK', чтобы закрыть окно.", " К сожалению Вы проиграли.");
	}
}