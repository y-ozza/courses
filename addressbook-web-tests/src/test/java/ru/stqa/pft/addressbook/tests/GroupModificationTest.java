package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by shurik on 20.06.2017.
 */
public class GroupModificationTest extends  TestBase{

   @Test
   public void testGroupModification() {
      app.getNavigationHelper().gotoGroupPage();
      if (!app.getGroupHelper().isThereAGroup()) {
         app.getGroupHelper().createGroup(new GroupData("test1", null, null));
      }
      List<GroupData> before = app.getGroupHelper().getGroupList();
      app.getGroupHelper().selectGroup(before.size() - 1);
      app.getGroupHelper().initGroupModification();
      int newId = before.get(before.size() - 1).getId();
      GroupData group = new GroupData(newId,"test11", "test22", "test33");
      app.getGroupHelper().fillGroupForm(group);
      app.getGroupHelper().submitGroupModification();
      app.getGroupHelper().returnToGroupPage();
      List<GroupData> after = app.getGroupHelper().getGroupList();
      Assert.assertEquals(before.size(), after.size());

      before.remove(before.size() - 1);
      before.add(group);
      Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
      before.sort(byId);
      after.sort(byId);
//      Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
      Assert.assertEquals(before, after);


   }


}
