package config;

import lombok.Data;
import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;


/**
 * responsible for reading a YAML configuration file and retrieving the specific URL.
 */
@Data
public class YAMLConfig {

    private String urltask1;
    private String urltask2;
    private String urltask3;
    private  String urltask4s;
    private  String urltask4d;

    public YAMLConfig() {
        setConfigUrl();
    }


    private void setConfigUrl(){

        Yaml yaml = new Yaml();

        try{
            InputStream inputStream = new FileInputStream((new File("src/test/resources/configurationURL.yaml")));
            Map<String, Object> config = yaml.load(inputStream);
            Map<String, String> uri = (Map<String, String>) config.get("uri");
            urltask1 = uri.get("urltask1");
            urltask2 = uri.get("urltask2");
            urltask3 = uri.get("urltask3");
            urltask4s = uri.get("urltask4s");
            urltask4d = uri.get("urltask4d");

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
