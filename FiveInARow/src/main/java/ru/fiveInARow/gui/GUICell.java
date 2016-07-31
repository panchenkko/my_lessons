package ru.fiveInARow.gui;

import ru.fiveInARow.interfaces.ICell;

import java.awt.*;

public class GUICell implements ICell<Graphics> {

	private boolean smallPaint = false;
	private boolean bigPaint = false;

	private boolean suggestEmpty = false;

	private boolean checked = false;
	private boolean progressChecked = false;
	private boolean checkedClick = false;

	private boolean red = false;
	private boolean green = false;
	private boolean blue = false;
	private boolean yellow = false;
	private boolean magenta = false;
	private boolean cyan = false;

    public GUICell() {
        suggestEmpty = true;
    }

    @Override
	public boolean isSmallCellPainted() {
		return this.smallPaint;
	}

	@Override
	public void smallCellPainting() {
		this.smallPaint = true;
        this.suggestEmpty = false;
    }

	@Override
	public void cancelSmallCellPainting() {
		this.smallPaint = false;
	}

	@Override
	public boolean isBigCellPainted() {
		return this.bigPaint;
	}

	@Override
	public void bigCellPainting() {
		this.bigPaint = true;
        this.suggestEmpty = false;
    }

	@Override
	public void cancelBigCellPainting() {
		this.bigPaint = false;

        this.red = false;
        this.green = false;
        this.blue = false;
        this.yellow = false;
        this.magenta = false;
        this.cyan = false;
	}



	@Override
	public void redCell() {
		this.red = true;
	}

	@Override
	public void greenCell() {
		this.green = true;
	}

	@Override
	public void blueCell() {
		this.blue = true;
	}

	@Override
	public void yellowCell() {
		this.yellow = true;
	}

	@Override
	public void magentaCell() {
		this.magenta = true;
	}

	@Override
	public void cyanCell() {
		this.cyan = true;
	}



	@Override
	public boolean isRedCell() {
		return this.red;
	}

	@Override
	public boolean isGreenCell() {
		return this.green;
	}

	@Override
	public boolean isBlueCell() {
		return this.blue;
	}

	@Override
	public boolean isYellowCell() {
		return this.yellow;
	}

	@Override
	public boolean isMagentaCell() {
		return this.magenta;
	}

	@Override
	public boolean isCyanCell() {
		return this.cyan;
	}

	@Override
	public void cancelAllColor() {
		this.red = false;
        this.green = false;
        this.blue = false;
        this.yellow = false;
        this.magenta = false;
        this.cyan = false;
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
	public boolean isChecked() {
		return this.checked;
	}

	@Override
	public void checked() {
		this.checked = true;
	}

	@Override
	public void cancelChecked() {
		this.checked = false;
	}

    @Override
    public boolean isProgressChecked() {
        return this.progressChecked;
    }

    @Override
    public void progressChecked() {
        this.progressChecked = true;
    }

    @Override
    public void cancelProgressChecked() {
        this.progressChecked = false;
    }

    @Override
	public void selectColor(Graphics paint, String symbol) {
		     if (isRedCell()) paint.setColor(Color.red);
		else if (isGreenCell()) paint.setColor(Color.green);
		else if (isBlueCell()) paint.setColor(Color.blue);
		else if (isYellowCell()) paint.setColor(Color.yellow);
		else if (isMagentaCell()) paint.setColor(Color.magenta);
		else if (isCyanCell()) paint.setColor(Color.cyan);

		else paint.setColor(Color.black);
	}

	@Override
	public void generateColor(int numColor) {
		switch (numColor) {
			case 0: redCell();
				    break;
			case 1: greenCell();
				    break;
			case 2: blueCell();
				    break;
			case 3: yellowCell();
				    break;
			case 4: magentaCell();
				    break;
			case 5: cyanCell();
				    break;
		}
	}

	@Override
	public int returnColor() {
		int color;

		     if (isRedCell()) color = 0;
		else if (isGreenCell()) color = 1;
		else if (isBlueCell()) color = 2;
		else if (isYellowCell()) color = 3;
		else if (isMagentaCell()) color = 4;
		else if (isCyanCell()) color = 5;

		else color = 6;

		return color;
	}

	@Override
	public void draw(Graphics paint) {
	}

	@Override
	public void draw(Graphics paint, int x, int y) {
		Color color = new Color(0,0,0);
		paint.drawRect(x * GUIBoard.getPADDING(), y * GUIBoard.getPADDING(),
				           GUIBoard.getPADDING(), GUIBoard.getPADDING());
		paint.setColor(color);

		x = x * GUIBoard.getPADDING() + 16;
		y = y * GUIBoard.getPADDING() + 25;

        if (isBigCellPainted()) {
			selectColor(paint, null);
            if (isCheckedClick())
                paint.fillOval(x - 13, y - 22, 39, 39);
            else
                paint.fillOval(x - 9, y - 18, 31, 31);
        } else if (isSmallCellPainted()) {
            selectColor(paint, null);
            paint.fillOval(x + 1, y - 8, 12, 12);
        } else if (isChecked()) {
            selectColor(paint, null);
            paint.fillOval(x + 3, y - 6, 8, 8);
        } else if (isSuggestEmpty()) {
			selectColor(paint, null);
		}
    }

	@Override
	public boolean isCheckedClick() {
		return this.checkedClick;
	}

	@Override
	public void checkedClick() {
		this.checkedClick = true;
	}

	@Override
	public void cancelCheckedClick() {
		this.checkedClick = false;
	}
}
