package ru.minesweeperLesson.minesweeper.interfaces;

/**
 * Описываем поведение доски
 */

public interface IBoard {

    /**
     * Количество помеченных бомб
     * @return сколько помеченных бомб
     */
	int returnSumBomb();

    /**
     * Отменяем данную ячейку как помеченную
     * @param x координата
     * @param y координата
     */
	void cancelSuggestBomb(int x, int y);

	/**
	 * Помеченная ли ячейка
	 * @param x координата
	 * @param y координата
	 * @return если данная ячейка помечена как бомба, то возвращаем true
	 */
	boolean returnSuggestBomb(int x, int y);

	/**
	 * Рисуем доску исходя из входящего массива ячеек
	 * @param cells массив ячеек
	 */
	void drawBoard(ICell[][] cells);

	/**
	 * Рисуем ячейку.
	 * @param x координата
	 * @param y координата
	 */
	void drawCell(int x, int y);

	/**
	 * Рисуем взрыв всех бомб
	 */
	void drawBang();

	/**
	 * Рисуем поздравление когда игра выиграна
	 */
	void drawCongratulate();

	/**
	 * Рисуем проигрыш
	 */
	void drawLosing();
}