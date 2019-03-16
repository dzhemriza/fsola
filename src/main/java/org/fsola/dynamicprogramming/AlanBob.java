/*
 * org.fsola
 *
 * File Name: AlanBob.java
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
 * Alan and Bob are brothers they have to dive a N number of presents
 * between each other in such way that Alan and Bob gets equal present.
 * Each present have it's own price that is used to divide into 2 groups.
 * <p/>
 * Used Resources: README file resources section line 1.
 */
public class AlanBob {

    private static final int NOT_USED = -1;
    private static final int PLACEHOLDER = 0;

    public static class Result {
        public int alanSum;
        public int[] presents;
    }

    /**
     * This approach is using a Knapsack to calculate items that fit in
     * Sum(presents) / 2 where weight[i] = presents[i] and cost[i] = presents[i].
     *
     * @param presents
     * @return
     */
    public static Result dp1(int[] presents) {
        int[] weight = new int[presents.length + 1];
        int[] cost = new int[presents.length + 1];

        weight[0] = 0;
        cost[0] = 0;
        for (int i = 0; i < presents.length; ++i) {
            weight[i + 1] = presents[i];
            cost[i + 1] = presents[i];
        }

        int maxSum = 0;
        for (int i = 0; i < presents.length; ++i) {
            maxSum += presents[i];
        }

        Knapsack.Result kr = Knapsack.dp2(maxSum / 2, weight, cost);

        Result r = new Result();
        r.presents = new int[kr.items.length];
        for (int i = 0; i < r.presents.length; ++i) {
            r.presents[i] = presents[kr.items[i] - 1];
        }
        r.alanSum = 0;
        for (int i = 0; i < r.presents.length; ++i) {
            r.alanSum += r.presents[i];
        }

        return r;
    }

    /**
     * This approach calculates all possible sums that could be build by
     * using presents in additional array.
     *
     * @param presents
     * @return
     */
    public static Result dp2(int[] presents) {
        // calculate the maximum sum that could be obtained
        int maxSum = 0;
        for (int i = 0; i < presents.length; ++i) {
            maxSum += presents[i];
        }

        int[] preCalculated = new int[maxSum + 1];
        for (int i = 0; i < preCalculated.length; ++i) {
            preCalculated[i] = NOT_USED;
        }
        preCalculated[0] = PLACEHOLDER;

        for (int i = 0; i < presents.length; ++i) {
            for (int j = maxSum; j >= 0; --j) {
                if (preCalculated[j] != NOT_USED) {
                    if (preCalculated[j + presents[i]] == NOT_USED) {
                        preCalculated[j + presents[i]] = i;
                    }
                }
            }
        }

        for (int sum = maxSum / 2; sum > 0; --sum) {
            if (preCalculated[sum] != NOT_USED) {
                // This is the solution

                Result r = new Result();
                r.alanSum = sum;

                // Traceback the solution
                ArrayList<Integer> traceBack = new ArrayList<>();

                for (int sol = sum; sol > PLACEHOLDER; sol = sol - presents[preCalculated[sol]]) {
                    traceBack.add(presents[preCalculated[sol]]);
                }

                r.presents = new int[traceBack.size()];
                int i = 0;
                for (Integer present : traceBack) {
                    r.presents[i++] = present;
                }
                return r;
            }
        }

        // No solution
        return null;
    }
}
