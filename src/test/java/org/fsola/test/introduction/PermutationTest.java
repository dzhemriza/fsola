/*
 * org.fsola
 *
 * File Name: PermutationTest.java
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

import org.fsola.introduction.Permutation;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PermutationTest {

    @Test
    public void testPermutation1() {
        Integer[][] e =
                new Integer[][]{{1, 2, 3}, {1, 3, 2}, {2, 1, 3}, {2, 3, 1},
                        {3, 1, 2}, {3, 2, 1}};

        Permutation perm = new Permutation();

        perm.solve(3);
        List<List<Integer>> r = perm.getAllPermutations();

        Assert.assertEquals(e.length, r.size());
        for (int i = 0; i < e.length; ++i) {
            List<Integer> rp = r.get(i);

            Assert.assertEquals(e[i].length, rp.size());

            for (int j = 0; j < e[i].length; ++j) {
                Assert.assertEquals(e[i][j], rp.get(j));
            }
        }

        // Print permutation after successful test
        System.out.println("Print all permutations of N = 3");
        for (List<Integer> currentPerm : r) {
            for (Integer n : currentPerm) {
                System.out.print(n.toString() + " ");
            }
            System.out.println();
        }
    }
}
