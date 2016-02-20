package ru.minesweeperLesson.minesweeper.levels;

import ru.minesweeperLesson.minesweeper.interfaces.TheNumOfTheField;

/**
 * Реализация легкого уровня игры
 */
public class Easy implements TheNumOfTheField {

	@Override
	public int sumBombs() {
		return 3;
	}

	@Override
	public int sumRow() {
		return 5;
	}

	@Override
	public int sumColumn() {
		return 5;
	}
}
