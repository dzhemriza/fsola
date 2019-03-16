/*
 * org.fsola
 *
 * File Name: WeightedQuickUnionTest.java
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
package org.fsola.test.graphtheory;

import org.fsola.graphtheory.WeightedQuickUnion;
import org.junit.Assert;
import org.junit.Test;

public class WeightedQuickUnionTest {

    // Graph
    //
    //   [0]----------[2]         [4]-------------[5]
    //    |                        |
    //    |                        |
    //    |                        |
    //   [1]                      [3]             [6]
    //

    @Test
    public void test1() {
        final int N = 7;
        WeightedQuickUnion qf = new WeightedQuickUnion(N);

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (i == j) {
                    Assert.assertTrue(qf.isConnected(i, j));
                } else {
                    Assert.assertFalse(qf.isConnected(i, j));
                }
            }
        }

        qf.union(0, 2);
        qf.union(0, 1);

        Assert.assertTrue(qf.isConnected(2, 1));
        Assert.assertTrue(qf.isConnected(0, 1));
        Assert.assertTrue(qf.isConnected(0, 2));

        Assert.assertFalse(qf.isConnected(2, 4));
        Assert.assertFalse(qf.isConnected(0, 3));
        Assert.assertFalse(qf.isConnected(1, 5));

        qf.union(4, 5);
        qf.union(4, 3);

        Assert.assertTrue(qf.isConnected(4, 5));
        Assert.assertTrue(qf.isConnected(5, 3));
        Assert.assertTrue(qf.isConnected(4, 3));

        Assert.assertFalse(qf.isConnected(2, 4));
        Assert.assertFalse(qf.isConnected(0, 3));
        Assert.assertFalse(qf.isConnected(1, 5));

        Assert.assertFalse(qf.isConnected(0, 6));
        Assert.assertFalse(qf.isConnected(0, 5));
    }
}
