package ru.fiveInARow.interfaces;

/**
 * Описывает поведение ячейки
 */
public interface ICell<T> {

	/**
	 * Является ли клетка не полностью закрашенной
	 */
	boolean isSmallCellPainted();

	/**
	 * Устанавливаем значение не полностью закрашенной клетки
	 */
	void smallCellPainting();

	void cancelSmallCellPainting();

	/**
	 * Является ли клетка полностью закрашенной
	 */
	boolean isBigCellPainted();

	/**
	 * Устанавливаем значение полностью закрашенной клетки
	 */
	void bigCellPainting();

	void cancelBigCellPainting();

	void redCell();
	void greenCell();
	void blueCell();
	void yellowCell();
	void magentaCell();
	void cyanCell();

	boolean isRedCell();
	boolean isGreenCell();
	boolean isBlueCell();
	boolean isYellowCell();
	boolean isMagentaCell();
	boolean isCyanCell();

	/**
	 * Пустая ли клетка
	 */
	boolean isSuggestEmpty();

	/**
	 * Устанавливаем значение пустой клетки
	 */
	void suggestEmpty();

	void cancelSuggestEmpty();

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

	void selectColor(T paint, String symbol);

	void generateColor(int numColor);
}
