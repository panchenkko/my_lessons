package ru.fiveInARow.interfaces;

/**
 * Логика игры
 */
public interface ILogic {

	/**
	 * Загружаем поле игры
     */
	void loadBoard(ICell[][] cells);

	/**
	 * Кол-во рядов
	 */
	int sumRow();

	/**
	 * Кол-во столбцов
	 */
	int sumColumn();

	/**
	 * Размер всего поля
     */
	ICell[][] sizeField();

	/**
	 * Кол-во создаваемых за ход окрашенных клеток
	 */
	int sumSmallCellsPainted();

	int sumInARow();

	/**
	 * Если все ячейки полностью закрашенные, то заканчиваем игру
     */
	boolean finish();

	/**
	 * Событие какое приходит от пользователя
	 *
	 * Проверяем, действительно ли первая клетка закрашенная
	 *
	 *  Где находится клетка
	 * @param x Место по горизонтали
	 * @param y Место по вертикали
	 */
	boolean checkingCells(int x, int y, int x2, int y2);

	/**
	 * Проверка первого хода
	 * @return Если все ячейки пустые, возвращаем истину
	 */
	boolean checkTheFirstMove();

	/**
	 * Перемещаем закрашенную клетку, на выбранную позицию пользователем
	 */
	void movePaintedCell(int x, int y, int x2, int y2);

	void selectColorForNewCell(int x, int y, int x2, int y2);

	/**
	 * Генерируем в начале игры 3 полностью закрашенные ячейки и 3 не полностью закрашенные
	 */
	void paintingCellsInStartGame();

	/**
	 * Генерация полностью и не полностью закрашенных ячеек
	 */
	void createSmallCells();
	void createBigCells();

	void checkingAroundCell(int x, int y);

	/**
	 * Проверка и если пользователь собрал n в ряд, делаем эти ячейки пустые
	 */
	void clearCells();
}
