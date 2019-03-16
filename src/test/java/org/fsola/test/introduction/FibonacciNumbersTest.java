/*
 * org.fsola
 *
 * File Name: FibonacciNumbersTest.java
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

import org.fsola.introduction.FibonacciNumbers;
import org.junit.Assert;
import org.junit.Test;

public class FibonacciNumbersTest {

    @Test
    public void testFibonacciNumbers1() {
        System.out.print("Fibonacci Rec: ");
        for (int i = 0; i < 20; ++i) {
            System.out.print("" +
                    FibonacciNumbers.fibonacciRec(i) + ", ");
        }
        System.out.println();

        Assert.assertEquals(144, FibonacciNumbers.fibonacciRec(12));
        Assert.assertEquals(610, FibonacciNumbers.fibonacciRec(15));
        Assert.assertEquals(4181, FibonacciNumbers.fibonacciRec(19));
    }

    @Test
    public void testFibonacciNumbers2() {
        System.out.print("Fibonacci Iter: ");
        for (int i = 0; i < 20; ++i) {
            System.out.print("" +
                    FibonacciNumbers.fibonacciIter(i) + ", ");
        }
        System.out.println();

        Assert.assertEquals(144, FibonacciNumbers.fibonacciIter(12));
        Assert.assertEquals(610, FibonacciNumbers.fibonacciIter(15));
        Assert.assertEquals(4181, FibonacciNumbers.fibonacciIter(19));
    }

    @Test
    public void testFibonacciNumbers3() {
        System.out.print("Fibonacci Iter2: ");
        for (int i = 0; i < 20; ++i) {
            System.out.print("" +
                    FibonacciNumbers.fibonacciIter2(i) + ", ");
        }
        System.out.println();

        Assert.assertEquals(144, FibonacciNumbers.fibonacciIter2(12));
        Assert.assertEquals(610, FibonacciNumbers.fibonacciIter2(15));
        Assert.assertEquals(4181, FibonacciNumbers.fibonacciIter2(19));
    }

    @Test
    public void testFibonacciNumbers4() {
        System.out.print("Fibonacci Array: ");
        for (int i = 0; i < 20; ++i) {
            System.out.print("" +
                    FibonacciNumbers.fibonacciArray(i) + ", ");
        }
        System.out.println();

        Assert.assertEquals(144, FibonacciNumbers.fibonacciArray(12));
        Assert.assertEquals(610, FibonacciNumbers.fibonacciArray(15));
        Assert.assertEquals(4181, FibonacciNumbers.fibonacciArray(19));
    }

    @Test
    public void testFibonacciNumbers5() {
        System.out.print("Fibonacci Matrix: ");
        for (int i = 0; i < 20; ++i) {
            System.out.print("" +
                    FibonacciNumbers.fibonacciMatrix(i) + ", ");
        }
        System.out.println();

        Assert.assertEquals(144, FibonacciNumbers.fibonacciMatrix(12));
        Assert.assertEquals(610, FibonacciNumbers.fibonacciMatrix(15));
        Assert.assertEquals(4181, FibonacciNumbers.fibonacciMatrix(19));
    }
}
