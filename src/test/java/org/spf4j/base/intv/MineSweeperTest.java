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
public class MineSweeperTest {

  @Test(expected = IllegalArgumentException.class)
  public void testRandomBoardEx() {
     testBoard(0, 1, 0);
  }


  @Test
  public void testRandomBoard() {
    testBoard(1, 1, 0);
    testBoard(1, 1, 1);
    testBoard(1, 10, 3);
    testBoard(10, 1, 3);
    testBoard(3, 4, 3);
    testBoard(4, 3, 6);
    testBoard(4, 3, 12);
  }

  public void testBoard(int w, int h, int nrm) {
    for (int i = 0; i < 10; i++) {
      MineSweeper.Board board = MineSweeper.Board.newRandomBoard(w, h, nrm);
      System.out.println(board);
      Assert.assertEquals(nrm, board.getNrMines());
      pokeBoard(board);
    }
  }

  public void pokeBoard(MineSweeper.Board board) {
    board.pokeAll(new MineSweeper.Board.DetectorReadingConsumer() {
      @Override
      public void accept(int reading) {
        if (reading < 0) {
          System.out.print('*');
        } else {
          System.out.print(reading);
        }
      }

      @Override
      public void endRow() {
        System.out.println();
      }
    });
    System.out.println();
  }

}
