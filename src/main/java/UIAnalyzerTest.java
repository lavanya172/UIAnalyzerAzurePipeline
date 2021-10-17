import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class UIAnalyzerTest {

    @Test
   public void testUICompliance(){
       ChromeOptions opt = new ChromeOptions();
       //loading extension
       opt.addExtensions(new File("./resources/src.crx"));

       //chrome driver
       System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");

       //create chrome
       ChromeDriver driver  = new ChromeDriver(opt);

       String path = System.getProperty("user.dir");
       System.out.println("path"+path);

       //open test page url
       driver.get(path+"/src/main/java/PageTest.html");

       //wait until compliance visible
       WebDriverWait wait = new WebDriverWait(driver, 20);
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myMeter")));

       //get compliance value
       String actualValue = driver.findElement(By.id("myMeter")).getAttribute("value");

       System.out.println(actualValue);
       Assert.assertEquals(actualValue, "100", "Reason: UI is not 100% compliance with the specification. \nRecommendation: \n\t\t UI Analyzer Test Heading \n\t\t fontSize: 24px; \n\n");
//       if(!actualValue.equals("100")) {
//           System.out.println("Recommendation: \n UI Analyzer Test Heading \n fontSize: 24px; ");
//       }
   }
}
