package ru.minesweeperLesson.minesweeper;

import ru.minesweeperLesson.minesweeper.console.ConsoleBoard;
import ru.minesweeperLesson.minesweeper.console.ConsoleCell;
import ru.minesweeperLesson.minesweeper.interfaces.ICell;
import ru.minesweeperLesson.minesweeper.interfaces.IGeneratorBoard;

import static org.junit.Assert.*;

public class BaseActionMyTest {

    LevelSelection level = new LevelSelection();
    ICell[][] cells = level.sizeField();
    final BaseAction action = new BaseAction(level, new ConsoleBoard(), new IGeneratorBoard() {
        @Override
        public ICell[][] generate() {
            for (int i = 0; i < level.sumRow(); i++)
                for (int j = 0; j < level.sumColumn(); j++)
                    cells[i][j] = new ConsoleCell(false);
            System.out.println("Бомб: " + level.sumBombs());
            System.out.println("Пустых клеток: " + ((level.sumRow() * level.sumColumn()) - level.sumBombs()));
            return cells;
        }
    });
}