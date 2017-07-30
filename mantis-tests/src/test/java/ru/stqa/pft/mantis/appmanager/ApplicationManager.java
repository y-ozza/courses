package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by shurik on 19.06.2017.
 */
public class ApplicationManager {
   private final Properties properties;
   WebDriver wd;



   private String browser;

   public ApplicationManager(String browser) {
      this.browser = browser;
      properties = new Properties();
   }


   public void init() throws IOException {

      String target = System.getProperty("target", "local");
      properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

      if (browser.equals(BrowserType.FIREFOX)) {
          wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
       }
       else if (browser.equals(BrowserType.CHROME)) {
          wd = new ChromeDriver();
       }
       else if (browser.equals(BrowserType.OPERA_BLINK)) {
          wd = new OperaDriver();
       }
      wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
//      wd.get("http://localhost:8081/addressbook/");
      wd.get(properties.getProperty("web.baseURL"));

   }


   public void stop() {
      wd.quit();
   }

   public ru.stqa.pft.mantis.appmanager.HttpSession newSession() {
      return  new ru.stqa.pft.mantis.appmanager.HttpSession(this);
   }

   public String getProperty(String key) {

      return properties.getProperty(key);
   }


}




