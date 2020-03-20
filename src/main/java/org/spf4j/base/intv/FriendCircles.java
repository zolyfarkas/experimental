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

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * https://leetcode.com/problems/friend-circles/
 * @author Zoltan Farkas
 */
public class FriendCircles {

    public int findCircleNum(int[][] M) {
      Set<Integer> people = new HashSet<>(M.length + M.length /2);
      for (int i = 0; i < M.length; i++) {
        people.add(i);
      }
      int result = 0;
      while (!people.isEmpty()) {
        traverse(M, people);
        result++;
      }
      return result;
    }

    public void traverse(int[][] M, Set<Integer> people) {
      Iterator<Integer> it = people.iterator();
      Integer first = it.next();
      it.remove();
      ArrayDeque<Integer> traverse = new ArrayDeque<>();
      traverse.add(first);
      Integer at;
      while ((at = traverse.poll()) != null) {
        for (int i = 0; i < M.length; i++) {
          if (M[at][i] > 0) {
            if (people.remove(i)) {
              traverse.add(i);
            }
          }
        }
      }
    }



}
