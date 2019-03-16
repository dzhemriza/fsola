/*
 * org.fsola
 *
 * File Name: ZipfSongTest.java
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

import org.fsola.misc.ZipfSong;
import org.junit.Assert;
import org.junit.Test;

public class ZipfSongTest {

    @Test
    public void test1() {
        long[] f = new long[]{
                30, 30, 15, 25
        };
        String[] songs = new String[]{
                "one", "two", "three", "four"
        };

        String[] r = ZipfSong.solve(4, 2, f, songs);

        String[] e = new String[]{
                "four", "two"
        };

        Assert.assertArrayEquals(e, r);
    }

    @Test
    public void test2() {
        long[] f = new long[]{
                197812, 78906, 189518, 39453, 210492, 26302, 22544, 19727,
                17535, 18782, 198189, 13151, 12139, 11272, 10521
        };
        String[] songs = new String[]{
                "re_hash", "5_4", "tomorrow_comes_today", "new_genious", "clint_eastwood",
                "man_research", "punk", "sound_check", "double_bass", "rock_the_house",
                "19_2000", "latin_simone", "starshine", "slow_country", "m1_a1"
        };

        String[] r = ZipfSong.solve(15, 3, f, songs);

        String[] e = new String[]{
                "19_2000",
                "clint_eastwood",
                "tomorrow_comes_today"
        };

        Assert.assertArrayEquals(e, r);
    }
}
