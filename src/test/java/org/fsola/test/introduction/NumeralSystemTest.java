/*
 * org.fsola
 *
 * File Name: NumeralSystemTest.java
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

import org.fsola.introduction.CustomMath;
import org.fsola.introduction.NumeralSystem;
import org.junit.Assert;
import org.junit.Test;

public class NumeralSystemTest {

    @Test
    public void testNumeralSystemTest1() {
        System.out.println(
                "Convert 10 to base 2: " + NumeralSystem.convert(10, 2));
        System.out.println(
                "Convert 10 to base 16: " + NumeralSystem.convert(10, 16));
        System.out.println(
                "Convert 13 to base 16: " + NumeralSystem.convert(13, 16));

        Assert.assertEquals("1010", NumeralSystem.convert(10, 2));
        Assert.assertEquals("A", NumeralSystem.convert(10, 16));
        Assert.assertEquals("D", NumeralSystem.convert(13, 16));
    }

    @Test
    public void testNumeralSystemTest2() {
        System.out.println("Convert 0.125 to base 2: " +
                NumeralSystem.convertLessThan1(0.125, 2, 10));

        Assert.assertEquals("001",
                NumeralSystem.convertLessThan1(0.125, 2, 10));
    }

    @Test
    public void testNumeralSystemTest3() {
        System.out.println("Convert 10.125 to base 2: " +
                NumeralSystem.convertReal(10.125, 2, 10));

        Assert.assertEquals("1010.001",
                NumeralSystem.convertReal(10.125, 2, 10));
    }

    @Test
    public void testNumeralSystemTest4() {
        System.out.println(
                "Calculate 1010(2): " + NumeralSystem.calculate("1010", 2));

        Assert.assertEquals(10, NumeralSystem.calculate("1010", 2));
    }

    @Test
    public void testNumeralSystemTest5() {
        System.out.println("Calculate 001(2): " +
                NumeralSystem.calculateLessThan1("001", 2));

        Assert.assertTrue(CustomMath
                .equalsDouble(0.125, NumeralSystem.calculateLessThan1("001", 2),
                        0.0001));
    }

    @Test
    public void testNumeralSystemTest6() {
        System.out.println("Calculate 1010.001(2): " +
                NumeralSystem.calculateReal("1010.001", 2));
    }
}
