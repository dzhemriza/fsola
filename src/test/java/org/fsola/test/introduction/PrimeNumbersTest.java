/*
 * org.fsola
 *
 * File Name: PrimeNumbersTest.java
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

import org.fsola.introduction.PrimeNumbers;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PrimeNumbersTest {

    @Test
    public void testPrimeNumberTest1() {
        Assert.assertEquals(true, PrimeNumbers.isPrimeSimple(2));
        Assert.assertEquals(true, PrimeNumbers.isPrimeSimple(3));
        Assert.assertEquals(true, PrimeNumbers.isPrimeSimple(5));
        Assert.assertEquals(false, PrimeNumbers.isPrimeSimple(16));
    }

    @Test
    public void testPrimeNumberTest2() {
        Assert.assertEquals(true, PrimeNumbers.isPrimeDiv(2));
        Assert.assertEquals(true, PrimeNumbers.isPrimeDiv(3));
        Assert.assertEquals(true, PrimeNumbers.isPrimeDiv(5));
        Assert.assertEquals(false, PrimeNumbers.isPrimeDiv(16));
        Assert.assertEquals(false, PrimeNumbers.isPrimeDiv(55));
    }

    @Test
    public void testPrimeNumberTest3() {
        List<Integer> primes = PrimeNumbers.eratosthenes(10000);
        for (int i = 0; i < primes.size(); ++i) {
            Assert.assertEquals(true,
                    PrimeNumbers.isPrimeSimple(primes.get(i)));
        }
    }

    @Test
    public void testPrimeNumberTest4() {
        List<Integer> primes = PrimeNumbers.findPrimes(1000000);
        for (int i = 0; i < primes.size(); ++i) {
            Assert.assertEquals(true,
                    PrimeNumbers.isPrimeSimple(primes.get(i)));
        }
    }
}
