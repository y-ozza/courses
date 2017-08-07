package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by shurik on 19.06.2017.
 */
public class TestBase {

   protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

   @BeforeSuite
   public void setUp() throws Exception {
      app.init();
 //     app.ftp().upload(new File("src/test/resources/config_int.php"), "config_int.php","config_int.php.bak" );
   }

   @AfterSuite
   public void tearDown() throws IOException {
//      app.ftp().restore("config_int.php","config_int.php.bak" );
      app.stop();
   }

   public boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
      String status = app.soap().getIssueStatus(issueId);
      if (status.equals("resolved")) return  false;
      else return  true;

   }

   public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
      if (isIssueOpen(issueId)) {
         throw new SkipException("Ignored because of issue " + issueId);
      }
   }


}
