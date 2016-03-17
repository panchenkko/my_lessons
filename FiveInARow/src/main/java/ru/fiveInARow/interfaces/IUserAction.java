package ru.fiveInARow.interfaces;

/**
 * Действия пользователя
 * Считывание данных от пользователя
 */
public interface IUserAction {

	/**
	 * Кнопка инициализации игры
	 */
	void initGame();

	/**
	 * Что вводит пользователь
	 *
	 *  Где находится клетка
	 * @param x Место по горизонтали
	 * @param y Место по вертикали
	 *  Куда переместится клетка
	 * @param x2 Место по горизонтали
	 * @param y2 Место по вертикали
	 */
	void select(int x, int y, int x2, int y2);
}
