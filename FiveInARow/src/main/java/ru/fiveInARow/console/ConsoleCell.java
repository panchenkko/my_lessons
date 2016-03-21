package ru.fiveInARow.console;

import ru.fiveInARow.interfaces.ICell;

import java.io.PrintStream;

/**
 * Реализация ячейки в консоли
 */
// PrintStream - входящий поток, какой реализует нашу консоль
public class ConsoleCell implements ICell<PrintStream> {

	private boolean smallPaint = false;
	private boolean bigPaint = false;

	private boolean suggestEmpty = false;

	private boolean checked = false;

	private boolean red = false;
	private boolean green = false;
	private boolean blue = false;
	private boolean yellow = false;
	private boolean magenta = false;
	private boolean cyan = false;

	@Override
	public boolean isSmallCellPainted() {
		return this.smallPaint;
	}

	@Override
	public void smallCellPainting() {
		this.smallPaint = true;
	}

	@Override
	public void cancelSmallCellPainting() {
		this.smallPaint = false;

//		this.red = false;
//		this.green = false;
//		this.blue = false;
//		this.yellow = false;
//		this.magenta = false;
//		this.cyan = false;
	}

	@Override
	public boolean isBigCellPainted() {
		return this.bigPaint;
	}

	@Override
	public void bigCellPainting() {
		this.bigPaint = true;
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
	public boolean isSuggestEmpty() {
		return this.suggestEmpty;
	}

	@Override
	public void suggestEmpty() {
		this.suggestEmpty = true;
	}

	@Override
	public void cancelSuggestEmpty() {
		this.suggestEmpty = false;
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
	public void draw(PrintStream paint) {
		if (this.isBigCellPainted())
			selectColor(paint, "O");
		else if (this.isSmallCellPainted())
			selectColor(paint, "*");
		else if (this.isSuggestEmpty())
			paint.print("[ ] ");
	}

	@Override
	public void selectColor(PrintStream paint, String symbol) {
		if (this.isRedCell())
			paint.printf("[\033[1;31m%s\033[0m] ", symbol);
		else if (this.isGreenCell())
			paint.printf("[\033[1;32m%s\033[0m] ", symbol);
		else if (this.isBlueCell())
			paint.printf("[\033[1;34m%s\033[0m] ", symbol);
		else if (this.isYellowCell())
			paint.printf("[\033[1;33m%s\033[0m] ", symbol);
		else if (this.isMagentaCell())
			paint.printf("[\033[1;35m%s\033[0m] ", symbol);
		else if (this.isCyanCell())
			paint.printf("[\033[1;36m%s\033[0m] ", symbol);
	}

	@Override
	public void generateColor(int numColor) {
		switch (numColor) {
			case 0: this.redCell();
					break;
			case 1: this.greenCell();
					break;
			case 2: this.blueCell();
					break;
			case 3: this.yellowCell();
					break;
			case 4: this.magentaCell();
					break;
			case 5: this.cyanCell();
					break;
		}
	}

	@Override
	public int checkColor() {
		int color;
		if (this.isRedCell())
			color = 0;
		else if (this.isGreenCell())
			color = 1;
		else if (this.isBlueCell())
			color = 2;
		else if (this.isYellowCell())
			color = 3;
		else if (this.isMagentaCell())
			color = 4;
		else if (this.isCyanCell())
			color = 5;
		else
			color = 6;
		return color;
	}
}
