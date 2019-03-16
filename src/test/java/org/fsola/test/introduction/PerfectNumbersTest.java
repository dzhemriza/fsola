/*
 * org.fsola
 *
 * File Name: PerfectNumbersTest.java
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

import org.fsola.introduction.PerfectNumbers;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PerfectNumbersTest {
    /**
     * From http://mathforum.org/library/drmath/view/51516.html
     */
    private final static String[] PERFECT_NUMBERS =
            {"6", "28", "496", "8128", "33550336", "8589869056", "137438691328",
                    "2305843008139952128",
                    "2658455991569831744654692615953842176",
                    "191561942608236107294793378084303638130997321548169216"};

    @Test
    public void testNumberDecompositionTest1() {

        List<String> perfectNums = PerfectNumbers.first10PerfectNumbers();

        System.out.println("Perfect numbers: ");

        for (String perfectNum : perfectNums) {
            System.out.print(perfectNum + " ");

            boolean found = false;

            for (String perfect : PERFECT_NUMBERS) {
                if (perfect.equals(perfectNum)) {
                    found = true;
                }
            }

            if (!found) {
                Assert.fail(perfectNum + " not a perfect number");
            }
        }

        System.out.println();
    }
}
