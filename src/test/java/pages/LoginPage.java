package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.WaitUtils;

public class LoginPage extends BasePage {
//    private WebDriver driver;
    private WaitUtils wait;
    
    private By nav_login = By.id("login2");
    private By usernameField = By.id("loginusername");
    private By passwordField = By.id("loginpassword");
    private By loginButton = By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]");
    
   
    
    public LoginPage(WebDriver driver) {
    	super(driver);
        this.wait = new WaitUtils(driver);
    }

    public void clickNavLogin() {
//    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.elementToBeClickable(nav_login)).click();
        wait.waitForElementToBeClickable(nav_login).click();
    }

    public void enterUsername(String username) {
//    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
		wait.waitForElementToBeVisible(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
//        WebElement pass = driver.findElement(passwordField);
//        typeText(pass, password);
        typeText(wait.waitForElementToBeVisible(passwordField), password);
    }

    public void clickLoginButton() {
    	WebElement loginbtn =wait.waitForElementToBeClickable(loginButton);
    	click(loginbtn);
    }
}
