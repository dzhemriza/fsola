/*
 * org.fsola
 *
 * File Name: Variate.java
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
public class Variate {

    /**
     * Store all variations
     */
    private List<List<Integer>> allVariations;

    /**
     * Store current variation
     */
    int[] currentVariation;

    int N; // Used to avoid putting in stack in recursion
    int K; // Used to avoid putting in stack in recursion

    private void rememberVariation() {
        ArrayList<Integer> variation = new ArrayList<>();

        for (int i : currentVariation) {
            variation.add(i + 1);
        }

        allVariations.add(variation);
    }

    public void variate(int i) {
        if (i >= K) {
            rememberVariation();
        } else {
            for (int j = 0; j < N; ++j) {
                currentVariation[i] = j;
                variate(i + 1);
            }
        }
    }

    public void solve(int n, int k) {
        currentVariation = new int[k];
        allVariations = new ArrayList<>();
        N = n;
        K = k;

        variate(0);

        currentVariation = null;
    }

    public List<List<Integer>> getAllVariations() {
        return allVariations;
    }
}
