/*
 * org.fsola
 *
 * File Name: ReverseBinaryTest.java
 *
 * Copyright 2015 Dzhem Riza
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

import org.fsola.misc.ReverseBinary;
import org.junit.Assert;
import org.junit.Test;

public class ReverseBinaryTest {

    @Test
    public void test1() {
        Assert.assertEquals(11, ReverseBinary.reverseBinary(13));
        Assert.assertEquals(61, ReverseBinary.reverseBinary(47));
    }
}
