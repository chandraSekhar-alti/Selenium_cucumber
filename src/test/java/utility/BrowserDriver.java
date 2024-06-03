package utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BrowserDriver {
    private static WebDriver driver;
    private Properties properties;
    private String applicationURL;

    public BrowserDriver() {
        // Private constructor to prevent instantiation
    }

    public WebDriver setUp() {
        properties = new Properties();
        try {
            properties.load(BrowserDriver.class.getClassLoader().getResourceAsStream("configuration.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String browser = properties.getProperty("browser");
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else {
            // Handle other browsers if needed
        }

        driver.manage().window().maximize();
        System.out.println("Navigated to the application ");
        return driver;
    }

    public void launchURL() {
        properties = new Properties();
        try {
            properties.load(BrowserDriver.class.getClassLoader().getResourceAsStream("configuration.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        applicationURL = properties.getProperty("appURL");
        driver.get(applicationURL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlToBe(applicationURL));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("The URL loaded is not as expected", applicationURL, actualUrl);
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void validatingURL(String expectedUrl) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        boolean urlValidation = wait.until(ExpectedConditions.urlContains(expectedUrl));
        Assert.assertTrue("HomePage URL validation failed", urlValidation);
    }
}
