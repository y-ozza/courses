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

   public void GroupPage() {
      if (isElementPresent(By.tagName("h1")) && wd.findElement(By.tagName("h1")).getText().equals("Groups") && isElementPresent(By.name("new"))) {
            return;
      }
       click(By.linkText("groups"));
   }

   public void returnBack() {
      wd.navigate().back();
   }

   public void gotoHomePage() {
      if (isElementPresent(By.id("maintable"))) {
         return;
      }
      else {
         wd.findElement(By.xpath("//div/div[3]/ul/li[1]/a")).click();
      }
   }


}
