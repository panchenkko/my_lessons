package ru.minesweeper;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.minesweeper.gui.GUICell;
import ru.minesweeper.interfaces.ICell;
import ru.minesweeper.levels.Expert;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LogicAndLevelExpertTest {

    private static StandardLogic standardLogic;

    private Expert expert;
    private ICell[][] cells;

    @BeforeClass
    public static void setUpClass() throws Exception {
        standardLogic = new StandardLogic();
    }

    @Before
    public void setUp() throws Exception {
        expert = standardLogic.expert();
        cells = standardLogic.sizeField();
        standardLogic.loadBoard(cells);

        for (int i = 0; i < expert.sumRow(); i++) {
            for (int j = 0; j < expert.sumColumn(); j++) {
                cells[i][j] = new GUICell(false);
            }
        }
    }

    @Test
    public void testSumBombs() throws Exception {
        // Проверяем на кол-во бомб
        assertThat(standardLogic.sumBombs(), is(99));
    }

    @Test
    public void testSumRow() throws Exception {
        // Проверяем на кол-во рядов
        assertThat(standardLogic.sumRow(), is(30));
    }

    @Test
    public void testSumColumn() throws Exception {
        // Проверяем на кол-во столбцов
        assertThat(standardLogic.sumColumn(), is(16));
    }

    @Test
    public void testSizeField() throws Exception {
        // Проверяем на размер поля
        assertThat(standardLogic.sizeField().length, is(30));
    }

    @Test
    public void testAllCellsIsOnlyEmpty() throws Exception {
        // Проверяем, что в начале игры все клетки являются ТОЛЬКО пустыми и не открытыми.
        for (int i = 0; i < expert.sumRow(); i++) {
            for (int j = 0; j < expert.sumColumn(); j++) {
                assertFalse(cells[i][j].isBomb());
                assertFalse(cells[i][j].isChecked());
                assertFalse(cells[i][j].isSuggest1());
                assertFalse(cells[i][j].isSuggest2());
                assertFalse(cells[i][j].isSuggest3());
                assertFalse(cells[i][j].isSuggest4());
                assertFalse(cells[i][j].isSuggest5());
                assertFalse(cells[i][j].isSuggest6());
                assertFalse(cells[i][j].isSuggest7());
                assertFalse(cells[i][j].isSuggest8());
                assertFalse(cells[i][j].isSuggestBomb());
                assertFalse(cells[i][j].isSuggestEmpty());
            }
        }
    }

    @Test
    public void testFinish() throws Exception {
        // Имитируем выигрыш игрока
        cells[1][1] = new GUICell(true);
        cells[3][3] = new GUICell(true);
        cells[5][5] = new GUICell(true);
        cells[7][7] = new GUICell(true);
        cells[9][9] = new GUICell(true);

        for (int i = 0; i < expert.sumRow(); i++) {
            for (int j = 0; j < expert.sumColumn(); j++) {
                if (cells[i][j].isBomb()) cells[i][j].suggestBomb();
                if (!cells[i][j].isBomb()) cells[i][j].suggestEmpty();
            }
        }

        assertTrue(standardLogic.finish());
    }

    @Test
    public void testShouldBang() throws Exception {
        // Проверка на взрыв бомбы, если игрок попытался открыть клетку с бомбой
        cells[5][5] = new GUICell(true);

        assertTrue(standardLogic.shouldBang(5, 5));
    }

    @Test
    public void testSuggestTrue() throws Exception {
        // Проверка, что игрок отметил клетку с бомбой
        standardLogic.suggest(5, 5, true);

        assertTrue(cells[5][5].isSuggestBomb());
    }

    @Test
    public void testSuggestFalse() throws Exception {
        // Проверка, что игрок открывает нужную ему клетку
        standardLogic.suggest(5, 5, false);

        assertTrue(cells[5][5].isSuggestEmpty());
    }

    @Test
    public void testSuggestFalseAndSuggestBomb() throws Exception {
        // Проверка, что игрок пытается открыть клетку, какую ранее пометил как бомбу
        standardLogic.suggest(5, 5, true);
        standardLogic.suggest(5, 5, false);

        assertTrue(cells[5][5].isSuggestBomb());
        assertFalse(cells[5][5].isSuggestEmpty());
    }

    @Test
    public void testCheckTheFirstMove() throws Exception {
        // На поле содержатся только пустые клетки (Проверка на первый ход)
        assertTrue(standardLogic.checkTheFirstMove());
    }

    @Test
    public void testCheckTheFirstMoveFalse() throws Exception {
        // Проверка первого хода по кол-ву бомб на поле (Должно быть 0)
        cells[5][5] = new GUICell(true);

        assertFalse(standardLogic.checkTheFirstMove());
    }

    @Test
    public void testClearAroundCell() throws Exception {
        // Проверка, что при первом ходе, откроются также соседние клетки
        cells[5][5].suggestEmpty();

        standardLogic.clearAroundCell(5, 5);

        assertTrue(cells[5][5].isSuggestEmpty());
        assertTrue(cells[4][4].isSuggestEmpty());
        assertTrue(cells[4][5].isSuggestEmpty());
        assertTrue(cells[4][6].isSuggestEmpty());
        assertTrue(cells[5][4].isSuggestEmpty());
        assertTrue(cells[5][6].isSuggestEmpty());
        assertTrue(cells[6][4].isSuggestEmpty());
        assertTrue(cells[6][5].isSuggestEmpty());
        assertTrue(cells[6][6].isSuggestEmpty());
    }

    @Test
    public void testBombsGeneration() throws Exception {
        // Проверка, что вначале игры сгенерировалось нужное кол-во бомб
        standardLogic.bombsGeneration();

        int count = 0;

        for (int i = 0; i < standardLogic.sumRow(); i++) {
            for (int j = 0; j < standardLogic.sumColumn(); j++) {
                if (cells[i][j].isBomb()) count++;
            }
        }

        assertThat(count, is(standardLogic.sumBombs()));
    }

    @Test
    public void testCheckingAroundCell() throws Exception {
        // Проверка, что клетка корректно подсчитываем кол-во соседних бомб
        cells[4][4] = new GUICell(true);
        cells[4][5] = new GUICell(true);
        cells[4][6] = new GUICell(true);
        cells[5][4] = new GUICell(true);
        cells[5][6] = new GUICell(true);
        cells[6][4] = new GUICell(true);
        cells[6][5] = new GUICell(true);
        cells[6][6] = new GUICell(true);

        assertThat(standardLogic.checkingAroundCell(5, 5), is(8));
    }

    @Test
    public void testArrangeValues() throws Exception {
        // Проверка, что после подсчета кол-ва соседних бомб, клетке дается нужная цифра
        cells[4][4] = new GUICell(true);
        cells[4][5] = new GUICell(true);
        cells[4][6] = new GUICell(true);
        cells[5][4] = new GUICell(true);
        cells[5][6] = new GUICell(true);
        cells[6][4] = new GUICell(true);
        cells[6][5] = new GUICell(true);
        cells[6][6] = new GUICell(true);

        standardLogic.arrangeValues(5, 5);

        assertTrue(cells[5][5].isSuggest8());
    }

    @Test
    public void testOpenEmptyCells() throws Exception {
        // Создаем по границам поля бомбы и открываем одну из пустых клеток.
        // Откроет ли все пустые клетки.
        for (int i = 0; i < standardLogic.sumRow(); i++) {
            for (int j = 0; j < standardLogic.sumColumn(); j++) {
                if (i < 3 || j < 3 || i > standardLogic.sumRow() - 4 || j > standardLogic.sumColumn() - 4)
                    cells[i][j] = new GUICell(true);
            }
        }

        cells[4][4].suggestEmpty();

        standardLogic.openEmptyCells();

        for (int i = 3; i <= standardLogic.sumRow() - 4; i++) {
            for (int j = 3; j <= standardLogic.sumColumn() - 4; j++) {
                assertTrue(cells[i][j].isSuggestEmpty());
            }
        }
    }
}