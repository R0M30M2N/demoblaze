package tests;

import java.util.Properties;




import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.chrome.ChromeOptions;


//import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigReader;

public class BaseTest {
    protected WebDriver driver;
    protected Properties loadData;

    @BeforeMethod
    
    public void setup() {
    	loadData=ConfigReader.getProperties();

        String browserName = loadData.getProperty("browser");
        String baseUrl = loadData.getProperty("baseurl");
        String headless = loadData.getProperty("headless");
        System.out.println("Browser selected: " + browserName);
        System.out.println("Base URL: " + baseUrl);
        ChromeOptions options = new ChromeOptions();

	    // headless mode
	    if (headless != null && headless.equalsIgnoreCase("true")) {
	    	options.addArguments("--headless");
		    options.addArguments("--disable-gpu");
		    options.addArguments("--window-size=1920,1080");
	        }
        if (browserName.equalsIgnoreCase("chrome")) {
//            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("firefox")) {
           
//            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if (browserName.equalsIgnoreCase("edge")) {
//			WebDriverManager.edgedriver().setup();
        	
			driver = new EdgeDriver();
		} else {
			throw new IllegalArgumentException("Invalid browser name: " + browserName);
		}

      driver.manage().window().maximize();
      System.out.println("Navigating to: " + baseUrl);
        driver.get(baseUrl);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
