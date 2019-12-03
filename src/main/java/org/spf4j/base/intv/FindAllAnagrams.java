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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 * @author Zoltan Farkas
 */
public class FindAllAnagrams {


    public static char[] sort(String s) {
      char[] toCharArray = s.toCharArray();
      Arrays.sort(toCharArray);
      return toCharArray;
    }

    public static char[] sort(String s, int from, int to) {
      char[] toCharArray = new char[to - from];
      for (int i = from, j = 0; i < to; i++, j++) {
        toCharArray[j] = s.charAt(i);
      }
      Arrays.sort(toCharArray);
      return toCharArray;
    }


    private static void incSort(char[] array, char remove, char add) {
      int i = 0;
      char c;
      while ((c = array[i]) != remove) {
        if (c > add) {
          array[i] = add;
          add = c;
        }
        i++;
      }
      array[i] = add;
      int j = i + 1;
      int lm1 = array.length;
      while (j < lm1 && (c = array[j]) < add) {
        array[j - 1] = c;
        array[j] = add;
        j++;
      }
    }

    public static  List<Integer> findAnagrams(String s, String p) {
      char[] ps = sort(p);
      int pl = p.length();
      int l = s.length();
      if (l < pl) {
        return Collections.EMPTY_LIST;
      }
      List<Integer> result = new ArrayList<>(4);
      char[] ts = sort(s, 0, pl);
      if (Arrays.equals(ts, ps)) {
          result.add(0);
      }
      for (int  i = pl, j = 0; i < l; i++, j++) {
        incSort(ts, s.charAt(j), s.charAt(i));
        if (Arrays.equals(ts, ps)) {
          result.add(j + 1);
        }
      }
      return result;
    }

}
