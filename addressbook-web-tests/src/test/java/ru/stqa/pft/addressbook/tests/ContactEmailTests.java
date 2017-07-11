package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by shurik on 11.07.2017.
 */
public class ContactEmailTests extends  TestBase{
   @Test
   public  void testEmails() {

      app.goTo().gotoHomePage();
      ContactData contact = app.contact().all().iterator().next();
      ContactData contactFromEditForm = app.contact().infoFromEditForm(contact);
      int a = 1;
      assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactFromEditForm)));
   }

   private String mergeEmails(ContactData contact) {

      return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
              .stream().filter((s) -> !s.equals(""))
              .collect(Collectors.joining("\n"));

   }
}
