package ru.minesweeperLesson.minesweeper.interfaces;

/**
 * Действия пользователя
 * Считывание данных от пользователя
 */
public interface IUserAction {
	// Кнопка инициализации игры
	void initGame();
	// Действие пользователя, что он выбрал какую-то ячейку
	void select(int x, int y, boolean bomb);
}
