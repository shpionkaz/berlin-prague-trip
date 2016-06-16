package com.shpionkaz.automation.challenge.landing.page;

import com.google.common.collect.Lists;
import com.shpionkaz.automation.challenge.BasePage;
import com.shpionkaz.automation.challenge.Configuration;
import com.shpionkaz.automation.challenge.landing.data.LandingPageProviders;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LandingPage extends BasePage implements LandingPageProviders {

    @FindBy(how = How.ID, using = DepartureInput)
    private WebElement departureInputFilter;

    @FindBy(how = How.ID, using = ArrivalInput)
    private WebElement arrivalInputFilter;

    @FindBy(how = How.ID, using = SearchFormSubmitButton)
    private WebElement submitFormButton;

    @FindBy(how = How.CSS, using = Body)
    private WebElement body;

    @FindBy(how = How.CSS, using = AirbnbCheckbox)
    private WebElement airbnbCheckbox;


    public LandingPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getPageUrl() {
        return Configuration.LANDING_PAGE_URL;
    }

    public void fillDepartureField(String departure) {
        logger.info("Filling departure input filed with value " + departure);
        departureInputFilter.sendKeys(departure);
    }

    public void fillArrivalField(String destination) {
        logger.info("Filling arrival input filed with value " + destination);
        arrivalInputFilter.sendKeys(destination);
    }

    public void submitForm() {
        logger.info("Submitting search form");
        submitFormButton.click();
    }

    public void blurInputFocus() {
        logger.info("Clicked outside of input fields to issue blur event");
        body.click();
    }

    public void toggleAirbnbCheckBox() {
        logger.info("Toggled airbnb checkbox");
        airbnbCheckbox.click();
    }

    @Override
    protected ExpectedCondition validationCondition() {
        logger.info("Validating fields presence");
        return ExpectedConditions.visibilityOfAllElements(Lists.newArrayList(
                departureInputFilter,
                arrivalInputFilter,
                airbnbCheckbox,
                submitFormButton
        ));
    }


}
