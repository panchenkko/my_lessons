package ru.minesweeperLesson.minesweeper.interfaces;

import ru.minesweeperLesson.minesweeper.logics.Easy;
import ru.minesweeperLesson.minesweeper.logics.Expert;
import ru.minesweeperLesson.minesweeper.logics.Medium;

public interface SelectLevelInterface {

    Easy easy();

    Medium medium();

    Expert expert();
}
