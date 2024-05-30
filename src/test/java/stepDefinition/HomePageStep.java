package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static pages.HomePage.*;
public class HomePageStep {

    @Given("user navigates to the login page")
    public void navigateToLoginPage() {
        click_on_sign_in_button();

    }

    @When("the user enters valid login credentials")
    public void userSuccessfullyEntersTheLoginDetails() {
        enteringDetails();
    }

    @Then("the user should be redirected to the homepage")
    public void userShouldAbleToSeeTheHomepage() {
        validatingURL();
        driver.quit();
    }
}
