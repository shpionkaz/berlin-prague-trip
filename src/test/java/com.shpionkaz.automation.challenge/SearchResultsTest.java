package com.shpionkaz.automation.challenge;

import com.shpionkaz.automation.challenge.landing.page.LandingPage;
import com.shpionkaz.automation.challenge.results.page.SearchResultsPage;
import org.testng.annotations.*;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SearchResultsTest extends BaseTest {

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        super.setUp();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUpTest() {
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
    public void testPriceSoringIsCorrectWithEnabledCheapestFilter() throws Exception {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goToPage();
        landingPage.validatePage();
        landingPage.fillArrivalField("Prague");
        landingPage.fillDepartureField("Berlin");
        landingPage.blurInputFocus();
        landingPage.toggleAirbnbCheckBox();
        SearchResultsPage searchResultsPage = landingPage.submitForm();
        searchResultsPage.validatePage();
        searchResultsPage.selectCheapestFilter();
        List<BigDecimal> prices = searchResultsPage.extractPrices();
        List<BigDecimal> sortedPrices = searchResultsPage.sortPrices();

        assertThat(prices, equalTo(sortedPrices));


    }
}
