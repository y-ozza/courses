package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

import java.sql.*;

/**
 * Created by shurik on 20.07.2017.
 */
public class dbConnectionTest {

   @Test
   public void testDbConnection()
   {
      Connection conn = null;

      try {
         conn =
                 DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?" +
                         "user=root&password=&serverTimezone=UTC");
         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery("select * from group_list");
         int a = 0;
         while (rs.next()) {
            a=0;
         }

      } catch (SQLException ex) {
         // handle any errors
         System.out.println("SQLException: " + ex.getMessage());
         System.out.println("SQLState: " + ex.getSQLState());
         System.out.println("VendorError: " + ex.getErrorCode());
      }
   }
}
