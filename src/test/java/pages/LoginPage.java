package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utility.BrowserDriver;
import utility.WaitUtils;

public class LoginPage {
    public WebDriver driver;

    public LoginPage() {
        // Initialize the driver instance here
        BrowserDriver browserDriver = new BrowserDriver();
        this.driver = browserDriver.setUp();
    }

    private final By signInButton = By.xpath("(//li[@class='authorization-link']/a)[1]");
    private final By userNameInputField = By.cssSelector("input#email");
    private final By userPasswordInputField = By.xpath("(//input[@id='pass'])[1]");
    private final By logInButton = By.xpath("(//span[text()='Sign In'])[1]");
    private final By userWelcomeText = By.xpath("(//span[@class='logged-in'])[1]");
    private final By invalidPasswordDialogueBox = By.cssSelector("div[role='alert']");
    private final By invalidPasswordErrorMessageText = By.cssSelector("div[role='alert'] > div > div");
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
}
