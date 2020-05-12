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

import java.util.*;

/**
 * Given source and target co-ordinates find the number of moves a Knight has to make to reach its target on a chess
 * board.
 *
 */
public class HorseWalkDistance {

  private static class Point {

    public int x;
    public int y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public boolean equals(Object obj) {
      if (obj instanceof Point) {
        Point pt = (Point) obj;
        return (x == pt.x) && (y == pt.y);
      }
      return super.equals(obj);
    }

    public int hashCode() {
      return Objects.hash(x, y);
    }

    public String toString() {
      return getClass().getName() + "[x=" + x + ",y=" + y + "]";
    }

  }

  private static Point[] getPointsToJumpTo(Point p) {
    int x = p.x;
    int y = p.y;
    return new Point[]{
      new Point(x + 1, y - 2),
      new Point(x + 1, y + 2),
      new Point(x - 1, y - 2),
      new Point(x - 1, y + 2),
      new Point(x + 2, y - 1),
      new Point(x + 2, y + 1),
      new Point(x - 2, y - 1),
      new Point(x - 2, y + 1)
    };
  }

  static class TraversalState {

    Point position;
    int nrStepsFromStart;

    TraversalState(Point p, int nrStepsFromStart) {
      this.position = p;
      this.nrStepsFromStart = nrStepsFromStart;
    }
  }

  private static boolean isInDirection(Point at, Point target, Point candidate) {

    return (Math.abs(target.x - at.x) > Math.abs(target.x - candidate.x))
            && (Math.abs(target.y - at.y) > Math.abs(target.y - candidate.y));

  }

  public static int knownDistance(Point targetPoint, Point at) {
    int xd = Math.abs(targetPoint.x - at.x);
    int yd = Math.abs(targetPoint.y - at.y);
    if (xd == 1) {
      if (yd == 0) {
        return 3;
      } else if (yd == 1) {
        return 2;
      } else {
        return -1;
      }
    } else if (xd == 0) {
      if (yd == 0) {
        return 0;
      } else if (yd == 1) {
        return 3;
      } else {
        return -1;
      }
    } else {
      return -1;
    }
  }

  public static int getSteps(int x, int y, int tx, int ty) {
    Point targetPoint = new Point(tx, ty);
    Set<Point> traversed = new HashSet<>();
    ArrayDeque<TraversalState> traverse = new ArrayDeque<>();
    traverse.addLast(new TraversalState(new Point(x, y), 0));
    TraversalState at;
    while (true) {
      at = traverse.removeFirst();
      int knownDistance = knownDistance(targetPoint, at.position);
      if (knownDistance >= 0) {
        return at.nrStepsFromStart + knownDistance;
      }
      traversed.add(at.position);
      Point[] next = getPointsToJumpTo(at.position);
      for (Point p : next) {
        if (!traversed.contains(p) && isInDirection(at.position, targetPoint, p)) {
          traverse.addLast(new TraversalState(p, at.nrStepsFromStart + 1));
        }
      }
    }
  }
}
