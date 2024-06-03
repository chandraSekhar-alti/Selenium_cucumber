package cucumberHooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import utility.BrowserFactory;
import utility.PropertyLoader;

import java.util.Properties;

public class Hooks {
    private static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void setup() {
        logger.info("Setting up before the Scenario");
        Properties properties = PropertyLoader.loadProperties("configuration.properties");
        BrowserFactory browserFactory = new BrowserFactory();

        browserFactory.browserSetup();
        driver = browserFactory.getDriver();
        String applicationURL = properties.getProperty("appURL");

        browserFactory.navigateToURL(driver, applicationURL);
        browserFactory.maximizeWindow();
        logger.info("Browser launched and navigated to URL: {}", applicationURL);
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
