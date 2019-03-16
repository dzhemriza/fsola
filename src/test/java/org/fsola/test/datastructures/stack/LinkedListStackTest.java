/*
 * org.fsola
 *
 * File Name: LinkedListStackTest.java
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
package org.fsola.test.datastructures.stack;

import org.fsola.datastructures.stack.LinkedListStack;
import org.junit.Assert;
import org.junit.Test;

public class LinkedListStackTest {

    @Test
    public void test1() {
        LinkedListStack<Integer> s = new LinkedListStack<>();

        Assert.assertTrue(s.isEmpty());

        for (int i = 1; i <= 10; ++i) {
            s.push(i);
            Assert.assertFalse(s.isEmpty());
        }

        Assert.assertEquals(Integer.valueOf(10), s.top());

        int i = 10;
        while (s.isEmpty()) {
            Assert.assertEquals(Integer.valueOf(i--), s.pop());
        }
    }
}
