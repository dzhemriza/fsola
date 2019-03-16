/*
 * org.fsola
 *
 * File Name: BinomialCoefficientTest.java
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

import org.fsola.introduction.BinomialCoefficient;
import org.fsola.introduction.BinomialCoefficientFact;
import org.junit.Assert;
import org.junit.Test;

public class BinomialCoefficientTest {

    @Test
    public void testBinomialCoefficient1() {
        System.out.println("n = 5, k = 3 => " +
                BinomialCoefficient.binomialCoefficient(5, 3));

        Assert.assertEquals(10, BinomialCoefficient.binomialCoefficient(5, 3));
        Assert.assertEquals(4950,
                BinomialCoefficient.binomialCoefficient(100, 2));
    }

    @Test
    public void testBinomialCoefficient2() {
        System.out.println("n = 5, k = 3 => " +
                BinomialCoefficient.binomialCoefficient2(5, 3));

        Assert.assertEquals(10, BinomialCoefficient.binomialCoefficient2(5, 3));
        Assert.assertEquals(4950,
                BinomialCoefficient.binomialCoefficient2(100, 2));
    }

    @Test
    public void testPrintPascalTriangle() {
        BinomialCoefficient.printPascalTriangle(9);
    }

    @Test
    public void testBinomialCoefficientFact() {
        System.out.println("n = 5, k = 3 => " +
                BinomialCoefficientFact.binomialCoefficient(5, 3));
        Assert.assertEquals(10, BinomialCoefficient.binomialCoefficient(5, 3));
    }
}
