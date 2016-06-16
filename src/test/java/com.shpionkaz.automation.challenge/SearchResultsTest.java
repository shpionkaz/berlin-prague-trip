package com.shpionkaz.automation.challenge;

import com.shpionkaz.automation.challenge.landing.page.LandingPage;
import com.shpionkaz.automation.challenge.results.page.SearchResultsPage;
import org.testng.annotations.*;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SearchResultsTest extends BaseTest {

    private LandingPage landingPage;
    private SearchResultsPage searchResultsPage;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        super.setUp();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUpTest() {
        landingPage = new LandingPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @AfterMethod(alwaysRun = true)
    public void deleteCookies() throws Exception {
        super.deleteCookies();
    }

    @Test
    public void testPriceSoringIsCorrectWithEnabledCheapestFilterForTrains(){
        landingPage.goToPage();
        landingPage.validatePage();
        landingPage.fillArrivalField("Prague");
        landingPage.fillDepartureField("Berlin");
        landingPage.blurInputFocus();
        landingPage.toggleAirbnbCheckBox();
        landingPage.submitForm();
        searchResultsPage.validatePage();
        searchResultsPage.selectCheapestFilter();

        String trainsTabId = searchResultsPage.clickTrainsTab();
        List<BigDecimal> prices = searchResultsPage.extractPrices(trainsTabId);
        List<BigDecimal> sortedPrices = searchResultsPage.sortPrices(prices);

        assertThat(prices, equalTo(sortedPrices));
    }

    @Test
    public void testPriceSoringIsCorrectWithEnabledCheapestFilterForFlights(){
        landingPage.goToPage();
        landingPage.validatePage();
        landingPage.fillArrivalField("Prague");
        landingPage.fillDepartureField("Berlin");
        landingPage.blurInputFocus();
        landingPage.toggleAirbnbCheckBox();
        landingPage.submitForm();
        searchResultsPage.validatePage();
        searchResultsPage.selectCheapestFilter();

        String planesTabId = searchResultsPage.clickPlanesTab();
        List<BigDecimal> prices = searchResultsPage.extractPrices(planesTabId);
        List<BigDecimal> sortedPrices = searchResultsPage.sortPrices(prices);

        assertThat(prices, equalTo(sortedPrices));
    }

    @Test
    public void testPriceSoringIsCorrectWithEnabledCheapestFilterForBuses(){
        landingPage.goToPage();
        landingPage.validatePage();
        landingPage.fillArrivalField("Prague");
        landingPage.fillDepartureField("Berlin");
        landingPage.blurInputFocus();
        landingPage.toggleAirbnbCheckBox();
        landingPage.submitForm();
        searchResultsPage.validatePage();
        searchResultsPage.selectCheapestFilter();
        String busesTabId = searchResultsPage.clickBusesTab();
        List<BigDecimal> prices = searchResultsPage.extractPrices(busesTabId);
        List<BigDecimal> sortedPrices = searchResultsPage.sortPrices(prices);

        assertThat(prices, equalTo(sortedPrices));
    }
}
