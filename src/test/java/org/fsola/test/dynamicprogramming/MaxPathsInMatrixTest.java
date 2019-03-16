/*
 * org.fsola
 *
 * File Name: MaxPathsInMatrixTest.java
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

import org.fsola.dynamicprogramming.MaxPathsInMatrix;
import org.junit.Assert;
import org.junit.Test;

public class MaxPathsInMatrixTest {

    @Test
    public void test1() {
        // Matrix:
        // [X] [X] [X]
        // [X] [X] [X]
        //
        int M = 2;
        int N = 3;
        int r = MaxPathsInMatrix.solve1(M - 1, N - 1);
        System.out.println("Result: " + r);
        Assert.assertEquals(3, r);
    }

    @Test
    public void test2() {
        int M = 2;
        int N = 3;
        int r = MaxPathsInMatrix.solve2(M, N);
        System.out.println("Result: " + r);
        Assert.assertEquals(3, r);
    }

    @Test
    public void test3() {
        int M = 5;
        int N = 5;
        int r1 = MaxPathsInMatrix.solve1(M - 1, N - 1);
        System.out.println("Result 1: " + r1);
        int r2 = MaxPathsInMatrix.solve2(M, N);
        System.out.println("Result 2: " + r2);

        Assert.assertEquals(r1, r2);
    }
}
