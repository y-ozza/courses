package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;

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
   private WebDriver wd;



   private String browser;
   private RegistrationHelper registrationHelper;
   private FtpHelper ftpHelper;
   private MailHelper mailHelper;
   private SoapHelper soapHelper;
   private JamesHelper jamesHelper;

   public ApplicationManager(String browser) {
      this.browser = browser;
      properties = new Properties();
   }


   public void init() throws IOException {

      String target = System.getProperty("target", "local");
      properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

   }


   public void stop() {
      if (wd != null) {
         wd.quit();

      }
   }

   public HttpSession newSession() {
      return  new HttpSession(this);
   }

   public String getProperty(String key) {

      return properties.getProperty(key);
   }


   public RegistrationHelper registration() {
      if (registrationHelper == null) {
         registrationHelper =  new RegistrationHelper(this);
      }
      return registrationHelper;
   }

   public FtpHelper ftp() {
      if (ftpHelper == null) {
         ftpHelper =  new FtpHelper(this);
      }
      return ftpHelper;
   }
   
   public MailHelper mail() {
      if(mailHelper == null) {
         mailHelper = new MailHelper(this);
      }
      return  mailHelper;
   }

   public SoapHelper soap() {
      if(soapHelper == null) {
         soapHelper = new SoapHelper(this);
      }
      return  soapHelper;
   }

   public JamesHelper james() {
      if(jamesHelper == null) {
         jamesHelper = new JamesHelper(this);
      }
      return  jamesHelper;
   }


   public WebDriver getDriver() {
      if (wd==null) {
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
         wd.get(properties.getProperty("web.baseUrl"));
      }

      return  wd;
   }
}



