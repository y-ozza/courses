package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by shurik on 10.07.2017.
 */
public class ContactPhoneTests extends  TestBase{
   @Test

   public void testContactPhones() {
      app.goTo().gotoHomePage();
      ContactData contact = app.contact().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

//      не годится, потому что не все телефоны могут быть заполнены
//      assertThat(contact.getHome(), equalTo(app.contact().cleaned(contactInfoFromEditForm.getHome())));
//      assertThat(contact.getMobile(), equalTo(app.contact().cleaned(contactInfoFromEditForm.getMobile())));
//      assertThat(contact.getWork(), equalTo(app.contact().cleaned(contactInfoFromEditForm.getWork())));

      assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));


   }

   public  static String cleaned(String phone) {
      return phone.replaceAll("\\s", "").replaceAll("[-()]","");

   }

   public String mergePhones(ContactData contact) {
      return Arrays.asList(contact.getHome(), contact.getMobile(), contact.getWork())
              .stream().filter((s) -> !s.equals(""))
              .map(ContactPhoneTests::cleaned)
              .collect(Collectors.joining("\n"));

   }


}
