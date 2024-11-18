package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultPage {
    private WebDriver driver;

    private By flightTimes = By.xpath("//div[contains(@class, 'flight-depart-time')]");
    private By flightPrices = By.xpath("//div[contains(@class, 'flight-price-value')]");

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Uçuş saatlerinin verilen aralıkta olup olmadığını kontrol eder.
     */
    public boolean areFlightsWithinTimeRange(String startTime, String endTime) {
        List<WebElement> times = driver.findElements(flightTimes);
        for (WebElement time : times) {
            String flightTime = time.getText();
            if (!isWithinRange(flightTime, startTime, endTime)) {
                return false;
            }
        }
        return true;
    }

    /**
     * THY uçuşlarının fiyatlarının artan sırada olup olmadığını kontrol eder.
     */
    public boolean areFlightPricesSortedAscending() {
        List<WebElement> prices = driver.findElements(flightPrices);

        double previousPrice = 0.0;
        for (WebElement priceElement : prices) {
            // Fiyat değerini çift (double) olarak al
            String priceText = priceElement.getText().replaceAll("[^0-9]", ""); // Sadece rakamları al
            double currentPrice = Double.parseDouble(priceText);

            // Fiyat sıralamasını kontrol et
            if (currentPrice < previousPrice) {
                return false;
            }
            previousPrice = currentPrice;
        }
        return true;
    }

    /**
     * Uçuş saatlerinin verilen aralıkta olup olmadığını kontrol etmek için yardımcı metot.
     */
    private boolean isWithinRange(String time, String start, String end) {
        return time.compareTo(start) >= 0 && time.compareTo(end) <= 0;
    }
}
