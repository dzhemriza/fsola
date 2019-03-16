/*
 * org.fsola
 *
 * File Name: TwoSumTest.java
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

import org.fsola.misc.TwoSum;
import org.junit.Assert;
import org.junit.Test;

public class TwoSumTest {

    @Test
    public void test1() {
        int[] A = new int[]{1, 2, -1, -3, 4, -5};
        Assert.assertEquals(1, TwoSum.sol1(A));
    }

    @Test
    public void test2() {
        int[] A = new int[]{1, 2, 1, 3, 4, 5};
        Assert.assertEquals(0, TwoSum.sol1(A));
    }

    @Test
    public void test3() {
        int[] A = new int[]{1, 2, -1, -3, 4, -5};
        Assert.assertEquals(1, TwoSum.sol2(A));
    }

    @Test
    public void test4() {
        int[] A = new int[]{1, 2, 1, 3, 4, 5};
        Assert.assertEquals(0, TwoSum.sol2(A));
    }

    @Test
    public void test5() {
        int[] A = new int[]{1, 2, -1, -3, 4, -5};
        Assert.assertEquals(1, TwoSum.sol3(A));
    }

    @Test
    public void test6() {
        int[] A = new int[]{1, 2, 1, 3, 4, 5};
        Assert.assertEquals(0, TwoSum.sol3(A));
    }
}
