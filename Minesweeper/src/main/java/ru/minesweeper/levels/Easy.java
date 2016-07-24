package ru.minesweeper.levels;

import ru.minesweeper.interfaces.ITheNumOfTheField;
import ru.minesweeper.interfaces.ITheNumOfTheField;

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
