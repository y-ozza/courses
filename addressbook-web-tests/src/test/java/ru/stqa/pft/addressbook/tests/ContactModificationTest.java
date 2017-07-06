package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by shurik on 20.06.2017.
 */
public class ContactModificationTest extends TestBase {
   @Test (enabled = false)
   public void ContactModificationTest() {
      app.goTo().gotoHomePage();

      if (!app.getContactHelper().isThereAContact()) {
         app.getContactHelper().createContact(new ContactData(null, null, null, null, null));
      }
      List<ContactData> before = app.getContactHelper().getContactList();
      int rId = before.get(0).getId();
      app.getContactHelper().initContactModification();
      app.getContactHelper().fillContacrForm(new ContactData("firstName_n", "lastName_n", "Address_n", "111-11-11", null), false);
      app.getContactHelper().submitContactModification();
      app.goTo().gotoHomePage();
      List<ContactData> after = app.getContactHelper().getContactList();
      before.remove(0);
      before.add(new ContactData(rId, "firstName_n", "lastName_n", "Address_n", "111-11-11", null));
      Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(before, after);

   }
}
