package ru.minesweeper.interfaces;

/**
 * Действия пользователя
 */

public interface IUserAction {

    /**
     * После того как пользователь выбрал уровень и мы сгенерировали поле,
     * передаем все значения для отрисовки поля
     */
	void initGame();

    /**
     * Ловим координаты, какие выбрал пользователь и ловим значение,
     * какой пользователь передал ячейке
     * @param x координата
     * @param y координата
     * @param bomb значение
     */
	void select(int x, int y, boolean bomb);
}
