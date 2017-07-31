package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

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
      String email = "user1@localhost.localdomain";
      app.registration().start("user6", email);
      List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
      String confirmationLink = findConfirmationLink(mailMessages, email);
      app.registration().finish(confirmationLink, "password");
      Assert.assertTrue(app.newSession().login("user1", "password"));

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
