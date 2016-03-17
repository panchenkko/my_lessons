package ru.fiveInARow.interfaces;

/**
 * Описывает поведения доски.
 */
public interface IBoard {

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
	 * Рисует, когда пользователь сложил пять в ряд
	 */
	void drawCongratulate();

	/**
	 * Рисует проигрыш.
	 */
	void drawLosing();
}
