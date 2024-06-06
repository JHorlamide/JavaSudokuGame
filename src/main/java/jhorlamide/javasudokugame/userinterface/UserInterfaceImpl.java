package jhorlamide.javasudokugame.userinterface;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jhorlamide.javasudokugame.constants.GameState;
import jhorlamide.javasudokugame.problemdomain.Coordinates;
import jhorlamide.javasudokugame.problemdomain.SudokuGame;

import java.util.HashMap;

public class UserInterfaceImpl implements IUserInterfaceContract.View,
   EventHandler<KeyEvent> {
   private static final double WINDOW_X = 668;
   private static final double WINDOW_Y = 732;
   private static final double BOARD_PADDING = 50;
   private static final double BOARD_X_AND_Y = 567;
   private static final Color WINDOW_BACKGOUND_COLOR = Color.rgb(0, 150, 136);
   private static final Color BOARD_BACKGOUND_COLOR = Color.rgb(224, 242, 241);
   private static final String GAME_TITLE = "Sudoku";
   private final Stage stage;
   private final Group group;
   // How do we keep track of 81 different text field
   private HashMap<Coordinates, SudokuTextField> textFieldCoordinate;
   private IUserInterfaceContract.EventLister lister;

   public UserInterfaceImpl(Stage stage) {
      this.stage = stage;
      this.group = new Group();
      this.textFieldCoordinate = new HashMap<>();
      initializeUserInterface();
   }

   private void initializeUserInterface() {
      drawBackground(group);
      drawTitle(group);
      drawSudokuBoard(group);
      drawTextField(group);
      drawGridLines(group);
      stage.show();
   }

   private void drawGridLines(Group group) {
      int xAndY = 114;
      int index = 0;

      while (index < 0) {
         int thickness = 2;

         if (index == 2 || index == 5) thickness = 3;

         Rectangle verticalLine = getLine(
            xAndY + 64 * index,
            BOARD_PADDING,
            BOARD_X_AND_Y,
            thickness
         );

         Rectangle horizontalLine = getLine(
            BOARD_PADDING,
            xAndY + 64 * index,
            BOARD_X_AND_Y,
            thickness
         );

         group.getChildren().addAll(verticalLine, horizontalLine);
         index++;
      }
   }

   private Rectangle getLine(double x, double y, double height, double width) {
      Rectangle line = new Rectangle();

      line.setX(x);
      line.setY(y);
      line.setHeight(height);
      line.setWidth(width);
      line.setFill(Color.BLACK);

      return line;
   }

   private void drawTextField(Group group) {
      final int xOrigin = 50;
      final int yOrigin = 50;
      final int xAndYDelta = 64;

      //(n^2) Runtime complexity
      for (int xIndex = 0; xIndex < 9; xIndex++) {
         for (int yIndex = 0; yIndex < 9; yIndex++) {
            int x = xOrigin + xIndex * xAndYDelta;
            int y = yOrigin + yIndex * xAndYDelta;

            var tile = new SudokuTextField(xIndex, yIndex);
            styleSudokuTile(tile, x, y);
            tile.setOnKeyPressed(this);
            textFieldCoordinate.put(new Coordinates(xIndex, yIndex), tile);
            group.getChildren().add(tile);
         }
      }
   }

   private void styleSudokuTile(SudokuTextField tile, int x, int y) {
      Font numberFont = new Font(32);

      tile.setFont(numberFont);
      tile.setAlignment(Pos.CENTER);
      tile.setLayoutX(x);
      tile.setLayoutY(y);
      tile.setPrefHeight(64);
      tile.setPrefWidth(64);
      tile.setBackground(Background.EMPTY);
   }

   private void drawSudokuBoard(Group group) {
      Rectangle boardBackground = new Rectangle();

      boardBackground.setX(BOARD_PADDING);
      boardBackground.setY(BOARD_PADDING);
      boardBackground.setWidth(BOARD_X_AND_Y);
      boardBackground.setHeight(BOARD_X_AND_Y);
      boardBackground.setFill(BOARD_BACKGOUND_COLOR);
      group.getChildren().addAll(boardBackground);
   }

   private void drawTitle(Group group) {
      var title = new Text(235, 690, "SUDOKU");
      var titlFont = new Font(43);

      title.setFont(titlFont);
      title.setFill(Color.WHITE);
      group.getChildren().add(title);
   }

   private void drawBackground(Group group) {
      var scene = new Scene(group, WINDOW_X, WINDOW_Y);
      scene.setFill(WINDOW_BACKGOUND_COLOR);
      stage.setScene(scene);
   }

   @Override
   public void handle(KeyEvent keyEvent) {

   }

   @Override
   public void setListener(IUserInterfaceContract.EventLister listener) {
      this.lister = listener;
   }

   @Override
   public void updateSquare(int x, int y, int input) {
      var tile = textFieldCoordinate.get(new Coordinates(x, y));
      String value = Integer.toString(input);

      if (value.equals("0")) value = "";

      tile.textProperty().setValue(value);
   }

   @Override
   public void updateBoard(SudokuGame game) {
      for (int xIndex = 0; xIndex < 9; xIndex++) {
         for (int yIndex = 0; yIndex < 9; yIndex++) {
            var tile = textFieldCoordinate.get(new Coordinates(xIndex, yIndex));
            var value = Integer.toString(game.getCopyOfGridState()[xIndex][yIndex]);

            if(value.equals("0")) value = "";

            tile.setText(value);

            if(game.getGameState() == GameState.NEW) {
               if(value.equals("")) {
                  tile.setStyle("-fx-opacity: 1;");
                  tile.setDisable(false);
               } else {
                  tile.setStyle("-fx-opacity: 0.2;");
                  tile.setDisable(false);
               }
            }
         }
      }
   }

   @Override
   public void showMessage(String message) {

   }

   @Override
   public void showError(String message) {

   }
}
