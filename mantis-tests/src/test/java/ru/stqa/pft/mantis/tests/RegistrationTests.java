package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * Created by shurik on 30.07.2017.
 */
public class RegistrationTests extends  TestBase {

   @BeforeMethod
   public void startMailServer() {
      app.mail().start();
   }

   @Test
   public  void testRegistration() throws IOException, MessagingException {
      long now = System.currentTimeMillis();
      String username = "user"+now;
      String email = "user1@localhost.localdomain";
      app.registration().start(username, email);
      List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
      String confirmationLink = findConfirmationLink(mailMessages, email);
      app.registration().finish(confirmationLink, "password");
      Assert.assertTrue(app.newSession().login(username, "password"));

   }

   @Test
   public  void testPwdReset() throws IOException, InterruptedException, MessagingException {
      HashMap<String, String> user = getUserFromBase();
      app.registration().goToUsersPage();
      app.registration().resetPassword(user);
      List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
      String confirmationLink = findConfirmationLink(mailMessages, user.get("email"));
      app.registration().finish(confirmationLink, "password1");
      Assert.assertTrue(app.newSession().login(user.get("username"), "password1"));
   }

   public HashMap<String, String> getUserFromBase() {
      Connection conn = null;
      HashMap<String, String> map = new HashMap<>();
      try {
         conn =
                 DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?" +
                         "user=root&password=&serverTimezone=UTC");
         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery("select * from mantis_user_table");
         String username = "";
         while (rs.next()) {
           username =  rs.getString("username");
           if(!username.equals("administrator")) {
              map.put("username", username);
              map.put("email", rs.getString("email"));
              break;
           }
         }

      } catch (SQLException ex) {
         // handle any errors
         System.out.println("SQLException: " + ex.getMessage());
         System.out.println("SQLState: " + ex.getSQLState());
         System.out.println("VendorError: " + ex.getErrorCode());
      }
      return map;
   }




   private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
      MailMessage mailMessage = mailMessages.stream().filter((m)->(m.to.equals(email))).findFirst().get();
      VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
      return  regex.getText(mailMessage.text);
   }

   @AfterMethod (alwaysRun = true)
   public void stopMailServer() {
      app.mail().stop();
   }

}
