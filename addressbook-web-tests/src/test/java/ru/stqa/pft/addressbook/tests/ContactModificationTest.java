package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by shurik on 20.06.2017.
 */
public class ContactModificationTest extends TestBase {
   @Test
   public void ContactModificationTest() {
      app.getNavigationHelper().gotoHomePage();
      if (!app.getContactHelper().isThereAContact()) {
         app.getContactHelper().createContact(new ContactData(null, null, null, null, null));
      }
      app.getContactHelper().initContactModification();
      app.getContactHelper().fillContacrForm(new ContactData("test11_1", "test2222", "test3333", "111-11-11", null), false);
      app.getContactHelper().submitContactModification();
      app.getNavigationHelper().returnBack();
   }
}
