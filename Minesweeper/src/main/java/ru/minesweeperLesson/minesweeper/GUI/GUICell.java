package ru.minesweeperLesson.minesweeper.GUI;

import ru.minesweeperLesson.minesweeper.Interfaces.ICell;

import java.awt.*;

public class GUICell implements ICell<Graphics> {

	public boolean isBomb() {
		return false;
	}

	public boolean isSuggestBomb() {
		return false;
	}

	public boolean isSuggestEmpty() {
		return false;
	}

	public void suggestEmpty() {
	}

	public void suggestBomb() {
	}

	public void draw(Graphics paint, boolean real) {

	}
}
