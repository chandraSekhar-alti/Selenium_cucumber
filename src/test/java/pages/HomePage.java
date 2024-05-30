package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    public static WebDriver driver;

    public static By signInButton = By.xpath("(//li[@class='authorization-link']/a)[1]");

    public static By userNameInputField = By.cssSelector("input#email");

    public static By userPasswordInputField = By.xpath("(//input[@id='pass'])[1]");

    public static By logInButton = By.xpath("(//span[text()='Sign In'])[1]");

    public static void click_on_sign_in_button(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://magento.softwaretestingboard.com/");

        driver.findElement(signInButton).click();

    }

    public static void enteringDetails(){
        driver.findElement(userNameInputField).sendKeys("jhondon@gmail.com");
        driver.findElement(userPasswordInputField).sendKeys("Test1234@");
        driver.findElement(logInButton).click();
    }

    public static void validatingURL(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        boolean UrlValidation = wait.until(ExpectedConditions.urlContains("https://magento.softwaretestingboard.com/"));
        Assert.assertTrue("HomePage URl validation got failed",UrlValidation);
    }

}
