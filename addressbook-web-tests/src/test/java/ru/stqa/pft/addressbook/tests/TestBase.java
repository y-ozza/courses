package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by shurik on 19.06.2017.
 */
public class TestBase {

   protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

   @BeforeSuite
   public void setUp() throws Exception {
      app.init();
   }

   @AfterSuite
   public void tearDown() {
      app.stop();
   }

   public void verifyGroupListInUI() {

      if (Boolean.getBoolean("verifyUI")) {
         Groups dbGroups = app.db().groups();
         Groups UIGroups = app.group().all();

         assertThat(UIGroups, equalTo(dbGroups.stream().map((g) -> new GroupData().withId(g.getId())
                 .withName(g.getName())).collect(Collectors.toSet())));


      }
   }
   public void verifyContactListInUI() {

      if (Boolean.getBoolean("verifyUI")) {
         Contacts dbContacts = app.db().contacts();
         Contacts UIContacts = app.contact().all();

         assertThat(UIContacts, equalTo(dbContacts.stream().map((g) -> new ContactData().withId(g.getId())
                 .withFirstName(g.getFirstName()).withLastName(g.getLastName())).collect(Collectors.toSet())));


      }
   }

}
