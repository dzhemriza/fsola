/*
 * org.fsola
 *
 * File Name: DevNum2.java
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

import java.util.ArrayList;
import java.util.List;

/**
 * Used Resources: README file resources section line 1
 */
public class DevNum2 {

    private final static int MAXN = 100;
    private static int[] m = new int[MAXN + 1];
    private static List<List<Integer>> result;

    private static void rememberResult(int pos) {
        ArrayList<Integer> s = new ArrayList();
        for (int i = 1; i < pos; ++i) {
            s.add(m[i]);
        }
        result.add(s);
    }

    private static void devNum(int n, int pos) {
        if (n == 1) {
            rememberResult(pos);
        } else {
            for (int k = n; k > 1; --k) {
                m[pos] = k;
                if ((m[pos] <= m[pos - 1]) && ((n % k) == 0)) {
                    devNum(n / k, pos + 1);
                }
            }
        }
    }

    /**
     * Finds all combinations of multiply of a number to be equals to n.
     *
     * @param n
     * @return
     */
    public static List<List<Integer>> solve(int n) {
        result = new ArrayList<>();
        m[0] = n + 1;

        devNum(n, 1);

        return result;
    }
}
