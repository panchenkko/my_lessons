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
        if (!this.suggestBomb) this.suggestEmpty = true;
	}

    @Override
    public void thisBomb() {
        this.bomb = true;
    }

	@Override
	public void suggestBomb() {
		this.suggestBomb = true;
	}

	@Override
	public void cancelSuggestBomb() {
		this.suggestBomb = false;
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
		paint.setFont(new Font("Comic Sans MS", Font.BOLD, 17));

		paint.setColor(new Color(187,187,187));
		paint.drawRect(x * GUIBoard.getPADDING(),
					   y * GUIBoard.getPADDING(),
				           GUIBoard.getPADDING(),
						   GUIBoard.getPADDING());

		x = x * GUIBoard.getPADDING() + 16;
		y = y * GUIBoard.getPADDING() + 25;

		if (real) {

			if (this.isBomb()) {
				paint.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
				paint.setColor(Color.red);
				paint.drawString("*", x - 4, y + 15);
			}

			else if (this.suggest8) {
				paint.setColor(Color.black);
				paint.drawString("8", x, y);
			}
			else if (this.suggest7) {
				paint.setColor(Color.black);
				paint.drawString("7", x, y);
			}
			else if (this.suggest6) {
				paint.setColor(Color.black);
				paint.drawString("6", x, y);
			}
			else if (this.suggest5) {
				paint.setColor(new Color(136,96,44));
				paint.drawString("5", x, y);
			}
			else if (this.suggest4) {
				paint.setColor(new Color(34,44,118));
				paint.drawString("4", x, y);
			}
			else if (this.suggest3) {
				paint.setColor(new Color(255,0,0));
				paint.drawString("3", x, y);
			}
			else if (this.suggest2) {
				paint.setColor(new Color(45,146,40));
				paint.drawString("2", x, y);
			}
			else if (this.suggest1) {
				paint.setColor(new Color(0,13,255));
				paint.drawString("1", x, y);
			}

		} else {

			if (this.suggestBomb) {
                paint.setColor(Color.black);
				paint.drawLine(x - 16, y - 25, x + 25, y + 15);
				paint.drawLine(x + 25, y - 25, x - 16, y + 15);
				paint.drawString("?", x, y);
			}

            else

			if (this.suggestEmpty) {
                if (this.suggest8) {
                    paint.setColor(Color.black);
                    paint.drawString("8", x, y);
                } else if (this.suggest7) {
                    paint.setColor(Color.black);
                    paint.drawString("7", x, y);
                } else if (this.suggest6) {
                    paint.setColor(Color.black);
                    paint.drawString("6", x, y);
                } else if (this.suggest5) {
                    paint.setColor(new Color(136, 96, 44));
                    paint.drawString("5", x, y);
                } else if (this.suggest4) {
                    paint.setColor(new Color(34, 44, 118));
                    paint.drawString("4", x, y);
                } else if (this.suggest3) {
                    paint.setColor(new Color(255, 0, 0));
                    paint.drawString("3", x, y);
                } else if (this.suggest2) {
                    paint.setColor(new Color(45, 146, 40));
                    paint.drawString("2", x, y);
                } else if (this.suggest1) {
                    paint.setColor(new Color(0, 13, 255));
                    paint.drawString("1", x, y);
                } else
                    paint.drawString("", x - 2, y);
            }
			else {
				paint.drawLine(x - 16, y - 25, x + 25, y + 15);
				paint.drawLine(x + 25, y - 25, x - 16, y + 15);
			}
		}
	}
}