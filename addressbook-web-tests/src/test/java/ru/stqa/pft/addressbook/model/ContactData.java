package ru.stqa.pft.addressbook.model;

public class ContactData {
   private final String firstName;
   private final String lastName;
   private final String address;
   private final String homePhone;
   private final String group;

   public ContactData(String firstName, String lastName, String address, String homePhone, String group) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.address = address;
      this.homePhone = homePhone;
      this.group = group;
   }

   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public String getAddress() {
      return address;
   }

   public String getHomePhone() {
      return homePhone;
   }

   public String getGroup() {
      return group;
   }
}
