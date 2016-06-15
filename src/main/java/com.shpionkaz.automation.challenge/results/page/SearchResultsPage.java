package com.shpionkaz.automation.challenge.results.page;


import com.google.common.collect.Lists;
import com.shpionkaz.automation.challenge.BasePage;
import com.shpionkaz.automation.challenge.Configuration;
import com.shpionkaz.automation.challenge.results.data.SearchResultsPageConstants;
import com.shpionkaz.automation.challenge.utils.TestUtils;
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

public class SearchResultsPage extends BasePage implements SearchResultsPageConstants {

    @FindBy(how = How.ID, using = "tab_train")
    private WebElement transTab;

    @FindBy(how = How.ID, using = "tab_flight")
    private WebElement planesTab;

    @FindBy(how = How.ID, using = "tab_bus")
    private WebElement busesTab;

    @FindBy(how = How.CSS, using = "#results-train .price-no")
    private List<WebElement> priceCellsTrain;

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
        priceSortingFilter.click();
    }

    public void selectFastestFilter() {
        priceSortingFilter.click();
    }

    public List<BigDecimal> extractPrices() {
        TestUtils.sleep(2);
        List<BigDecimal> prices = Lists.newArrayList();
        for (WebElement priceCell : priceCellsTrain) {
            String text = priceCell.getText();
            double value = Double.valueOf(text.replaceAll("[^(\\d|\\.)]+", ""));
            prices.add(BigDecimal.valueOf(value));
        }
        return prices;
    }

    public List<BigDecimal> sortPrices() {
        List<BigDecimal> prises = extractPrices();
        Collections.sort(prises, new Comparator<BigDecimal>() {
            @Override
            public int compare(BigDecimal a, BigDecimal b) {
                return a.compareTo(b);
            }
        });
        return prises;
    }

    @Override
    protected ExpectedCondition validationCondition() {
        return ExpectedConditions.visibilityOfAllElements(Lists.newArrayList(
                transTab,
                busesTab,
                planesTab,
                priceSortingFilter,
                travelTimeSortingFilter
        ));
    }
}
