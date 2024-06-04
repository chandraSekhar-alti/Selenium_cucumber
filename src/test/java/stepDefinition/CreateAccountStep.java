package stepDefinition;

import cucumberHooks.Hooks;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.CreateAccountPage;

public class CreateAccountStep {


    Hooks hooks = new Hooks();
    WebDriver driver = Hooks.getDriver();
    CreateAccountPage createAccountPage = new CreateAccountPage(driver);

    @When("I click on the create an account button")
    public void iClickOnTheCreateAnAccountant() throws InterruptedException {
        createAccountPage.clickOnCreateAccountButton();
        Thread.sleep(5000);
    }
}
