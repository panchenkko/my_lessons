package ru.minesweeper.interfaces;

/**
 * Описываем поведение ячейки
 */

public interface ICell<T> {

    /**
     * Является ли данная ячейка бомбой
     * @return если данная ячейка бомба, возвращаем true
     */
	boolean isBomb();

    /**
     * Является ли данная ячейка помеченной как бомба
     * @return если данная ячейка помечена как бомба, возвращаем true
     */
	boolean isSuggestBomb();

    /**
     * Является ли данная ячейка уже открытой
     * @return если данная ячейка уже открыта, возвращаем true
     */
	boolean isSuggestEmpty();

    /**
     * Помечаем данную ячейку как открытую
     */
	void suggestEmpty();

    /**
     * Помечаем данную ячейку как бомбу
     */
    void thisBomb();

    /**
     * Помечаем данную ячейку помеченную как бомба
     */
	void suggestBomb();

    /**
     * Отменяем значение помеченной бомбы
     */
	void cancelSuggestBomb();

    /**
     * Является ли данная ячейка проверенной
     * @return если данная ячейка является проверенной, возвращаем true
     */
	boolean isChecked();

	/**
	 * Помечаем данную ячейку как проверенную
	 */
	void checked();

    /**
     * Является ли данная ячейка какой-то из этих цифр
     * @return если данная ячейка является, то возвращаем true
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
	 * Помечаем данную ячейку как цифру (от 1 до 8)
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
	 * Рисуем ячейку
	 * @param paint Класс прорироски (консольный или графический)
	 * @param real рисовать реальное значение ячейки или же что предположил пользователь
     */
	void draw(T paint, boolean real);

    /**
     * Для GUI нужны доп. параметры, для нахождения координат данной ячейки
     */
	void draw(T paint, int x, int y, boolean real);
}