package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTest  extends TestBase{


    @Test
    public void ContactCreationTest() {
//        app.getContactHelper().returnToContactPage();
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData newContact = new ContactData("firstName", "lastName", "Address", "111-11-11", "test1");
        app.getContactHelper().createContact(newContact);
        List<ContactData> after = app.getContactHelper().getContactList();
        before.add(newContact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }

}
