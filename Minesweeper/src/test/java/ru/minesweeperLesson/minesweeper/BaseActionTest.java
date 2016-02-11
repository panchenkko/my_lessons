package ru.minesweeperLesson.minesweeper;

import org.junit.Test;
import ru.minesweeperLesson.minesweeper.interfaces.ICell;
import ru.minesweeperLesson.minesweeper.interfaces.IGeneratorBoard;
import ru.minesweeperLesson.minesweeper.console.ConsoleBoard;
import ru.minesweeperLesson.minesweeper.console.ConsoleCell;
import ru.minesweeperLesson.minesweeper.levels.Easy;

public class BaseActionTest {

    final BaseAction action = new BaseAction(new Easy(), new ConsoleBoard(), new IGeneratorBoard() {
        @Override
        public ICell[][] generate() {
            return new ICell[][] {
                    {new ConsoleCell(true), new ConsoleCell(false)},
                    {new ConsoleCell(true), new ConsoleCell(false)}
            };
        }
    });

    @Test
    public void successGame() {
        action.initGame();
        action.select(0, 0, true);
        action.select(1, 0, true);
        action.select(0, 1, false);
        action.select(1, 1, false);
    }

    @Test
    public void failureGame() {
        action.initGame();
        action.select(0, 0, true);
        action.select(1, 0, false);
    }
}