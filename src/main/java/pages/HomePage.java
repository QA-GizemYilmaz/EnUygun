package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage extends BaseTest {
    private WebDriver driver;

    // Locators
    public By fromInput = By.xpath("//input[@data-testid='endesign-flight-origin-autosuggestion-input' and @role='combobox' and @type='text']");
    private By toInput = By.xpath("//input[@data-testid='endesign-flight-destination-autosuggestion-input' and @role='combobox' and @type='text']");
    private By roundTripDate = By.cssSelector("input[data-testid='enuygun-homepage-flight-departureDate-datepicker-input'][aria-expanded='true']");
    private By departOnDate = By.xpath("//input[@data-testid='enuygun-homepage-flight-departureDate-datepicker-input' and @readonly='' and @name='departureDate']");
    private By returnOn = By.xpath("//input[@data-testid='enuygun-homepage-flight-returnDate-datepicker-input' and @readonly='' and @name='returnDate' and @inputmode='text']");
    private By searchButton = By.xpath("//*[@id=\"headlessui-tabs-panel-:rh:\"]/div/div/form/div/div[2]/div[1]/div[2]/button/div/span/div/div[1]");
    private final By acceptCookies = By.xpath("//*[@id='onetrust-accept-btn-handler']");

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Behaviors (Actions)
    public By getAcceptCookiesButton() {return acceptCookies; }

    public void searchFlight(String from, String to, String startDate, String  endDate) {
        //this method search for the flight
        waitForElementVisibility(fromInput, 15).sendKeys(from);
        pressEnter();
        waitForElementVisibility(toInput, 15).sendKeys(to);
        pressEnter();
        waitForElementVisibility(returnOn, 15).click();

        //JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].value = arguments[1];", roundTripDate, startDate);
        //sleep(50000);

        waitForElementVisibility(roundTripDate, 15).sendKeys(startDate);
        pressEnter();
        waitForElementVisibility(departOnDate, 15).sendKeys(endDate);
        pressEnter();
        waitForElementVisibility(searchButton, 15).sendKeys(to);



    }



}
