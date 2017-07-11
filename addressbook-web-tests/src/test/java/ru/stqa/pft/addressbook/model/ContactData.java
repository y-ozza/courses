package ru.stqa.pft.addressbook.model;

public class ContactData {
   private int id;
   private  String firstName;
   private  String lastName;

   private  String address;
   private  String home;
   private  String mobile;
   private  String work;
   private  String allPhones;
   private  String email;
   private  String email2;
   private  String email3;
   private  String allEmails;
   private  String group;

   public ContactData(String firstName, String lastName, String address, String home, String mobile, String work, String group) {
      this.id = Integer.MAX_VALUE;
      this.firstName = firstName;
      this.lastName = lastName;
      this.address = address;
      this.home = home;
      this.mobile = mobile;
      this.work = work;
      this.group = group;
   }
   public ContactData(int id, String firstName, String lastName, String address, String home,  String mobile, String work, String group) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.address = address;
      this.home = home;
      this.mobile = mobile;
      this.work = work;
      this.group = group;
   }

   public ContactData() {

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

   public String getHome() {
      return home;
   }

   public String getMobile() {
      return mobile;
   }

   public String getWork() {
      return work;
   }

   public String getGroup() {
      return group;
   }

   public String getAllPhones() {
      return allPhones;
   }

   public String getAllEmails() {
      return allEmails;
   }

   public String getEmail() {
      return email;
   }

   public ContactData withEmail(String email) {
      this.email = email;
      return  this;
   }

   public String getEmail2() {
      return email2;
   }

   public ContactData withEmail2(String email2) {
      this.email2 = email2;
      return  this;
   }

   public String getEmail3() {
      return email3;
   }

   public ContactData withEmail3(String email3) {
      this.email3 = email3;
      return this;
   }



   public ContactData withId(int id) {
      this.id = id;
      return this;
   }

   public ContactData withFirstName(String firstName) {
      this.firstName = firstName;
      return this;
   }

   public ContactData withLastName(String lastName) {
      this.lastName = lastName;
      return this;
   }

   public ContactData withHomePhone(String homePhone) {
      this.home = homePhone;
      return this;
   }
   public ContactData withMobilePhone(String mobilePhone) {
      this.mobile = mobilePhone;
      return this;
   }
   public ContactData withWorkPhone(String workPhone) {
      this.work = workPhone;
      return this;
   }

   public ContactData withAddress(String address) {
      this.address = address;
      return this;
   }

   public ContactData withAllPhones(String allPhones) {
      this.allPhones = allPhones;
      return this;
   }

   public ContactData withAllEmails(String allEmails) {
      this.allEmails = allEmails;
      return  this;
   }



   public ContactData withGroup(String group) {
      this.group = group;
      return this;
   }
}
