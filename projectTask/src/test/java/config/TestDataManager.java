package config;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import java.io.FileReader;
import java.io.IOException;

/**
 * responsible for reading a testData file and retrieving objects.
 */
public class TestDataManager {

    private WebDriver driver;

    static JSONParser jsonparsor = new JSONParser();

    public TestDataManager(WebDriver driver) {
        this.driver = driver;
    }


    public static Object searchData() throws IOException, ParseException {
        FileReader reader = new FileReader("src/test/resources/testData/data.json");
        Object searchdata = jsonparsor.parse(reader);
        return searchdata;
    }
}
