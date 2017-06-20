package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest  extends TestBase{


    @Test
    public void ContactCreationTest() {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContacrForm(new ContactData("test1", "test2", "test3", "111-11-11"));
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().returnBack();
    }

}
