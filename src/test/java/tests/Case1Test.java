package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultPage;

public class Case1Test extends BaseTest {

    @Test
    public void testFlightTimeFilter() {
        logger.info("Starting testFlightTimeFilter test.");
        sleep(5000);
        HomePage homePage = new HomePage(driver);
        homePage.searchFlight("İstanbul", "Ankara", "2024-12-01", "2024-12-05");
        sleep(50000);
        boolean isFilteredCorrectly = searchResultPage.areFlightsWithinTimeRange("10:00", "18:00");
        Assert.assertTrue(isFilteredCorrectly, "Flight times are not within the specified range!");
        logger.info("Test testFlightTimeFilter completed successfully.");
    }
}
