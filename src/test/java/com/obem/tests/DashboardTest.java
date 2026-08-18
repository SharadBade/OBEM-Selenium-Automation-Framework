package com.obem.tests;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.obem.base.BaseTest;
import com.obem.pages.DashboardPage;
import com.obem.pages.LoginPage;
import com.obem.utils.ConfigReader;

public class DashboardTest extends BaseTest {

    @Test(description = "Verify dashboard is loaded after login", groups = {"smoke", "regression"})
    public void verifyDashboard() {
        requireCredentials();

        DashboardPage dashboard = new LoginPage(driver)
                .login(ConfigReader.get("username"), ConfigReader.get("password"));

        Assert.assertTrue(
                dashboard.isDisplayed(),
                "OBEM Dashboard should be displayed.");
    }

    @Test(description = "Verify dashboard navigation to Building", groups = {"regression"})
    public void verifyBuildingNavigation() {
        requireCredentials();

        DashboardPage dashboard = new LoginPage(driver)
                .login(ConfigReader.get("username"), ConfigReader.get("password"));

        Assert.assertTrue(
                dashboard.openBuildingModule().isDisplayed(),
                "Building module should open from Dashboard.");
    }

    private void requireCredentials() {
        if (ConfigReader.get("username") == null
                || ConfigReader.get("username").isBlank()
                || ConfigReader.get("password") == null
                || ConfigReader.get("password").isBlank()) {
            throw new SkipException("OBEM credentials are not configured.");
        }
    }
}
