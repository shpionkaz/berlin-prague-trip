package com.shpionkaz.automation.challenge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestExecutionListener implements ITestListener {

    private Logger logger = LoggerFactory.getLogger(TestExecutionListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        logger.info(iTestResult.getName() + "");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logger.info(iTestResult.getName() + " ran successfully, took " + (iTestResult.getEndMillis() - iTestResult.getStartMillis()));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.error(iTestResult.getName() + " failed ", iTestResult.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger.error(iTestResult.getName() + " skipped ", iTestResult.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        logger.error(iTestResult.getName() + " failed, but within success percentage ", iTestResult.getThrowable());
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }
}
