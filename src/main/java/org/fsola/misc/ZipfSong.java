/*
 * org.fsola
 *
 * File Name: ZipfSong.java
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

import java.util.Arrays;

/**
 * Your slightly pointy-bearded boss has assigned you to write software to
 * find the best songs from different music albums. And the software should
 * be finished in an hour. But don’t panic, you don’t have to solve the
 * problem of writing an AI with good taste. At your disposal is the impeccable
 * taste of a vast horde of long-tailed monkeys. Well, at least almost. The
 * monkeys are not very communicative (or rather, you’re not sure which song
 * “Ook!” is supposed to refer to) so you can’t ask them which songs are the
 * best. What you can do however is to look at which songs the monkeys have
 * listened to and use this information to deduce which songs are the best.
 * <p>
 * At first, you figure that the most listened to songs must be the best songs.
 * However, you quickly realize that this approach is flawed. Even if all songs
 * of the album are equally good, the early songs are more likely to be listened
 * to more often than the later ones, because monkeys will tend to start listening
 * to the first song, listen for a few songs and then, when their fickle ears
 * start craving something else, stop listening. Instead, if all songs are equal,
 * you expect that their play frequencies should follow Zipf’s Law.
 * <p>
 * Zipf’s Law is an empirical law originally formulated about word frequencies in
 * natural languages, but it has been observed that many natural phenomena, such
 * as population sizes and incomes, approximately follow the same law. It predicts
 * that the relative frequency of thei’th most common object (in this case, a song)
 * should be proportional to 1/i.
 * <p>
 * To illustrate this in our setting, suppose we have an album where all songs are
 * equally good. Then by Zipf’s Law, you expect that the first song is listened to
 * twice as often as the second song, and more generally that the first song is
 * listened to i times as often as the i’th song. When some songs are better than
 * others, those will be listened to more often than predicted by Zipf’s Law, and
 * those are the songs your program should select as the good songs. Specifically,
 * suppose that song i has been played fi times but that Zipf’s Law predicts that
 * it would have been played zi times. Then you define the quality of song i to be
 * qi = fi / zi. Your software should select the songs with the highest values of qi.
 * <p>
 * Used Resources: README file resources section line 65.
 */
public class ZipfSong {

    private static class Holder implements Comparable<Holder> {
        private long q;
        private String song;

        public Holder(long q, String song) {
            this.q = q;
            this.song = song;
        }

        private static int comp(Holder lhs, Holder rhs) {
            if (lhs.q < rhs.q)
                return -1;
            else if (lhs.q > rhs.q)
                return 1;
            else
                return 0;
        }

        @Override
        public int compareTo(Holder other) {
            return -1 * comp(this, other);
        }
    }

    public static String[] solve(int N, int M, long[] f, String[] songs) {
        Holder[] h = new Holder[N];

        for (int i = 0; i < N; ++i) {
            h[i] = new Holder((i + 1) * f[i], songs[i]);
        }

        Arrays.sort(h); // stable sort (replace with mergeSort)

        String[] res = new String[M];

        for (int i = 0; i < M; ++i) {
            res[i] = h[i].song;
        }

        return res;
    }
}
