package ru.minesweeper.interfaces;

import ru.minesweeper.levels.Easy;
import ru.minesweeper.levels.Expert;
import ru.minesweeper.levels.Medium;

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