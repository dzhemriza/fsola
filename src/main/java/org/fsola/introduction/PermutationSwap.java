/*
 * org.fsola
 *
 * File Name: PermutationSwap.java
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
public class PermutationSwap {

    private List<List<Integer>> allPermutations;
    /**
     * Currently generated permutation.
     */
    private int[] currentPermutation;

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
     * Recursive algorithm for finding all permutations using swap.
     *
     * @param k
     */
    private void permute(int k) {
        if (k == 0) {
            rememberPermutation();
        } else {
            permute(k - 1);

            for (int i = 0; i < k - 1; ++i) {
                int swap = currentPermutation[i];
                currentPermutation[i] = currentPermutation[k - 1];
                currentPermutation[k - 1] = swap;

                permute(k - 1);

                swap = currentPermutation[i];
                currentPermutation[i] = currentPermutation[k - 1];
                currentPermutation[k - 1] = swap;
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
        currentPermutation = new int[n];

        for (int i = 0; i < n; ++i) {
            currentPermutation[i] = i;
        }

        permute(n);

        // Cleanup used additional memory
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
