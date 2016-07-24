package ru.minesweeper;

import org.junit.BeforeClass;
import org.junit.Test;

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
        standardLogic.easy();
        assertThat(standardLogic.sizeField().length, is(9));
    }

    @Test
    public void testMedium() throws Exception {
        standardLogic.medium();
        assertThat(standardLogic.sizeField().length, is(16));
    }

    @Test
    public void testExpert() throws Exception {
        standardLogic.expert();
        assertThat(standardLogic.sizeField().length, is(30));
    }
}