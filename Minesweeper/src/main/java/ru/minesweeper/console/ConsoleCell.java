package ru.minesweeper.console;

import ru.minesweeper.interfaces.ICell;
import ru.minesweeper.interfaces.ICell;

import java.io.PrintStream;

/**
 * Реализация ячейки в консоли
 */

// PrintStream - входящий поток, какой реализует нашу консоль
public class ConsoleCell implements ICell<PrintStream> {

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

	public ConsoleCell(boolean bomb) {
		this.bomb = bomb;
	}

	@Override
	public boolean isBomb() {
		return this.bomb;
	}

	@Override
	public void thisBomb() {
		this.bomb = true;
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
	public void suggestBomb() {
		this.suggestBomb = true;
	}

	@Override
	public void cancelSuggestBomb() {
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
	public void draw(PrintStream paint, boolean real) {
		if (real) {
            if (this.isBomb()) paint.print("[\033[1;31m*\033[0m] ");

            else if (this.suggest8) paint.print("[\033[1;31m8\033[0m] ");
            else if (this.suggest7) paint.print("[\033[1;31m7\033[0m] ");
            else if (this.suggest6) paint.print("[\033[1;35m6\033[0m] ");
            else if (this.suggest5) paint.print("[\033[1;36m5\033[0m] ");
            else if (this.suggest4) paint.print("[\033[1;34m4\033[0m] ");
            else if (this.suggest3) paint.print("[\033[1;32m3\033[0m] ");
            else if (this.suggest2) paint.print("[\033[1;33m2\033[0m] ");
            else if (this.suggest1) paint.print("[\033[1;30m1\033[0m] ");

            else paint.print("[ ] ");
        }
		else {
            if (this.suggestBomb) paint.print("[\033[1;31m?\033[0m] ");

            else if (this.suggestEmpty) {

                     if (this.suggest8) paint.print("[\033[1;31m8\033[0m] ");
                else if (this.suggest7) paint.print("[\033[1;31m7\033[0m] ");
                else if (this.suggest6) paint.print("[\033[1;35m6\033[0m] ");
                else if (this.suggest5) paint.print("[\033[1;36m5\033[0m] ");
                else if (this.suggest4) paint.print("[\033[1;34m4\033[0m] ");
                else if (this.suggest3) paint.print("[\033[1;32m3\033[0m] ");
                else if (this.suggest2) paint.print("[\033[1;33m2\033[0m] ");
                else if (this.suggest1) paint.print("[\033[1;30m1\033[0m] ");

                else paint.print("[ ] ");
            }

			else paint.print("[Х] ");
		}
	}

	@Override
	public void draw(PrintStream graphics, int x, int y, boolean real) {
	}
}