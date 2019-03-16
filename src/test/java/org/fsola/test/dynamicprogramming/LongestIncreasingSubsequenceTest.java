/*
 * org.fsola
 *
 * File Name: LongestIncreasingSubsequenceTest.java
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

import org.fsola.dynamicprogramming.LongestIncreasingSubsequence;
import org.junit.Assert;
import org.junit.Test;

public class LongestIncreasingSubsequenceTest {

    @Test
    public void test1() {
        int[] A = new int[]{10, 22, 9, 33, 21, 50, 41, 60};

        Assert.assertEquals(4, LongestIncreasingSubsequence.lis(A));
    }
}
