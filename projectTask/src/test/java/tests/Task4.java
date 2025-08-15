package tests;

import base.BaseTest;
import config.YAMLConfig;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import pageObject.ColorChangePage;
import pageObject.SpinnerTaskPage4;

import java.awt.*;
import java.io.IOException;
import java.util.logging.Logger;

public class Task4 extends BaseTest {

    private static final Logger logger = Logger.getLogger(Task4.class.getName());
    YAMLConfig config = new YAMLConfig();
    static String url;

    @Test(priority = 1)
    public void spinner() throws AWTException, InterruptedException, IOException, ParseException {
        SpinnerTaskPage4 spinnerTaskPage4 = new SpinnerTaskPage4(driver);
        url = config.getUrltask4s();
        driver.get(url);
        spinnerTaskPage4.clickShowSpinnerBtn();
        spinnerTaskPage4.clickgridCheckbox();
    }
    @Test(priority = 2)
    public void colorChange() throws AWTException, InterruptedException, IOException, ParseException {
        ColorChangePage colorChangePage = new ColorChangePage(driver);
        url = config.getUrltask4d();
        driver.get(url);
        colorChangePage.colorChange();

    }
}


