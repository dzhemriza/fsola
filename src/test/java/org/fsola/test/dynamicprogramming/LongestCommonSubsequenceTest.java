/*
 * org.fsola
 *
 * File Name: LongestCommonSubsequenceTest.java
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

import org.fsola.dynamicprogramming.LongestCommonSubsequence;
import org.junit.Assert;
import org.junit.Test;

public class LongestCommonSubsequenceTest {

    @Test
    public void test1() {
        int[] A = new int[]{1, 2, 4, 6, 13};
        int[] B = new int[]{2, 4, 5, 7, 31, 42};
        int r = LongestCommonSubsequence.lcs1(A, B);
        Assert.assertEquals(2, r);
    }

    @Test
    public void test2() {
        int[] A = new int[]{1, 2, 4, 6, 13};
        int[] B = new int[]{2, 4, 5, 7, 31, 42};
        int r = LongestCommonSubsequence.lcs2(A, B, A.length - 1, B.length - 1);
        Assert.assertEquals(2, r);
    }

    @Test
    public void test3() {
        int[] A = new int[]{1, 2, 4, 6, 13};
        int[] B = new int[]{2, 4, 5, 7, 31, 42};
        int r = LongestCommonSubsequence.lcs3(A, B);
        Assert.assertEquals(2, r);
    }

    @Test
    public void test4() {
        int[] A = new int[]{1, 2, 4, 6, 13};
        int[] B = new int[]{2, 4, 5, 7, 31, 42, 13};
        int r = LongestCommonSubsequence.lcs3(A, B);
        Assert.assertEquals(3, r);
    }

    @Test
    public void test5() {
        int[] A = new int[]{2, 4, 5, 7, 31, 42, 13};
        int[] B = new int[]{2, 4, 5, 7, 31, 42, 13};
        int r = LongestCommonSubsequence.lcs3(A, B);
        Assert.assertEquals(7, r);
    }

    @Test
    public void test6() {
        int[] A = new int[]{1, 2, 3, 4, 5, 6};
        int[] B = new int[]{7, 8, 9, 10, 11, 12, 13, 14, 15};
        int r = LongestCommonSubsequence.lcs3(A, B);
        Assert.assertEquals(0, r);
    }

    @Test
    public void test7() {
        System.out.println("test7");
        int[] A = new int[]{1, 2, 4, 6, 13};
        int[] B = new int[]{2, 4, 5, 7, 31, 42};
        int r = LongestCommonSubsequence.lcs4(A, B);
        Assert.assertEquals(2, r);
    }

    @Test
    public void test8() {
        System.out.println("test8");
        int[] A = new int[]{1, 2, 4, 6, 13};
        int[] B = new int[]{2, 4, 5, 7, 31, 42, 13};
        int r = LongestCommonSubsequence.lcs4(A, B);
        Assert.assertEquals(3, r);
    }

    @Test
    public void test9() {
        System.out.println("test9");
        int[] A = new int[]{2, 4, 5, 7, 31, 42, 13};
        int[] B = new int[]{2, 4, 5, 7, 31, 42, 13};
        int r = LongestCommonSubsequence.lcs4(A, B);
        Assert.assertEquals(7, r);
    }

    @Test
    public void test10() {
        System.out.println("test10");
        int[] A = new int[]{1, 2, 3, 4, 5, 6};
        int[] B = new int[]{7, 8, 9, 10, 11, 12, 13, 14, 15};
        int r = LongestCommonSubsequence.lcs4(A, B);
        Assert.assertEquals(0, r);
    }
}
