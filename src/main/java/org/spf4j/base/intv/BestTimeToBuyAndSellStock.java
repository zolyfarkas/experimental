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

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 * @author Zoltan Farkas
 */
public class BestTimeToBuyAndSellStock {

    public static int maxProfit(int[] prices) {
      int l = prices.length;
      if (l <= 0) {
        return 0;
      }
      int [] maxProfitUpTo = new int[l];
      int min = prices[0];
      int pm1 = 0;
      for (int i = 1; i < l; i++) {
        int currPrice = prices[i];
        if (currPrice > min) {
          int profit = Math.max(currPrice - min, pm1);
          maxProfitUpTo[i] = profit;
          pm1 = profit;
        } else {
          min = currPrice;
          maxProfitUpTo[i] = pm1;
        }
      }
      int [] maxProfitFrom = new int[l];
      int lm1 = l - 1;
      int max = prices[lm1];
      int pp1 = 0;
      for (int i = lm1 - 1; i >=0; i--) {
         int currPrice = prices[i];
         if (currPrice < max) {
           int profit = Math.max(max - currPrice, pp1);
           maxProfitFrom[i] = profit;
           pp1 = profit;
         } else {
           max = currPrice;
           maxProfitFrom[i] =  pp1;
         }
      }

      int maxProfit = 0;
      for (int i = 0; i < lm1; i++) {
        int profitAt = maxProfitUpTo[i] + maxProfitFrom[i + 1];
        if (profitAt > maxProfit) {
          maxProfit = profitAt;
        }
      }
      int lp = maxProfitUpTo[lm1];
      if (lp > maxProfit) {
        maxProfit = lp;
      }
      return maxProfit;
    }

}
