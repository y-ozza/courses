package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by shurik on 20.06.2017.
 */
public class GroupModificationTest extends  TestBase{

   @Test
   public void testGroupModification() {
      app.getNavigationHelper().gotoGroupPage();
      app.getGroupHelper().initGroupModification();
      app.getGroupHelper().fillGroupForm(new GroupData("test11", "test22", "test33"));
      app.getGroupHelper().submitGroupModification();
      app.getGroupHelper().returnToGroupPage();
   }


}
