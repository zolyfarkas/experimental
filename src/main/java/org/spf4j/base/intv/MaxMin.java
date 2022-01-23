/*
 * Copyright 2021 SPF4J.
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

import java.util.Scanner;

/**
 * @author Zoltan Farkas
 */
public final class MaxMin {

  public static void main(String[] args) {
    System.out.println("Enter a number, (-1 to quit)");
    Scanner scanner = new Scanner(System.in);
    int number;
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    while ((number = scanner.nextInt()) != -1) {
      if (min > number) {
        min = number;
      }
      if (max < number) {
        max = number;
      }
       System.out.println("max: " + max);
       System.out.println("min: " + min);
    }
  }

}
