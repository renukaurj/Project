package pageObject;

import config.TestDataManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.ColorChangedCondition;
import utils.CommonUtils;

import java.io.IOException;

public class ColorChangePage {

    private WebDriver driver;

    // Constructor to initialize WebElements
    TestDataManager testDataManager;
    JSONObject searcObj;

    public ColorChangePage(WebDriver driver) throws IOException, ParseException {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initializes all @FindBy elements
        testDataManager = new TestDataManager(driver);
        searcObj = (JSONObject) TestDataManager.searchData();
    }

    public void colorChange() {

        By colorButton = By.id("colorChange");
        String expectedColor = String.valueOf(searcObj.get("expectedColor"));
        CommonUtils.waitForCondition(driver, new ColorChangedCondition(colorButton, expectedColor), 10);
        System.out.println("Button color changed to: " + expectedColor);
    }

}
