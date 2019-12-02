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

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Zoltan Farkas
 */
public class SmallersMissingTest {

  @Test
  public void testMissing() {
    Assert.assertEquals(2, SmallersMissing.firstMissingPositive(new int[] {3,4,-1,1}));
    Assert.assertEquals(1, SmallersMissing.firstMissingPositive(new int[] {7,8,9,11,12}));
  }

  @Test
  public void testMissing2() {
     Assert.assertEquals(1, SmallersMissing.firstMissingPositive(new int[] {-1,-2}));
  }

  @Test
  public void testMissing3() {
     Assert.assertEquals(2, SmallersMissing.firstMissingPositive(new int[] {1}));
  }

  @Test
  public void testMissing4() {
    Assert.assertEquals(3, SmallersMissing.firstMissingPositive(new int[] {1,2,0}));
  }

}
