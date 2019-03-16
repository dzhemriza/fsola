/*
 * org.fsola
 *
 * File Name: Knapsack.java
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
 * Knapsack problem from DP point of view.
 * <p/>
 * Used Resources: README file resources section line 1, line 52.
 */
public class Knapsack {

    public static class Result {
        public int best;
        public int[] items;
    }

    /**
     * Warning: weight[0] and cost[0] are ignored
     *
     * @param capacity
     * @param weight
     * @param cost
     * @return
     */
    public static Result dp1(int capacity, int[] weight, int[] cost) {
        int[] D = new int[capacity + 1]; // store best result
        boolean[][] S = new boolean[capacity + 1][weight.length]; // store used items

        // Initialize tables used to store previous results
        for (int i = 0; i < D.length; ++i) {
            D[i] = 0;
        }
        for (int i = 0; i < capacity + 1; ++i) {
            for (int j = 0; j < weight.length; ++j) {
                S[i][j] = false;
            }
        }

        // Find optimal solution step by step
        for (int capacityK = 1; capacityK <= capacity; ++capacityK) {

            int maxValue = 0;
            int maxIndex = 0;
            for (int itemK = 1; itemK < weight.length; ++itemK) {

                // If this item fits in current capacity and if this item is not used
                if (weight[itemK] <= capacityK && !S[capacityK - weight[itemK]][itemK]) {

                    if (cost[itemK] + D[capacityK - weight[itemK]] > maxValue) {
                        maxValue = cost[itemK] + D[capacityK - weight[itemK]];
                        maxIndex = itemK;
                    }
                }
            }

            if (maxIndex != 0) {
                D[capacityK] = maxValue;
                for (int i = 0; i < weight.length; ++i) {
                    S[capacityK][i] = S[capacityK - weight[maxIndex]][i];
                }
                S[capacityK][maxIndex] = true;
            }

            if (D[capacityK - 1] > D[capacityK]) {
                // Use previous solution as best
                D[capacityK] = D[capacityK - 1];
                for (int i = 0; i < weight.length; ++i) {
                    S[capacityK][i] = S[capacityK - 1][i];
                }
            }
        }

        Result r = new Result();
        r.best = D[capacity]; // best solution

        // calculate the count of items;
        int c = 0;
        for (int i = 1; i < weight.length; ++i) {
            if (S[capacity][i]) {
                c++;
            }
        }

        // populate items itself
        r.items = new int[c];
        c = 0;
        for (int i = 1; i < weight.length; ++i) {
            if (S[capacity][i]) {
                r.items[c] = i;
                c++;
            }
        }

        return r;
    }

    /**
     * Warning: weight[0] and cost[0] are ignored
     *
     * @param capacity
     * @param weight
     * @param cost
     * @return
     */
    public static Result dp2(int capacity, int[] weight, int[] cost) {
        int[][] D = new int[capacity + 1][weight.length]; // store the best result

        for (int i = 0; i < capacity + 1; ++i) {
            D[i][0] = 0; // If we get no item the knapsack will be empty
        }

        for (int itemK = 1; itemK < weight.length; ++itemK) {
            for (int capacityK = 0; capacityK <= capacity; ++capacityK) {
                if (weight[itemK] <= capacityK) {
                    D[capacityK][itemK] = Math.max(D[capacityK][itemK - 1],
                            D[capacityK - weight[itemK]][itemK - 1] + cost[itemK]);
                } else {
                    D[capacityK][itemK] = D[capacityK][itemK - 1];
                }
            }
        }

        // Trace back the solution
        ArrayList<Integer> traceBack = new ArrayList<>();
        for (int capacityK = capacity, itemK = weight.length - 1; itemK > 0; --itemK) {
            if (D[capacityK][itemK] != D[capacityK][itemK - 1]) {
                // This item is used
                traceBack.add(itemK);
                capacityK -= weight[itemK];
            }
        }

        System.out.print("Traceback:");
        for (int i = 0; i < traceBack.size(); ++i) {
            System.out.print(" " + traceBack.get(i));
        }
        System.out.println();

        System.out.println("Matrix:");
        for (int i = 0; i < D.length; ++i) {
            for (int j = 0; j < D[0].length; ++j) {
                System.out.print(" " + D[i][j]);
            }
            System.out.println();
        }

        Result r = new Result();
        r.best = D[capacity][weight.length - 1];
        r.items = new int[traceBack.size()];
        for (int i = 0; i < r.items.length; ++i) {
            r.items[i] = traceBack.get(i);
        }
        return r;
    }
}
