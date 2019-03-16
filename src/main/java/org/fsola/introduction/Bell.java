/*
 * org.fsola
 *
 * File Name: Bell.java
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
package org.fsola.introduction;

/**
 * Used Resources: README file resources section line 1
 */
public class Bell {

    private static int MAXN = 100;
    private static int[] M = new int[MAXN];

    /**
     * Calculates Stirling N-th number
     *
     * @param n
     */
    private static void stirling(int n) {
        if (n == 0) {
            M[0] = 1;
        } else {
            M[0] = 0;
        }

        for (int i = 1; i <= n; ++i) {
            M[i] = 1;

            for (int j = i - 1; j >= 1; --j) {
                M[j] = j * M[j] + M[j - 1];
            }
        }
    }

    /**
     * Calculates Bell's N-th number
     *
     * @param n
     * @return
     */
    public static int bell(int n) {
        stirling(n);

        int result = 0;
        for (int i = 0; i <= n; ++i) {
            result += M[i];
        }

        return result;
    }
}
