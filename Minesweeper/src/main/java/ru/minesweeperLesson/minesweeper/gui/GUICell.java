package ru.minesweeperLesson.minesweeper.gui;

import ru.minesweeperLesson.minesweeper.interfaces.ICell;

import javax.swing.*;
import java.awt.*;

public class GUICell implements ICell<Graphics> {

	private boolean bomb;
	private boolean suggestBomb = false;
	private boolean suggestEmpty = false;

	public GUICell(boolean bomb) {
		this.bomb = bomb;
	}

	@Override
	public boolean isBomb() {
		return this.bomb;
	}

	@Override
	public boolean isSuggestBomb() {
		return this.suggestBomb;
	}

	@Override
	public boolean isSuggestEmpty() {
		return this.suggestEmpty;
	}

	@Override
	public void suggestEmpty() {
		this.suggestEmpty = true;
	}

	@Override
	public void suggestBomb() {
		this.suggestBomb = true;
	}

	@Override
	public boolean isChecked() {
		return false;
	}

	@Override
	public void checked() {

	}

	@Override
	public boolean isSuggest1() {
		return false;
	}

	@Override
	public boolean isSuggest2() {
		return false;
	}

	@Override
	public boolean isSuggest3() {
		return false;
	}

	@Override
	public boolean isSuggest4() {
		return false;
	}

	@Override
	public boolean isSuggest5() {
		return false;
	}

	@Override
	public boolean isSuggest6() {
		return false;
	}

	@Override
	public boolean isSuggest7() {
		return false;
	}

	@Override
	public boolean isSuggest8() {
		return false;
	}

	@Override
	public void suggest1() {

	}

	@Override
	public void suggest2() {

	}

	@Override
	public void suggest3() {

	}

	@Override
	public void suggest4() {

	}

	@Override
	public void suggest5() {

	}

	@Override
	public void suggest6() {

	}

	@Override
	public void suggest7() {

	}

	@Override
	public void suggest8() {

	}

	public void draw(Graphics paint, boolean real) {
		if (real) {
			if (this.isBomb()) {
				paint.setColor(Color.red);
			} else {
				paint.setColor(Color.green);
			}
		} else {
			if (this.suggestBomb) {
				paint.setColor(Color.yellow);
			} else if (this.suggestEmpty) {
				paint.setColor(Color.green);
			} else {
				paint.setColor(Color.gray);
			}
		}
	}
}
