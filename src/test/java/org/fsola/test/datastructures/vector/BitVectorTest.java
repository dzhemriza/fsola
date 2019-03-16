/*
 * org.fsola
 *
 * File Name: BitVectorTest.java
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
package org.fsola.test.datastructures.vector;

import org.fsola.datastructures.vector.BitVector;
import org.junit.Assert;
import org.junit.Test;

public class BitVectorTest {
    private static int MAX = 2048;


    @Test
    public void test1() {
        BitVector vector = new BitVector();

        for (int i = 0; i < MAX; ++i) {
            if (i % 2 == 0) {
                vector.setBit(i, true);
            } else {
                vector.setBit(i, false);
            }
        }

        for (int i = 0; i < MAX; ++i) {
            if (i % 2 == 0) {
                Assert.assertTrue(vector.getBit(i));
            } else {
                Assert.assertFalse(vector.getBit(i));
            }
        }
    }

    @Test
    public void test2() {
        BitVector vector = new BitVector();

        for (int i = 0; i < MAX; ++i) {
            if (i % 2 == 0) {
                vector.setBit(i, false);
            } else {
                vector.setBit(i, true);
            }
        }

        for (int i = 0; i < MAX; ++i) {
            if (i % 2 == 0) {
                Assert.assertFalse(vector.getBit(i));
            } else {
                Assert.assertTrue(vector.getBit(i));
            }
        }
    }

    @Test
    public void test3() {
        BitVector vector = new BitVector();

        for (int i = 0; i < MAX; ++i) {
            vector.setBit(i, true);
        }

        for (int i = 0; i < MAX; ++i) {
            Assert.assertTrue(vector.getBit(i));
        }
    }

    @Test
    public void test4() {
        BitVector vector = new BitVector();

        for (int i = 0; i < MAX; ++i) {
            vector.setBit(i, false);
        }

        for (int i = 0; i < MAX; ++i) {
            Assert.assertFalse(vector.getBit(i));
        }
    }
}
