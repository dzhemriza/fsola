/*
 * org.fsola
 *
 * File Name: MinCoinsFormingNTest.java
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

import org.fsola.dynamicprogramming.MinCoinsFormingN;
import org.junit.Assert;
import org.junit.Test;

public class MinCoinsFormingNTest {

    @Test
    public void test1Bfs() {
        int[] coins = new int[]{1, 2, 3};

        int N = 4;
        Assert.assertEquals(2, MinCoinsFormingN.solveBfs(coins, N));
        N = 5;
        Assert.assertEquals(2, MinCoinsFormingN.solveBfs(coins, N));
    }

    @Test
    public void test2Dp() {
        int[] coins = new int[]{1, 2, 3};

        int N = 4;
        Assert.assertEquals(2, MinCoinsFormingN.solveDp(coins, N));
        N = 5;
        Assert.assertEquals(2, MinCoinsFormingN.solveDp(coins, N));
    }
}
