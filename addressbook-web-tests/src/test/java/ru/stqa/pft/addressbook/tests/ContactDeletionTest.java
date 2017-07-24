package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTest extends TestBase {

    @Test (enabled = true)
    public void ContactDeletionTest() {
        app.goTo().gotoHomePage();
        Contacts before = app.db().contacts();
        if (before.size()==0) {///!app.contact().isThereAContact()) {
            app.contact().createContact(new ContactData().withFirstName("firstName").withLastName("lastName").withAddress("Address").withHomePhone("111-11-11"));
        }
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedContact)));
        verifyContactListInUI();



//        app.contact().selectContact();
//        app.contact().deleteSelectedContact();
//        app.goTo().gotoHomePage();


//        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//        before.sort(byId);
//        after.sort(byId);
//        before.remove(before.size() - 1);
//        Assert.assertEquals(before, after);
    }


}
