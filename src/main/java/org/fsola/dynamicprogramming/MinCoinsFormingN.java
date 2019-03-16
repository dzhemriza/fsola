/*
 * org.fsola
 *
 * File Name: MinCoinsFormingN.java
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

import org.fsola.datastructures.queue.LinkedListQueue;

/**
 * Find minimum number of coins P that form N.
 * <p/>
 * Used Resources: README file resources section line 47.
 */
public class MinCoinsFormingN {

//
//    N = 4
//    Coins = {1, 2, 3}
//
//
//                              [4]
//
//             -1 /            -2 |         -3 \
//
//            [3]                [2]            [1]
//
//     -1 /  -2 |  -3 \      -1 / -2 \        -1 |
//
//       [2]   [1]    [0]      [1]   [0]        [0]
//
//   -1 / -2 \               -1 |
//
//    [1]    [0]               [0]
//
//  -1 |
//
//    [0]
//

    /**
     * Infinity marker
     */
    private static int INF = 10000;

    /**
     * Solve problem using DP approach.
     *
     * @param coins
     * @param N
     * @return
     */
    public static int solveDp(int[] coins, int N) {
        // The problem is solved using bottom-up approach
        int[] t = new int[N + 1];

        for (int i = 0; i < t.length; ++i) {
            t[i] = INF;
        }
        t[0] = 0;

        for (int SumK = 1; SumK <= N; ++SumK) {
            for (int i = 0; i < coins.length; ++i) {
                int CoinK = coins[i];
                if (CoinK <= SumK) {
                    t[SumK] = Math.min(t[SumK], t[SumK - CoinK] + 1);
                }
            }
        }

        return t[N];
    }

    /**
     * Solve problem using BFS algorithm.
     *
     * @param coins
     * @param N
     * @return
     */
    public static int solveBfs(int[] coins, int N) {
        // BFS is used to find shortest path between vertices in
        // unweighted graph
        int[] p = new int[N + 1];
        int[] path = new int[N + 1];

        for (int i = 0; i < p.length; ++i) {
            p[i] = 0;
        }

        LinkedListQueue<Integer> q = new LinkedListQueue<>();
        q.put(N);
        path[N] = -1;

        while (!q.isEmpty()) {
            int u = q.get();

            if (u == 0) {
                // we reach 0 so terminate BFS
                break;
            }

            for (int i = 0; i < coins.length; ++i) {
                int v = u - coins[i];

                if (v >= 0 && p[v] == 0) {
                    p[v] = p[u] + 1;
                    path[v] = u;
                    q.put(v);
                }
            }
        }

        System.out.print("Path: 0");
        for (int x = path[0]; x != -1; x = path[x]) {
            System.out.print(" " + x);
        }
        System.out.println();
        return p[0];
    }
}
