package ru.minesweeper.interfaces;

import ru.minesweeper.levels.Easy;
import ru.minesweeper.levels.Expert;
import ru.minesweeper.levels.Medium;

/**
 * Выбор уровня пользователем
 */
public interface ISelectLevel {
    Easy easy();
    Medium medium();
    Expert expert();
}
