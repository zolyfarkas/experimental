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

import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/k-closest-points-to-origin/
 * @author Zoltan Farkas
 */
public class KCLosestPoints {

    public static int[][] kClosest(int[][] points, int k) {
       PriorityQueue<int[]> pq = new PriorityQueue<>(k, (a, b) -> {
         long a0 = a[0];
         long a1 = a[1];
         long b0 = b[0];
         long b1 = b[1];
         return Long.compare(b0 * b0 + b1 * b1, a0 * a0 + a1 * a1); });
       for (int[] point : points) {
         pq.add(point);
         if (pq.size() > k) {
           pq.remove();
         }
       }
       return pq.toArray(new int[pq.size()][]);
    }

}
