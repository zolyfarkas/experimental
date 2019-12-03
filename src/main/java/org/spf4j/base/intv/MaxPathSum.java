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

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * @author Zoltan Farkas
 */
public class MaxPathSum {


   public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

   public static class MaxResult  {
     int maxPath;
     Integer maxGain;

    public MaxResult(int maxPath,  int maxGain) {
      this.maxPath = maxPath;
      this.maxGain = maxGain;
    }

   }

   public static int maxPathSum(TreeNode root) {
     return maxPathSum2(root).maxPath;

   }

  public static int max(int ... vals) {
    int m = vals[0];
    for (int i = 0; i<vals.length; i++) {
      int v = vals[i];
      if (v > m) {
        m = v;
      }
    }
    return m;
  }

  public static MaxResult maxPathSum2(TreeNode root) {
    if (root.left == null) {
      if (root.right == null) {
        return new MaxResult(root.val, root.val);
      } else {
        MaxResult mrl = maxPathSum2(root.right);
        return new MaxResult(Math.max(root.val,Math.max(mrl.maxGain + root.val, mrl.maxPath)),
                Math.max(root.val, mrl.maxGain + root.val));

      }
    } else {
      if (root.right == null) {
        MaxResult mrl = maxPathSum2(root.left);
        return new MaxResult(Math.max(root.val,Math.max(mrl.maxGain + root.val, mrl.maxPath)),
                Math.max(root.val, mrl.maxGain + root.val));
      } else {
        MaxResult lres = maxPathSum2(root.left);
        MaxResult rres = maxPathSum2(root.right);
        return new MaxResult(max(root.val,
                lres.maxGain + root.val,
                rres.maxGain + root.val,
                rres.maxGain + lres.maxGain + root.val,
                lres.maxPath,
                rres.maxPath),
                max(root.val, lres.maxGain + root.val, rres.maxGain + root.val));
      }
    }

  }


}
