package utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ThreadGuard;

import java.util.Properties;

public class BrowserFactory {
    public static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver browserSetup() {
        WebDriver driver = getDriverInstance();
        if (driver == null) {
            try {
                Properties properties = new Properties();
                properties.load(BrowserFactory.class.getResourceAsStream("/configuration.properties"));

                String browserName = properties.getProperty("browser");

                if (browserName.equalsIgnoreCase("chrome")) {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                } else if (browserName.equalsIgnoreCase("edge")) {
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                } else if (browserName.equalsIgnoreCase("firefox")) {
                    WebDriverManager.firefoxdriver().setup();
                    driver =new FirefoxDriver();
                } else {
                    Logger log = null;
                    log.info("unsupported browser is passed " + browserName);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return getDriverInstance();
    }

    public static synchronized WebDriver getDriverInstance() {
        return driver.get();
    }

    private static synchronized void setDriverInstance(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

}
