package ru.fiveInARow.console;

import ru.fiveInARow.interfaces.ICell;

import java.io.PrintStream;
import java.util.Set;

/**
 * Реализация ячейки в консоли
 */
// PrintStream - входящий поток, какой реализует нашу консоль
public class ConsoleCell implements ICell<PrintStream> {

	private boolean smallPaint = false;
	private boolean bigPaint = false;

	private boolean suggestEmpty = false;

	private boolean checked = false;
	private boolean progressChecked = false;

	private boolean red = false;
	private boolean green = false;
	private boolean blue = false;
	private boolean yellow = false;
	private boolean magenta = false;
	private boolean cyan = false;

    public ConsoleCell() {
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
	public void draw(PrintStream paint) {
		     if (isBigCellPainted()) selectColor(paint, "O");
		else if (isSmallCellPainted()) selectColor(paint, "*");
		else if (isSuggestEmpty()) paint.print("[ ] ");
	}

	@Override
	public void draw(PrintStream graphics, int x, int y) {
	}

	@Override
	public boolean isCheckedClick() {
		return false;
	}

	@Override
	public void checkedClick() {
	}

	@Override
	public void cancelCheckedClick() {
	}

	@Override
	public void selectColor(PrintStream paint, String symbol) {
		     if (isRedCell()) paint.printf("[\033[1;31m%s\033[0m] ", symbol);
		else if (isGreenCell()) paint.printf("[\033[1;32m%s\033[0m] ", symbol);
		else if (isBlueCell()) paint.printf("[\033[1;34m%s\033[0m] ", symbol);
		else if (isYellowCell()) paint.printf("[\033[1;33m%s\033[0m] ", symbol);
		else if (isMagentaCell()) paint.printf("[\033[1;35m%s\033[0m] ", symbol);
		else if (isCyanCell()) paint.printf("[\033[1;36m%s\033[0m] ", symbol);
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
    public String toString() {
        return "ConsoleCell{" +
                "smallPaint=" + smallPaint +
                ", bigPaint=" + bigPaint +
                ", suggestEmpty=" + suggestEmpty +
                ", checked=" + checked +
                ", red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                ", yellow=" + yellow +
                ", magenta=" + magenta +
                ", cyan=" + cyan +
                '}';
    }
}
