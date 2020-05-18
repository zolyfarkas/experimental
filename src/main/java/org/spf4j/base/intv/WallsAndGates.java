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

/**
 * https://leetcode.com/problems/walls-and-gates/
 *
 * @author Zoltan Farkas
 */
public class WallsAndGates {

  static final class TrState {

    int x;
    int y;
    int dist;

    public TrState(int x, int y, int dist) {
      this.x = x;
      this.y = y;
      this.dist = dist;
    }

  }

  public static void wallsAndGates(int[][] rooms) {
    if (rooms.length == 0) {
      return;
    }
    ArrayDeque<TrState> visit = new ArrayDeque<>();
    for (int i = 0; i < rooms.length; i++) {
      for (int j = 0; j < rooms[i].length; j++) {
        int val = rooms[i][j];
        if (val == 0) {
          visit.add(new TrState(i, j, 0));
        }
      }
    }
    TrState at;
    while ((at = visit.pollFirst()) != null) {
      int x = at.x;
      int y = at.y;
      for (int i = Math.max(x - 1, 0), il = Math.min(x + 2, rooms.length); i < il; i++) {
        for (int j = Math.max(y - 1, 0), jl = Math.min(y + 2, rooms[x].length); j < jl; j++) {
          if (((i == x && j != y)
                  || (j == y && i != x)) && rooms[i][j] == 2147483647) {
            int nd = at.dist + 1;
            rooms[i][j] = nd;
            visit.addLast(new TrState(i, j, nd));
          }
        }
      }
    }
  }

}
