package jhorlamide.javasudokugame.problemdomain;

import java.util.Objects;

public class Coordinates {
   private final int x;
   private final int y;

   public Coordinates(int x, int y) {
      this.x = x;
      this.y = y;
   }

   public int getX() {
      return x;
   }

   public int getY() {
      return y;
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) return true;
      if (other == null || getClass() != other.getClass()) return false;
      Coordinates that = (Coordinates) other;
      return x == that.x && y == that.y;
   }

   @Override
   public int hashCode() {
      return Objects.hash(x, y);
   }
}
