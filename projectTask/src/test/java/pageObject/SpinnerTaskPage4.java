package pageObject;

import config.TestDataManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.IOException;
import java.time.Duration;

public class SpinnerTaskPage4 {


    private WebDriver driver;
    // WebElements using @FindBy annotations

    @FindBy(id = "showspinner")
    private WebElement showSpinnerBtn;
    String gridChecboxClick = "//*[@id=\"table\"]/tbody/tr[2]/td[4]/input";

    @FindBy(xpath = "//*[@id=\\\"table\\\"]/tbody/tr[2]/td[4]/input")
    private WebElement gridChecboxClick1;

    // Constructor to initialize WebElements
    TestDataManager testDataManager;
    JSONObject searcObj;

    public SpinnerTaskPage4(WebDriver driver) throws IOException, ParseException {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initializes all @FindBy elements
        testDataManager = new TestDataManager(driver);
        searcObj = (JSONObject) TestDataManager.searchData();
    }



public void clickShowSpinnerBtn() {

    showSpinnerBtn.click();

}

    public void clickgridCheckbox() throws InterruptedException {

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))        // Total time to wait
                .pollingEvery(Duration.ofSeconds(5))        // Frequency to check condition
                .ignoring(NoSuchElementException.class);    // Ignore this exception

        try{
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(gridChecboxClick)));
            element.click();
            System.out.println("Element clicked successfully!");

        }catch(TimeoutException e){
            System.out.println("timeout waiting for element to be clickable..");
        }

    }
}