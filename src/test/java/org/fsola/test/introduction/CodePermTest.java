/*
 * org.fsola
 *
 * File Name: CodePermTest.java
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

import org.fsola.introduction.CodePerm;
import org.junit.Assert;
import org.junit.Test;

public class CodePermTest {

    @Test
    public void testCodePerm1() {
        int[] perm = new int[]{5, 3, 6, 4, 2, 1};
        int code = CodePerm.codePerm(perm);
        System.out.println("Code of permutation: " + code);

        Assert.assertEquals(551, code);
    }

    @Test
    public void testCodePerm2() {
        int[] expectedPerm = new int[]{5, 3, 6, 4, 2, 1};
        int[] perm = CodePerm.decodePerm(551, expectedPerm.length);

        System.out.print("Restored permutation:");
        for (int i : perm) {
            System.out.print(" " + i);
        }
        System.out.println();

        Assert.assertArrayEquals(expectedPerm, perm);
    }
}
