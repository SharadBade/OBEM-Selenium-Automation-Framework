package com.obem.base;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Logger logger = LogManager.getLogger(getClass());

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    protected void click(By locator) {
        logger.info("Clicking: {}", locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String value) {
        logger.info("Typing into: {}", locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(value);
    }

    protected String text(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    protected boolean displayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void selectVisibleText(By locator, String value) {
        org.openqa.selenium.support.ui.Select select =
                new org.openqa.selenium.support.ui.Select(
                        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)));
        select.selectByVisibleText(value);
    }
}
