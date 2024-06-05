package pages;

import com.github.javafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.Logger;


public class CreateAccountPage {
    public WebDriver driver;
    private static final Logger logger = LogManager.getLogger(CreateAccountPage.class);
    private final Faker faker;

    // Form data values
    public String firstName;
    public String lastName;
    public String email;
    public String password;


    public CreateAccountPage(WebDriver driver){
        this.driver = driver;
        this.faker = new Faker();
    }

    private final By fireNameLocator = By.cssSelector("input#firstname");
    private final By lastNameLocator = By.cssSelector("input#lastname");
    private final By emailAddress = By.cssSelector("input#email_address");
    private final By passwordInput = By.cssSelector("input#password");
    private final By confirmationPasswordField = By.cssSelector("input#password-confirmation");
    public final By createAccountButton = By.xpath("//button[@title='Create an Account']/span");


    public void clickOnCreateAccountButton(By locator){
        WebElement button = driver.findElement(locator);
        if (button.isDisplayed()){
            button.click();
        }else {
            logger.info("{} button is not displayed", locator);
        }
    }

    public void fillingRegistrationForm(){
        firstName =faker.name().firstName();
        logger.info("FirstName is :- {}",firstName);
        enterTextIntoInputField(fireNameLocator,firstName);

        lastName = faker.name().lastName();
        logger.info("Last Name is :- {}",lastName);
        enterTextIntoInputField(lastNameLocator,lastName);

        email = faker.internet().emailAddress();
        logger.info("Email address id :- {}",email);
        enterTextIntoInputField(emailAddress,email);

        password = faker.internet().password(10,15,true,true,true);
        logger.info("password is :- {}",password);
        enterTextIntoInputField(passwordInput,password);
        enterTextIntoInputField(confirmationPasswordField,password);
    }


    /**
     * Enters data into an input field identified by the given locator.
     * @param locator The By locator strategy used to identify the input field.
     * @param value   The value to be entered into the input field.
     */
    public void enterTextIntoInputField( By locator,String value){
        try{
            WebElement inputField = driver.findElement(locator);
            if (inputField.isDisplayed()){
                inputField.clear();
                inputField.click();
                inputField.sendKeys(value);
            }else {
                logger.error("Input field identified by locator {} is not displayed",locator);
            }
        }catch (Exception e){
            logger.error("Error entering text into input field identified by locator {}: {}", locator, e.getMessage());
        }
    }

}
