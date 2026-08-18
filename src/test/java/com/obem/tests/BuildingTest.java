package com.obem.tests;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.obem.base.BaseTest;
import com.obem.pages.BuildingPage;
import com.obem.pages.LoginPage;
import com.obem.utils.ConfigReader;

public class BuildingTest extends BaseTest {

    @Test(
        description = "Verify Building module and building search",
        groups = {"regression"}
    )
    public void verifyBuildingSearch() {

        requireCredentials();

        BuildingPage building = new LoginPage(driver)
                .login(
                    ConfigReader.get("username"),
                    ConfigReader.get("password")
                )
                .openBuildingModule();

        Assert.assertTrue(
                building.isDisplayed(),
                "Building page should be displayed."
        );

        building.searchBuilding("Pune Tower");

        Assert.assertTrue(
                building.isBuildingListed(),
                "Matching building should be displayed."
        );
    }


    @Test(
        description = "Verify opening building details",
        groups = {"regression"}
    )
    public void verifyBuildingDetails() {

        requireCredentials();

        BuildingPage building = new LoginPage(driver)
                .login(
                    ConfigReader.get("username"),
                    ConfigReader.get("password")
                )
                .openBuildingModule();

        Assert.assertTrue(
                building.openFirstBuilding()
                        .isBuildingDetailsDisplayed(),
                "Building details should be displayed."
        );
    }


    /**
     * Verify that OBEM credentials are configured.
     */
    private void requireCredentials() {

        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");

        if (username == null
                || username.isBlank()
                || password == null
                || password.isBlank()) {

            throw new SkipException(
                    "OBEM credentials are not configured."
            );
        }
    }
}