package utility;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class hooks {
    private static WebDriver driver;
    private Properties properties;
    private String applicationURL;
    private static final Logger logger = LogManager.getLogger(hooks.class);

    @Before
    public void setup() {
        logger.info("Setting up before the Scenario");
        properties = PropertyLoader.loadProperties("configuration.properties");
        BrowserFactory browserFactory = new BrowserFactory();

        browserFactory.browserSetup();
        driver = browserFactory.getDriver();
        applicationURL = properties.getProperty("appURL");

        browserFactory.navigateToURL(driver,applicationURL);
        browserFactory.maximizeWindow();
        logger.info("Browser launched and navigated to URL: " + applicationURL);
    }

    @After
    public void tearDown() {
        logger.info("Tearing down after the Scenario");
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("Browser closed");
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
