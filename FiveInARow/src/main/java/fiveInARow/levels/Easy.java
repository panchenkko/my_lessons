package fiveInARow.levels;

import fiveInARow.interfaces.TheNumOfTheField;

/**
 * Реализация легкого уровня игры
 */
public class Easy implements TheNumOfTheField {

	@Override
	public int sumBombs() {
		return 10;
	}

	@Override
	public int sumRow() {
		return 9;
	}

	@Override
	public int sumColumn() {
		return 9;
	}
}
