package com.scientific.calc.Models;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransferNumber {
    private Date date;
    private BigInteger number;

    public TransferNumber() {
        this.date = new Date();
        number = new BigInteger("0");
    }

    public TransferNumber(String number) {
        this.date = new Date();
        this.number = new BigInteger(number);
    }

    public String getDate() {
        return new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss").format(date);
    }

    public String getBinary() {
        return number.toString(2);
    }

    public String getOctal() {
        return number.toString(8);
    }

    public String getDecimal() {
        return number.toString(10);
    }

    public String getHexadecimal() {
        return number.toString(16);
    }

    public static void printAllFormats(BigInteger number) {
        System.out.println("Binary: " + number.toString(2));
        System.out.println("Octal: " + number.toString(8));
        System.out.println("Decimal: " + number.toString(10));
        System.out.println("Hex: " + number.toString(16));
    }
}
