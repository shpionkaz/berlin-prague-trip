package com.shpionkaz.automation.challenge.landing.page;

import com.google.common.collect.Lists;
import com.shpionkaz.automation.challenge.BasePage;
import com.shpionkaz.automation.challenge.Configuration;
import com.shpionkaz.automation.challenge.landing.data.LandingPageConstants;
import com.shpionkaz.automation.challenge.results.page.SearchResultsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LandingPage extends BasePage implements LandingPageConstants {

    @FindBy(how = How.ID, using = DEPARTURE_INPUT_ID)
    private WebElement departureInputFilter;

    @FindBy(how = How.ID, using = ARRIVAL_INPUT_ID)
    private WebElement arrivalInputFilter;

    @FindBy(how = How.ID, using = SEARCH_FORM_SUBMIT_BUTTON)
    private WebElement submitFormButton;

    @FindBy(how = How.CSS, using = "body")
    private WebElement body;

    @FindBy(how = How.CSS, using = ".hotel-checkbox__airbnb")
    private WebElement airbnbCheckbox;


    public LandingPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getPageUrl() {
        return Configuration.LANDING_PAGE_URL;
    }

    public void fillDepartureField(String departure) {
        departureInputFilter.sendKeys(departure);
    }

    public void fillArrivalField(String destination) {
        arrivalInputFilter.sendKeys(destination);
    }

    public SearchResultsPage submitForm() {
        submitFormButton.click();
        return new SearchResultsPage(driver);
    }

    public void blurInputFocus(){
        body.click();
    }

    public void toggleAirbnbCheckBox(){
        airbnbCheckbox.click();
    }

    @Override
    protected ExpectedCondition validationCondition() {
        return ExpectedConditions.visibilityOfAllElements(Lists.newArrayList(
                departureInputFilter,
                arrivalInputFilter,
                airbnbCheckbox,
                submitFormButton
        ));
    }


}
