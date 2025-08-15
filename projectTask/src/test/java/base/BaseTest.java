package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.CommonUtils;

/**
 * responsible for Initialize, Manage and Quit WebDriver which run before and after test class
 */

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void tearDown(ITestResult result) throws InterruptedException {
        if (ITestResult.FAILURE == result.getStatus()) {
            CommonUtils.captureFullPageScreenshot(driver, result.getName());
        }

        if (driver != null) {
            driver.quit();
        }
    }

}