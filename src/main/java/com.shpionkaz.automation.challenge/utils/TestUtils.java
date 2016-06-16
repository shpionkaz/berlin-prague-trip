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

    public static void waitForPageLoad(WebDriver driver, ExpectedCondition pageLoadCondition) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Configuration.FLUENT_WAIT_TIMEOUT, TimeUnit.SECONDS)
                .pollingEvery(Configuration.FLUENT_WAIT_INTERVAL, TimeUnit.SECONDS);

        wait.until(pageLoadCondition);
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

    public static void prepareChromeDriver(){
        ClassLoader classLoader = TestUtils.class.getClassLoader();
        File chromedriver = new File(classLoader.getResource("chromedriver").getFile());
        chromedriver.setExecutable(true);
        System.setProperty("webdriver.chrome.driver", chromedriver.getAbsolutePath());
    }
}