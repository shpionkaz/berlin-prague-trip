package com.shpionkaz.automation.challenge;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.concurrent.TimeUnit;

import static com.shpionkaz.automation.challenge.utils.TestUtils.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.startsWith;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().pageLoadTimeout(Configuration.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        initAnnotations();
    }

    public void initAnnotations() {
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Configuration.AJAX_LOCATOR_FACTORY_TIMEOUT);
        PageFactory.initElements(finder, this);
    }

    public void goToPage() {
        driver.get(getFullPageUrl());
    }

    public void validatePage() {
        ExpectedCondition pageValidationCondition = validationCondition();
        waitForPageLoad(driver, pageValidationCondition);
        assertThat(driver.getCurrentUrl(), startsWith(getFullPageUrl()));
    }

    public String getFullPageUrl() {
        return Configuration.BASE_URL + getPageUrl();
    }

    public abstract String getPageUrl();

    protected abstract ExpectedCondition validationCondition();

}