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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Zoltan Farkas
 */
public class MergeIntervals {
  /**
   * https://leetcode.com/problems/merge-intervals/
   * @param intervals
   * @return
   */
  public int[][] merge(int[][] intervals) {
    if (intervals.length == 0) {
      return intervals;
    }
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
    List<int[]> result = new ArrayList<>(intervals.length);
    int[] intv = intervals[0];
    for (int i = 1; i < intervals.length; i++) {
      int[] other = intervals[i];
      int i1 = intv[1];
      if (i1 >= other[0]) {
        int o1 = other[1];
        if (o1 > i1) {
          intv[1] = o1;
        }
      } else {
        result.add(intv);
        intv = other;
      }
    }
    result.add(intv);
    return result.toArray(new int[result.size()][]);
  }
}
