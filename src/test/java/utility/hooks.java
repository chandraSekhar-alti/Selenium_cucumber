package utility;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class hooks {
    private static WebDriver driver;
    private Properties properties;
    private String applicationURL;

    @Before
    public void setup() throws InterruptedException {
        properties = new Properties();
        try {
            properties.load(hooks.class.getClassLoader().getResourceAsStream("configuration.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("This will run before the Scenario");

        String browser = properties.getProperty("browser");
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver=new FirefoxDriver();
        }

        applicationURL = properties.getProperty("appURL");
        driver.get(applicationURL);
        Thread.sleep(3000);
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlToBe(applicationURL));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("The URL loaded is not as expected", applicationURL, actualUrl);

    }

    @After
    public void tearDown(){
        System.out.println("This will run after the Scenario");
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}