public class MyFirstProgram {
   public static void main(String[] args) {
      System.out.println("Hello World");
      Point point1 = new Point(1.0, 1.0);
      Point point2 = new Point(2.0, 2.0);
      System.out.println(distanceBetween(point1, point2));
      System.out.println(point1.distanceTo(point2));

   }
   public static class Point {
      double x, y;
      public  Point(double x, double y) {
         this.x = x;
         this.y = y;
      }

      public double distanceTo(Point thePoint) {
         return  Math.sqrt(Math.pow((x - thePoint.x), 2.0)+(Math.pow((y - thePoint.y), 2.0)));

      }

   }
   public static double distanceBetween(Point point1, Point point2) {
      return  Math.sqrt(Math.pow((point1.x - point2.x), 2.0)+(Math.pow((point1.y - point2.y), 2.0)));
   }

}