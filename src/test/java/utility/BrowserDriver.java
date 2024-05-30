package utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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

    public static void launchURL(WebDriver driver, String url) {
        driver.get(url);
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null; // Reset driver instance
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
