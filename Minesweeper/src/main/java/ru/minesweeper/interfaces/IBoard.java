package ru.minesweeper.interfaces;

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
     * Флажок, какой равен true, если игра уже подошла к концу
     * @return возвращаем значение флажка
     */
	boolean isFinish();

    /**
     * Флажок, какой равен true, если игра уже подошла к концу
     * @param isFinish меняем значение флажка
     */
    void setIsFinish(boolean isFinish);

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