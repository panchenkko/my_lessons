package ru.fiveInARow.console;

import ru.fiveInARow.interfaces.ICell;

import java.io.PrintStream;

/**
 * Реализация ячейки в консоли
 */
// PrintStream - входящий поток, какой реализует нашу консоль
public class ConsoleCell implements ICell<PrintStream> {

	private boolean suggestEmpty = false;

	private boolean checked = false;


	public ConsoleCell(boolean bomb) {
		this.bomb = bomb;
	}

	@Override
	public boolean isPaintCell() {
		return false;
	}

	@Override
	public void paintCell() {

	}

	@Override
	public boolean isPaintedCell() {
		return false;
	}

	@Override
	public void painedCell() {

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
		if (real)
			if (this.isBomb())
				paint.print("[\033[1;31m*\033[0m] ");
			else
				paint.print("[ ] ");
		else {
			if (this.suggestBomb)
				paint.print("[\033[1;31m?\033[0m] ");

			else if (this.suggest8)
				paint.print("[\033[1;31m8\033[0m] ");
			else if (this.suggest7)
				paint.print("[\033[1;31m7\033[0m] ");
			else if (this.suggest6)
				paint.print("[\033[1;35m6\033[0m] ");
			else if (this.suggest5)
				paint.print("[\033[1;36m5\033[0m] ");
			else if (this.suggest4)
				paint.print("[\033[1;34m4\033[0m] ");
			else if (this.suggest3)
				paint.print("[\033[1;32m3\033[0m] ");
			else if (this.suggest2)
				paint.print("[\033[1;33m2\033[0m] ");
			else if (this.suggest1)
				paint.print("[\033[1;30m1\033[0m] ");

			else if (this.suggestEmpty)
				paint.print("[ ] ");
			else
				paint.print("[Х] ");
		}
	}
}
