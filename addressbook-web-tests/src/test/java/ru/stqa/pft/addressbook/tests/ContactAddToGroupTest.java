package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


/**
 * Created by shurik on 24.07.2017.
 */
public class ContactAddToGroupTest extends TestBase {

   @Test
   public void testContactAddToGroup() {
      Contacts beforeContacts = app.db().contacts();
      if (beforeContacts.size() == 0) {
         app.goTo().gotoHomePage();
         app.contact().createContact(new ContactData().withFirstName("firstName").withLastName("lastName").withAddress("Address").withHomePhone("111-11-11"));
      }
      Groups beforeGroups = app.db().groups();
      if (beforeGroups.size() == 0) {
         app.goTo().GroupPage();
         app.group().create(new GroupData().withName("gName").withFooter("gFooter").withHeader("gHeader"));
      }
      ContactData modifiedContact = beforeContacts.iterator().next();
      GroupData groupToSelect = null;
      Groups addedGroups = modifiedContact.getGroups();
      if (addedGroups.size() == 0) {
         groupToSelect = beforeGroups.iterator().next();
      } else {
         for (GroupData currentGroup : beforeGroups) {
            groupToSelect = currentGroup;
            for (GroupData addedGroup : addedGroups) {
               if (groupToSelect.equals(addedGroup)) {
                  groupToSelect = null;
                  break;
               }
            }
            if (groupToSelect != null) break;
         }
      }
      if (groupToSelect == null) {
         GroupData oneMoreGroup = new GroupData().withName("gName").withFooter("gFooter").withHeader("gHeader");
         app.goTo().GroupPage();
         app.group().create(oneMoreGroup);
         groupToSelect = oneMoreGroup;
      }

      app.goTo().gotoHomePage();
      app.contact().selectContactById(modifiedContact.getId());
      app.contact().selectGroup(groupToSelect);
      app.contact().addToSelectedGroup();

//    проверка
      Contacts modifiedContactFromBase = app.db().getContactByID(modifiedContact.getId());
      for (ContactData c : modifiedContactFromBase) {
         addedGroups = c.getGroups();
         Boolean groupIsAdded = false;
         for (GroupData g : addedGroups) {
            if (g.getName().equals(groupToSelect.getName()))
               groupIsAdded = true;  //потому что в тесте группа выбирается ПО ИМЕНИ!!!!
         }
         System.out.println("Группа добавлена? " + groupIsAdded + "!!!");


      }


   }

}