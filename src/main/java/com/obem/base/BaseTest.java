package com.obem.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.obem.driver.DriverFactory;
import com.obem.utils.ConfigReader;

public abstract class BaseTest {

    protected WebDriver driver;

    protected final Logger logger =
            LogManager.getLogger(getClass());

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        // Read browser configuration
        String browser = ConfigReader.get("browser");

        // Read headless configuration
        boolean headless = ConfigReader.getBoolean("headless");

        // Read application URL
        String baseUrl = ConfigReader.get("baseUrl");

        // Validate configuration
        if (browser == null || browser.isBlank()) {
            throw new IllegalArgumentException(
                    "Browser is not configured in config.properties");
        }

        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalArgumentException(
                    "baseUrl is not configured in config.properties");
        }

        logger.info("Starting browser: {}", browser);
        logger.info("Headless mode: {}", headless);
        logger.info("Application URL: {}", baseUrl);

        // Start WebDriver
        DriverFactory.initDriver(browser, headless);

        // Get driver for current thread
        driver = DriverFactory.getDriver();

        // Open Local OBEM
        driver.get(baseUrl);

        logger.info("Local OBEM application opened successfully.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        logger.info("Closing browser.");

        DriverFactory.quitDriver();
    }
}