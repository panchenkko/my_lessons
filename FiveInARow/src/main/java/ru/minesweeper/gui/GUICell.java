package ru.minesweeper.gui;

import ru.minesweeper.interfaces.ICell;

import javax.swing.*;
import java.awt.*;

public class GUICell extends JPanel implements ICell<Graphics> {

	private boolean bomb;
	private boolean suggestBomb = false;
	private boolean suggestEmpty = false;

	private boolean checked = false;

	private boolean suggest1 = false;
	private boolean suggest2 = false;
	private boolean suggest3 = false;
	private boolean suggest4 = false;
	private boolean suggest5 = false;
	private boolean suggest6 = false;
	private boolean suggest7 = false;
	private boolean suggest8 = false;

	private Image[] img;

	public GUICell(boolean bomb) {
		this.bomb = bomb;

		img = new Image[13];

		for (int i = 0; i < 13; i++) {
			img[i] = (new ImageIcon(i + ".png")).getImage();
		}
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
		return this.checked;
	}

	@Override
	public void checked() {
		this.checked = true;
	}

	@Override
	public boolean isSuggest1() {
		return this.suggest1;
	}

	@Override
	public boolean isSuggest2() {
		return this.suggest2;
	}

	@Override
	public boolean isSuggest3() {
		return this.suggest3;
	}

	@Override
	public boolean isSuggest4() {
		return this.suggest4;
	}

	@Override
	public boolean isSuggest5() {
		return this.suggest5;
	}

	@Override
	public boolean isSuggest6() {
		return this.suggest6;
	}

	@Override
	public boolean isSuggest7() {
		return this.suggest7;
	}

	@Override
	public boolean isSuggest8() {
		return this.suggest8;
	}

	@Override
	public void suggest1() {
		this.suggest1 = true;
	}

	@Override
	public void suggest2() {
		this.suggest2 = true;
	}

	@Override
	public void suggest3() {
		this.suggest3 = true;
	}

	@Override
	public void suggest4() {
		this.suggest4 = true;
	}

	@Override
	public void suggest5() {
		this.suggest5 = true;
	}

	@Override
	public void suggest6() {
		this.suggest6 = true;
	}

	@Override
	public void suggest7() {
		this.suggest7 = true;
	}

	@Override
	public void suggest8() {
		this.suggest8 = true;
	}

	@Override
	public void draw(Graphics paint, boolean real) {

	}

	@Override
	public void draw(Graphics paint, int x, int y, boolean real) {
		x = x < 1 ? 17 : 58;
		y = y < 1 ? 25 : 66;
		if (real) {
			if (this.isBomb()) {
				paint.setColor(Color.red);
				paint.fillRect(x, y, x, y);
				paint.drawString("*", x, y);
//				paint.drawImage(img[9], x * 40, y * 40, new GUIBoard());
			} else {
				paint.drawString("-", x, y);
			}
		} else {
			if (this.suggestBomb) {
				paint.drawString("?", x, y);
			} else if (this.suggestEmpty) {
				paint.drawString("-", x, y);
			} else {
				paint.drawString("X", x, y);
			}
		}
	}
}
