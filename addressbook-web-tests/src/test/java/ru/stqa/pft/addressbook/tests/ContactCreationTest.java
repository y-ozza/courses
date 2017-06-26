package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest  extends TestBase{


    @Test
    public void ContactCreationTest() {
        app.getContactHelper().createContact(new ContactData("test1_new", "test2", "test3", "111-11-11", "test1"));
    }

}
