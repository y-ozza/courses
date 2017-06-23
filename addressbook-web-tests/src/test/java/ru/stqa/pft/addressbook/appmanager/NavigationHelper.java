package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by shurik on 19.06.2017.
 */
public class NavigationHelper extends  HelperBase{


   public NavigationHelper(WebDriver wd) {
      super(wd);
   }

   public void gotoGroupPage() {
       click(By.linkText("groups"));
   }

   public void returnBack() {
      wd.navigate().back();
   }

   public void gotoHomePage() {
      wd.findElement(By.xpath("//div/div[3]/ul/li[1]/a")).click();
   }


}
