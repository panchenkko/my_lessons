package ru.minesweeperLesson.minesweeper.interfaces;

import ru.minesweeperLesson.minesweeper.levels.Easy;
import ru.minesweeperLesson.minesweeper.levels.Expert;
import ru.minesweeperLesson.minesweeper.levels.Medium;

/**
 * Выбор уровня пользователем
 */
public interface ISelectLevel {
    Easy easy();
    Medium medium();
    Expert expert();
}
