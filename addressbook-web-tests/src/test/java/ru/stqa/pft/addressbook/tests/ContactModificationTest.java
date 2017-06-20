package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by shurik on 20.06.2017.
 */
public class ContactModificationTest extends TestBase {
   @Test
   public void ContactCreationTest() {
      app.getContactHelper().initContactModification();
      app.getContactHelper().fillContacrForm(new ContactData("test111", "test2222", "test3333", "111-11-11"));
      app.getContactHelper().submitContactModification();
      app.getNavigationHelper().returnBack();
   }
}
