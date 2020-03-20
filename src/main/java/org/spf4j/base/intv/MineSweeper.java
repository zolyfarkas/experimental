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

  public static final class Board {

    /** could be replaced by a set of points where the mines are to reduce mem footprint*/
    private final boolean [][] matrix;
    private final int width;
    private final int height;
    private int nrMines;

    public Board(int width, int height) {
      if (width <=0 || height <= 0) {
        throw new IllegalArgumentException("Invalid minefield w=" + width + ", h=" + height);
      }
      matrix = new boolean[width][height];
      this.width = width;
      this.height = height;
      this.nrMines = 0;
    }

    public boolean isMine(int x, int y) {
      return matrix[x][y];
    }

    /**
     * Put a mine to a position.
     * @param x
     * @param y
     * @return true if there was a mine already there
     */
    public boolean setMine(int x, int y) {
      boolean ex = matrix[x][y];
      if (!ex) {
        matrix[x][y] = true;
        nrMines++;
      }
      return ex;
    }

    /**
     * Poke around in the mine field with a metal detector.
     * @param x coordinate
     * @param y coordinate
     * @return -1 if mine, number of adjacent mines otherwise.
     */
    public int poke(int x, int y) {
      if (isMine(x,y)) {
        return -1;
      } else {
        int nrm = 0;
        for (int i = Math.max(x - 1, 0), xp1 = Math.min(x + 2, width); i < xp1; i++) {
          for (int j = Math.max(y - 1, 0), yp1 = Math.min(y + 2, height); j < yp1; j++) {
            if (isMine(i, j)) {
              nrm++;
            }
          }
        }
        return nrm;
      }
    }

    interface DetectorReadingConsumer {
      void accept(int reading);
      void endRow();
    }

    void pokeAll(DetectorReadingConsumer drc) {
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          drc.accept(poke(x, y));
        }
        drc.endRow();
      }
    }

    public int getNrMines() {
      return nrMines;
    }

    /**
     * Create a random mine sweeper board.
     * @param width
     * @param height
     * @param nrMines
     * @return
     */
    public static Board newRandomBoard(int width, int height, int nrMines) {
      Board board = new Board(width, height);
      ThreadLocalRandom rnd = ThreadLocalRandom.current();
      int space = width * height;
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
        if (board.setMine(minePosition % width, minePosition / width)) {
          throw new IllegalStateException("Attempting to set a mine to same place: " + minePosition);
        }
      }
      return board;
    }

    @Override
    public String toString() {
      StringBuilder result = new StringBuilder(height * (width + 1));
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          result.append(isMine(x, y) ? '*' : 'O');
        }
        result.append('\n');
      }
      return result.toString();
    }
  }



}
