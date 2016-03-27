package ru.minesweeperLesson.minesweeper.interfaces;

/**
 * Описывает поведение ячейки
 */
public interface ICell<T> {

	/**
	 * Бомба ли это
     */
	boolean isBomb();

	/**
	 * Пользователь предположил что это бомба
     */
	boolean isSuggestBomb();

	/**
	 * Пользователь предположил что это пустая клетка
     */
	boolean isSuggestEmpty();

	/**
	 * Устанавливает значение пустой клетки
	 */
	void suggestEmpty();

	/**
	 * Устанавливает значение бомбы
	 */
	void suggestBomb();

	void cancelSuggestBomb();

	/**
	 * Была ли проверена данная ячейка
     */
	boolean isChecked();

	/**
	 * Устанавливаем значение, что ячейка была проверена
	 */
	void checked();

	/**
	 * Проверка цифр
     */
	boolean isSuggest1();
	boolean isSuggest2();
	boolean isSuggest3();
	boolean isSuggest4();
	boolean isSuggest5();
	boolean isSuggest6();
	boolean isSuggest7();
	boolean isSuggest8();

	/**
	 * Устанавливаем цифру для ячейки
	 */
	void suggest1();
	void suggest2();
	void suggest3();
	void suggest4();
	void suggest5();
	void suggest6();
	void suggest7();
	void suggest8();

	/**
	 * Рисует клетку
	 * @param paint Сама прорисовка (это якобы то, на чем мы будем рисовать)
	 * @param real Рисовать реальную клетку или же что предположил пользователь
     */
	void draw(T paint, boolean real);

	void draw(T graphics, int x, int y, boolean real);
}
