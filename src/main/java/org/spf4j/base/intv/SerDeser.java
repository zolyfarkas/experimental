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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Objects;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * @author Zoltan Farkas
 */
public class SerDeser {

  public static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }

    @Override
    public int hashCode() {
      int hash = 7;
      hash = 23 * hash + this.val;
      hash = 23 * hash + Objects.hashCode(this.left);
      hash = 23 * hash + Objects.hashCode(this.right);
      return hash;
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
      final TreeNode other = (TreeNode) obj;
      if (this.val != other.val) {
        return false;
      }
      if (!Objects.equals(this.left, other.left)) {
        return false;
      }
      if (!Objects.equals(this.right, other.right)) {
        return false;
      }
      return true;
    }


  }

  private static class NodeInfo {

    public NodeInfo(TreeNode node, int parentId) {
      this.node = node;
      this.parentId = parentId;
    }

    private TreeNode node;
    private int parentId;
  }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
       if (root == null) {
         return "";
       }
       StringBuilder result = new StringBuilder(16);
       int idx = 1;
       ArrayDeque<NodeInfo> queue = new ArrayDeque<>(4);
       queue.add(new NodeInfo(root, 0));
       NodeInfo current;
       while ((current = queue.pollFirst()) != null) {
          result.append(current.parentId);
          result.append(',');
          int pid = idx++;
//          result.append(pid);
//          result.append(',');
          result.append(current.node.val);
          result.append(',');
          if (current.node.left != null) {
            queue.add(new NodeInfo(current.node.left, -pid));
          }
          if (current.node.right != null) {
            queue.add(new NodeInfo(current.node.right, pid));
          }
       }
       return result.toString();

    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data.isEmpty()) {
          return null;
        }
        ArrayList<TreeNode> list = new ArrayList();
        list.add(null);
        int at = 0;
        int l = data.length() - 1;
        while (at < l) {
          int indexOf = data.indexOf(',', at);
          if (indexOf < 0) {
            throw new IllegalStateException("Unexpected EOF at " + at);
          }
          int parentId = Integer.parseInt(data.substring(at, indexOf));
          at = indexOf + 1;
          indexOf = data.indexOf(',', at);
          if (indexOf < 0) {
            throw new IllegalStateException("Unexpected EOF at " + at);
          }
          int value = Integer.parseInt(data.substring(at, indexOf));
          TreeNode node = new TreeNode(value);
          TreeNode parent = list.get(Math.abs(parentId));
          if (parent != null) {
            if (parentId < 0) {
              parent.left = node;
            } else {
              parent.right = node;
            }
          }
          list.add(node);
          at = indexOf + 1;
        }
        if (list.size() >= 2) {
          return list.get(1);
        } else {
          return null;
        }
    }
}
