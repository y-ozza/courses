package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import ru.stqa.pft.addressbook.appmanager.SessionHelper;
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
