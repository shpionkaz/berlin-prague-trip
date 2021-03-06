package com.shpionkaz.automation.challenge;

import com.shpionkaz.automation.challenge.utils.TestUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    WebDriver driver;

    public void setUp() throws Exception {
       TestUtils.prepareChromeDriver();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1024, 768));
    }

    public void tearDown() throws Exception {
        driver.close();
    }

    public void deleteCookies() throws Exception {
        driver.manage().deleteAllCookies();
    }
}



