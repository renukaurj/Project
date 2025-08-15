package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * The CommonUtils class is a utility class containing
 * shared static methods used across test framework.
 */
public class CommonUtils {
    private static final Logger logger = Logger.getLogger(CommonUtils.class.getName());

    /**
     * return boolean value true if Links are broken else false
     */

    public static boolean isLinkBroken(String linkUrl) {
        try {
            URL url = new URL(linkUrl);
            HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
            httpURLConnect.setConnectTimeout(5000);
            httpURLConnect.connect();

            if (httpURLConnect.getResponseCode() >= 400) {
                logger.warning(linkUrl + " - " + httpURLConnect.getResponseMessage() + " is a broken link");
                return true;
            }
        } catch (IOException ioe) {
            logger.log(Level.SEVERE, linkUrl + " - Exception: " + ioe.getMessage() + " is ioe exception", ioe);
            return true;
        } catch (ClassCastException cce) {
            logger.log(Level.SEVERE, linkUrl + " - Exception: " + cce.getMessage() + " is cce", cce);
            return true;
        }

        return false;
    }

    /**
     * Returns list of broken links from a list of WebElements.
     */
    public static List<String> getBrokenLinks(List<WebElement> links) {
        return links.parallelStream()
                .map(link -> link.getAttribute("href"))
                .filter(url -> url != null && !url.isEmpty())
                .filter(CommonUtils::isLinkBroken)
                .collect(Collectors.toList());
    }

    /**
     * writing brokenlinks to text file.
     */
    public static void writeBrokenLinksReport(String url, List<String> brokenLinks) {

        FileWriter writer = null;

        try {
            writer = new FileWriter("broken_links_report.txt");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Broken Links Report for " + url + "\n");
            stringBuilder.append("====================\n");
            stringBuilder.append("Total broken links: " + brokenLinks.size() + "\n");
            stringBuilder.append("====================\n");
            stringBuilder.append("List of all broken links:  " + "\n\n");


            for (String link : brokenLinks) {
                if (link != null && !link.trim().isEmpty()) {
                    stringBuilder.append(link.trim());
                    stringBuilder.append("\n");
                } else {
                    logger.warning("Skipped link is null or empty.");
                }
            }
            writer.write(stringBuilder.toString());

            logger.info("Broken links report successfully written to file.");

        } catch (IOException e) {
            logger.severe("Error writing broken links report: " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    logger.severe("Error closing the file writer: " + e.getMessage());
                }
            }
        }
    }

    // Set value of an element using JavaScriptExecuter
    public static void setValueByJS(WebDriver driver, WebElement element, String value) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].value = arguments[1];", element, value);
    }

    //capture full page screenshot
    public static void captureFullPageScreenshot(WebDriver driver, String testName) throws InterruptedException {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        File dest = new File("failurescreenshots/" + testName + "_" + timestamp + ".png");

        try {

            Screenshot screenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                    .takeScreenshot(driver);
            BufferedImage image = screenshot.getImage();

            Files.createDirectories(dest.getParentFile().toPath());
            ImageIO.write(image, "PNG", dest);
            logger.info("Full-page screenshot saved: " + dest.getAbsolutePath());
        } catch (IOException e) {
            logger.info("Failed to save full-page screenshot: " + e.getMessage());
        }
    }
    // Custom ExpectedCondition class
    public static class ColorChangedCondition implements ExpectedCondition<Boolean> {
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

    // Wait wrapper for any ExpectedCondition
    public static void waitForCondition(WebDriver driver, ExpectedCondition<?> condition, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(condition);
    }

}
