package jhorlamide.javasudokugame;

import javafx.application.Application;
import javafx.stage.Stage;
import jhorlamide.javasudokugame.userinterface.IUserInterfaceContract;
import jhorlamide.javasudokugame.userinterface.UserInterfaceImpl;

import java.io.IOException;

public class SudokuApplication extends Application {
   private IUserInterfaceContract.View userInterfaceContract;

   public static void main(String[] args) {
      launch(args);
   }

   @Override
   public void start(Stage stage) throws IOException {
      userInterfaceContract = new UserInterfaceImpl(stage);

      try {
         SudokuBuildLogic.build(uiImpl);
      } catch (IOException e) {
         e.printStackTrace();
         throw e;
      }
   }
}