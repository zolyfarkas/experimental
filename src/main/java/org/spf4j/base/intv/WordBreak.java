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

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author Zoltan Farkas
 */
public class WordBreak {


     public static boolean wordBreak(String s, List<String> wordDict) {
      SortedSet<String> words = new TreeSet<>(wordDict);
      int sl = s.length();
      LinkedHashSet<Integer> matchedTo = new LinkedHashSet<>();
      matchedTo.add(0);
      Integer at;
      while (true) {
        Iterator<Integer> qit = matchedTo.iterator();
        if (!qit.hasNext()) {
          break;
        }
        at = qit.next();
        qit.remove();
        if (at == sl) {
          return true;
        }
        SortedSet<String> testStrs = words.tailSet(s.substring(at, at + 1));
        Iterator<String> it = testStrs.iterator();
        while (it.hasNext()) {
          String word = it.next();
          int matchNr = matchAt(s, at, word);
          if (matchNr == 0) {
            break;
          }
          if (matchNr == word.length()) {
            matchedTo.add(at + matchNr);
          }
        }
      }
      return false;
    }

     public static int matchAt(String s, int at,  String word) {
       int l = word.length();
       int sl = s.length();
       int i = 0;
       for (; i < l && at < sl; i++, at++) {
         if (s.charAt(at) != word.charAt(i)) {
           return i;
         }
       }
       return i;
     }


}
