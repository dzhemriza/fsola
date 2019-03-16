/*
 * org.fsola
 *
 * File Name: LowestCommonMultipleTest.java
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

import org.fsola.introduction.LowestCommonMultiple;
import org.junit.Assert;
import org.junit.Test;

public class LowestCommonMultipleTest {

    @Test
    public void testLowestCommonDenominator1() {
        System.out.println(
                "LCM(5, 10): " + LowestCommonMultiple.lcm(new int[]{5, 10}));
        System.out.println("LCM(2, 8, 16): " +
                LowestCommonMultiple.lcm(new int[]{2, 8, 16}));
        System.out.println("LCM(2, 8, 18): " +
                LowestCommonMultiple.lcm(new int[]{2, 8, 18}));

        Assert.assertEquals(10, LowestCommonMultiple.lcm(new int[]{5, 10}));
        Assert.assertEquals(16, LowestCommonMultiple.lcm(new int[]{2, 8, 16}));
        Assert.assertEquals(72, LowestCommonMultiple.lcm(new int[]{2, 8, 18}));
    }

    @Test
    public void testLowestCommonDenominator2() {
        System.out.println("LCM(5, 10): " +
                LowestCommonMultiple.lcmIter(new int[]{5, 10}));
        System.out.println("LCM(2, 8, 16): " +
                LowestCommonMultiple.lcmIter(new int[]{2, 8, 16}));
        System.out.println("LCM(2, 8, 18): " +
                LowestCommonMultiple.lcmIter(new int[]{2, 8, 18}));

        Assert.assertEquals(10, LowestCommonMultiple.lcmIter(new int[]{5, 10}));
        Assert.assertEquals(16,
                LowestCommonMultiple.lcmIter(new int[]{2, 8, 16}));
        Assert.assertEquals(72,
                LowestCommonMultiple.lcmIter(new int[]{2, 8, 18}));
    }
}
