/*
 * org.fsola
 *
 * File Name: RomanNumbers.java
 *
 * Copyright 2014 Dzhem Riza
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fsola.introduction;

/**
 * Used Resources: README file resources section line 1
 */
public class RomanNumbers {

    private static String[] ROMAN_1_9 =
            {"", "A", "AA", "AAA", "AB", "B", "BA", "BAA", "BAAA", "AC"};
    private static String[] ROMAN_DIGITS = {"IVX", "XLC", "CDM", "M"};

    /**
     * Translates given number x of specific power to roman number.
     *
     * @param x
     * @param power
     * @return
     */
    private static String getRomanDigit(int x, int power) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < ROMAN_1_9[x].length(); ++i) {
            int charPos = (int) (ROMAN_1_9[x].charAt(i) - 'A');

            sb.append(ROMAN_DIGITS[power].charAt(charPos));
        }

        return sb.toString();
    }

    /**
     * Converts given number X to roman number represented as {@link String}.
     *
     * @param x
     * @return
     */
    public static String decimalToRoman(int x) {
        StringBuilder sb = new StringBuilder();

        for (int power = 0; x > 0; x /= 10, power++) {
            String romanDigit = getRomanDigit(x % 10, power);
            sb.insert(0, romanDigit);
        }

        return sb.toString();
    }

    /**
     * Translates given roman number represented as single char to decimal
     * representation.
     *
     * @param x
     * @return
     */
    private static int getNumberFromRomanDigit(char x) {
        switch (x) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                throw new IllegalArgumentException(
                        "Unexpected character: " + x);
        }
    }

    /**
     * Converts given roman number to decimal.
     *
     * @param roman
     * @return
     */
    public static int romanToDecimal(String roman) {
        int old = 1000;
        int result = 0;

        for (int i = 0; i < roman.length(); ++i) {
            int value = getNumberFromRomanDigit(roman.charAt(i));
            result += value;

            if (value > old) {
                result -= old * 2;
            }

            old = value;
        }

        if (!roman.equals(decimalToRoman(result))) {
            throw new IllegalArgumentException(
                    "Malformed roman number: " + roman);
        }

        return result;
    }
}
