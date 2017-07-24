package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by shurik on 19.06.2017.
 */
public class ContactHelper extends  HelperBase {
   public ContactHelper(WebDriver wd) {
      super(wd);
   }

   public void submitContactCreation() {
      click(By.name("submit"));
   }

   public void fillContacrForm(ContactData contactData, boolean creation) {
      type(By.name("firstname"),contactData.getFirstName()  );
      type(By.name("lastname"),contactData.getLastName()  );
      type(By.name("email"),contactData.getEmail());
      type(By.name("home"),contactData.getHome());
      type(By.name("mobile"),contactData.getMobile());
//      attach(By.name("photo"), contactData.getPhoto());
      if (creation) {
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText("test1");
      }
      else {
         Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
   }

   public void initContactCreation() {
      click(By.linkText("add new"));
   }


   public void deleteSelectedContact() {
      wd.findElement(By.xpath("//div[@id='content']/form[2]/div[2]/input")).click();
      wd.switchTo().alert().accept();
   }

   public void delete(ContactData contact) {
      selectContactById(contact.getId());
      deleteSelectedContact();
      returnToContactPage();
   }

   public void selectContact() {   //выбор первого контакта на странице
      if (!wd.findElement(By.name("selected[]")).isSelected()) {
         click(By.name("selected[]"));
      }

   }

   public void selectContactById(int id) {
      wd.findElement(By.cssSelector("input[value = '"+ id + "']")).click();
   }


   public void initContactModification() {
      wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img")).click();
   }

   public void initContactModificationById(int id) {

      //Building locators!!!
      wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr/td[8]/a[@href=\"edit.php?id="+id+"\"]")).click();

//      2-nd way
//      WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value = '%s']", id)));
//      WebElement row = wd.findElement(By.xpath("./../.."));
//      List <WebElement> cells = row.findElements(By.tagName("td"));
//      cells.get(7).findElement(By.tagName("a")).click();

//      3-rd way
//      wd.findElement(By.xpath(String.format("input[value = '%s']/../../td[8]/a",id))).click();

//      4-th way
//      wd.findElement(By.xpath(String.format("tr/.//[input[value = '%s']]/td[8]/a", id))).click();

//      5-th way
//      wd.findElement(By.cssSelector(String.format("a[href ='edit.php?id=%s']", id))).click();

   }

   public void submitContactModification() {
      click(By.name("update"));

   }

   public boolean isThereAContact() {
      return isElementPresent(By.name("selected[]"));
   }


   public void createContact(ContactData contactData) {
      initContactCreation();
      fillContacrForm(contactData, true);
      submitContactCreation();
      returnToContactPage();
      getContactList();

   }

   public void returnToContactPage() {
      click(By.linkText("home"));
   }

   public List<ContactData> getContactList() {
      List<ContactData> contacts = new ArrayList<ContactData>();
      List<WebElement> elements = wd.findElements(By.name("entry"));
      for (WebElement element:elements) {
         List<WebElement> cells = element.findElements(By.tagName("td"));
         int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
         contacts.add(new ContactData(id,  cells.get(2).getText(), cells.get(1).getText(), null, null, null, null, null));
      }
      return  contacts;

   }

   public Contacts all() {
      Contacts contacts = new Contacts();
      List<WebElement> elements = wd.findElements(By.name("entry"));
      for (WebElement element:elements) {
         List<WebElement> cells = element.findElements(By.tagName("td"));
         int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
         String allPhones = cells.get(5).getText();
         String allEmails = cells.get(4).getText();
         String address  = cells.get(3).getText();
         String[] phones = allPhones.split("\n");// это перевод строки!
         contacts.add(new ContactData().withId(id).withFirstName( cells.get(2).getText()).withLastName(cells.get(1).getText()).withAddress(address)
                 .withAllPhones(allPhones).withAllEmails(allEmails));
         int a = 0;
      }
      return  contacts;

   }

   public ContactData infoFromEditForm(ContactData contact) {

      initContactModificationById(contact.getId());
      String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
      String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
      String home = wd.findElement(By.name("home")).getAttribute("value");
      String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
      String work = wd.findElement(By.name("work")).getAttribute("value");
      String email = wd.findElement(By.name("email")).getAttribute("value");
      String email2 = wd.findElement(By.name("email2")).getAttribute("value");
      String email3 = wd.findElement(By.name("email3")).getAttribute("value");
      String address = wd.findElement(By.name("address")).getAttribute("value");
      wd.navigate().back();;
      return new ContactData().withFirstName(firstName).withLastName(lastName).withHomePhone(home).withWorkPhone(work).withMobilePhone(mobile)
              .withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);

   }

   public void selectGroup(GroupData groupToSelect) {
      new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(groupToSelect.getName());

   }

   public void addToSelectedGroup() {
      click(By.name("add"));
   }
}
