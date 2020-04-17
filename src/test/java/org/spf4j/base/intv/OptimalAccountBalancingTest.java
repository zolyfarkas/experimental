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

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Zoltan Farkas
 */
public class OptimalAccountBalancingTest {


  @Test
  public void testNrBallances() {
    Assert.assertEquals(4, OptimalAccountBalancing.minTransfers(new int[][] {{0,1,5},{2,3,1},{2,0,1},{4,0,2}}));
  }


  @Test
  public void testNrBallances2() {
    Assert.assertEquals(4, OptimalAccountBalancing.minTransfers(new int[][] {{1,2,3},{1,3,3},{6,4,1},{5,4,4}}));
  }
}
