package ru.fiveInARow;

import ru.fiveInARow.exceptions.NotEmptyCellsException;
import ru.fiveInARow.exceptions.NullMethodException;
import ru.fiveInARow.gui.Main;
import ru.fiveInARow.interfaces.*;

/**
 * Базовые действия пользователя
 */
public class BaseAction implements IUserAction {

	private final IGeneratorBoard generator;
	private final IBoard board;
	public final ILogic logic;

	public BaseAction(final ILogic logic, final IBoard board, final IGeneratorBoard generator) {
		this.generator = generator;
		this.board = board;
		this.logic = logic;
	}

	@Override
	public void initGame() {
		final ICell[][] cells = generator.generate();
		this.logic.loadBoard(cells);
		this.logic.paintingCellsInStartGame();
		this.board.drawBoard(cells);
	}

	@Override
	public void select(int x, int y, int x2, int y2) {
        this.logic.clearCellProgressChecked();
        if (this.logic.progressCheck(x, y, x2, y2)) {
            this.logic.clearCellChecked(); // Очищаем флажки клеток
            this.logic.movePaintedCell(x, y, x2, y2);  // Перемещаем большой шарик на выбранную клетку
            this.logic.createBigCells(); // С маленьких шариков делаем большие
            if (this.logic.checkingCells(x2, y2)) { // Очищаем поле, если игрок собрал n в ряд
                // Если игрок не собрал n в ряд, то создаём n новых маленьких шаров
                try {
                    this.logic.createSmallCells(); // Создаем новые маленькие шарики
                } catch (NotEmptyCellsException e) {
                    System.out.println(e.getMessage());
                }
            }

            Main.setScore("Ваш счет: " + this.logic.getScore() + " ");
            System.out.println("Ваш счет: " + this.logic.getScore() + "\n");

            if (this.logic.finish()) {
                this.board.drawLosing();
            } else {
                this.board.drawSelect();
            }
        } else {
            Main.setScore("Перекрыто!");
            try {
                this.board.cancelCheckedClick();
            } catch (NullMethodException e) {
                System.out.println(e.getMessage());
            }
        }
	}
}
