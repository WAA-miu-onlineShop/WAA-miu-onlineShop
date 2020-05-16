package com.miu.waa.groupbravo.onlineshop.utils;

public class NumberToWordsConverter {
    /** The Constant specialNames. */
    private static final String[] specialNames = { "", " Thousand", " Million", " Billion", " Trillion", " Quadrillion",
            " Quintillion" };

    /** The Constant tensNames. */
    private static final String[] tensNames = { "", " Ten", " Twenty", " Thirty", " Forty", " Fifty", " Sixty",
            " Seventy", " Eighty", " Ninety" };

    /** The Constant numNames. */
    private static final String[] numNames = { "", " One", " Two", " Three", " Four", " Five", " Six", " Seven",
            " Eight", " Nine", " Ten", " Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen", " Sixteen",
            " Seventeen", " Eighteen", " Nineteen" };

    /**
     * Convert less than one thousand.
     *
     * @param num
     *            the num
     * @return the string
     */
    private String convertLessThanOneThousand(final int num) {
        String current;
        int number = num;
        if (number % 100 < 20) {
            current = numNames[number % 100];
            number /= 100;
        } else {
            current = numNames[number % 10];
            number /= 10;

            current = tensNames[number % 10] + current;
            number /= 10;
        }
        if (number == 0) {
            return current;
        }
        return numNames[number] + " Hundred" + current;
    }

    /**
     * Convert.
     *
     * @param num
     *            the num
     * @return the string
     */
    public String convert(final Long num) {
        Long number = num;
        if (number == 0) {
            return "zero";
        }

        String prefix = "";

        if (number < 0) {
            number = -number;
            prefix = "Negative";
        }

        String current = "";
        int place = 0;

        do {
            Long n = number % 1000;
            if (n != 0) {
                String s = convertLessThanOneThousand(n.intValue());
                current = s + specialNames[place] + current;
            }
            place++;
            number /= 1000;
        } while (number > 0);

        return (prefix + current).trim();
    }
}
