package ru.minesweeperLesson.minesweeper.Interfaces;

/**
 * Описывает поведение ячейки
 */
public interface ICell<T> {

	// Бомба ли это
	boolean isBomb();
	// Пользователь предположил что это бомба
	boolean isSuggestBomb();
	// Пользователь предположил что это пустая клетка
	boolean isSuggestEmpty();
	// Устанавливает значение пустой клетки
	void suggestEmpty();
	// Устанавливает значение бомбы
	void suggestBomb();

	/**
	 * Рисует клетку
	 * @param paint Сама прорисовка (это якобы то, на чем мы будем рисовать)
	 * @param real Рисовать реальную клетку или же что предположил пользователь
     */
	void draw(T paint, boolean real);
}
