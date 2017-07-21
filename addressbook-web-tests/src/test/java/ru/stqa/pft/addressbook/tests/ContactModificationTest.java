package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by shurik on 20.06.2017.
 */
public class ContactModificationTest extends TestBase {
   @Test (enabled = true)
   public void ContactModificationTest() {
      app.goTo().gotoHomePage();
      Contacts before = app.db().contacts();

      if (before.size()==0) { //!app.contact().isThereAContact()) {
         app.contact().createContact(new ContactData().withFirstName("firstName").withLastName("lastName").withEmail("Email").withHomePhone("111-11-11").withMobilePhone("222"));
      }
  //    int rId = before.get(0).getId();
      ContactData modifiedContact = before.iterator().next();
      ContactData newContact = new ContactData().withId(modifiedContact.getId()).withFirstName("firstName").withLastName("lastName").withEmail("Email")
              .withHomePhone("111-11-11").withMobilePhone("222");
      app.contact().initContactModificationById(modifiedContact.getId()); //!!
      app.contact().fillContacrForm(newContact, false);
      app.contact().submitContactModification();
      app.goTo().gotoHomePage();
      Contacts after = app.db().contacts();
      assertThat(after, equalTo(before.without(modifiedContact).withAdded(newContact)));



//      before.remove(0);
//      before.add(new ContactData(rId, "firstName_n", "lastName_n", "Address_n", "111-11-11", null));
//      Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//      before.sort(byId);
//      after.sort(byId);
//      Assert.assertEquals(before, after);

   }
}
