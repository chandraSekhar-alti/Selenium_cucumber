package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import utility.BrowserDriver;

public class LoginStep {

    @Before
    public void setUp() {
        BrowserDriver.setUp(); // Ensure driver is initialized
    }

    @After
    public void tearDown() {
        BrowserDriver.quitDriver(); // Quit driver after scenario
    }

    @Given("I am on login page")
    public void navigateToLoginPage() throws InterruptedException {
        BrowserDriver.launchURL(BrowserDriver.getDriver(),"https://magento.softwaretestingboard.com/");
        Thread.sleep(3000);
    }
}
