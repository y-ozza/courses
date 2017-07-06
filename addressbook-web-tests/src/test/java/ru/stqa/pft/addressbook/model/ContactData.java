package ru.stqa.pft.addressbook.model;

public class ContactData {
   private int id;
   private final String firstName;
   private final String lastName;
   private final String address;
   private final String homePhone;
   private final String group;


   public ContactData(String firstName, String lastName, String address, String homePhone, String group) {
      this.id = Integer.MAX_VALUE;
      this.firstName = firstName;
      this.lastName = lastName;
      this.address = address;
      this.homePhone = homePhone;
      this.group = group;
   }
   public ContactData(int id, String firstName, String lastName, String address, String homePhone, String group) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.address = address;
      this.homePhone = homePhone;
      this.group = group;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      ContactData that = (ContactData) o;

      if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
      return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
   }

   @Override
   public int hashCode() {
      int result = firstName != null ? firstName.hashCode() : 0;
      result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
      return result;
   }

   public int getId() {
      return id;
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
