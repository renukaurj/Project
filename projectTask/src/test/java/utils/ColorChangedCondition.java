package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;


public class ColorChangedCondition implements ExpectedCondition<Boolean> {
    private final By locator;
    private final String expectedColor;

    public ColorChangedCondition(By locator, String expectedColor) {
        this.locator = locator;
        this.expectedColor = expectedColor;
    }

    @Override
    public Boolean apply(WebDriver driver) {
        try {
            WebElement element = driver.findElement(locator);
            String currentColor = element.getCssValue("color");
            System.out.println("Current color: " + currentColor);
            return currentColor.equals(expectedColor);
        } catch (Exception e) {
            return false;
        }
    }
}