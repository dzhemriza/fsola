/*
 * org.fsola
 *
 * File Name: CombinationsTest.java
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
package org.fsola.test.introduction;

import org.fsola.introduction.Combinations;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CombinationsTest {

    @Test
    public void testCombinations1() {
        List<List<Integer>> r = Combinations.solve(5, 3);

        for (List<Integer> s : r) {
            System.out.print("Combination:");
            for (Integer i : s) {
                System.out.print(" " + i);
            }
            System.out.println();
        }

        int[][] e = {{1, 2, 3}, {1, 2, 4}, {1, 2, 5}, {1, 3, 4}, {1, 3, 5},
                {1, 4, 5}, {2, 3, 4}, {2, 3, 5}, {2, 4, 5}, {3, 4, 5}};

        Assert.assertEquals(e.length, r.size());
        for (int i = 0; i < e.length; ++i) {
            Assert.assertEquals(e[i].length, r.get(i).size());

            for (int j = 0; j < e[i].length; ++j) {
                Assert.assertEquals(Integer.valueOf(e[i][j]), r.get(i).get(j));
            }
        }
    }
}
