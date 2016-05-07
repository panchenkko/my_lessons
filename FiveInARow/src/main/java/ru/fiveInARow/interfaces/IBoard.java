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

	void drawSelect();

	void drawCongratulate();

	/**
	 * Рисует проигрыш.
	 */
	void drawLosing();
}
