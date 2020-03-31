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
package org.spf4j.ts;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import org.threeten.extra.YearQuarter;

/**
 *
 * [1, 4] (a=1), [5, 8]  (a=2), [9, 9] (a=3), [10, 10] (a=4), [0, 20] (b=1)
 *
 * [0, 0] (a=null,b=1), [1, 4] (a=2, b=1), ..., [10, 10] (a=4, b=1), [11, 20] (a=null, b=1) ...
 *
 *
 * @author Zoltan Farkas
 */
public class TemporalValidatyJoiner<T> {

  public static class Key<T> {

    private T key;

    private Temporal validFrom;

    private Temporal validTo;

  }

  private final int nrColumns;

  private final SortedSet<Temporal> validityPoints;


  public TemporalValidatyJoiner(final int nrColumns) {
    this.nrColumns = nrColumns;
    this.validityPoints = new TreeSet<>();
  }

  public void add(Temporal from, Temporal to, T joinValue, int columnIdx, Object value) {
    validityPoints.add(from);
    if (!from.equals(to)) {
      validityPoints.add(to);
    }
  }


   public  SortedMap<Key, Object[]> getResultSet() {
     return new TreeMap<>();
   }

  public static Instant endOf(final Temporal t1) {
    return endOf(t1, ZoneOffset.UTC);
  }

  public static Instant endOf(final Temporal t1, final ZoneOffset zo) {
    Class<? extends Temporal> aClass = t1.getClass();
    if (aClass == LocalDate.class) {
      return ((LocalDate) t1). plusDays(1).atStartOfDay(zo).toInstant();
    } else if (aClass == YearMonth.class) {
      return ((YearMonth) t1).plusMonths(1).atDay(1).atStartOfDay(zo).toInstant();
    }  else if (aClass == YearQuarter.class) {
      return ((YearQuarter) t1).plusQuarters(1).atDay(1).atStartOfDay(zo).toInstant();
    } else if (aClass == Year.class) {
      return ((Year) t1).plusYears(1).atDay(1).atStartOfDay(zo).toInstant();
    } else if (aClass == ZonedDateTime.class) {
      return ((ZonedDateTime) t1).toInstant();
    } else if (aClass == LocalDateTime.class) {
      return ((LocalDateTime) t1).toInstant(zo);
    } else if (aClass == Instant.class) {
      return ((Instant) t1);
    } else {
      throw new IllegalArgumentException("Invalid temporal " + t1);
    }
  }

}
