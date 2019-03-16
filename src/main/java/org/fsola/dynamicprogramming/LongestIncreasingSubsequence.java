/*
 * org.fsola
 *
 * File Name: LongestIncreasingSubsequence.java
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
package org.fsola.dynamicprogramming;

/**
 * Find longest increasing subsequence of numbers in integer array.
 * <p/>
 * Used Resources: README file resources section line 21, line 49.
 */
public class LongestIncreasingSubsequence {

    //
    // Sequence: 10, 22, 9, 33, 21, 50, 41, 60
    //
    //                              10
    //            /                 |            \           \     \     \
    //           22                 33           21          50    41    60
    //     /         \ \  \        / | \        / | \        |     |
    //    33         50 41 60     50 41 60     50 41 60      60    60
    //  / |  \       |  |         |  |         |  |
    // 50 41 60      60 60        60 60        60 60
    // |  |
    // 60 60
    //

    public static int lis(int[] A) {
        int N = A.length;
        int S[] = new int[A.length];

        for (int i = 0; i < N; ++i) {
            S[i] = 0;
        }

        for (int i = 0; i < N; ++i) {
            for (int j = i + 1; j < N; ++j) {
                if (A[i] < A[j]) {
                    S[j] = Math.max(S[j], S[i] + 1);
                }
            }
        }

        // Debug
        System.out.print("S:");
        for (int i = 0; i < N; ++i) {
            System.out.print(" " + S[i]);
        }
        System.out.println();

        int max = 0;
        for (int i = 0; i < N; ++i) {
            if (S[max] < S[i]) {
                max = i;
            }
        }

        return S[max];
    }
}
