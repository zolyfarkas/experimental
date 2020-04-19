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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-consecutive-sequence/
 *
 * @author Zoltan Farkas
 */
public class LongestConsecutiveSequence {

  public static int getSequence(Set<Integer> numbers) {
    int seq = 1;
    Iterator<Integer> it = numbers.iterator();
    int at = it.next();
    it.remove();
    int i = at;
    while (numbers.contains(++i)) {
      numbers.remove(i);
      seq++;
    }
    i = at;
    while (numbers.contains(--i)) {
      numbers.remove(i);
      seq++;
    }
    return seq;
  }

  public static int longestConsecutive(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    Set<Integer> numbers = new HashSet<>(nums.length + nums.length / 3);
    for (int nr : nums) {
      numbers.add(nr);
    }
    int max = 1;
    while (!numbers.isEmpty()) {
      int sequence = getSequence(numbers);
      if (sequence > max) {
        max = sequence;
      }
    }
    return max;
  }

}
