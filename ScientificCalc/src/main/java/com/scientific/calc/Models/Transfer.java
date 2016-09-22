package com.scientific.calc.Models;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transfer {
    private Date date;
    private String binary;
    private long decimal;

    public Transfer(Date date, String binary, long decimal) {
        this.date = date;
        this.binary = binary;
        this.decimal = decimal;
    }

    public Transfer(String binary, long decimal) {
        this.binary = binary;
        this.decimal = decimal;
    }

    public Date getDate() {
        return date;
    }

    public String getBinary() {
        return binary;
    }

    public long getDecimal() {
        return decimal;
    }

    public static long transfer(String binary) {
        int result = 0;

        for (int i = 0; i < binary.length(); i++) {
            result += Math.pow(2, i) * (binary.charAt(i) == '1' ? 1 : 0);
        }
        return result;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "date=" + new SimpleDateFormat("yyyy.MM.dd, HH:mm:ss").format(date) +
                ", binary=" + binary +
                ", decimal=" + decimal +
                '}';
    }
}
