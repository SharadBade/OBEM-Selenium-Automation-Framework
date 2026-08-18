package com.obem.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.obem.base.BasePage;

public class EnergyPage extends BasePage {

    // Replace placeholders with actual OBEM locators.
    private final By pageHeader =
            By.xpath("//h1[contains(normalize-space(),'Energy')]");
    private final By consumptionCard =
            By.cssSelector("[data-testid='energy-consumption']");
    private final By periodDropdown = By.id("energy-period");
    private final By trendChart =
            By.cssSelector("[data-testid='energy-trend-chart']");

    public EnergyPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return displayed(pageHeader);
    }

    public boolean isConsumptionCardDisplayed() {
        return displayed(consumptionCard);
    }

    public EnergyPage selectPeriod(String period) {
        selectVisibleText(periodDropdown, period);
        return this;
    }

    public boolean isTrendChartDisplayed() {
        return displayed(trendChart);
    }
}
