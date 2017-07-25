package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


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
      if (app.db().groups().isEmpty()) {
         app.goTo().GroupPage();
         app.group().create(new GroupData().withName("gName").withFooter("gFooter").withHeader("gHeader"));
      }
      Groups beforeGroups = app.db().groups();
      ContactData modifiedContact = beforeContacts.iterator().next();
      GroupData groupToSelect = null;
      Groups addedGroupsBefore = modifiedContact.getGroups();
      if (addedGroupsBefore.size() == 0) {
         groupToSelect = beforeGroups.iterator().next();
      } else {
         for (GroupData currentGroup : beforeGroups) {
            if(!addedGroupsBefore.contains(currentGroup)) {
               groupToSelect = currentGroup;
               break;
            }
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
      Groups addedGroupsAfter = null;
      for (ContactData c : modifiedContactFromBase) { //здесь один контакт
         addedGroupsAfter = c.getGroups();
         Boolean groupIsAdded = false;
         for (GroupData g : addedGroupsAfter) {
            if (g.getName().equals(groupToSelect.getName())) { //потому что при добавлении в web-интерфейсе группа выбирается ПО ИМЕНИ!!!!
               groupIsAdded = true;
               break;
            }
         }
         Assert.assertTrue(groupIsAdded);

      }

   }

}