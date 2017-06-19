package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by shurik on 19.06.2017.
 */
public class NavigationHelper {
   FirefoxDriver wd;


   public NavigationHelper(FirefoxDriver wd) {
      this.wd = wd;
   }

   public void gotoGroupPage() {
       wd.findElement(By.linkText("groups")).click();
   }
}
