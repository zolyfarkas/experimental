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

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *
 * @author Zoltan Farkas
 */
public class FreeRooms {


  public static int minMeetingRooms(int[][] intervals) {
    if (intervals.length == 0) {
      return 0;
    }
    Arrays.sort(intervals, (a, b) ->  Integer.compare(a[0], b[0]));
    PriorityQueue<Integer> rooms = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
    rooms.add(intervals[0][1]);
    for (int i = 1; i < intervals.length; i++) {
      int [] intv = intervals[i];
      Integer peek = rooms.peek();
      int soi = intv[0];
      int eoi = intv[1];
      if (peek <= soi) {
        rooms.remove();
        rooms.add(eoi);
      } else {
        rooms.add(eoi);
      }
    }
    return rooms.size();
  }

}
