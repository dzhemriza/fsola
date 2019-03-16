/*
 * org.fsola
 *
 * File Name: NumeralSystem.java
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
public class NumeralSystem {

    private final static double EPS = 0.0001;

    /**
     * Converts given number n to character representation.
     *
     * @param n
     * @return
     */
    private static char getChar(int n) {
        return (char) ((n < 10) ? (n + '0') : (n + 'A' - 10));
    }

    /**
     * Converts given character to number 0..9 and A..Z
     *
     * @param c
     * @return
     */
    private static int getValue(char c) {
        return ((c >= '0') && (c <= '9')) ? (c - '0') : (c - 'A' + 10);
    }

    /**
     * Converts number from base 10 to custom base.
     *
     * @param n
     * @param base
     * @return
     */
    public static String convert(int n, int base) {
        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            sb.append(getChar(n % base));
            n /= base;
        }

        return sb.reverse().toString();
    }

    /**
     * Converts 0.X number from base 10 to custom base.
     *
     * @param n
     * @param base
     * @param precision
     * @return
     */
    public static String convertLessThan1(double n, int base, int precision) {
        StringBuilder sb = new StringBuilder();

        for (; precision > 0; --precision) {

            // Do we already have 0 with EPS precision
            if (Math.abs(n) < EPS) {
                break;
            }

            n *= base;
            sb.append(getChar((int) Math.floor(n)));

            n -= Math.floor(n);
        }

        return sb.toString();
    }

    /**
     * Converts a real number from base 10 to custom base.
     *
     * @param n
     * @param base
     * @param precision
     * @return
     */
    public static String convertReal(double n, int base, int precision) {
        StringBuilder sb = new StringBuilder();

        // Test for negative number
        if (n < 0) {
            sb.append("-");
        }

        CustomMath.IntFracResult intFracResult = CustomMath.modf(n);

        String integer = convert((int) intFracResult.getInteger(), base);

        sb.append(integer);
        sb.append(".");

        String fraction =
                convertLessThan1(intFracResult.getFraction(), base, precision);

        sb.append(fraction);

        return sb.toString();
    }

    /**
     * Calculates given number represented as {@link String} on a given base to
     * {@code int}.<p/>For generating such number please refer to {@link
     * #convert(int, int)} method.
     *
     * @param numb
     * @param base
     * @return
     */
    public static int calculate(String numb, int base) {
        int result = 0;

        for (int i = 0; i < numb.length(); ++i) {
            result = result * base + getValue(numb.charAt(i));
        }

        return result;
    }

    /**
     * Calculates given fraction represented as {@link String} on a given base
     * to {@link double}.</p>For generating such fractions please refer to
     * {@link #convertLessThan1(double, int, int)}.
     *
     * @param numb
     * @param base
     * @return
     */
    public static double calculateLessThan1(String numb, int base) {
        double result = 0.0;

        for (int i = numb.length() - 1; i >= 0; --i) {
            result = (result + getValue(numb.charAt(i))) / base;
        }

        return result;
    }

    /**
     * Calculates given real number represented as {@link String} on a given
     * base to {@link double}.</p>For generating such fractions please refer to
     * {@link #convertReal(double, int, int)}.
     *
     * @param numb
     * @param base
     * @return
     */
    public static double calculateReal(String numb, int base) {
        int minus = 1;

        if (numb.charAt(0) == '-') {
            minus = -1;
        }

        if (!numb.contains(".")) {
            return calculate(numb, base);
        }

        String[] integerFraction = numb.split("\\.");

        double result = calculate(integerFraction[0], base);
        result += calculateLessThan1(integerFraction[1], base);

        return result * minus;
    }
}
