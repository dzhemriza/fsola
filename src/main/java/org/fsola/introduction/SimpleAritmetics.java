/*
 * org.fsola
 *
 * File Name: SimpleAritmetics.java
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
import java.util.List;
import java.util.Random;

/**
 * Used Resources: README file resources section line 1, line 45
 */
public class SimpleAritmetics {

    /**
     * Calculates the count of digits in one integer number.
     *
     * @param num
     * @return
     */
    public static int countOfDigitsOfNumber(int num) {
        int digits;

        for (digits = 0; num > 0; digits++, num /= 10)
            ;

        return digits;
    }

    /**
     * Calculates sum of 1 + 2 + .. + n recursivly
     *
     * @param n
     * @return
     */
    public static int sumRecursive(int n) {
        if (n == 0) {
            return 0;
        } else {
            return n + sumRecursive(n - 1);
        }
    }

    /**
     * Calculates sum of 1 + 2 + .. + n
     *
     * @param n
     * @return
     */
    public static int sum(int n) {
        int sum = 0;

        for (int i = 1; i <= n; ++i) {
            sum += i;
        }

        return sum;
    }

    /**
     * Calculates sum of 1 + 2 + .. + n using S = n(n + 1)/2.
     *
     * @param n
     * @return
     */
    public static int sumOptimized(int n) {
        return (n * (n + 1)) / 2;
    }

    /**
     * Calculates the sum of all elements in arr.
     *
     * @param arr
     * @return
     */
    public static int sum(int[] arr) {
        int sum = 0;

        for (int i = 0; i < arr.length; ++i) {
            sum += arr[i];
        }
        return sum;
    }

    /**
     * Multiplies all elements in arr.
     *
     * @param arr
     * @return
     */
    public static int mult(int[] arr) {
        int mult = 1;

        for (int i = 0; i < arr.length; ++i) {
            mult *= arr[i];
        }

        return mult;
    }

    /**
     * Calculates X ^ N.
     *
     * @param x
     * @param n
     * @return
     */
    public static int power(int x, int n) {
        int pow = x;

        for (int i = 1; i < n; ++i) {
            pow *= x;
        }

        return pow;
    }

    /**
     * Calculates X ^ N.
     *
     * @param x
     * @param n
     * @return
     */
    public static int fastPower(int x, int n) {
        if (n == 0) {
            return 1;
        }

        if (n == 1) {
            return x;
        }

        if ((n % 2) == 0) {
            return fastPower(x * x, n / 2);
        } else {
            return x * fastPower(x * x, n / 2);
        }
    }

    /**
     * Calculates factoriel recursivley.
     *
     * @param n
     * @return
     */
    public static int factorielRecursive(int n) {
        if (n == 1)
            return 1;
        return factorielRecursive(n - 1) * n;
    }

    /**
     * Calculates factoriel non recursivley.
     *
     * @param n
     * @return
     */
    public static int factorielNonRecursive(int n) {
        int fac = 1;

        for (int f = 1; f <= n; ++f) {
            fac *= f;
        }

        return fac;
    }

    /**
     * Calculates the sum of 2 matrices.
     *
     * @param a
     * @param b
     * @return
     */
    public static int[][] matrixSum(int[][] a, int[][] b) {
        if ((a.length != b.length) || (a[0].length != b[0].length)) {
            throw new IllegalArgumentException("A length != B length");
        }

        int[][] c = new int[a.length][a[0].length];

        for (int i = 0; i < a.length; ++i) {
            for (int j = 0; j < a[i].length; ++j) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }

        return c;
    }

    /**
     * Multiply 2 matrices.
     *
     * @param a
     * @param b
     * @return
     */
    public static int[][] matrixMult(int[][] a, int[][] b) {
        int m = a.length;
        int n = a[0].length;
        int o = b.length;
        int p = b[0].length;

        // A(MxN) * B(NxP) = C(MxP)
        // Cij = Sum[k=1..n](Aik*Bkj)

        if (n != o) {
            throw new IllegalArgumentException("A length != B length");
        }

        int[][] c = new int[m][p];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < p; ++j) {
                c[i][j] = 0;
                for (int k = 0; k < n; ++k) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return c;
    }

    /**
     * Clone matrix.
     *
     * @param m
     * @return
     */
    public static int[][] matrixClone(int[][] m) {
        int M = m.length;
        int N = m[0].length;

        int[][] r = new int[M][N];

        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                r[i][j] = m[i][j];
            }
        }

        return r;
    }

    /**
     * matrix ^ N
     *
     * @param matrix
     * @param n
     * @return
     */
    public static int[][] matrixPower(int[][] matrix, int n) {
        int[][] r = matrixClone(matrix);

        for (int i = 1; i < n; ++i) {
            r = matrixMult(r, matrix);
        }

        return r;
    }

    /**
     * Find digits count of n!.
     *
     * @param n
     * @return
     */
    public static int digitsNFac(int n) {
        double digit = 0.0;

        for (int i = 1; i <= n; ++i) {
            digit += Math.log10(i);
        }

        return (int) (digit + 1);
    }

    /**
     * Method reverse given array.
     *
     * @param a
     * @param <T>
     */
    public static <T> void reverseArray(T[] a) {
        for (int i = 0, j = a.length - 1; i < j; ++i, --j) {
            T tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }

    /**
     * Method reverse given {@link ArrayList}.
     *
     * @param a
     * @param <T>
     */
    public static <T> void reverseArrayList(ArrayList<T> a) {
        for (int i = 0, j = a.size() - 1; i < j; ++i, --j) {
            T tmp = a.get(i);
            a.set(i, a.get(j));
            a.set(j, tmp);
        }
    }

    /**
     * Method decomposes number to given digits.
     *
     * @param n
     * @return
     */
    public static List<Integer> decomposeNumberToDigits(int n) {
        ArrayList<Integer> digits = new ArrayList<>();

        while (n > 0) {
            int digit = n % 10;
            n /= 10;

            digits.add(digit);
        }

        reverseArrayList(digits);

        return digits;
    }

    /**
     * Method decomposes number to given digits using recursion.
     *
     * @param n
     * @return
     */
    public static List<Integer> decomposeNumberToDigitsRec(int n) {
        ArrayList<Integer> digits = new ArrayList<>();

        if (n < 10) {
            digits.add(n);
        } else {
            List<Integer> digitsTmp = decomposeNumberToDigitsRec(n / 10);
            digits.addAll(digitsTmp);
            digits.add(n % 10);
        }

        return digits;
    }

    /**
     * Algorithm prints a sequence of numbers which are increasing and
     * decreasing using the formula 10^k (1 <= k <= n). Example for n = 5: 10,
     * 100, 1000, 10000, 100000, 100000, 10000, 1000, 100, 10. Algorithm is
     * using recursion.
     *
     * @param k
     * @param pos
     * @param n
     * @return
     */
    public static List<Integer> printIncreasingAndDecreasingSequenceRec(int k,
                                                                        int pos,
                                                                        int n) {
        ArrayList<Integer> r = new ArrayList<>();

        r.add(pos);
        if (k < n) {
            List<Integer> rtmp =
                    printIncreasingAndDecreasingSequenceRec(k + 1, pos * 10, n);

            r.addAll(rtmp);
        }
        r.add(pos);

        return r;
    }

    /**
     * Algorithm prints a sequence of numbers which are increasing and
     * decreasing using the formula 10^k (1 <= k <= n). Example for n = 5: 10,
     * 100, 1000, 10000, 100000, 100000, 10000, 1000, 100, 10. Algorithm is
     * using iteration.
     *
     * @param n
     * @return
     */
    public static List<Integer> printIncreasingAndDecreasingSequenceIter(
            int n) {
        ArrayList<Integer> r = new ArrayList<>();

        for (int i = 1, result = 10; i <= n; ++i, result *= 10) {
            r.add(result);
        }

        // In reverse order add inverted mirror of current result
        int size = r.size();
        for (int i = size - 1; i >= 0; --i) {
            r.add(r.get(i));
        }

        return r;
    }

    public static int[] shuffleArray(int[] arr) {
        int[] newArr = new int[arr.length];

        for (int i = 0; i < arr.length; ++i) {
            newArr[i] = arr[i];
        }

        Random rand = new Random();

        for (int i = 0; i < 1000; ++i) {
            int j = rand.nextInt(arr.length);
            int k = rand.nextInt(arr.length);

            int t = newArr[j];
            newArr[j] = newArr[k];
            newArr[k] = t;
        }

        return newArr;
    }
}
