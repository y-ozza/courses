package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;
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


}
