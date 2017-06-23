package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;


import java.util.concurrent.TimeUnit;

/**
 * Created by shurik on 19.06.2017.
 */
public class ApplicationManager {
   WebDriver wd;



   private NavigationHelper navigationHelper;
   private GroupHelper groupHelper;
   private SessionHelper sessionHelper;
   private ContactHelper contactHelper;
   private String browser;

   public ApplicationManager(String browser) {
      this.browser = browser;
   }


   public void init() {
       if (browser.equals(BrowserType.FIREFOX)) {
          wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
       }
       else if (browser.equals(BrowserType.CHROME)) {
          wd = new ChromeDriver();
       }
       else if (browser.equals(BrowserType.OPERA_BLINK)) {
          wd = new OperaDriver();
       }
      wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
      wd.get("http://localhost:8081/addressbook/");
      groupHelper = new GroupHelper(wd);
      navigationHelper = new NavigationHelper(wd);
      sessionHelper = new SessionHelper(wd);
      contactHelper = new ContactHelper(wd);
      sessionHelper.login("admin", "secret");
   }


   public void stop() {
      wd.quit();
   }

   public GroupHelper getGroupHelper() {
      return groupHelper;
   }

   public ContactHelper getContactHelper() {
      return contactHelper;
   }

   public NavigationHelper getNavigationHelper() {
      return navigationHelper;
   }
}
