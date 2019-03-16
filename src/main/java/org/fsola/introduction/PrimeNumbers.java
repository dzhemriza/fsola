/*
 * org.fsola
 *
 * File Name: PrimeNumbers.java
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

/**
 * Used Resources: README file resources section line 1, line 5
 */
public class PrimeNumbers {

    /**
     * Check is given number n is prime.
     *
     * @param n
     * @return
     */
    public static boolean isPrimeSimple(int n) {

        if (n == 2) {
            return true;
        }

        int m = (int) Math.sqrt(n);
        for (int i = 2; i <= m; ++i) {
            if ((n % i) == 0) {
                return false;
            }
        }

        return true;
    }

    public static int[] PRIMES_PRECOMPUTED =
            new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47,
                    53, 59, 61, 67, 71, 73, 79, 83, 89, 97};

    /**
     * Check is given number n is prime.</p> To check is a given number n is
     * prime we need to check that is not divided to any other prime number in
     * interval [2, sqrt(n)].
     *
     * @param n
     * @return
     */
    public static boolean isPrimeDiv(int n) {

        for (int i = 0; (i < PRIMES_PRECOMPUTED.length) && (
                PRIMES_PRECOMPUTED[i] * PRIMES_PRECOMPUTED[i] <= n);
             ++i) {
            if ((n % PRIMES_PRECOMPUTED[i]) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Finds all prime numbers in interval [2, n] using Eratosthenes algorithm.
     *
     * @param n
     * @return
     */
    public static List<Integer> eratosthenes(int n) {
        // Bit vector
        boolean[] isPrime = new boolean[n + 1];

        for (int i = 0; i < isPrime.length; ++i) {
            isPrime[i] = true;
        }

        isPrime[0] = false;

        for (int i = 2; i <= n; ++i) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; ++i) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        return primes;
    }

    /**
     * Check is given number n is prime.</p> To check is a given number n is
     * prime we need to check that is not divided to any other prime number in
     * interval [2, sqrt(n)].
     *
     * @param primes
     * @param n
     * @return
     */
    public static boolean isPrimeDiv(ArrayList<Integer> primes, int n) {

        for (int i = 0;
             (i < primes.size()) && (primes.get(i) * primes.get(i) <= n);
             ++i) {
            if ((n % primes.get(i)) == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Finds all prime numbers in interval [2, n] using pre-calculated prime
     * numbers.
     *
     * @param n
     * @return
     */
    public static List<Integer> findPrimes(int n) {
        ArrayList<Integer> primes = new ArrayList<>();

        for (int i = 2; i <= n; ++i) {
            if (isPrimeDiv(primes, i)) {
                primes.add(i);
            }
        }

        return primes;
    }

    /**
     * Check if given number N is prime or not. Without using {@link
     * Math#sqrt(double)} method.
     *
     * @param n
     * @return
     */
    public static boolean isPrime2(int n) {
        if (n == 2 || n == 3) {
            return true;
        }

        if (n == 1 || (n % 2) == 0) {
            return false;
        }

        //i * i <= n; -> avoid sqrt(n)
        for (int i = 3; i * i <= n; i += 2) {
            if ((n % i) == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Algorithm for finding a primer number at least as large as n.
     *
     * @param n
     * @return
     */
    public static int nextPrime(int n) {
        if (n <= 0) { // less than zero
            n = 3;
        }

        if ((n % 2) == 0) {
            n++;
        }

        while (!isPrime2(n)) {
            n += 2;
        }

        return n;
    }
}
