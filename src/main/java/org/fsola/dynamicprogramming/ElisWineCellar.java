/*
 * org.fsola
 *
 * File Name: ElisWineCellar.java
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

/**
 * Eli has inherited from her grandmother (Nora) winery. There are N <= 200 wine bottles
 * in a row. For simplicity them numbered from 0 to N-1 inclusive. Their starting prices
 * are non-negative integers that are given in the array P []. The price of the i-th
 * bottle is given in P[i]. The more mature bottles, the more expensive they become. If
 * k is bottle aged X years, its price is X * P[k].
 * In his will Elly's grandmother asked her granddaughter every year to sell one of them,
 * or choose the leftmost or rightmost left. What is the maximum amount of money that Eli
 * can win if the bottles sold in the best order for it? We believe that the bottles are
 * aged for one year, when he was first sold by them. For example, if we have 4 bottles of
 * prices {P[0] = 1, P[1] = 4, P[2] = 2, P[3] = 3}, the optimal solution would be to sell
 * the bottles in a row {0, 3, 2, 1} for profit 1 * 1 + 2 * 3 + 3 * 2 + 4 * 4 = 29.
 * <p/>
 * Used Resources: README file resources section line 51.
 */
public class ElisWineCellar {

    public static int solve1impl(int[] P, int year, int left, int right) {
        if (right < left) {
            return 0;
        }

        int l = solve1impl(P, year + 1, left + 1, right) + year * P[left];
        int r = solve1impl(P, year + 1, left, right - 1) + year * P[right];

        return Math.max(l, r);
    }

    public static int solve1(int[] P) {
        return solve1impl(P, 1, 0, P.length - 1);
    }

    public static int solve2impl(int[] P, int[][][] D, int year, int left, int right) {
        if (right < left) {
            return 0;
        }

        if (D[year][left][right] != -1) {
            return D[year][left][right];
        }

        int l = solve2impl(P, D, year + 1, left + 1, right) + year * P[left];
        int r = solve2impl(P, D, year + 1, left, right - 1) + year * P[right];

        D[year][left][right] = Math.max(l, r);

        return D[year][left][right];
    }

    public static int solve2(int[] P) {
        int years = P.length + 1;
        int bottles = P.length;
        int[][][] D = new int[years][bottles][bottles];

        // D is used to store already calculated results
        // P.length + 1 => Number of years

        for (int i = 0; i < years; ++i) {
            for (int j = 0; j < bottles; ++j) {
                for (int k = 0; k < bottles; ++k) {
                    D[i][j][k] = -1; // mark as not calculated yet
                }
            }
        }

        return solve2impl(P, D, 1, 0, P.length - 1);
    }

    public static int solve3impl(int[] P, int[][][] D, boolean[][][] S, int year, int left, int right) {
        if (right < left) {
            return 0;
        }

        if (D[year][left][right] != -1) {
            return D[year][left][right];
        }

        int l = solve3impl(P, D, S, year + 1, left + 1, right) + year * P[left];
        int r = solve3impl(P, D, S, year + 1, left, right - 1) + year * P[right];

        D[year][left][right] = Math.max(l, r);
        if (l < r) {
            S[year][left][right] = true;
        }

        return D[year][left][right];
    }

    private static void print(boolean[][][] S) {
        int left = 0;
        int right = S.length - 2;

        for (int year = 1; year < S.length; ++year) {
            if (S[year][left][right]) {
                right--;
                System.out.println("right");
            } else {
                left++;
                System.out.println("left");
            }
        }
    }

    public static int solve3(int[] P) {
        int years = P.length + 1;
        int bottles = P.length;
        int[][][] D = new int[years][bottles][bottles];
        boolean[][][] S = new boolean[years][bottles][bottles];

        // D is used to store already calculated results
        // P.length + 1 => Number of years

        for (int i = 0; i < years; ++i) {
            for (int j = 0; j < bottles; ++j) {
                for (int k = 0; k < bottles; ++k) {
                    D[i][j][k] = -1; // mark as not calculated yet
                    S[i][j][k] = false;
                }
            }
        }

        int r = solve3impl(P, D, S, 1, 0, P.length - 1);
        print(S);

        return r;
    }
}
