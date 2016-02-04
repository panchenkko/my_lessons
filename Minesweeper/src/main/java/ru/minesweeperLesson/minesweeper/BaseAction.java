package ru.minesweeperLesson.minesweeper;

import ru.minesweeperLesson.minesweeper.Interfaces.*;

/**
 * Базовые действия пользователя
 */
public class BaseAction implements IUserAction {

	private final IGeneratorBoard generator;
	private final IBoard board;
	private final ISuperLogic logic;

	public BaseAction(final ISuperLogic logic, final IBoard board, final IGeneratorBoard generator) {
		this.generator = generator;
		this.board = board;
		this.logic = logic;
	}

	@Override
	public void initGame() {
		final ICell[][] ICells = generator.generate();
		this.board.drawBoard(ICells);
		this.logic.loadBoard(ICells);
	}

	@Override
	public void select(int x, int y, boolean bomb) {
		this.logic.suggest(x, y, bomb);
		this.board.drawCell(x, y);
		if (this.logic.shouldBang(x, y)) {
			this.board.drawBang();
			this.board.drawLosing();
		}
		if (this.logic.finish()) {
			this.board.drawCongratulate();
		}
	}
}
