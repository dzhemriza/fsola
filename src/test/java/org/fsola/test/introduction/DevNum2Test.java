/*
 * org.fsola
 *
 * File Name: DevNum2Test.java
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

import org.fsola.introduction.DevNum2;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DevNum2Test {

    @Test
    public void testDevNum1() {
        int n = 50;
        List<List<Integer>> r = DevNum2.solve(n);

        for (List<Integer> s : r) {
            System.out.print("DevNum2 " + n + ":");
            for (Integer i : s) {
                System.out.print(" " + i);
            }
            System.out.println();
        }

        for (List<Integer> s : r) {
            int p = 1;
            for (Integer i : s) {
                p *= i;
            }
            Assert.assertEquals(p, n);
        }
    }
}
