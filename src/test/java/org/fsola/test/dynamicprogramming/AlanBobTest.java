/*
 * org.fsola
 *
 * File Name: AlanBobTest.java
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

import org.fsola.dynamicprogramming.AlanBob;
import org.junit.Assert;
import org.junit.Test;

public class AlanBobTest {

    @Test
    public void test1() {
        int[] presents = new int[]{3, 2, 3, 2, 2, 77, 89, 23, 90, 11};

        AlanBob.Result r = AlanBob.dp1(presents);
        Assert.assertNotNull(r);

        System.out.println("Alan get presents with sum: " + r.alanSum);
        System.out.print("Items:");
        for (int i = 0; i < r.presents.length; ++i) {
            System.out.print(" " + r.presents[i]);
        }
        System.out.println();
    }

    @Test
    public void test2() {
        int[] presents = new int[]{1, 2, 3, 5};

        AlanBob.Result r = AlanBob.dp1(presents);
        Assert.assertNotNull(r);

        System.out.println("Alan get presents with sum: " + r.alanSum);
        System.out.print("Items:");
        for (int i = 0; i < r.presents.length; ++i) {
            System.out.print(" " + r.presents[i]);
        }
        System.out.println();
    }

    @Test
    public void test3() {
        int[] presents = new int[]{1, 2, 3, 5};

        AlanBob.Result r = AlanBob.dp2(presents);
        Assert.assertNotNull(r);

        System.out.println("Alan get presents with sum: " + r.alanSum);
        System.out.print("Items:");
        for (int i = 0; i < r.presents.length; ++i) {
            System.out.print(" " + r.presents[i]);
        }
        System.out.println();
    }

    @Test
    public void test4() {
        int[] presents = new int[]{3, 2, 3, 2, 2, 77, 89, 23, 90, 11};

        AlanBob.Result r = AlanBob.dp2(presents);
        Assert.assertNotNull(r);

        System.out.println("Alan get presents with sum: " + r.alanSum);
        System.out.print("Items:");
        for (int i = 0; i < r.presents.length; ++i) {
            System.out.print(" " + r.presents[i]);
        }
        System.out.println();
    }
}
