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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/alien-dictionary/
 * @author Zoltan Farkas
 */
public class AlienDict {


   public static class OderGraph {
     Set<Character> chars = new HashSet<>();
     Map<Character, Set<Character>>  outgoing = new HashMap<>();
     Map<Character, Set<Character>>  incoming = new HashMap<>();

     public void addOrder(char c1, char c2) {
       chars.add(c1);
       chars.add(c2);
       Set<Character> ogs = outgoing.get(c1);
       if (ogs == null) {
         ogs = new HashSet<>(4);
         outgoing.put(c1, ogs);
       }
       ogs.add(c2);
       Set<Character> igs = incoming.get(c2);
       if (igs == null) {
         igs = new HashSet<>(4);
         incoming.put(c2, igs);
       }
       igs.add(c1);
     }


     public void addChars(String s) {
       for (int i = 0,  l = s.length(); i < l; i++) {
          chars.add(s.charAt(i));
       }
     }

     public void buildOrder(StringBuilder addTo) {
       Map<Character, Integer> order = new HashMap<>();
       for (Character c : chars) {
         if (!incoming.containsKey(c)) {
           traverseOrder(c, order);
         }
       }
       List<Character>[] charOrder = new List[order.size()];
       for (Map.Entry<Character, Integer> entry : order.entrySet()) {
         Integer idx = entry.getValue();
         List<Character> lc = charOrder[idx];
         if (lc == null) {
           lc = new ArrayList<>(4);
           charOrder[idx] = lc;
         }
         lc.add(entry.getKey());
       }
       for (List<Character> lc : charOrder) {
         if (lc != null) {
          for (Character c : lc) {
            addTo.append(c);
          }
         }
       }
       if (addTo.length() != this.chars.size()) {
         throw new IllegalStateException("Cycle detected");
       }
     }

     public void traverseOrder(char c, Map<Character, Integer> order) {
       Set<Character> path = new HashSet<>();
       traverseOrder(c, path, order, 0);
     }


     public void traverseOrder(char c, Set<Character> path, Map<Character, Integer> order, int depth) {
       if (path.contains(c)) {
         throw new IllegalStateException("cycle: " + path);
       }
       path.add(c);
       Integer oi = order.get(c);
       if (oi == null) {
         order.put(c, depth);
       } else {
         if (oi >= depth)  {
           return;
         }
         order.put(c, depth);
       }
       Set<Character> next = outgoing.get(c);
       if (next != null) {
         for (Character nc : next) {
           traverseOrder(nc, path, order, depth + 1);
         }
       }
       path.remove(c);
     }

   }


   static char[] getOrder(String s1, String s2) {
     for (int i = 0, l = Math.min(s1.length(), s2.length()); i < l; i++) {
       char c1 = s1.charAt(i);
       char c2 = s2.charAt(i);
       if (c1 != c2) {
         return new char[] {c1, c2};
       }
     }
     return null;
   }

   public static String alienOrder(String[] words) {
     if (words.length == 0) {
       return "";
     }
     if (words.length == 1) {
       return words[0];
     }
     OderGraph og = new OderGraph();
     for (int i = 0, l = words.length - 1; i < l; i++) {
       String w1 = words[i];
       String w2 = words[i + 1];
       char[] order = getOrder(w1, w2);
       if (order != null) {
         og.addOrder(order[0], order[1]);
       }
       og.addChars(w1);
       og.addChars(w2);
     }
     StringBuilder sb = new StringBuilder(og.chars.size());
     try {
      og.buildOrder(sb);
      return sb.toString();
     } catch (IllegalStateException ex) {
       return "";
     }

   }
}
