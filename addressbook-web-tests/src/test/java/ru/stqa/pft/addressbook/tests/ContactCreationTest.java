package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.*;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest  extends TestBase{

    @DataProvider
    public Iterator<Object[]>  ContactsFromXML() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
        String xmlLine = "";
        String line = reader.readLine();
        while (line != null) {
           xmlLine+= line;
            line = reader.readLine();
        }
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        List<ContactData> contacts = (List<ContactData>)xStream.fromXML(xmlLine);
        int a = 0;
        return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();

    }


    @Test (dataProvider = "ContactsFromXML")
    public void ContactCreationTest(ContactData contact) {
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

        app.goTo().gotoHomePage();
        Contacts before = app.contact().all();
//        File photo = new File("src/test/resources/sample.png");
//        ContactData newContact = new ContactData().withFirstName("firstName").withLastName("lastName").withAddress("Address").withHomePhone("111-11-11")
//                .withPhoto(photo);
//        app.contact().createContact(newContact);
        app.contact().createContact(contact);
        Contacts after = app.contact().all();
        int a = 0;
        assertThat(after.size(), equalTo(before.size()+1));
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test (enabled = false)
    public  void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/sample.png");
        System.out.println(photo.exists());
        System.out.println(photo.getAbsolutePath());



    }

}
