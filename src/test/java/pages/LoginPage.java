package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.WaitUtils;

import java.time.Duration;

public class LoginPage {
    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By signInButton = By.xpath("(//li[@class='authorization-link']/a)[1]");
    private final By userNameInputField = By.cssSelector("input#email");
    private final By userPasswordInputField = By.xpath("(//input[@id='pass'])[1]");
    private final By logInButton = By.xpath("(//span[text()='Sign In'])[1]");
    private final By userWelcomeText = By.xpath("(//span[@class='logged-in'])[1]");
    private final By invalidPasswordDialogueBox = By.cssSelector("div[role='alert']");
    private final By invalidPasswordErrorMessageText = By.cssSelector("div[role='alert'] > div > div");
    private final By invalidEmailErrorMessageText = By.xpath("//div[@role='alert']/div/div");
    private final By invalidEmailErrorMsgDialogueBox = By.cssSelector("div[role='alert'] > div");
    private final By emailErrorMessage = By.xpath("//div[@id='email-error']");
    private final By passwordErrorMessage = By.xpath("//div[@id='pass-error']");
    public final By createAccountButton = By.xpath("(//a[text()='Create an Account'])[1]");



    public void clickOnSignInButton() {
        driver.findElement(signInButton).click();
    }

    public void enterUserDetails(String userEmail, String userPassword) {
        driver.findElement(userNameInputField).sendKeys(userEmail);
        driver.findElement(userPasswordInputField).sendKeys(userPassword);
    }

    public void clickOnLoginButton() {
        driver.findElement(logInButton).click();
    }

    public void validatingUserWelcomeText(String expectedText){
        WaitUtils.waitForElementToBeVisible(driver,userWelcomeText);
        String actualText = driver.findElement(userWelcomeText).getText();
        Assert.assertEquals("Actual text and expected text didn't match",expectedText,actualText);
    }

    public void validatingInvalidPasswordPopUpBox(){
        driver.findElement(invalidPasswordDialogueBox).isDisplayed();
        String actualText = driver.findElement(invalidPasswordErrorMessageText).getText();
        String expectedText = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
        Assert.assertEquals("expectedText and Actual text is not matched in validating the invalid password function while login",expectedText,actualText);
    }

    public void validatingTheApplicationUrlLoad(String expectedAppURL){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlToBe(expectedAppURL));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("The URL loaded is not as expected", expectedAppURL, actualUrl);
    }

    public void validatingInvalidEmailPopupBox(){
        driver.findElement(invalidEmailErrorMsgDialogueBox).isDisplayed();
        String actualText = driver.findElement(invalidEmailErrorMessageText).getText();
        String expectedText = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
        Assert.assertEquals("Expected text and ActualText didn't matched ",expectedText,actualText);
    }

    public void validatingEmailErrorMsgWhileEnteringNumbers(String expectedText){
        driver.findElement(emailErrorMessage).isDisplayed();
        String actualText = driver.findElement(emailErrorMessage).getText();
        Assert.assertEquals("Expected text and ActualText didn't matched While validating the email error", expectedText, actualText);
    }

    public void validatingPopMsgWhileEnteringNullValues(String expectedErrorMsg){
        String actualEmailText = driver.findElement(emailErrorMessage).getText();
        Assert.assertEquals("ActualText and Expected text didn't matched while passing the empty values in the Email input field",actualEmailText, expectedErrorMsg);
        String actualPasswordText = driver.findElement(passwordErrorMessage).getText();
        Assert.assertEquals("ActualText and Expected text didn't matched while passing the empty values in the password input field",actualPasswordText,expectedErrorMsg);
    }
}