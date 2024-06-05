package stepDefinition;

import cucumberHooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.CreateAccountPage;
import pages.LoginPage;


public class CreateAccountStep {

    private WebDriver driver;
    private final CreateAccountPage createAccountPage;
    private final LoginPage loginPage;

    public CreateAccountStep(){
        this.driver = Hooks.getDriver();
        this.createAccountPage = new CreateAccountPage(driver);
        this.loginPage = new LoginPage(driver);
    }


    @When("I click on the create an account button")
    public void iClickOnTheCreateAnAccountant() throws InterruptedException {
        createAccountPage.clickOnCreateAccountButton(loginPage.createAccountButton);
    }

    @And("I fill out the registration form with valid details")
    public void iFillOutTheRegistrationFormWithValidDetails() {
        System.out.println("iFillOutTheRegistrationFormWithValidDetails");
        createAccountPage.fillingRegistrationForm();
    }

    @And("I submit the registration form")
    public void iSubmitTheRegistrationForm() {
        String url = "https://magento.softwaretestingboard.com/customer/account/";
        createAccountPage.clickOnCreateAccountButton(createAccountPage.createAccountButton);
        loginPage.validatingTheApplicationUrlLoad(url);

    }

    @Then("I should be registered as a new user")
    public void iShouldBeRegisteredAsANewUser() {
        loginPage.validatingUserWelcomeText("Welcome, "+createAccountPage.firstName+" "+createAccountPage.lastName+"!");
    }
}
