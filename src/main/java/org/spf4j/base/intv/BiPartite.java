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

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/is-graph-bipartite/
 * @author Zoltan Farkas
 */
public class BiPartite {

  public static class Graph {

    Set<Integer>[] edges;

    public Graph(int size) {
       edges = new Set[size];
    }


    public void addEdge(int a, int b) {
      Set<Integer> xe = edges[a];
      if (xe == null) {
        xe = new HashSet(4);
        edges[a] = xe;
      }
      xe.add(b);
      xe = edges[b];
      if (xe == null) {
        xe = new HashSet(4);
        edges[b] = xe;
      }
      xe.add(a);
    }

    public void addNode(int node) {
      if (edges[node] == null) {
        edges[node] = new HashSet<>(4);
      }
    }

    public boolean isBipartite() {
      int nrNodes = edges.length;
      Boolean[] groups = new Boolean[nrNodes];
      for (int node = 0; node < nrNodes; node++) {
        if (groups[node] == null) {
          if (!isBipartite(node, false, groups)) {
            return false;
          }
        }
      }
      return true;
    }

    public boolean isBipartite(Integer at, boolean thisGroup, Boolean[] groups) {
      Boolean get = groups[at];
      if (get == null) {
        groups[at] = thisGroup;
        Set<Integer> connected = edges[at];
        for (Integer to : connected) {
          if (!isBipartite(to, !thisGroup, groups)) {
            return false;
          }
        }
        return true;
      } else {
        return get == thisGroup;
      }
    }

  }

  public static boolean isBipartite(int[][] graph) {
    int length = graph.length;
    Graph g = new Graph(length);
    for (int i = 0; i < length; i++) {
      int[] conn = graph[i];
      if (conn.length == 0) {
        g.addNode(i);
      } else {
        for (int j = 0; j < conn.length; j++) {
          g.addEdge(i, conn[j]);
        }
      }
    }
    return g.isBipartite();

  }

}
