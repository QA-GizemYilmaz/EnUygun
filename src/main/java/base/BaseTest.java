package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.HomePage;
import pages.SearchResultPage;


public class BaseTest {
    protected static WebDriver driver;
    protected HomePage homePage;
    protected SearchResultPage searchResultPage;
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);


    @BeforeMethod
    public void setup() {
        logger.info("Setting up WebDriver.");
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.enuygun.com/");
        } else {
            logger.warn("Driver was already initialized.");
        }
        homePage = new HomePage(driver);
        searchResultPage = new SearchResultPage(driver);
        logger.info("Setup completed.");
        // wait until Kabul Et pop is opened and click Kabul et button
        waitForElementVisibility(homePage.getAcceptCookiesButton(), 15).click();
        //sleep(5000);
    }


    public WebElement waitForElementVisibility(By locator, int timeoutInSeconds) {
        if (driver == null) {
            logger.error("Driver is null. Ensure setup method is called before tests.");
            throw new IllegalStateException("Driver is not initialized. Check setup method.");
        }

        try {
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            return customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Failed to locate element: {} within timeout: {} seconds", locator.toString(), timeoutInSeconds, e);
            throw e;
        }
    }


    public void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.err.println("An error occurred during the wait: " + e.getMessage());
        }
    }


    public void pressEnter() {
        if (driver == null) {
            logger.error("Driver is not initialized. Ensure setup method is called before tests.");
            throw new IllegalStateException("Driver is not initialized. Check setup method.");
        }
        try {
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ENTER).perform();
            logger.info("Enter key pressed successfully.");
        } catch (Exception e) {
            logger.error("Failed to press Enter key.", e);
            throw new RuntimeException("Failed to press Enter key.", e);
        }
    }


    @AfterMethod
    public void tearDown() {
        logger.info("Tearing down WebDriver.");
        if (driver != null) {
            driver.quit();
        }
    }

}

