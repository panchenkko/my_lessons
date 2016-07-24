package ru.minesweeper.interfaces;

/**
 * Логика игры
 */

public interface ILogic {

	/**
	 * Загружаем поле игры
     */
	void loadBoard(ICell[][] cells);

    /**
     * Инициализируем двухмерный массив, за счет методов sumRow() и sumColumn()
     * @return возвращаем размер всего поля в виде двухмерного массива
     */
	ICell[][] sizeField();

    /**
     * Взрыв бомбы
     * @param x координата
     * @param y координата
     * @return если пользователь выбрал клетку где бомба, то взрываемся
     */
	boolean shouldBang(int x, int y);

    /**
     * Конец игры
     * @return если пользователь всё разгадал, возвращаем истину
     */
	boolean finish();

    /**
     * Предположения пользователя (Бомба или пустая клетка)
     * @param x координата
     * @param y координата
     * @param bomb есть ли бомба на клетке
     */
	void suggest(int x, int y, boolean bomb);

    /**
     * Проверка первого хода.
     * @return если на поле нет бомб, возвращаем истину
     */
	boolean checkTheFirstMove();

    /**
     * Очистка вокруг ячейки при первом ходе
     * (Для того, чтобы у пользователя не открылась в начале игры только одна ячейка)
     * @param x координата
     * @param y координата
     */
	void clearAroundCell(int x, int y);

    /**
     * Генерация бомб на поле
     */
	void bombsGeneration();

    /**
     * Возвращаем количество бомб вокруг ячейки
     * @param x координата
     * @param y координата
     * @return количество бомб вокруг ячейки
     */
	int checkingAroundCell(int x, int y);

	/**
	 * Расставляем значения в клетках (от 1-го до 8-ми) после первого клика
	 */
	void beforeGameArrangeValues();

    /**
     * Открываем пустые ячейки
     */
	void openEmptyCells();
}