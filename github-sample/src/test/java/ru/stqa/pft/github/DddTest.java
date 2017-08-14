package ru.stqa.pft.github;

import org.testng.annotations.Test;
import ru.dadata.api.DaData;
import ru.dadata.api.entity.*;

/**
 * Created by shurik on 08.08.2017.
 */
public class DddTest {

   @Test
   public  void testDadata(){
      DaData daData = new DaData("f275b717c307a5f6f10007d745587e008982ca99", "58c651ea23084e60d00dfd2ad5d44826ee533aff");
      Address address = daData.cleanAddress("мск сухонска 11/-89");
      Phone phone = daData.cleanPhone("тел 7165219 доб139");
      Passport passport = daData.cleanPassport("4509 235857");
      Name name = daData.cleanName("Срегей владимерович иванов");
      Email email = daData.cleanEmail("serega@yandex/ru");
      Birthdate birthdate = daData.cleanBirthdate("24/3/12");
      Vehicle vehicle = daData.cleanVehicle("форд фокус");

      int a = 0;

   }

}
