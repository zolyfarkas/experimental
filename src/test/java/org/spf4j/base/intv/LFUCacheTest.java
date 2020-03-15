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
public class LFUCacheTest {


  @Test
  public void testSomeMethod() {
    LFUCache cache = new LFUCache(2);
    cache.put(3, 1);
    cache.put(2, 1);
    cache.put(2, 2);
    cache.put(4, 4);
    Assert.assertEquals(2, cache.get(2));
  }

  @Test
  public void testSomeMethod2() {
    LFUCache cache = new LFUCache(0);
    cache.put(0, 0);
    Assert.assertEquals(-1, cache.get(0));
  }

}
