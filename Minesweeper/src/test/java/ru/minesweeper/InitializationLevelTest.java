package ru.minesweeper;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.minesweeper.levels.Easy;
import ru.minesweeper.levels.Expert;
import ru.minesweeper.levels.Medium;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class InitializationLevelTest {

    private static StandardLogic standardLogic;

    @BeforeClass
    public static void setUpClass() throws Exception {
        standardLogic = new StandardLogic();
    }

    @Test
    public void testEasy() throws Exception {
        Easy easy = new Easy();

        standardLogic.easy();

        assertThat(standardLogic.sumRow(), is(easy.sumRow()));
        assertThat(standardLogic.sumColumn(), is(easy.sumColumn()));
        assertThat(standardLogic.sumBombs(), is(easy.sumBombs()));
    }

    @Test
    public void testMedium() throws Exception {
        Medium medium = new Medium();

        standardLogic.medium();

        assertThat(standardLogic.sumRow(), is(medium.sumRow()));
        assertThat(standardLogic.sumColumn(), is(medium.sumColumn()));
        assertThat(standardLogic.sumBombs(), is(medium.sumBombs()));
    }

    @Test
    public void testExpert() throws Exception {
        Expert expert = new Expert();

        standardLogic.expert();

        assertThat(standardLogic.sumRow(), is(expert.sumRow()));
        assertThat(standardLogic.sumColumn(), is(expert.sumColumn()));
        assertThat(standardLogic.sumBombs(), is(expert.sumBombs()));
    }
}