package ru.fiveInARow;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.fiveInARow.exceptions.NotEmptyCellsException;
import ru.fiveInARow.gui.GUICell;
import ru.fiveInARow.interfaces.ICell;

import java.util.Random;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LogicTest {

    private static Logic logic;

    private ICell[][] cells;

    @BeforeClass
    public static void setUpClass() throws Exception {
        logic = new Logic();
    }

    @Before
    public void setUp() throws Exception {
        // Загружаем поле, инициализируем каждую клетку в поле и делаем её пустую
        cells = logic.sizeField();
        logic.loadBoard(cells);

        for (int i = 0; i < logic.sumRow(); i++) {
            for (int j = 0; j < logic.sumColumn(); j++) {
                cells[i][j] = new GUICell();
            }
        }
    }

    @Test
    public void testFinish_AllBig() throws Exception {
        // Проверка, что если в поле все клетки заполнены большими шарами, то пользователь проиграл
        Random rand = new Random();

        for (int i = 0; i < logic.sumRow(); i++) {
            for (int j = 0; j < logic.sumColumn(); j++) {
                cells[i][j].generateColor(rand.nextInt(6));
                cells[i][j].bigCellPainting();
            }
        }

        assertTrue(logic.finish());
    }

    @Test
    public void testFinish_OneSmall() throws Exception {
        // Проверка, что если все клетки с большими шарами, а одна с маленьким, то пользователь проиграл
        Random rand = new Random();

        for (int i = 0; i < logic.sumRow(); i++) {
            for (int j = 0; j < logic.sumColumn(); j++) {
                cells[i][j].generateColor(rand.nextInt(6));

                if (i == 0 && j == 0)
                    cells[i][j].smallCellPainting();
                else
                    cells[i][j].bigCellPainting();
            }
        }

        assertTrue(logic.finish());
    }

    @Test
    public void testFinish_OneEmpty() throws Exception {
        // Проверка, что если все клетки с большими шарами, а одна пустая, то пользователь проиграл
        Random rand = new Random();

        for (int i = 0; i < logic.sumRow(); i++) {
            for (int j = 0; j < logic.sumColumn(); j++) {
                if (i >= 0 || j >= 0) {
                    cells[i][j].generateColor(rand.nextInt(6));
                    cells[i][j].bigCellPainting();
                }
            }
        }

        assertTrue(logic.finish());
    }

    @Test
    public void testFinishFalse_TwoSmall() throws Exception {
        // Проверка, что если все клетки с большими шарами, а две с маленькими, то пользователь не проиграл
        Random rand = new Random();

        for (int i = 0; i < logic.sumRow(); i++) {
            for (int j = 0; j < logic.sumColumn(); j++) {
                cells[i][j].generateColor(rand.nextInt(6));

                if ((i == 0 && j == 0) || (i == 0 && j == 1))
                    cells[i][j].smallCellPainting();
                else
                    cells[i][j].bigCellPainting();
            }
        }

        assertFalse(logic.finish());
    }

    @Test
    public void testSumEmptyCells() throws Exception {
        // Проверяем, что кол-во пустых клеток совпадает с правдой
        cells[0][0].bigCellPainting();

        assertThat(logic.sumEmptyCells(), is(logic.sumRow() * logic.sumColumn() - 1));
    }

    @Test
    public void testCheckingCells_BigBall_On_EmptyCell() throws Exception {
        // Проверяем, что если пользователь перемещает клетку с большим мячиком на пустую, то всё хорошо
        cells[0][0].bigCellPainting();
        cells[0][1].suggestEmpty();

        assertTrue(logic.checkingCells(0, 0, 0, 1));
    }

    @Test
    public void testCheckingCells_BigBall_On_SmallBall() throws Exception {
        // Проверяем, что если пользователь перемещает клетку с большим мячиком на клетку с маленьким, то всё хорошо
        cells[0][0].bigCellPainting();
        cells[0][1].smallCellPainting();

        assertTrue(logic.checkingCells(0, 0, 0, 1));
    }

    @Test
    public void testCheckingCellsFalse_BigBall_On_BigCell() throws Exception {
        // Проверяем, что если пользователь перемещает клетку с большим мячиком на клетку с большим, то всё плохо
        cells[0][0].bigCellPainting();
        cells[0][1].bigCellPainting();

        assertFalse(logic.checkingCells(0, 0, 0, 1));
    }

    @Test
    public void testClearCellChecked() throws Exception {
        // Проверяем, что если есть проверенные клетки, то после вызова метода они становятся не проверенными
        Random rand = new Random();

        int count = 0;

        while (count != 3) {
            int x = rand.nextInt(logic.sumRow());
            int y = rand.nextInt(logic.sumColumn());
            if (!cells[x][y].isChecked()) {
                cells[x][y].checked();
                count++;
            }
        }

        logic.clearCellChecked();

        for (int i = 0; i < logic.sumRow(); i++) {
            for (int j = 0; j < logic.sumColumn(); j++) {
                assertFalse(cells[i][j].isChecked());
            }
        }
    }

    @Test
    public void testPaintingCellsInStartGame() throws Exception {
        // Проверяем, что кол-во созданных вначале игры шариков, совпадает с нужным кол-вом
        logic.paintingCellsInStartGame();

        int count = 0;

        for (int i = 0; i < logic.sumRow(); i++) {
            for (int j = 0; j < logic.sumColumn(); j++) {
                if (cells[i][j].isBigCellPainted() || cells[i][j].isSmallCellPainted())
                    count++;
            }
        }

        assertThat(count, is(logic.sumCellsPainted() + logic.sumCellsPainted()));
    }

    @Test
    public void testMovePaintedCell_BigInEmpty() throws Exception {
        // Проверяем, что если переместить большой шар с одной клетки на другую пустую клетку, то все старые данные
        // о клетках будут стерты, а новые присвоены
        cells[0][0].bigCellPainting();
        cells[0][0].redCell();
        cells[0][1].suggestEmpty();

        logic.movePaintedCell(0, 0, 0, 1);

        assertTrue(cells[0][0].isSuggestEmpty());
        assertFalse(cells[0][0].isRedCell());

        assertTrue(cells[0][1].isBigCellPainted());
        assertTrue(cells[0][1].isRedCell());
    }

    @Test
    public void testMovePaintedCell_BigInSmall() throws Exception {
        // Проверяем, что если переместить большой шар на клетку с маленьким шаром, то все старые данные о клетках
        // будут серты, а новые присвоены
        cells[0][0].bigCellPainting();
        cells[0][0].redCell();
        cells[0][1].smallCellPainting();
        cells[0][1].blueCell();

        logic.movePaintedCell(0, 0, 0, 1);

        assertTrue(cells[0][0].isSuggestEmpty());
        assertFalse(cells[0][0].isRedCell());

        assertTrue(cells[0][1].isBigCellPainted());
        assertTrue(cells[0][1].isRedCell());
        assertFalse(cells[0][1].isBlueCell());
    }

    @Test
    public void testCreateBigCells() throws Exception {
        // Проверяем, что маленькие шары, после вызова метода превращаются в большие шары
        cells[0][0].smallCellPainting();
        cells[0][1].smallCellPainting();
        cells[1][0].smallCellPainting();
        cells[1][1].smallCellPainting();

        logic.createBigCells();

        assertTrue(cells[0][0].isBigCellPainted());
        assertTrue(cells[0][1].isBigCellPainted());
        assertTrue(cells[1][0].isBigCellPainted());
        assertTrue(cells[1][1].isBigCellPainted());

        assertFalse(cells[0][0].isSmallCellPainted());
        assertFalse(cells[0][1].isSmallCellPainted());
        assertFalse(cells[1][0].isSmallCellPainted());
        assertFalse(cells[1][1].isSmallCellPainted());
    }

    @Test
    public void testCreateSmallCells() throws Exception {
        // Проверяем, что метод создает указанное кол-во маленьких шаров
        logic.createSmallCells();

        int count = 0;

        for (int i = 0; i < logic.sumRow(); i++) {
            for (int j = 0; j < logic.sumColumn(); j++) {
                if (cells[i][j].isSmallCellPainted())
                    count++;
            }
        }

        assertThat(count, is(logic.sumCellsPainted()));
    }

    @Test
    public void testCreateSmallCells_IfSomeNEmptyCells() throws Exception {
        // Проверяем, что метод сможет создать новые маленькие шары, если на поле ровно logic.sumCellsPainted()
        // свободных ячеек
        for (int i = 0; i < logic.sumRow(); i++) {
            for (int j = 0; j < logic.sumColumn(); j++) {
                if (i > 0 || (i == 0 && j >= logic.sumCellsPainted()))
                    cells[i][j].bigCellPainting();
            }
        }

        logic.createSmallCells();

        int count = 0;

        for (int i = 0; i < logic.sumRow(); i++) {
            for (int j = 0; j < logic.sumColumn(); j++) {
                if (cells[i][j].isSmallCellPainted() || cells[i][j].isSuggestEmpty())
                    count++;
            }
        }

        assertThat(count, is(logic.sumCellsPainted()));
    }

    @Test(expected = NotEmptyCellsException.class)
    public void testCreateSmallCellsFalse_IfFullFieldBusy() throws Exception {
        // Проверяем, что метод не сможет создать новые маленькие шары, если всё поле занято большими шарами
        for (int i = 0; i < logic.sumRow(); i++) {
            for (int j = 0; j < logic.sumColumn(); j++) {
                cells[i][j].bigCellPainting();
            }
        }

        logic.createSmallCells();
    }

    @Test(expected = NotEmptyCellsException.class)
    public void testCreateSmallCellsFalse_IfSomeTheRightHalfOfTheCells() throws Exception {
        // Заполняем всё поле большими шарами, кроме value ячеек, их оставляем пустые.
        // Проверяем, что если не хватит одного места для создания всех маленьких шаров, то выдаст ошибку.
        int value = logic.sumCellsPainted() - 1;
        for (int i = 0; i < logic.sumRow(); i++) {
            for (int j = 0; j < logic.sumColumn(); j++) {
                if (i < logic.sumRow() - 1 || (i == logic.sumRow() - 1 && j < logic.sumColumn() - value))
                    cells[i][j].bigCellPainting();
            }
        }

        logic.createSmallCells();
    }

    @Test
    public void testCreateSmallBalls() throws Exception {
        logic.createBalls(true, false);

        int count = 0;
        for (int i = 0; i < logic.sumRow(); i++) {
            for (int j = 0; j < logic.sumColumn(); j++) {
                if (cells[i][j].isSmallCellPainted()) count++;
            }
        }

        assertThat(count, is(logic.sumCellsPainted()));
    }

    @Test
    public void testCreateBigBalls() throws Exception {
        logic.createBalls(false, true);

        int count = 0;
        for (int i = 0; i < logic.sumRow(); i++) {
            for (int j = 0; j < logic.sumColumn(); j++) {
                if (cells[i][j].isBigCellPainted()) count++;
            }
        }

        assertThat(count, is(logic.sumCellsPainted()));
    }

    @Test
    public void testCreateSmallAndBigBalls() throws Exception {
        logic.createBalls(true, true);

        int countSmallBalls = 0;
        int countBigBalls = 0;
        for (int i = 0; i < logic.sumRow(); i++) {
            for (int j = 0; j < logic.sumColumn(); j++) {
                if (cells[i][j].isSmallCellPainted()) countSmallBalls++;
                if (cells[i][j].isBigCellPainted()) countBigBalls++;
            }
        }

        assertThat(countSmallBalls, is(logic.sumCellsPainted()));
        assertThat(countBigBalls, is(logic.sumCellsPainted()));
    }

    @Test
    public void testClearCells() throws Exception {
        // Проверяем, что если шар появится на ячейке, где будет совпадение сразу по горизонтали и по вертикали, то
        // очистит с двух сторон
        cells[0][2].bigCellPainting();
        cells[1][2].bigCellPainting();
        cells[3][2].bigCellPainting();
        cells[4][2].bigCellPainting();

        cells[2][2].bigCellPainting();

        cells[2][0].bigCellPainting();
        cells[2][1].bigCellPainting();
        cells[2][3].bigCellPainting();
        cells[2][4].bigCellPainting();


        cells[0][2].redCell();
        cells[1][2].redCell();
        cells[3][2].redCell();
        cells[4][2].redCell();

        cells[2][2].redCell();

        cells[2][0].redCell();
        cells[2][1].redCell();
        cells[2][3].redCell();
        cells[2][4].redCell();

        assertFalse(logic.checkingCells(2, 2));

        assertFalse(cells[0][2].isBigCellPainted());
        assertFalse(cells[1][2].isBigCellPainted());
        assertFalse(cells[3][2].isBigCellPainted());
        assertFalse(cells[4][2].isBigCellPainted());

        assertFalse(cells[2][2].isBigCellPainted());

        assertFalse(cells[2][0].isBigCellPainted());
        assertFalse(cells[2][1].isBigCellPainted());
        assertFalse(cells[2][3].isBigCellPainted());
        assertFalse(cells[2][4].isBigCellPainted());


        assertTrue(cells[0][2].isSuggestEmpty());
        assertTrue(cells[1][2].isSuggestEmpty());
        assertTrue(cells[3][2].isSuggestEmpty());
        assertTrue(cells[4][2].isSuggestEmpty());

        assertTrue(cells[2][2].isSuggestEmpty());

        assertTrue(cells[2][0].isSuggestEmpty());
        assertTrue(cells[2][1].isSuggestEmpty());
        assertTrue(cells[2][3].isSuggestEmpty());
        assertTrue(cells[2][4].isSuggestEmpty());
    }

    @Test
    public void testClearBalls() throws Exception {
        // Проверяем, что очистит все "checked" ячейки
        cells[0][0].bigCellPainting();
        cells[0][1].bigCellPainting();
        cells[0][2].bigCellPainting();
        cells[0][3].bigCellPainting();
        cells[0][4].bigCellPainting();

        cells[0][0].checked();
        cells[0][1].checked();
        cells[0][2].checked();
        cells[0][3].checked();
        cells[0][4].checked();

        logic.clearBalls();

        assertFalse(cells[0][0].isBigCellPainted());
        assertFalse(cells[0][1].isBigCellPainted());
        assertFalse(cells[0][2].isBigCellPainted());
        assertFalse(cells[0][3].isBigCellPainted());
        assertFalse(cells[0][4].isBigCellPainted());

        assertTrue(cells[0][0].isSuggestEmpty());
        assertTrue(cells[0][1].isSuggestEmpty());
        assertTrue(cells[0][2].isSuggestEmpty());
        assertTrue(cells[0][3].isSuggestEmpty());
        assertTrue(cells[0][4].isSuggestEmpty());

        assertThat(logic.getScore(), is(5));
    }

    @Test
    public void test_9_00_() throws Exception {
        // Проверяем, что на 9 часов находит одинаковые шары
        cells[0][0].bigCellPainting();
        cells[0][1].bigCellPainting();
        cells[0][2].bigCellPainting();
        cells[0][3].bigCellPainting();
        cells[0][4].bigCellPainting();

        cells[0][0].redCell();
        cells[0][1].redCell();
        cells[0][2].redCell();
        cells[0][3].redCell();
        cells[0][4].redCell();

        assertThat(logic._9_00_(0, 4), is(4));
    }

    @Test
    public void test_10_30_() throws Exception {
        // Проверяем, что на 10:30 находит одинаковые шары
        cells[0][0].bigCellPainting();
        cells[1][1].bigCellPainting();
        cells[2][2].bigCellPainting();
        cells[3][3].bigCellPainting();
        cells[4][4].bigCellPainting();

        cells[0][0].redCell();
        cells[1][1].redCell();
        cells[2][2].redCell();
        cells[3][3].redCell();
        cells[4][4].redCell();

        assertThat(logic._10_30_(4, 4), is(4));
    }

    @Test
    public void test_12_00_() throws Exception {
        // Проверяем, что на 12 часов находит одинаковые шары
        cells[0][0].bigCellPainting();
        cells[1][0].bigCellPainting();
        cells[2][0].bigCellPainting();
        cells[3][0].bigCellPainting();
        cells[4][0].bigCellPainting();

        cells[0][0].redCell();
        cells[1][0].redCell();
        cells[2][0].redCell();
        cells[3][0].redCell();
        cells[4][0].redCell();

        assertThat(logic._12_00_(4, 0), is(4));
    }

    @Test
    public void test_13_30_() throws Exception {
        // Проверяем, что на 13:30 находит одинаковые шары
        cells[0][8].bigCellPainting();
        cells[1][7].bigCellPainting();
        cells[2][6].bigCellPainting();
        cells[3][5].bigCellPainting();
        cells[4][4].bigCellPainting();

        cells[0][8].redCell();
        cells[1][7].redCell();
        cells[2][6].redCell();
        cells[3][5].redCell();
        cells[4][4].redCell();

        assertThat(logic._13_30_(4, 4), is(4));
    }

    @Test
    public void test_15_00_() throws Exception {
        // Проверяем, что на 15 часов находит одинаковые шары
        cells[0][0].bigCellPainting();
        cells[0][1].bigCellPainting();
        cells[0][2].bigCellPainting();
        cells[0][3].bigCellPainting();
        cells[0][4].bigCellPainting();

        cells[0][0].redCell();
        cells[0][1].redCell();
        cells[0][2].redCell();
        cells[0][3].redCell();
        cells[0][4].redCell();

        assertThat(logic._15_00_(0, 0), is(4));
    }

    @Test
    public void test_16_30_() throws Exception {
        // Проверяем, что на 16:30 находит одинаковые шары
        cells[0][0].bigCellPainting();
        cells[1][1].bigCellPainting();
        cells[2][2].bigCellPainting();
        cells[3][3].bigCellPainting();
        cells[4][4].bigCellPainting();

        cells[0][0].redCell();
        cells[1][1].redCell();
        cells[2][2].redCell();
        cells[3][3].redCell();
        cells[4][4].redCell();

        assertThat(logic._16_30_(0, 0), is(4));
    }

    @Test
    public void test_18_00_() throws Exception {
        // Проверяем, что на 18 часов находит одинаковые шары
        cells[0][0].bigCellPainting();
        cells[1][0].bigCellPainting();
        cells[2][0].bigCellPainting();
        cells[3][0].bigCellPainting();
        cells[4][0].bigCellPainting();

        cells[0][0].redCell();
        cells[1][0].redCell();
        cells[2][0].redCell();
        cells[3][0].redCell();
        cells[4][0].redCell();

        assertThat(logic._18_00_(0, 0), is(4));
    }

    @Test
    public void test_19_30_() throws Exception {
        // Проверяем, что на 19:30 находит одинаковые шары
        cells[0][8].bigCellPainting();
        cells[1][7].bigCellPainting();
        cells[2][6].bigCellPainting();
        cells[3][5].bigCellPainting();
        cells[4][4].bigCellPainting();

        cells[0][8].redCell();
        cells[1][7].redCell();
        cells[2][6].redCell();
        cells[3][5].redCell();
        cells[4][4].redCell();

        assertThat(logic._19_30_(0, 8), is(4));
    }
}