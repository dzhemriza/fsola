/*
 * org.fsola
 *
 * File Name: SimpleBinaryTree.java
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
package org.fsola.misc;

/**
 * Yi has moved to Sweden and now goes to school here. The first years of
 * schooling she got in China, and the curricula do not match completely
 * in the two countries. Yi likes mathematics, but now… The teacher explains
 * the algorithm for subtraction on the board, and Yi is bored. Maybe it is
 * possible to perform the same calculations on the numbers corresponding to
 * the reversed binary representations of the numbers on the board? Yi dreams
 * away and starts constructing a program that reverses the binary
 * representation, in her mind. As soon as the lecture ends, she will go home
 * and write it on her computer.
 * <p>
 * TASK
 * <p>
 * Your task will be to write a program for reversing numbers in binary. For
 * instance, the binary representation of 13 is 1101, and reversing it gives
 * 1011, which corresponds to number 11.
 * <p>
 * Used Resources: README file resources section line 65.
 */
public class ReverseBinary {

    private static final long BASE = 2;

    public static long reverseBinary(long x) {
        long r = 0;
        while (x > 0) {
            long p = x % BASE;
            x /= BASE;

            r *= BASE;
            r += p;
        }

        return r;
    }
}
