package jhorlamide.javasudokugame.problemdomain;

import java.io.IOException;

public interface IStorage {
   void updateGameData(SudokuGame game) throws IOException;
   SudokuGame getGameData() throws IOException;
}
