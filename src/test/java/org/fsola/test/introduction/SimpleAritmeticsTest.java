/*
 * org.fsola
 *
 * File Name: SimpleAritmeticsTest.java
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

import org.fsola.introduction.SimpleAritmetics;
import org.junit.Assert;
import org.junit.Test;
import sun.java2d.pipe.SpanShapeRenderer;

import java.util.ArrayList;
import java.util.List;

public class SimpleAritmeticsTest {

    @Test
    public void testCountOfDigitsOfNumber1() {
        Assert.assertEquals(4, SimpleAritmetics.countOfDigitsOfNumber(1234));
        Assert.assertEquals(0, SimpleAritmetics.countOfDigitsOfNumber(0));
    }

    @Test
    public void testSum1() {
        Assert.assertEquals(15, SimpleAritmetics.sum(5));
        Assert.assertEquals(0, SimpleAritmetics.sum(0));
    }

    @Test
    public void testSum2() {
        Assert.assertEquals(15, SimpleAritmetics.sumOptimized(5));
        Assert.assertEquals(0, SimpleAritmetics.sumOptimized(0));
    }

    @Test
    public void testSum3() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        Assert.assertEquals(15, SimpleAritmetics.sum(arr));
        Assert.assertEquals(0, SimpleAritmetics.sum(new int[0]));
    }

    @Test
    public void testSum4() {
        Assert.assertEquals(15, SimpleAritmetics.sumRecursive(5));
        Assert.assertEquals(0, SimpleAritmetics.sumRecursive(0));
    }

    @Test
    public void testMult() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        Assert.assertEquals(120, SimpleAritmetics.mult(arr));
        Assert.assertEquals(1, SimpleAritmetics.mult(new int[0]));
    }

    @Test
    public void testPower() {
        Assert.assertEquals(64, SimpleAritmetics.power(2, 6));
    }

    @Test
    public void testFastPower() {
        for (int i = 2; i < 7; ++i) {
            for (int j = 2; j < 7; ++j) {
                Assert.assertEquals(SimpleAritmetics.power(i, j),
                        SimpleAritmetics.fastPower(i, j));
            }
        }
    }

    @Test
    public void testFac() {
        Assert.assertEquals(120, SimpleAritmetics.factorielRecursive(5));
        Assert.assertEquals(120, SimpleAritmetics.factorielNonRecursive(5));
    }

    @Test
    public void testMatrixSum() {
        int[][] a = {{1, 2}, {3, 4}};
        int[][] b = {{5, 6}, {7, 8}};
        int[][] c = SimpleAritmetics.matrixSum(a, b);
        int[][] d = {{6, 8}, {10, 12}};
        for (int i = 0; i < c.length; ++i) {
            for (int j = 0; j < c[i].length; ++j) {
                Assert.assertEquals(d[i][j], c[i][j]);
            }
        }
    }

    @Test
    public void testMatrixMult() {
        int[][] a = {{1, 2}, {3, 4}};
        int[][] b = {{5, 6}, {7, 8}};
        int[][] c = SimpleAritmetics.matrixMult(a, b);
        int[][] d = {{19, 22}, {43, 50}};
        for (int i = 0; i < c.length; ++i) {
            for (int j = 0; j < c[i].length; ++j) {
                Assert.assertEquals(d[i][j], c[i][j]);
            }
        }
    }

    @Test
    public void testDigitsNFac() {
        Assert.assertEquals(3, SimpleAritmetics.digitsNFac(5));
        Assert.assertEquals(7, SimpleAritmetics.digitsNFac(10));
    }

    @Test
    public void testReverseArray() {
        Integer[] a = new Integer[]{1, 2, 3};
        SimpleAritmetics.reverseArray(a);

        Assert.assertArrayEquals(new Integer[]{3, 2, 1}, a);
    }

    @Test
    public void testDecomposeNumberToDigits1() {
        ArrayList<Integer> e = new ArrayList<>();
        e.add(1);
        e.add(2);
        e.add(3);
        e.add(4);

        List<Integer> d = SimpleAritmetics.decomposeNumberToDigits(1234);

        Assert.assertArrayEquals(e.toArray(), d.toArray());
    }

    @Test
    public void testDecomposeNumberToDigits2() {
        ArrayList<Integer> e = new ArrayList<>();
        e.add(1);
        e.add(2);
        e.add(3);
        e.add(4);

        List<Integer> d = SimpleAritmetics.decomposeNumberToDigitsRec(1234);

        Assert.assertArrayEquals(e.toArray(), d.toArray());
    }

    @Test
    public void testPrintIncreasingAndDecreasingSequence1() {
        List<Integer> r = SimpleAritmetics
                .printIncreasingAndDecreasingSequenceRec(1, 10, 5);

        System.out.print("Sequence:");
        for (int i = 0; i < r.size(); ++i) {
            System.out.print(" " + r.get(i));
        }
        System.out.println();

        Integer[] e =
                new Integer[]{10, 100, 1000, 10000, 100000, 100000, 10000, 1000,
                        100, 10};
        Assert.assertArrayEquals(e, r.toArray());
    }

    @Test
    public void testPrintIncreasingAndDecreasingSequence2() {
        List<Integer> r = SimpleAritmetics
                .printIncreasingAndDecreasingSequenceIter(5);

        System.out.print("Sequence:");
        for (int i = 0; i < r.size(); ++i) {
            System.out.print(" " + r.get(i));
        }
        System.out.println();

        Integer[] e =
                new Integer[]{10, 100, 1000, 10000, 100000, 100000, 10000, 1000,
                        100, 10};
        Assert.assertArrayEquals(e, r.toArray());
    }
}
