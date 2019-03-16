/*
 * org.fsola
 *
 * File Name: MaxCombinationsOfCoinsThatFromN.java
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
 * Given an infinite supply of ‘m’ coin denominations S[m] = {S1, S2... Sm},
 * calculate all the different combinations which can be used to get change
 * for some quantity ‘N’
 * <p/>
 * Used Resources: README file resources section line 50.
 */
public class MaxCombinationsOfCoinsThatFromN {

    //
    // N = 4
    // Coins = {1, 2, 3}
    //
    //                                      [4]
    //                -1 /                -2 |          -3 \
    //                 [3]                  [2]           [1]
    //     -1 /      -2 |  -3 \         -1 / -2 \       -1 |
    //      [2]        [1]    [0]         [1]   [0]        [0]
    //   -1 / -2 \   -1 |               -1 |
    //    [1]   [0]    [0]                [0]
    //  -1 |
    //    [0]
    //
    // Count = 7 repeated combinations
    // 1: {1, 1, 1, 1}
    // 2: {2, 1, 1}
    // 3: {1, 2, 1}
    // 4: {3, 1}
    // 5: {1, 1, 2}
    // 6: {2, 2}
    // 7: {1, 3}
    //
    // Non repeated combinations count 4
    // 1: {1, 1, 1, 1}
    // 2: {2, 1, 1}
    // 3: {3, 1}
    // 4: {2, 2}
    //

    public static int count(int[] coins, int N) {
        int[] C = new int[N + 1];

        for (int i = 0; i < C.length; ++i) {
            C[i] = 0;
        }

        for (int CountK = 1; CountK < C.length; ++CountK) {
            for (int i = 0; i < coins.length; ++i) {
                if (0 <= CountK - coins[i]) {
                    C[CountK] = Math.max(C[CountK - coins[i]] + 1, C[CountK]);
                }
            }
        }

        // Debug
        System.out.print("C:");
        for (int i = 0; i < C.length; ++i) {
            System.out.print(" " + C[i]);
        }
        System.out.println();

        return C[N];
    }
}
