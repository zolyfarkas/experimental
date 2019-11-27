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
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author Zoltan Farkas
 */
public class FreeRoomsExperimental {

  public static class IntRange implements Comparable<IntRange> {

    private int from;
    private int to;

    public IntRange(int from, int to) {
      this.from = from;
      this.to = to;
    }

    @Override
    public int compareTo(IntRange o) {
      int i = Integer.compare(this.from, o.from);
      if (i == 0) {
        return -Integer.compare(this.to, o.to);
      }
      return i;
    }

    @Override
    public String toString() {
      return "IntRange{" + "from=" + from + ", to=" + to + '}';
    }

  }

  public static int freeSpace(SortedSet<IntRange> room) {
    int sum = 0;
    for (IntRange rr :room) {
      sum += rr.to - rr.from;
    }
    return sum;
  }

  public static void reserve(List<SortedSet<IntRange>> rooms, int[] interval) {
    IntRange elem = new IntRange(interval[0], interval[1]);
    for (SortedSet<IntRange> room : rooms) {
      int initial = freeSpace(room);
      SortedSet<IntRange> headSet = room.headSet(elem);
      if (!headSet.isEmpty()) {
        IntRange free1 = headSet.last();
        if (free1.to >= elem.to) {
          room.remove(free1);
          room.add(new IntRange(free1.from, elem.from));
          if (free1.to > elem.to) {
            room.add(new IntRange(elem.to, free1.to));
          }
          if (initial - freeSpace(room) != elem.to - elem.from) {
            throw new IllegalStateException();
          }
          return;
        }
      }
      IntRange free2 = room.tailSet(elem).first();
      if (elem.from >= free2.from && free2.to >= elem.to) {
        room.remove(free2);
        if (free2.to > elem.to) {
          room.add(new IntRange(elem.to, free2.to));
        }
        if (initial - freeSpace(room) != elem.to - elem.from) {
            throw new IllegalStateException();
        }
        return;
      }
    }
    addNewRoom(interval, rooms);
  }

  public static void addNewRoom(int[] interval, List<SortedSet<IntRange>> rooms) {
    SortedSet<IntRange> set = new TreeSet<>();
    int start = interval[0];
    int end = interval[1];
    if (start > 0) {
      set.add(new IntRange(0, start));
    }
    if (end < Integer.MAX_VALUE) {
      set.add(new IntRange(end, Integer.MAX_VALUE));
    }
    rooms.add(set);
  }

  public static int minMeetingRooms(int[][] intervals) {
    if (intervals.length == 0) {
      return 0;
    }
    List<SortedSet<IntRange>> rooms = new ArrayList<>(4);
    for (int i = 0; i < intervals.length; i++) {
      reserve(rooms, intervals[i]);
    }
    return rooms.size();
  }

}
