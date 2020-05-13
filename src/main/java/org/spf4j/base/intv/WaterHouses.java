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
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * https://leetcode.com/problems/optimize-water-distribution-in-a-village/
 *
 * Implementation:
 * https://en.wikipedia.org/wiki/Reverse-delete_algorithm
 *
 *
 *
 * @author Zoltan Farkas
 */
public class WaterHouses {

  static class Edge implements Comparable<Edge> {
    int from;
    int to;
    int cost;

    public Edge(int from, int to, int cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }

    @Override
    public int hashCode() {
      return this.from + this.to * 597;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      final Edge other = (Edge) obj;
      if (this.from != other.from) {
        return false;
      }
      return this.to == other.to;
    }

    @Override
    public int compareTo(Edge o) {
      int compare = -Integer.compare(cost, o.cost);
      if (compare != 0) {
        return compare;
      }
      compare = Integer.compare(to, o.to);
      if (compare != 0) {
        return compare;
      }
      return Integer.compare(from, o.from);
    }

  }

  static class WaterGraph {
    List<Set<Edge>> node2Edges;
    int nrEdges;
    BitSet visited;

    public WaterGraph(final int n) {
      int n1 = n + 1;
      node2Edges = new ArrayList<>(n1);
      for (int i = 0; i < n1; i++) {
        node2Edges.add(new HashSet<>(4));
      }
      nrEdges = 0;
      visited = new BitSet(node2Edges.size());
    }

    public int getCost() {
      int cost = 0;
      for (Edge edge : getEdgesMap()) {
        cost += edge.cost;
      }
      return cost;
    }

    public void remove(Edge edge) {
      node2Edges.get(edge.from).remove(edge);
      node2Edges.get(edge.to).remove(edge);
      nrEdges--;
    }

    public void add(Edge edge) {
      node2Edges.get(edge.from).add(edge);
      node2Edges.get(edge.to).add(edge);
      nrEdges++;
    }

    public SortedSet<Edge> getEdges() {
      SortedSet<Edge> result = new TreeSet<>();
      for (Set<Edge> edges : node2Edges) {
        for (Edge e: edges) {
          result.add(e);
        }
      }
      return result;
    }

    private Set<Edge> getEdgesMap() {
      Set<Edge> result = new HashSet<>(nrEdges + nrEdges / 4 + 1);
      for (Set<Edge> edges : node2Edges) {
        for (Edge e: edges) {
          result.add(e);
        }
      }
      return result;
    }

    public void addEdge(int from, int to, int cost) {
      Edge edge = new Edge(from, to, cost);
      node2Edges.get(from).add(edge);
      node2Edges.get(to).add(edge);
      nrEdges++;
    }

    ArrayDeque<Integer> toVisit = new ArrayDeque<>();

    public boolean isConnected(int startAt) {
      visited.clear();
      toVisit.add(startAt);
      visited.set(startAt);
      Integer at;
      while ((at = toVisit.pollLast()) != null) {
       for (Edge edge : node2Edges.get(at)) {
         if (!visited.get(edge.from)) {
           toVisit.add(edge.from);
           visited.set(edge.from);
         } else if (!visited.get(edge.to)) {
           toVisit.add(edge.to);
           visited.set(edge.to);
         }
       }
      }
      return (visited.cardinality() == node2Edges.size());
    }
  }

  public static int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
    WaterGraph graph = new WaterGraph(n);
    for (int i = 0; i <  wells.length; i++) {
      graph.addEdge(0, i + 1, wells[i]);
    }
    for (int[] conn : pipes) {
      graph.addEdge(conn[0], conn[1], conn[2]);
    }
    for (Edge edge : graph.getEdges()) {
      graph.remove(edge);
      if (!graph.isConnected(edge.from)) {
        graph.add(edge);
      }
    }
    return graph.getCost();
  }
}
