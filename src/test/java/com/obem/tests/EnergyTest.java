package com.obem.tests;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.obem.base.BaseTest;
import com.obem.pages.EnergyPage;
import com.obem.pages.LoginPage;
import com.obem.utils.ConfigReader;

public class EnergyTest extends BaseTest {

    @Test(description = "Verify Energy module dashboard", groups = {"regression"})
    public void verifyEnergyDashboard() {
        requireCredentials();

        EnergyPage energy = new LoginPage(driver)
                .login(ConfigReader.get("username"), ConfigReader.get("password"))
                .openEnergyModule();

        Assert.assertTrue(energy.isDisplayed(),
                "Energy module should be displayed.");

        Assert.assertTrue(energy.isConsumptionCardDisplayed(),
                "Energy consumption card should be displayed.");

        Assert.assertTrue(energy.isTrendChartDisplayed(),
                "Energy trend chart should be displayed.");
    }

    @Test(description = "Verify Energy period filter", groups = {"regression"})
    public void verifyEnergyPeriodFilter() {
        requireCredentials();

        EnergyPage energy = new LoginPage(driver)
                .login(ConfigReader.get("username"), ConfigReader.get("password"))
                .openEnergyModule();

        energy.selectPeriod("Last 7 Days");

        Assert.assertTrue(energy.isTrendChartDisplayed(),
                "Energy trend chart should remain visible after period selection.");
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
