package com.obem.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.obem.base.BasePage;

public class AlarmFddPage extends BasePage {

    // Alarm/FDD page heading
    private final By pageHeader =
            By.xpath("//h1[contains(normalize-space(),'Alarm') or contains(normalize-space(),'FDD')]");

    // Severity dropdown
    private final By severityFilter =
            By.id("severity");

    // Alarm result rows
    private final By alarmRows =
            By.cssSelector("[data-testid='alarm-row']");

    public AlarmFddPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Verify Alarm/FDD page is displayed.
     */
    public boolean isDisplayed() {
        return displayed(pageHeader);
    }

    /**
     * Select the required alarm severity.
     */
    public AlarmFddPage selectSeverity(String severity) {
        selectVisibleText(severityFilter, severity);
        return this;
    }

    /**
     * Verify the selected severity.
     */
    public boolean isSeveritySelected(String expectedSeverity) {

        String selectedSeverity =
                driver.findElement(severityFilter)
                      .getAttribute("value");

        return selectedSeverity.equalsIgnoreCase(expectedSeverity);
    }

    /**
     * Verify alarm results are displayed.
     */
    public boolean areAlarmResultsDisplayed() {
        return displayed(alarmRows);
    }
}
