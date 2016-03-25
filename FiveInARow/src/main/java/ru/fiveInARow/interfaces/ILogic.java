package ru.fiveInARow.interfaces;

/**
 * Логика игры
 */
public interface ILogic {

	int score();

	int getScore();

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

	void clearCellChecked();

	/**
	 * Перемещаем закрашенную клетку, на выбранную позицию пользователем
	 */
	void movePaintedCell(int x, int y, int x2, int y2);

	/**
	 * Генерируем в начале игры 3 полностью закрашенные ячейки и 3 не полностью закрашенные
	 */
	void paintingCellsInStartGame();

	/**
	 * Генерация полностью и не полностью закрашенных ячеек
	 */
	void createBigCells();
	void createSmallCells();

	/**
	 * Проверка и если пользователь собрал n в ряд, делаем эти ячейки пустые
	 */
	void clearCells(int x2, int y2);

	int _9_00_(int x, int y);
	int _10_30_(int x, int y);
	int _12_00_(int x, int y);
	int _13_30_(int x, int y);
	int _15_00_(int x, int y);
	int _16_30_(int x, int y);
	int _18_00_(int x, int y);
	int _19_30_(int x, int y);

	void checkCell_9_00_(int x, int y);
	void checkCell_10_30_(int x, int y);
	void checkCell_12_00_(int x, int y);
	void checkCell_13_30_(int x, int y);
	void checkCell_15_00_(int x, int y);
	void checkCell_16_30_(int x, int y);
	void checkCell_18_00_(int x, int y);
	void checkCell_19_30_(int x, int y);
}
