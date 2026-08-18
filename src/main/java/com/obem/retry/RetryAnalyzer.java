package com.obem.retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.obem.utils.ConfigReader;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private final int maxRetryCount = ConfigReader.getInt("retryCount");

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}
