/*
 * org.fsola
 *
 * File Name: FindWordFrequencyInTextTest.java
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
package org.fsola.test.misc;

import org.fsola.datastructures.hashtable.RobinHoodHashTable;
import org.fsola.misc.FindWordFrequencyInText;
import org.junit.Assert;
import org.junit.Test;

import static org.fsola.misc.FindWordFrequencyInText.Word;

public class FindWordFrequencyInTextTest {

    @Test
    public void test1() {
        // aa => 3
        // bb => 1
        // cc => 2
        // dd => 1
        // ee => 1
        // ff => 1
        final String text = "aa aa aa bb cc dd ee ff gg cc";

        RobinHoodHashTable<String, Word> req = FindWordFrequencyInText.solve
                (text);

        Assert.assertEquals(3, req.get("aa").getFrequency());
        Assert.assertEquals(1, req.get("bb").getFrequency());
        Assert.assertEquals(2, req.get("cc").getFrequency());
        Assert.assertEquals(1, req.get("dd").getFrequency());
        Assert.assertEquals(1, req.get("ee").getFrequency());
        Assert.assertEquals(1, req.get("ff").getFrequency());
        Assert.assertNull(req.get("aaa"));
    }
}
