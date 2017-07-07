package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;


public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().GroupPage();
        Set<GroupData> before = app.group().all();
        GroupData group = new GroupData().withName("test2");
        app.group().create(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size()+1);

        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(group);
        for(GroupData g: before) {
            System.out.println(g.toString() + ": " + g.hashCode());
            System.out.println(after.contains(g));
        }
        for(GroupData g: after) {
            System.out.println(g.toString() + ": " + g.hashCode());
            System.out.println(before.contains(g));
        }
        System.out.println(before.equals(after));
        System.out.println(before.containsAll(after));
        Assert.assertEquals(before, after);

//        int maxId_old = 0;
//        for (GroupData g: after) {
//            if(g.getId() > maxId_old) {
//                maxId_old = g.getId();
//            }
//        }

//        Comparator<? super GroupData> byId = (Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
//        int maxId = after.stream().max( (Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
//        group.setId(maxId);

//        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//        before.sort(byId);
//        after.sort(byId);
//        Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));

    }

}
