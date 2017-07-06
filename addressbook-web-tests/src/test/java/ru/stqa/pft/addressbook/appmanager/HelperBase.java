package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by shurik on 19.06.2017.
 */
public class HelperBase {
   WebDriver wd;

   public HelperBase(WebDriver wd) {
      this.wd = wd;
   }

   public void click(By locator) {
      wd.findElement(locator).click();
   }

   protected void type(By locator, String text) {
      click(locator);
      if (text != null) {
         String existingText = wd.findElement(locator).getAttribute("value");
         if (!existingText.equals(text)) {
            wd.findElement(locator).clear();
            wd.findElement(locator).sendKeys(text);
         }
      }
   }

   public  boolean isAlertPresent() {
      try {
         wd.switchTo().alert();
         return true;
      } catch (NoAlertPresentException e) {
         return false;
      }
   }

   protected boolean isElementPresent(By locator) {
      try {
         wd.findElement(locator);
         return  true;
      }
      catch (NoSuchElementException e) {
         return false;
      }
   }
}
