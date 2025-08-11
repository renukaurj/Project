package tests;

import base.BaseTest;
import config.YAMLConfig;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import pageObject.SearchPage;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * responsible for enter text without sendKeys().
 */
public class TextWithoutSendKeysTask2 extends BaseTest {

    private static final Logger logger = Logger.getLogger(TextWithoutSendKeysTask2.class.getName());
    YAMLConfig config = new YAMLConfig();
    static String url;

    @Test
    public void textWithoutSendKeys() throws IOException, ParseException {
        SearchPage searchPage = new SearchPage(driver);
        url = config.getUrltask2();
        driver.get(url);
        searchPage.textSerchBox();
        searchPage.clickSearchButton();
    }
}
