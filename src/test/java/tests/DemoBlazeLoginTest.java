package tests;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import utils.WaitUtils;

public class DemoBlazeLoginTest extends BaseTest {
	@Test(dataProvider = "dp")
	public void login(String username, String password) throws Exception {

		LoginPage lg = new LoginPage(driver);
		WaitUtils wait = new WaitUtils(driver);

		lg.clickNavLogin();
		lg.enterUsername(username);
		lg.enterPassword(password);
		lg.clickLoginButton();

		boolean loginSuccess = false;

		// ---------------- HANDLE ALERT FOR FAILED LOGIN ----------------
		try {
//                 	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//                     wait.until(ExpectedConditions.alertIsPresent());
			wait.waitForAlert();
			// Switch to alert and get text
			String alertText = driver.switchTo().alert().getText();
			driver.switchTo().alert().accept();
			loginSuccess = false;
			System.out.println("Login failed for user: " + username + " | Alert says: " + alertText);
		} catch (Exception ignored) {
			loginSuccess = true;
			System.out.println("Login successful for user: " + username);
		}

		// ---------------- HANDLE LOGOUT IF LOGIN IS SUCCESSFUL ----------------
		if (loginSuccess) {
			wait.waitForElementToBeClickable(By.id("logout2")).click();
		}

		// ---------------- REFRESH BEFORE NEXT ITERATION ----------------
		Thread.sleep(1000);
		driver.navigate().refresh();
		Thread.sleep(1000);

	}
	
	

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { 
				{ "testmorning", "test123" }, // valid
				{ "testmorning1", "test123" }, // invalid
				{ "testmorning2", "test123" } // invalid
		};
	}

}
