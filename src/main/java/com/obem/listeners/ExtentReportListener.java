package com.obem.listeners;

import java.io.File;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.obem.driver.DriverFactory;
import com.obem.utils.ScreenshotUtils;

public class ExtentReportListener implements ITestListener {

    private static final ExtentReports EXTENT = createReport();
    private static final ThreadLocal<ExtentTest> TEST = new ThreadLocal<>();

    private static ExtentReports createReport() {
        new File("test-output").mkdirs();

        ExtentSparkReporter spark =
                new ExtentSparkReporter("test-output/ExtentReport.html");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Project", "OpenBlue Enterprise Manager");
        extent.setSystemInfo("Framework", "Selenium + TestNG + Maven");
        extent.setSystemInfo("Language", "Java 17");
        return extent;
    }

    @Override
    public void onStart(ITestContext context) {
        EXTENT.setSystemInfo("Suite", context.getSuite().getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        TEST.set(EXTENT.createTest(
                result.getTestClass().getName() + "." +
                result.getMethod().getMethodName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        TEST.get().pass("Test passed");
        TEST.remove();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        TEST.get().fail(result.getThrowable());

        if (DriverFactory.getDriver() != null) {
            String screenshot = ScreenshotUtils.capture(
                    DriverFactory.getDriver(),
                    result.getMethod().getMethodName());

            if (!screenshot.isBlank()) {
                try {
                    TEST.get().addScreenCaptureFromPath(screenshot);
                } catch (Exception ignored) {
                }
            }
        }

        TEST.remove();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        TEST.get().skip("Test skipped");
        TEST.remove();
    }

    @Override
    public void onFinish(ITestContext context) {
        EXTENT.flush();
    }
}
