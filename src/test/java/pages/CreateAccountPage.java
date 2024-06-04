package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateAccountPage {
    public WebDriver driver;

    public CreateAccountPage(WebDriver driver){
        this.driver = driver;
    }

    private final By createAccountButton = By.xpath("(//a[text()='Create an Account'])[1]");

    public void clickOnCreateAccountButton(){
        driver.findElement(createAccountButton).isDisplayed();
        driver.findElement(createAccountButton).click();
    }

}
