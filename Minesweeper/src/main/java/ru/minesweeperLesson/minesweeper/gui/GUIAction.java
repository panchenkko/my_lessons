package ru.minesweeperLesson.minesweeper.gui;

import ru.minesweeperLesson.minesweeper.BaseAction;
import ru.minesweeperLesson.minesweeper.interfaces.IGeneratorBoard;
import ru.minesweeperLesson.minesweeper.interfaces.ILogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUIAction extends BaseAction implements ActionListener, MouseListener {

	private GUIBoard board;

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
		System.out.println("X: " + returnX(e));
		System.out.println("Y: " + returnY(e));

		// Если ячейка уже помечена как бомба и пользователь говорит что в ячейке нет бомбы
		if (board.returnSuggestBomb(returnX(e), returnY(e)) && e.getButton() != 1)  {
			board.cancelSuggestBomb(returnX(e), returnY(e));
		} else if (board.returnSuggestBomb(returnX(e), returnY(e)))
			select(returnX(e), returnY(e), true);
		else
		select(returnX(e), returnY(e), e.getButton() != 1);
		board.repaint();
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
