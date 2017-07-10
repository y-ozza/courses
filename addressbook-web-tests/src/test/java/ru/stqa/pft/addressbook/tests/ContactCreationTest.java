package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest  extends TestBase{


    @Test (enabled = true)
    public void ContactCreationTest() {
//        List<ContactData> before = app.getContactHelper().getContactList();
//        ContactData newContact = new ContactData("firstName", "lastName", "Address", "111-11-11", "test1");
//        app.getContactHelper().createContact(newContact);
//        List<ContactData> after = app.getContactHelper().getContactList();
//        before.add(newContact);
//        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//        before.sort(byId);
//        after.sort(byId);
//        Assert.assertEquals(before, after);
//

        Contacts before = app.contact().all();
        ContactData newContact = new ContactData().withFirstName("firstName").withLastName("lastName").withAddress("Address").withHomePhone("111-11-11");
        app.contact().createContact(newContact);
        Contacts after = app.contact().all();
        int a = 0;
        assertThat(after.size(), equalTo(before.size()+1));
        assertThat(after, equalTo(before.withAdded(newContact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
