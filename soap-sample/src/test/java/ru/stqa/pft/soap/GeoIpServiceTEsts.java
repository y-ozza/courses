package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by shurik on 03.08.2017.
 */
public class GeoIpServiceTEsts {

   @Test
   public void testMyIp() {
      GeoIP geoIp = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("73.199.73.252");
      Assert.assertEquals(geoIp.getCountryCode(), "USA");
   }
}
