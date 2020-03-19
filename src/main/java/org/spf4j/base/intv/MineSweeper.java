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

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Zoltan Farkas
 */
public class MineSweeper {

  public static class Board {
    int [][] matrix;

    int nrMines;
    int w;
    int h;

    public Board(int w, int h) {
      matrix = new int[w][h];
      this.w = w;
      this.h = h;
      this.nrMines = 0;
    }

    public boolean isMine(int x, int y) {
      return matrix[x][y] == -1;
    }

    public int setMine(int x, int y) {
      int ex = matrix[x][y];
      matrix[x][y] = -1;
      if (ex >=0) {
        nrMines++;
      }
      return ex;
    }

    public int getNrMines() {
      return nrMines;
    }

    public static Board newRandomBoard(int w, int h, int nrMines) {
      Board board = new Board(w, h);
      ThreadLocalRandom rnd = ThreadLocalRandom.current();
      int space = w * h;
      int[] arrMatrix = new int[space];
      for (int i = 0; i < space; i++) {
        arrMatrix[i] = i;
      }
      for (int i = 0; i < nrMines; i++) {
         int mPos = rnd.nextInt(i, space);
         if (mPos != i) {
           int tmp = arrMatrix[i];
           arrMatrix[i]= arrMatrix[mPos];
           arrMatrix[mPos] = tmp;
         }
        int minePosition = arrMatrix[i];
        if (board.setMine(minePosition % w, minePosition / w) != 0) {
          throw new IllegalStateException("Attempting to set a mine to same place: " + minePosition);
        }
      }
      return board;
    }

    @Override
    public String toString() {
      StringBuilder result = new StringBuilder(h * (w + 1));
      for (int[] row : matrix) {
        for (int val : row) {
          if (val < 0)  {
            result.append('*');
          } else {
            result.append(val);
          }
        }
        result.append('\n');
      }
      return result.toString();
    }



  }



}
