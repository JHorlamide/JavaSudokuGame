package jhorlamide.javasudokugame.userinterface;

import jhorlamide.javasudokugame.problemdomain.SudokuGame;

public interface IUserInterfaceContract {
   interface EventLister {
      void onSudokuInput(int x, int y, int input);
      void onDialogClick();
   }

   interface View {
      void setListener(IUserInterfaceContract.EventLister listener);
      void updateSquare(int x, int y, int input);
      void updateBoard(SudokuGame game);
      void showMessage(String message);
      void showError(String message);
   }
}
