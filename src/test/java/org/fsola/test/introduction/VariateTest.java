/*
 * org.fsola
 *
 * File Name: VariateTest.java
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

import org.fsola.introduction.Variate;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class VariateTest {

    @Test
    public void testVariate1() {
        Variate variate = new Variate();
        variate.solve(4, 2);
        List<List<Integer>> variations = variate.getAllVariations();

        System.out.println("All Variations:");
        for (List<Integer> var : variations) {
            for (Integer i : var) {
                System.out.print(" " + i);
            }
            System.out.println();
        }

        int[][] expected =
                new int[][]{{1, 1}, {1, 2}, {1, 3}, {1, 4}, {2, 1}, {2, 2},
                        {2, 3}, {2, 4}, {3, 1}, {3, 2}, {3, 3}, {3, 4}, {4, 1},
                        {4, 2}, {4, 3}, {4, 4}};

        Assert.assertEquals(expected.length, variations.size());
        for (int i = 0; i < expected.length; ++i) {
            Assert.assertEquals(expected[i].length, variations.get(i).size());
            for (int j = 0; j < expected[i].length; ++j) {
                Assert.assertEquals(Integer.valueOf(expected[i][j]),
                        variations.get(i).get(j));
            }
        }
    }
}
