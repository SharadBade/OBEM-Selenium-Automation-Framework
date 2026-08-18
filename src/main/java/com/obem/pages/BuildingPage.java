package com.obem.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.obem.base.BasePage;

public class BuildingPage extends BasePage {

    // Building page heading
    private final By pageHeader =
            By.xpath("//h1[contains(normalize-space(),'Building')]");

    // Building search input
    private final By buildingSearch =
            By.id("building-search");

    // Building search button
    private final By buildingSearchButton =
            By.id("building-search-button");

    // Building table row
    private final By buildingRow =
            By.cssSelector("[data-testid='building-row']");

    // Clickable building link inside first row
    private final By firstBuildingLink =
            By.cssSelector("[data-testid='building-row'] a");

    // Building details section
    private final By buildingDetails =
            By.cssSelector("[data-testid='building-details']");

    public BuildingPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Verify Building page is displayed.
     */
    public boolean isDisplayed() {

        return displayed(pageHeader);
    }

    /**
     * Search for a building.
     */
    public BuildingPage searchBuilding(String buildingName) {

        type(buildingSearch, buildingName);

        click(buildingSearchButton);

        return this;
    }

    /**
     * Verify that at least one building is displayed.
     */
    public boolean isBuildingListed() {

        return displayed(buildingRow);
    }

    /**
     * Open the first building from the table.
     */
    public BuildingPage openFirstBuilding() {

        click(firstBuildingLink);

        return this;
    }

    /**
     * Verify Building Details page.
     */
    public boolean isBuildingDetailsDisplayed() {

        return displayed(buildingDetails);
    }
}