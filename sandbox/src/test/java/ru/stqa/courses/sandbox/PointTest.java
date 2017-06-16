package ru.stqa.courses.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Created by shurik on 13.06.2017.
 */
public class PointTest {
   @Test
   public  void  testDistanceTo() {
      Point point1 = new Point(2.0, 2.0);
      Point point2 = new Point(5.0, 6.0);
      Assert.assertEquals(point1.distanceTo(point2), 5.0);
   }
}
