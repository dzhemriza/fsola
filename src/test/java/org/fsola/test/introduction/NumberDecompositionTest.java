/*
 * org.fsola
 *
 * File Name: NumberDecompositionTest.java
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

import org.fsola.introduction.NumberDecomposition;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class NumberDecompositionTest {

    @Test
    public void testNumberDecompositionTest1() {
        List<Integer> decomposition = NumberDecomposition.decomposition(10);
        Assert.assertEquals(2, decomposition.size());
        Assert.assertEquals(new Integer(2), decomposition.get(0));
        Assert.assertEquals(new Integer(5), decomposition.get(1));
    }
}
