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

	void cancelAllColor();

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

	void cancelChecked();

    /**
     * Была ли проверена данная ячейка
     */
    boolean isProgressChecked();

    /**
     * Устанавливаем значение, что ячейка была проверена
     */
    void progressChecked();

    void cancelProgressChecked();

	void selectColor(T paint, String symbol);

	void generateColor(int numColor);

	int returnColor();

	/**
	 * Рисуем клетку
	 * @param paint Сама прорисовка (это якобы то, на чем мы будем рисовать)
     */
	void draw(T paint);

	void draw(T graphics, int x, int y);


	boolean isCheckedClick();

	void checkedClick();

	void cancelCheckedClick();
}
