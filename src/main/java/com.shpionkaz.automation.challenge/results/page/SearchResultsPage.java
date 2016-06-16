package com.shpionkaz.automation.challenge.results.page;


import com.google.common.collect.Lists;
import com.shpionkaz.automation.challenge.BasePage;
import com.shpionkaz.automation.challenge.Configuration;
import com.shpionkaz.automation.challenge.results.data.SearchResultsPageProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchResultsPage extends BasePage implements SearchResultsPageProviders {

    @FindBy(how = How.ID, using = "tab_train")
    private WebElement transTab;

    @FindBy(how = How.ID, using = "tab_flight")
    private WebElement planesTab;

    @FindBy(how = How.ID, using = "tab_bus")
    private WebElement busesTab;

    @FindBy(how = How.ID, using = "sortby-price")
    private WebElement priceSortingFilter;

    @FindBy(how = How.ID, using = "sortby-traveltime")
    private WebElement travelTimeSortingFilter;


    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getPageUrl() {
        return Configuration.SEARCH_RESULTS_URL;
    }

    public void selectCheapestFilter() {
        logger.info("Selected on Cheapest filter");
        priceSortingFilter.click();
    }

    public String clickPlanesTab() {
        logger.info("Clicked on PlainsTab filter");
        planesTab.click();
        return planesTab.getAttribute("id");
    }

    public String clickTrainsTab() {
        logger.info("Clicked on TrainsTab filter");
        transTab.click();
        return transTab.getAttribute("id");
    }

    public String clickBusesTab() {
        logger.info("Clicked on BusesTab filter");
        busesTab.click();
        return busesTab.getAttribute("id");
    }

    public List<BigDecimal> extractPrices(String activeTabId) {
        logger.info("Extracting prises from the activeTab " + activeTabId);

        List<BigDecimal> prices = Lists.newArrayList();

        List<WebElement> pricesElements = driver.findElements(By.cssSelector("#"+activeTabId+" .price-no"));
        for (WebElement priceCell : pricesElements) {
            String text = priceCell.getText();
            prices.add(extractPriceFromString(text));
        }
        logger.info("Extracted prises " + prices);
        return prices;
    }

    private BigDecimal extractPriceFromString(String rawText) {
        String regex = "[^(\\d|\\.)]+";
        double value = Double.valueOf(rawText.replaceAll(regex, ""));
        return BigDecimal.valueOf(value);
    }

    public List<BigDecimal> sortPrices(List<BigDecimal> prices) {
        Collections.sort(prices, new Comparator<BigDecimal>() {
            @Override
            public int compare(BigDecimal a, BigDecimal b) {
                return a.compareTo(b);
            }
        });
        return prices;
    }

    @Override
    protected ExpectedCondition validationCondition() {
        logger.info("Validating fields presence");
        return ExpectedConditions.visibilityOfAllElements(Lists.newArrayList(
                transTab,
                busesTab,
                planesTab,
                priceSortingFilter,
                travelTimeSortingFilter
        ));
    }
}
