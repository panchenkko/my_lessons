package ru.minesweeper;

import ru.minesweeper.interfaces.*;

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
		final ICell[][] cells = this.generator.generate();
		this.board.drawBoard(cells);
		this.logic.loadBoard(cells);
	}

	@Override
	public void select(int x, int y, boolean bomb) {
        if (!this.board.isFinish()) {
            this.logic.suggest(x, y, bomb);
            if (this.logic.checkTheFirstMove() && !bomb) {
                this.logic.clearAroundCell(x, y);
                this.logic.bombsGeneration();
                this.logic.beforeGameArrangeValues();
            }

            if (!bomb) this.logic.openEmptyCells();

            if (this.logic.shouldBang(x, y)) {
                this.board.drawBang();
                this.board.drawLosing();
            } else
                this.board.drawCell(x, y);
            if (this.logic.finish() && !this.logic.shouldBang(x, y)) this.board.drawCongratulate();
        }
    }
}
