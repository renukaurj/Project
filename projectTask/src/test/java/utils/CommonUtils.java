package utils;

import org.openqa.selenium.WebElement;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
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
            logger.log(Level.SEVERE, linkUrl + " - Exception: " + ioe.getMessage() + " is a broken link", ioe);
            return true;
        } catch (ClassCastException cce) {
            logger.log(Level.SEVERE, linkUrl + " - Exception: " + cce.getMessage() + " is a broken link", cce);
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

        BufferedWriter writer = null;

        try {

            writer = new BufferedWriter(new FileWriter("broken_links_report.txt"));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Broken Links Report for "+url+"\n");
            stringBuilder.append("====================\n");
            stringBuilder.append("Total broken links: " + brokenLinks.size() + "\n");
            stringBuilder.append("====================\n");
            stringBuilder.append("List of all broken links:  "+"\n\n");


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
}
