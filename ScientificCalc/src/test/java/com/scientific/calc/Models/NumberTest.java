package com.scientific.calc.Models;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class NumberTest {

    private TransferNumber number;

    @Before
    public void setUp() throws Exception {
        number = new TransferNumber("77");
    }

    @Test
    public void binToDec() throws Exception {
        TransferNumber.printList(new BigInteger(number.getDecimal()));

        assertThat(number.getBinary(), is("1001101"));
        assertThat(number.getOctal(), is("115"));
        assertThat(number.getDecimal(), is("77"));
        assertThat(number.getHexadecimal(), is("4d"));
    }

}