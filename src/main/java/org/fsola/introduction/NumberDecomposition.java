/*
 * org.fsola
 *
 * File Name: NumberDecomposition.java
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
public class NumberDecomposition {

    /**
     * Decompose a given number N into a sum of sub numbers.
     *
     * @param n
     * @return
     */
    public static List<Integer> decomposition(int n) {
        ArrayList<Integer> result = new ArrayList<>();

        int numberForDecomposition = n;

        for (int i = 2; numberForDecomposition != 1; ++i) {
            int howToDecompose = 0;

            while ((numberForDecomposition % i) == 0) {
                howToDecompose++;
                numberForDecomposition = numberForDecomposition / i;
            }

            for (int j = 0; j < howToDecompose; ++j) {
                result.add(i);
            }
        }

        return result;
    }
}
