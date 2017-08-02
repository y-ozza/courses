package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

/**
 * Created by shurik on 30.07.2017.
 */
public class RegistrationHelper extends  HelperBase{

   public RegistrationHelper(ApplicationManager app) {
      super(app);
   }

   public void start(String username, String email) {

      wd.get(app.getProperty("web.baseUrl")+"/signup_page.php");
      type(By.name("username"), username);
      type(By.name("email"), email);
      click(By.cssSelector("input[value='Signup']"));
   }


   public void finish(String confirmationLink, String password) {
      wd.get(confirmationLink);
      type(By.name("password"), password);
      type(By.name("password_confirm"), password);
      click(By.cssSelector("input[value='Update User']"));

   }


   public void goToUsersPage() {
      wd.get(app.getProperty("web.baseUrl"));
      type(By.name("username"), "administrator");
      type(By.name("password"), "root");
      click(By.cssSelector("input[value='Login']"));
      click(By.linkText("Manage Users"));
   }

   public void resetPassword(HashMap<String, String> user) throws InterruptedException {
      String username = user.get("username");
      click(By.linkText(username));
//      Thread.sleep(10000);
      click(By.cssSelector("input[value='Reset Password']"));
   }
}
