package ru.fiveInARow;

import ru.fiveInARow.interfaces.*;

/**
 * Базовые действия пользователя
 */
public class BaseAction implements IUserAction {

	private final IGeneratorBoard generator;
	private final IBoard board;
	private final ILogic logic;

	public BaseAction(final ILogic logic, final IBoard board, final IGeneratorBoard generator) {
		this.generator = generator;
		this.board = board;
		this.logic = logic;
	}

	@Override
	public void initGame() {
		final ICell[][] cells = generator.generate();
		this.board.drawBoard(cells);
		this.logic.loadBoard(cells);
	}

	@Override
	public void select(int x, int y, int x2, int y2) {
		this.logic.painting();
		if (!bomb)
			this.logic.openEmptyCells();
		if (this.logic.shouldBang(x, y)) {
			this.board.drawLosing();
		} else
			this.board.drawCell(x, y);
		if (this.logic.finish())
			this.board.drawCongratulate();
	}
}
