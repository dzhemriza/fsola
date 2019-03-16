/*
 * org.fsola
 *
 * File Name: SumZero.java
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
 * For a given set of numbers a1, a2, a3, ..., an find all combinations of plus
 * and minus that the sum is equals of zero.
 * <p/>
 * Used Resources: README file resources section line 1
 */
public class SumZero {
    private static ArrayList<List<Integer>> result;
    private static int[] a;

    /**
     * Check for given solution is it equals to zero.
     *
     * @return
     */
    private static boolean isSumZero() {
        int s = 0;

        for (int i = 0; i < a.length; ++i) {
            s += a[i];
        }

        return (s == 0);
    }

    /**
     * Checks is current variation is a solution or not.
     */
    private static void checkSolution() {
        if (isSumZero()) {
            ArrayList<Integer> s = new ArrayList<>();
            for (int i : a) {
                s.add(i);
            }
            result.add(s);
        }
    }

    /**
     * Generates variations
     *
     * @param i
     */
    private static void variate(int i) {
        if (i >= a.length) {
            checkSolution();
            return;
        }

        a[i] = Math.abs(a[i]);
        variate(i + 1);
        a[i] = -Math.abs(a[i]);
        variate(i + 1);
    }

    public static List<List<Integer>> solve(int[] a) {
        result = new ArrayList<>();
        SumZero.a = a;

        variate(0);

        return result;
    }
}
