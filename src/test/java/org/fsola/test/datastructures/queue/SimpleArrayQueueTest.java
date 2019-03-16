/*
 * org.fsola
 *
 * File Name: SimpleArrayQueueTest.java
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
package org.fsola.test.datastructures.queue;

import org.fsola.datastructures.queue.SimpleArrayQueue;
import org.junit.Assert;
import org.junit.Test;

public class SimpleArrayQueueTest {

    @Test
    public void testSimpleArrayStack1() {
        SimpleArrayQueue<Integer> queue = new SimpleArrayQueue<>();

        Assert.assertEquals(true, queue.isEmpty());

        for (int i = 1; i <= 100; ++i) {
            queue.put(i);
        }
        Assert.assertEquals(false, queue.isEmpty());

        try {
            queue.put(101);
            Assert.fail("Queue must be full");
        } catch (IllegalStateException e) {
            // Queue is full
        }

        for (int i = 1; i <= 100; ++i) {
            Assert.assertEquals(Integer.valueOf(i), queue.get());
        }
        Assert.assertEquals(true, queue.isEmpty());

        try {
            queue.get();
            Assert.fail("Queue must be empty");
        } catch (IllegalStateException e) {
            // Queue is empty
        }
    }
}
