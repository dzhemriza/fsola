/*
 * org.fsola
 *
 * File Name: CustomMath.java
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

import java.util.ArrayList;

/**
 * Contains methods and classes for math that are not supported by java or some
 * of them are used just for illustration of simple implementation.
 * <p/>
 * Used Resources: README file resources section line 1, line 3
 */
public class CustomMath {

    /**
     * Used eps constant for float operations.
     */
    public static float FLOAT_EPS = 0.00f;

    /**
     * Result of {@link #modf(double)} method.
     */
    public static class IntFracResult {
        private long integer;
        private double fraction;

        public IntFracResult(long integer, double fraction) {
            this.integer = integer;
            this.fraction = fraction;
        }

        public long getInteger() {
            return integer;
        }

        public double getFraction() {
            return fraction;
        }
    }

    /**
     * Obtaining the integer and fractional parts.
     *
     * @param n
     */
    public static IntFracResult modf(double n) {
        long integer = (long) n;
        double fraction = n - integer;

        return new IntFracResult(integer, fraction);
    }

    /**
     * Compares 2 {@code double}'s based on {@code eps}.
     *
     * @param lhs
     * @param rhs
     * @param eps
     * @return
     */
    public static boolean equalsDouble(double lhs, double rhs, double eps) {
        return (Math.abs(rhs - lhs) <= eps);
    }

    /**
     * Compare the given {@code float} and check is it equals to zero based on
     * {@code eps}.
     *
     * @param x
     * @param eps
     * @return
     */
    public static boolean equalZero(float x, float eps) {
        return (x <= eps);
    }

    /**
     * Compare the given {@code float} and check is it equals to zero.
     *
     * @param x
     * @return
     */
    public static boolean equalZero(float x) {
        return equalZero(x, FLOAT_EPS);
    }

    /**
     * Compares 2 {@code float}'s based on {@code eps}.
     *
     * @param lhs
     * @param rhs
     * @param eps
     * @return
     */
    public static boolean equalsFloat(float lhs, float rhs, float eps) {
        return (Math.abs(rhs - lhs) <= eps);
    }

    /**
     * Compares 2 {@code float}'s.
     *
     * @param lhs
     * @param rhs
     * @return
     */
    public static boolean equalsFloat(float lhs, float rhs) {
        return equalsFloat(lhs, rhs, FLOAT_EPS);
    }

    public static boolean isGreaterThan(float lhs, float rhs) {
        return isGreaterThan(lhs, rhs, FLOAT_EPS);
    }

    /**
     * Greater than {@code lhs > rhs}.
     *
     * @param lhs
     * @param rhs
     * @param eps
     * @return
     */
    public static boolean isGreaterThan(float lhs, float rhs, float eps) {
        return lhs - rhs > eps;
    }

    /**
     * Just enough implementation of Big number used for perfect numbers only.
     */
    public static class BigNum {
        private ArrayList<Integer> num = new ArrayList<>();

        public BigNum() {
            // Initialize array of size 1 and first number is 1
            num.add(1);
        }

        /**
         * Doubles the big number.
         */
        public void doubleN() {
            int temp = 0;
            int carry = 0;

            for (int i = 0; i < num.size(); ++i) {
                temp = num.get(i) * 2 + carry;
                num.set(i, temp % 10);
                carry = temp / 10;
            }

            if (carry > 0) {
                num.add(carry);
            }
        }

        public void minusLastDigit() {
            num.set(0, num.get(0) - 1);
        }

        /**
         * Prints the content of big number.
         *
         * @return
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            for (int i = num.size() - 1; i >= 0; --i) {
                sb.append(num.get(i));
            }
            return sb.toString();
        }
    }
}
