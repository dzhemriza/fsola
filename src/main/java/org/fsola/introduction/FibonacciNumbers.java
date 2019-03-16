/*
 * org.fsola
 *
 * File Name: FibonacciNumbers.java
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
 * Used Resources: README file resources section line 1, line 46
 */
public class FibonacciNumbers {

    /**
     * Calculates Fibonacci numbers using recursion.
     *
     * @param n
     * @return
     */
    public static int fibonacciRec(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        return fibonacciRec(n - 1) + fibonacciRec(n - 2);
    }

    /**
     * Iterative approach for finding Fibonacci numbers.
     *
     * @param n
     * @return
     */
    public static int fibonacciIter(int n) {
        int fn = 1;
        int fn1 = 0;
        int fn2;

        for (int i = n; i > 0; --i) {
            fn2 = fn1;
            fn1 = fn;
            fn = fn1 + fn2;
        }

        return fn1;
    }

    /**
     * Iterative approach for finding Fibonacci numbers.
     *
     * @param n
     * @return
     */
    public static int fibonacciIter2(int n) {
        int f1 = 0;
        int f2 = 1;

        for (int i = n; i > 0; --i) {
            f2 = f1 + f2;
            f1 = f2 - f1;
        }

        return f1;
    }

    /**
     * Iterative approach for finding Fibonacci numbers using array.
     *
     * @param n
     * @return
     */
    public static int fibonacciArray(int n) {
        int N = 2;

        if (2 <= n) {
            N = n;
        }

        int[] fib = new int[N + 1];

        fib[0] = 0;
        fib[1] = 1;

        for (int i = 2; i <= n; ++i) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        return fib[n];
    }

    /**
     * Calculates Fibonacci numbers using matrix.
     *
     * @param n
     * @return
     */
    public static int fibonacciMatrix(int n) {
        int[][] m = new int[][]{
                {1, 1},
                {1, 0}
        };

        m = SimpleAritmetics.matrixPower(m, n - 1);

        return m[0][0];
    }
}
