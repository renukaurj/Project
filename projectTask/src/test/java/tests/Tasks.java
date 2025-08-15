package tests;

import base.BaseTest;
import config.YAMLConfig;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pageObject.ColorChangePage;
import pageObject.SearchPage;
import pageObject.SpinnerTaskPage4;
import utils.CommonUtils;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class Tasks extends BaseTest {

    private static final Logger logger = Logger.getLogger(Tasks.class.getName());
    YAMLConfig config = new YAMLConfig();
    static String url;


    @Test(priority = 1)
    public void brokenLinksAndExportToFile() {
        url = config.getUrltask1();
        logger.info("brokenLinksAndExportToFile URL = " + url);
        driver.get(url);
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        logger.info("Total count of links: " + allLinks.size());
        List<String> brokenLinks = CommonUtils.getBrokenLinks(allLinks);
        CommonUtils.writeBrokenLinksReport(url, brokenLinks);
        // Logging broken link count
        logger.info("Total count of brokenlinks: " + brokenLinks.size());
    }

    @Test(priority = 2)
    public void textWithoutSendKeys() throws IOException, ParseException {
        SearchPage searchPage = new SearchPage(driver);
        url = config.getUrltask2();
        driver.get(url);
        searchPage.textSerchBox();
        searchPage.clickSearchButton();
    }

    @Test(priority = 3)
    public void failedScreenShotTask3() throws AWTException, InterruptedException, IOException, ParseException {
        SearchPage searchPage = new SearchPage(driver);
        url = config.getUrltask3();
        driver.get(url);
        searchPage.clickButton();
    }

    @Test(priority = 4)
    public void spinner() throws AWTException, InterruptedException, IOException, ParseException {
        SpinnerTaskPage4 spinnerTaskPage4 = new SpinnerTaskPage4(driver);
        url = config.getUrltask4s();
        driver.get(url);
        spinnerTaskPage4.clickShowSpinnerBtn();
        spinnerTaskPage4.clickgridCheckbox();
    }
    @Test(priority = 4)
    public void colorChange() throws AWTException, InterruptedException, IOException, ParseException {
        ColorChangePage colorChangePage = new ColorChangePage(driver);
        url = config.getUrltask4d();
        driver.get(url);
        colorChangePage.colorChange();

    }
}
