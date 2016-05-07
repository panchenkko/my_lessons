package ru.minesweeperLesson.minesweeper.interfaces;

import ru.minesweeperLesson.minesweeper.levels.Easy;
import ru.minesweeperLesson.minesweeper.levels.Expert;
import ru.minesweeperLesson.minesweeper.levels.Medium;

/**
 * Выбор уровня игры
 */

public interface ISelectLevel {

    /**
     * Инициализируем лёгкий уровень
     * @return возвращаем уровень
     */
    Easy easy();

    /**
     * Инициализируем средний уровень
     * @return возвращаем уровень
     */
    Medium medium();

    /**
     * Инициализируем уровень эксперт
     * @return возвращаем уровень
     */
    Expert expert();
}