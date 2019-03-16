/*
 * org.fsola
 *
 * File Name: TwoChoiceHashTableTest.java
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
package org.fsola.test.datastructures.hashtable;

import org.fsola.datastructures.hashtable.TwoChoiceHashTable;
import org.fsola.test.utils.Utils;
import org.junit.Assert;
import org.junit.Test;

public class TwoChoiceHashTableTest {

    private static final int MAX = 2048;

    @Test
    public void testHashTable1() {
        TwoChoiceHashTable<String, String> table =
                new TwoChoiceHashTable<>();

        for (int i = 0; i < Utils.STRINGS_KV.length; ++i) {
            table.put(Utils.STRINGS_KV[i][0], Utils.STRINGS_KV[i][1]);
        }

        for (int i = 0; i < Utils.STRINGS_KV.length; ++i) {
            Assert.assertEquals(Utils.STRINGS_KV[i][1],
                    table.get(Utils.STRINGS_KV[i][0]));
        }
    }

    @Test
    public void testHashTable2() {
        TwoChoiceHashTable<String, String> table =
                new TwoChoiceHashTable<>();

        for (int i = 0; i < Utils.STRINGS_KV.length; ++i) {
            table.put(Utils.STRINGS_KV[i][0], Utils.STRINGS_KV[i][1]);
        }

        table.put(Utils.STRINGS_KV[0][0], "a");
        Assert.assertEquals("a", table.get(Utils.STRINGS_KV[0][0]));
    }

    @Test
    public void testHashTable3() {
        TwoChoiceHashTable<String, String> table =
                new TwoChoiceHashTable<>();

        for (int i = 0; i < Utils.STRINGS_KV.length; ++i) {
            table.put(Utils.STRINGS_KV[i][0], Utils.STRINGS_KV[i][1]);
        }

        Assert.assertEquals(Utils.STRINGS_KV[0][1],
                table.delete(Utils.STRINGS_KV[0][0]));
        Assert.assertNull(table.get(Utils.STRINGS_KV[0][0]));
    }

    @Test
    public void testHashTable4() {
        String[][] kv = Utils.generateRandomKV(MAX);

        TwoChoiceHashTable<String, String> table =
                new TwoChoiceHashTable<>();

        for (int i = 0; i < kv.length; ++i) {
            table.put(kv[i][0], kv[i][1]);
        }

        for (int i = 0; i < kv.length; ++i) {
            Assert.assertEquals(kv[i][1], table.get(kv[i][0]));
        }
    }
}
