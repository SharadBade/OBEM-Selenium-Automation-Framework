package com.obem.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.obem.base.BasePage;

public class AlarmFddPage extends BasePage {

    // Replace placeholders with actual OBEM locators.
    private final By pageHeader =
            By.xpath("//h1[contains(normalize-space(),'Alarm') or contains(normalize-space(),'FDD')]");
    private final By severityFilter = By.id("severity");
    private final By statusFilter = By.id("alarm-status");
    private final By alarmRow =
            By.cssSelector("[data-testid='alarm-row']");
    private final By fddPanel =
            By.cssSelector("[data-testid='fdd-panel']");

    public AlarmFddPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return displayed(pageHeader);
    }

    public AlarmFddPage filterBySeverity(String severity) {
        selectVisibleText(severityFilter, severity);
        return this;
    }

    public AlarmFddPage filterByStatus(String status) {
        selectVisibleText(statusFilter, status);
        return this;
    }

    public boolean isAlarmListed() {
        return displayed(alarmRow);
    }

    public boolean isFddPanelDisplayed() {
        return displayed(fddPanel);
    }
}
