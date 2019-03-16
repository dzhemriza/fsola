/*
 * org.fsola
 *
 * File Name: Brackets.java
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
package org.fsola.misc;

/**
 * Given a string with symbols '(' and ')' check are the brackets are
 * balanced or not.
 */
public class Brackets {

    public static boolean check(String brackets) {
        int c = 0;

        for (int i = 0; i < brackets.length(); ++i) {
            if ('(' == brackets.charAt(i)) {
                ++c;
            } else if (')' == brackets.charAt(i)) {
                --c;
            }

            if (c < 0) {
                return false;
            }
        }

        return (c == 0);
    }
}
