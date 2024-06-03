package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utility.PropertyLoader;
import utility.hooks;

import java.util.Properties;


public class LoginStep {

    Properties properties = PropertyLoader.loadProperties("configuration.properties");
    String applicationURL = properties.getProperty("appURL");

    WebDriver driver = hooks.getDriver();
    LoginPage loginPage = new LoginPage(driver);


    @Given("I am on login page")
    public void navigateToLoginPage() {
        loginPage.validatingTheApplicationUrlLoad(applicationURL);
        System.out.println("application URL validation successfully!!!");
    }

    @When("I enter {string} and {string}")
    public void loginToWebsite(String userEmail, String userPassword) {
        loginPage.clickOnSignInButton();
        loginPage.enterUserDetails(userEmail, userPassword);
    }

    @And("I click the login button")
    public void clickOnLoginButton() {
        loginPage.clickOnLoginButton();
    }

    @Then("I should be logged in with welcome message {string}")
    public void validatingHomePage(String expectedWelcomeMessage) {
//        BrowserDriver.validatingURL("https://magento.softwaretestingboard.com/");
        loginPage.validatingUserWelcomeText(expectedWelcomeMessage);
    }

    @Then("I should get the error popup message")
    public void iShouldGetTheErrorPopupMessage() {
        loginPage.validatingInvalidPasswordPopUpBox();
    }
}