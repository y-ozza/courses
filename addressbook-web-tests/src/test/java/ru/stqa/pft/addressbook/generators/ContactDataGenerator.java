package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import org.junit.runners.Parameterized;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shurik on 16.07.2017.
 */
public class ContactDataGenerator {
@Parameter(names = "-c", description = "People count")
   int count;
@Parameter(names = "-f", description = "Target file")
   String file;
@Parameter(names = "-d", description = "Data format")
   String format;

public  static  void  main(String[] args) throws IOException {
   ContactDataGenerator generator = new ContactDataGenerator();
   JCommander jCommander = new JCommander(generator);
   try {
      jCommander.parse(args);
   }
   catch (ParameterException ex) {
      jCommander.usage();
      return;
      
   }
   generator.run();
}

   private void run() throws IOException {

   List<ContactData> list = generateContacts();
   if (format.equals("xml")) {
      saveAsXML(list, new File(file));
   }
   else System.out.println("Bad format");

   }

   private void saveAsXML(List<ContactData> list, File file) throws IOException {
   XStream xstream = new XStream();
   xstream.processAnnotations(ContactData.class);
   String xmlString = xstream.toXML(list);
   Writer writer = new FileWriter(file);
   writer.write(xmlString);
   writer.close();


   }

   private List<ContactData> generateContacts() {
      List<ContactData> list = new ArrayList<ContactData>();
      for (int i = 0; i<count; i++) {
         list.add(new ContactData().withFirstName("fname"+i).withLastName("lname"+i).withEmail("email@"+i).withHomePhone("11"+i).withMobilePhone("22"+i));
      }
      return  list;
   }

}
