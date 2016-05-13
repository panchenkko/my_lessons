package ru.minesweeperLesson.minesweeper.interfaces;

/**
 * Содержит количество рядов, столбцов и количество бомб на поле
 */

public interface ITheNumOfTheField {

    /**
     * Количество бомб
     * @return возвращаем количество бомб исходя от сложности выбранного уровня
     */
    int sumBombs();

    /**
     * Количество рядов
     * @return возвращаем количество рядов исходя от сложности выбранного уровня
     */
    int sumRow();

    /**
     * Количество столбцов
     * @return возвращаем количество столбцов исходя от сложности выбранного уровня
     */
    int sumColumn();
}