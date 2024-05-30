package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utility.BrowserDriver;
import utility.WaitUtils;

public class LoginPage {
    public WebDriver driver;

    public LoginPage() {
        this.driver = BrowserDriver.getDriver();
    }

    private final By signInButton = By.xpath("(//li[@class='authorization-link']/a)[1]");
    private final By userNameInputField = By.cssSelector("input#email");
    private final By userPasswordInputField = By.xpath("(//input[@id='pass'])[1]");
    private final By logInButton = By.xpath("(//span[text()='Sign In'])[1]");
    private final By userWelcomeText = By.xpath("(//span[@class='logged-in'])[1]");
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
}
