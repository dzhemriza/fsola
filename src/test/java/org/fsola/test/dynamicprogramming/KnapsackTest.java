/*
 * org.fsola
 *
 * File Name: KnapsackTest.java
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

import org.fsola.dynamicprogramming.Knapsack;
import org.junit.Assert;
import org.junit.Test;

public class KnapsackTest {

    @Test
    public void test1() {
        // First element is ignored and it's always 0
        int capacity = 10;
        int[] weight = new int[]{0, 2, 3, 5, 6, 1};
        int[] cost = new int[]{0, 2, 3, 4, 2, 1};

        Knapsack.Result r = Knapsack.dp1(capacity, weight, cost);
        Assert.assertNotNull(r);

        System.out.println("Best: " + r.best);
        System.out.print("Items:");
        for (int i = 0; i < r.items.length; ++i) {
            System.out.print(" " + r.items[i]);
        }
        System.out.println();
    }

    @Test
    public void test2() {
        // First element is ignored and it's always 0
        int capacity = 10;
        int[] weight = new int[]{0, 2, 3, 5, 6, 1};
        int[] cost = new int[]{0, 2, 3, 4, 2, 1};

        Knapsack.Result r = Knapsack.dp2(capacity, weight, cost);
        Assert.assertNotNull(r);

        System.out.println("Best: " + r.best);
        System.out.print("Items:");
        for (int i = 0; i < r.items.length; ++i) {
            System.out.print(" " + r.items[i]);
        }
        System.out.println();
    }

    @Test
    public void test3() {
        // First element is ignored and it's always 0
        int capacity = 70;
        int[] weight = new int[]{0, 30, 15, 50, 10, 20, 40, 5, 65};
        int[] cost = new int[]{0, 5, 3, 9, 1, 2, 7, 1, 12};

        Knapsack.Result r = Knapsack.dp1(capacity, weight, cost);
        Assert.assertNotNull(r);

        System.out.println("Best: " + r.best);
        System.out.print("Items:");
        for (int i = 0; i < r.items.length; ++i) {
            System.out.print(" " + r.items[i]);
        }
        System.out.println();
    }

    @Test
    public void test4() {
        // First element is ignored and it's always 0
        int capacity = 70;
        int[] weight = new int[]{0, 30, 15, 50, 10, 20, 40, 5, 65};
        int[] cost = new int[]{0, 5, 3, 9, 1, 2, 7, 1, 12};

        Knapsack.Result r = Knapsack.dp2(capacity, weight, cost);
        Assert.assertNotNull(r);

        System.out.println("Best: " + r.best);
        System.out.print("Items:");
        for (int i = 0; i < r.items.length; ++i) {
            System.out.print(" " + r.items[i]);
        }
        System.out.println();
    }

    @Test
    public void test5() {
        // First element is ignored and it's always 0
        int capacity = 50;
        int[] weight = new int[]{0, 14, 26, 12, 16};
        int[] cost = new int[]{0, 7, 5, 5, 5};

        Knapsack.Result r = Knapsack.dp2(capacity, weight, cost);
        Assert.assertNotNull(r);

        System.out.println("Best: " + r.best);
        System.out.print("Items:");
        for (int i = 0; i < r.items.length; ++i) {
            System.out.print(" " + r.items[i]);
        }
        System.out.println();
    }
}
