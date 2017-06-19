package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by shurik on 19.06.2017.
 */
public class ContactHelper extends  HelperBase {
   public ContactHelper(FirefoxDriver wd) {
      super(wd);
   }

   public void submitContactCreation() {
      click(By.name("submit"));
   }

   public void fillContacrForm(ContactData contactData) {
      type(By.name("firstname"),contactData.getFirstName()  );
      type(By.name("lastname"),contactData.getLastName()  );
      type(By.name("address"),contactData.getHomePhone());
      type(By.name("home"),contactData.getAddress());
   }

   public void initContactCreation() {
      click(By.linkText("add new"));
   }

}
