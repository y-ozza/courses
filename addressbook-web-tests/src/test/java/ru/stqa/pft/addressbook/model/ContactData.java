package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
   @XStreamOmitField
   @Id
   @Column(name = "id")
   private int id;
   @Expose
   @Column(name = "firstname")
   private  String firstName;
   @Expose
   @Column(name = "lastname")
   private  String lastName;

   @Column(name = "address")
   @Type(type = "text")
   private  String address;
   @Expose
   @Column(name = "home")
   @Type(type = "text")
   private  String home;
   @Column(name = "mobile")
   @Type(type = "text")
   private  String mobile;
   @Column(name = "work")
   @Type(type = "text")
   private  String work;
   @Transient
   private  String allPhones;
   @Expose
   @Column(name = "email")
   @Type(type = "text")
   private  String email;
   @Column(name = "email2")
   @Type(type = "text")
   private  String email2;
   @Column(name = "email3")
   @Type(type = "text")
   private  String email3;
   @Transient
   private  String allEmails;
   @Column(name = "photo")
   @Type(type = "text")
   private String photo;
   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "address_in_groups",
           joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
   private Set<GroupData> groups = new HashSet<GroupData>();


   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      ContactData that = (ContactData) o;

      if (id != that.id) return false;
      if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
      if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
      if (home != null ? !home.equals(that.home) : that.home != null) return false;
      if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
      return email != null ? email.equals(that.email) : that.email == null;
   }

   @Override
   public int hashCode() {
      int result = id;
      result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
      result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
      result = 31 * result + (home != null ? home.hashCode() : 0);
      result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
      result = 31 * result + (email != null ? email.hashCode() : 0);
      return result;
   }

   @Override
   public String toString() {
      return "ContactData{" +
              "id=" + id +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              '}';
   }

   public ContactData(String firstName, String lastName, String address, String home, String mobile, String work, String group) {
      this.id = Integer.MAX_VALUE;
      this.firstName = firstName;
      this.lastName = lastName;
      this.address = address;
      this.home = home;
      this.mobile = mobile;
      this.work = work;
   }
   public ContactData(int id, String firstName, String lastName, String address, String home,  String mobile, String work, String group) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.address = address;
      this.home = home;
      this.mobile = mobile;
      this.work = work;
   }

   public ContactData() {

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

   public File getPhoto() {
      return new File(photo);
   }

   public ContactData withPhoto(File photo) {
      this.photo = photo.getPath();
      return  this;
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

   public Groups getGroups() {
      return new Groups(groups);
   }

   //   public ContactData withGroup(String group) {
//      this.group = group;
//      return this;
//   }
}
