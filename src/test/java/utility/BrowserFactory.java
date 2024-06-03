package utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class BrowserFactory {
    private Properties properties;
    public WebDriver driver;
    private String browserName;
    private static final Logger logger = LogManager.getLogger(BrowserFactory.class);


    public void browserSetup(){
        properties = PropertyLoader.loadProperties("configuration.properties");
        browserName = properties.getProperty("browser");

        logger.info("This will run before the Scenario");

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver=new FirefoxDriver();
        }else{
            logger.info("invalid browser type " + browserName);
        }
    }

    public WebDriver getDriver(){
        return driver;
    }

    public void navigateToURL(WebDriver driver, String applicationURL){
        driver.get(applicationURL);
        logger.info("Navigated to URL: " + applicationURL);
    }
    public void maximizeWindow() {
        driver.manage().window().maximize();
        logger.info("Browser window maximized");
    }
}
