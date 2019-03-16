/*
 * org.fsola
 *
 * File Name: HashFunctionsTest.java
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
package org.fsola.test.hash;

import org.fsola.hash.HashFunctions;
import org.junit.Test;

public class HashFunctionsTest {

    private static int PRIME = 997;

    private final static String[] STRINGS = new
            String[]{"Aliens", "Blade Runner", "Pacific Rim",
            "The Matrix", "The Day the Earth Stood Still", "Avatar",
            "Star Trek Into Darkness"};

    @Test
    public void testHash1() {
        for (String s : STRINGS) {
            System.out
                    .println("Additive hash 1: " + HashFunctions.additiveHash1
                            (s, PRIME));
        }
    }

    @Test
    public void testHash2() {
        for (String s : STRINGS) {
            System.out
                    .println("Additive hash 2: " + HashFunctions.additiveHash2
                            (s, PRIME));
        }
    }

    @Test
    public void testHash3() {
        for (String s : STRINGS) {
            System.out
                    .println("Rotate hash: " + HashFunctions.rotateHash(s,
                            PRIME));
        }
    }

    @Test
    public void testHash4() {
        for (String s : STRINGS) {
            System.out
                    .println("One by one hash: " + HashFunctions.oneByOneHash(s,
                            PRIME));
        }
    }

    @Test
    public void testHash5() {
        for (String s : STRINGS) {
            System.out
                    .println("Pearson hash: " + HashFunctions.pearsonHash(s,
                            PRIME, HashFunctions.TABLE_255));
        }
    }

    @Test
    public void testHash6() {
        for (String s : STRINGS) {
            System.out
                    .println("hash2: " + HashFunctions.hash2(s,
                            PRIME, HashFunctions.TABLE_255));
        }
    }

    @Test
    public void testHash7() {
        for (String s : STRINGS) {
            System.out
                    .println("hashBasedOnRandom: " + HashFunctions
                            .hashBasedOnRandom(s, 10, 1024));
        }
    }
}
