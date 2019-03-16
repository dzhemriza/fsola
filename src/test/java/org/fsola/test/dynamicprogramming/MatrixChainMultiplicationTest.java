/*
 * org.fsola
 *
 * File Name: MatrixChainMultiplicationTest.java
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
package org.fsola.test.dynamicprogramming;

import org.fsola.dynamicprogramming.MatrixChainMultiplication;
import org.junit.Assert;
import org.junit.Test;

public class MatrixChainMultiplicationTest {

    @Test
    public void test1() {
        int[] dimensions = new int[]{40, 20, 30, 10, 30};
        int r = MatrixChainMultiplication.sol1(dimensions, 1, dimensions.length - 1);
        Assert.assertEquals(26000, r);
    }

    @Test
    public void test2() {
        int[] dimensions = new int[]{10, 20, 30, 40, 30};
        int r = MatrixChainMultiplication.sol1(dimensions, 1, dimensions.length - 1);
        Assert.assertEquals(30000, r);
    }

    @Test
    public void test3() {
        int[] dimensions = new int[]{10, 20, 30};
        int r = MatrixChainMultiplication.sol1(dimensions, 1, dimensions.length - 1);
        Assert.assertEquals(6000, r);
    }

    @Test
    public void test4() {
        int[] dimensions = new int[]{40, 20, 30, 10, 30};
        int r = MatrixChainMultiplication.sol2(dimensions);
        Assert.assertEquals(26000, r);
    }

    @Test
    public void test5() {
        int[] dimensions = new int[]{10, 20, 30, 40, 30};
        int r = MatrixChainMultiplication.sol2(dimensions);
        Assert.assertEquals(30000, r);
    }

    @Test
    public void test6() {
        int[] dimensions = new int[]{10, 20, 30};
        int r = MatrixChainMultiplication.sol2(dimensions);
        Assert.assertEquals(6000, r);
    }

    @Test
    public void test7() {
        int[] dimensions = new int[]{40, 20, 30, 10, 30};
        int r = MatrixChainMultiplication.sol3(dimensions);
        Assert.assertEquals(26000, r);
    }

    @Test
    public void test8() {
        int[] dimensions = new int[]{10, 20, 30, 40, 30};
        int r = MatrixChainMultiplication.sol3(dimensions);
        Assert.assertEquals(30000, r);
    }

    @Test
    public void test9() {
        int[] dimensions = new int[]{10, 20, 30};
        int r = MatrixChainMultiplication.sol3(dimensions);
        Assert.assertEquals(6000, r);
    }
}
