/*
 * org.fsola
 *
 * File Name: PrintMatrixInSpiralFormTest.java
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
package org.fsola.test.misc;

import org.fsola.misc.PrintMatrixInSpiralForm;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PrintMatrixInSpiralFormTest {

    private static void print(List<Integer> r) {
        System.out.print("Spiral:");
        for (Integer i : r) {
            System.out.print(" " + i);
        }
        System.out.println();
    }

    private static void verify(int[] expected, List<Integer> r) {
        int x = 0;
        for (Integer i : r) {
            Assert.assertEquals(Integer.valueOf(expected[x++]), i);
        }
        Assert.assertEquals(expected.length, r.size());
    }

    @Test
    public void test1() {
        Assert.assertTrue(true);
        int[][] m = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[] expected = new int[]{1, 2, 3, 6, 9, 8, 7, 4, 5};

        List<Integer> r = PrintMatrixInSpiralForm.spiral(m);
        Assert.assertNotNull(r);

        print(r);
        verify(expected, r);
    }

    @Test
    public void test2() {
        Assert.assertTrue(true);
        int[][] m = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
        };
        int[] expected = new int[]{1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7};

        List<Integer> r = PrintMatrixInSpiralForm.spiral(m);
        Assert.assertNotNull(r);

        print(r);
        verify(expected, r);
    }

    @Test
    public void test3() {
        Assert.assertTrue(true);
        int[][] m = new int[][]{
                {1, 2, 3, 4}
        };
        int[] expected = new int[]{1, 2, 3, 4};

        List<Integer> r = PrintMatrixInSpiralForm.spiral(m);
        Assert.assertNotNull(r);

        print(r);
        verify(expected, r);
    }

    @Test
    public void test4() {
        Assert.assertTrue(true);
        int[][] m = new int[][]{
                {1}
        };
        int[] expected = new int[]{1};

        List<Integer> r = PrintMatrixInSpiralForm.spiral(m);
        Assert.assertNotNull(r);

        print(r);
        verify(expected, r);
    }
}
