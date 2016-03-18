package ru.fiveInARow.console;

import ru.fiveInARow.interfaces.ICell;

import java.io.PrintStream;

/**
 * Реализация ячейки в консоли
 */
// PrintStream - входящий поток, какой реализует нашу консоль
public class ConsoleCell implements ICell<PrintStream> {

	private boolean smallPaint;
	private boolean bigPaint;

	private boolean suggestEmpty = false;

	private boolean checked = false;

	public ConsoleCell(boolean smallPaint, boolean bigPaint) {
		this.smallPaint = smallPaint;
		this.bigPaint = bigPaint;
	}

	@Override
	public boolean isPaintCell() {
		return this.smallPaint;
	}

	@Override
	public void paintCell() {
		this.smallPaint = true;
	}

	@Override
	public boolean isPaintedCell() {
		return this.bigPaint;
	}

	@Override
	public void paintedCell() {
		this.bigPaint = true;
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
	public void draw(PrintStream paint) {
		if (this.isPaintedCell())
			paint.print("\033[1;41m[O]\033[0m ");
		else if (this.isPaintCell())
			paint.print("[\033[1;31mo\033[0m] ");
		else {
			paint.print("[ ] ");
		}
	}
}
