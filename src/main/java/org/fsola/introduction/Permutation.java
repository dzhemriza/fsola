/*
 * org.fsola
 *
 * File Name: Permutation.java
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
 * Used Resources: README file resources section line 1
 */
public class Permutation {

    private List<List<Integer>> allPermutations;
    /**
     * Boolean array used to mark used element in permutation.
     */
    private boolean[] used;
    /**
     * Currently generated permutation.
     */
    private int[] currentPermutation;
    /**
     * Local copy of {@link #solve(int)} method's parameter used to skip stack
     * push/pop in recursion.
     */
    private int N;

    /**
     * Method remembers current generated permutation and stores it into {@link
     * #allPermutations}.
     */
    private void rememberPermutation() {
        ArrayList<Integer> perm = new ArrayList<>();

        for (int i = 0; i < currentPermutation.length; ++i) {
            perm.add(currentPermutation[i] + 1);
        }

        allPermutations.add(perm);
    }

    /**
     * Recursive algorithm for finding all permutations.
     *
     * @param i
     */
    private void permute(int i) {
        if (i >= N) {
            rememberPermutation();
        } else {
            for (int k = 0; k < N; ++k) {
                if (!used[k]) {
                    used[k] = true;
                    currentPermutation[i] = k;
                    permute(i + 1);
                    used[k] = false;
                }
            }
        }
    }

    /**
     * Finds all permutations and store them in {@link #allPermutations} linked
     * list.
     *
     * @param n
     */
    public void solve(int n) {
        // Initialize all required data before finding all permutations
        allPermutations = new ArrayList<>();
        N = n;
        currentPermutation = new int[n];
        used = new boolean[n];

        for (int i = 0; i < used.length; ++i) {
            used[i] = false;
        }

        permute(0);

        // Cleanup used additional memory
        used = null;
        currentPermutation = null;
    }

    /**
     * @return Returns all generated permutations. {@code null} if {@link
     *         #solve(int)} method never called.
     */
    public List<List<Integer>> getAllPermutations() {
        return allPermutations;
    }
}
