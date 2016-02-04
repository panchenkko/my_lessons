package ru.minesweeperLesson.minesweeper.Interfaces;

/**
 * Генерация самого игрового поля
 */
public interface IGeneratorBoard {
	/**
	 * Метод генерации поля
	 * Это может быть: сложный, средний или легкий уровень
 	 */
	ICell[][] generate();
}
