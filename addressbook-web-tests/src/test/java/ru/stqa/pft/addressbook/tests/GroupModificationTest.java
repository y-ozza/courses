package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by shurik on 20.06.2017.
 */
public class GroupModificationTest extends  TestBase{

   @BeforeMethod

   public  void  ensurePreconditions() {
      //старая версия - проверка через web
//      app.goTo().GroupPage();
//      if (app.group().all().size()==0) {
//         app.group().create(new GroupData().withName("test1"));
//      }


      if (app.db().groups().size()==0) {
        app.goTo().GroupPage();
         app.group().create(new GroupData().withName("test1"));
      }

   }

   @Test
   public void testGroupModification() {
      Groups before = app.db().groups();
      GroupData modifiedGroup = before.iterator().next();
      GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test11").withHeader("test22").withFooter("test33");
      app.goTo().GroupPage();
      app.group().modify(group);
      assertThat(app.group().count(), equalTo(before.size()));
      Groups after = app.db().groups();
      assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

      verifyGroupListInUI();
   }

//      Groups before = app.group().all();  через web - медленно
//      assertThat(app.group().count(), equalTo(before.size()));
//      Groups after = app.group().all();
   //     Assert.assertEquals(before.size(), after.size()); замена - хеширование

}
