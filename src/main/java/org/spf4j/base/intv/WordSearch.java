/*
 * Copyright 2019 SPF4J.
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
package org.spf4j.base.intv;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Zoltan Farkas
 */
public class WordSearch {

     public static boolean existsAt(int i, int j, char[][] board, String word, int wat, Set<Long> added) {
        char[] bi = board[i];
        if (bi[j] == word.charAt(wat)) {
            long val = (((long) i) << 32)  + j;
            if (!added.add(val)) {
                return false;
            }
            int nat = wat + 1;
            int wl = word.length();
            if (nat >= wl) {
                return true;
            }
            if (i > 0 && existsAt(i - 1, j, board, word, nat, added)) {
                return true;
            }
            if (j > 0 && existsAt(i, j - 1, board, word, nat, added)) {
                return true;
            }
            int ip1 = i + 1;
            if (ip1 < board.length && existsAt(ip1, j, board, word, nat, added)) {
                return true;
            }
            int jp1  = j + 1;
            if (jp1 < bi.length && existsAt(i, jp1, board, word, nat, added)) {
                return true;
            }
            added.remove(val);
            return false;
        } else {
            return false;
        }
    }

    public static boolean exist(char[][] board, String word) {
        int wl = word.length();
        if (wl == 0) {
            return true;
        }
        Set<Long> set = new HashSet<>((int) (1.33 * wl));
        for (int i = 0; i < board.length; i++) {
            char[] line = board[i];
            for (int j = 0; j < line.length; j++) {
                if (existsAt(i, j, board, word, 0, set)) {
                    return true;
                }
                set.clear();
            }
        }
        return false;
    }

}
