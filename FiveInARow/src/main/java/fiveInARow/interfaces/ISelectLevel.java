package fiveInARow.interfaces;

import fiveInARow.levels.Easy;
import fiveInARow.levels.Expert;
import fiveInARow.levels.Medium;

/**
 * Выбор уровня пользователем
 */
public interface ISelectLevel {
    Easy easy();
    Medium medium();
    Expert expert();
}
