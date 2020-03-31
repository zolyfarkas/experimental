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

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/logger-rate-limiter/
 * @author Zoltan Farkas
 */
public class LoggerRateLimiter {


  private Map<String, Integer> lastPrintedTime = new HashMap<>();

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
      Integer lastPrtTime = lastPrintedTime.get(message);
      if (lastPrtTime == null) {
        lastPrintedTime.put(message, timestamp);
        return true;
      }
      if (timestamp - lastPrtTime >= 10) {
        lastPrintedTime.put(message, timestamp);
        return true;
      } else {
        return false;
      }
    }
}
