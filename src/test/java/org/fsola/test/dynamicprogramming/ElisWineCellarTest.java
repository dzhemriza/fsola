/*
 * org.fsola
 *
 * File Name: ElisWineCellarTest.java
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

import org.fsola.dynamicprogramming.ElisWineCellar;
import org.junit.Assert;
import org.junit.Test;

public class ElisWineCellarTest {

    @Test
    public void test1() {
        int[] P = new int[]{1, 4, 2, 3};
        int r = ElisWineCellar.solve1(P);
        System.out.println("Max price: " + r);
        Assert.assertEquals(29, r);
    }

    @Test
    public void test2() {
        int[] P = new int[]{1, 4, 2, 3};
        int r = ElisWineCellar.solve2(P);
        System.out.println("Max price: " + r);
        Assert.assertEquals(29, r);
    }

    @Test
    public void test3() {
        int[] P = new int[]{1, 4, 2, 3};
        int r = ElisWineCellar.solve3(P);
        System.out.println("Max price: " + r);
        Assert.assertEquals(29, r);
    }
}
