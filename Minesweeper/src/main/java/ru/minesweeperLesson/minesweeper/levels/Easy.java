package ru.minesweeperLesson.minesweeper.levels;

import ru.minesweeperLesson.minesweeper.interfaces.ITheNumOfTheField;

public class Easy implements ITheNumOfTheField {

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
