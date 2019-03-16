/*
 * org.fsola
 *
 * File Name: Combinations.java
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
 * Finds all k combinations
 * <p/>
 * Used Resources: README file resources section line 1
 */
public class Combinations {

    /**
     * Stores current solution.
     */
    private static int[] m;
    private static List<List<Integer>> result;
    private static int N;
    private static int K;

    private static void rememberSolution() {
        ArrayList<Integer> s = new ArrayList<>();
        for (int i : m) {
            s.add(i);
        }
        result.add(s);
    }

    private static void comb(int i, int after) {
        if (i > K) {
            return;
        }
        for (int j = after + 1; j <= N; ++j) {
            m[i - 1] = j;

            if (i == K) {
                rememberSolution();
            }

            comb(i + 1, j);
        }
    }

    public static List<List<Integer>> solve(int n, int k) {
        result = new ArrayList<>();
        m = new int[k];
        N = n;
        K = k;

        comb(1, 0);

        return result;
    }
}
