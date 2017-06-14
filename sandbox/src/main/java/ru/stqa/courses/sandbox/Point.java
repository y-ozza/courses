package ru.stqa.courses.sandbox;

/**
 * Created by shurik on 13.06.2017.
 */
public class Point {
   double x, y;

   public Point(double x, double y) {
      this.x = x;
      this.y = y;
   }

   public double distanceTo(Point thePoint) {
      return Math.sqrt(Math.pow((x - thePoint.x), 2.0) + (Math.pow((y - thePoint.y), 2.0)));

   }

}
