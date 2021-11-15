package dataProviders;

import utilities.DriverType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;
    public final String propertyFilePath = "src\\main\\resources\\configuration.properties";

    public ConfigFileReader(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
            throw new RuntimeException("Configurations.properties not found at "+ propertyFilePath);
        }
    }

    public DriverType getBrowserName(){
        String browser = properties.getProperty("browser");
        if (browser.equalsIgnoreCase("chrome")){
            return DriverType.CHROME;
        }else if (browser.equalsIgnoreCase("firefox")){
            return DriverType.FIREFOX;
        }else if (browser.equalsIgnoreCase("IE")){
            return DriverType.IE;
        }else if (browser.equalsIgnoreCase("edge")){
            return DriverType.EDGE;
        }
        else {return null;}
    }

    public String getUIdata(String value){
        String url = properties.getProperty(value);
        if (url!=null) return url;
        else throw new RuntimeException("url not specified in the Configurations.properties file");
    }

    public long getImplicitlyWait(){
        String wait = properties.getProperty("implicitWait");
        if (wait!=null) return Long.parseLong(wait);
        else throw new RuntimeException("url not specified in the Configurations.properties file");
    }

    public String getAPIData(String value){
        String url = properties.getProperty(value);
        if (url!=null) return url;
        else throw new RuntimeException("url not specified in the Configurations.properties file");
    }
}
