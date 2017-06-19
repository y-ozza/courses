package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by shurik on 19.06.2017.
 */
public class NavigationHelper extends  HelperBase{


   public NavigationHelper(FirefoxDriver wd) {
      super(wd);
   }

   public void gotoGroupPage() {
       click(By.linkText("groups"));
   }

   public void returnBack() {
      wd.navigate().back();
   }

}
