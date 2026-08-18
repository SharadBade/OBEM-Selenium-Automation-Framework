package com.obem.driver;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public final class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {}

    public static void initDriver(String browser, boolean headless) {
        WebDriver webDriver;

        if ("edge".equalsIgnoreCase(browser)) {
            EdgeOptions options = new EdgeOptions();
            if (headless) {
                options.addArguments("--headless=new");
            }
            options.addArguments("--window-size=1920,1080");
            webDriver = new EdgeDriver(options);
        } else {
            ChromeOptions options = new ChromeOptions();
            if (headless) {
                options.addArguments("--headless=new");
            }
            options.addArguments("--window-size=1920,1080");
            webDriver = new ChromeDriver(options);
        }

        DRIVER.set(webDriver);
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        getDriver().manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return DRIVER.get();
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}
