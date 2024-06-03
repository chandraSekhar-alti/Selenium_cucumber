package utility;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {

    private BrowserFactory browserFactory;
    private WebDriver driver;

    @Before
    public void setup(){
        browserFactory = new BrowserFactory();
        driver = browserFactory.browserSetup();
    }

    @After(order = 0)
    public void tearDown(){
        driver.quit();
    }
}
