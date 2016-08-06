package ru.fiveInARow.interfaces;

import ru.fiveInARow.exceptions.NullMethodException;

/**
 * Описывает поведения доски.
 */
public interface IBoard {

    /**
     * Если пользователь не выбрал ячейку, помечаем ячейку как выбранную
     * @param x Координата переданной ячейки
     * @param y Координата переданной ячейки
     */
    void checkingClick(int x, int y) throws NullMethodException;

    void cancelCheckedClick() throws NullMethodException;

	/**
	 * Рисует доску исходя из входящего массива ячеек.
	 * @param cells Массив ячеек.
	 */
	void drawBoard(ICell[][] cells);

	void drawSelect();

	/**
	 * Рисует проигрыш.
	 */
	void drawLosing();
}
