package pageObject;

import config.TestDataManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import utils.CommonUtils;

import java.awt.*;
import java.io.IOException;

/**
 * Responsibility of this class is define locators for page elements, Provide methods (actions) to interact with those elements.
 */
public class SearchPage {

    private WebDriver driver;
    // WebElements using @FindBy annotations

    @FindBy(xpath = "//*[@id=\"APjFqb\"]")
    private WebElement searchfield;

    @FindBy(xpath = "//div[3]/center/input[1]")
    private WebElement searchBtn;

    @FindBy(xpath = "//div[3]/center/input[1")
    private WebElement searchBtnRandom;


    TestDataManager testDataManager;
    JSONObject searcObj;

    // Constructor to initialize WebElements
    public SearchPage(WebDriver driver) throws IOException, ParseException {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initializes all @FindBy elements
        testDataManager = new TestDataManager(driver);
        searcObj = (JSONObject) TestDataManager.searchData();
    }

    public void textSerchBox() {
        String searchItem = String.valueOf(searcObj.get("searchItem"));
        CommonUtils.setValueByJS(driver, searchfield, searchItem);
       // Assert.assertEquals("laptop",searchItem);
    }

    public void clickSearchButton() {
        searchBtn.click();
    }

    public void clickButton() {
        searchBtnRandom.click();
    }
}
