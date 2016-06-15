package com.shpionkaz.automation.challenge.utils;

import com.shpionkaz.automation.challenge.Configuration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestUtils {

    private static Logger logger = LoggerFactory.getLogger(TestUtils.class);

    public static ExpectedCondition<WebElement> visibilityOfElementLocated(final By locator) {
        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement toReturn = driver.findElement(locator);
                if (toReturn.isDisplayed()) {
                    return toReturn;
                }
                return null;
            }
        };
    }

    public static void waitForPageLoad(WebDriver driver, ExpectedCondition pageLoadCondition) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Configuration.FLUENT_WAIT_TIMEOUT, TimeUnit.SECONDS)
                .pollingEvery(Configuration.FLUENT_WAIT_INTERVAL, TimeUnit.SECONDS);

        wait.until(pageLoadCondition);
    }

    public static WebElement waitUntilClickable(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, 45);
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }


    public static WebElement switchToNewWindow(WebDriver driver, String iframeId) {
        driver.switchTo().frame(iframeId);
        return driver.switchTo().activeElement();
    }

    public static boolean isEnabled(WebDriver driver, String eachField) {
        return (driver.findElement(By.id(eachField)).isEnabled());
    }

    public static boolean isDisabled(WebDriver driver, String eachField) {
        return (!driver.findElement(By.id(eachField)).isEnabled());
    }

    public static boolean isVisible(WebDriver driver, String eachField) {
        return (driver.findElement(By.id(eachField)).isDisplayed());
    }

    public static boolean isInvisible(WebDriver driver, String eachField) {
        return (!driver.findElement(By.id(eachField)).isDisplayed());
    }

    public static String captureScreenshot(WebDriver driver, String folder, String fileName) {

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File targetFile = new File(folder, fileName + ".png");
        try {
            FileUtils.copyFile(screenshot, targetFile);
        } catch (IOException e) {
            logger.error("Error while writing file ", e);
        }

        return targetFile.getAbsolutePath();
    }

    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}