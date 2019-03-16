/*
 * org.fsola
 *
 * File Name: SimpleArrayStackTest.java
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

import org.fsola.datastructures.stack.SimpleArrayStack;
import org.junit.Assert;
import org.junit.Test;

public class SimpleArrayStackTest {

    @Test
    public void testSimpleArrayStack1() {
        SimpleArrayStack<Integer> stack = new SimpleArrayStack<>();

        Assert.assertEquals(true, stack.isEmpty());

        for (int i = 1; i <= 100; ++i) {
            stack.put(i);
        }
        Assert.assertEquals(false, stack.isEmpty());

        try {
            stack.put(101);
            Assert.fail("Stack must be full");
        } catch (IllegalStateException e) {
            // Stack is full
        }

        for (int i = 100; i >= 1; --i) {
            Assert.assertEquals(Integer.valueOf(i), stack.pop());
        }
        Assert.assertEquals(true, stack.isEmpty());

        try {
            stack.pop();
            Assert.fail("Stack must be empty");
        } catch (IllegalStateException e) {
            // Stack is empty
        }
    }
}
