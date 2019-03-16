/*
 * org.fsola
 *
 * File Name: HashFunctions.java
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
package org.fsola.hash;

import java.util.Random;

/**
 * Used Resources: README file resources section line 4, line 7
 */
public class HashFunctions {

    private static Random RAND = new Random();

    /**
     * Random permutation of 0, 1, 2, .., 255
     */
    public static int[] TABLE_255 = {62, 219, 34, 252, 212, 217, 121, 108,
            68, 113, 225, 6, 46, 2, 97, 223, 67, 73, 161, 58, 83, 218, 59, 222,
            168, 213, 159, 9, 31, 44, 253, 231, 244, 138, 90, 13, 64, 127, 167,
            8, 162, 15, 232, 133, 26, 241, 19, 224, 182, 142, 187, 238, 4, 125,
            89, 229, 145, 7, 49, 170, 0, 50, 228, 216, 131, 29, 166, 163, 174,
            107, 103, 205, 239, 141, 245, 129, 14, 230, 79, 150, 23, 154, 148,
            85, 192, 220, 198, 5, 153, 126, 57, 76, 149, 110, 28, 188, 75, 81,
            202, 22, 233, 208, 190, 171, 135, 105, 227, 186, 211, 71, 237, 45,
            242, 130, 54, 1, 181, 87, 60, 82, 137, 30, 124, 39, 98, 144, 37,
            172, 101, 189, 184, 178, 33, 11, 176, 151, 136, 177, 104, 86, 111,
            74, 146, 63, 249, 122, 116, 25, 165, 20, 48, 179, 235, 42, 61, 88,
            185, 109, 194, 12, 38, 36, 95, 215, 248, 118, 56, 206, 106, 183,
            112, 69, 139, 152, 17, 128, 18, 200, 40, 27, 55, 140, 96, 91, 196,
            209, 173, 21, 156, 16, 155, 99, 53, 52, 100, 93, 77, 234, 250, 203,
            78, 134, 180, 204, 47, 255, 132, 160, 65, 226, 254, 70, 119, 117,
            115, 24, 123, 94, 210, 175, 102, 195, 201, 10, 41, 120, 193, 84, 43,
            80, 221, 3, 66, 199, 35, 92, 251, 51, 236, 157, 164, 247, 147, 143,
            240, 207, 158, 214, 32, 197, 169, 191, 246, 243, 72, 114};

    public static int additiveHash1(String s, int size) {
        int r = 0;

        for (int i = 0; i < s.length(); ++i) {
            r += (int) s.charAt(i);
        }

        return r % size;
    }

    public static int additiveHash2(String s, int size) {
        int r = s.length();

        for (int i = 0; i < s.length(); ++i) {
            r += (int) s.charAt(i);
        }

        return r % size;
    }

    public static int rotateHash(String s, int size) {
        int r = s.length();

        for (int i = 0; i < s.length(); ++i) {
            r = (r << 4) ^ (r >> 8) ^ ((int) s.charAt(i));
        }

        return r % size;
    }

    public static int oneByOneHash(String s, int size) {
        int r = 0;

        for (int i = 0; i < s.length(); ++i) {
            r += (int) s.charAt(i);
            r += r << 10;
            r ^= r >> 6;
        }

        r += r << 3;
        r ^= r >> 11;
        r += r << 15;
        return r % size;
    }

    public static int pearsonHash(String s, int size, int[] table) {
        int r = s.length();

        for (int i = 0; i < s.length(); ++i) {
            r = table[r ^ (((int) s.charAt(i)) % 256)];
        }
        return r % size;
    }

    public static int hash2(String s, int size, int[] table) {
        int r = s.length();

        for (int i = 0; i < s.length(); ++i) {
            r = (r << 8) ^ table[Math.abs((r >> 24) ^ (((int) s.charAt(i)) %
                    256))];
        }
        return r % size;
    }

    /**
     * Simple hash generation algorithm using hash code of the object.
     *
     * @param t
     * @param rounds
     * @param <T>
     * @return
     */
    public static <T> int hashBasedOnRandom(T t, int rounds, int limit) {
        RAND.setSeed(t.hashCode());

        int h = RAND.nextInt(limit);
        for (int i = 0; i < rounds; ++i) {
            h = RAND.nextInt(limit);
        }

        return h;
    }
}
