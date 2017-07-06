package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by shurik on 20.06.2017.
 */
public class GroupModificationTest extends  TestBase{
   @BeforeMethod
   public  void  ensurePreconditions() {
      app.goTo().GroupPage();
      if (app.group().list().size()==0) {
         app.group().create(new GroupData().withName("test1"));
      }

   }

   @Test
   public void testGroupModification() {
      List<GroupData> before = app.group().list();
      int lastIndex = before.size() - 1;
      int newId = before.get(lastIndex).getId();
      GroupData group = new GroupData().withId(newId).withName("test11").withHeader("test22").withFooter("test33");
      app.group().modify(lastIndex, group);
      List<GroupData> after = app.group().list();
      Assert.assertEquals(before.size(), after.size());

      before.remove(lastIndex);
      before.add(group);
      Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
      before.sort(byId);
      after.sort(byId);
//      Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
      Assert.assertEquals(before, after);


   }



}
