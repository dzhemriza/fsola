/*
 * org.fsola
 *
 * File Name: LinkedListQueueTest.java
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

import org.fsola.datastructures.queue.LinkedListQueue;
import org.junit.Assert;
import org.junit.Test;

public class LinkedListQueueTest {

    @Test
    public void test1() {
        LinkedListQueue<Integer> q = new LinkedListQueue<>();

        Assert.assertTrue(q.isEmpty());

        for (int i = 1; i <= 10; ++i) {
            q.put(i);
            Assert.assertFalse(q.isEmpty());
        }

        Assert.assertEquals(Integer.valueOf(1), q.peek());

        int i = 1;
        while (q.isEmpty()) {
            Assert.assertEquals(Integer.valueOf(i++), q.get());
        }
    }
}