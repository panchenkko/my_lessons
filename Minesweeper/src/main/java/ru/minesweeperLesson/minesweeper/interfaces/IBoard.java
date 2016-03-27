package ru.minesweeperLesson.minesweeper.interfaces;

/**
 * Описывает поведения доски.
 */
public interface IBoard {

	int returnSumBomb();

	void cancelSuggestBomb(int x, int y);

	boolean returnSuggestBomb(int x, int y);

	/**
	 * Рисует доску исходя из входящего массива ячеек.
	 * @param cells Массив ячеек.
	 */
	void drawBoard(ICell[][] cells);

	/**
	 * Рисует ячейку.
	 * @param x позиция по горизонтали.
	 * @param y позиция по вертикали.
	 */
	void drawCell(int x, int y);

	/**
	 * Рисует взрыв всех бомб.
	 */
	void drawBang();

	/**
	 * Рисует поздравление когда игра выиграна.
	 */
	void drawCongratulate();

	/**
	 * Рисует проигрыш.
	 */
	void drawLosing();
}
