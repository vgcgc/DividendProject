package com.zerobase.dividendproject.model;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Month {
    JAN("Jan", 1),
    FEB("Feb", 2),
    MAR("Mar", 3),
    APR("Apr", 4),
    MAY("May", 5),
    JUN("Jun", 6),
    JUL("Jul", 7),
    AUG("Aug", 8),
    SEP("Sep", 9),
    OCT("Oct", 10),
    NOV("Nov", 11),
    DEC("Dec", 12),
    ERR("Err", -1);

    private final String str;
    private final int num;

    Month(String inStr, int inNum) {
        this.str = inStr;
        this.num = inNum;
    }

    public static int monthStrToInt(String inStr) {
        return Arrays.stream(values())
                .filter(m -> m.str.equals(inStr)).findAny().orElse(ERR).getNum();
    }
}
