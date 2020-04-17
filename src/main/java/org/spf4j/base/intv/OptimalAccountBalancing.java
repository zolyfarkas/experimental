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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/optimal-account-balancing/
 * this is still wip.
 * @author Zoltan Farkas
 */
public class OptimalAccountBalancing {

  public static int minTransfers(int[][] transactions) {
    Map<Integer, Integer> positions = new HashMap<>();
    for (int[] transaction : transactions) {
      positions.compute(transaction[0], (k, v) -> v == null ? -transaction[2] : v - transaction[2]);
      positions.compute(transaction[1], (k, v) -> v == null ? transaction[2] : v + transaction[2]);
    }
    int size = positions.size();
    // position -> nr people
    Map<Integer, Integer> negatives = new HashMap<>(size/2);
    SortedMap<Integer, Integer> positives = new TreeMap<>();
    for (Integer posVal : positions.values()) {
      if (posVal > 0) {
        positives.compute(posVal, (k, v) -> v == null ? 1 : v + 1);
      } else if (posVal < 0) {
        negatives.compute(-posVal, (k, v) -> v == null ? 1 : v + 1);
      }
    }
    int nrTransfers = 0;
    Iterator<Map.Entry<Integer, Integer>> it = negatives.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry<Integer, Integer> next = it.next();
      Integer pos = next.getKey();
      int curVal = next.getValue();
      Integer opposites = positives.get(pos);
      if (opposites == null) {
        positives.headMap(pos);
        continue;
      }
      int matched = Math.min(curVal, opposites);
      nrTransfers += matched;
      curVal -= matched;
      opposites -= matched;
      if (curVal == 0) {
        it.remove();
      } else {
        next.setValue(curVal);
      }
      if (opposites == 0) {
        positives.remove(pos);
      } else {
        positives.put(pos, opposites);
      }
    }
    if (positives.isEmpty()) {
      if (negatives.isEmpty()) {
        return nrTransfers;
      } else {
        throw new IllegalStateException();
      }
    }
    int nrNegatives = count(negatives);
    int nrPositives = count(positives);
    //Todo: continue
    return -1;
  }


   public static int count(Map<Integer, Integer> positions) {
    int result = 0;
    for (Integer v : positions.values()) {
      result += v;
    }
    return result;
  }


}
