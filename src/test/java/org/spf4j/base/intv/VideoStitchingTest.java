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
import org.junit.Test;import static org.junit.Assert.*;

/**
 *
 * @author Zoltan Farkas
 */
public class VideoStitchingTest {



  @Test
  public void testSomeMethod() {
    Assert.assertEquals(3, VideoStitching.videoStitching(new int[][] {{0,2},{4,6},{8,10},{1,9},{1,5},{5,9}}, 10));
  }

  @Test
  public void testSomeMethod2() {
    Assert.assertEquals(2, VideoStitching.videoStitching(new int[][] {{0,4},{2,8}}, 5));
  }

  @Test
  public void testSomeMethod3() {
    Assert.assertEquals(2, VideoStitching.videoStitching(new int[][] {{0,4},{2,8}}, 8));
  }

  @Test
  public void testSomeMethod4() {
    Assert.assertEquals(-1, VideoStitching.videoStitching(new int[][] {{0,4},{2,8}}, 9));
  }

  @Test
  public void testSomeMethod5() {
    Assert.assertEquals(-1, VideoStitching.videoStitching(new int[][] {{0,5},{6,8}}, 7));
  }

  @Test
  public void testSomeMethod6() {
    Assert.assertEquals(1, VideoStitching.videoStitching(
            new int[][] {{5,7},{1,8},{0,0},{2,3},{4,5},{0,6},{5,10},{7,10}}, 5));
  }





}
