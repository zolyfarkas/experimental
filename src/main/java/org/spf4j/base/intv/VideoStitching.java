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

import java.util.Arrays;


/**
 * https://leetcode.com/problems/video-stitching/
 *
 * @author Zoltan Farkas
 */
public class VideoStitching {

  static class SearchResult {
    int coveredTo;
    int at;

    public SearchResult(int coveredTo, int at) {
      this.coveredTo = coveredTo;
      this.at = at;
    }


  }

  public static SearchResult itNexIncl(int[][] clips, int from,  int incl) {
    int prevFound = -1;
    int i = from;
    while (i < clips.length) {
      int[] next = clips[i];
      int start = next[0];
      if (start > incl) {
        return new SearchResult(prevFound, i);
      } else {
        int nxt = next[1];
        if (nxt > prevFound) {
          prevFound = nxt;
        }
      }
      i++;
    }
    return new SearchResult(prevFound, i);
  }


  public static int videoStitching(int[][] clips, int T) {
    if (clips.length < 1) {
      throw new IllegalArgumentException("Illegal Arg: " + clips);
    }
    Arrays.sort(clips, (l, r) -> Integer.compare(l[0], r[0]));
    int i = 0;
    int from = 0;
    SearchResult itNexIncl;
    int count = 0;
    do {
      itNexIncl = itNexIncl(clips, i, from);
      if (itNexIncl.coveredTo < 0) {
        return -1;
      }
      count++;
      from = itNexIncl.coveredTo;
      if (from >= T) {
        return count;
      }
      i = itNexIncl.at;
    } while(i < clips.length);
    return -1;
  }

}