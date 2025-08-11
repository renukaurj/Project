package tests;

import base.BaseTest;
import config.YAMLConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utils.CommonUtils;
import java.util.List;
import java.util.logging.Logger;

/**
 * responsible for extracting broken links from the webpage and export output to txt file.
 */

public class BrokenLinksTest extends BaseTest {

    private static final Logger logger = Logger.getLogger(BrokenLinksTest.class.getName());
    YAMLConfig config = new YAMLConfig();
    static String url;


    @Test
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
}
