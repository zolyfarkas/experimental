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
public class MwsSlowTest {

  @Test
  public void testMinWindow() {
    String minWindow = MwsSlow.minWindow("ADOBECODEBANC", "ABC");
    System.out.println(minWindow);
    Assert.assertEquals("BANC", minWindow);
  }
  @Test
  public void testMinWindow2() {
    String minWindow = MwsSlow.minWindow("a", "aa");
    System.out.println(minWindow);
    Assert.assertEquals("", minWindow);
  }

}
