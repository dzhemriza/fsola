/*
 * org.fsola
 *
 * File Name: RomanNumbersTest.java
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

import org.fsola.introduction.RomanNumbers;
import org.junit.Assert;
import org.junit.Test;

public class RomanNumbersTest {

    @Test
    public void testRomanNumbers1() {
        System.out.println(
                "20 to roman number = " + RomanNumbers.decimalToRoman(20));
        System.out.println(
                "2014 to roman number = " + RomanNumbers.decimalToRoman(2014));
        System.out.println(
                "207 to roman number = " + RomanNumbers.decimalToRoman(207));

        Assert.assertEquals("XX", RomanNumbers.decimalToRoman(20));
        Assert.assertEquals("CCVII", RomanNumbers.decimalToRoman(207));
        Assert.assertEquals("MMXIV", RomanNumbers.decimalToRoman(2014));
    }

    @Test
    public void testRomanNumbers2() {
        System.out.println(
                "XX to decimal number = " + RomanNumbers.romanToDecimal("XX"));
        System.out.println("MMXIV to decimal number = " +
                RomanNumbers.romanToDecimal("MMXIV"));
        System.out.println("CCVII to decimal number = " +
                RomanNumbers.romanToDecimal("CCVII"));

        Assert.assertEquals(20, RomanNumbers.romanToDecimal("XX"));
        Assert.assertEquals(2014, RomanNumbers.romanToDecimal("MMXIV"));
        Assert.assertEquals(207, RomanNumbers.romanToDecimal("CCVII"));
    }
}
