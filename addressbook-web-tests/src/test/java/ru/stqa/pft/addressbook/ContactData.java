package ru.stqa.pft.addressbook;

public class ContactData {
   private final String firstName;
   private final String lastName;
   private final String address;
   private final String homePhone;

   public ContactData(String firstName, String lastName, String address, String homePhone) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.address = address;
      this.homePhone = homePhone;
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
}
