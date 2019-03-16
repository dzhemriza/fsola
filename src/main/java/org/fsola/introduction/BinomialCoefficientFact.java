/*
 * org.fsola
 *
 * File Name: BinomialCoefficientFact.java
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
 * Used Resources: README file resources section line 1
 */
public class BinomialCoefficientFact {

    private static ArrayList<Integer> primes = new ArrayList<>();
    private static ArrayList<Integer> counts = new ArrayList<>();

    /**
     * Modifies {@link #primes} and {@link #counts} arrays.
     *
     * @param x
     * @param how
     */
    private static void modify(int x, int how) {
        assert (counts.size() == primes.size());

        for (int i = 0; i < primes.size(); ++i) {
            if (x == primes.get(i)) {
                int count = counts.get(i);
                counts.set(i, count + how);
                return;
            }
        }

        counts.add(how);
        primes.add(x);
    }

    private static void solve(int start, int end, int inc) {
        for (int i = start; i <= end; ++i) {
            int mul = i;
            int prime = 2;

            while (mul != 1) {
                int how = 0;

                for (how = 0; (mul % prime) == 0; ++how, mul /= prime)
                    ;

                if (how > 0) {
                    modify(prime, inc * how);
                }

                prime++;
            }
        }
    }

    /**
     * Calculates Binomial Coefficient
     *
     * @return
     */
    private static int calc() {
        int result = 1;

        assert (counts.size() == primes.size());

        for (int i = 0; i < primes.size(); ++i) {
            for (int j = 0; j < counts.get(i); ++j) {
                result *= primes.get(i);
            }
        }

        return result;
    }

    /**
     * Calculates Binomial Coefficient using reducing factorization.
     *
     * @param n
     * @param k
     * @return
     */
    public static int binomialCoefficient(int n, int k) {
        primes = new ArrayList<>();
        counts = new ArrayList<>();

        if (n - k < k) {
            k = n - k;
        }

        solve(n - k + 1, n, 1);
        solve(1, k, -1);
        return calc();
    }
}
