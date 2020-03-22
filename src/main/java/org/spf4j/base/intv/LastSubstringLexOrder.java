/*
 * Copyright 2020 SPF4J.
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


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/last-substring-in-lexicographical-order/
 *
 * @author Zoltan Farkas
 */
public class LastSubstringLexOrder {

   public static String lastSubstring(String s) {
    if (s.isEmpty()) {
      return "";
    }
    List<Integer> maxims = new LinkedList<>();
    char maxChar = s.charAt(0);
    char prev = maxChar;
    maxims.add(0);
    int l = s.length();
    for (int i = 1; i < l; i++) {
      char curr = s.charAt(i);
      if (prev != curr) {
        if (curr > maxChar) {
          maxims.clear();
          maxims.add(i);
          maxChar = curr;
        } else if (curr == maxChar) {
          maxims.add(i);
        }
      }
      prev = curr;
    }
    Iterator<Integer> iterator = maxims.iterator();
    int maxIdx = iterator.next();
    while (iterator.hasNext()) {
      int idx = iterator.next();
      if (compareSpecial(l, s, maxIdx, idx) < 0) {
        maxIdx = idx;
      }
    }
    return s.substring(maxIdx);
  }

  public static int compareSpecial(int l, String str, int idx1, int idx2) {
    int result = 0;
    do {
      char c1 = str.charAt(idx1);
      char c2 = str.charAt(idx2);
      result = c1 - c2;
      if (result != 0) {
        return result;
      }
      idx1++;
      idx2++;
      if (idx2 >= l) {
        return 1;
      }
    } while (true);
  }


}
