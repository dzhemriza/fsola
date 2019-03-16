/*
 * org.fsola
 *
 * File Name: RodCutting.java
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
package org.fsola.dynamicprogramming;

import java.util.ArrayList;

/**
 * Given a rod of length n inches and an array of prices that contains
 * prices of all pieces of size smaller than n. Determine the maximum
 * value obtainable by cutting up the rod and selling the pieces. For
 * example, if length of the rod is 8 and the values of different pieces
 * are given as following, then the maximum obtainable value is
 * 22 (by cutting in two pieces of lengths 2 and 6).
 * <p/>
 * length   | 1   2   3   4   5   6   7   8
 * --------------------------------------------
 * price    | 1   5   8   9  10  17  17  20
 * <p/>
 * Used Resources: README file resources section line 39, line 53.
 */
public class RodCutting {

    private static int exhaustiveSearchImpl(int N, int[] length, int[] price) {
        if (N == 0) {
            return 0;
        }

        int max = 0;
        for (int i = 0; i < length.length; ++i) {
            if (N - length[i] >= 0) {
                int current = exhaustiveSearchImpl(N - length[i], length, price) + price[i];
                if (current > max) {
                    max = current;
                }
            }
        }

        return max;
    }

    public static int exhaustiveSearch(int N, int[] length, int[] price) {
        return exhaustiveSearchImpl(N, length, price);
    }

    public static int memoizationImpl(int N, int[] length, int[] price, int[] cache) {
        if (N == 0) {
            return 0;
        }

        if (cache[N] != 0) {
            return cache[N];
        }

        int max = 0;
        for (int i = 0; i < length.length; ++i) {
            if (N - length[i] >= 0) {
                int current = exhaustiveSearchImpl(N - length[i], length, price) + price[i];
                if (current > max) {
                    max = current;
                }
            }
        }

        cache[N] = max;

        return max;
    }

    public static int memoization(int N, int[] length, int[] price) {
        // Build and initialize cache with 0
        int[] cache = new int[N + 1];
        for (int i = 0; i < cache.length; ++i) {
            cache[i] = 0;
        }

        return memoizationImpl(N, length, price, cache);
    }

    public static int bottomUp(int N, int[] length, int[] price) {
        // Build and initialize cache with 0
        int[] cache = new int[N + 1];
        for (int i = 0; i < cache.length; ++i) {
            cache[i] = 0;
        }

        for (int k = 1; k <= N; ++k) {
            for (int i = 0; i < length.length; ++i) {
                if (k - length[i] >= 0) {
                    cache[k] = Math.max(cache[k], cache[k - length[i]] + price[i]);
                }
            }
        }

        return cache[N];
    }

    public static class Result {
        public int max = 0;
        public ArrayList<Integer> sol = new ArrayList<>();
    }

    public static Result bottomUpWithSolution(int N, int[] length, int[] price) {
        // Build and initialize cache with 0
        int[] cache = new int[N + 1];
        for (int i = 0; i < cache.length; ++i) {
            cache[i] = 0;
        }
        int[] sol = new int[N + 1];
        for (int i = 0; i < sol.length; ++i) {
            sol[i] = -1;
        }

        for (int k = 1; k <= N; ++k) {
            for (int i = 0; i < length.length; ++i) {
                if (k - length[i] >= 0) {
                    if (cache[k] < cache[k - length[i]] + price[i]) {
                        cache[k] = cache[k - length[i]] + price[i];
                        sol[k] = i;
                    }
                }
            }
        }

        Result r = new Result();
        r.max = cache[N];

        // Build solution
        for (int s = N; s > 0; s -= length[sol[s]]) {
            r.sol.add(sol[s]);
        }

        return r;
    }
}
