/*
 * org.fsola
 *
 * File Name: SumZeroTest.java
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

import org.fsola.introduction.SumZero;
import org.fsola.introduction.Variate;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SumZeroTest {

    @Test
    public void testSumZero1() {
        List<List<Integer>> r =
                SumZero.solve(new int[]{1, 2, 3, 4, 5, 6, 7, 8});

        for (List<Integer> s : r) {
            System.out.print("SumZero Solution:");
            for (Integer i : s) {
                System.out.print(" " + i);
            }
            System.out.println();
        }

        // Check for sum equals to zero
        for (List<Integer> s : r) {
            int sum = 0;
            for (Integer i : s) {
                sum += i;
            }
            Assert.assertEquals(0, sum);
        }
    }
}
