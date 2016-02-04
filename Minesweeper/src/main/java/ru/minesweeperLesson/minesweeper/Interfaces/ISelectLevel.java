package ru.minesweeperLesson.minesweeper.Interfaces;

import ru.minesweeperLesson.minesweeper.Logics.Easy;
import ru.minesweeperLesson.minesweeper.Logics.Expert;
import ru.minesweeperLesson.minesweeper.Logics.Medium;

/**
 * Выбор уровня пользователем
 */
public interface ISelectLevel {
    Easy easy();
    Medium medium();
    Expert expert();
}
