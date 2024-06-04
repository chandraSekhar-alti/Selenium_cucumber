package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.LoginPage;
import utility.PropertyLoader;
import cucumberHooks.Hooks;

import java.util.Properties;


public class LoginStep {

    private static final Logger log = LoggerFactory.getLogger(LoginStep.class);
    Properties properties = PropertyLoader.loadProperties("configuration.properties");
    String applicationURL = properties.getProperty("appURL");

    WebDriver driver = Hooks.getDriver();
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
    public void iClickTheLoginButton() {
        loginPage.clickOnLoginButton();
    }

    @Then("I should be logged in with welcome message {string}")
    public void validatingHomePage(String expectedWelcomeMessage) {
        loginPage.validatingTheApplicationUrlLoad(applicationURL);
        loginPage.validatingUserWelcomeText(expectedWelcomeMessage);
    }

    @Then("I should get the error popup message")
    public void iShouldGetTheErrorPopupMessage() {
        loginPage.validatingInvalidPasswordPopUpBox();
    }

    @When("I enter invalid {string} and valid {string}")
    public void loginToWebsiteWithInvalidCredential(String invalidEmail, String password) {
        loginPage.clickOnSignInButton();
        loginPage.enterUserDetails(invalidEmail, password);
    }

    @Then("I should get the invalid username error popup message")
    public void iShouldGetTheInvalidUsernameErrorPopupMessage() {
        loginPage.validatingInvalidEmailPopupBox();
    }


    @Then("I should get an error popup message saying {string}")
    public void iShouldGetAnErrorPopupMessageSaying(String emptyEmail) {
        loginPage.validatingPopMsgWhileEnteringNullValues(emptyEmail);
    }

    @Then("I should get the Email error popup message saying {string}")
    public void iShouldGetTheEmailErrorPopupMessageSaying(String expectedText) {
        loginPage.validatingEmailErrorMsgWhileEnteringNumbers(expectedText);
    }
}