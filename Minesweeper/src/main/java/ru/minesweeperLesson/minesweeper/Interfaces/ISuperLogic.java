package ru.minesweeperLesson.minesweeper.Interfaces;

/**
 * Логика игры
 */
public interface ISuperLogic {

	// Загружаем поле игры
	void loadBoard(ICell[][] ICells);

	int sumBombs();

	int sizeRow();

	int sizeColumn();

	ICell[][] sizeField();

	/**
	 * Проверка: нужно ли бахнуть.
	 * Если пользователь выбрал клетку где бомба, то мы взрываемся
	 * Здесь описываем логику, как должна работать наша программа
	 */
	boolean shouldBang(int x, int y);
	// Проверка: если пользователь всё разгадал, то мы его поздравляем
	boolean finish();

	/**
	 * Событие какое приходит от пользователя
	 * После мы должны отметить у себя в бизнес-логике это событие
	 * @param x координата
	 * @param y координата
	 * @param bomb есть ли бомба на клетке
     */
	void suggest(int x, int y, boolean bomb);
}
