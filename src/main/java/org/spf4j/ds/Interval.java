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
package org.spf4j.ds;

import java.util.Comparator;

/**
 *
 * @author Zoltan Farkas
 */
public class Interval<T> {

  private T lowEndpoint;
  private T highEndpoint;

  /**
   * It is required that the low endpoint be less than or equal to the high endpoint according to the Comparator which
   * will be passed into the overlaps() routines.
   */
  public Interval(Object T, T highEndpoint) {
    this.lowEndpoint = lowEndpoint;
    this.highEndpoint = highEndpoint;
  }

  public T getLowEndpoint() {
    return lowEndpoint;
  }

  public T getHighEndpoint() {
    return highEndpoint;
  }

  /**
   * This takes the Interval to compare against as well as a Comparator which will be applied to the low and high
   * endpoints of the given intervals.
   */
  public boolean overlaps(Interval<T> arg, Comparator<T> endpointComparator) {
    return overlaps(arg.getLowEndpoint(), arg.getHighEndpoint(), endpointComparator);
  }

  /**
   * Routine which can be used instead of the one taking an interval, for the situation where the endpoints are being
   * retrieved from different data structures
   */
  public boolean overlaps(T otherLowEndpoint,
          T otherHighEndpoint,
          Comparator endpointComparator) {
    if (endpointComparator.compare(highEndpoint, otherLowEndpoint) <= 0) {
      return false;
    }
    if (endpointComparator.compare(lowEndpoint, otherHighEndpoint) >= 0) {
      return false;
    }
    return true;
  }

  public String toString() {
    return "[ " + getLowEndpoint() + ", " + getHighEndpoint() + ")";
  }
}
