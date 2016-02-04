package ru.minesweeperLesson.minesweeper.Interfaces;

/**
 * Описывает поведения доски.
 */
public interface IBoard {

	/**
	 * Рисует доску исходя из входящего массива ячеек.
	 * @param ICells Массив ячеек.
	 */
	void drawBoard(ICell[][] ICells);

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
