/*
 * org.fsola
 *
 * File Name: GreatestCommonDivisorTest.java
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

import org.fsola.introduction.GreatestCommonDivisor;
import org.junit.Assert;
import org.junit.Test;

public class GreatestCommonDivisorTest {

    @Test
    public void testGreatestCommonDivisorTest1() {
        System.out
                .println("GCD(5, 10): " + GreatestCommonDivisor.gcdIter(5, 10));
        System.out.println(
                "GCD(111, 11): " + GreatestCommonDivisor.gcdIter(111, 11));

        Assert.assertEquals(5, GreatestCommonDivisor.gcdIter(5, 10));
        Assert.assertEquals(1, GreatestCommonDivisor.gcdIter(111, 11));
    }

    @Test
    public void testGreatestCommonDivisorTest2() {
        System.out
                .println("GCD(5, 10): " + GreatestCommonDivisor.gcdRec(5, 10));
        System.out.println(
                "GCD(111, 11): " + GreatestCommonDivisor.gcdRec(111, 11));

        Assert.assertEquals(5, GreatestCommonDivisor.gcdRec(5, 10));
        Assert.assertEquals(1, GreatestCommonDivisor.gcdRec(111, 11));
    }
}
