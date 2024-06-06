module jhorlamide.javasudokugame {
   requires javafx.controls;
   requires javafx.fxml;


   opens jhorlamide.javasudokugame to javafx.fxml;
   exports jhorlamide.javasudokugame;
}