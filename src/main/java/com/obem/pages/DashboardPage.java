package com.obem.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.obem.base.BasePage;

public class DashboardPage extends BasePage {

    // Replace placeholders with actual OBEM locators.
    private final By dashboardHeader =
            By.xpath("//h1[contains(normalize-space(),'Dashboard')]");

    private final By buildingMenu = By.id("building");
    private final By energyMenu = By.id("energy");
    private final By alarmFddMenu = By.id("alarm-fdd");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return displayed(dashboardHeader);
    }

    public BuildingPage openBuildingModule() {
        click(buildingMenu);
        return new BuildingPage(driver);
    }

    public EnergyPage openEnergyModule() {
        click(energyMenu);
        return new EnergyPage(driver);
    }

    public AlarmFddPage openAlarmFddModule() {
        click(alarmFddMenu);
        return new AlarmFddPage(driver);
    }
}
