package ru.fiveInARow.gui;

import ru.fiveInARow.BaseAction;
import ru.fiveInARow.interfaces.IGeneratorBoard;
import ru.fiveInARow.interfaces.ILogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUIAction extends BaseAction implements ActionListener, MouseListener {

	private GUIBoard board;
	private int x = 0, y = 0;

	public GUIAction(ILogic logic, GUIBoard board, IGeneratorBoard generator) {
		super(logic, board, generator);
		this.board = board;
		this.board.addMouseListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		this.initGame();
	}

	public int returnX(MouseEvent e) {
		return e.getX() / GUIBoard.getPADDING();
	}

	public int returnY(MouseEvent e) {
		return e.getY() / GUIBoard.getPADDING();
	}

	public void mouseClicked(MouseEvent e) {
		System.out.println("X: " + returnX(e) + ", " + "Y: " + returnY(e) + "\n");

		if (!board.getIsSmallCell(returnX(e), returnY(e))) {
			if (!board.isCheckClick() || board.getIsBigCell(returnX(e), returnY(e))) {
                board.cancelCheckedClick();
				x = returnX(e);
				y = returnY(e);
				board.checkingClick(x, y);
			} else if (this.logic.checkingCells(x, y, returnX(e), returnY(e))) {
				select(x, y, returnX(e), returnY(e));
				board.cancelCheckedClick();
			}
            board.repaint();
		} else if (board.isCheckClick()) {
			select(x, y, returnX(e), returnY(e));
			board.cancelCheckedClick();
			board.repaint();
		}
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}
