package ru.minesweeperLesson.minesweeper.interfaces;

/**
 * Логика игры
 */
public interface ILogic {

	/**
	 * Загружаем поле игры
     */
	void loadBoard(ICell[][] cells);

	/**
	 * Количество бомб
     */
	int sumBombs();

	/**
	 * Количество рядов
     */
	int sumRow();

	/**
	 * Количество столбцов
     */
	int sumColumn();

	/**
	 * Размер всего поля
     */
	ICell[][] sizeField();

	/**
	 * Проверка: нужно ли бахнуть.
	 * Если пользователь выбрал клетку где бомба, то мы взрываемся
	 */
	boolean shouldBang(int x, int y);

	/**
	 * Если пользователь всё разгадал, то мы выводим поздравление
     */
	boolean finish();

	/**
	 * Событие какое приходит от пользователя
	 * После мы должны отметить у себя в бизнес-логике это событие
	 * @param x координата
	 * @param y координата
	 * @param bomb есть ли бомба на клетке
     */
	void suggest(int x, int y, boolean bomb);

	/**
	 * Проверка первого хода
	 * @return Если на поле нет бомб, возвращаем истину
     */
	boolean checkTheFirstMove();

	/**
	 * Очистка вокруг ячейки при первом ходе
     */
	void clearAroundCell(int x, int y);

	/**
	 * Генерация бомб на поле
	 */
	void bombsGeneration();

	/**
	 * Возвращаем количество бомб вокруг ячейки
	 */
	int checkingAroundCell(int x, int y);

	/**
	 * Проверка и открытие пустых ячеек
	 */
	void openEmptyCells(int x, int y);
}
