package tests;

import base.BaseTest;
import config.YAMLConfig;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import pageObject.SearchPage;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Logger;

public class FullScreenshotTask3 extends BaseTest {

    private static final Logger logger = Logger.getLogger(FullScreenshotTask3.class.getName());
    YAMLConfig config = new YAMLConfig();
    static String url;

    @Test
    public void failedScreenShotTask3() throws AWTException, InterruptedException, IOException, ParseException {
        SearchPage searchPage = new SearchPage(driver);
        url = config.getUrltask3();
        driver.get(url);
        searchPage.clickButton();
    }}
