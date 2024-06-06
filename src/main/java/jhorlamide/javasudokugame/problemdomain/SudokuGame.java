package jhorlamide.javasudokugame.problemdomain;

import jhorlamide.javasudokugame.constants.GameState;

import java.io.Serializable;

public class SudokuGame implements Serializable {
   private final GameState gameState;
   private final int[][] gridState;
   private static final int GRID_BOUNDARY = 9;

   public SudokuGame(GameState gameState, int[][] gridState) {
      this.gameState = gameState;
      this.gridState = gridState;
   }

   public GameState getGameState() {
      return gameState;
   }

   public int[][] getCopyOfGridState() {
      return SudokuUtitlity.copyNewArray(gridState);
   }
}
