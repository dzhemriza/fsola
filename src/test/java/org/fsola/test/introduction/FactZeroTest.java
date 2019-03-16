/*
 * org.fsola
 *
 * File Name: FactZeroTest.java
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

import org.fsola.introduction.FactZero;
import org.junit.Assert;
import org.junit.Test;

public class FactZeroTest {

    @Test
    public void testFactZeroTest1() {
        Assert.assertEquals(2, FactZero.calc(10));
        Assert.assertEquals(4, FactZero.calc(20));
    }
}
