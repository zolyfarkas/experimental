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
public class LRUCacheTest {

  @Test
  public void testCache() {

    LRUCache cache = new LRUCache(2 /* capacity */);

    cache.put(1, 1);
    cache.put(2, 2);
    Assert.assertEquals(1, cache.get(1));      // returns 1
    cache.put(3, 3);    // evicts key 2
    Assert.assertEquals(-1, cache.get(2));       // returns -1 (not found)
    cache.put(4, 4);    // evicts key 1
    Assert.assertEquals(-1, cache.get(1));       // returns -1 (not found)
    Assert.assertEquals(3, cache.get(3));       // returns 3
    Assert.assertEquals(4, cache.get(4));       // returns 4
  }

  @Test
  public void testCache2() {
    LRUCache cache = new LRUCache(2 /* capacity */);
    cache.put(2, 1);
    cache.put(1, 1);
    cache.put(2, 3);
    cache.put(4, 1);
    Assert.assertEquals(-1, cache.get(1));      // returns 1
    Assert.assertEquals(3, cache.get(2));      // returns 1
  }

}
