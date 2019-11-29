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
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/task-scheduler/
 *
 * @author Zoltan Farkas
 */
public class TaskScheduler {
/*
  2
  AAAAAA
  BBBBBB
  CCCCCC
  DDDDDD
  EEEEEE
  F
  G

  ABCADEAFGA__A__A
  */
  public static int leastInterval(char[] tasks, int n) {
    Map<Character, Integer> classify = new HashMap<>(32);
    for (char c : tasks) {
      classify.compute(c, (k, v) -> v == null ? 1 : v + 1);
    }
    int nrOps = 0;
    PriorityQueue<Map.Entry<Character, Integer>> pq =
            new PriorityQueue<>((a, b) -> Integer.compare(b.getValue(), a.getValue()));
    pq.addAll(classify.entrySet());
    List<Map.Entry<Character, Integer>> toAdd = new ArrayList<>(n + 1);
    while (true) {
      Map.Entry<Character, Integer> next;
      int i = 0;
      while (i <= n && (next = pq.poll())  != null) {
        Integer value = next.getValue();
        if (value  > 1) {
          next.setValue(value - 1);
          toAdd.add(next);
        }
        nrOps++;
        i++;
      }
      if (toAdd.isEmpty() && pq.isEmpty()) {
        break;
      }
      pq.addAll(toAdd);
      toAdd.clear();
      nrOps += n - i  + 1;
    }
    return nrOps;
  }

}
