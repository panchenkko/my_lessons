package ru.fiveInARow.interfaces;

/**
 * Описывает поведение ячейки
 */
public interface ICell<T> {

	/**
	 * Является ли клетка не полностью закрашенной
	 */
	boolean isPaintCell();

	/**
	 * Устанавливаем значение не полностью закрашенной клетки
	 */
	void paintCell();

	/**
	 * Является ли клетка полностью закрашенной
	 */
	boolean isPaintedCell();

	/**
	 * Устанавливаем значение полностью закрашенной клетки
	 */
	void paintedCell();

	/**
	 * Пустая ли клетка
	 */
	boolean isSuggestEmpty();

	/**
	 * Устанавливаем значение пустой клетки
	 */
	void suggestEmpty();

	/**
	 * Была ли проверена данная ячейка
     */
	boolean isChecked();

	/**
	 * Устанавливаем значение, что ячейка была проверена
	 */
	void checked();

	/**
	 * Рисуем клетку
	 * @param paint Сама прорисовка (это якобы то, на чем мы будем рисовать)
     */
	void draw(T paint);
}
