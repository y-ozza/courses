package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

/**
 * Created by shurik on 05.08.2017.
 */
public class SoapTests {

   @Test
   public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
      MantisConnectPortType mc = new MantisConnectLocator()
              .getMantisConnectPort(new URL("http://localhost:8081/mantisbt-1.2.19/api/soap/mantisconnect.php"));
      ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
      System.out.println(projects.length);
      for (ProjectData project:projects) {
         System.out.println(project.getName());
      }



   }
}
