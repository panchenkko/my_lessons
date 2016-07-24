package ru.minesweeper.console;

import ru.minesweeper.interfaces.IBoard;
import ru.minesweeper.interfaces.ICell;
import ru.minesweeper.interfaces.IBoard;
import ru.minesweeper.interfaces.ICell;

/**
 * Реализация поля в консоли
 */

public class ConsoleBoard implements IBoard {

    private ICell[][] cells;

    private boolean isFinish = false;

    @Override
    public int returnSumBomb() {
        return 0;
    }

    @Override
    public boolean isFinish() {
        return isFinish;
    }

    @Override
    public void setIsFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }

    @Override
    public void cancelSuggestBomb(int x, int y) {
    }

    @Override
    public boolean returnSuggestBomb(int x, int y) {
        return false;
    }

    @Override
    public void drawBoard(ICell[][] cells) {
        this.cells = cells;
        this.redraw(false);
    }

    @Override
    public void drawCell(int x, int y) {
        System.out.println("********** ВЫБОР **********");
        this.redraw(false);
    }

    @Override
    public void drawBang() {
        System.out.println("********** БУМ **********");
        this.redraw(true);
    }

    @Override
    public void drawCongratulate() {
        System.out.println("********** ВЫ ВЫИГРАЛИ! **********");
    }

    @Override
    public void drawLosing() {
        System.out.println("********** ВЫ ПРОИГРАЛИ! **********");
    }

    private void redraw(boolean real) {
        int space, numRow = 0;
        for (ICell[] row : cells) {
            // Нумерация столбцов
            if (numRow < 1) {
                for (int i = 1; i <= row.length; i++) {
                    if (i == 1) space = 4;
                    else if (i > 10) space = 2;
                    else space = 3;
                    for (int j = 0; j < space; j++)
                        System.out.print(" ");
                    System.out.print(i);
                }
                System.out.println();
            }
            // Нумерация рядов
            if (numRow < 9) System.out.print(" ");
            System.out.print(++numRow + " ");

            for (ICell cell : row) {
                cell.draw(System.out, real);
            }
            System.out.println();
        }
        System.out.println();
    }
}
