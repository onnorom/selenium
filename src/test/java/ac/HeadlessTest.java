package ac;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;


public class HeadlessTest {
    Logger log = LoggerFactory.getLogger(HeadlessTest.class);

    @Test
    public void HeadlessChromeDriverTest() throws IOException {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.navigate().to("https://www.avenuecode.com/");

        String pageTitle = driver.getTitle();
        log.info("Page opened: {}", pageTitle);

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //copying the file into /screenshots directory
        FileUtils.copyFile(scrFile, new File("output/screenshots/homepage.png"));

        //Assert.assertTrue(pageTitle.contains("Trusted Advisors for E-Commerce | Avenue Code"));
        Assert.assertTrue(pageTitle.contains("Avenue Code  | Trusted Advisors for Enterprise IT Consulting"));
        log.info("Quitting driver");
        driver.quit();
    }
}
