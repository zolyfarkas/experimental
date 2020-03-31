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
public class IntervalNode<I, T> extends RBNode<T> {

  private Interval<I> interval;
  private Comparator<I> endpointComparator;
  private I minEndpoint;
  private I maxEndpoint;

  public IntervalNode(Interval<I> interval, Comparator<I> endpointComparator, T data) {
    super(data);
    this.interval = interval;
    this.endpointComparator = endpointComparator;
  }

  public void copyFrom(RBNode<T> arg) {
    IntervalNode argNode = (IntervalNode) arg;
    this.interval = argNode.interval;
  }

  public Interval<I> getInterval() {
    return interval;
  }

  public I getMinEndpoint() {
    return minEndpoint;
  }

  public I getMaxEndpoint() {
    return maxEndpoint;
  }

  public boolean update() {
    I newMaxEndpoint = computeMaxEndpoint();
    I newMinEndpoint = computeMinEndpoint();

    if ((maxEndpoint != newMaxEndpoint) || (minEndpoint != newMinEndpoint)) {
      maxEndpoint = newMaxEndpoint;
      minEndpoint = newMinEndpoint;
      return true;
    }

    return false;
  }

  // Computes maximum endpoint without setting it in this node
  public I computeMinEndpoint() {
    IntervalNode<I, T> left = (IntervalNode) getLeft();
    if (left != null) {
      return left.getMinEndpoint();
    }
    return interval.getLowEndpoint();
  }

  // Computes maximum endpoint without setting it in this node
  public I computeMaxEndpoint() {
    I curMax = interval.getHighEndpoint();
    if (getLeft() != null) {
      IntervalNode<I, T> left = (IntervalNode) getLeft();
      if (endpointComparator.compare(left.getMaxEndpoint(), curMax) > 0) {
        curMax = left.getMaxEndpoint();
      }
    }

    if (getRight() != null) {
      IntervalNode<I, T> right = (IntervalNode) getRight();
      if (endpointComparator.compare(right.getMaxEndpoint(), curMax) > 0) {
        curMax = right.getMaxEndpoint();
      }
    }
    return curMax;
  }

  public String toString() {
    String res = interval.toString();
    Object d = getData();
    if (d != null) {
      res += " " + d;
    }
    return res;
  }
}
