package utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowserDriver {
    private static WebDriver driver;

    private BrowserDriver() {
        // Private constructor to prevent instantiation
    }

    public static WebDriver setUp() {
        if (driver == null) {
            // Initialize WebDriver if not already initialized
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void launchURL() {
        String url = "https://magento.softwaretestingboard.com/";
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlToBe(url));

        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("The URL loaded is not as expected", url, actualUrl);
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
