package ru.fiveInARow.gui;

import ru.fiveInARow.interfaces.IBoard;
import ru.fiveInARow.interfaces.ICell;

import javax.swing.*;
import java.awt.*;

public class GUIBoard extends JPanel implements IBoard {

	public static final int PADDING = 45;

    public static int getPADDING() {
        return PADDING;
    }

    public boolean real = false;

	public ICell<Graphics>[][] cells;

    public boolean getIsBigCell(int x, int y) {
        return cells[x][y].isBigCellPainted();
    }

    public boolean getIsSmallCell(int x, int y) {
        return cells[x][y].isSmallCellPainted();
    }

    // Если пользователь уже выбрал какую-то ячейку, возвращаем истину
	public boolean isCheckClick() {
		for (int i = 0; i != cells.length; i++) {
			for (int j = 0; j != cells[0].length; j++) {
				if (cells[i][j].isCheckedClick()) {
                    return true;
                }
			}
		}
		return false;
	}

    // Если игрок не выбрал для перемещения ни одну ячейку, помечаем ячейку как выбранную
    @Override
	public void checkingClick(int x, int y) {
		if (!cells[x][y].isSuggestEmpty() && !cells[x][y].isCheckedClick()) {
            cells[x][y].checkedClick();
        }
	}

    // Отменяем все выбранные ячейки
    @Override
	public void cancelCheckedClick() {
		for (int i = 0; i != cells.length; i++) {
			for (int j = 0; j != cells[0].length; j++) {
				if (cells[i][j].isCheckedClick()) {
                    cells[i][j].cancelCheckedClick();
                }
			}
		}
	}

    // Рисуем поле
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		if (this.cells != null) {
			for (int x = 0; x != cells.length; x++) {
				for (int y = 0; y != cells[0].length; y++) {
					graphics.setColor(Color.black);
					cells[x][y].draw(graphics, x, y);
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
	public void drawSelect() {
        repaint();
	}

	public void drawLosing() {
//		Main.setScore("Вы проиграли!");
		this.repaint();
		Main.setLosing();
	}
}